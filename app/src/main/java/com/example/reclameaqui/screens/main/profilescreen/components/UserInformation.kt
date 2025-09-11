package com.example.reclameaqui.screens.main.profilescreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reclameaqui.R

@Composable
fun UserNameInformation(
    content: String,
    type: String
) {
    Box (
        modifier = Modifier
            .height(70.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(size = 24.dp),
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = content,
                        /*fontWeight = FontWeight.Bold,*/
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(.1f)
                    )
                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .padding(top = 8.dp, bottom = 8.dp)
                        .width(2.dp)
                        .background(Color.Black)
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                    IconButton(
                        onClick = { /*Editar informação*/ }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = stringResource(
                                R.string.alterar_informacao_userInformation, type
                            ),
                            modifier = Modifier.size(25.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(24.dp))
                }
            }
        }
        Text(
            text = type,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(start = 42.dp, top = 10.dp)
        )
    }
}

@Composable
fun UserLikeInformation(
    content: String,
    type: String
) {
    Box (
        modifier = Modifier
            .height(70.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(size = 24.dp),
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = content,
                        /*fontWeight = FontWeight.Bold,*/
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(.1f)
                    )
                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .padding(top = 8.dp, bottom = 8.dp)
                        .width(2.dp)
                        .background(Color.Black)
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                    IconButton(
                        onClick = { /*Editar informação*/ }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = stringResource(
                                R.string.alterar_informacao_userInformation, type
                            ),
                            modifier = Modifier.size(25.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(24.dp))
                }
            }
        }
        Text(
            text = type,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(start = 42.dp, top = 10.dp)
        )
    }
}