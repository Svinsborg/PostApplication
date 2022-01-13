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
import java.io.IOException
import kotlin.math.roundToInt



const val URL0 = "https://raw.githubusercontent.com/Svinsborg/jsonfiles/main/autocreate.json"
const val URL1 = "https://viktorov.ml/res/doc/AutoCreate.json"
const val URL2 = "https://95.165.135.238/res/doc/AutoCreate.json"
const val URL3 = "http://viktorov.ml:8008/post"
const val URL4 = "http://viktorov.ml:8008/sql"

sealed interface DownloadResult<out T> {
    data class Success<T>(val value: T) : DownloadResult<T>
    data class Error(val cause: Exception) : DownloadResult<Nothing>
    data class Progress(val percent: Int) : DownloadResult<Nothing>
}

typealias PostsResult = DownloadResult<List<Post>>

class ConnectionToJsonFile (engine: HttpClientEngine) {

    private val client = HttpClient(engine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
            acceptContentTypes = listOf(
                ContentType.Text.Plain,
                ContentType.Application.Json
            )
        }
        install(Logging) {
            //logger = Logger.DEFAULT
            //level = LogLevel.ALL
            Log.d("->> HTTP Client: ", Logger.DEFAULT.toString())
            Log.d("->> HTTP Client: ", LogLevel.ALL.toString())
        }
    }

/*    suspend fun getPost():List<Post> {
        return withContext(Dispatchers.IO) {
            try {
                client.get {
                    url(URL3)
                    onDownload { bytesSentTotal, contentLength ->  println("==== >>>>> Received $bytesSentTotal bytes from $contentLength !!!!!!!!")  }
                }
            } catch (err: RedirectResponseException) {
                Log.e("->> Error redirect: ", err.response.status.description)
                emptyList()
            } catch (err: ClientRequestException) {
                Log.e("->> Error client: ", err.response.status.description)
                emptyList()
            } catch (err: ServerResponseException) {
                Log.e("->> Error server: ", err.response.status.description)
                emptyList()
            } catch (err: Exception) {
                Log.e("->> Error others: ", err.message.orEmpty())
                emptyList()
            }
        }
    }*/

    fun getPost(): Flow<PostsResult> =
        flow {
            try {
                val response = client.request<HttpResponse> {
                    url(URL3)
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
            } catch (e: IOException) {
                emit(DownloadResult.Error(e))
            }  catch (e: IOException) {
                emit(DownloadResult.Error(e))
            } catch (err: RedirectResponseException) {
                Log.e("->> Error redirect: ", err.response.status.description)
            } catch (err: ClientRequestException) {
                Log.e("->> Error client: ", err.response.status.description)
            } catch (err: ServerResponseException) {
                Log.e("->> Error server: ", err.response.status.description)
            } catch (err: Exception) {
                Log.e("->> Error others: ", err.message.orEmpty())
            }
        }

/*    fun DatdSize(i: Long, j: Long) {
        val bySend = i
        val conSize = j
        println("==== >>>>> Received $bySend bytes from $conSize !!!!!!!!")
    }*/
}
