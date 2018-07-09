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

        itemView.findViewById<TextView>(R.id.h_currency_tv_name).setText(dataModel.name)
        itemView.findViewById<TextView>(R.id.h_currency_tv_volume).setText(dataModel.volume)
        itemView.findViewById<TextView>(R.id.h_currency_tv_amount).setText(dataModel.amount)
    }
}