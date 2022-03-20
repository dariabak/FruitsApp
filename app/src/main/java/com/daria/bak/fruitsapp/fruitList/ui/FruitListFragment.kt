package com.daria.bak.fruitsapp.fruitList.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import com.daria.bak.fruitsapp.R
import com.daria.bak.fruitsapp.databinding.FruitListLayoutBinding

class FruitListFragment: Fragment() {
    private lateinit var binding: FruitListLayoutBinding

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

        return binding.root
    }
}