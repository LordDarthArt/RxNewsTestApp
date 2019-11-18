package tk.lorddarthart.rxnewstestapp.app.view.fragment.on_open

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import tk.lorddarthart.rxnewstestapp.app.view.base.BaseFragment
import tk.lorddarthart.rxnewstestapp.app.viewmodel.fragment.on_open.LoadingFragmentViewModel
import tk.lorddarthart.rxnewstestapp.databinding.FragmentLoadingBinding

class LoadingFragment : BaseFragment() {
    private lateinit var loadingFragmentBinding: FragmentLoadingBinding

    private val loadingFragmentViewModel: LoadingFragmentViewModel by lazy {
        ViewModelProvider(
                this
        )[LoadingFragmentViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        loadingFragmentBinding = FragmentLoadingBinding.inflate(inflater, container, false)

        initialization()

        return loadingFragmentBinding.root
    }

    override fun hangObservers() {

    }

    override fun initListeners() {

    }

    override fun start() {

    }

}
