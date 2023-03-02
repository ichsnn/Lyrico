package com.app.lyrico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.app.lyrico.databinding.ActivityAboutSongDetailBinding

class AboutSongDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityAboutSongDetailBinding

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_ABOUT = "extra_about"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutSongDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra(EXTRA_TITLE)
        val about = intent.getStringExtra(EXTRA_ABOUT)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title
        binding.tvAboutSong.text = about
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}