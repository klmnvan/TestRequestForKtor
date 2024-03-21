package com.metehanbolat.httprequestwithktorclientcompose.data.remote

import android.util.Log
import com.metehanbolat.httprequestwithktorclientcompose.data.remote.dto.PostRequest
import com.metehanbolat.httprequestwithktorclientcompose.data.remote.dto.PostResponse
import com.metehanbolat.httprequestwithktorclientcompose.data.remote.models.Catalog
import io.ktor.client.*
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.*
import io.ktor.http.*

class PostServiceImpl(private val client: HttpClient) : PostsService {

    override suspend fun getPosts(): List<PostResponse> {
        return try {
            client.get { url(HttpRoutes.POSTS) }
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun createPost(postRequest: PostRequest): PostResponse? {
        return try {
            client.post<PostResponse> {
                url(HttpRoutes.POSTS)
                contentType(ContentType.Application.Json)
                body = postRequest
            }
        } catch (e: RedirectResponseException) {
            Log.d("Error 3xx", e.response.status.description)
            null
        } catch (e: ClientRequestException) {
            Log.d("Error 4xx", e.response.status.description)
            null
        } catch (e: ServerResponseException) {
            Log.d("Error 5xx", e.response.status.description)
            null
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }

    override suspend fun getCatalog(): List<Catalog> {
        return try {
            client.get { url(HttpRoutes.CATALOG) }
        } catch (e: RedirectResponseException) {
            Log.d("Error перенаправление", e.response.status.description)
            emptyList()
        } catch (e: ClientRequestException) {
            Log.d("Error 4xx", e.response.status.description)
            emptyList()
        } catch (e: ServerResponseException) {
            Log.d("Error 5xx", e.response.status.description)
            emptyList()
        } catch (e: Exception) {
            Log.d("Error", e.message.toString())
            emptyList()
        }
    }

    override suspend fun sendCode(userEmail: String): String? {
        return try {
            client.post {
                url(HttpRoutes.SENDCODE)
                contentType(ContentType.Application.Json)
                headers {
                    append("User-email", userEmail)
                }
            }
        } catch (e: RedirectResponseException) {
            Log.d("Error 3xx", e.response.status.description)
            null
        } catch (e: ClientRequestException) {
            Log.d("Error 4xx", e.response.status.description)
            null
        } catch (e: ServerResponseException) {
            Log.d("Error 5xx", e.response.status.description)
            null
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }
    
}