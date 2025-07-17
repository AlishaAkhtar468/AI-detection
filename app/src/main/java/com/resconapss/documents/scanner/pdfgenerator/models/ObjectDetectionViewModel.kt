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

    fun updateDetectionResults(q1Value: Offset, q2Value: Offset, q3Value: Offset, q4Value: Offset, text: String) {
        CoroutineScope(Dispatchers.Main).launch {
            _q1.value = q1Value
            _q2.value = q2Value
            _q3.value = q3Value
            _q4.value = q4Value
            _drawableText.value = text
        }
    }
}


