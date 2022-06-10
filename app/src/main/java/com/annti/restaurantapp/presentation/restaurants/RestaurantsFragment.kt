package com.annti.restaurantapp.presentation.restaurants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.annti.restaurantapp.R
import com.annti.restaurantapp.databinding.FragmentRestaurantsBinding
import com.annti.restaurantapp.presentation.restaurants.adapter.RestaurantsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class RestaurantsFragment : Fragment(R.layout.fragment_restaurants) {

    private val viewModel: RestaurantsViewModel by viewModel()
    private lateinit var binding: FragmentRestaurantsBinding
    private val restaurantAdapter: RestaurantsAdapter by lazy { RestaurantsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRestaurantsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.error.observe(viewLifecycleOwner) {
            binding.txtError.text = it
        }
        viewModel.errorView.observe(viewLifecycleOwner) { error ->
            if (error) {
                binding.frameAlarmError.visibility = View.VISIBLE
                binding.listRestaurant.visibility = View.GONE
            } else {
                binding.frameAlarmError.visibility = View.GONE
                binding.listRestaurant.visibility = View.VISIBLE
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else binding.progressBar.visibility = View.GONE
        }
        setRecyclerViewListRestaurant()
        getRestaurantList()
    }

    private fun setRecyclerViewListRestaurant() {
        with(binding.listRestaurant) {
            adapter = restaurantAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(false)
        }
    }

    private fun getRestaurantList() {
        viewModel.getRestaurantsList()
        viewModel.restaurantList.observe(viewLifecycleOwner) {
            restaurantAdapter.items = it
        }
    }

}