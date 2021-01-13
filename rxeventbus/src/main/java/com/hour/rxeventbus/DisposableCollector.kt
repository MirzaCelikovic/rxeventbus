package com.hour.rxeventbus

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

internal class DisposableCollector {

    private var compositeDisposable: CompositeDisposable? = null

    fun add(disposable: Disposable?) {
        disposable?.let {
            compositeDisposable = compositeDisposable ?: CompositeDisposable()
            compositeDisposable?.add(it)
        }
    }

    fun clear() {
        compositeDisposable?.clear()
        compositeDisposable = null
    }
}