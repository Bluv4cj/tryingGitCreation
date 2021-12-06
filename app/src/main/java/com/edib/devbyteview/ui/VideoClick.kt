package com.edib.devbyteview.ui

import com.edib.devbyteview.domain.DevByteVideo

/**
 * Click listener for Videos. By giving the block a name it helps a reader understand what it does.
 *
 */

class VideoClick (val block : (DevByteVideo) -> Unit) {
    /**
     * Called when a video is clicked
     *
     * @param video the video that was clicked
     */

    fun onClick(video: DevByteVideo) = block(video)
}