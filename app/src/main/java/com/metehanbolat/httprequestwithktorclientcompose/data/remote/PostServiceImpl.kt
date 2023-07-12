package com.metehanbolat.httprequestwithktorclientcompose.data.remote

import com.metehanbolat.httprequestwithktorclientcompose.data.remote.dto.PostRequest
import com.metehanbolat.httprequestwithktorclientcompose.data.remote.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

class PostServiceImpl(private val client: HttpClient): PostsService {
    override suspend fun getPosts(): List<PostResponse> {
        return try {
            client.get { url(HttpRoutes.POSTS) }
        } catch (e: RedirectResponseException) {
6            /** Используется для возврата ошибок, связанных с перенаправлением. */
            /** Например, если нужно использовать прокси, он вернет свою ошибку или если есть
             * миграция, он вернет ее. */
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            // 4xx - responses
            /** Мы используем его для возврата ошибок клиента. Если сделан неправильный запрос или
             * запрошен платеж или используется неавторизованный метод */
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            // 5xx - responses
            /** Мы использовали его для возврата ошибок сервера. Например, если сервер, на
             * который мы отправляем запрос, не отвечает, будет возвращен один из 500 кодов ошибок. */
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            /** Мы также можем использовать обычный класс Exception для возвращаемых строк. */
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
            // 3xx - responses
            /** Он используется для возврата любых ошибок, связанных с перенаправлением. */
            /** Например, если нужно использовать прокси, он вернет свою ошибку или если есть
             * миграция, он вернет ее. */
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            // 4xx - responses
            /** Мы используем его для возврата ошибок клиента. Если сделан неправильный запрос или
             * запрошен платеж или используется неавторизованный метод */
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            // 5xx - responses
            /** Мы использовали его для возврата ошибок сервера. Например, если сервер, на который
             * мы отправляем запрос, не отвечает, будет возвращен один из 500 кодов ошибок. */
            println("Error: ${e.response.status.description}")
            null
        } catch (e: Exception) {
            /** Мы также можем использовать обычный класс Exception для возвращаемых строк. */
            println("Error: ${e.message}")
            null
        }
    }

    /** Поскольку мы не используем внедрение зависимостей, нам нужно создать экземпляр значения,
     * которое мы даем в качестве конструктора.
     * Мы можем сделать это в интерфейсе. */
}