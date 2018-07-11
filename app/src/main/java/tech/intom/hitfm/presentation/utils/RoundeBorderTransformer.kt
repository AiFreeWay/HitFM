package tech.intom.hitfm.presentation.utils

import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.widget.ImageView

class RoundeBorderTransformer {

    companion object {

        fun transform(imageView: ImageView, res: Resources) {
            val source = (imageView.getDrawable() as BitmapDrawable).bitmap
            val drawable = RoundedBitmapDrawableFactory.create(res, source)
            drawable.isCircular = true
            drawable.cornerRadius = Math.max(source.width / 10.0f, source.height / 10.0f)
            imageView.setImageDrawable(drawable)
        }
    }
}