package com.example.plantae.ui.plant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plantae.R
import com.example.plantae.data.entity.Plants
import com.example.plantae.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.plant_fragment.*
import timber.log.Timber

@AndroidEntryPoint
class PlantFragment : Fragment(), PlantItemListener {
    private val TAG = "PlantFragment"


    private val viewModel by viewModels<PlantViewModel>()
    private lateinit var plantAdapter: PlantAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.plant_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        fetchResponse()
        viewModel.response.observe(viewLifecycleOwner) { res ->
            when (res) {
                is NetworkResult.Success -> {
                    progress_bar.visibility = View.GONE
//                    plant = res.data?.plants!!
                    plantAdapter.setItems(res.data?.plants as ArrayList<Plants>)
                    Timber.d("plants ${res.data}")
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), res.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    progress_bar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupRecyclerView() {
        plant_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            plantAdapter = PlantAdapter(this@PlantFragment)
            adapter = plantAdapter
        }
    }

    override fun onClickedPlant(plant: Plants) {
        Timber.d("onClickedPlant: $plant")
    }

    private fun fetchResponse() {
        viewModel.fetchPlants()
    }

}