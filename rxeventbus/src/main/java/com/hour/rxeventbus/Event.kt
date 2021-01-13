package com.hour.rxeventbus

/**
 * All events that go through RxEventBus should extend Event class
 *
 * class SampleEmptyNotificationEvent: Event()
 * data class SampleDataNotificationEvent(val sampleData: String): Event()
 **/

abstract class Event