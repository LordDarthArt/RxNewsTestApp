package tk.lorddarthart.rxnewstestapp.app.view.fragment.general.main.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import tk.lorddarthart.rxnewstestapp.R
import tk.lorddarthart.rxnewstestapp.app.view.base.BaseFragment
import tk.lorddarthart.rxnewstestapp.databinding.FragmentErrorBindingImpl

class ErrorFragment : BaseFragment() {
    private lateinit var errorFragmentBinding: FragmentErrorBindingImpl

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mainView = inflater.inflate(R.layout.fragment_error, container, false)
        return mainView
    }

    override fun start() {
        baseActivity.setSupportActionBar(toolbar)
        arguments?.let { arguments ->
            if (arguments.getString("werror") == "noconnection") {
                textViewErrorDescription.text = resources.getString(R.string.errorText_noConnection)
            } else if (arguments.getString("werror") == "nonews") {
                textViewErrorDescription.text = resources.getString(R.string.errorText_noNews)
            }
        }
    }
}
