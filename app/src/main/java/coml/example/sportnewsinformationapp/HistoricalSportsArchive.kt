package coml.example.sportnewsinformationapp

import adapters.HistoricalArchiveAdapter
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import models.HistoricalArchive
import java.util.Calendar


class HistoricalSportsArchive : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HistoricalArchiveAdapter
    private var archivesList: MutableList<HistoricalArchive> = ArrayList(getSampleArchives())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_historical_sports_archive, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.archivesRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = HistoricalArchiveAdapter(archivesList)
        recyclerView.adapter = adapter

        archivesList.addAll(getSampleArchives())
        adapter.notifyDataSetChanged()

        view.findViewById<FloatingActionButton>(R.id.addHistoricalArchive).setOnClickListener {
            showAddHistoricalArchiveDialog()
        }
    }

    private fun getSampleArchives(): List<HistoricalArchive> {
        return listOf(
            HistoricalArchive(
                historyName = "African first olympic participation",
                date = "1904",
                description = "South Africa was the first country to participate in Olympics."
            ),
            HistoricalArchive(
                historyName = "FIFA World Cup",
                date = "1930",
                description = "The first ever FIFA World Cup took place in Uruguay."
            ),
            HistoricalArchive(
                historyName = "Introduction of the Three-Point Line",
                date = "1979",
                description = "The NBA introduced the three-point line for the first time, adopting a rule that had been used in the ABA."
            ),
            HistoricalArchive(
                historyName = "Wayne Gretzky's All-Time Points Record",
                date = "1991",
                description = "Wayne Gretzky of the Los Angeles Kings surpassed Gordie Howe's record for the most career points in NHL history."
            ),
            HistoricalArchive(
                historyName = "Olympic Inclusion",
                date = "1896",
                description = "Swimming has been a part of the modern Olympic Games since their inception in 1896.."
            ),
            HistoricalArchive(
                historyName = "The Barefoot Marathoner",
                date = "1960",
                description = "Abebe Bikila, an Ethiopian marathon runner, made history at the 1960 Rome Olympics by winning the marathon gold medal while running barefoot."
            )
        )
    }
    private fun showAddHistoricalArchiveDialog() {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_add_historical_archive, null)
        val date = view.findViewById<EditText>(R.id.historyDateInput)
        date.isFocusable = false

        date.setOnClickListener{
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, dayOfMonth ->
                // Format the selected date and set it to the EditText
                val selectedDate = String.format("%d-%02d-%02d", selectedYear, selectedMonth + 1, dayOfMonth)
                date.setText(selectedDate)
            }, year, month, day).show()
        }
        val name = view.findViewById<EditText>(R.id.historyNameInput).text.toString()
        val description = view.findViewById<EditText>(R.id.historyDescriptionInput).text.toString()
        val dateInput = date.text.toString()

        AlertDialog.Builder(requireContext()).apply {
            setView(view)
            setTitle("Add Historical Archive")
            setPositiveButton("Add") { _, _ ->
                val newArchive = HistoricalArchive(name, dateInput, description)
                (recyclerView.adapter as HistoricalArchiveAdapter).apply {
                    archivesList.add(newArchive)
                    adapter?.notifyDataSetChanged()
                }
            }
            setNegativeButton("Cancel", null)
        }.show()
    }


}