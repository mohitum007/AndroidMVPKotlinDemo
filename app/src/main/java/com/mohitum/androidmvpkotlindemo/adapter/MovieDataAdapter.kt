package com.mohitum.androidmvpkotlindemo.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mohitum.androidmvpkotlindemo.R
import com.mohitum.androidmvpkotlindemo.model.MovieData
import com.mohitum.androidmvpkotlindemo.model.Search
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_row.view.*

class MovieDataAdapter(private val movieData: MovieData):
        RecyclerView.Adapter<MovieDataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.movie_row, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return movieData.Search.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(movieData.Search[position])
    }


    class ViewHolder(private val containerView: View)
        : RecyclerView.ViewHolder(containerView) {

        fun bindItems(item: Search) {
            containerView.movieTitle.text = item.Title
            var description: String = "Type: ${item.Type}, Released: ${item.Year}"
            containerView.movieDescription.text = description
            Picasso.with(containerView.getContext()).load(item.Poster).into(containerView.moviePosterImage);
        }
    }
}