package tech.intom.hitfm.presentation.adapters

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import tech.intom.hitfm.R
import tech.intom.hitfm.domain.models.RadioItem
import tech.intom.hitfm.presentation.utils.CircleTransform
import tech.intom.hitfm.presentation.utils.RoundeBorderTransformer
import java.lang.Exception
import java.util.*

class CorouselImagesAdapter(private val context: Context) : PagerAdapter() {

    private var mData: List<RadioItem> = Collections.emptyList()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = ImageView(context)

        Picasso.get()
                .load("http://xn----7sba2bifvkei7czchq.xn--p1ai/uploads/posts/2016-11/medium/1478126998_black-octopus-superfluidity.jpg")
                .error(R.drawable.splash_image)
                .placeholder(R.drawable.splash_image)
                .transform(CircleTransform())
                .into(view, object: Callback {
                    override fun onSuccess() {
                        RoundeBorderTransformer.transform(view, context.resources)
                    }

                    override fun onError(e: Exception?) { } })

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        //return mData.size
        return 4
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    fun loadData(data: List<RadioItem>) {
        mData = data
    }
}