package ru.hell.postapplication

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.client.features.logging.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext



const val URL0 = "https://raw.githubusercontent.com/Svinsborg/jsonfiles/main/autocreate.json"
const val URL1 = "https://viktorov.ml/res/doc/AutoCreate.json"
const val URL2 = "https://95.165.135.238/res/doc/AutoCreate.json"
const val URL3 = "http://viktorov.ml:8008/post"
const val URL4 = "http://viktorov.ml:8008/sql"

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

    suspend fun getPost():List<Post> {
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
                Log.e("->> Error others: ", err.message)
                emptyList()
            }
        }
    }

    fun DatdSize(i: Long, j: Long) {
        val bySend = i
        val conSize = j
        println("==== >>>>> Received $bySend bytes from $conSize !!!!!!!!")
    }
}
