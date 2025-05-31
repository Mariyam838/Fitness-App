package com.example.fitness

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment

class GoalsFragment : Fragment() {

    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var stepGoalEditText: EditText
    private lateinit var workoutDaysEditText: EditText
    private lateinit var waterReminderSwitch: Switch
    private lateinit var workoutReminderSwitch: Switch
    private lateinit var themeSwitch: Switch
    private lateinit var saveButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_goals, container, false)

        // Init SharedPreferences
        sharedPrefs = requireContext().getSharedPreferences("UserPrefs", 0)

        stepGoalEditText = view.findViewById(R.id.etStepGoal)
        workoutDaysEditText = view.findViewById(R.id.etWorkoutDays)
        waterReminderSwitch = view.findViewById(R.id.switchWaterReminder)
        workoutReminderSwitch = view.findViewById(R.id.switchWorkoutReminder)
        themeSwitch = view.findViewById(R.id.switchTheme)
        saveButton = view.findViewById(R.id.btnSaveGoals)

        // Load saved values
        stepGoalEditText.setText(sharedPrefs.getString("step_goal", ""))
        workoutDaysEditText.setText(sharedPrefs.getString("workout_days", ""))
        waterReminderSwitch.isChecked = sharedPrefs.getBoolean("water_reminder", false)
        workoutReminderSwitch.isChecked = sharedPrefs.getBoolean("workout_reminder", false)
        themeSwitch.isChecked = sharedPrefs.getBoolean("dark_mode", false)

        // Toggle theme
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            sharedPrefs.edit().putBoolean("dark_mode", isChecked).apply()
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked)
                    AppCompatDelegate.MODE_NIGHT_YES
                else
                    AppCompatDelegate.MODE_NIGHT_NO
            )
        }

        // Save button
        saveButton.setOnClickListener {
            sharedPrefs.edit().apply {
                putString("step_goal", stepGoalEditText.text.toString())
                putString("workout_days", workoutDaysEditText.text.toString())
                putBoolean("water_reminder", waterReminderSwitch.isChecked)
                putBoolean("workout_reminder", workoutReminderSwitch.isChecked)
                apply()
            }
            Toast.makeText(requireContext(), "Goals saved!", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
