package com.egabruskiy.curiositywatcher.view.fullScreenGallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.egabruskiy.curiositywatcher.R
import com.egabruskiy.curiositywatcher.data.model.CuriosityImage
import com.github.chrisbanes.photoview.PhotoView

class FullScreenGalleryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private  var imageList : List<CuriosityImage>? = null

    fun setList(imageList: List<CuriosityImage>){
        this.imageList = imageList
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.full_screen_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        imageList?.get(position)?.let { (holder as ViewHolder).bind(it) }

    }

    override fun getItemCount(): Int {
        return imageList?.size ?: 0

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var imageView: PhotoView = itemView.findViewById(R.id.image_view)
        fun bind(image: CuriosityImage) {
            Glide.with(itemView.context)
                .load(image.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
        }

        }
}