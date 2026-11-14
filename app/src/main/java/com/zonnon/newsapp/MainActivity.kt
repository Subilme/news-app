package com.zonnon.newsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.zonnon.newsapp.domain.usecases.AppEntryUsecases
import com.zonnon.newsapp.presentation.onboarding.OnboardingScreen
import com.zonnon.newsapp.presentation.onboarding.OnboardingViewModel
import com.zonnon.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var usecases: AppEntryUsecases

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        lifecycleScope.launch {
            usecases.readAppEntry().collect {
                Log.d("Test",it.toString())
            }
        }
        setContent {
            NewsAppTheme {
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                    val viewModel: OnboardingViewModel = hiltViewModel()
                    OnboardingScreen(event = viewModel::onEvent)
                }
            }
        }
    }
}
