package tech.intom.hitfm.presentation.utils

import android.support.v4.view.ViewPager

class CorousePageChangeListener(private val imagePager: ViewPager) :
        ViewPager.OnPageChangeListener {

    private var index = 0

    override fun onPageSelected(position: Int) {
        index = position

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        val width = imagePager.width
        imagePager.scrollTo((width * position + width * positionOffset).toInt(), 0)
    }

    override fun onPageScrollStateChanged(state: Int) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            imagePager.currentItem = index
        }
    }
}