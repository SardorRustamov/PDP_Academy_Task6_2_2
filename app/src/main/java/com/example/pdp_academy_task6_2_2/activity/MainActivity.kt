package com.example.pdp_academy_task6_2_2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.pdp_academy_task6_2_2.R
import com.example.pdp_academy_task6_2_2.adapter.MovieAdapter
import com.example.pdp_academy_task6_2_2.database.DataBaseClass
import com.example.pdp_academy_task6_2_2.databinding.ActivityMainBinding
import com.example.pdp_academy_task6_2_2.models.MovieData
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var list: List<MovieData>
    private lateinit var dataBaseClass: DataBaseClass
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        dataBaseClass= DataBaseClass(this)

        functionV()

        binding.addBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, AddMovieActivity::class.java)
            startActivity(intent)
        })
    }

    override fun onRestart() {
        super.onRestart()
        functionV()
    }

    private fun functionV() {

        list = dataBaseClass.allMovie()

        movieAdapter = MovieAdapter(list,
            {movieData, positoin ->
                val intent = Intent(this, EditMovieActivity::class.java)
                val pos = list[positoin].id
                intent.putExtra("key", pos)
                intent.putExtra("key2", positoin)
                startActivity(intent)
            },
            { position ->
                val intent = Intent(this, InfoActivity::class.java)
                intent.putExtra("name", list[position].movieName)
                intent.putExtra("author", list[position].movieAuthor)
                intent.putExtra("about", list[position].aboutMovie)
                intent.putExtra("data", list[position].movieDate)

                startActivity(intent)
            },
            { movieData, position ->
                dataBaseClass.deleteMovie(movieData)
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
                functionV()
            }
        )

        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.rv.addItemDecoration(dividerItemDecoration)

        binding.rv.adapter=movieAdapter
    }
}