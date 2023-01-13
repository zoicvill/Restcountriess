package com.safr.restcountries.presentation.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope

import com.safr.restcountries.data.model.CountryItem
import com.safr.restcountries.data.model.Currencies

import com.safr.restcountries.databinding.FragmentTwoScrBinding
import com.safr.restcountries.presentation.adapter.loadImageUtils
import com.safr.restcountries.presentation.viewmodel.ViewModelLand
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

import kotlinx.coroutines.launch


@OptIn(ExperimentalCoroutinesApi::class)
@RequiresApi(Build.VERSION_CODES.M)
@AndroidEntryPoint
class TwoScrFragment : Fragment() {

    private var nameArg: String? = null

    private val mainViewModel: ViewModelLand by activityViewModels()

    private var binding: FragmentTwoScrBinding? = null
    private val mBinding get() = binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nameArg = arguments?.let { TwoScrFragmentArgs.fromBundle(it).name }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTwoScrBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doInitialization()
    }

    private fun doInitialization() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.landContent.collect{
                setView(it.data?.last { ff -> ff.name == nameArg })
            }
        }

    }

    private fun setView( list: CountryItem?) = mBinding.run{
        CoroutineScope(Dispatchers.Main).launch {
            imageView2.loadImageUtils(list?.flags?.png)
        }
        nameLand.text = list?.name
        nameRegion.text = list?.region
        sity.text= list?.capital
        timezones.text = list?.timezones?.joinToString(separator = "\n")
        usd.text = list?.currencies?.map(Currencies::name)?.joinToString()
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }



}