package com.egabruskiy.curiositywatcher.view.maingallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.egabruskiy.curiositywatcher.R
import com.egabruskiy.curiositywatcher.data.model.CuriosityImage
import com.egabruskiy.curiositywatcher.util.RecyclerViewListener


class MainGalleryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    private  var imageList : List<CuriosityImage>? = null
    lateinit var mListener: RecyclerViewListener



    fun setList(imageList: List<CuriosityImage>){
        this.imageList = imageList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_gallery_image_item, parent, false)
        return ViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        imageList?.get(position)?.let { (holder as ViewHolder).bind(it) }
    }


    class ViewHolder(itemView: View, private val listener: RecyclerViewListener) : RecyclerView.ViewHolder(itemView){

         var imageView: ImageView = itemView.findViewById(R.id.image_view_main_gallery_item)

        fun bind(image: CuriosityImage) {
            Glide.with(itemView.context)
                .load(image.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)

            imageView.setOnClickListener{
                listener.onClick(position = adapterPosition)
            }
        }
    }
    override fun getItemCount(): Int {
        return imageList?.size ?: 0
    }


    fun setClickListener(listener: RecyclerViewListener){
        mListener = listener
    }

}