package com.example.fitness

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavouritesFragment : Fragment() {

    private lateinit var adapter: FavoriteAdapter
    private val favorites = mutableListOf<FavoriteWorkout>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favourites, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerFavourites)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        loadFavoritesFromPrefs()
        adapter = FavoriteAdapter(favorites) { workoutToDelete ->
            removeFromPrefs(requireContext(), workoutToDelete.title)
            adapter.removeWorkout(workoutToDelete)
        }

        recyclerView.adapter = adapter
        return view
    }

    private fun loadFavoritesFromPrefs() {
        favorites.clear()
        val prefs = requireContext().getSharedPreferences("favorites", Context.MODE_PRIVATE)
        prefs.all.forEach { (_, value) ->
            val parts = (value as String).split("|")
            if (parts.size == 3) {
                favorites.add(
                    FavoriteWorkout(parts[0], parts[1], parts[2].toInt())
                )
            }
        }
    }

    private fun removeFromPrefs(context: Context, key: String) {
        val prefs = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
        prefs.edit().remove(key).apply()
    }
}
