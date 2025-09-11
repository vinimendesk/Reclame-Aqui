package com.example.reclameaqui.screens.main.familymembersscreen.components


import com.example.reclameaqui.data.User
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.reclameaqui.R
import com.example.reclameaqui.ui.theme.bodyFontFamily
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun FamilyMemberCard(member: User) {

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

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize()
        ) {

            // Nome do membro.
            Box (
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 8.dp)
            ) {

                // Nome do autor.
                Text(
                    text = member.name,
                    fontWeight = FontWeight.Bold,
                    fontFamily = bodyFontFamily,
                    fontSize = 16.sp
                )

            }

            // Foto perfil + whatLikeMore + whatDislikeMore.
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                Spacer(modifier = Modifier.width(24.dp))

                // No futuro será subtituído por AsyncImage.
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = stringResource(R.string.imagem_do_autor_complaintcard),
                    modifier = Modifier
                        .size(85.dp)
                )

                // whatLikeMore + whatDislikeMore
                Column(
                    modifier = Modifier
                        .padding(start = 8.dp)
                ) {
                    // O que mais agrada?.
                    Text(
                        text = stringResource(R.string.o_que_mais_agrada_familymembercard),
                        fontFamily = bodyFontFamily,
                        textAlign = TextAlign.Start,
                        fontSize = 14.sp
                    )
                    // O que mais agrada resposta.
                    Text(
                        text = member.whatLikeMore,
                        fontFamily = bodyFontFamily,
                        textAlign = TextAlign.Start,
                        fontSize = 10.sp,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )

                    // O que mais odeia?.
                    Text(
                        text = stringResource(R.string.o_que_mais_odeia_familymembercard),
                        fontFamily = bodyFontFamily,
                        textAlign = TextAlign.Start,
                        fontSize = 14.sp
                    )
                    // O que mais odeia resposta.
                    Text(
                        text = member.whatDislikeMore,
                        fontFamily = bodyFontFamily,
                        textAlign = TextAlign.Start,
                        fontSize = 10.sp
                    )
                }

            }

            // Reclamações.
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp)
            ) {

                val complaints = context.getString(
                    R.string.reclamações_familymembercard,
                    member.complaintsCount
                )

                // O que mais odeia?.
                Text(
                    text = complaints,
                    fontFamily = bodyFontFamily,
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp
                )

            }

        }
    }

}

@Preview
@Composable
fun ComplaintCardPreview(){
    FamilyMemberCard(
        User(
            name = "Vinicius Mendes",
            whatLikeMore = "Jogar vôlei",
            whatDislikeMore = "Mexer em minhas coisas",
            complaintsCount = 0
        )
    )
}