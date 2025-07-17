package com.resconapss.documents.scanner.pdfgenerator.activitvities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Size
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.resconapss.documents.scanner.pdfgenerator.Drawing.DrawFunction
import com.resconapss.documents.scanner.pdfgenerator.R
import com.resconapss.documents.scanner.pdfgenerator.models.ObjectDetectionViewModel
import com.resconapss.documents.scanner.pdfgenerator.models.ObjectDetectorHelper
import com.resconapss.documents.scanner.pdfgenerator.screens.ImageTransformation
import com.resconapss.documents.scanner.pdfgenerator.utills.Config
import com.resconapss.documents.scanner.pdfgenerator.utills.Utils.Companion.sizeHeight
import com.resconapss.documents.scanner.pdfgenerator.utills.Utils.Companion.sizeWidth
import java.io.ByteArrayOutputStream
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraView : ComponentActivity(){
    private val capturedImages = mutableListOf<ByteArray>()

    private lateinit var viewModel: ObjectDetectionViewModel

    lateinit  var resultViewSize: Size

    private lateinit var cameraExecutor: ExecutorService

    private lateinit var objectDetectorHelper: ObjectDetectorHelper
    private lateinit var bitmapBuffer: Bitmap
    private var preview: androidx.camera.core.Preview? = null
    private var imageAnalyzer: ImageAnalysis? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ObjectDetectionViewModel::class.java)
        objectDetectorHelper = ObjectDetectorHelper(
            context = this@CameraView,
            viewModel = viewModel,
            objectDetectorListener = this
        )
        cameraExecutor = Executors.newSingleThreadExecutor()

        setContent {

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                bindCameraUseCases()
                CameraViewContent()
            }
        }
    }
    @Composable
    fun CameraViewContent() {
        CameraView(viewModel)
    }

    @Preview(showBackground = true)
    @Composable
    fun CameraPreviewPreview() {
    }

    @SuppressLint("NotConstructor")
    @Composable
    fun CameraView(viewModel: ObjectDetectionViewModel) {
        // Observe LiveData as State
        val q1 by viewModel.q1.observeAsState(Offset.Zero)
        val q2 by viewModel.q2.observeAsState(Offset.Zero)
        val q3 by viewModel.q3.observeAsState(Offset.Zero)
        val q4 by viewModel.q4.observeAsState(Offset.Zero)

        DrawFunction(q1, q2, q3, q4)

        Log.d("PointsDetectionCamera", "P 1 $q1")
        Log.d("PointsDetectionCamera", "P 2 $q2")
        Log.d("PointsDetectionCamera", "P 3 $q3")
        Log.d("PointsDetectionCamera", "P 4 $q4")

    }

    @Composable
    @SuppressLint("UnsafeOptInUsageError")
    private fun bindCameraUseCases() {
        val executor = ContextCompat.getMainExecutor(this)
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        var imageCapture: ImageCapture? = null
        val cameraProvider = cameraProviderFuture.get()
        val lifecycleOwner = this
        lateinit var previewView: PreviewView

        var selectedImageUris by remember {
            mutableStateOf<List<Uri>>(emptyList())
        }
        val context = LocalContext.current

        val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickMultipleVisualMedia(),
            onResult = { uris ->
                selectedImageUris = uris
                // Start the new Activity and pass the list of URIs
                val intent = Intent(context, ScanDetailsFirstActivity::class.java).apply {
                    putParcelableArrayListExtra("imageUris", ArrayList(uris))
                }
                context.startActivity(intent)
            }
        )

        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val boxConstraint = this
            sizeWidth = with(LocalDensity.current) { boxConstraint.maxWidth.toPx() }
            sizeHeight = with(LocalDensity.current) { boxConstraint.maxHeight.toPx() }
            resultViewSize = android.util.Size(sizeWidth.toInt(), sizeHeight.toInt())

            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { ctx ->
                    previewView = PreviewView(ctx)
                    cameraProviderFuture.addListener({
                        imageAnalyzer = ImageAnalysis.Builder()
                            .setTargetRotation(android.view.Surface.ROTATION_0)
                            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                            .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
                            .build().also {
                                it.setAnalyzer(cameraExecutor) { image ->
                                    if (!::bitmapBuffer.isInitialized) {
                                        bitmapBuffer = Bitmap.createBitmap(
                                            image.width,
                                            image.height,
                                            Bitmap.Config.ARGB_8888
                                        )
                                    }
                                    detectObjects(image)
                                }
                            }

                        imageCapture = ImageCapture.Builder()
                            .setTargetRotation(previewView.display.rotation)
                            .build()

                        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            cameraSelector,
                            imageCapture,
                            preview,
                            imageAnalyzer
                        )
                    }, executor)

                    preview = androidx.camera.core.Preview.Builder().build().also {
                        it.setSurfaceProvider(previewView.surfaceProvider)
                    }
                    previewView
                }
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        Config.gallerycamdecider=1
                        multiplePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icgallery),
                        contentDescription = "Gallery Icon",
                        modifier = Modifier.size(40.dp),
                        tint = Color.Unspecified
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = {
                        imageCapture?.takePicture(
                            executor,
                            object : ImageCapture.OnImageCapturedCallback() {
                                override fun onCaptureSuccess(image: ImageProxy) {
                                    val bitmap = image.toBitmap()
                                    val boundingBox = getBoundingBox(bitmap)

                                    Log.d("CaptureButton", "Image Height: ${bitmap.height}, Image Width: ${bitmap.width}")

                                    if (boundingBox.width() > 0 && boundingBox.height() > 0) {
                                        val croppedBitmap = cropToBoundingBox(bitmap, boundingBox)


                                        val q1 = viewModel.q1.value ?: Offset.Zero
                                        val q2 = viewModel.q2.value ?: Offset.Zero
                                        val q3 = viewModel.q3.value ?: Offset.Zero
                                        val q4 = viewModel.q4.value ?: Offset.Zero

                                        navigateToNextScreen(bitmap, q1, q2, q3, q4)

                                    } else {
                                        Log.e("CaptureButton", "Invalid bounding box dimensions")
                                    }

                                    image.close()
                                }

                                override fun onError(exception: ImageCaptureException) {
                                    Log.e("CaptureButton", "Error capturing image: ${exception.message}", exception)
                                }
                            }
                        )
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.cambutton),
                        contentDescription = "Capture Icon",
                        modifier = Modifier.size(40.dp),
                        tint = Color.Unspecified
                    )
                }

                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }

    private fun getBoundingBox(bitmap: Bitmap): Rect {
        // Example: capture a central rectangle
        val centerX = bitmap.width / 2
        val centerY = bitmap.height / 2
        val boxWidth = bitmap.width / 2
        val boxHeight = bitmap.height / 2
        return Rect(
            centerX - boxWidth / 2,
            centerY - boxHeight / 2,
            centerX + boxWidth / 2,
            centerY + boxHeight / 2
        )
    }
    private fun cropToBoundingBox(bitmap: Bitmap, boundingBox: Rect): Bitmap {
        // Ensure bounding box stays within the bitmap's dimensions
        val left = boundingBox.left.coerceAtLeast(0)
        val top = boundingBox.top.coerceAtLeast(0)
        val right = boundingBox.right.coerceAtMost(bitmap.width)
        val bottom = boundingBox.bottom.coerceAtMost(bitmap.height)

        return Bitmap.createBitmap(
            bitmap,
            left,
            top,
            right - left,
            bottom - top
        )
    }
    //    private fun cropToBoundingBox(bitmap: Bitmap, boundingBox: Rect): Bitmap {
