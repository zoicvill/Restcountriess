package com.safr.restcountries.presentation.fragment

import com.safr.restcountries.presentation.adapter.LandAdapter
import com.safr.restcountries.presentation.adapter.LandNumberClickListener
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.safr.restcountries.core.DataState
import com.safr.restcountries.data.model.CountryItem
import com.safr.restcountries.databinding.FragmentFirstBinding
import com.safr.restcountries.presentation.viewmodel.ViewModelLand
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay


@AndroidEntryPoint
@OptIn(ExperimentalCoroutinesApi::class)
@RequiresApi(Build.VERSION_CODES.M)
class FirstFragment : Fragment() {

    private val mainViewModel: ViewModelLand by activityViewModels()

    private var binding: FragmentFirstBinding? = null

    private val mBinding get() = binding!!

    private val laAdapter by lazy {
        LandAdapter(object : LandNumberClickListener {
            override fun onItemClick(item: CountryItem) {
                findNavController()
                    .navigate(FirstFragmentDirections.actionFirstFragmentToTwoScrFragment(item.name))
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.recycler.adapter = laAdapter
        doInitialization()
        upLoad()
    }

    private fun upLoad(){
        mBinding.upload.setOnClickListener {
            doInitialization()
            mBinding.progressBar.isVisible = true
        }
    }

    private fun setResultHistory(result: List<CountryItem>?) {
        laAdapter.submitList(result)
    }

    private fun doInitialization() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.getlandContent()

            mainViewModel.mainContentLoading.collect{
                if (it){
                    delay(3000)
                    mBinding.progressBar.isVisible = false
                }
                mBinding.error.isVisible= it
            }
        }



        lifecycleScope.launchWhenStarted {
            mainViewModel.landContent.collect{
                setResult(it)
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun setResult(result: DataState<List<CountryItem>?>) = mBinding.run{
        when (result) {
            is DataState.Error -> {
               progressBar.isVisible = false
            }
            is DataState.Loading -> {
                progressBar.isVisible = true
            }
            is DataState.Success -> {
                setResultHistory(result.data)
                progressBar.isVisible = false
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}