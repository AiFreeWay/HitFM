package tech.intom.hitfm.presentation.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import tech.intom.hitfm.presentation.screens.radio.RadioItemFragment

class CorouselFragmentAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(p0: Int): Fragment {
        return RadioItemFragment()
    }

    override fun getCount(): Int {
        return 4
    }
}