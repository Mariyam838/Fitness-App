package com.example.fitness

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import android.content.Context

class HomeWorkoutDetailFragment : Fragment() {

    companion object {
        fun newInstance(title: String, description: String, imageResId: Int): HomeWorkoutDetailFragment {
            val fragment = HomeWorkoutDetailFragment()
            val args = Bundle()
            args.putString("title", title)
            args.putString("description", description)
            args.putInt("imageResId", imageResId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_workout_detail, container, false)

        val title = arguments?.getString("title") ?: "Workout"
        val description = arguments?.getString("description") ?: ""
        val imageResId = arguments?.getInt("imageResId") ?: R.drawable.pushup_card

        view.findViewById<TextView>(R.id.tvWorkoutTitle).text = title
        view.findViewById<TextView>(R.id.tvWorkoutDescription).text = description
        view.findViewById<ImageView>(R.id.imgDetailWorkout).setImageResource(imageResId)

        view.findViewById<Button>(R.id.btnStartTraining).setOnClickListener {
            Toast.makeText(requireContext(), "Workout Started!", Toast.LENGTH_SHORT).show()
        }

        val heartBtn = view.findViewById<ImageView>(R.id.btnFavoriteHeart)
        heartBtn.setOnClickListener {
            saveToFavorites(requireContext(), title, description, imageResId)
            Toast.makeText(requireContext(), "$title added to Favorites", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun saveToFavorites(context: Context, title: String, desc: String, imageResId: Int) {
        val sharedPrefs = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        val entry = "$title|$desc|$imageResId"
        editor.putString(title, entry)
        editor.apply()
    }
}