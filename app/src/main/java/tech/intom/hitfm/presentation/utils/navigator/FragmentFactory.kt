package tech.intom.hitfm.presentation.utils.navigator

import android.os.Bundle
import android.support.v4.app.Fragment
import tech.intom.hitfm.application.exceptions.exceptions.InvalidFragmentException
import tech.intom.hitfm.presentation.screens.news.NewsFragment
import tech.intom.hitfm.presentation.screens.programs.ProgramsFragment
import tech.intom.hitfm.presentation.screens.radio.RadioFragment

/**
 * Created by root on 09.04.18.
 */
object FragmentFactory {

    val RADIO_FRAGMENT_TAG = RadioFragment::class.java.canonicalName
    val PROGRAMS_FRAGMENT_TAG = ProgramsFragment::class.java.canonicalName
    val NEWS_FRAGMENT_TAG = NewsFragment::class.java.canonicalName

    fun createFragment(tag: String): Fragment {
        return when(tag) {
            RADIO_FRAGMENT_TAG -> RadioFragment()
            PROGRAMS_FRAGMENT_TAG -> ProgramsFragment()
            NEWS_FRAGMENT_TAG -> NewsFragment()
            else -> throw InvalidFragmentException()
        }
    }

    fun createFragment(tag: String, data: Bundle): Fragment {
        val fragment = createFragment(tag)
        fragment.arguments = data

        return fragment
    }
}