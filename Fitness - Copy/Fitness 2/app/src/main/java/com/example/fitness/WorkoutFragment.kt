package com.example.fitness

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WorkoutFragment : Fragment() {

    private lateinit var categoryList: List<Category>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_workout, container, false)

        categoryList = listOf(
            Category("Push Up", R.drawable.banner_full_body),
            Category("Crab Walk", R.drawable.crabwalk),
            Category("Bridge", R.drawable.bridge)
        )

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerCategoryBanners)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = CategoryBannerAdapter(categoryList) { category ->
            val detailFragment = WorkoutDetailFragment.newInstance(
                category.title,
                "", // ✅ Passing empty string triggers fallback description
                category.imageResId
            )
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}