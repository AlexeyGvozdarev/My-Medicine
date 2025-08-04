package com.example.mymedicines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.mymedicines.databinding.ActivityMainBinding
import com.example.mymedicines.view.medecine.list.DrugListFragment

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
                replace(binding.fragmentContainerView.id, DrugListFragment::class.java, null)
            }
        }
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainerView.id, fragment)
            addToBackStack(null)
            commit()
        }
    }

    fun popFragmentBack() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}