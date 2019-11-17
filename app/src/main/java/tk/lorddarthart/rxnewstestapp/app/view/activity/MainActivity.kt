package tk.lorddarthart.rxnewstestapp.app.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import tk.lorddarthart.rxnewstestapp.R
import tk.lorddarthart.rxnewstestapp.app.view.base.BaseActivity
import tk.lorddarthart.rxnewstestapp.app.view.base.interfaces.IBaseActivity
import tk.lorddarthart.rxnewstestapp.app.viewmodel.activity.MainActivityViewModel
import tk.lorddarthart.rxnewstestapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var mainActivityBinding: ActivityMainBinding

    private val mainActivityViewModel: MainActivityViewModel by lazy {
        ViewModelProvider(
                this
        )[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initialization()
    }

    override fun hangObservers() {
        // do something
    }

    override fun initListeners() {
        // do something
    }

    override fun start() {
        // do something
    }
}
