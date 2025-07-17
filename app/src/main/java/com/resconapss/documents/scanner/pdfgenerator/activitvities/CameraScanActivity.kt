package com.resconapss.documents.scanner.pdfgenerator.activitvities

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import com.resconapss.documents.scanner.pdfgenerator.screens.CameraScreen
import com.resconapss.documents.scanner.pdfgenerator.ui.theme.DocumentScannerAITheme
import com.resconapss.documents.scanner.pdfgenerator.utills.Config
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.bitmapToByteArray
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.openGalleryLauncher

class CameraScanActivity : ComponentActivity() {

    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private lateinit var previewView: PreviewView



    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        previewView = PreviewView(this)
        setContent {
            DocumentScannerAITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {


                    CameraPreview()
                   CameraScreen(this)
                 /*   openGalleryLauncher = registerForActivityResult(
                        ActivityResultContracts.GetContent()
                    ) { uri: Uri? ->
                        uri?.let {
                            val intent = Intent(this, ScanDetailsFirstActivity::class.java)
                            intent.putExtra("image_uri", uri.toString())
                            startActivity(intent)
                        }
                    }*/

                }
            }
        }
    }

    @Preview(showBackground = true, device = Devices.PIXEL_4_XL)
    @Composable
    fun CamPreview() {
        DocumentScannerAITheme {

            CameraScreen(this)
        }
    }


    @Composable
    fun CameraPreview() {



        val context = LocalContext.current

        AndroidView({ previewView }) { view ->
            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()
                bindPreview(cameraProvider, view)
            }, ContextCompat.getMainExecutor(context))
        }
    }

    private fun bindPreview(cameraProvider: ProcessCameraProvider, previewView: PreviewView) {


        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        val preview = androidx.camera.core.Preview.Builder().build().also {
            it.setSurfaceProvider(previewView.surfaceProvider)
        }

        cameraProvider.unbindAll()
        val camera = cameraProvider.bindToLifecycle(
            this,
            cameraSelector,
            preview
        )
    }

    companion object{

        fun captureImage(context: Activity) {
            val executor = ContextCompat.getMainExecutor(context)
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

            cameraProviderFuture.addListener({
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

                val preview = androidx.camera.core.Preview.Builder().build()
                val imageCapture = ImageCapture.Builder().build()

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        context as LifecycleOwner,
                        cameraSelector,
                        preview,
                        imageCapture
                    )

                    imageCapture.takePicture(
                        executor,
                        object : ImageCapture.OnImageCapturedCallback() {
                            override fun onCaptureSuccess(image: ImageProxy) {
                                var bitmap = image.toBitmap()

                                bitmap=Config.rotateBitmap(bitmap,90f)
                                Toast.makeText(context, "Image clicked", Toast.LENGTH_SHORT).show()

                                val byteArray = bitmapToByteArray(bitmap)

                                navigateToNextScreen(byteArray, context)

                                image.close()
                            }

                            override fun onError(exception: ImageCaptureException) {
                                Log.e("CaptureButton", "Error capturing image: ${exception.message}", exception)
                            }
                        }
                    )
                } catch (exc: Exception) {
                    Log.e("CaptureButton", "Use case binding failed", exc)
                }

            }, executor)
        }
        fun capture_multiple_Image(context: Activity) {
            val executor = ContextCompat.getMainExecutor(context)
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

            cameraProviderFuture.addListener({
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

                val preview = androidx.camera.core.Preview.Builder().build()
                val imageCapture = ImageCapture.Builder().build()

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        context as LifecycleOwner,
                        cameraSelector,
                        preview,
                        imageCapture
                    )

                    imageCapture.takePicture(
                        executor,
                        object : ImageCapture.OnImageCapturedCallback() {
                            override fun onCaptureSuccess(image: ImageProxy) {
                                var bitmap = image.toBitmap()

                                bitmap=Config.rotateBitmap(bitmap,90f)
                                Toast.makeText(context, "Image clicked", Toast.LENGTH_SHORT).show()

                                val byteArray = bitmapToByteArray(bitmap)

                                navigateToNextScreen(byteArray, context)

                                image.close()
                            }

                            override fun onError(exception: ImageCaptureException) {
                                Log.e("CaptureButton", "Error capturing image: ${exception.message}", exception)
                            }
                        }
                    )
                } catch (exc: Exception) {
                    Log.e("CaptureButton", "Use case binding failed", exc)
                }

            }, executor)
        }
        fun navigateToNextScreen(byteArray: ByteArray, context: Activity) {
            val intent = Intent(context, ScanDetailsFirstActivity::class.java).apply {
                putExtra("image", byteArray)
            }
            context.startActivity(intent)
            context.finish()
        }
        fun take_photo_fromgallery( context: Activity) {

            openGalleryLauncher.launch("image/*")
        }
    }





}





