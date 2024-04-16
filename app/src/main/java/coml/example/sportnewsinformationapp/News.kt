package coml.example.sportnewsinformationapp

import adapters.NewsAdapter
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import models.NewsItem


class News : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    private val newsItems = ArrayList(
        listOf(
            NewsItem(
                title = "Football Fever: Local Team Clinches Championship!",
                description = "The local high school football team clinched the state championship after a thrilling final game that ended in overtime.",
                imageUrl = "drawable/football"
            ),
            NewsItem(
                title = "Marathon Marvel: Record-Breaking Runner Takes the Lead",
                description = "John Smith, an amateur runner from Smalltown, set a new record in the annual city marathon, beating the previous record set in 2015.",
                imageUrl = "drawable/runners"
            ),
            NewsItem(
                title = "Basketball Buzz: Smalltown Heroes Triumph!",
                description = "The Smalltown basketball team emerged victorious in the regional tournament, securing their spot in the upcoming state championships.",
                imageUrl = "drawable/basket_ball"
            ),
            NewsItem(
                title = "Swimming Sensation: Champions Make Waves",
                description = "The Smalltown Swim Club celebrated their latest victory at the state championships, with several swimmers breaking personal records.",
                imageUrl = "drawable/swimming"
            ),
            NewsItem(
                title = "Volleyball Vibes: Community Courts Reopen with Upgrades",
                description = "The community volleyball courts at Central Park have reopened after several months of renovations, featuring new nets and upgraded facilities.",
                imageUrl = "drawable/volleyball"
            )
        )
    )


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        recyclerView = view.findViewById(R.id.newsRecyclerView) // Make sure you define this ID in your fragment's layout
        recyclerView.layoutManager = LinearLayoutManager(context)
        newsAdapter = NewsAdapter(getNewsItems())
        recyclerView.adapter = newsAdapter

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.newsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = NewsAdapter(getNewsItems())
        newsAdapter = NewsAdapter(newsItems)
        recyclerView.adapter = newsAdapter

        view.findViewById<FloatingActionButton>(R.id.addNewsFab).setOnClickListener {
            showAddNewsDialog()
        }
    }
    private fun getNewsItems(): List<NewsItem> {

        return newsItems;
    }
    @SuppressLint("NotifyDataSetChanged", "DiscouragedApi")
    private fun showAddNewsDialog() {
        // Dialog view
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_news, null)
        val titleInput = dialogView.findViewById<EditText>(R.id.titleInput)
        val descriptionInput = dialogView.findViewById<EditText>(R.id.descriptionInput)
        val imageUrlInput = dialogView.findViewById<EditText>(R.id.imageUrlInput)
        AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Add News")
            .setPositiveButton("Add") {  dialog, which->
                val title = titleInput.text.toString()
                val description = descriptionInput.text.toString()
                val imageUrl = imageUrlInput.text.toString()
                val newItem = NewsItem(title, description,imageUrl)
                newsItems.add(newItem)
                Toast.makeText(requireContext(),imageUrl, Toast.LENGTH_LONG).show()
                newsAdapter.notifyDataSetChanged()
            }.setNegativeButton("Cancel", null)
            .show()
    }
}

