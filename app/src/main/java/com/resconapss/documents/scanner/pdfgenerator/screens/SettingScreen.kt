package com.resconapss.documents.scanner.pdfgenerator.screens

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.resconapss.documents.scanner.pdfgenerator.R
import com.resconapss.documents.scanner.pdfgenerator.activitvities.CreateFolderActivity
import com.resconapss.documents.scanner.pdfgenerator.generalmathods.MyWidgetsFunctions
import com.resconapss.documents.scanner.pdfgenerator.utills.Config
import com.resconapss.documents.scanner.pdfgenerator.utills.Config.Companion.popinbold


@Composable
fun SettingScreen(context: Activity) {
    Config.mycontext = context
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Toolbar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f)
            ) {
                MyWidgetsFunctions.my_custom_toolbar(
                    title = stringResource(id = R.string.settings),
                    context = context
                )
            }
            Spacer(modifier = Modifier.fillMaxWidth().height(30.dp))
          Box(modifier = Modifier
              .fillMaxWidth()
              .weight(1.9f))
          {

              LazyColumn(
                  modifier = Modifier
                      .fillMaxWidth()
                      .padding(12.dp)
              ) {
                  item {
                      Column(
                          modifier = Modifier
                              .background(
                                  brush = Brush.linearGradient(
                                      colors = listOf(
                                          colorResource(id = R.color.gradiant1),
                                          colorResource(id = R.color.gradiant2)
                                      ),
                                      start = Offset(20f, 20f),
                                      end = Offset(1000f, 1000f)
                                  ),
                                  shape = RoundedCornerShape(10.dp)
                              )
                              .fillMaxWidth()
                              .height(170.dp)
                      ) {
                          Box(
                              modifier = Modifier
                                  .fillMaxSize()
                                  .padding(5.dp)
                                  .background(Color.White)
                          ) {
                              Column(
                                  verticalArrangement = Arrangement.Center,
                                  horizontalAlignment = Alignment.CenterHorizontally,
                                  modifier = Modifier
                                      .padding(2.dp)
                                      .background(
                                          brush = Brush.linearGradient(
                                              colors = listOf(Color.Magenta, Color.Cyan),
                                              start = Offset(20f, 20f),
                                              end = Offset(1000f, 1000f)
                                          ),
                                          shape = RoundedCornerShape(10.dp)
                                      )
                                      .fillMaxSize()
                              ) {
                                  Text(
                                      modifier = Modifier
                                          .wrapContentSize()
                                          .padding(start = 10.dp, top = 10.dp),
                                      text = stringResource(R.string.getcloudbackupfordocuments),
                                      fontSize = 15.sp,
                                      fontFamily = popinbold,
                                      color = colorResource(id = R.color.white)
                                  )
                                  Row(
                                      modifier = Modifier
                                          .fillMaxSize()
                                          .padding(10.dp)
                                  ) {
                                      Box(
                                          modifier = Modifier
                                              .fillMaxHeight()
                                              .weight(1f)
                                      ) {
                                          Spacer(modifier = Modifier.width(28.dp))

                                          MyWidgetsFunctions.upgradetoprimium {

                                          }

                                      }

                                      Box(
                                          modifier = Modifier
                                              .fillMaxHeight()
                                              .weight(1f)
                                      ) {
                                          Image(
                                              painter = painterResource(id = R.drawable.girl),
                                              contentDescription = null,
                                              modifier = Modifier
                                                  .wrapContentSize()
                                                  .align(Alignment.CenterEnd)
                                          )
                                      }
                                  }
                              }
                          }
                      }
                  }
                  item {
                      Spacer(modifier = Modifier
                          .fillMaxWidth()
                          .height(20.dp))
                      Box(modifier = Modifier
                          .fillMaxWidth()
                          .height(100.dp).clickable {
                              val intent=Intent(context,CreateFolderActivity::class.java)
                              context.startActivity(intent)
                          }
                          .background(
                              colorResource(id = R.color.neela), shape = RoundedCornerShape(18.dp)
                          ))
                      {
                          Box(
                              modifier = Modifier
                                  .fillMaxSize()
                                  .padding(5.dp)
                                  .background(
                                      colorResource(id = R.color.gradiant3),
                                      shape = RoundedCornerShape(18.dp)
                                  )
                          )
                          {
                              Row(
                                  verticalAlignment = Alignment.CenterVertically,
                                  horizontalArrangement = Arrangement.SpaceBetween,
                                  modifier = Modifier
                                      .fillMaxWidth()
                                      .padding(10.dp)
                              ) {
                                  Row(verticalAlignment = Alignment.CenterVertically) {
                                      // Replace 'R.drawable.ic_folder' with your folder icon resource
                                      Icon(
                                          painter = painterResource(id = R.drawable.folders),
                                          contentDescription = null,
                                          tint = Color.Unspecified,
                                          modifier = Modifier.size(50.dp)
                                      )
                                      Spacer(modifier = Modifier.width(16.dp))
                                      Column {
                                          Text(text = "Create Custom Folder", fontFamily = popinbold, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                          Text(text = "Make your folder according to your choose", fontSize = 14.sp, color = Color.White)
                                      }
                                  }


                              }
                          }
                      }
                  }
                  item {
                      Spacer(modifier = Modifier
                          .fillMaxWidth()
                          .height(20.dp))

                      Text(text = stringResource(id = R.string.otherresources), fontFamily = popinbold, color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold)


                  }
                  item {
                      Spacer(modifier = Modifier
                          .fillMaxWidth()
                          .height(10.dp))
                      Box(modifier = Modifier
                          .fillMaxWidth()
                          .wrapContentHeight()
                          .background(
                              colorResource(id = R.color.sigmawhite),
                              shape = RoundedCornerShape(20.dp)
                          )
                          )
                      {
                          Column {
                              Row(modifier = Modifier
                                  .fillMaxWidth()
                                  .height(70.dp)
                                  .padding(5.dp)) {

                                  Box(modifier = Modifier
                                      .fillMaxHeight()
                                      .weight(0.5f))
                                  {
                                      Image(
                                          modifier = Modifier
                                              .align(Alignment.Center)
                                              .fillMaxSize()
                                              .padding(5.dp),
                                          painter = painterResource(id = R.drawable.privacy),  // Replace with your drawable resource ID
                                          contentDescription = "Profile"
                                      )

                                  }
                                  Box(modifier = Modifier
                                      .fillMaxHeight()
                                      .weight(2f))
                                  {
                                      Text(
                                          modifier = Modifier
                                              .wrapContentSize()
                                              .align(AbsoluteAlignment.CenterLeft)
                                              .padding(start = 10.dp),
                                          text = stringResource(id = R.string.privacypolicy),
                                          fontSize = 20.sp,
                                          fontFamily = popinbold,
                                          color = colorResource(id = R.color.black)
                                      )
                                  }
                                  Box(modifier = Modifier
                                      .fillMaxHeight()
                                      .weight(0.5f)
                                     )
                                  {
                                      Image(
                                          modifier = Modifier
                                              .align(Alignment.Center)
                                              .fillMaxSize()
                                              .padding(15.dp),
                                          painter = painterResource(id = R.drawable.forwordarrow),  // Replace with your drawable resource ID
                                          contentDescription = "Profile"
                                      )
                                  }

                              }
                              Spacer(modifier = Modifier
                                  .fillMaxWidth()
                                  .height(5.dp))
                              Row(modifier = Modifier
                                  .fillMaxWidth()
                                  .height(70.dp)
                                  .padding(5.dp)) {

                                  Box(modifier = Modifier
                                      .fillMaxHeight()
                                      .weight(0.5f))
                                  {
                                      Image(
                                          modifier = Modifier
                                              .align(Alignment.Center)
                                              .fillMaxSize()
                                              .padding(5.dp),
                                          painter = painterResource(id = R.drawable.rateus),  // Replace with your drawable resource ID
                                          contentDescription = "Profile"
                                      )

                                  }
                                  Box(modifier = Modifier
                                      .fillMaxHeight()
                                      .weight(2f))
                                  {
                                      Text(
                                          modifier = Modifier
                                              .wrapContentSize()
                                              .align(AbsoluteAlignment.CenterLeft)
                                              .padding(start = 10.dp),
                                          text = stringResource(id = R.string.rateus),
                                          fontSize = 20.sp,
                                          fontFamily = popinbold,
                                          color = colorResource(id = R.color.black)
                                      )
                                  }
                                  Box(modifier = Modifier
                                      .fillMaxHeight()
                                      .weight(0.5f)
                                  )
                                  {
                                      Image(
                                          modifier = Modifier
                                              .align(Alignment.Center)
                                              .fillMaxSize()
                                              .padding(15.dp),
                                          painter = painterResource(id = R.drawable.forwordarrow),  // Replace with your drawable resource ID
                                          contentDescription = "Profile"
                                      )
                                  }

                              }
                              Spacer(modifier = Modifier
                                  .fillMaxWidth()
                                  .height(5.dp))
                              Row(modifier = Modifier
                                  .fillMaxWidth()
                                  .height(70.dp)
                                  .padding(5.dp)) {

                                  Box(modifier = Modifier
                                      .fillMaxHeight()
                                      .weight(0.5f))
                                  {
                                      Image(
                                          modifier = Modifier
                                              .align(Alignment.Center)
                                              .fillMaxSize()
                                              .padding(5.dp),
                                          painter = painterResource(id = R.drawable.shareit),  // Replace with your drawable resource ID
                                          contentDescription = "Profile"
                                      )

                                  }
                                  Box(modifier = Modifier
                                      .fillMaxHeight()
                                      .weight(2f))
                                  {
                                      Text(
                                          modifier = Modifier
                                              .wrapContentSize()
                                              .align(AbsoluteAlignment.CenterLeft)
                                              .padding(start = 10.dp),
                                          text = stringResource(id = R.string.shareit),
                                          fontSize = 20.sp,
                                          fontFamily = popinbold,
                                          color = colorResource(id = R.color.black)
                                      )
                                  }
                                  Box(modifier = Modifier
                                      .fillMaxHeight()
                                      .weight(0.5f)
                                  )
                                  {
                                      Image(
                                          modifier = Modifier
                                              .align(Alignment.Center)
                                              .fillMaxSize()
                                              .padding(15.dp),
                                          painter = painterResource(id = R.drawable.forwordarrow),  // Replace with your drawable resource ID
                                          contentDescription = "Profile"
                                      )
                                  }

                              }
                              Spacer(modifier = Modifier
                                  .fillMaxWidth()
                                  .height(5.dp))
                              Row(modifier = Modifier
                                  .fillMaxWidth()
                                  .height(70.dp)
                                  .padding(5.dp)) {

                                  Box(modifier = Modifier
                                      .fillMaxHeight()
                                      .weight(0.5f))
                                  {
                                      Image(
                                          modifier = Modifier
                                              .align(Alignment.Center)
                                              .fillMaxSize()
                                              .padding(5.dp),
                                          painter = painterResource(id = R.drawable.moreapps),  // Replace with your drawable resource ID
                                          contentDescription = "Profile"
                                      )

                                  }
                                  Box(modifier = Modifier
                                      .fillMaxHeight()
                                      .weight(2f))
                                  {
                                      Text(
                                          modifier = Modifier
                                              .wrapContentSize()
                                              .align(AbsoluteAlignment.CenterLeft)
                                              .padding(start = 10.dp),
                                          text = stringResource(id = R.string.moreapps),
                                          fontSize = 20.sp,
                                          fontFamily = popinbold,
                                          color = colorResource(id = R.color.black)
                                      )
                                  }
                                  Box(modifier = Modifier
                                      .fillMaxHeight()
                                      .weight(0.5f)
                                  )
                                  {
                                      Image(
                                          modifier = Modifier
                                              .align(Alignment.Center)
                                              .fillMaxSize()
                                              .padding(15.dp),
                                          painter = painterResource(id = R.drawable.forwordarrow),  // Replace with your drawable resource ID
                                          contentDescription = "Profile"
                                      )
                                  }

                              }
                              //////////////////////////////////////////



                          }
                      }
                  }
              }

          }
        }
    }
}




