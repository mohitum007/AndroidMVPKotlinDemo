package com.mohitum.androidmvpkotlindemo.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.mohitum.androidmvpkotlindemo.R
import com.mohitum.androidmvpkotlindemo.adapter.MovieDataAdapter
import com.mohitum.androidmvpkotlindemo.interfaces.MainActivityView
import com.mohitum.androidmvpkotlindemo.model.MovieData
import com.mohitum.androidmvpkotlindemo.presenter.MainActivityPresenter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main activity view class
 *
 * @author Mohit Sharma
 */
class MainActivity : AppCompatActivity(), MainActivityView {

    /**
     * The MainActivityPresenter for this view
     */
    private var mPresenter = MainActivityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPresenter.attach(this)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        searchButton.setOnClickListener { onSearchClick() }
    }

    /**
     * Handle search button click event
     */
    fun onSearchClick() {
        mPresenter.searchMovie(movieNameEditText.text.toString())
        hideKeyboard()
    }

    override fun onDestroy() {
        mPresenter.detach()
        super.onDestroy()
    }

    /**
     * Show toast messages
     *
     * @param idString resource id of the message from strings.xml
     */
    override fun showMessage(idString: Int) {
        Toast.makeText(this, idString, Toast.LENGTH_LONG).show()
    }

    /**
     * Show error message in case of loading fails
     *
     * @param errorString The error message to be shown
     */
    override fun showErrorMessage(errorString: String?) {
        recyclerView.visibility = View.GONE
        loadingProgress.visibility = View.GONE
        error_label.visibility = View.VISIBLE
        error_label.text = errorString
        hideKeyboard()
    }

    /**
     * Show loading progress view
     */
    override fun showLoadingProgress() {
        recyclerView.visibility = View.GONE
        error_label.visibility = View.GONE
        loadingProgress.visibility = View.VISIBLE
    }

    /**
     * Create or update the recyclerView adapter
     *
     * @param movieData The moview search results obtained
     */
    override fun updateMoviesAdapter(movieData: MovieData) {
        recyclerView.visibility = View.VISIBLE
        error_label.visibility = View.GONE
        loadingProgress.visibility = View.GONE
        if (recyclerView.adapter is MovieDataAdapter) {
            val movieSearchAdapter = recyclerView.adapter as MovieDataAdapter
            movieSearchAdapter.movieData = movieData
            movieSearchAdapter.notifyDataSetChanged()
        } else {
            recyclerView.adapter = MovieDataAdapter(movieData)
        }
    }

    /**
     * Function to hide soft keyboard
     */
    fun hideKeyboard() {
        val inputManager: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.SHOW_FORCED)

    }

}
