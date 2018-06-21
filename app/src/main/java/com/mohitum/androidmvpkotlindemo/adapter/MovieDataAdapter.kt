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

/**
 * Recycler view adapter class for inflating the movie data
 *
 * @author Mohit Sharma
 */
class MovieDataAdapter(var movieData: MovieData):
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

    /**
     * View Holder for binding a single row/item in the recycler view
     */
    class ViewHolder(private val containerView: View)
        : RecyclerView.ViewHolder(containerView) {

        /**
         * Binds UI elements with movie data
         *
         * @param item movie item
         */
        fun bindItems(item: Search) {
            containerView.movieTitle.text = item.Title
            var description: String = "Type: ${item.Type}, Released: ${item.Year}"
            containerView.movieDescription.text = description
            Picasso.with(containerView.getContext()).load(item.Poster).into(containerView.moviePosterImage);
        }
    }
}