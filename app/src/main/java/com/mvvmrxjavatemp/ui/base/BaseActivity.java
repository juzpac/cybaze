package com.mvvmrxjavatemp.ui.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.mvvmrxjavatemp.AppClass;
import com.mvvmrxjavatemp.di.component.ActivityComponent;
import com.mvvmrxjavatemp.di.component.ApplicationComponent;
import com.mvvmrxjavatemp.di.component.DaggerActivityComponent;
import com.mvvmrxjavatemp.di.module.ActivityModule;
import com.mvvmrxjavatemp.utils.CommonUtils;
import com.mvvmrxjavatemp.utils.NetworkUtils;
import com.template.R;

import java.util.Objects;

import io.reactivex.plugins.RxJavaPlugins;


public abstract class BaseActivity<V extends BaseViewModel> extends AppCompatActivity {

    // this can probably depend on isLoading variable of BaseViewModel,
    // since its going to be common for all the activities
    private ProgressDialog mProgressDialog;
    private V mViewModel;
    private BaseFragment baseFragment;

    ActivityComponent activityComponent;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        activityComponent = DaggerActivityComponent.builder().activityModule(new ActivityModule(this))
                .applicationComponent(getApplicationComponent()).build();
        performDependencyInjection();
        mViewModel = getViewModel();
        RxJavaPlugins.setErrorHandler(e -> Log.d("RxException", Objects.requireNonNull(e.getLocalizedMessage())));
        setNavigator();
        setUp();
    }

    public void setUp() {

    }


    public void setNavigator() {

    }

    /**
     * Activity component for dependency injection
     *
     * @return activity component
     */
    protected ActivityComponent getActivityComponent() {
        return activityComponent;
    }


    /**
     * Application component for dependency injection
     *
     * @return application component
     */
    protected ApplicationComponent getApplicationComponent() {
        return AppClass.getApplicationComponent();
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    public void openActivityOnTokenExpire() {

    }

    protected abstract void performDependencyInjection();

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void onInternetConnectionFailure() {
        Toast.makeText(this, R.string.common_text_no_internet_connection_message, Toast.LENGTH_SHORT).show();
    }

    public void onApiFailure() {
        Toast.makeText(this, R.string.common_text_api_failure_message, Toast.LENGTH_SHORT).show();
    }
}

