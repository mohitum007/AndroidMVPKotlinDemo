package com.mohitum.androidmvpkotlindemo.interfaces

import android.support.annotation.StringRes
import com.mohitum.androidmvpkotlindemo.model.MovieData

/**
 * Defines the view interface for MainActivity
 */
interface MainActivityView {
    /**
     * Shows a message in MainActivity
     *
     * @param idString The id of the string we want to show
     */
    fun showMessage(@StringRes idString: Int)

    /**
     * Shows error messages while fetching list in MainActivity
     *
     * @param errorString The string we want to show
     */
    fun showErrorMessage(errorString: String?)

    /**
     * Shows loading progress in MainActivity
     */
    fun showLoadingProgress()

    /**
     * Calls the MainActivity to update the recycler view adapter
     *
     * @param movieData The list of Movies
     */
    fun updateMoviesAdapter(movieData: MovieData)

}