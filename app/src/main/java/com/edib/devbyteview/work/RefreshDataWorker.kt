package com.edib.devbyteview.work
/*
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.edib.devbyteview.database.getDatab
import com.edib.devbyteview.repository.VideosRepository
import retrofit2.HttpException
import timber.log.Timber

class RefreshDataWorker(appContext: Context, params : WorkerParameters
) : CoroutineWorker(appContext, params) {

    //given a specific name to a worker which will be in workerManager
    companion object{
        const val WORK_NAME = "com.edib.devbyteview.work.RefresherDataWork"
    }
    override suspend fun doWork(): Result {

        val database = getDatab(applicationContext)
        val repository = VideosRepository(database)

        try {
            repository.refreshVideos()
            Timber.d("Work request for sync is run")
        }catch (e:HttpException){
            return Result.retry()
        }

       return Result.success()
    }
}

 */