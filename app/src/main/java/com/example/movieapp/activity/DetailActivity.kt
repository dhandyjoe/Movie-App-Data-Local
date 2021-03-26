package com.example.movieapp.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.MediaController
import com.example.movieapp.R
import com.example.movieapp.database.DatabaseHelper
import com.example.movieapp.model.ModelMovie
import com.example.movieapp.databinding.ActivityDetailBinding
import com.example.movieapp.utils.UserPreferences

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    companion object {
        private var movieModel: ModelMovie? = null
        private var CODE_LOGIN: Int = 1
    }

    private var statusFav = false
    lateinit var userPreferences: UserPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if(intent.hasExtra(MainActivity.EXTRA_MOVIE_DETAILS)) {
            movieModel = intent.getParcelableExtra(MainActivity.EXTRA_MOVIE_DETAILS)!!
        } else if (intent.hasExtra(AllMovieActivity.EXTRA_MOVIE_DETAILS)) {
            movieModel = intent.getParcelableExtra(AllMovieActivity.EXTRA_MOVIE_DETAILS)!!
        }

        userPreferences = UserPreferences(this)

        if(movieModel != null) {
            statusFavorite()
            binding.ivBackDetail.setOnClickListener {
                onBackPressed()
            }

            val mediaControler = MediaController(this)
            mediaControler.setAnchorView(binding.vvDetail)
            binding.vvDetail.setMediaController(mediaControler)
            binding.vvDetail.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + movieModel!!.trailer))
            binding.vvDetail.start()

            binding.tvJudulDetail.text = movieModel!!.judul
            binding.tvGenreDetail.text = movieModel!!.genre
            binding.tvDescDetail.text = movieModel!!.desc
            binding.tvRatingDetail.text = movieModel!!.rating.toString()
        }

        binding.ivFavorite.setOnClickListener {
            if(userPreferences.getStatusUser()) {
                addDeleteMovie()
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivityForResult(intent, CODE_LOGIN)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == CODE_LOGIN) {
            if (resultCode == Activity.RESULT_OK) {
                addDeleteMovie()
            }
        }
    }

    fun addDeleteMovie() {
        if(statusFav) {
            val db = DatabaseHelper(this)
            db.deleteFavorite(movieModel!!)
            iconFavorite(false)
        } else {
            val dataHappyPLace = ModelMovie(
                movieModel!!.id,
                binding.tvJudulDetail.text.toString(),
                binding.tvDescDetail.text.toString(),
                binding.tvGenreDetail.text.toString(),
                movieModel!!.poster,
                movieModel!!.trailer,
                movieModel!!.rating
            )
            val db = DatabaseHelper(this)
            db.addFavorite(dataHappyPLace)
            iconFavorite(true)
        }
    }

    fun iconFavorite(boolean: Boolean) {
        if(boolean) {
            statusFav = true
            binding.ivFavorite.setImageResource(R.drawable.ic_favorite_enable)
        } else {
            statusFav = false
            binding.ivFavorite.setImageResource(R.drawable.ic_favorite_disable)
        }
    }

    fun statusFavorite () {
        val dbHandler = DatabaseHelper(this)
        val cursor = dbHandler.queryById(movieModel!!.id.toString())

        if(cursor.moveToNext()) {
            iconFavorite(true)
        } else {
            iconFavorite(false)
        }
    }
}