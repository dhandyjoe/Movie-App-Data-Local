package com.example.movieapp.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.database.Constants
import com.example.movieapp.model.ModelMovie
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.database.DatabaseHelper
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.utils.UserPreferences
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    companion object {
        var EXTRA_MOVIE_DETAILS = "extra_movie_details"
        private var CODE_LOGIN: Int = 1
    }

    private var movieList: ArrayList<ModelMovie>? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var userPreferences: UserPreferences
    private var db = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Code for status bar transparant
        val w: Window = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        binding.rvMovieShimmer.startShimmer()

        userPreferences = UserPreferences(this)
        userPreferences.deleteAllData()
        db = DatabaseHelper(this)

        movieList = Constants.getMovieList()
        binding.tvAllMovie.text = movieList!!.size.toString()
        binding.tvAllFavorite.text = db.getFavoriteMovie().size.toString()

        val handler = Handler()
        handler.postDelayed({
            binding.rvMovieShimmer.stopShimmer()
            binding.rvMovieShimmer.visibility = View.INVISIBLE
            binding.rvMovie.visibility = View.VISIBLE
        }, 8000)



        if(userPreferences.getStatusUser()) {
            binding.tvUsername.text = userPreferences.getNameUser()
            binding.tvUsername.isClickable = false
            binding.tvStatusLogin.visibility = View.INVISIBLE
        } else {
            binding.tvUsername.setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivityForResult(intent, CODE_LOGIN)
            }
        }

        // Connect Adapter
        binding.rvMovie.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val data = MovieAdapter(movieList!!)
        binding.rvMovie.adapter = data

        // Intent to Detail Page
        data.setOnClickListener(object : MovieAdapter.OnClickListener {
            override fun onClick(position: Int, model: ModelMovie) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(EXTRA_MOVIE_DETAILS, model)
                startActivity(intent)
             }
        })

        // Intent ViewAll
        binding.tvViewAllMovie.setOnClickListener {
            val intent = Intent(this, AllMovieActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == CODE_LOGIN) {
            if(resultCode == Activity.RESULT_OK) {
                if(userPreferences.getStatusUser()) {
                    binding.tvUsername.text = userPreferences.getNameUser()
                    binding.tvUsername.isClickable = false
                    binding.tvStatusLogin.visibility = View.INVISIBLE
                } else {
                    binding.tvUsername.setOnClickListener {
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivityForResult(intent, CODE_LOGIN)
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.tvAllFavorite.text = db.getFavoriteMovie().size.toString()

        if(userPreferences.getStatusUser()) {
            binding.tvUsername.text = userPreferences.getNameUser()
            binding.tvUsername.isClickable = false
            binding.tvStatusLogin.visibility = View.INVISIBLE
        } else {
            binding.tvUsername.setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivityForResult(intent, CODE_LOGIN)
            }
        }
    }
}