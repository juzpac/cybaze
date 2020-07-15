package com.mvvmrxjavatemp.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mvvmrxjavatemp.AppClass;
import com.mvvmrxjavatemp.di.component.ApplicationComponent;
import com.mvvmrxjavatemp.di.component.DaggerFragmentComponent;
import com.mvvmrxjavatemp.di.component.FragmentComponent;
import com.mvvmrxjavatemp.di.module.FragmentModule;

public abstract class BaseFragment<V extends BaseViewModel> extends Fragment implements View.OnKeyListener {

    private BaseActivity mActivity;
    private View mRootView;
    private V mViewModel;
    private FragmentComponent fragmentComponent;

    /**
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract V getViewModel();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
            fragmentComponent = DaggerFragmentComponent.builder()
                    .fragmentModule(new FragmentModule(mActivity))
                    .applicationComponent(getApplicationComponent())
                    .build();
            performDependencyInjection();
            setUpViewModel();
            mViewModel = getViewModel();
            setNavigator();
            setUp();
        }
        return mRootView;
    }

    public void setUpViewModel() {

    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    public void setUp() {

    }


    public void setNavigator() {

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRootView.setFocusableInTouchMode(true);
        mRootView.requestFocus();
        mRootView.setOnKeyListener(this);
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }


    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    public boolean isNetworkConnected() {
        return mActivity != null && mActivity.isNetworkConnected();
    }

    public void openActivityOnTokenExpire() {
        if (mActivity != null) {
            mActivity.openActivityOnTokenExpire();
        }
    }

    protected abstract void performDependencyInjection();

    /**
     * Application component for dependency injection
     *
     * @return application component
     */
    protected ApplicationComponent getApplicationComponent() {
        return AppClass.getApplicationComponent();
    }

    public FragmentComponent getFragmentComponent() {
        return fragmentComponent;
    }

    public View getmRootView() {
        return mRootView;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                onFragmentBackPressed();
                return true;
            }
        }

        return false;
    }

    protected void onFragmentBackPressed() {
        getBaseActivity().onBackPressed();
    }
}
