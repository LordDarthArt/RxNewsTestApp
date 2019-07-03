package tk.lorddarthart.rxnewstestapp.application.view.activity

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.google.android.material.bottomnavigation.BottomNavigationView
import tk.lorddarthart.rxnewstestapp.R
import tk.lorddarthart.rxnewstestapp.application.view.base.BaseActivity
import tk.lorddarthart.rxnewstestapp.application.view.fragment.*

class MainActivity : BaseActivity(), MainMainFragment.OnFragmentInteractionListener, MainVipFragment.OnFragmentInteractionListener {

    private val responseText: String? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_main -> {
                initializeFragment(MainFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                initializeFragment(SearchFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                initializeFragment(NotificationsFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_account -> {
                initializeFragment(AccountFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.selectedItemId = R.id.navigation_main
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        // Получаем ответ - строку от сервера
    }

    private fun initializeFragment(fr: Fragment) {
        // Инициализация кастомного фрагмента
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.mainFragment, fr)
        fragmentManager.commit()
    }

    override fun onFragmentInteraction(uri: Uri) {

    }
}
