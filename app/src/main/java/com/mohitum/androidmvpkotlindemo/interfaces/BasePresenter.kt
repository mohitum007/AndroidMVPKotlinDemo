package com.mohitum.androidmvpkotlindemo.interfaces

interface BasePresenter<in T> {

    /**
     * Called when the view is created and wants to attach its presenter
     */
    fun attach(view: T)

    /**
     * Called when the view is destroyed to get rid of its presenter
     */
    fun detach()
}