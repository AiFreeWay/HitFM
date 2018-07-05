package tech.intom.rusradio.presentation.adapters.holders

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import tech.intom.rusradio.R
import tech.intom.rusradio.application.models.News

/**
 * Created by root on 11.04.18.
 */
class CurrencyHolder : BaseHolder<News> {

    constructor(context: Context) : super(context, null)

    constructor(view: View) : super(view, null)

    override fun create(viewGroup: ViewGroup): BaseHolder<News> {
        val view = viewInflater(viewGroup, R.layout.h_news)
        return CurrencyHolder(view)
    }

    override fun bind(dataModel: News) {

        itemView.findViewById<TextView>(R.id.h_currency_tv_name).setText(dataModel.name)
        itemView.findViewById<TextView>(R.id.h_currency_tv_volume).setText(dataModel.volume)
        itemView.findViewById<TextView>(R.id.h_currency_tv_amount).setText(dataModel.amount)
    }
}