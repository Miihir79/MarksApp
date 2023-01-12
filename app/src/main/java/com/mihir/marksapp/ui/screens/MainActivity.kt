package com.mihir.marksapp.ui.screens

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.gson.Gson
import com.mihir.marksapp.R
import com.mihir.marksapp.ui.adapter.QuestionAdapter
import com.mihir.marksapp.databinding.ActivityMainBinding
import com.mihir.marksapp.model.Data

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        window.statusBarColor = ContextCompat.getColor(this, R.color.purple_500)
        supportActionBar?.hide()
        setContentView(binding.root)

        val data = loadJSONFromAssets("JsonData.json")
        val dataInFormat = data.toKotlinObject<Data>()

        binding.recycler.adapter = QuestionAdapter(dataInFormat)

        binding.infoText.text = dataInFormat.size.toString() + " Qs"

    }
}

fun Context.loadJSONFromAssets(fileName: String): String {
    return applicationContext.assets.open(fileName).bufferedReader().use { reader ->
        reader.readText()
    }
}

inline fun <reified T: Any> String.toKotlinObject(): T =
    Gson().fromJson(this, T::class.java)