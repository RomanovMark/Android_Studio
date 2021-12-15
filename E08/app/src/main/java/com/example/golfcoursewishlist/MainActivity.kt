package com.example.golfcoursewishlist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager


open public class MainActivity : AppCompatActivity() {
    private var isListView = true
    private var mStaggeredLayoutManager: StaggeredGridLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find RecyclerView from layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        // Use StaggeredGridLayoutManager as a layout manager for recyclerView
        mStaggeredLayoutManager =
            StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = mStaggeredLayoutManager

        // Use GolfCourseWishlistAdapter as a adapter for recyclerView
        recyclerView.adapter = GolfCourseWishlistAdapter(Places.placeList())
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_toggle -> {
                if (isListView) {
                    item.setIcon(R.drawable.ic_baseline_view_stream_24)
                    item.title = "Show as list"
                    isListView = false
                    mStaggeredLayoutManager?.spanCount = 2
                } else {
                    item.setIcon(R.drawable.ic_baseline_view_column_24)
                    item.title = "Show as grid"
                    isListView = true
                    mStaggeredLayoutManager?.spanCount = 1
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

class Place {
    var name: String? = null
    var image: String? = null

    fun getImageResourceId(context: Context): Int {
        return context.resources.getIdentifier(
            this.image,
            "drawable",
            context.packageName
        )
    }
}

class Places {
    // like "static" in other OOP languages
    companion object {
        // hard code a few places
        var placeNameArray = arrayOf(
            "Black Mountain",
            "Chambers Bay",
            "Clear Water",
            "Harbour Town",
            "Muirfield",
            "Old Course",
            "Pebble Beach",
            "Spy Class"
        )

        // return places
        fun placeList(): ArrayList<Place> {
            val list = ArrayList<Place>()
            for (i in placeNameArray.indices) {
                val place = Place()
                place.name = placeNameArray[i]
                // image name is same as place name without space's
                place.image =
                    placeNameArray[i].replace("\\s+".toRegex(), "").toLowerCase()
                list.add(place)
            }
            return list
        }
    }
}
