package com.zonnon.newsapp.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.zonnon.newsapp.presentation.home.HomeScreen
import com.zonnon.newsapp.presentation.home.HomeViewModel
import com.zonnon.newsapp.presentation.onboarding.OnboardingScreen
import com.zonnon.newsapp.presentation.onboarding.OnboardingViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route,
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewModel: OnboardingViewModel = hiltViewModel()
                OnboardingScreen(event = viewModel::onEvent)
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route,
        ) {
            composable(route = Route.NewsNavigatorScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(articles = articles, navigate = {})
            }
        }
    }
}
