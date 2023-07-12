package com.metehanbolat.httprequestwithktorclientcompose.data.remote

import com.metehanbolat.httprequestwithktorclientcompose.data.remote.dto.PostRequest
import com.metehanbolat.httprequestwithktorclientcompose.data.remote.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface PostsService {

    suspend fun getPosts(): List<PostResponse>
    suspend fun createPost(postRequest: PostRequest): PostResponse?

    // Buraya implementasyondan sonra geleceksin.
    companion object {
        fun create(): PostsService {
            return PostServiceImpl(
                /** Здесь мы понимаем причину интеграции библиотеки Android.*/
                client = HttpClient(Android) {
                    /** В этой области мы можем получить доступ к функциям серверной части Ktor.
                     * Например, в качестве примеров можно привести ведение журнала, сериализацию Json и аутентификацию.*/
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    /** Мы указываем, что с помощью этой функции мы можем сериализовать или десериализовать данные Json. */
                    install(JsonFeature) {
                         /** Но нам нужно конкретно сказать, с каким плагином или библиотекой мы будем это делать, и мы можем указать это таким образом.
                          * Здесь json можно использовать в моши, но мы используем KotlinxSerializer. */
                        serializer = KotlinxSerializer()
                    }
                    /** С этим покончено, давайте сделаем запрос в Main Activity.*/
                }
            )
        }
    }

}