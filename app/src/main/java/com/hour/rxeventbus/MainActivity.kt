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
                Log.e("Event", "received event ${it.value}")
            }
        )

        Log.e("Event", "tryingto send event")
        Handler().postDelayed({
            RxEventBus.notifySubscribers(MyEvent("This is event"))
            Log.e("Event", "eventttttjslkdafj;aslkdjf;laksjfd;askdlj;")
        }, 2000)
    }
}