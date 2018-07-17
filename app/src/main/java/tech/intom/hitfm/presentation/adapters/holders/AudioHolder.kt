package tech.intom.hitfm.presentation.adapters.holders

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import tech.intom.hitfm.R
import tech.intom.hitfm.domain.models.AudioItem

class AudioHolder : BaseHolder<AudioItem> {

    constructor(context: Context) : super(context, null)

    constructor(view: View) : super(view, null)

    override fun create(viewGroup: ViewGroup): BaseHolder<AudioItem> {
        val view = viewInflater(viewGroup, R.layout.h_audio_item)
        return AudioHolder(view)
    }

    override fun bind(dataModel: AudioItem) {

    }
}