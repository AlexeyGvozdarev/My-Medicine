package com.example.mymedicines

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