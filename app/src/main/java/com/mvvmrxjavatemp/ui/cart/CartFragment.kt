package com.mvvmrxjavatemp.ui.cart

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.mvvmrxjavatemp.data.model.api.response.GetCartResponse
import com.mvvmrxjavatemp.ui.base.BaseFragment
import com.mvvmrxjavatemp.ui.base.ViewModelFactory
import com.mvvmrxjavatemp.ui.main.MainViewModel

import com.template.R
import kotlinx.android.synthetic.main.frag_cart.view.*
import javax.inject.Inject

class CartFragment : BaseFragment<CartViewModel>(),CartFragmentNavigator {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var cartViewModel: CartViewModel

    private lateinit var mainViewModel: MainViewModel

    override fun setUpViewModel() {
        cartViewModel = ViewModelProvider(this, viewModelFactory).get(CartViewModel::class.java)
    }

    override fun getLayoutId() = R.layout.frag_cart
    override fun getViewModel() = cartViewModel

    override fun performDependencyInjection() {
        fragmentComponent.inject(this)
    }

    override fun setNavigator() {
        cartViewModel.navigator = this
    }

    override fun setUp() {
        activity?.let {
            mainViewModel = ViewModelProviders.of(it, viewModelFactory).get(MainViewModel::class.java)
        }
        cartViewModel.getCart(baseActivity)
        initRV()
    }

    override fun listFetched(homeResponse: GetCartResponse) {
        getmRootView().cartRV.apply {
            adapter=CartAdapter(baseActivity,homeResponse.data.products)
            homeResponse.data.cartCount.let {
                mainViewModel.liveCartCount.value=it.toInt()
            }
        }
    }
    private fun initRV() {
        getmRootView().cartRV.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(baseActivity,RecyclerView.VERTICAL))
        }
    }
}
