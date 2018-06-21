package com.mohitum.androidmvpkotlindemo.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.mohitum.androidmvpkotlindemo.R
import com.mohitum.androidmvpkotlindemo.adapter.MovieDataAdapter
import com.mohitum.androidmvpkotlindemo.interfaces.MainActivityContract
import com.mohitum.androidmvpkotlindemo.model.MovieData
import com.mohitum.androidmvpkotlindemo.presenter.MainActivityPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityContract.MainActivityView {

    /**
     * The [MainActivityPresenter] for this view
     */
    private var mPresenter = MainActivityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPresenter.attach(this)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        searchButton.setOnClickListener { mPresenter.searchMovie(movieNameEditText.text.toString()) }
    }

    override fun onDestroy() {
        mPresenter.detach()
        super.onDestroy()
    }

    override fun showMessage(idString: Int) {
        Toast.makeText(this, idString, Toast.LENGTH_LONG).show()
    }

    override fun showErrorMessage(errorString: String?) {
        recyclerView.visibility = View.GONE
        loadingProgress.visibility = View.GONE
        error_label.visibility = View.VISIBLE
        error_label.text = errorString
    }

    override fun showLoadingProgress() {
        recyclerView.visibility = View.GONE
        error_label.visibility = View.GONE
        loadingProgress.visibility = View.VISIBLE
    }

    /**
     * Create / update the [RecyclerView.getAdapter]
     * @param searchResults The results obtained in [MainActivityPresenter.searchMovie]
     */
    override fun updateMoviesAdapter(movieData: MovieData) {
        recyclerView.visibility = View.VISIBLE
        error_label.visibility = View.GONE
        loadingProgress.visibility = View.GONE
        if (recyclerView.adapter is MovieDataAdapter) {
            //Already an adapter, just needs to update
            val movieSearchAdapter = recyclerView.adapter as MovieDataAdapter
            movieSearchAdapter.movieData = movieData
            movieSearchAdapter.notifyDataSetChanged()
        } else {
            //Create a new adapter
            recyclerView.adapter = MovieDataAdapter(movieData)
        }
    }

}
