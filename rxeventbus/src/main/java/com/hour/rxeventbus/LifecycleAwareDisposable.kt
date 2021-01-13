package com.hour.rxeventbus

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.Disposable

internal class LifecycleAwareDisposable : LifecycleObserver, RxDisposableContainer {

    private val disposableCollector = DisposableCollector()

    override fun addRxSubscription(disposable: Disposable?) {
        disposableCollector.add(disposable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        disposableCollector.clear()
    }
} 