package com.kessir.tipcalculator.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry

abstract class ObservableViewModel(app: Application) : AndroidViewModel(app), Observable {

    @delegate:Transient
    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        callbacks.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        callbacks.add(callback)
    }

    fun notifyChange() {
        callbacks.notifyChange(this, 0)
    }

}