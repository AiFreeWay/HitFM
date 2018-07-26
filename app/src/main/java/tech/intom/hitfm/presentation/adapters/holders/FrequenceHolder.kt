package tech.intom.hitfm.presentation.adapters.holders

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import tech.intom.hitfm.R
import tech.intom.hitfm.presentation.models.Frequence

class FrequenceHolder : BaseHolder<Frequence> {

    constructor(context: Context) : super(context, null)

    constructor(view: View) : super(view, null)

    override fun create(viewGroup: ViewGroup): BaseHolder<Frequence> {
        val view = viewInflater(viewGroup, R.layout.h_frequence)
        return FrequenceHolder(view)
    }

    override fun bind(dataModel: Frequence) {

        val titleTextView = this.itemView.findViewById<TextView>(R.id.h_frequence_title)
        val frequenceTextView = this.itemView.findViewById<TextView>(R.id.h_frequence_frequence)

        if (dataModel.isSelected) {
            titleTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.dark_blue))
            frequenceTextView.setTextColor(Color.WHITE)
            frequenceTextView.setBackgroundResource(R.drawable.pink_round_rectangle)
        } else {
            titleTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.lightTextColor))
            frequenceTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.dark_blue))
            frequenceTextView.setBackgroundResource(R.drawable.grey_round_rectangle)
        }

        titleTextView.text = dataModel.title
        frequenceTextView.findViewById<TextView>(R.id.h_frequence_frequence).text = dataModel.frequence.toString()
    }
}