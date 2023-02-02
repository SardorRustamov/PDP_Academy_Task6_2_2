package com.example.pdp_academy_task6_2_2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.pdp_academy_task6_2_2.R
import com.example.pdp_academy_task6_2_2.database.DataBaseClass
import com.example.pdp_academy_task6_2_2.databinding.ActivityInfoBinding
import com.example.pdp_academy_task6_2_2.databinding.ActivityMainBinding
import com.example.pdp_academy_task6_2_2.models.MovieData
import java.io.Serializable

class InfoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityInfoBinding
    private var list: List<MovieData> = ArrayList()
    private lateinit var dataBaseClass: DataBaseClass


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val author = intent.getStringExtra("author")
        val about = intent.getStringExtra("about")
        val data = intent.getStringExtra("data")

        binding.apply {

            toolbarText.text = name
            infoMovieName.text = name
            infoMovieAuthor.text = author
            infoAboutMovie.text = about
            infoMovieDate.text = data

            saveBtn.setOnClickListener(View.OnClickListener {
                finish()
            })
        }
    }
}