package com.app.lyrico

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.lyrico.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var rvTopSong: RecyclerView
    private val listTopSongs = ArrayList<Song>()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvTopSong = binding.rvTopSongs
        rvTopSong.setHasFixedSize(true)

        listTopSongs.addAll(getListTopSongs())

        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.profile_menu -> {
                val aboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(aboutIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("Recycle")
    private fun getListTopSongs(): ArrayList<Song> {
        val dataTitle = resources.getStringArray(R.array.data_song_title)
        val dataSinger = resources.getStringArray(R.array.data_singer)
        val dataLyrics = resources.getStringArray(R.array.data_lyrics)
        val dataImg = resources.obtainTypedArray(R.array.data_top_song_img)
        val dataReleaseDate = resources.getStringArray(R.array.data_release_date)
        val dataAbout = resources.getStringArray(R.array.data_song_about)
        val listSong = ArrayList<Song>()

        for (i in dataTitle.indices) {
            val song = Song(
                rank = i + 1,
                title = dataTitle[i],
                singer = dataSinger[i],
                lyrics = dataLyrics[i],
                imgPhoto = dataImg.getResourceId(i, -1),
                releaseDate = dataReleaseDate[i],
                about = dataAbout[i]
            )
            listSong.add(song)
        }
        return listSong
    }

    private fun showRecyclerList() {
        rvTopSong.layoutManager = LinearLayoutManager(this)
        val listTopSongAdapter = ListTopSongAdapter(listTopSongs)
        // add divider
        rvTopSong.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rvTopSong.adapter = listTopSongAdapter

        listTopSongAdapter.setOnItemClickCallback(object : ListTopSongAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Song) {
                val songDetailIntent = Intent(this@MainActivity, SongDetailActivity::class.java)
                songDetailIntent.putExtra(SongDetailActivity.EXTRA_DETAIL, data)
                startActivity(songDetailIntent)
            }
        })
    }
}