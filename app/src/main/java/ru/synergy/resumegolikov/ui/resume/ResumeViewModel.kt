package ru.synergy.resumegolikov.ui.resume

import android.webkit.WebView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.synergy.resumegolikov.R

class ResumeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        println(text)

    }
    val text: LiveData<String> = _text
}