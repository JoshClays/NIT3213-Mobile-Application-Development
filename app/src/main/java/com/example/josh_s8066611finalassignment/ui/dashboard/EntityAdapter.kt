package com.example.josh_s8066611finalassignment.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.josh_s8066611finalassignment.R
import com.example.josh_s8066611finalassignment.data.model.EntityItem
import com.google.android.material.imageview.ShapeableImageView
import com.bumptech.glide.Glide

class EntityAdapter(
    private val entities: List<EntityItem>,
    private val onClick: (EntityItem) -> Unit
) : RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {

    inner class EntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val coverImage: ShapeableImageView = itemView.findViewById<ShapeableImageView>(R.id.imageCover)
        private val title: TextView = itemView.findViewById(R.id.textTitle)
        private val author: TextView = itemView.findViewById(R.id.textAuthor)
        private val genre: TextView = itemView.findViewById(R.id.textGenre)
        private val year: TextView = itemView.findViewById(R.id.textYear)

        fun bind(entity: EntityItem) {
            // Load cover image using Glide
            if (!entity.coverUrl.isNullOrEmpty()) {
                Glide.with(itemView.context)
                    .load(entity.coverUrl)
                    .placeholder(R.drawable.ic_placeholder_book)
                    .error(R.drawable.ic_placeholder_book)
                    .into(coverImage)
            } else {
                coverImage.setImageResource(R.drawable.ic_placeholder_book)
            }

            // Set text content
            title.text = entity.title
            author.text = entity.author
            genre.text = entity.genre
            year.text = entity.publicationYear.toString()

            // Set click listener
            itemView.setOnClickListener { onClick(entity) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_entity, parent, false)
        return EntityViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        holder.bind(entities[position])
    }

    override fun getItemCount(): Int = entities.size
}