package tech.intom.hitfm.presentation.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import tech.intom.hitfm.presentation.screens.common.CarouselBodyFragment

class CorouselFragmentAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(p0: Int): Fragment {
        return CarouselBodyFragment()
    }

    override fun getCount(): Int {
        return 4
    }
}