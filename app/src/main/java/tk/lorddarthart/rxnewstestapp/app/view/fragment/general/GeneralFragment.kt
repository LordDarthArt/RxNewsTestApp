package tk.lorddarthart.rxnewstestapp.app.view.fragment.general

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import tk.lorddarthart.rxnewstestapp.R
import tk.lorddarthart.rxnewstestapp.app.view.base.BaseFragment
import tk.lorddarthart.rxnewstestapp.app.viewmodel.fragment.general.GeneralViewModel
import tk.lorddarthart.rxnewstestapp.databinding.FragmentGeneralBinding

class GeneralFragment : BaseFragment() {
    private lateinit var generalFragmentBinding: FragmentGeneralBinding
    private lateinit var generalFragmentNavController: NavController

    private val generalFragmentViewModel: GeneralViewModel by lazy {
        ViewModelProvider(
                this
        )[GeneralViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        generalFragmentBinding = FragmentGeneralBinding.inflate(inflater, container, false)

        initialization()

        return generalFragmentBinding.root
    }

    override fun hangObservers() {
        // do something
    }

    override fun initListeners() {
        // do something
    }

    override fun start() {
        generalFragmentNavController = findNavController()
        generalFragmentBinding.bottomNavigationBar.setupWithNavController(generalFragmentNavController)

        NavigationUI.setupActionBarWithNavController(baseActivity, generalFragmentNavController)
    }

}
