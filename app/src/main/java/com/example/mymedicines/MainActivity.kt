package com.example.mymedicines
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.fragment.app.commit
//import androidx.fragment.app.FragmentManager
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.core.app.AppOpsManagerCompat
//import com.example.mymedicines.databinding.ActivityMainBinding
//import com.example.mymedicines.ui.theme.MyMedicinesTheme
//
//class MainActivity : ComponentActivity() {
//    lateinit var binding: ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        if (savedInstanceState == null){
//            supportFragmentManager.
//        }
//
//
//    }
//}



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.mymedicines.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Переменная для View Binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализация View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Проверяем, что savedInstanceState равен null, чтобы избежать дублирования фрагмента
        if (savedInstanceState == null) {
            // Загружаем фрагмент в контейнер
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(binding.fragmentContainerView.id, RecyclerViewFragment::class.java, null)
            }
        }
    }
}