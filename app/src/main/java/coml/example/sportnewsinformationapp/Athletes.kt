package coml.example.sportnewsinformationapp

import adapters.AthletesAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import models.Athlete


class Athletes : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AthletesAdapter

    private val athletesList = ArrayList(listOf(
        Athlete(
            name = "Usain Bolt",
            sportName = "Short distance",
            country = "Jamaica",
            bestPerformance = "World record holder for the 100 metres, 200 metres and 4 × 100 metres relay",
            medals = "8 Olympic gold medals",
            facts = "Widely considered to be the greatest sprinter of all time"
        ),
        Athlete(
            name = "Michael Phelps",
            sportName = "Swimming",
            country = "USA",
            bestPerformance = "Most successful and most decorated Olympian of all time",
            medals = "23 Olympic gold medals",
            facts = "Holds the records for Olympic gold medals, Olympic gold medals in individual events, and Olympic medals in individual events for a male"
        ),
        Athlete(
            name = "Abebe Bikela",
            sportName = "Marathon runner",
            country = "Ethiopia",
            bestPerformance = "Won tokyo marathon with bare foot",
            medals = "2 Olympic gold medals",
            facts = "Widely considered to be the in the history of Marathon"
        ),
        Athlete(
            name = "Usain Bolt",
            sportName = "Short distance",
            country = "Jamaica",
            bestPerformance = "World record holder for the 100 metres, 200 metres and 4 × 100 metres relay",
            medals = "8 Olympic gold medals",
            facts = "Widely considered to be the greatest sprinter of all time"
        )
    ))

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_athletes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.athletesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = AthletesAdapter(getAthletes())
        recyclerView.adapter = adapter
        val addAthleteFab: FloatingActionButton = view.findViewById(R.id.addAthleteFab)
        addAthleteFab.setOnClickListener {
            showAddAthleteDialog()
        }
    }

    private fun getAthletes(): List< Athlete> {
        return athletesList;
    }
    private fun showAddAthleteDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_athlete, null)
        val nameInput = dialogView.findViewById<EditText>(R.id.athleteNameInput)
        val sportInput = dialogView.findViewById<EditText>(R.id.athleteSportInput)
        val countryInput = dialogView.findViewById<EditText>(R.id.athleteCountryInput)
        val performanceInput = dialogView.findViewById<EditText>(R.id.athletePerformanceInput)
        val medalsInput = dialogView.findViewById<EditText>(R.id.athleteMedalsInput)
        val factsInput = dialogView.findViewById<EditText>(R.id.athleteFactsInput)

        AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Add Athlete")
            .setPositiveButton("Add") { _, _ ->
                val newAthlete = Athlete(
                    name = nameInput.text.toString(),
                    sportName = sportInput.text.toString(),
                    country = countryInput.text.toString(),
                    bestPerformance = performanceInput.text.toString(),
                    medals = medalsInput.text.toString(),
                    facts = factsInput.text.toString()
                )
                // Assume athletesList is your data list and adapter is your RecyclerView adapter
                athletesList.add(newAthlete)
                adapter.notifyDataSetChanged()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

}