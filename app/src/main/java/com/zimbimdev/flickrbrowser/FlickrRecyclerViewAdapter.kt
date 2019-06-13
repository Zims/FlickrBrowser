package com.zimbimdev.flickrbrowser

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class FlickrImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var thumbnail: ImageView = view.findViewById(R.id.thumbnail)
    var title: TextView = view.findViewById(R.id.title)
}
// in jave the FlickrImageViewHolder will be in seperate file as private but
// in kotlin do it in the same file to prevent menory leaks

class FlickrRecyclerViewAdapter(private var photoList: List<Photo>) : RecyclerView.Adapter<FlickrImageViewHolder>() {
    private val TAG = "FlickrRecyclerViewAdapt"


    override fun getItemCount(): Int {
//        Log.d(TAG, ".getItemCount called")
        return if (photoList.isNotEmpty()) photoList.size else 0
    }

    fun loadNewData(newPhotos: List<Photo>) {
        photoList = newPhotos
        notifyDataSetChanged()
    }

    fun getPhoto(position: Int): Photo? {
        return if (photoList.isNotEmpty()) photoList[position] else null
    }

    override fun onBindViewHolder(holder: FlickrImageViewHolder, position: Int) {
        // called by the layout manager when it wants new data in an existing view

        val photoItem = photoList[position]
//        Log.d(TAG, ".onBindViewHolder: ${photoItem.title} --> $position")

        Picasso.with(holder.thumbnail.context).load(photoItem.image)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder)
            .into(holder.thumbnail)

        holder.title.text = photoItem.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrImageViewHolder {
        // Called by the layout manager when it needs a new view
        Log.d(TAG, ".onCreateViewHolder new view requested")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.browse, parent, false)
        return FlickrImageViewHolder(view)
    }
}