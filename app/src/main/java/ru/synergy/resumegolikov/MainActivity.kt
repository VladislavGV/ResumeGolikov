package ru.synergy.resumegolikov

import android.os.Bundle
import android.widget.Button
import android.widget.ExpandableListAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import ru.synergy.resumegolikov.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var listViewAdapter: ExpandableListAdapter
    private lateinit var resumeList: List<String>
    private lateinit var exp1 : HashMap<String, List<String>>

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listViewAdapter = ExpandableListAdapter(this, resumeList, exp1)
        tvResumeList.setAdapter(listViewAdapter)

        showList()


        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_resume, R.id.navigation_contacts
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun showList() {
        resumeList = ArrayList()
        exp1 = HashMap()

        (resumeList as ArrayList<String>).add("Образовательный холдтнг Синергия")
        (resumeList as ArrayList<String>).add("ООО О'КЕЙ")
        (resumeList as ArrayList<String>).add("ООО Фреш Маркет")
    }
}