//        return Bitmap.createBitmap(
//            bitmap,
//            boundingBox.left,
//            boundingBox.top,
//            boundingBox.width(),
//            boundingBox.height()
//        )
//    }
//    private fun navigateToNextScreen(byteArray: ByteArray, q1: Offset, q2: Offset, q3: Offset, q4: Offset) {
//        val intent = Intent(this@CameraView, Activity::class.java).apply {
//            putExtra("image", byteArray)
//            putExtra("q1", floatArrayOf(q1.x, q1.y))
//            putExtra("q2", floatArrayOf(q2.x, q2.y))
//            putExtra("q3", floatArrayOf(q3.x, q3.y))
//            putExtra("q4", floatArrayOf(q4.x, q4.y))
//
//            Log.e("TAGI","x1=$q1")
//            Log.e("TAGI","x2=$q2")
//            Log.e("TAGI","x3=$q3")
//            Log.e("TAGI","x4=$q4")
//
//        }
//        startActivity(intent)
//    }
    private fun navigateToNextScreen(bitmap: Bitmap, q1: Offset, q2: Offset, q3: Offset, q4: Offset) {
        val byteArray = bitmapToByteArray(bitmap)
        val intent = Intent(this@CameraView, ImageTransformation::class.java).apply {
            putExtra("full_image", byteArray)
            putExtra("q1", floatArrayOf(q1.x, q1.y))
            putExtra("q2", floatArrayOf(q2.x, q2.y))
            putExtra("q3", floatArrayOf(q3.x, q3.y))
            putExtra("q4", floatArrayOf(q4.x, q4.y))
            putExtra("screen_width", resultViewSize.width.toFloat())
            putExtra("screen_height", resultViewSize.height.toFloat())
        }
        startActivity(intent)
    }



    private fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream)
        return stream.toByteArray()
    }
    private fun detectObjects(image: ImageProxy) {
        // Copy out RGB bits to the shared bitmap buffer
        image.use { bitmapBuffer.copyPixelsFromBuffer(image.planes[0].buffer) }

        val imageRotation = image.imageInfo.rotationDegrees
        // Pass Bitmap and rotation to the object detector helper for processing and detection

        objectDetectorHelper.detect(bitmapBuffer, imageRotation)
// Update the detected bitmap state
    }
    //------------------------Fin  onCreate --------------------------------
    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}