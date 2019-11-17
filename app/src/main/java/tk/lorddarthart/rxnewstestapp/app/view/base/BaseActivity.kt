package tk.lorddarthart.rxnewstestapp.app.view.base

import androidx.appcompat.app.AppCompatActivity
import tk.lorddarthart.rxnewstestapp.app.view.base.interfaces.IBaseActivity

abstract class BaseActivity : AppCompatActivity(), IBaseActivity {
    override fun initialization() {
        hangObservers()
        initListeners()
        start()
    }
}