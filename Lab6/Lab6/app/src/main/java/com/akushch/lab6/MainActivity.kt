package com.akushch.lab6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var nEditText: EditText
    lateinit var minEditText: EditText
    lateinit var maxEditText: EditText
    lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nEditText = findViewById(R.id.n_edit_text)
        minEditText = findViewById(R.id.min_edit_text)
        maxEditText = findViewById(R.id.max_edit_text)
        submitButton = findViewById(R.id.submit_button)

        submitButton.setOnClickListener {
            val obj2Intent: Intent? = packageManager.getLaunchIntentForPackage("com.akushch.object2")
            obj2Intent!!.addFlags(FLAG_ACTIVITY_SINGLE_TOP)
            obj2Intent.putExtra("N", nEditText.text.toString().toInt())
            obj2Intent.putExtra("MIN", minEditText.text.toString().toDouble())
            obj2Intent.putExtra("MAX", maxEditText.text.toString().toDouble())
            startActivity(obj2Intent)
        }

    }
}