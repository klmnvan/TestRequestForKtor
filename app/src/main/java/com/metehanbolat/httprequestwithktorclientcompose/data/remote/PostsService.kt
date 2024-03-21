package com.metehanbolat.httprequestwithktorclientcompose.data.remote

import com.metehanbolat.httprequestwithktorclientcompose.data.remote.dto.PostRequest
import com.metehanbolat.httprequestwithktorclientcompose.data.remote.dto.PostResponse
import com.metehanbolat.httprequestwithktorclientcompose.data.remote.models.Catalog
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface PostsService {

    suspend fun getPosts(): List<PostResponse>
    suspend fun createPost(postRequest: PostRequest): PostResponse?
    suspend fun getCatalog():  List<Catalog>
    suspend fun sendCode(userEmail: String): String?

    companion object {
        fun create(): PostsService {
            return PostServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }

}