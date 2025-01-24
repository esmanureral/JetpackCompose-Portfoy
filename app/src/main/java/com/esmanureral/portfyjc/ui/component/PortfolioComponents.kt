package com.esmanureral.portfyjc.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.esmanureral.portfyjc.R
import com.esmanureral.portfyjc.data.model.Project


@Preview
@Composable
 fun PortfolioCard(modifier: Modifier=Modifier)
{
    val portfolioShowState=remember{ mutableStateOf(false) }

    Surface(modifier =modifier.fillMaxSize() )
    {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(400.dp)
                .padding(20.dp),
            elevation = CardDefaults.cardElevation(10.dp),//kenarlara gölge verdi.
            shape= RoundedCornerShape(corner = CornerSize(15.dp)),//kenar ovalliği
            colors = CardDefaults.cardColors(containerColor = Color.Black)//arka card rengi
        )
        { //CARD {
            Column(
                modifier=Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,//dikeyde en üsten
                horizontalAlignment = Alignment.CenterHorizontally//yatayda ortala
            )
            {
                PortfolioProfileImage()
                //divider profile kısmının altından çizgi çekti.
                Divider(
                    modifier = modifier
                        .padding(20.dp)
                        .width(250.dp),
                    color = Color.Gray,
                    thickness = 0.5.dp//çizginin inceliği
               )
                //Cizgi altından devam edip bilgilerimizin gözükmesi için:
                PortfolioPersonInfo()
                Button(onClick = {
                    //portfolioShowState butona tıklandıgında false yerine true olsun
                    portfolioShowState.value=!portfolioShowState.value
                }) {
                    Text(text = "Portföy")
                }
                PortfolioProject(isShown = portfolioShowState.value)

            }
        }
    }
}

@Preview
@Composable
private fun PortfolioProfileImage(modifier: Modifier=Modifier) {
    Surface(
        modifier=modifier
            .size(150.dp)
            .padding(10.dp),
        shape= CircleShape,
        border = BorderStroke(1.dp,Color.Red),//circle kenarının rengi
        shadowElevation =10.dp
        ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(CircleShape)
        )
          }
}

@Preview
@Composable
private fun PortfolioPersonInfo() {
    Column(
        modifier = Modifier.padding(5.dp)

    ) {
        Text(
            text="Esmanur Eral",
            style = MaterialTheme.typography.headlineMedium, //yazı stili
            color = Color.White//text rengi
            )
        Text(
            text="Bilgisayar Mühendisliği",
            style = MaterialTheme.typography.titleMedium, //yazı stili
            color = Color.White//text rengi
         )
    }
}
@Preview
@Composable
 fun PortfolioProject(modifier: Modifier = Modifier,isShown:Boolean=false) {
    if(isShown){
        Box(modifier=modifier
            .fillMaxSize()
            .padding(5.dp)
        ){
            Surface(
                modifier=modifier
                    .padding(5.dp)
                    .fillMaxSize(),
                shape= RoundedCornerShape(corner = CornerSize(10.dp)),
                border = BorderStroke(width = 1.dp, color = Color.Red)
                ) {
                PortfolioProjectItem(data = listOf(
                    Project(
                        "JetpackCompose-HesapMakinesi",

                        "Bu proje, Jetpack Compose kullanılarak temel aritmetik işlemleri gerçekleştiren bir hesap makinesi uygulamasıdır."

                    ),
                    Project(
                        "TipTimeKotlin",

                        "Bu uygulama, restoran faturaları için bahşiş hesaplamanızı kolaylaştırmak amacıyla geliştirilmiştir."
                    ),
                    Project(
                        "Kotlin Hesap Makinesi",

                        "Bu proje, Kotlin kullanarak geliştirdiğim basit bir hesap makinesi uygulamasıdır."
                    ),




                    ))

            }
        }
    }
}

@Composable
fun PortfolioProjectItem(modifier: Modifier = Modifier,data:List<Project>) {
    LazyColumn {
        items(data){item ->
            Card(
                modifier=modifier.padding(1.dp).fillMaxWidth(),
                shape = RoundedCornerShape(20.dp)
            ) {
                Row(
                    modifier=modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(10.dp)
                ){
                    PortfolioProfileImage(modifier=Modifier.size(100.dp))
                    Column(
                        modifier=modifier
                            .padding(10.dp)
                            .align(Alignment.CenterVertically)//dikeyde ortala
                    ) {
                        Text(text = item.projectName.toString(),
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleMedium,

                        )
                        Text(text = item.projectDescription.toString(),
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 12.sp)

                    }

                }

            }
            

        }
    }
    
}