package com.mvvmrxjavatemp.ui.product_page

import android.graphics.Paint
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mvvmrxjavatemp.data.model.api.request.AddToCartRequest
import com.mvvmrxjavatemp.data.model.api.response.ProductResponse
import com.mvvmrxjavatemp.ui.base.BaseFragment
import com.mvvmrxjavatemp.ui.base.ViewModelFactory
import com.mvvmrxjavatemp.ui.main.MainViewModel
import com.squareup.picasso.Picasso
import com.template.R
import kotlinx.android.synthetic.main.product_fragment.view.*
import javax.inject.Inject

class ProductFragment : BaseFragment<ProductViewModel>(),ProductNavigator {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var productViewModel: ProductViewModel

    private lateinit var mainViewModel: MainViewModel

    override fun setUpViewModel() {
        productViewModel = ViewModelProvider(this, viewModelFactory).get(ProductViewModel::class.java)
    }

    override fun getLayoutId() = R.layout.product_fragment
    override fun getViewModel() = productViewModel

    override fun performDependencyInjection() {
        fragmentComponent.inject(this)
    }

    override fun setNavigator() {
        productViewModel.navigator = this
    }

    override fun setUp() {
        activity?.let {
            mainViewModel = ViewModelProviders.of(it, viewModelFactory).get(MainViewModel::class.java)
        }
        productViewModel.getProduct(baseActivity,requireArguments().getString("productid")!!)
        productViewModel.quantity.observe(viewLifecycleOwner, Observer {
            getmRootView().qtyTV.text=it.toString()
        })
        getmRootView().minusTV.setOnClickListener{
            productViewModel.decreaseQty()
        }
        getmRootView().plusTV.setOnClickListener{
            productViewModel.inCreaseQty()
        }
        getmRootView().addToCartBTN.setOnClickListener{
            productViewModel.addToCart(baseActivity,
            AddToCartRequest(requireArguments().getString("productid")!!,productViewModel.quantity.value.toString()))
        }
    }

    override fun productDetailsFetched(homeResponse: ProductResponse) {
        getmRootView().apply {
            homeResponse.data.product.let {
                Picasso.get().load(it.imageUrl).into(image);
                text.text=it.title
                descrptionTV.text=it.description
                priceTV.text=it.price
                priceTV.setPaintFlags(priceTV.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
                offerPrice.text=it.splPrice
            }
        }
    }

    override fun updateCart(cartCount: String) {
        mainViewModel.liveCartCount.value=cartCount.toInt()
    }
}
