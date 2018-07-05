package tech.intom.rusradio.presentation.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import tech.intom.rusradio.presentation.adapters.holders.BaseHolder
import java.util.*

/**
 * Created by root on 15.08.17.
 */
open class MultyRvAdapter<M> (protected var mBaseHolderTemplate: BaseHolder<M>,
                              protected var mOnEmptyDataListener: ((Boolean) -> Unit)?) :
        RecyclerView.Adapter<BaseHolder<M>>() {

    protected var mData: List<M> = Collections.emptyList()

    override fun onBindViewHolder(holder: BaseHolder<M>, position: Int) = holder.bind(getItem(position))

    override fun getItemCount(): Int = mData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = mBaseHolderTemplate.create(parent)

    override fun getItemViewType(position: Int): Int = 0

    open fun loadData(data: List<M>) {
        mData = data
        notifyDataSetChanged()

        mOnEmptyDataListener?.invoke(mData.size == 0)
    }

    fun getData(): List<M> = mData

    fun getItem(position: Int): M = mData.get(position)
}