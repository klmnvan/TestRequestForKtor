package com.metehanbolat.httprequestwithktorclientcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.metehanbolat.httprequestwithktorclientcompose.data.remote.PostsService
import com.metehanbolat.httprequestwithktorclientcompose.data.remote.dto.PostResponse
import com.metehanbolat.httprequestwithktorclientcompose.data.remote.models.Catalog
import com.metehanbolat.httprequestwithktorclientcompose.ui.theme.HTTPRequestWithKtorClientComposeTheme

class MainActivity : ComponentActivity() {

    private val service = PostsService.create()

    @SuppressLint("ProduceStateDoesNotAssignValue")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /** Observable bir snapshot'a ihtiyacımız olacak o yüzden produceState kullanabiliriz.*//*
            val posts = produceState<List<PostResponse>>(
                initialValue = emptyList(),
                producer = {
                    value = service.getPosts()
                    var i = 5
                }
            )*/

           /* val posts = produceState<List<Catalog>>(
                initialValue = emptyList(),
                producer = {
                    value = service.getCatalog()
                }
            )*/

            val posts1 = produceState(
                initialValue = "",
                producer = {
                    service.sendCode("nesklmnvan@gmail.com")!!
                }
            )

            HTTPRequestWithKtorClientComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Text(text = posts1.value + " что-то", fontSize = 14.sp)
                    /*LazyColumn {
                        items(posts.value) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(text = it.name.toString(), fontSize = 20.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = it.description  + " - title", fontSize = 14.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = it.category  + " - body", fontSize = 14.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = it.price.toString() + " - userId", fontSize = 14.sp)
                            }
                        }
                    }*/
                }
            }
        }
    }
}
