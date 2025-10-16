package com.example.reclameaqui.screens.main.recentvomplaintsscreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.reclameaqui.R
import com.example.reclameaqui.data.ComplaintPost
import com.example.reclameaqui.data.ComplaintPostUi
import com.example.reclameaqui.ui.theme.bodyFontFamily
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ComplaintCard(
    post: ComplaintPostUi,
    isMyComplaint: Boolean,
    deletePost: () -> Unit
) {

    val context = LocalContext.current

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 20.dp)
            .border(
                border = BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(24.dp)
            )
    ) {

        Column() {

            // Linha e nome do autor.
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 16.dp)
            ) {

                // No futuro será subtituído por AsyncImage.
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = stringResource(R.string.imagem_do_autor_complaintcard),
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 12.dp)
                )

                // Nome do autor.
                Text(
                    text = post.author,
                    fontWeight = FontWeight.Bold,
                    fontFamily = bodyFontFamily,
                    textAlign = TextAlign.Start
                )

                if (isMyComplaint) {
                    Box(
                        contentAlignment = Alignment.BottomEnd,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(
                            onClick = { deletePost() },
                            modifier = Modifier.size(30.dp)
                            ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Excluir post"
                            )
                        }
                    }
                }

            }
                Text(
                    text = post.text,
                    fontFamily = bodyFontFamily,
                    textAlign = TextAlign.Justify,
                    overflow = TextOverflow.Ellipsis, // Adiciona "..." se o texto precisar ultrapssar o limite máximo.
                    modifier = Modifier
                        .padding(
                            top = 8.dp,
                            start = 20.dp,
                            end = 20.dp
                        )
                        .fillMaxWidth()
                        .heightIn(max = 100.dp)
                        .verticalScroll(rememberScrollState())
                )

            // Data da postagem.
                // Passar para o ViewModel.
                val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
                val textPostDate = context.getString(
                    R.string.postado_em_as_complaintcard,
                    post.postDate.format(dateFormatter),
                    post.postDate.format(timeFormatter)
                )

            Box(
                contentAlignment = Alignment.BottomEnd,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = textPostDate,
                    fontFamily = bodyFontFamily,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .padding(
                            bottom = 12.dp,
                            start = 20.dp,
                            end = 20.dp
                        )
                        .fillMaxWidth()
                )
            }

        }

    }

}

/*
@Preview
@Composable
fun ComplaintCardPreview(){
    ComplaintCard("Vinicius Mendes", "Oi, eu sou o vini.", LocalDateTime.now())
}*/
