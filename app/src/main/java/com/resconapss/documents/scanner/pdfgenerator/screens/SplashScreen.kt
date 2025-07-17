package com.resconapss.documents.scanner.pdfgenerator.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.resconapss.documents.scanner.pdfgenerator.R
import com.resconapss.documents.scanner.pdfgenerator.activitvities.CloudBackupHomeActivity
import com.resconapss.documents.scanner.pdfgenerator.activitvities.CreateFolderActivity
import com.resconapss.documents.scanner.pdfgenerator.activitvities.HomeActivity
import com.resconapss.documents.scanner.pdfgenerator.activitvities.IDCardScanActivity
import com.resconapss.documents.scanner.pdfgenerator.activitvities.IntroActivity
import com.resconapss.documents.scanner.pdfgenerator.activitvities.SettingActivity
import com.resconapss.documents.scanner.pdfgenerator.utills.MySharedPreferens
import kotlinx.coroutines.delay

lateinit var mySharedPreferens: MySharedPreferens
var firstcheck:Boolean=false

@Composable
fun SplashScreen() {



    val popinbold = FontFamily(
        Font(R.font.popinboldsate, FontWeight.Bold)
    )
    val popinreguler = FontFamily(
        Font(R.font.popinregulersate, FontWeight.Normal)
    )
    val context = LocalContext.current

    // Remember the state to track if the next activity has been started
    var hasNavigated by remember { mutableStateOf(false) }

    // Schedule the next activities using LaunchedEffect
    LaunchedEffect(Unit) {
        delay(5000)
        if (!hasNavigated) {
            moveToNext(context)
            hasNavigated = true
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // first box
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(top = 30.dp, start = 20.dp),
                        text = stringResource(R.string.app_name),
                        fontSize = 26.sp,
                        fontFamily = popinbold,
                        color = Color.Black
                    )
                    Text(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(start = 20.dp),
                        text = stringResource(R.string.idscard),
                        fontSize = 14.sp,
                        fontFamily = popinreguler,
                        color = colorResource(id = R.color.lightblue)
                    )
                }
            }

            // second box
            Box(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // inner_box1
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.icon),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(20.dp)
                                .align(Alignment.Center)
                        )
                    }
                    // inner_box2
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        val composition by rememberLottieComposition(
                            LottieCompositionSpec.RawRes(R.raw.splashanimation)
                        )
                        LottieAnimation(
                            composition,
                            iterations = LottieConstants.IterateForever,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(50.dp)
                        )
                    }
                }
            }
            // third box
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxWidth()
            )
        }
    }
}

private fun moveToNext(context: Context) {

    mySharedPreferens= MySharedPreferens(context)
    firstcheck=mySharedPreferens.getfirsttime("first_time")
    if(!firstcheck)
    {
        context.startActivity(Intent(context, IntroActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        })
    }
    else
    {
        context.startActivity(Intent(context, HomeActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        })
    }


}
