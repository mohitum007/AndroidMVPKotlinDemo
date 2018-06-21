package com.mohitum.androidmvpkotlindemo.interfaces

/**
 * Defines the presenter interface for MainActivity
 */
interface Presenter {
    /**
     * Search for a movie from online database
     *
     * @param text The search text
     */
    fun searchMovie(text: String)

    /**
     * Called when the view is created and wants to attach its presenter
     */
    fun attach(view: MainActivityView)

    /**
     * Called when the view is destroyed to get rid of its presenter
     */
    fun detach()
}