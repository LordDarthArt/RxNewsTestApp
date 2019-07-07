package tk.lorddarthart.rxnewstestapp.application.view.base

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {
    protected lateinit var mainActivity: BaseActivity
    protected lateinit var mainView: View

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        mainActivity = context as BaseActivity
    }

    open fun initViews() {

    }

    open fun setContent() {

    }
}