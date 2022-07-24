package ar.com.jdodevelopment.tmdb.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import ar.com.jdodevelopment.tmdb.presentation.navigation.MainNavGraph
import ar.com.jdodevelopment.tmdb.ui.theme.TmdbApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            TmdbApplicationTheme {
                val navController = rememberNavController()
                MainNavGraph(navController)
            }
        }
    }
}