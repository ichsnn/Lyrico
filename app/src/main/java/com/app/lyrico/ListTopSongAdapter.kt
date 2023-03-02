package com.app.lyrico

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.lyrico.databinding.ItemRowSongBinding

class ListTopSongAdapter(private val listTopSong: ArrayList<Song>) :
    RecyclerView.Adapter<ListTopSongAdapter.ListTopSongHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListTopSongHolder {
        val binding = ItemRowSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListTopSongHolder(binding)
    }

    override fun getItemCount(): Int = listTopSong.size

    override fun onBindViewHolder(holder: ListTopSongHolder, position: Int) {
        val song = listTopSong[position]
        val binding = holder.binding
        binding.tvRank.text = song.rank.toString()
        binding.tvSongTitle.text = song.title
        binding.tvSinger.text = song.singer
        binding.imgItemPhoto.setImageResource(song.imgPhoto)

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listTopSong[holder.adapterPosition])
        }
    }

    inner class ListTopSongHolder(var binding: ItemRowSongBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Song)
    }

}