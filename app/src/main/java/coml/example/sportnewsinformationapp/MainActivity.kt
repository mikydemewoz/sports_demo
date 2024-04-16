package coml.example.sportnewsinformationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var tabs: TabLayout
    private lateinit var bottomNavigationView: BottomNavigationView

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            viewPager = findViewById(R.id.view_pager)
            tabs = findViewById(R.id.tabs)
            bottomNavigationView = findViewById(R.id.bottom_navigation)
            viewPager.adapter = ScreenSlidePagerAdapter(this)
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> "Sports"
                    1 -> "News"
                    2 -> "Athletes"
                    3 -> "Events"
                    4 -> "Historical Archives"
                    5 -> "About Me"
                    else -> "Tab ${(position + 1)}"
                }
            }.attach()
            bottomNavigationView.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.action_news -> viewPager.currentItem = 1
                    R.id.action_events -> viewPager.currentItem = 3
                    R.id.action_historical -> viewPager.currentItem = 4
                    else -> false
                }
                true
            }

        }

        private inner class ScreenSlidePagerAdapter(fa: AppCompatActivity) : FragmentStateAdapter(fa) {
            override fun getItemCount(): Int = 6

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> Sports()
                    1 -> News()
                    2 -> Athletes()
                    3 -> Events()
                    4 -> HistoricalSportsArchive()
                    5 -> AboutMe()
                    else -> Fragment()
                }
            }
        }
}