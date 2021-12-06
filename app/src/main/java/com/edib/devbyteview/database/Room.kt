package com.edib.devbyteview.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

/*
@Dao
interface VideoDao {

    // the helper method to insert the video into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(videos : List<DatabaseVideo>)


    // the helper method to get video from the database
    @Query("select * from database_video")
    fun getVideo() : LiveData<List<DatabaseVideo>>

}

@Database(version =1, entities = [DatabaseVideo::class], exportSchema = false)
abstract class VideosDatabase : RoomDatabase(){
    abstract val videoDao : VideoDao

}

private lateinit var INSTANCE : VideosDatabase

fun getDatab(context : Context) : VideosDatabase{
    synchronized(VideosDatabase::class.java){
        if (!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                VideosDatabase::class.java,
                "video"
            ).build()
        }
    }
    return INSTANCE
}


 */
