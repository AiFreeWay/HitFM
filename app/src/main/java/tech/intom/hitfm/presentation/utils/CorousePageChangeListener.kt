package tech.intom.hitfm.presentation.utils

import android.support.v4.view.LinkagePager
import android.widget.TextView

class CorousePageChangeListener(private val text: TextView, private val btn: TextView) :
        LinkagePager.OnPageChangeListener {

    private var index = 0

    override fun onPageSelected(position: Int) {

        var translationX = 0f

        if (index > position) {
            translationX = 150f
        } else {
            translationX = -150f
        }

        text.animate()
                .translationX(translationX)
                .setDuration(200)
                .withEndAction { text.translationX = 0f }
                .start()

        btn.animate()
                .translationX(translationX)
                .setDuration(200)
                .withEndAction { btn.translationX = 0f }
                .start()

        index = position

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageScrollStateChanged(state: Int) {}
}