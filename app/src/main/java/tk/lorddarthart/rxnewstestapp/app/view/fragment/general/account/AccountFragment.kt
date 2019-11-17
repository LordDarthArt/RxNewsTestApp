package tk.lorddarthart.rxnewstestapp.app.view.fragment.general.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import tk.lorddarthart.rxnewstestapp.R
import tk.lorddarthart.rxnewstestapp.app.view.base.BaseFragment


/**
 * A simple [Fragment] subclass.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        mainView = inflater.inflate(R.layout.fragment_account, container, false)

        initialization()

        return mainView
    }

    override fun hangObservers() {

    }

    override fun initListeners() {

    }

    override fun start() {
//        try {
//            (baseActivity as AppCompatActivity).setSupportActionBar(toolbar)
//        } catch (e: NullPointerException) {
//            e.message?.let {
//                Snackbar.make(mainView, it, Snackbar.LENGTH_LONG).show()
//            }
//        }
    }
}
