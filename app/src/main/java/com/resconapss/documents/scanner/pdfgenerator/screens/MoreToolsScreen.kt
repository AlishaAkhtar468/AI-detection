package com.resconapss.documents.scanner.pdfgenerator.screens

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.resconapss.documents.scanner.pdfgenerator.R
import com.resconapss.documents.scanner.pdfgenerator.activitvities.CloudBackupHomeActivity
import com.resconapss.documents.scanner.pdfgenerator.activitvities.ConverttoWordActivity
import com.resconapss.documents.scanner.pdfgenerator.activitvities.CreateFolderActivity
import com.resconapss.documents.scanner.pdfgenerator.activitvities.RecentFilesActivity
import com.resconapss.documents.scanner.pdfgenerator.activitvities.SignaturePhotoActivity
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.MyWidgetsFunctions
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.UsableWidgets
import com.resconapss.documents.scanner.pdfgenerator.models.tools_item
import com.resconapss.documents.scanner.pdfgenerator.utills.Config
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.mycontext

import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.popinbold

@Composable

fun MoreToolsScreen(context: Activity) {
    Config.mycontext=context
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)

    ) {


        MyWidgetsFunctions.my_custom_toolbar(
            title = stringResource(id = R.string.moretools),
            context = context
        )
        UsableWidgets.spacer1(mymodifier = Modifier
            .fillMaxWidth()
            .height(30.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(scrollState)
        ) {
            Text(
                stringResource(id = R.string.convertor),
                fontWeight = FontWeight.Bold,
                fontFamily = popinbold,
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 20.dp)
            )
            UsableWidgets.spacer1(mymodifier = Modifier
                .fillMaxWidth()
                .height(30.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)) {
                LazyGridtool1()
            }

            UsableWidgets.spacer1(mymodifier = Modifier
                .fillMaxWidth()
                .height(30.dp))
            Text(
                stringResource(id = R.string.edit),
                fontWeight = FontWeight.Bold,
                fontFamily = popinbold,
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 20.dp)
            )
            UsableWidgets.spacer1(mymodifier = Modifier
                .fillMaxWidth()
                .height(30.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)) {
                LazyGridtool2()
            }

            UsableWidgets.spacer1(mymodifier = Modifier
                .fillMaxWidth()
                .height(30.dp))
            Text(
                stringResource(id = R.string.creator),
                fontWeight = FontWeight.Bold,
                fontFamily = popinbold,
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 20.dp)
            )
            UsableWidgets.spacer1(mymodifier = Modifier
                .fillMaxWidth()
                .height(30.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)) {
                LazyGridtool3()
            }
        }


    }
}

@Composable
fun LazyGridtool1() {
    val items = listOf(
        tools_item(R.drawable.tool_word, "To Word"),
        tools_item(R.drawable.tool_excel, "To Excel"),
        tools_item(R.drawable.tool_exeltopdf, "To PDF"),
        tools_item(R.drawable.tool_erase, "Smart Erase"),
        tools_item(R.drawable.tool_ppt, "To PPT")
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        itemsIndexed(items) { index, item ->
            GridItemView(item = item, index = index,1)
        }
    }
}

@Composable
fun LazyGridtool2() {
    val items = listOf(
        tools_item(R.drawable.tool_importfile, "Import Files"),
        tools_item(R.drawable.tool_cssign, "Cs Sign"),
        tools_item(R.drawable.tool_adwatermark, "Watermark"),
        tools_item(R.drawable.tool_merge, "Merge PDF")
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        itemsIndexed(items) { index, item ->
            GridItemView(item = item, index = index,2)
        }
    }
}

@Composable
fun LazyGridtool3() {
    val items = listOf(
        tools_item(R.drawable.tool_idcard, "ID Cards"),
        tools_item(R.drawable.tool_reconizetext, "Recognize Tex"),
        tools_item(R.drawable.tool_idcardmaker, "ID Photo Maker"),
        tools_item(R.drawable.tool_scanexcel, "Scan to Excel"),
        tools_item(R.drawable.tool_questionset, "Question Set"),
        tools_item(R.drawable.tool_book, "Make Book"),
        tools_item(R.drawable.tool_videoslide, "ppt Slide"),
        tools_item(R.drawable.tool_importimages, "Import Images"),
        tools_item(R.drawable.tool_slide, "White Board")
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        itemsIndexed(items) { index, item ->
            GridItemView(item = item, index = index,3)
        }
    }
}
fun gotonextactivity(index:Int,context: Activity,type:Int)
{
    when(type)
    {
        1->{
            when(index)
            {
                0->{
                    val intent=Intent(context,ConverttoWordActivity::class.java)
                    context.startActivity(intent)
                }
                1->{
                    val intent=Intent(context,ConverttoWordActivity::class.java)
                    context.startActivity(intent)
                }
                2->{
                    val intent=Intent(context,CreateFolderActivity::class.java)
                    context.startActivity(intent)
                }
                3->{
                    val intent=Intent(context,CloudBackupHomeActivity::class.java)
                    context.startActivity(intent)
                }
                else->{}
            }
        }
        2->{
            when(index)
            {
                0->{
                val intent=Intent(context,CloudBackupHomeActivity::class.java)
                context.startActivity(intent)
                }

                1->{
                    val intent=Intent(context,RecentFilesActivity::class.java)
                    context.startActivity(intent)
                }
                2->{
                    val intent=Intent(context,SignaturePhotoActivity::class.java)
                    context.startActivity(intent)
                }
                3->{
                    val intent=Intent(context,CreateFolderActivity::class.java)
                    context.startActivity(intent)
                }

                else->{}
            }

        }
        3->{

            when(index)
            {
                0->{
                    val intent=Intent(context,CreateFolderActivity::class.java)
                    context.startActivity(intent)
                }
                1->{
                    val intent=Intent(context,RecentFilesActivity::class.java)
                    context.startActivity(intent)
                }
                2->{
                    val intent=Intent(context,CloudBackupHomeActivity::class.java)
                    context.startActivity(intent)
                }
                3->{
                    val intent=Intent(context,SignaturePhotoActivity::class.java)
                    context.startActivity(intent)
                }


                else->{}
            }
        }

    }
    {

    }

}

@Composable
fun GridItemView(item: tools_item, index: Int,pos:Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            // Handle click event here
            Log.e("Index","$index")
           gotonextactivity(index, mycontext,pos)


        }
    ) {
        // Your existing Image and Text components
        // Replace this with your Image and Text components
        Image(
            painter = painterResource(id = item.iconRes),
            contentDescription = item.label,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(64.dp)
        )
        Text(
            text = item.label,
            fontSize = 16.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}



