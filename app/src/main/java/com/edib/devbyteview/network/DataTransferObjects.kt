package com.edib.devbyteview.network



import com.edib.devbyteview.database.DatabaseVideo
import com.edib.devbyteview.domain.DevByteVideo
import com.squareup.moshi.JsonClass

// the video holder list

@JsonClass(generateAdapter = true)
data class NetworkVideoContainer (val videos : List<NetworkVideo>)


@JsonClass(generateAdapter = true)
data class NetworkVideo(
    val title : String,
    val description : String,
    val url : String,
    val updated :  String,
    val thumbnail : String,
    val clodedCaptions: String?
)

/*
// converting network results to database object

fun NetworkVideoContainer.asDatabaseModel(): List<DatabaseVideo>{
    return videos.map{
        DatabaseVideo (
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail

        )

    }

}

 */



/**
 * Convert Network results to database objects
 */
fun NetworkVideoContainer.asDomainModel(): List<DevByteVideo>{
    return videos.map {
        DevByteVideo(
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail

        )
    }
}