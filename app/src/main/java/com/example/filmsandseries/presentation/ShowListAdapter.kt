package com.example.filmsandseries.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.filmsandseries.R
import com.example.filmsandseries.model.ShowItem
import javax.inject.Inject

class ShowListAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<ShowListAdapter.ShowViewHolder>() {

    private var onShowClickListener: ((Int) -> Unit)? = null

    var showList = mutableListOf<ShowItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        return ShowViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_show, parent, false)
        )
    }
    override fun getItemCount() = showList.size

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        val itemShowImage = holder.itemView.findViewById<ImageView>(R.id.item_show_image)
        val itemShowTitle = holder.itemView.findViewById<TextView>(R.id.item_show_title)

        val currentShow = showList[position]
        holder.itemView.apply {
            glide.load(currentShow.imageItem).into(itemShowImage)
            itemShowTitle.text = currentShow.titleItem
        }

        holder.itemView.setOnClickListener {
            onShowClickListener?.let {
                currentShow.idItem?.let { it1 -> it(it1) }
            }
        }
    }

    fun setOnShowClickListener(listener: (Int) -> Unit) {
        onShowClickListener = listener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(shows: List<ShowItem>) {
        this.showList.addAll(shows)
        notifyDataSetChanged()
    }

    class ShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}