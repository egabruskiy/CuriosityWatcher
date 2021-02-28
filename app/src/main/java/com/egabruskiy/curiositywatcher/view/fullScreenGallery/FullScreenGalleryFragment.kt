package com.egabruskiy.curiositywatcher.view.fullScreenGallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.egabruskiy.curiositywatcher.R
import com.egabruskiy.curiositywatcher.data.model.ResourceResult
import com.egabruskiy.curiositywatcher.databinding.FullScreenGalleryFragmentBinding
import com.egabruskiy.curiositywatcher.util.POS_ARG
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import kotlinx.android.synthetic.main.full_screen_gallery_fragment.*
import kotlinx.android.synthetic.main.main_gallery_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FullScreenGalleryFragment : Fragment() {

    private val viewBinding by viewBinding(FullScreenGalleryFragmentBinding::bind)

    private val viewModel : FullScreenGalleryViewModel by viewModel()

    private val fullScreenGalleryAdapter = FullScreenGalleryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.full_screen_gallery_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.position = it.getInt(POS_ARG)
        }


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.viewPager.apply {
            adapter = fullScreenGalleryAdapter
        }
        setImagesObserver()
    }


    private fun setImagesObserver() {

        viewModel.offlineImages.observe(viewLifecycleOwner) { result ->

            when (result) {
                is ResourceResult.Loading -> {
                    viewBinding.fullScreenContentProgressbar.show()
                    viewBinding.fullScreenEmptyTextview.visibility = View.GONE
                }
                is ResourceResult.Success -> {
                    viewBinding.fullScreenContentProgressbar.hide()
                    viewBinding.fullScreenEmptyTextview.visibility = View.GONE
                    fullScreenGalleryAdapter.setList((result.data))
                    viewBinding.viewPager.setCurrentItem(viewModel.position,false)
                }

                is ResourceResult.Empty -> {
                    viewBinding.fullScreenContentProgressbar.hide()
                    viewBinding.fullScreenEmptyTextview.visibility = View.VISIBLE
                }

                is ResourceResult.Error -> {
                    viewBinding.fullScreenContentProgressbar.hide()
                    viewBinding.fullScreenEmptyTextview.visibility = View.VISIBLE

                }
            }
        }
    }
}