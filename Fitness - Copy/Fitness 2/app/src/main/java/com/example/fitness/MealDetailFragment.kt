package com.example.fitness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class MealDetailFragment : Fragment() {

    companion object {
        fun newInstance(title: String, uses: String, recipe: String, calories: Int, imageResId: Int): MealDetailFragment {
            val fragment = MealDetailFragment()
            val args = Bundle()
            args.putString("title", title)
            args.putString("uses", uses)
            args.putString("recipe", recipe)
            args.putInt("calories", calories)
            args.putInt("imageResId", imageResId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meal_detail, container, false)

        val title = arguments?.getString("title") ?: "Meal"
        val uses = arguments?.getString("uses") ?: "Purpose not specified."
        val recipe = arguments?.getString("recipe") ?: "No recipe provided."
        val calories = arguments?.getInt("calories") ?: 0
        val imageResId = arguments?.getInt("imageResId") ?: R.drawable.breakfast_banner

        view.findViewById<TextView>(R.id.tvMealTitle).text = title
        view.findViewById<TextView>(R.id.tvMealUses).text = uses
        view.findViewById<TextView>(R.id.tvMealRecipe).text = recipe
        view.findViewById<TextView>(R.id.tvMealCalories).text = "Calories: $calories kcal"
        view.findViewById<ImageView>(R.id.imgMeal).setImageResource(imageResId)

        return view
    }
}
