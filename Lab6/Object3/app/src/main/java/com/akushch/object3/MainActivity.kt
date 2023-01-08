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
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        if (hasFocus) {
            val numHorizontalLabels = 10
            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = clipboard.primaryClip
            val clipData = clip!!.getItemAt(0).text.toString()

            val dataPoints = clipData.split("\n")

            val points = mutableListOf<DataPoint>()
            for (i in dataPoints.indices) {
                val x = (i+1).toDouble()
                val y = dataPoints[i].toDouble()
                val point = DataPoint(x, y)
                points.add(point)
            }

            val series = LineGraphSeries<DataPoint>(points.toTypedArray())
            series.color = Color.RED

            val graphView = findViewById<GraphView>(R.id.idGraphView)
            graphView.gridLabelRenderer.numHorizontalLabels = numHorizontalLabels
            graphView.removeAllSeries()
            graphView.titleColor = Color.BLUE
            graphView.title = "Графік вектора"
            graphView.viewport.isScalable = true
            graphView.viewport.isScrollable = true
            graphView.viewport.setMinX(0.0)
            graphView.viewport.setMaxX(points.size.toDouble())
            graphView.viewport.setMinY(0.0)
            graphView.viewport.setMaxY(points.maxBy { it.y }!!.y)

            graphView.addSeries(series)
        }
    }
}