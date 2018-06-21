package com.mohitum.androidmvpkotlindemo.presenter

import com.mohitum.androidmvpkotlindemo.R
import com.mohitum.androidmvpkotlindemo.constant.AppUrls
import com.mohitum.androidmvpkotlindemo.interfaces.MainActivityView
import com.mohitum.androidmvpkotlindemo.interfaces.Presenter
import com.mohitum.androidmvpkotlindemo.model.MovieData
import com.mohitum.androidmvpkotlindemo.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Main presenter class for MainActivity
 *
 * @author Mohit Sharma
 */
class MainActivityPresenter: Presenter {

    /**
     * The reference to MainActivityView
     */
    var mView: MainActivityView? = null

    val apiService by lazy {
        ApiService.create()
    }
    var disposable: Disposable? = null

    /**
     * Link view with the presenter
     *
     * @param view MainActivity view instance
     */
    override fun attach(view: MainActivityView) {
        this.mView = view
    }

    /**
     * Unlink view with the presenter
     */
    override fun detach() {
        this.mView = null
    }

    /**
     * Search for a movie from the remote database
     *
     * @param text search string
     */
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

    /**
     * Handle success result
     *
     * @param result movies data result
     */
    private fun onResult(result: MovieData){
        mView?.updateMoviesAdapter(result)
    }

    /**
     * Handle error response
     *
     * @param error the error throwable
     */
    private fun onError(error: Throwable){
        mView?.showErrorMessage(error.message)
    }
}