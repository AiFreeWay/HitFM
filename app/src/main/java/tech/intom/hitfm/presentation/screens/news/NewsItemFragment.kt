package tech.intom.hitfm.presentation.screens.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fmt_news_item.*
import tech.intom.hitfm.R
import tech.intom.hitfm.application.utils.Logger
import tech.intom.hitfm.presentation.screens.abstractions.FragmentChild
import tech.intom.hitfm.presentation.screens.main.MainActivity
import tech.intom.hitfm.presentation.utils.CircleTransform
import tech.intom.hitfm.presentation.utils.RoundeBorderTransformer
import java.lang.Exception

class NewsItemFragment : MvpAppCompatFragment(), FragmentChild<MainActivity> {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.logObjectCreating(this)
        return inflater.inflate(R.layout.fmt_news_item, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Picasso.get()
                .load("http://pilerats.com/assets/Uploads/_resampled/SetWidth940-pnau-party-14.jpg")
                .error(R.drawable.splash_image)
                .placeholder(R.drawable.splash_image)
                .transform(CircleTransform())
                .into(fmt_news_item_image, object: Callback {
                    override fun onSuccess() {
                        RoundeBorderTransformer.transform(fmt_news_item_image, resources)
                    }

                    override fun onError(e: Exception?) { } })
    }

    override fun onResume() {
        super.onResume()
        getParentView().showBackNavigateToolbar(true)
    }

    override fun getParentView() = activity as MainActivity
}