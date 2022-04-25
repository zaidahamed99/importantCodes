package com.example.egci428qz6180220

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.ListView
import com.example.egci428qz6180220.Models.ARoute
import com.example.egci428qz6180220.Models.RouteProvider
import android.view.MenuItem as MenuItem1

class MainActivity : AppCompatActivity() {

    private var adapter: RouteAdapter? = null
    private var routeList: ArrayList<ARoute>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listView)

        /* (1.1) load all routes names from RouteProvider.kt to variable "routeList" */
        routeList!!.addAll(RouteProvider.getData())

        /* (1.2) Prepare adapter by using RouteAdapter class and set adapter to listView*/
        adapter = RouteAdapter(this, R.layout.routes, routeList!!)
        listView.adapter = adapter

            /*...........(1.2)...............*/

            listView.setOnItemClickListener { parent, view, position, id ->
                /* (1.3) Get the selected route name and transfer to RouteViewActivity by using displayMap function */
                var selectedRoute = routeList!![position]
                var routeName = selectedRoute.name
                Log.d("Show Route", routeName)

                displayMap(selectedRoute)
                /*...........(1.3)...............*/

            }
    }

    private fun displayMap(aRoute: ARoute) {
        val intent = Intent(this, RouteViewActivity::class.java)
        intent.putExtra("selRoute", aRoute.name)
        intent.putExtra("point1lat", aRoute.points[0].lat)
        intent.putExtra("point1lon", aRoute.points[0].lon)
        intent.putExtra("point2lat", aRoute.points[1].lat)
        intent.putExtra("point2lon", aRoute.points[1].lon)
        /*...........(1.3)...............*/
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem1): Boolean {
        val id = item!!.getItemId();
        if(id == R.id.menuAdd){
            actionAddClickHandler(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun actionAddClickHandler(item: MenuItem1) {

        /* (1.4) Go to RouteMapActivity Page by clicking at the + sign on the menu bar. */
        val intent = Intent(this, RouteMapsActivity::class.java)
        /*...........(1.4)...............*/
    }
}