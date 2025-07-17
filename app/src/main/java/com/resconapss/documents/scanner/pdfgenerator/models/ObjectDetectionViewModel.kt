package com.resconapss.documents.scanner.pdfgenerator.models

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ObjectDetectionViewModel : ViewModel() {
    private val _q1 = MutableLiveData(Offset.Zero)
    private val _q2 = MutableLiveData(Offset.Zero)
    private val _q3 = MutableLiveData(Offset.Zero)
    private val _q4 = MutableLiveData(Offset.Zero)
    private val _drawableText = MutableLiveData("")

    val q1: LiveData<Offset> = _q1
    val q2: LiveData<Offset> = _q2
    val q3: LiveData<Offset> = _q3
    val q4: LiveData<Offset> = _q4
    val drawableText: LiveData<String> = _drawableText

    fun updateDetectionResults(q1: Offset, q2: Offset, q3: Offset, q4: Offset, time: String) {
        _q1.postValue(q1)
        _q2.postValue(q2)
        _q3.postValue(q3)
        _q4.postValue(q4)


    }
}


