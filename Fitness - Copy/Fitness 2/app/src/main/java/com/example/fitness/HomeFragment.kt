package com.example.fitness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Push-Up Card Click
        view.findViewById<ImageView>(R.id.cardPushUp).setOnClickListener {
            openDetail(
                title = "Push-Up",
                desc = "Build upper body strength",
                extra = "Do 3 sets of 15 reps",
                imageResId = R.drawable.pushup_card
            )
        }

        // Squat Card Click
        view.findViewById<ImageView>(R.id.cardSquat).setOnClickListener {
            openDetail(
                title = "Squat",
                desc = "Leg and glute workout",
                extra = "3 sets of 20 reps",
                imageResId = R.drawable.squat_card
            )
        }

        // Plank Card Click
        view.findViewById<ImageView>(R.id.cardPlank).setOnClickListener {
            openDetail(
                title = "Plank",
                desc = "Core stability",
                extra = "Hold for 1 minute",
                imageResId = R.drawable.plank_card
            )
        }

        // Burpees Card Click
        view.findViewById<ImageView>(R.id.cardBurpees).setOnClickListener {
            openDetail(
                title = "Burpees",
                desc = "Full-body cardio",
                extra = "3 sets of 10 reps",
                imageResId = R.drawable.burpees_card
            )
        }

        return view
    }

    private fun openDetail(title: String, desc: String, extra: String, imageResId: Int) {
        val detailFragment = HomeWorkoutDetailFragment.newInstance(
            title = title,
            description = "$desc\n$extra",
            imageResId = imageResId
        )

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailFragment)
            .addToBackStack(null)
            .commit()
    }
}
