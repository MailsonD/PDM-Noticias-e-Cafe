package com.ifpb.noticia_e_cafe.tela;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.ifpb.noticia_e_cafe.R;
import com.ifpb.noticia_e_cafe.component.NewsDetailsComponent;

public class TelaDetalhesNoticia extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scrollView = new ScrollView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView.setLayoutParams(layoutParams);
        String conteudo = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer luctus leo id dignissim mollis. Aenean consequat, ligula eget blandit pellentesque, augue eros sodales neque, vitae volutpat neque mauris non nibh. Aenean ultricies urna in sodales molestie. Sed ac commodo enim, nec scelerisque ligula. Vivamus dignissim nulla urna, nec faucibus augue consectetur sit amet. Suspendisse sed placerat sem, ac placerat diam. Etiam arcu sem, porta non eros ac, pulvinar volutpat sapien. Etiam porttitor ut nisl vel placerat. Suspendisse hendrerit in risus in condimentum.\n" +
                "\n" +
                "Etiam in gravida nisi. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Praesent maximus, neque in gravida lacinia, odio dolor feugiat urna, id scelerisque diam ante ac erat. Maecenas vestibulum non ante quis pulvinar. Vestibulum id ligula in velit interdum lacinia quis non sapien. Proin erat libero, maximus non eleifend in, feugiat a felis. Quisque lacinia enim vitae libero dapibus pretium.\n" +
                "\n" +
                "Curabitur at lectus dictum lectus bibendum tempor eu eget sapien. Pellentesque sagittis dictum urna, blandit congue nulla pharetra non. Nam tincidunt viverra sem, nec pretium metus elementum a. Duis vel maximus purus, ac feugiat massa. In mattis ante neque, eu porttitor mi lobortis id. Vestibulum tincidunt dignissim arcu, ut cursus metus mattis id. Donec bibendum, arcu a aliquet dapibus, mauris nunc vehicula nisl, vitae scelerisque sem purus id quam.\n" +
                "\n" +
                "Curabitur eros lorem, imperdiet sed tellus vel, commodo dignissim felis. Sed quis sapien dui. Praesent eros risus, scelerisque ac lacus non, imperdiet rutrum nunc. Fusce condimentum enim vel enim posuere, sed dapibus erat tempor. Aenean mi elit, tincidunt non dui efficitur, consequat rhoncus eros. Aliquam ullamcorper faucibus hendrerit. Duis faucibus commodo lacus porta rhoncus. In hac habitasse platea dictumst. Vestibulum vitae velit vel lorem porttitor varius at quis nulla. Nulla a dui sapien.";
        ImageView capaNoticia = new ImageView(this);
        capaNoticia.setImageResource(R.drawable.logo);
        NewsDetailsComponent newsDetailsComponent = new NewsDetailsComponent(this, "Este é o titulo da notícia para que eu teste esta bagaça", conteudo, "01/02/1998", "https://www.lipsum.com/feed/html", capaNoticia, getWindowManager());
        scrollView.addView(newsDetailsComponent);

        setContentView(scrollView);

    }
}
