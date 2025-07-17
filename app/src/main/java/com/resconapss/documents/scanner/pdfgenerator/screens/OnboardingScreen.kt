package com.resconapss.documents.scanner.pdfgenerator.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.resconapss.documents.scanner.pdfgenerator.R
import com.resconapss.documents.scanner.pdfgenerator.activitvities.HomeActivity
import com.resconapss.documents.scanner.pdfgenerator.activitvities.IntroActivity
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.images
import com.resconapss.documents.scanner.pdfgenerator.utills.MySharedPreferens
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(navController: NavHostController, context: Activity) {

    val popinbold = FontFamily(
        Font(R.font.popinboldsate, FontWeight.Bold)
    )
    val popinreguler = FontFamily(
        Font(R.font.popinregulersate, FontWeight.Normal)
    )

    val introimages = listOf(
        R.drawable.intro1,
        R.drawable.intro2,
        R.drawable.intro3,

    )
    val titles = listOf(
        "Document Scanner",
        "ID Card Scanner",
        "Document Backup"
    )

    val descriptions = listOf(
        "Please scan your Documents for various Purpose.",
        "Please scan your ID card for various Purpose.",
        "Retrieve the deleted documents using the Backup."
    )
    val pagerState = rememberPagerState()


    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HorizontalPager(
            state = pagerState,
            count =introimages.size,

        ) { currentPage ->
            Column(
                Modifier
                    .wrapContentSize()
                    .padding(26.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(

                    painter = painterResource(id = introimages[currentPage]),
                    contentDescription = null,
                    modifier = Modifier.wrapContentSize().padding(20.dp).align(Alignment.CenterHorizontally)

                )
                Text(
                    text = titles[currentPage],
                    textAlign = TextAlign.Center,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = popinbold,

                )
                Text(
                    text = descriptions[currentPage],
                    Modifier.padding(top = 47.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontFamily = popinreguler,

                )
            }
        }

        PageIndicator(
            pageCount = introimages.size,
            currentPage = pagerState.currentPage,
            modifier = Modifier.padding(60.dp)
        )
    }

    ButtonsSection(
        pagerState = pagerState,
        navController = navController,
        context = context
    )

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ButtonsSection(pagerState: PagerState, navController: NavHostController, context: Activity) {

    val scope = rememberCoroutineScope()

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(30.dp)){
        if (pagerState.currentPage != 2){
            Text(text = "Next",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .clickable {

                           scope.launch {
                               val nextPage = pagerState.currentPage +1
                               pagerState.scrollToPage(nextPage)
                           }
                },
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(text = "Back",
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .clickable {
                        scope.launch {
                            val prevPage = pagerState.currentPage -1
                            if (prevPage >= 0){
                                pagerState.scrollToPage(prevPage)
                            }
                        }
                    },
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }else{
            OutlinedButton(onClick = {

                val mySharedPreferens=MySharedPreferens(context)
                mySharedPreferens.savefirstitme("first_time",true)
                val intent= Intent(context, HomeActivity::class.java)
                context.startActivity(intent)
                context.finish()

            },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            , colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF52C4D3)
            )
                ) {
                Text(
                    text = "Get Started",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun PageIndicator(pageCount: Int, currentPage: Int, modifier: Modifier) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        repeat(pageCount){
            IndicatorSingleDot(isSelected = it == currentPage )
        }


    }
}

@Composable
fun IndicatorSingleDot(isSelected: Boolean) {

    val width = animateDpAsState(targetValue = if (isSelected) 35.dp else 15.dp, label = "")
    Box(modifier = Modifier
        .padding(2.dp)
        .height(15.dp)
        .width(width.value)
        .clip(CircleShape)
        .background(if (isSelected) Color(0xFF69BEE4) else Color(0xFFB1E1E7))
    )
}



private fun onBoardingIsFinished(context: IntroActivity) {
    val sharedPreferences = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
   val editor = sharedPreferences.edit()
    editor.putBoolean("isFinished", true)
    editor.apply()

}

