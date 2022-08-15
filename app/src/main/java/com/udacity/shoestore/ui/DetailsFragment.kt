package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentDetailsBinding
import com.udacity.shoestore.viewmodels.ShoesListViewModel


class DetailsFragment : Fragment() {

    private lateinit var detailsBinding: FragmentDetailsBinding
    private val detailsViewModel: ShoesListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        detailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)


        return detailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsBinding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = detailsViewModel

            detailsViewModel.shoeListNavigated.observe(viewLifecycleOwner, Observer {
                if (it) {
                    findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToShoeListFragment())
                    invalidateAll()
                    detailsViewModel.shoeListComplete()
                }
            })
        }
    }
}