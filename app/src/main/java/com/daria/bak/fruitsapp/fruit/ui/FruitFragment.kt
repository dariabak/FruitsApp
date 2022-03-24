package com.daria.bak.fruitsapp.fruit.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.android.volley.toolbox.Volley
import com.daria.bak.fruitsapp.R
import com.daria.bak.fruitsapp.databinding.FruitLayoutBinding
import com.daria.bak.fruitsapp.fruit.business.FruitViewModel
import com.daria.bak.fruitsapp.fruit.business.FruitViewModelFactory
import com.daria.bak.fruitsapp.fruit.data.FruitClient
import com.daria.bak.fruitsapp.fruit.data.FruitClientInterface


class FruitFragment: Fragment() {
lateinit var binding: FruitLayoutBinding
lateinit var viewModel: FruitViewModel
    var viewStartTime: Long = 0

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fruit_layout,
            container,
            false
        )
        viewStartTime = System.currentTimeMillis()
        binding.lifecycleOwner = this
        val queue = Volley.newRequestQueue(context)
        val client: FruitClientInterface = FruitClient(queue)
        viewModel = ViewModelProviders.of(this, FruitViewModelFactory(client)).get(
            FruitViewModel::class.java)

        val args = FruitFragmentArgs.fromBundle(requireArguments())
        viewModel.setFruitData(args.type, args.price.toFloat(), args.weight.toFloat())
        binding.typeText.text = viewModel.type
        binding.priceText.text = viewModel.price
        binding.weightText.text = viewModel.weight
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewTime = System.currentTimeMillis() - viewStartTime
        Log.i("FruitFragment", "View created in: $viewTime ms")
        viewModel.sendViewAnalytics(viewTime)
    }
}