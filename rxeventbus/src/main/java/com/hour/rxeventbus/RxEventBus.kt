package com.hour.rxeventbus

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class RxEventBus {

    private val rxBus by lazy {
        RxBus()
    }

    private val magicDisposable by lazy {
        MagicLifecycleDisposable()
    }

    companion object {
        @Volatile
        private var INSTANCE: RxEventBus? = null

        private fun getInstance(): RxEventBus {
            return INSTANCE ?: synchronized(this) {
                RxEventBus().also {
                    INSTANCE = it
                }
            }
        }

        fun <T: Event> addSubscriber(
            parent: Any,
            clazz: Class<T>,
            subscriber: Consumer<T>
        ) {
            val disposable = addSubscriber(clazz, subscriber)
            getInstance().magicDisposable.add(parent, disposable)
        }

        private fun <T: Event> addSubscriber(
            clazz: Class<T>,
            subscriber: Consumer<T>
        ): Disposable {
            return getInstance()
                .rxBus
                .listen(clazz)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)
        }

        fun <T: Event> notifySubscribers(vararg events: T) {
            for (event in events) {
                getInstance().rxBus.publish(event)
            }
        }
    }
}