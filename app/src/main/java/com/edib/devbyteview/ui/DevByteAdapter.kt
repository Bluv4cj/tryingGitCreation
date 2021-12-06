package com.edib.devbyteview.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.edib.devbyteview.R
import com.edib.devbyteview.databinding.DevByteItemBinding
import com.edib.devbyteview.domain.DevByteVideo
import java.util.zip.Inflater

/**
 * RecyclerView Adapter for setting up data binding on the items in the list.
 */

class DevByteAdapter(val callBack : VideoClick) : RecyclerView.Adapter<DevByteAdapter.DevByteHolder>() {

    /**
    * The videos that our Adapter will show
    */
    var videos : List<DevByteVideo> = emptyList()
    set(value) {

        field = value
        // For an extra challenge, update this to use the paging library.

        // Notify any registered observers that the data set has changed. This will cause every
        // element in our RecyclerView to be invalidated.
        notifyDataSetChanged()
    }

    class DevByteHolder(val binding : DevByteItemBinding): RecyclerView.ViewHolder(binding.root){

        companion object{

            @LayoutRes
            val LAYOUT = R.layout.dev_byte_item

        }

    }

    /**
    * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
    * an item.
    */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevByteHolder {
        val view = DevByteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DevByteHolder(view)
    }

    override fun onBindViewHolder(holder: DevByteHolder, position: Int) {

        holder.binding.also{
            it.video = videos[position]
            it.videoCallback = callBack
        }

    }

    override fun getItemCount(): Int {
      return  videos.size
    }
}