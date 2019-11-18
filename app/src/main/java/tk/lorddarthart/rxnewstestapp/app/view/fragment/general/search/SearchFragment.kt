package tk.lorddarthart.rxnewstestapp.app.view.fragment.general.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import tk.lorddarthart.rxnewstestapp.app.view.base.BaseFragment
import tk.lorddarthart.rxnewstestapp.databinding.FragmentSearchBinding


/**
 * Fragment for searching elements
 */
class SearchFragment : BaseFragment() {
    private lateinit var searchFragmentBinding: FragmentSearchBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        searchFragmentBinding = FragmentSearchBinding.inflate(inflater, container, false)

        initialization()

        return mainView
    }

    override fun hangObservers() {
        // do something
    }

    override fun initListeners() {
        // do something
    }

    override fun start() {
        try {
            baseActivity.setSupportActionBar(searchFragmentBinding.toolbarSearch)
        } catch (e: NullPointerException) {
            e.message?.let {
                Snackbar.make(mainView, it, Snackbar.LENGTH_LONG).show()
            }
        }
    }
}
