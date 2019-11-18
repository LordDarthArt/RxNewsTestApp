package tk.lorddarthart.rxnewstestapp.app.view.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import tk.lorddarthart.rxnewstestapp.R
import tk.lorddarthart.rxnewstestapp.app.view.base.BaseActivity
import tk.lorddarthart.rxnewstestapp.app.viewmodel.activity.MainActivityViewModel

class MainActivity : BaseActivity() {

    lateinit var parentNavController: NavController

    private val mainActivityViewModel: MainActivityViewModel by lazy {
        ViewModelProvider(
                this
        )[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialization()
    }

    override fun hangObservers() {
        // do something
    }

    override fun initListeners() {
        // do something
    }

    override fun start() {
        parentNavController = findNavController(R.id.main_parent_fragment_container)
    }
}
