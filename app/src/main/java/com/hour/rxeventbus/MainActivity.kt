package com.hour.rxeventbus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import io.reactivex.functions.Consumer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RxEventBus.addSubscriber(
            this,
            MyEvent::class.java,
            Consumer {
                Log.e("Event", "Event ${it.value} received")
            }
        )

        Log.e("Event", "Sending event")
        Handler().postDelayed({
            val event = MyEvent("123")
            RxEventBus.notifySubscribers(event)
            Log.e("Event", "Event ${event.value} sent")
        }, 2000)
    }
}