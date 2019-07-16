package com.ifpb.noticia_e_cafe.component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ImageComponent extends LinearLayout {

    private ImageView imageView;
    private Drawable drawableImage;

    private int width = -1;
    private int heigth = -1;
    private int gravity = Gravity.CENTER_HORIZONTAL;
    private int orientation = HORIZONTAL;

    public ImageComponent(Context context,Drawable drawableImage){
        super(context);
        this.drawableImage = drawableImage;
        init();
    }

    public ImageComponent(Context context,Drawable drawableImage,int width,int heigth){
        super(context);
        construct(drawableImage,width,heigth);
        init();
    }

    public ImageComponent(Context context,Drawable drawableImage,int width,int heigth, int gravity, int orientation){
        super(context);
        construct(drawableImage,width,heigth);
        this.gravity = gravity;
        this.orientation = orientation;
    }

    private void construct(Drawable drawableImage, int width, int heigth){
        this.drawableImage = drawableImage;
        this.width = width;
        this.heigth = heigth;
    }

    private void init() {
        int widthParam = width < 0 ? LayoutParams.WRAP_CONTENT : width;
        int heigthParam = heigth < 0 ? LayoutParams.WRAP_CONTENT : width;
        setLayoutParams(new LayoutParams(widthParam,heigthParam));
        setOrientation(orientation);
        setGravity(gravity);

        imageView = new ImageView(getContext());
        imageView.setImageDrawable(drawableImage);

        addView(imageView);
    }

}
