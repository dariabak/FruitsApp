package com.daria.bak.fruitsapp.fruitList.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.Volley
import com.daria.bak.fruitsapp.R
import com.daria.bak.fruitsapp.business.FruitListViewModel
import com.daria.bak.fruitsapp.business.FruitListViewModelFactory
import com.daria.bak.fruitsapp.data.FruitListRepo
import com.daria.bak.fruitsapp.data.FruitListRepoInterface
import com.daria.bak.fruitsapp.data.FruitListService
import com.daria.bak.fruitsapp.data.FruitListServiceInterface
import com.daria.bak.fruitsapp.databinding.FruitListLayoutBinding

class FruitListFragment: Fragment() {
    private lateinit var binding: FruitListLayoutBinding
    private lateinit var viewModel: FruitListViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: FruitListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fruit_list_layout,
            container,
            false
        )
        binding.lifecycleOwner = this
        val queue = Volley.newRequestQueue(context)
        val service: FruitListServiceInterface = FruitListService(queue)
        val repo: FruitListRepoInterface = FruitListRepo(service)
        viewModel = ViewModelProviders.of(this, FruitListViewModelFactory(repo)).get(
            FruitListViewModel::class.java)

//        viewModel.getFruitList()

        linearLayoutManager = LinearLayoutManager(requireActivity())
        binding.fruitList.layoutManager = linearLayoutManager
        viewModel.onFruitDownloadedListener = { fruitList ->
            adapter = FruitListAdapter(viewModel.fruitList,this)
            binding.fruitList.adapter = adapter
        }

        return binding.root
    }
}