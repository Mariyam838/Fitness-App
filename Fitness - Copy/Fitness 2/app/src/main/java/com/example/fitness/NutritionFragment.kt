package com.example.fitness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class NutritionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_nutrition, container, false)

        setupBannerClick(
            view.findViewById(R.id.imgBreakfast),
            title = "Breakfast",
            purpose = "Energizes your day with proteins & carbs",
            recipe = "Oats with fruits, Greek yogurt, or eggs",
            calories = 350,
            imageResId = R.drawable.breakfast_banner
        )

        setupBannerClick(
            view.findViewById(R.id.imgLunch),
            title = "Lunch",
            purpose = "Main meal to fuel your afternoon",
            recipe = "Grilled chicken with quinoa and steamed veggies",
            calories = 550,
            imageResId = R.drawable.lunch_banner
        )

        setupBannerClick(
            view.findViewById(R.id.imgDinner),
            title = "Dinner",
            purpose = "Light and nutritious to end your day",
            recipe = "Baked salmon with greens",
            calories = 400,
            imageResId = R.drawable.dinner_banner
        )

        setupBannerClick(
            view.findViewById(R.id.imgSnacks),
            title = "Snacks",
            purpose = "Healthy fillers between meals",
            recipe = "Almonds, boiled eggs, fruit smoothie",
            calories = 200,
            imageResId = R.drawable.snack_banner
        )

        return view
    }

    private fun setupBannerClick(
        view: ImageView,
        title: String,
        purpose: String,
        recipe: String,
        calories: Int,
        imageResId: Int
    ) {
        view.setOnClickListener {
            val fragment = MealDetailFragment.newInstance(title, purpose, recipe, calories, imageResId)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
