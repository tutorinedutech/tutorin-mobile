package com.dicoding.tutorinedutech.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.util.Pair
import androidx.lifecycle.ViewModelProvider
import com.dicoding.tutorinedutech.databinding.ActivitySplashScreenBinding
import com.dicoding.tutorinedutech.helper.ViewModelFactory

class SplashScreenActivity : AppCompatActivity() {
    private var _binding: ActivitySplashScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var splashScreenVM: SplashScreenVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        supportActionBar?.hide()
        actionBar?.hide()

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        splashScreenVM = ViewModelProvider(this, factory)[SplashScreenVM::class.java]
        _binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val mainActivityIntent = Intent(this, MainActivity::class.java)
        val optionsCompat: ActivityOptionsCompat =
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                Pair(binding.ivLogotype, "profile"),
            )

        Handler(Looper.getMainLooper()).postDelayed(
            {
                startActivity(
                    mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION),
                    optionsCompat.toBundle()
                )
            },
            SPLASH_LOGOTYPE_TIMEOUT
        )
    }

    companion object {
        const val SPLASH_LOGOTYPE_TIMEOUT = 800L
    }
}