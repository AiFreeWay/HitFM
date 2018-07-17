package tech.intom.hitfm.presentation.adapters.holders

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import tech.intom.hitfm.R
import tech.intom.hitfm.domain.models.ProgramInfoItem
import tech.intom.hitfm.presentation.utils.CircleTransform
import tech.intom.hitfm.presentation.utils.RoundeBorderTransformer
import java.lang.Exception

class GridHodler : BaseHolder<ProgramInfoItem> {

    constructor(context: Context) : super(context, null)

    constructor(view: View) : super(view, null)

    override fun create(viewGroup: ViewGroup): BaseHolder<ProgramInfoItem> {
        val view = viewInflater(viewGroup, R.layout.h_grid)
        return GridHodler(view)
    }

    override fun bind(dataModel: ProgramInfoItem) {

        val view = itemView.findViewById<ImageView>(R.id.h_grid_image)

        Picasso.get()
                .load("https://cdn.img.inosmi.ru/images/24126/31/241263151.jpg")
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