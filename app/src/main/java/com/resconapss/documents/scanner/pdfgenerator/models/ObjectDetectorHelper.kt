package com.resconapss.documents.scanner.pdfgenerator.models

import android.content.Context
import android.graphics.Bitmap
import android.os.SystemClock
import android.util.Log
import androidx.compose.ui.geometry.Offset
import com.resconapss.documents.scanner.pdfgenerator.activitvities.CameraView
import com.resconapss.documents.scanner.pdfgenerator.utills.Utils
import org.tensorflow.lite.gpu.CompatibilityList
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.Rot90Op
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.vision.detector.Detection
import org.tensorflow.lite.task.vision.detector.ObjectDetector

class ObjectDetectorHelper(
    var SCORE_THRESHOLD: Float = 0.9f,
    var numThreads: Int = 2,
    var maxResults: Int = 1,
    var currentDelegate: Int = 0,
    var currentModel: Int = 0,
    val context: Context,
    val viewModel: ObjectDetectionViewModel,
    val objectDetectorListener: CameraView,
) {

    private var objectDetector: ObjectDetector? = null

    // Last points for smoothing
    private var lastQ1: Offset? = null
    private var lastQ2: Offset? = null
    private var lastQ3: Offset? = null
    private var lastQ4: Offset? = null

    private val alpha = 0.3f // smoothing factor

    init {
        setupObjectDetector()
    }

    fun clearObjectDetector() {
        objectDetector = null
    }

    private fun setupObjectDetector() {
        val optionsBuilder =
            ObjectDetector.ObjectDetectorOptions.builder()
                .setScoreThreshold(SCORE_THRESHOLD)
                .setMaxResults(maxResults)

        val baseOptionsBuilder = BaseOptions.builder().setNumThreads(numThreads)

        when (currentDelegate) {
            DELEGATE_GPU -> {
                if (CompatibilityList().isDelegateSupportedOnThisDevice) {
                    baseOptionsBuilder.useGpu()
                }
            }
            DELEGATE_NNAPI -> {
                baseOptionsBuilder.useNnapi()
            }
            // Default is CPU, no action needed
        }

        optionsBuilder.setBaseOptions(baseOptionsBuilder.build())

        val modelName = when (currentModel) {
            MODEL_MOBILENETV1 -> "docmodel.tflite"
            MODEL_EFFICIENTDETV0 -> "efficientdet-lite0.tflite"
            MODEL_EFFICIENTDETV1 -> "efficientdet-lite1.tflite"
            MODEL_EFFICIENTDETV2 -> "efficientdet-lite2.tflite"
            else -> "docmodel.tflite"
        }

        try {
            objectDetector = ObjectDetector.createFromFileAndOptions(context, modelName, optionsBuilder.build())
        } catch (e: IllegalStateException) {
            Log.e("ObjectDetectorHelper", "TFLite failed to load model: ${e.message}")
        }
    }

    fun detect(image: Bitmap, imageRotation: Int) {
        if (objectDetector == null) setupObjectDetector()

        var inferenceTime = SystemClock.uptimeMillis()

        val imageProcessor = ImageProcessor.Builder()
            .add(Rot90Op(-imageRotation / 90))
            .build()

        val tensorImage = imageProcessor.process(TensorImage.fromBitmap(image))

        val results = objectDetector?.detect(tensorImage)
        if (!results.isNullOrEmpty()) {
            results.forEach { detection ->
                val boundingBox = detection.boundingBox
                val score = detection.categories.firstOrNull()?.score ?: 0f

                if (score >= SCORE_THRESHOLD) {
                    val scaleX = Utils.sizeWidth / tensorImage.width
                    val scaleY = Utils.sizeHeight / tensorImage.height

                    val top = boundingBox.top * scaleY
                    val bottom = boundingBox.bottom * scaleY
                    val left = (boundingBox.left * scaleX) - 100
                    val right = (boundingBox.right * scaleX) + 100

                    val q1 = Offset(left, top)
                    val q2 = Offset(right, top)
                    val q3 = Offset(right, bottom)
                    val q4 = Offset(left, bottom)

                    val smoothQ1 = smoothEMA(q1, lastQ1).also { lastQ1 = it }
                    val smoothQ2 = smoothEMA(q2, lastQ2).also { lastQ2 = it }
                    val smoothQ3 = smoothEMA(q3, lastQ3).also { lastQ3 = it }
                    val smoothQ4 = smoothEMA(q4, lastQ4).also { lastQ4 = it }

                    viewModel.updateDetectionResults(smoothQ1, smoothQ2, smoothQ3, smoothQ4, inferenceTime.toString())

                    Log.d("SmoothDetection", "Q1=$smoothQ1 Q2=$smoothQ2 Q3=$smoothQ3 Q4=$smoothQ4")
                }
            }
        } else {
            // ❗ No detection - reset points to Offset.Zero to clear lines
            lastQ1 = null
            lastQ2 = null
            lastQ3 = null
            lastQ4 = null

            viewModel.updateDetectionResults(Offset.Zero, Offset.Zero, Offset.Zero, Offset.Zero, inferenceTime.toString())
            Log.d("SmoothDetection", "No detection - cleared points")
        }

        inferenceTime = SystemClock.uptimeMillis() - inferenceTime
    }

    private fun smoothEMA(newPoint: Offset, lastPoint: Offset?): Offset {
        return if (lastPoint == null) {
            newPoint
        } else {
            Offset(
                x = alpha * newPoint.x + (1 - alpha) * lastPoint.x,
                y = alpha * newPoint.y + (1 - alpha) * lastPoint.y
            )
        }
    }

    interface DetectorListener {
        fun onError(error: String)
        fun onResults(
            results: MutableList<Detection>?,
            inferenceTime: Long,
            imageHeight: Int,
            imageWidth: Int
        )
    }

    companion object {
        const val DELEGATE_CPU = 0
        const val DELEGATE_GPU = 1
        const val DELEGATE_NNAPI = 2
        const val MODEL_MOBILENETV1 = 0
        const val MODEL_EFFICIENTDETV0 = 1
        const val MODEL_EFFICIENTDETV1 = 2
        const val MODEL_EFFICIENTDETV2 = 3
    }
}
