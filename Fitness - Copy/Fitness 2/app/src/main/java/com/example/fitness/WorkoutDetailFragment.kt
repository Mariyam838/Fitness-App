package com.example.fitness

import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment

class WorkoutDetailFragment : Fragment() {

    companion object {
        fun newInstance(title: String, description: String, imageResId: Int): WorkoutDetailFragment {
            val fragment = WorkoutDetailFragment()
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
        val view = inflater.inflate(R.layout.fragment_workout_detail, container, false)

        val workoutTitle = arguments?.getString("title") ?: "Workout"
        val imageResId = arguments?.getInt("imageResId") ?: R.drawable.ic_fitness
        val workoutDescription = arguments?.getString("description") ?: getDefaultDescription(workoutTitle)

        view.findViewById<TextView>(R.id.tvWorkoutTitle).text = workoutTitle
        view.findViewById<TextView>(R.id.tvWorkoutDescription).text = workoutDescription
        view.findViewById<ImageView>(R.id.ivWorkoutImage).setImageResource(imageResId)

        when (workoutTitle.lowercase()) {
            "push up" -> {
                setupVideo(view, R.id.video1, R.id.thumbnail1, R.id.playButton1, R.raw.pushup1)
                setupVideo(view, R.id.video2, R.id.thumbnail2, R.id.playButton2, R.raw.pushup2)
                setupVideo(view, R.id.video3, R.id.thumbnail3, R.id.playButton3, R.raw.pushup3)
            }
            "crab walk" -> {
                setupVideo(view, R.id.video1, R.id.thumbnail1, R.id.playButton1, R.raw.crabwalk1)
                setupVideo(view, R.id.video2, R.id.thumbnail2, R.id.playButton2, R.raw.crabwalk2)
                setupVideo(view, R.id.video3, R.id.thumbnail3, R.id.playButton3, R.raw.crabwalk3)
            }
            "bridge" -> {
                setupVideo(view, R.id.video1, R.id.thumbnail1, R.id.playButton1, R.raw.bridge1)
                setupVideo(view, R.id.video2, R.id.thumbnail2, R.id.playButton2, R.raw.bridge2)
                setupVideo(view, R.id.video3, R.id.thumbnail3, R.id.playButton3, R.raw.bridge3)
            }
        }

        return view
    }

    private fun setupVideo(view: View, videoId: Int, thumbId: Int, playBtnId: Int, resId: Int) {
        val videoView = view.findViewById<VideoView>(videoId)
        val thumbnail = view.findViewById<ImageView>(thumbId)
        val playButton = view.findViewById<ImageView>(playBtnId)

        val uri = Uri.parse("android.resource://${requireActivity().packageName}/$resId")
        videoView.setVideoURI(uri)
        videoView.setMediaController(MediaController(requireContext()))
        videoView.setOnPreparedListener { it.isLooping = true }

        val playAction = View.OnClickListener {
            thumbnail.visibility = View.GONE
            playButton.visibility = View.GONE
            videoView.visibility = View.VISIBLE
            videoView.requestFocus()
            videoView.start()
        }

        thumbnail.setOnClickListener(playAction)
        playButton.setOnClickListener(playAction)
    }

    private fun getDefaultDescription(title: String): String {
        return when (title.lowercase()) {
            "push up" -> "Push-ups build upper body strength by targeting the chest, shoulders, and triceps. They also improve core engagement and stability."
            "crab walk" -> "Crab Walk improves coordination and strengthens the arms, shoulders, core, and legs. Great for full-body conditioning."
            "bridge" -> "Bridge exercises strengthen your lower back, glutes, and hamstrings while improving posture and flexibility."
            else -> "This workout is designed to help build strength, flexibility, and endurance. Suitable for all fitness levels."
        }
    }
}
