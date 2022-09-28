package ru.synergy.resumegolikov.ui.resume

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResumeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        println(text)
    }
    val text: LiveData<String> = _text
}