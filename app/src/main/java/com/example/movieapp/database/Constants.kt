package com.example.movieapp.database

import com.example.movieapp.R
import com.example.movieapp.model.ModelMovie

object Constants {

    fun getMovieList(): ArrayList<ModelMovie> {
        val movieList = ArrayList<ModelMovie>()

        val a1 = ModelMovie(
            1,
            "Wonder Woman 1984",
            "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
            "Action, Fantasy, Adventure",
            R.drawable.wonder_woman,
            R.raw.wonder_woman_1984,
                3F,
        )

        val a2 = ModelMovie(
                2,
                "Avengers: Endgame",
                "Terdampar di luar angkasa tanpa persediaan makanan dan minuman, Tony Stark berusaha mengirim pesan untuk Pepper Potts dimana persediaan oksigen mulai menipis. Sementara itu para Avengers yang tersisa harus menemukan cara untuk mengembalikan 50% mahluk di seluruh dunia yang telah dilenyapkan oleh Thanos.",
                "Action, Adventure",
            R.drawable.avenger,
            R.raw.avengers,
                5F,
        )

        val a3 = ModelMovie(
                3,
                "Mulan",
                "When the Emperor of China issues a decree that one man per family must serve in the Imperial Chinese Army to defend the country from Huns, Hua Mulan, the eldest daughter of an honored warrior, steps in to take the place of her ailing father. She is spirited, determined and quick on her feet. Disguised as a man by the name of Hua Jun, she is tested every step of the way and must harness her innermost strength and embrace her true potential.",
                "Fantasy, Adventure",
            R.drawable.mulan,
            R.raw.mulan,
                2.5F,
        )

        val a4 = ModelMovie(
                4,
                "Now You See Me 2",
                "Satu tahun setelah mereka mengelabui para FBI dan mendapat pujian dari masyarakat luas dengan teknik ilusi yang lebih tinggi lagi dan membawa mereka ke seluruh dunia.",
                "Action, Comedy",
            R.drawable.now_you_see_me_2,
            R.raw.now_you_see_me_2,
                3F,
        )

        val a5 = ModelMovie(
                5,
                "The Raid 2: Berandal",
                "RAMA (Iko Uwais) adalah seorang polisi baru yang meninggalkan ISA, istrinya yang tengah hamil tua untuk melakukan tugas berbahaya. Setelah berhasil mempertahankan dirinya dari 30 lantai apartemen milik raja gembong narkotik TAMA, RAMA direkrut oleh BUNAWAR, kepala polisi yang dikenal jujur dan mempunyai reputasi yang bersih, sebagai polisi yang menyamar.",
                "Action, Fantasy, Adventure",
            R.drawable.the_raid_2,
            R.raw.the_raid_2,
                2F,
        )
        movieList.add(a1)
        movieList.add(a2)
        movieList.add(a3)
        movieList.add(a4)
        movieList.add(a5)
        return movieList
    }
}