package tk.lorddarthart.rxnewstestapp.app.view.base

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import tk.lorddarthart.rxnewstestapp.app.view.base.interfaces.IBaseFragment

abstract class  BaseFragment: Fragment(), IBaseFragment {
    protected lateinit var baseActivity: BaseActivity
    protected lateinit var mainView: View

    override fun onAttach(context: Context) {
        super.onAttach(context)

        baseActivity = context as BaseActivity
    }

    override fun initialization() {
        hangObservers()
        initListeners()
        start()
    }
}