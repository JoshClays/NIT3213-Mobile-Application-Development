package com.example.josh_s8066611finalassignment.ui.details

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.josh_s8066611finalassignment.R
import com.example.josh_s8066611finalassignment.data.model.EntityItem

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        // Retrieve the EntityItem passed from the intent
        val entity = intent.getSerializableExtra("ENTITY_ITEM") as? EntityItem

        if (entity != null) {
            // Bind views
            val tvTitle = findViewById<TextView>(R.id.tvDetailTitle)
            val tvAuthor = findViewById<TextView>(R.id.tvDetailAuthor)
            val tvGenre = findViewById<TextView>(R.id.tvDetailGenre)
            val tvYear = findViewById<TextView>(R.id.tvDetailPublicationYear)
            val tvDescription = findViewById<TextView>(R.id.tvDetailDescription)


            // Set values
            tvTitle.text = entity.title
            tvAuthor.text = entity.author
            tvGenre.text = entity.genre
            tvYear.text = entity.publicationYear.toString()
            tvDescription.text = entity.description

        }

        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()

        }
    }
}