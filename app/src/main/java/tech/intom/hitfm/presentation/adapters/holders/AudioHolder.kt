package tech.intom.hitfm.presentation.adapters.holders

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import tech.intom.hitfm.R
import tech.intom.hitfm.domain.models.RadioItem

class AudioHolder : BaseHolder<RadioItem> {

    constructor(context: Context) : super(context, null)

    constructor(view: View) : super(view, null)

    override fun create(viewGroup: ViewGroup): BaseHolder<RadioItem> {
        val view = viewInflater(viewGroup, R.layout.h_audio_item)
        return AudioHolder(view)
    }

    override fun bind(dataModel: RadioItem) {

        itemView.findViewById<TextView>(R.id.h_audio_tv_name).text = dataModel.title
        itemView.findViewById<TextView>(R.id.h_audio_tv_volume).text = dataModel.title
        itemView.findViewById<TextView>(R.id.h_audio_tv_amount).text = dataModel.title
    }
}