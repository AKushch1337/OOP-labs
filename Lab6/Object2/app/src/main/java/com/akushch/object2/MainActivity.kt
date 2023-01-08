package com.akushch.object2

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import org.w3c.dom.Text
import java.util.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        generateNumbers()
        val obj3Intent: Intent? =
            packageManager.getLaunchIntentForPackage("com.akushch.object3")
        obj3Intent!!.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(obj3Intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        generateNumbers()
        val obj3Intent: Intent? =
            packageManager.getLaunchIntentForPackage("com.akushch.object3")
        obj3Intent!!.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(obj3Intent)
    }

    private fun generateNumbers() {
        val n = intent.extras!!.getInt("N")
        val min = intent.extras!!.getDouble("MIN")
        val max = intent.extras!!.getDouble("MAX")
        val r = Random()

        val vector = DoubleArray(n) { min + (max - min) * r.nextDouble() }

        val tableLayout = findViewById<TableLayout>(R.id.table_layout)
        tableLayout.removeAllViews()

        val numColumns = 7

        // Set the number of decimal places to display
        val numDecimalPlaces = 2

        // Add a row for each group of elements in the vector
        for (i in vector.indices step numColumns) {
            val row = TableRow(this)
            for (j in i until i + numColumns) {
                if (j >= vector.size) {
                    break
                }
                val valueTextView = TextView(this)
                val value = vector[j]
                val formattedValue = String.format("%.${numDecimalPlaces}f", value)
                valueTextView.text = formattedValue
                row.addView(valueTextView)
            }
            tableLayout.addView(row)
        }


        val clipString = vector.joinToString(separator = "\n")

        val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

        val clip = ClipData.newPlainText("Vector Data", clipString)

        clipboard.setPrimaryClip(clip)
    }
}


