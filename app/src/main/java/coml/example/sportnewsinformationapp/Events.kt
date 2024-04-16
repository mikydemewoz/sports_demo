package coml.example.sportnewsinformationapp

import adapters.EventsAdapter
import android.annotation.SuppressLint
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
import models.Event
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar


class Events :  Fragment() {

    private lateinit var recyclerView: RecyclerView
    private var eventsAdapter: EventsAdapter? = null
    private var eventsList: MutableList<Event> = ArrayList(getSampleEvents())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.eventsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        eventsAdapter = EventsAdapter(eventsList)
        recyclerView.adapter = eventsAdapter

        val addEventFab: FloatingActionButton = view.findViewById(R.id.addEventFab)
        addEventFab.setOnClickListener {
            showAddEventDialog()
        }
    }

    @SuppressLint("NewApi", "MissingInflatedId")
    private fun showAddEventDialog() {
        val layoutInflater = LayoutInflater.from(context)
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_event, null)

        val eventNameInput = dialogView.findViewById<EditText>(R.id.eventNameInput)
        val eventDescriptionInput = dialogView.findViewById<EditText>(R.id.eventDescriptionInput)
        val eventDateInput = dialogView.findViewById<EditText>(R.id.eventDateInput)

        eventDateInput.isFocusable = false
        eventDateInput.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(requireContext(), { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = LocalDate.of(year, monthOfYear + 1, dayOfMonth)
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                eventDateInput.setText(selectedDate)
            }, year, month, day)

            datePickerDialog.show()
        }

        AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Add Event")
            .setPositiveButton("Add") { _, _ ->
                val name = eventNameInput.text.toString()
                val description = eventDescriptionInput.text.toString()
                val date = eventDateInput.text.toString()

                val newEvent = Event(name, description, date)
                eventsList.add(newEvent)
                eventsAdapter?.notifyDataSetChanged()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun getSampleEvents(): List<Event> {
        return listOf(
            Event("International Tennis Open", "Elite tennis tournament featuring top-ranked players.", "2023-09-04"),
            Event("Global Cycling Championship", "World's best cyclists compete in prestigious race.", " 2023-06-18"),
            Event("World Swimming Championships", "Aquatic competition showcasing world-class swimmers.", "2023-07-15"),
            Event("World Athletics Championships", "Track and field showcase featuring elite athletes.", "2023-08-12"),
            Event("International Basketball Invitational", "Elite basketball teams compete in international tournament.", "2024-04-05"),
            Event("Global Golf Masters Tournament", "Premier golf event attracting top golfers worldwide.", "2024-06-20"),
            Event("World Equestrian Games", "Elite equestrian competition featuring various disciplines.", "2024-09-08"),
            Event("International Surfing Classic", "Surfing competition showcasing world's best surfers.", "2024-08-15"),
            Event("World Martial Arts Championship", "Martial arts tournament featuring diverse disciplines.", "2024-11-10"),
            Event("Global Sailing Regatta", "Sailing competition attracting top sailors worldwide.", "2024-07-07")
        )
    }


}