package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext

import android.view.ViewGroup
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.DeleteMemoriseListener
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.MemoriseClickedListener
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.RowType
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.utils.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class MemoriseDelegate<T>(val listeners: T?) :
    AbsListItemAdapterDelegate<MemoriseItem, RowType, MemoriseViewHolder<T>>()
        where T : DeleteMemoriseListener, T : MemoriseClickedListener {
    override fun isForViewType(item: RowType, items: MutableList<RowType>, position: Int): Boolean =
        item is MemoriseItem

    override fun onCreateViewHolder(parent: ViewGroup): MemoriseViewHolder<T> =
        MemoriseViewHolder(parent.inflate(R.layout.memorise_row), listeners)

    override fun onBindViewHolder(
        item: MemoriseItem,
        holder: MemoriseViewHolder<T>,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNullOrEmpty().not()) {
            holder.update(item)
        } else {
            holder.bind(item)
        }
    }
}