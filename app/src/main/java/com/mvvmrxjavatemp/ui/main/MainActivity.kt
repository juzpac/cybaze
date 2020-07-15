package com.mvvmrxjavatemp.ui.main

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.mvvmrxjavatemp.ui.base.BaseActivity
import com.mvvmrxjavatemp.ui.base.ViewModelFactory
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE
import com.google.android.play.core.install.model.UpdateAvailability
import com.template.R
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity<MainViewModel>(), MainNavigator {

    private val IMMEDIATE_RQST_CODE = 156
    private var mAppUpdateInfo: AppUpdateInfo? = null
    private lateinit var appUpdateManager: AppUpdateManager

    private lateinit var navController: NavController

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var doubleBackToExitPressedOnce = false
    override fun getLayoutId() = R.layout.activity_main
    override fun getViewModel() = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    override fun performDependencyInjection() {
        activityComponent.inject(this)
    }

    override fun setNavigator() {
        viewModel.navigator = this
    }

    override fun setUp() {
        navController = findNavController(R.id.nav_host_fragment)
        home_bottom_navigation.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, arguments ->

        }
        viewModel.liveCartCount.observe(this, Observer {
            if (it == 0)
                home_bottom_navigation.removeBadge(R.id.cartFragment)
            else
                home_bottom_navigation.getOrCreateBadge(R.id.cartFragment).number = it
        })

        // Creates instance of the manager.
        appUpdateManager = AppUpdateManagerFactory.create(this)

        // Returns an intent object that you use to check for an update.
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo

        // Checks whether the platform allows the specified type of update,
        // and checks the update priority.
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            mAppUpdateInfo = appUpdateInfo

            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(IMMEDIATE)
            ) {

                appUpdateManager.startUpdateFlowForResult(
                        // Pass the intent that is returned by 'getAppUpdateInfo()'.
                        appUpdateInfo,
                        // Or 'AppUpdateType.FLEXIBLE' for flexible updates.
                        IMMEDIATE,
                        // The current activity making the update request.
                        this,
                        // Include a request code to later monitor this update request.
                        IMMEDIATE_RQST_CODE
                )
            }
        }
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onResume() {
        super.onResume()

        appUpdateManager
                .appUpdateInfo
                .addOnSuccessListener { appUpdateInfo ->

                    if (appUpdateInfo.updateAvailability()
                            == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS
                    ) {
                        // If an in-app update is already running, resume the update.
                        appUpdateManager.startUpdateFlowForResult(
                                appUpdateInfo,
                                IMMEDIATE,
                                this,
                                IMMEDIATE_RQST_CODE
                        )
                    }
                }
    }
}