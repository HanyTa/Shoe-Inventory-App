package com.udacity.shoestore.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.ShoesListViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [ShoeListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeListFragment : Fragment() {

    private var shoesListBinding: FragmentShoeListBinding? = null
    private val shoeListViewModel: ShoesListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentShoeListBinding.inflate(
            inflater,
            container,
            false
        )
        shoesListBinding = binding

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }



        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shoesListBinding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = shoeListViewModel

            shoeListViewModel.addShoeNavigated.observe(viewLifecycleOwner, Observer {
                if (it) {
                    navigateToAddShoe()
                    shoeListViewModel.shoeAddedComplete()
                }
            })
            shoeListViewModel.shoesList.observe(viewLifecycleOwner, Observer {
                invalidateAll()
                buildShoesList(it)
            })
        }
    }

    @SuppressLint("InflateParams", "SetTextI18n")
    private fun buildShoesList(list: List<Shoe>) {
        val inflater = ContextCompat.getSystemService(
            requireContext(),
            LayoutInflater::class.java
        ) as LayoutInflater
        for (shoe in list) {
            val rowView: View = inflater.inflate(R.layout.shoe_item, null)
            with(rowView) {
                val name = findViewById<TextView>(R.id.name)
                val size = findViewById<TextView>(R.id.size)
                val company = findViewById<TextView>(R.id.company)
                val description = findViewById<TextView>(R.id.description)
                name.text = shoe.name
                size.text = shoe.size.toInt().toString()
                company.text = "Company: ${shoe.company}"
                description.text = shoe.description

                if (shoe.size != 0.0)
                    shoesListBinding?.shoesContainer?.addView(rowView)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                NavHostFragment.findNavController(this).navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment2())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToAddShoe() {
        findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToDetailsFragment())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        shoesListBinding = null
    }
}