package com.example.fitness

import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class FavoriteAdapter(
    private val favoriteList: MutableList<FavoriteWorkout>,
    private val onDeleteClick: (FavoriteWorkout) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tvFavWorkoutTitle)
        val description: TextView = itemView.findViewById(R.id.tvFavWorkoutDesc)
        val image: ImageView = itemView.findViewById(R.id.imgFavWorkout)
        val deleteButton: ImageView = itemView.findViewById(R.id.btnDeleteFav)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite_card, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val workout = favoriteList[position]
        holder.title.text = workout.title
        holder.description.text = workout.description
        holder.image.setImageResource(workout.imageResId)

        holder.deleteButton.setOnClickListener {
            onDeleteClick(workout) // Callback to fragment
        }
    }

    override fun getItemCount(): Int = favoriteList.size

    fun removeWorkout(workout: FavoriteWorkout) {
        val index = favoriteList.indexOf(workout)
        if (index != -1) {
            favoriteList.removeAt(index)
            notifyItemRemoved(index)
        }
    }
}
