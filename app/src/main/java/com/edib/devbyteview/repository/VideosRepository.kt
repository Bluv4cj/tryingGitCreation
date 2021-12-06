package com.edib.devbyteview.repository
/*
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.edib.devbyteview.database.VideosDatabase
import com.edib.devbyteview.database.asDomainModel
import com.edib.devbyteview.domain.DevByteVideo
import com.edib.devbyteview.network.DevByteNetwork
import com.edib.devbyteview.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class VideosRepository(private val database : VideosDatabase)  {

    /*
    val videos : LiveData<List<DevByteVideo>> = Transformations.map(database.videoDao.getVideo()){
        it.asDomainModel()
    }

     */
    val videos: LiveData<List<DevByteVideo>> = Transformations.map(database.videoDao.getVideo()) {
        it.asDomainModel()
    }

    /*
    suspend fun refreshVideos(){
        withContext(Dispatchers.IO){
            Timber.d("refresh video is called")
            val playlist = DevByteNetwork.devbyte.getPlaylist()
            database.videoDao.insertAll(playlist.asDatabaseModel())

        }
    }

     */

    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            Timber.d("refresh videos is called");
            val playlist = DevByteNetwork.devBytes.getPlaylist()
            database.videoDao.insertAll(playlist.asDatabaseModel())
        }
    }
}

 */

