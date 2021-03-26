package com.example.movieapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapter.AllMovieAdapter
import com.example.movieapp.database.Constants
import com.example.movieapp.model.ModelMovie
import com.example.movieapp.databinding.ActivityAllMovieBinding

class AllMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllMovieBinding

    companion object {
        var EXTRA_MOVIE_DETAILS = "extra_movie_details"
    }

    var movieList: ArrayList<ModelMovie>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllMovieBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        movieList = Constants.getMovieList()

        binding.ivBackAllMovie.setOnClickListener {
            onBackPressed()
        }

        // connect adapter
        binding.rvAllmovie.layoutManager =  LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val data = AllMovieAdapter(movieList!!)
        binding.rvAllmovie.adapter = data

        // intent to data detail
        data.setOnClickListener(object : AllMovieAdapter.OnClickListener {
            override fun onClick(position: Int, model: ModelMovie) {
                val intent = Intent(this@AllMovieActivity, DetailActivity::class.java)
                intent.putExtra(MainActivity.EXTRA_MOVIE_DETAILS, model)
                startActivity(intent)
            }
        })
    }
}