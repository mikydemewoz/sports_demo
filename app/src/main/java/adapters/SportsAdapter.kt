package adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coml.example.sportnewsinformationapp.R
import models.SportCategory

class SportsAdapter(private val sportsList: ArrayList<SportCategory>) :
    RecyclerView.Adapter<SportsAdapter.SportsViewHolder>() {

    class SportsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sportsTypeTextView: TextView = view.findViewById(R.id.sportsTypeTextView)
        val sportsNameTextView: TextView = view.findViewById(R.id.sportsNameTextView)
        val instructionTextView: TextView = view.findViewById(R.id.instructionTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sport_item, parent, false)
        return SportsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SportsViewHolder, position: Int) {
        Log.d("SportsAdapter", "Binding view holder for position $position")
        val sport = sportsList[position]
        holder.sportsTypeTextView.text = sport.sportsType
        holder.sportsNameTextView.text = sport.sportsName
        holder.instructionTextView.text = sport.instruction
    }

    override fun getItemCount() = sportsList.size
}
