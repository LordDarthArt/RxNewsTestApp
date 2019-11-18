package tk.lorddarthart.rxnewstestapp.app.view.fragment.general.main.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tk.lorddarthart.rxnewstestapp.R
import tk.lorddarthart.rxnewstestapp.app.view.base.BaseFragment
import tk.lorddarthart.rxnewstestapp.databinding.FragmentErrorBinding

class ErrorFragment : BaseFragment() {
    private lateinit var errorFragmentBinding: FragmentErrorBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        errorFragmentBinding = FragmentErrorBinding.inflate(inflater, container, false)



        return errorFragmentBinding.root
    }

    override fun hangObservers() {
        // do nothing
    }

    override fun initListeners() {
        // do nothing
    }

    override fun start() {
        baseActivity.setSupportActionBar(errorFragmentBinding.fragmentErrorToolbar)
        arguments?.let { arguments ->
            if (arguments.getString("werror") == "noconnection") {
                errorFragmentBinding.fragmentErrorDescription.text = resources.getString(R.string.errorText_noConnection)
            } else if (arguments.getString("werror") == "nonews") {
                errorFragmentBinding.fragmentErrorDescription.text = resources.getString(R.string.errorText_noNews)
            }
        }
    }
}
