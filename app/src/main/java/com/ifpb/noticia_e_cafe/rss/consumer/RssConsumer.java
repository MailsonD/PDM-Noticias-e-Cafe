package com.ifpb.noticia_e_cafe.rss.consumer;


import android.text.Html;
import android.util.Log;

import com.ifpb.noticia_e_cafe.exception.ConnectionUrlException;
import com.ifpb.noticia_e_cafe.exception.ConsumerExcpetion;
import com.ifpb.noticia_e_cafe.exception.DrawableCreatorExcpetion;
import com.ifpb.noticia_e_cafe.model.entities.Noticia;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.ifpb.noticia_e_cafe.util.CreateConnectionUrl.getInputStream;
import static com.ifpb.noticia_e_cafe.util.DrawableCreator.gerarDrawable;
import static com.ifpb.noticia_e_cafe.util.ImgFinder.encontraImg;

/**
 * @author Mailson
 * @mail mailssondennis@gmail.com
 * @see Noticia
 */
public class RssConsumer {

    public static final String URL_RADARSERTANEJO = "https://www.radarsertanejo.com/";
    public static final String URL_UIRAUNANET = "http://uirauna.net/";
    public static final String URL_RADARPB = "http://radarpb.com.br/";

    /**
     * Método para consumir os dados de um arquivo RSS e criar uma lista de notícias através desse arquivo.
     *
     * @param urlString -> Se refere a url do arquivo RSS onde vai ser feito o consumo
     * @return List<Noticia> quando o consumo é concluído com sucesso
     * @throws ConsumerExcpetion quando se tem algum erro no consumo do arquivo
     */
    public static List<Noticia> consume(String urlString) throws ConsumerExcpetion {
        List<Noticia> noticias = new ArrayList<>();
        try {
            //Adiciona o path /feed a url do site que se deseja consumir o RSS
            URL url = new URL(urlString + "feed");

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

            //Diz a fábria para ignorar namespaces na leitura do XML
            factory.setNamespaceAware(false);

            //Constroi um novo XmlPullParser, que vai ser responsável por ler o XML inteiro
            XmlPullParser xpp = factory.newPullParser();

            //Aponta para o xpp o local de onde ele deve consumir o XML e qual o encode que ele deve seguir
            xpp.setInput(getInputStream(url), "UTF_8");

            //Variável responsável por dizer se o ponteiro do XML se encontra dentro ou fora do Item da notícia
            boolean insideItem = false;

            //Identificador de tags do xpp, para sabermos em que tag estamos
            int eventType = xpp.getEventType();


            Noticia noticia = null;
            //Realiza a leitura do XML, linha por linha
            while (eventType != XmlPullParser.END_DOCUMENT) {
                try {
                    //Testa se estamos no início de uma tag
                    if (eventType == XmlPullParser.START_TAG) {
                        //Testa se esta tag é uma abertura de Item
                        if (xpp.getName().equalsIgnoreCase(Item.ABERTURA.value())) {
                            noticia = new Noticia();
                            noticia.setSiteFonte(urlString);
                            insideItem = true;
                            //Cria uma nova notícia caso estejamos no início de um Item
                            //E a partir daí ele vai adicionando os atributos a notícia
                        } else if (xpp.getName().equalsIgnoreCase(Item.ID.value())) {
                            if (insideItem) {
                                noticia.setGuid(xpp.nextText());
                            }
                        } else if (xpp.getName().equalsIgnoreCase(Item.TITULO.value())) {
                            if (insideItem) {
                                noticia.setTitulo(xpp.nextText());
                            }
                        } else if (xpp.getName().equalsIgnoreCase(Item.PUBLICACAO.value())) {
                            if (insideItem) {
                                noticia.setDataPublicacao(xpp.nextText());
                            }
                        } else if (xpp.getName().equalsIgnoreCase(Item.CONTEUDO.value())) {
                            if (insideItem) {
                                //O conteúdo da noticia vem com várias tags HTML dentro dele, por isso precisamos fazer um tratamento.
                                String html = xpp.nextText();
                                //Geramos a imagem da notícia a partidir do conteúdo
                                String urlImg = encontraImg(html);
                                noticia.setUrlImg(urlImg);
//                            if(urlImg != null){
//                                noticia.setImg(gerarDrawable(new URL(urlImg)));
//                            }else{
//                                noticia.setImg(null);
                                //Utilizamos o interpretador de html para formatar o texto do conteúdo para a gente
                                noticia.setConteudo(Html.fromHtml(html).toString());
                            }
                        } else if (xpp.getName().equalsIgnoreCase(Item.DESCRICAO.value())) {
                            if (insideItem) {
                                noticia.setDecricao(Html.fromHtml(xpp.nextText()).toString());
                            }
                        } else if (xpp.getName().equalsIgnoreCase(Item.LINK.value())) {
                            if (insideItem) {
                                noticia.setLink(xpp.nextText());
                            }
                        }
                    } else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase(Item.ABERTURA.value())) {
                        insideItem = false;
                        //Não estamos mais dentro do item, então nossa notícia está concluída e a adicionamos a lista
                        noticias.add(noticia);
                    }
                    eventType = xpp.next();
                    //Testamos se chegamos ao fim de um tag e se essa tag é a tag Item.
                } catch (Exception e){
                    Log.e("APP_ERROR","Falha ao ler uma linha do XML");
                    e.printStackTrace();
                    eventType = xpp.next();

                }
            }
        } catch (XmlPullParserException e1) {
            Log.e("APP_ERROR","Falha ao ler o XML");
            e1.printStackTrace();
        } catch (MalformedURLException e) {
            Log.e("APP_ERROR","Url errada: "+urlString);
            e.printStackTrace();
        } catch (ConnectionUrlException e) {
            Log.e("APP_ERROR","Falha ao abrir a conexão com a url");
            e.printStackTrace();
        } catch (IOException e1) {
            Log.e("APP_ERROR","Falha ao abrir o arquivo");
            e1.printStackTrace();
        }
        return noticias;

    }


    private enum Item {
        ABERTURA("item"),
        TITULO("title"),
        LINK("link"),
        PUBLICACAO("pubdate"),
        DESCRICAO("description"),
        CONTEUDO("content:encoded"),
        ID("guid");


        private final String value;

        Item(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }

    }


}
