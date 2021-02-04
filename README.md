# rxeventbus

## General info
This is simple EventBus based on RxJava2.
	
## Instructions:

Add gradle dependency

```
implementation 'com.github.MirzaCelikovic:rxeventbus:1.1.1'
```

Extend `Event` class when creating events that will be notified to consumers.

```
data class MyEvent(val value: String) : Event()
```

Publish event through RxEventBus
```
RxEventBus.notifySubscribers(MyEvent("This is event"))
```

Subscribe to receive and consume event in an lifecycle aware component e.g. an Activity so disposables are automatically taken care of.
```
        RxEventBus.addSubscriber(
            this@MainActivity, // Lifecycle aware component
            MyEvent::class.java,
            Consumer {
                Log.e("Event", "received event ${it.value}")
            }
        )
```
