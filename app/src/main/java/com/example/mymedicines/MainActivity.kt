package com.example.mymedicines

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.mymedicines.databinding.ActivityMainBinding
import com.example.mymedicines.view.medecine.info.InformationFragment
import com.example.mymedicines.view.medecine.list.DrugListFragment
import com.example.mymedicines.view.medecine.newMed.NewMedecineFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel()

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
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        if (intent?.getBooleanExtra("OPEN_NEWMEDECCINEFRAGMENT", false) == true) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, NewMedecineFragment())
                .addToBackStack("newMedecineFragment")
                .commit()
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "NAV_CHANNEL",
                "Navigation Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Channel for navigation between fragments"
            }
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
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