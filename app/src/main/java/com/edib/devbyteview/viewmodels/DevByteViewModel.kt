package com.edib.devbyteview.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.edib.devbyteview.database.getDatab
import com.edib.devbyteview.domain.DevByteVideo
import com.edib.devbyteview.network.DevByteNetwork
import com.edib.devbyteview.network.asDomainModel
import com.edib.devbyteview.repository.VideosRepository
import kotlinx.coroutines.launch
import java.io.IOException

class DevByteViewModel(application: Application): AndroidViewModel(application) {
/*
    // video repository
   // private val videoRepository = VideosRepository(getDatab(application))


    // to get the playlist
   // val playlist = videoRepository.videos

    private val _playlist = MutableLiveData<List<DevByteVideo>>()

    val playlist: LiveData<List<DevByteVideo>>
        get() = _playlist


    // to triggered network error
    private var _eventNetworkError = MutableLiveData<Boolean>()

    val eventNetWorkError: LiveData<Boolean> get() = _eventNetworkError

    private var _isNetworkErrorShow = MutableLiveData<Boolean>()

    val isNetworkErrorShow get() = _isNetworkErrorShow

    init {
        getDataFromRepository()
    }

    private fun getDataFromRepository() {
        viewModelScope.launch {
            try {
                videoRepository.refreshVideos()
                _eventNetworkError.value = false
                _isNetworkErrorShow.value = false
            } catch (e: IOException) {

                if (playlist.value.isNullOrEmpty()) _eventNetworkError.value = true
            }
        }
    }

    // to reset the network  error

    fun onNetworkErrorShow() {
        _isNetworkErrorShow.value = true
    }*/



    private val _playlist = MutableLiveData<List<DevByteVideo>>()

    val playlist: LiveData<List<DevByteVideo>>
        get() = _playlist

    /**
     * Event triggered for network error. This is private to avoid exposing a
     * way to set this value to observers.
     */
    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    /**
     * Event triggered for network error. Views should use this to get access
     * to the data.
     */
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    /**
     * Flag to display the error message. This is private to avoid exposing a
     * way to set this value to observers.
     */
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    /**
     * Flag to display the error message. Views should use this to get access
     * to the data.
     */
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {
        refreshDataFromNetwork()
    }

    /**
     * Refresh data from network and pass it via LiveData. Use a coroutine launch to get to
     * background thread.
     */
    private fun refreshDataFromNetwork() = viewModelScope.launch {

        try {
            val playlist = DevByteNetwork.devBytes.getPlaylist()
            _playlist.postValue(playlist.asDomainModel())

            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false

        } catch (networkError: IOException) {
            // Show a Toast error message and hide the progress bar.
            _eventNetworkError.value = true
        }
    }

    /**
     * Resets the network error flag.
     */
    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }















    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DevByteViewModel::class.java)) {
                return DevByteViewModel(app) as T
            }
            throw IllegalArgumentException("unknown viewModel")
        }

    }
}