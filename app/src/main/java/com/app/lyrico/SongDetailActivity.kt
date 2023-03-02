package com.app.lyrico

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.lyrico.databinding.ActivitySongDetailBinding

@Suppress("DEPRECATION")
class SongDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySongDetailBinding
    private var song: Song? = null

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        song = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DETAIL, Song::class.java)
        } else {
            intent.getParcelableExtra(EXTRA_DETAIL)
        }

        Log.d("ABOUT", "onCreate: ${song!!.about.isEmpty()}")
        if (song != null) {
            binding.imgItemPhoto.setImageResource(song!!.imgPhoto)
            binding.tvSongTitle.text = song!!.title
            binding.tvSinger.text = song!!.singer
            binding.tvLyrics.text = song!!.lyrics
            binding.tvAboutSongDetail.text = song!!.about
            binding.tvReleaseDate.text = "Release Date : ${song!!.releaseDate}"
        }

        binding.tvAboutSongDetail.setOnClickListener {
            val aboutSongIntent =
                Intent(this@SongDetailActivity, AboutSongDetailActivity::class.java).apply {
                    putExtra(AboutSongDetailActivity.EXTRA_TITLE, song?.title)
                    putExtra(AboutSongDetailActivity.EXTRA_ABOUT, song?.about)
                }
            startActivity(aboutSongIntent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_song_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_share -> {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, getShareLyricsText())
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(shareIntent, "Share Lyrics"))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getShareLyricsText(): String {
        return "**${song?.title} - ${song?.singer}**\n\n${song?.lyrics}"
    }
}