package com.chilangolabs.modelonow.customwidgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import com.chilangolabs.modelonow.R;

/**
 * @author gorro
 */
public class ButtonMontserrat extends Button {

    Typeface tf;

    public ButtonMontserrat(Context context) {
        super(context);
        initStyle();
    }

    public ButtonMontserrat(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.montserrat, 0, 0);
        try {
            int font = a.getInteger(R.styleable.montserrat_type, 0);
            initStyle(font);
        } finally {
            a.recycle();
        }
    }

    private void initStyle() {
        tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-Regular.otf");
        this.setTypeface(tf);

    }

    private void initStyle(int font) {
        switch (font) {
            case 0:
                tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-Regular.otf");
                this.setTypeface(tf);
                break;
            case 1:
                tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-SemiBold.ttf");
                this.setTypeface(tf);
                break;
            case 2:
                tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-Bold.otf");
                this.setTypeface(tf);
                break;
            default:
                tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-Regular.otf");
                this.setTypeface(tf);
                break;
        }
    }
}
