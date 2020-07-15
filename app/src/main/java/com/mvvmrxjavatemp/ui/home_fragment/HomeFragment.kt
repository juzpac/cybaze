package com.mvvmrxjavatemp.ui.home_fragment

import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mvvmrxjavatemp.data.model.api.response.HomeResponse
import com.mvvmrxjavatemp.ui.base.BaseFragment
import com.mvvmrxjavatemp.ui.base.ViewModelFactory
import com.mvvmrxjavatemp.ui.main.MainViewModel
import com.mvvmrxjavatemp.utils.ui.SpaceAdjust

import com.template.R
import kotlinx.android.synthetic.main.fragment_home.view.*
import javax.inject.Inject

class HomeFragment : BaseFragment<HomeViewModel>(), HomeNavigator {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var homeViewModel: HomeViewModel

    private lateinit var mainViewModel: MainViewModel

    override fun setUpViewModel() {
        homeViewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    override fun getLayoutId() = R.layout.fragment_home
    override fun getViewModel() = homeViewModel

    override fun performDependencyInjection() {
        fragmentComponent.inject(this)
    }

    override fun setNavigator() {
        homeViewModel.navigator = this
    }

    override fun setUp() {
        activity?.let {
            mainViewModel = ViewModelProviders.of(it, viewModelFactory).get(MainViewModel::class.java)
        }
        homeViewModel.getHome(baseActivity)
        initRV()
    }

    private fun initRV() {
        getmRootView().productRV.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(baseActivity, 3)
            addItemDecoration(SpaceAdjust(baseActivity))
        }
    }

    override fun dataFetched(homeResponse: HomeResponse) {
        getmRootView().productRV.apply {
            adapter = ProductRVAdapter(baseActivity, homeResponse.data.products) {
                findNavController().navigate(R.id.productFragment, bundleOf("productid" to it))
            }
        }
    }
}
