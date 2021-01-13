package com.hour.rxeventbus

import io.reactivex.disposables.Disposable

interface RxDisposableContainer {
    fun addRxSubscription(disposable: Disposable?)
}