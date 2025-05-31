package com.example.fitness

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WorkoutAdapter(
    private val workoutList: List<Workout>,
    private val onClick: (Workout) -> Unit
) : RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {

    inner class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val workoutImage: ImageView = itemView.findViewById(R.id.ivWorkoutThumb)
        val workoutTitle: TextView = itemView.findViewById(R.id.tvWorkoutName)
        val workoutTime: TextView = itemView.findViewById(R.id.tvWorkoutTime)

        fun bind(workout: Workout) {
            workoutImage.setImageResource(workout.imageResId)
            workoutTitle.text = workout.title
            workoutTime.text = workout.description
            itemView.setOnClickListener { onClick(workout) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_workout, parent, false)
        return WorkoutViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        holder.bind(workoutList[position])
    }

    override fun getItemCount() = workoutList.size
}
