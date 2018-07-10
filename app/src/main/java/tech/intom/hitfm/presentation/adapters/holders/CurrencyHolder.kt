package tech.intom.hitfm.presentation.adapters.holders

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import tech.intom.hitfm.R
import tech.intom.hitfm.domain.models.ProgramItem

/**
 * Created by root on 11.04.18.
 */
class CurrencyHolder : BaseHolder<ProgramItem> {

    constructor(context: Context) : super(context, null)

    constructor(view: View) : super(view, null)

    override fun create(viewGroup: ViewGroup): BaseHolder<ProgramItem> {
        val view = viewInflater(viewGroup, R.layout.h_news)
        return CurrencyHolder(view)
    }

    override fun bind(dataModel: ProgramItem) {

        itemView.findViewById<TextView>(R.id.h_news_tv_name).text = dataModel.title
        itemView.findViewById<TextView>(R.id.h_news_tv_volume).text = dataModel.title
        itemView.findViewById<TextView>(R.id.h_news_tv_amount).text = dataModel.title
    }
}