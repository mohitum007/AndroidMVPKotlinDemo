package com.mohitum.androidmvpkotlindemo.presenter

import android.support.test.runner.AndroidJUnit4
import com.mohitum.androidmvpkotlindemo.R
import com.mohitum.androidmvpkotlindemo.interfaces.MainActivityView
import com.nhaarman.mockito_kotlin.*
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityPresenterTest {

    @Test
    fun testAttach() {
        val mainActivityPresenter = MainActivityPresenter()
        assertNull(mainActivityPresenter.mView)

        mainActivityPresenter.attach(mock())
        assertNotNull(mainActivityPresenter.mView)
    }

    @Test
    fun testDetach() {
        val mainActivityPresenter = MainActivityPresenter()
        mainActivityPresenter.attach(mock())
        assertNotNull(mainActivityPresenter.mView)

        mainActivityPresenter.detach()
        assertNull(mainActivityPresenter.mView)
    }

    @Test
    fun testEmptySearchMovie() {
        val mainActivityPresenter = MainActivityPresenter()
        val mainActivityView = mock<MainActivityView>()

        //Test null text
        mainActivityPresenter.attach(mainActivityView)
        mainActivityPresenter.searchMovie("")
        verify(mainActivityView).showMessage(R.string.search_error_no_text)
    }
}