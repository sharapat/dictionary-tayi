package uz.tayi.lugat.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import org.koin.android.viewmodel.ext.android.viewModel
import uz.tayi.lugat.R
import uz.tayi.lugat.data.Status

class SplashActivity : AppCompatActivity() {

    companion object {
        const val TAG = "SplashActivity"
    }

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel.process.observe(this, Observer {
            when (it.status) {
                Status.ERROR -> {
                    
                }
            }
        })
    }
}
