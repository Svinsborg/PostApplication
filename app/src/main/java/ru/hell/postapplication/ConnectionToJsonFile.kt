package ru.hell.postapplication

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
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }



    suspend fun getPost():List<Post> {
        return withContext(Dispatchers.IO) {
            try {
                client.get {
                    url(URL3)
                }
            } catch (err: RedirectResponseException) {
                println("->> Error redirect: ${err.response.status.description}")
                emptyList()
            } catch (err: ClientRequestException) {
                println("->> Error client: ${err.response.status.description}")
                emptyList()
            } catch (err: ServerResponseException) {
                println("->> Error server: ${err.response.status.description}")
                emptyList()
            } catch (err: Exception) {
                println("->> Error others: ${err.message}")
                emptyList()
            }
        }
    }
}