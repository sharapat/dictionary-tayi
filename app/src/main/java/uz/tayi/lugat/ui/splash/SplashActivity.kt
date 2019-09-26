package uz.tayi.lugat.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.android.viewmodel.ext.android.viewModel
import uz.tayi.lugat.R
import uz.tayi.lugat.data.Status
import uz.tayi.lugat.extensions.visible
import uz.tayi.lugat.ui.base.BaseActivity
import uz.tayi.lugat.ui.main.MainActivity

class SplashActivity : BaseActivity(), SplashView {

    companion object {
        const val TAG = "SplashActivity"
    }

    private val presenter: SplashPresenter by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter.init(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.getDataIfAppIsFirstLaunched()
        presenter.process.observe(this, Observer {
            when(it?.status) {
                Status.ERROR -> {
                    toast(it.message)
                    Log.d("qatelik", it.message!!)
                }
                Status.SUCCESS -> {
                    goToMainScreen()
                }
                else -> {
                    loadingProgress.visible()
                }
            }
        })
    }

    override fun goToMainScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
