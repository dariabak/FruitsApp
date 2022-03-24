package com.daria.bak.fruitsapp.fruitList.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.android.volley.toolbox.Volley
import com.daria.bak.fruitsapp.R
import com.daria.bak.fruitsapp.databinding.FruitListLayoutBinding
import com.daria.bak.fruitsapp.fruitList.business.Fruit
import com.daria.bak.fruitsapp.fruitList.business.FruitListViewModel
import com.daria.bak.fruitsapp.fruitList.business.FruitListViewModelFactory
import com.daria.bak.fruitsapp.fruitList.data.FruitListRepo
import com.daria.bak.fruitsapp.fruitList.data.FruitListRepoInterface
import com.daria.bak.fruitsapp.fruitList.data.FruitListService
import com.daria.bak.fruitsapp.fruitList.data.FruitListServiceInterface


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

        linearLayoutManager = LinearLayoutManager(requireActivity())
        binding.fruitList.layoutManager = linearLayoutManager

        adapter = FruitListAdapter(arrayListOf())
        binding.fruitList.adapter = adapter
//        adapter = FruitListAdapter(viewModel.fruitList,this)
//        binding.fruitList.adapter = adapter
//        adapter.notifyDataSetChanged()
        adapter.setTapHandler { fruit ->
            Log.i("FruitListFragment", "Listener works")
            var navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            var action =
                FruitListFragmentDirections.actionFruitListFragmentToFruitFragment()
            action.type = fruit.type
            action.price = fruit.price.toString()
            action.weight = fruit.weight.toString()
            navController.navigate(action)
        }
//        viewModel.onFruitDownloadedListener = { fruitList ->
//            adapter = FruitListAdapter(viewModel.fruitList,this)
//            binding.fruitList.adapter = adapter
//
//            adapter.setTapHandler { fruit ->
//                Log.i("FruitListFragment", "Listener works")
//                var navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
//                var action =
//                    FruitListFragmentDirections.actionFruitListFragmentToFruitFragment()
//                action.type = fruit.type
//                action.price = fruit.price.toString()
//                action.weight = fruit.weight.toString()
//                navController.navigate(action)
//            }
//            adapter.notifyDataSetChanged()
//        }

        binding.pullRefresh.setOnRefreshListener(OnRefreshListener {
            viewModel.refreshData()
            binding.pullRefresh.setRefreshing(false)
        })
        viewModel.fruitListState.observe(viewLifecycleOwner) {
            when(it) {
                is FruitListState.Success -> {
                    adapter.dataSet = it.fruitList

                    binding.successLayout.visibility = View.VISIBLE
                    binding.errorLayout.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    adapter.notifyDataSetChanged()

                }
                is FruitListState.Loading -> {
                    binding.successLayout.visibility = View.GONE
                    binding.errorLayout.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                is FruitListState.Error -> {
                    binding.successLayout.visibility = View.GONE
                    binding.errorLayout.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    binding.errorText.text = it.message
                }
            }
        }
        return binding.root
    }
}