package ru.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import ru.app.R

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val desc = intent.getStringExtra("desc")
        val textView: TextView = findViewById(R.id.tv_descriptionMovie)
        if (desc.isNullOrEmpty()) {
            textView.text = "Нет описания"
        } else {
            textView.text = desc
        }
    }
}