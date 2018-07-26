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

class AudioContainerHolder : BaseHolder<AudioContainerItem> {

    constructor(context: Context) : super(context, null)

    constructor(view: View) : super(view, null)

    override fun create(viewGroup: ViewGroup): BaseHolder<AudioContainerItem> {
        val view = viewInflater(viewGroup, R.layout.h_audio_container)
        return AudioContainerHolder(view)
    }

    override fun bind(dataModel: AudioContainerItem) {

        val view = itemView.findViewById<ImageView>(R.id.h_audio_container_image)

        Picasso.get()
                .load("https://bimru.ru/uploads/posts/2018-05/1527746244_cf2ae2e40ed603da4554829d4aaca54a.jpg")
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