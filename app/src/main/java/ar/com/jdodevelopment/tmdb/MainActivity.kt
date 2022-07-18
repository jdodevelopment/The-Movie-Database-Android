package ar.com.jdodevelopment.tmdb

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import ar.com.jdodevelopment.tmdb.ui.theme.TmdbApplicationTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TmdbApplicationTheme {
                Text("Hello")
            }
        }
    }

}