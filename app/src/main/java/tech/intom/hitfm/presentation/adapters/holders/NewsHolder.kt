package tech.intom.hitfm.presentation.adapters.holders

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import tech.intom.hitfm.R
import tech.intom.hitfm.domain.models.AudioContainerItem
import tech.intom.hitfm.presentation.utils.CircleTransform
import tech.intom.hitfm.presentation.utils.RoundeBorderTransformer
import java.lang.Exception

/**
 * Created by root on 11.04.18.
 */
class NewsHolder : BaseHolder<AudioContainerItem> {

    constructor(context: Context, onClick: ((AudioContainerItem) -> Unit)) : super(context, onClick)

    constructor(view: View, onClick: ((AudioContainerItem) -> Unit)) : super(view, onClick)

    override fun create(viewGroup: ViewGroup): BaseHolder<AudioContainerItem> {
        val view = viewInflater(viewGroup, R.layout.h_news)
        return NewsHolder(view, mOnClick!!)
    }

    override fun bind(dataModel: AudioContainerItem) {

        val view = itemView.findViewById<ImageView>(R.id.h_audio_container_image)
        view.setOnClickListener { mOnClick?.invoke(dataModel) }

        Picasso.get()
                .load("http://pilerats.com/assets/Uploads/_resampled/SetWidth940-pnau-party-14.jpg")
                .error(R.drawable.splash_image)
                .placeholder(R.drawable.splash_image)
                .transform(CircleTransform())
                .into(view, object: Callback {
                    override fun onSuccess() {
                        RoundeBorderTransformer.transform(view, view.resources)
                    }

                    override fun onError(e: Exception?) { } })
    }
}