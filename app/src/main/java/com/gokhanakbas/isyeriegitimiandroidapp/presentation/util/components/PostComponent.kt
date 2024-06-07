package com.gokhanakbas.isyeriegitimiandroidapp.presentation.util.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gokhanakbas.isyeriegitimiandroidapp.R
import com.gokhanakbas.isyeriegitimiandroidapp.domain.model.Post
import com.gokhanakbas.isyeriegitimiandroidapp.presentation.navigation.Screen
import com.gokhanakbas.isyeriegitimiandroidapp.ui.theme.GaziAcikMavi


@Composable
fun PostComponent (navController: NavController, post: Post) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        border = BorderStroke(1.dp, GaziAcikMavi)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //Paylaşan kişinin bilgileri ve tarihin olduğu kısım
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(modifier=Modifier.weight(0.7f), verticalAlignment = Alignment.CenterVertically){
                        Image(
                            painter = painterResource(id = R.drawable.gazi_university_logo),
                            contentDescription = "",
                            modifier = Modifier
                                .size(30.dp)
                                .weight(0.2f)
                                .clickable {
                                    navController.navigate(Screen.FirmPage.passNavigate(firm_id = post.post_firm.firm_id))
                                })
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = post.post_firm.firm_name,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .weight(0.8f)
                                .clickable {
                                navController.navigate(Screen.FirmPage.passNavigate(firm_id = post.post_firm.firm_id))
                            })
                    }
                    Text(text = post.post_date, fontSize = 18.sp, color = Color.LightGray, modifier = Modifier.weight(0.3f))

                }

                Spacer(modifier = Modifier.height(8.dp))
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .padding(start = 15.dp, end = 15.dp)
                        .background(Color.LightGray)
                )
                Spacer(modifier = Modifier.height(8.dp))
                //Fotoğraf ve açıklamanın olacağı kısım.
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = post.post_label,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Start,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.havelsan),
                                contentDescription = "",
                                contentScale = ContentScale.Fit,
                                alignment = Alignment.Center,
                                modifier = Modifier.clip(
                                    RoundedCornerShape(6.dp)
                                )
                            )
                        }

                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                //Buttonların olacağı kısım
                var favoriIcon = if(post.post_isFavourite) Color.Yellow else Color.Black
                var favoriText = if(post.post_isFavourite) stringResource(id = R.string.favorilerden_cikar) else stringResource(id = R.string.favorilere_ekle)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(onClick = {
                        if(post.post_isFavourite){
                            post.post_isFavourite=false
                        }
                        else{
                            post.post_isFavourite=true
                        }
                        //Favorilere ekleme kısmı . Burada işlemin gerçekleşip gerçekleşmediğine göre de rengi değiştirebilirsin.
                    }) {

                        Icon(
                            painter = painterResource(id = R.drawable.bookmark_icon),
                            contentDescription = "",
                            modifier = Modifier.size(18.dp),
                            tint = favoriIcon,

                        )

                        Text(text = favoriText, fontSize = 18.sp)
                    }
                    OutlinedButton(onClick = {
                        //ilana yönlendirme
                        navController.navigate(Screen.AdvertPage.passNavigate(advert_id = post.post_advert_id))
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.forward_icon),
                            contentDescription = "",
                            modifier = Modifier.size(18.dp)
                        )
                        Text(text = stringResource(id = R.string.ilana_git), fontSize = 18.sp)
                    }
                }
            }
        }
    }
}