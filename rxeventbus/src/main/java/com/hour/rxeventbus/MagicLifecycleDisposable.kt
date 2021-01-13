package com.hour.rxeventbus

import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.disposables.Disposable
import java.util.*

internal class MagicLifecycleDisposable {

    private val map = WeakHashMap<Any, LifecycleAwareDisposable>()

    fun add(parent: Any, disposable: Disposable) {
        if (parent is RxDisposableContainer) {
            parent.addRxSubscription(disposable)
            return
        }

        if (map.containsKey(parent)) {
            val observer = map[parent]
            observer?.addRxSubscription(disposable)
            return
        }

        if (parent is LifecycleOwner) {
            val lifecycleAwareDisposable = LifecycleAwareDisposable()
            parent.lifecycle.addObserver(lifecycleAwareDisposable)
            map[parent] = lifecycleAwareDisposable
            return
        }

        if (parent is RecyclerView.ViewHolder) {
            add(parent.itemView.context, disposable)
            Log.w(LOG_TAG, "Adding disposables via view will tie them to context(Activity)")
        }

        if (parent is View) {
            // Try to avoid this!
            add(parent.context, disposable)
            Log.w(LOG_TAG, "Adding disposables via view will tie them to context(Activity)")
            return
        }

        Log.e(LOG_TAG, "Disposing not implemented, add disposable logic for object [${parent.javaClass.simpleName}]: $parent")
    }

    companion object {
        private val LOG_TAG = MagicLifecycleDisposable::class.java.simpleName
    }
}