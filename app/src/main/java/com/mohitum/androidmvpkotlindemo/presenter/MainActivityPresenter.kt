package com.mohitum.androidmvpkotlindemo.presenter

import com.mohitum.androidmvpkotlindemo.R
import com.mohitum.androidmvpkotlindemo.interfaces.MainActivityContract

class MainActivityPresenter: MainActivityContract.Presenter {

    /**
     * The reference to MainActivityView
     */
    var mView: MainActivityContract.MainActivityView? = null

    override fun attach(view: MainActivityContract.MainActivityView) {
        this.mView = view
    }

    override fun detach() {
        this.mView = null
    }

    override fun searchMovie(text: String) {
        if (text.isEmpty() && mView != null) {
            mView?.showMessage(R.string.search_error_no_text)
        }
        //TODO: add API Call here
    }
}