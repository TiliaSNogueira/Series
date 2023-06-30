package com.example.filmsandseries.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.filmsandseries.R
import com.example.filmsandseries.model.ShowDetails
import javax.inject.Inject

class FavoritesShowsAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<FavoritesShowsAdapter.FavoriteShowViewHolder>() {

    private var onShowClickListener: ((Int) -> Unit)? = null

    private val diffUtil = object : DiffUtil.ItemCallback<ShowDetails>() {
        override fun areItemsTheSame(oldItem: ShowDetails, newItem: ShowDetails): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ShowDetails, newItem: ShowDetails): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerlistDiffer = AsyncListDiffer(this, diffUtil)

    var favoritesShowList: List<ShowDetails>
        get() = recyclerlistDiffer.currentList
        set(value) = recyclerlistDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteShowViewHolder {
        return FavoriteShowViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_show, parent, false)
        )
    }

    override fun getItemCount() = favoritesShowList.size

    override fun onBindViewHolder(holder: FavoriteShowViewHolder, position: Int) {
        val itemShowImage = holder.itemView.findViewById<ImageView>(R.id.show_image)
        val itemShowTitle = holder.itemView.findViewById<TextView>(R.id.show_title)
        val itemShowSummary = holder.itemView.findViewById<TextView>(R.id.show_summary)

        val currentShow = favoritesShowList[position]
        holder.itemView.apply {
            glide.load(currentShow.image).into(itemShowImage)
            itemShowTitle.text = currentShow.title
            itemShowSummary.text = currentShow.summary
        }

        holder.itemView.setOnClickListener {
            onShowClickListener?.let {
                currentShow.id?.let { it1 -> it(it1) }
            }
        }
    }

    fun setOnShowClickListener(listener: (Int) -> Unit) {
        onShowClickListener = listener
    }

    class FavoriteShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}