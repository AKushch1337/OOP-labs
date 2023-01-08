package com.akushch.object3

import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Get the clipboard manager
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        // Check if there is data on the clipboard
        if (hasFocus) {
            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            // Get the clipboard data
            val clip = clipboard.primaryClip
            val clipData = clip!!.getItemAt(0).text.toString()

            // Split the clipboard data into a list of strings
            val dataPoints = clipData.split("\n")

            // Create a list of DataPoint objects
            val points = mutableListOf<DataPoint>()
            for (i in dataPoints.indices) {
                val x = i.toDouble()
                val y = dataPoints[i].toDouble()
                val point = DataPoint(x, y)
                points.add(point)
            }

            // Create a line graph series
            val series = LineGraphSeries<DataPoint>(points.toTypedArray())
            series.color = Color.RED

            // Get the GraphView from the layout
            val graphView = findViewById<GraphView>(R.id.idGraphView)
            graphView.removeAllSeries()
            graphView.titleColor = Color.BLUE
            graphView.title = "My Graph"
            graphView.viewport.isScalable = true
            graphView.viewport.isScrollable = true
            graphView.viewport.setMinX(0.0)
            graphView.viewport.setMaxX(points.size.toDouble())
            graphView.viewport.setMinY(0.0)
            graphView.viewport.setMaxY(points.maxBy { it.y }!!.y)

            // Add the series to the GraphView
            graphView.addSeries(series)
        }
    }
}