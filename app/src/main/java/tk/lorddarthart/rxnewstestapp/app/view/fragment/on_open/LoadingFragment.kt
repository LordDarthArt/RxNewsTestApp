package tk.lorddarthart.rxnewstestapp.app.view.fragment.on_open

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tk.lorddarthart.rxnewstestapp.app.viewmodel.fragment.on_open.LoadingFragmentViewModel
import tk.lorddarthart.rxnewstestapp.R


class LoadingFragment : Fragment() {

    private val loadingFragmentViewModel: LoadingFragmentViewModel by lazy {
        ViewModelProvider(
                this
        )[LoadingFragmentViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_loading, container, false)
    }

}
