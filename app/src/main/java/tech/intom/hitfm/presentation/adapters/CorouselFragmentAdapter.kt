package tech.intom.hitfm.presentation.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import tech.intom.hitfm.presentation.models.CarouselModel
import tech.intom.hitfm.presentation.screens.carousel.CarouselBodyFragment

class CorouselFragmentAdapter(fragmentManager: FragmentManager, private val data: List<CarouselModel>) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(p0: Int): Fragment {
        val fragment = CarouselBodyFragment()
        fragment.setCatouselModel(data[p0])
        return fragment
    }

    override fun getCount(): Int {
        return data.size
    }
}