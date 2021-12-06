package com.edib.devbyteview.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edib.devbyteview.R
import com.edib.devbyteview.databinding.FragmentDevByteBinding
import com.edib.devbyteview.domain.DevByteVideo
import com.edib.devbyteview.viewmodels.DevByteViewModel


class DevByteFragment : Fragment() {

    private lateinit var _binding : FragmentDevByteBinding

    private val binding get() = _binding

    /**
     * One way to delay creation of the viewModel until an appropriate lifecycle method is to use
     * lazy. This requires that viewModel not be referenced before onActivityCreated, which we
     * do in this Fragment.
     */

    private val model : DevByteViewModel by lazy {
        val activity = requireNotNull(this.activity){
            "You can only access the viewModel after onActivityCreated()"
        }

        ViewModelProvider(this,
            DevByteViewModel.Factory(activity.application))
            .get(DevByteViewModel::class.java)
    }

    /**
     * RecyclerView Adapter for converting a list of Video to cards.
     */

    private  var  modelAdapter : DevByteAdapter? = null


    /**
     * Called immediately after onCreateView() has returned, and fragment's
     * view hierarchy has been created. It can be used to do final
     * initialization once these pieces are in place, such as retrieving
     * views or restoring state.
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model.playlist.observe(viewLifecycleOwner, Observer<List<DevByteVideo>> { video ->
            video?.apply {
                modelAdapter?.videos =  video
            }
        })

    }


    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return Return the View for the fragment's UI.
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_dev_byte, container, false)

        _binding = FragmentDevByteBinding.inflate(inflater, container, false)


        // Set the lifecycleOwner so DataBinding can observe LiveData
        binding.setLifecycleOwner(viewLifecycleOwner)


        binding.videoModel = model

        modelAdapter = DevByteAdapter(VideoClick{

            // When a video is clicked this block or lambda will be called by DevByteAdapter


            // context is not around, we can safely discard this click since the Fragment is no
            // longer on the screen
            val packageMessager = context?.packageManager ?: return@VideoClick

            // Try to generate a direct intent to the YouTube app
            var intent = Intent(Intent.ACTION_VIEW, it.launchUri)
            if (intent.resolveActivity(packageMessager) == null){

                // YouTube app isn't found, use the web url

                intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
            }

            startActivity(intent)
        })

        // setting the recyclerView

        binding.root.findViewById<RecyclerView>(R.id.recycle).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = modelAdapter
        }



        // Observer for the network error.
        model.eventNetWorkError.observe(viewLifecycleOwner, Observer<Boolean> {isNetworkError ->
            if(isNetworkError) onNetworkError()
        })



        return binding.root
    }



    /**
     * Method for displaying a Toast error message for network errors.
     */
    private fun onNetworkError(){
        if(!model.isNetworkErrorShow.value!!){
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            model.onNetworkErrorShow()
        }
    }

    /**
     * Helper method to generate YouTube app links
     */
    private val DevByteVideo.launchUri : Uri
    get() {
        val httpUri = Uri.parse(url)
        return Uri.parse("vnd.youtube:" + httpUri.getQueryParameter("v"))

    }


}