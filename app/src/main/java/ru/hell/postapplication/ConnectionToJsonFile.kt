package ru.hell.postapplication

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.math.roundToInt

const val URL4 = "http://viktorov.ml:8008/api/v1/post"    // ===>>> внешняя ссылка
const val URL5 = "http://192.168.1.75:8008/api/v1/post"  // ===>>> для локальной отладки

sealed interface DownloadResult<out T> {
    data class Success<T>(val value: T) : DownloadResult<T>
    data class Error(val cause: Exception) : DownloadResult<Nothing>
    data class Progress(val percent: Int) : DownloadResult<Nothing>
}

typealias PostsResult = DownloadResult<List<Post>>

class ConnectionToJsonFile (engine: HttpClientEngine) {

    private val clientHttp = HttpClient(engine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
            acceptContentTypes = listOf(
                ContentType.Text.Plain,
                ContentType.Application.Json
            )
        }
        install(UserAgent) {
            agent = "Post App Client"
        }
        install(Logging) {
            Log.d("->> HTTP Client: ", Logger.DEFAULT.toString())
            Log.d("->> HTTP Client: ", LogLevel.ALL.toString())
        }
    }

    fun getPost(): Flow<PostsResult> =
        flow {
            try {
                val response = clientHttp.request<HttpResponse> {
                    url(URL5)
                    method = HttpMethod.Get
                }
                val data = ByteArray((response.contentLength() ?: 0L).toInt())
                var offset = 0
                var progress = 0
                do {
                    val currentRead =
                        response.content.readAvailable(data, offset, DEFAULT_BUFFER_SIZE / 10)
                    offset += currentRead
                    val currentProgress = (offset * 100f / data.size).roundToInt()
                    if (currentProgress != progress) {
                        progress = currentProgress
                        emit(DownloadResult.Progress(progress))
                    }
                } while (currentRead > 0)
                emit(DownloadResult.Success(Json.decodeFromString(String(data))))
            } catch (e: Exception) {
                emit(DownloadResult.Error(e))
            }
        }
}
