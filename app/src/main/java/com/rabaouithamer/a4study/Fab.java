package com.rabaouithamer.a4study;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.support.design.widget.FloatingActionButton;
import com.gordonwong.materialsheetfab.AnimatedFab;

/**
 * Created by Rabaoui Thamer on 08/10/2016.
 */

public class Fab extends FloatingActionButton implements AnimatedFab {

    public Fab(Context context) {
        super(context);
        init(context);
    }

    public Fab(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Fab(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        //do stuff that was in your original constructor...
    }

    @Override
    public void show() {
        show(0, 0);
    }

    /**
     * Shows the FAB and sets the FAB's translation.
     *
     * @param translationX translation X value
     * @param translationY translation Y value
     */
    @Override
    public void show(float translationX, float translationY) {
        // NOTE: Using the parameters is only needed if you want
        // to support moving the FAB around the screen.
        // NOTE: This immediately hides the FAB. An animation can
        // be used instead - see the sample app.
        setVisibility(View.VISIBLE);
    }

    /**
     * Hides the FAB.
     */
    @Override
    public void hide() {
        // NOTE: This immediately hides the FAB. An animation can
        // be used instead - see the sample app.
        setVisibility(View.INVISIBLE);
    }

}
