package com.daria.bak.fruitsapp.fruit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.daria.bak.fruitsapp.R
import com.daria.bak.fruitsapp.databinding.FruitLayoutBinding

class FruitFragment: Fragment() {
lateinit var binding: FruitLayoutBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fruit_layout,
            container,
            false
        )


        binding.lifecycleOwner = this
        return binding.root
    }

}