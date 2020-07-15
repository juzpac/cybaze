package com.mvvmrxjavatemp.di.component;

import com.mvvmrxjavatemp.di.PerFragment;
import com.mvvmrxjavatemp.di.module.FragmentModule;
import com.mvvmrxjavatemp.ui.cart.CartFragment;
import com.mvvmrxjavatemp.ui.home_fragment.HomeFragment;
import com.mvvmrxjavatemp.ui.product_page.ProductFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(HomeFragment fragment);
    void inject(ProductFragment fragment);
    void inject(CartFragment fragment);
}
