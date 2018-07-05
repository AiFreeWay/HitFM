package tech.intom.rusradio.presentation.utils.navigator

import android.os.Bundle
import android.support.v4.app.Fragment
import tech.intom.rusradio.application.exceptions.exceptions.InvalidFragmentException
import tech.intom.rusradio.presentation.screens.news.NewsFragment

/**
 * Created by root on 09.04.18.
 */
object FragmentFactory {

    val CURRENCY_FRAGMENT_TAG = NewsFragment::class.java.canonicalName

    fun createFragment(tag: String): Fragment {
        return when(tag) {
            CURRENCY_FRAGMENT_TAG -> NewsFragment()
            else -> throw InvalidFragmentException()
        }
    }

    fun createFragment(tag: String, data: Bundle): Fragment {
        val fragment = createFragment(tag)
        fragment.arguments = data

        return fragment
    }
}