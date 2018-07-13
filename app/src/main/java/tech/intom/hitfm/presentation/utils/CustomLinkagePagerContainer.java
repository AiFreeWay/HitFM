package tech.intom.hitfm.presentation.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.LinkagePager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import me.crosswall.lib.coverflow.core.LinkagePagerContainer;

public class CustomLinkagePagerContainer extends LinkagePagerContainer {

    private LinkagePager mPager;

    public CustomLinkagePagerContainer(Context context) {
        super(context);
    }

    public CustomLinkagePagerContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLinkagePagerContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @SuppressLint({"MissingSuperCall"})
    protected void onFinishInflate() {
        try {
            this.mPager = (LinkagePager)this.getChildAt(0);
            this.mPager.addOnPageChangeListener(this);
        } catch (Exception var2) {
            throw new IllegalStateException("The root child of PagerContainer must be a ViewPager");
        }
    }

    public boolean onTouchEvent(MotionEvent ev) {
        return this.mPager.dispatchTouchEvent(ev);
    }

    public LinkagePager getViewPager() {
        return this.mPager;
    }
}
