package com.mohitum.androidmvpkotlindemo.presenter

import android.content.Context
import com.mohitum.androidmvpkotlindemo.R
import com.mohitum.androidmvpkotlindemo.constant.AppUrls
import com.mohitum.androidmvpkotlindemo.interfaces.MainActivityContract
import com.mohitum.androidmvpkotlindemo.model.MovieData
import com.mohitum.androidmvpkotlindemo.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivityPresenter: MainActivityContract.Presenter {

    /**
     * The reference to MainActivityView
     */
    var mView: MainActivityContract.MainActivityView? = null

    val apiService by lazy {
        ApiService.create()
    }
    var disposable: Disposable? = null

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
        beginSearch(text)
    }

    private fun beginSearch(searchString: String) {
        mView?.showLoadingProgress()
        disposable =
                apiService.getMovies(searchString, AppUrls.MOVIEDB_API_KEY_VALUE)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> onResult(result) },
                                { error -> onError(error) }
                        )
    }

    private fun onResult(result: MovieData){
        mView?.updateMoviesAdapter(result)
    }

    private fun onError(error: Throwable){
        mView?.showErrorMessage(error.message)
    }
}