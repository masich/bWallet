package com.masic.bwallet.base

import android.util.Log

/**
 * Created by masic on 20.12.2017.
 */
interface MvpPresenter<V : MvpView> {
    fun setView(mvpView : V)

    fun getView(): V?

    fun viewIsReady() {
        Log.d(this.javaClass.simpleName, "viewIsReady()")
    }

    fun detachView()

    fun destroy()
}