package com.masic.bwallet.base

/**
 * Created by masic on 20.12.2017.
 */
abstract class PresenterBase<T : MvpView> : MvpPresenter<T> {
    private var view: T? = null

    override fun setView(mvpView: T) {
        view = mvpView
    }

    override fun getView(): T? {
        return view
    }

    override fun detachView() {
        view = null
    }


    protected fun isViewAttached() = view != null
}