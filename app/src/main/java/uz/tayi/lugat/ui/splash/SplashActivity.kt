package uz.tayi.lugat.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import org.koin.android.viewmodel.ext.android.viewModel
import uz.tayi.lugat.R
import uz.tayi.lugat.data.Status
import uz.tayi.lugat.ui.base.BaseActivity
import uz.tayi.lugat.ui.main.MainActivity

class SplashActivity : BaseActivity() {

    companion object {
        const val TAG = "SplashActivity"
    }

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onStart() {
        super.onStart()
        viewModel.process.observe(this, Observer {
            when (it.status) {
                Status.ERROR -> {
                    toast(it.data)
                }
                Status.SUCCESS -> {
                    goToMainWindow()
                }
                Status.LOADING -> {
                    // do nothing
                }
            }
        })
        viewModel.initializeDatabase()
    }

    private fun goToMainWindow() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
