package com.versatilogics.apps.mvvm_advanced.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.versatilogics.apps.mvvm_advanced.data.models.Video
import com.versatilogics.apps.mvvm_advanced.databinding.RvRowVideoBinding
import javax.inject.Inject

class VideosAdapter @Inject constructor() :
    RecyclerView.Adapter<VideosAdapter.VideoView>() {

    private val list = arrayListOf<Video>()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): VideoView {
        return VideoView(
            RvRowVideoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(vh: VideoView, position: Int) {
        vh.bind(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun update(data: List<Video>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    inner class VideoView(private val itemBinding: RvRowVideoBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(position: Int) {
            list[position].let {
                itemBinding.textViewName.text = it.title?.replaceFirstChar { it.uppercase() }
                itemBinding.textViewLink.text = it.channel?.title?.replaceFirstChar { it.uppercase() }
                Glide.with(itemBinding.imageView.context).load(it.imageUrl?.replace("http", "https"))
                    .into(itemBinding.imageView)
            }

        }
    }
}