package com.egabruskiy.curiositywatcher.view.maingallery

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.egabruskiy.curiositywatcher.R
import com.egabruskiy.curiositywatcher.data.model.CuriosityImage
import com.egabruskiy.curiositywatcher.data.model.ResourceResult
import com.egabruskiy.curiositywatcher.databinding.MainGalleryFragmentBinding
import com.egabruskiy.curiositywatcher.util.ConnectionLiveData
import com.egabruskiy.curiositywatcher.util.POS_ARG
import com.egabruskiy.curiositywatcher.util.RecyclerViewListener
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainGalleryFragment : Fragment() {
     lateinit var connectionLiveData: ConnectionLiveData

    private val viewModel : MainGalleryViewModel by viewModel()
    private val mainGalleryAdapter = MainGalleryAdapter()
    private val viewBinding by viewBinding(MainGalleryFragmentBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.main_gallery_fragment, container, false)
    }


    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        connectionLiveData = ConnectionLiveData(requireActivity())


        connectionLiveData.observe(viewLifecycleOwner) {
            viewModel.changeNetworkStatus(it)
        }

        setImageAdapter()

        setImagesObserver()


        viewModel.networkStatus.observe(viewLifecycleOwner, Observer {
            if (it) viewModel.updateImageList()
        })

        viewBinding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.updateImageList()
            viewBinding.swipeRefreshLayout.isRefreshing = false
        }
    }



    private fun setImagesObserver() {
        viewModel.offlineImages.observe(viewLifecycleOwner) {

            when (it) {
                is ResourceResult.Loading -> {
                    viewBinding.contentProgressbar.show()
                    viewBinding.emptyTextview.visibility = View.GONE
                }
                is ResourceResult.Success -> {
                    viewBinding.contentProgressbar.hide()
                    viewBinding.emptyTextview.visibility = View.GONE
                    mainGalleryAdapter.setList((it.data))
                }

                is ResourceResult.Empty -> {
                    viewBinding.contentProgressbar.hide()
                    viewBinding.emptyTextview.visibility = View.VISIBLE

                }

                is ResourceResult.Error -> {
                    viewBinding.contentProgressbar.hide()
                    viewBinding.emptyTextview.visibility = View.VISIBLE
                }
            }
        }
    }



    private fun setImageAdapter(){
        viewBinding.mainGalleryRv.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = mainGalleryAdapter

        }
        mainGalleryAdapter.setClickListener(object :
                RecyclerViewListener {
            override fun onClick(position: Int) {
                val bundle = Bundle()
                bundle.putInt(POS_ARG, position)
                findNavController().navigate(R.id.main_gallery_fragment_to_full_screen_fragment, bundle)

            }

            override fun onLongClick(image: CuriosityImage) {
                AlertDialog.Builder(activity).apply {
                    setTitle("Delete Image?")
                    setPositiveButton("Yes") { _: DialogInterface, _: Int ->
                        image.deleted = 1
                        viewModel.updateImage(image)
//                        viewModel.updateRepository()
                        viewModel.updateImageList()
                    }
                    setCancelable(true)
                    show()

                }


            }
        })
    }

}