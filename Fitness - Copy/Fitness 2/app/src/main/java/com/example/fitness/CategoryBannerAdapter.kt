package com.example.fitness

import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class CategoryBannerAdapter(
    private val categories: List<Category>,
    private val onClick: (Category) -> Unit
) : RecyclerView.Adapter<CategoryBannerAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val img: ImageView = view.findViewById(R.id.imgCategoryBanner)
        private val text: TextView = view.findViewById(R.id.tvCategoryTitle)

        fun bind(category: Category) {
            img.setImageResource(category.imageResId)
            text.text = category.title
            itemView.setOnClickListener { onClick(category) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category_banner, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount() = categories.size
}
