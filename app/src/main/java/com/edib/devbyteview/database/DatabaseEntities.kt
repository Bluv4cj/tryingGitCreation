package com.edib.devbyteview.database


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.edib.devbyteview.domain.DevByteVideo


/*
@Entity(tableName = "database_video")
data class DatabaseVideo constructor(
        val title : String,
    val description : String,
    val url : String,
    val updated : String,
    val thumbnail : String
        ) {
    @PrimaryKey
    var id : Int = 0
}


// map the databaseVideo to a domain entities

fun List<DatabaseVideo>.asDomainModel(): List<DevByteVideo> {
    return map {
        DevByteVideo(
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail

        )
    }
}

 */

