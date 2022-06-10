package com.annti.restaurantapp.presentation.hits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.annti.restaurantapp.R
import com.annti.restaurantapp.databinding.FragmentHitsBinding
import com.annti.restaurantapp.presentation.hits.adapter.HitsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HitsFragment : Fragment(R.layout.fragment_hits) {

    private val viewModel: HitsViewModel by viewModel()
    private lateinit var binding: FragmentHitsBinding
    private val restaurantAdapter: HitsAdapter by lazy { HitsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHitsBinding.inflate(layoutInflater, container, false)
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
                binding.listHits.visibility = View.GONE
            } else {
                binding.frameAlarmError.visibility = View.GONE
                binding.listHits.visibility = View.VISIBLE
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else binding.progressBar.visibility = View.GONE
        }
        setRecyclerViewListHits()
        getHitsList()
    }

    private fun setRecyclerViewListHits() {
        with(binding.listHits) {
            adapter = restaurantAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(false)
        }
    }

    private fun getHitsList() {
        viewModel.getHitsList()
        viewModel.hitsList.observe(viewLifecycleOwner) {
            restaurantAdapter.items = it
        }
    }
}