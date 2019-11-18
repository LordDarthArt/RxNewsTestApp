package tk.lorddarthart.rxnewstestapp.app.view.fragment.general.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import tk.lorddarthart.rxnewstestapp.R
import tk.lorddarthart.rxnewstestapp.app.view.adapter.PagerAdapter
import tk.lorddarthart.rxnewstestapp.app.view.base.BaseFragment
import tk.lorddarthart.rxnewstestapp.app.viewmodel.fragment.general.main.MainFragmentViewModel
import tk.lorddarthart.rxnewstestapp.databinding.FragmentMainBinding

class MainFragment : BaseFragment() {
    private lateinit var mainFragmentBinding: FragmentMainBinding

    private val mainFragmentViewModel: MainFragmentViewModel by lazy {
        ViewModelProvider(
                this
        )[MainFragmentViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainFragmentBinding = FragmentMainBinding.inflate(inflater, container, false)

        initialization()

        return mainView
    }

    override fun hangObservers() {

    }

    override fun initListeners() {
        mainFragmentBinding.fragmentMainTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                mainFragmentBinding.fragmewntMainPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    override fun start() {
        baseActivity.setSupportActionBar(mainFragmentBinding.fragmentMainToolbar)

        with(mainFragmentBinding.fragmentMainTabLayout) {
            addTab(newTab().setText(R.string.tab_label1))
            addTab(newTab().setText(R.string.tab_label2))
            tabGravity = TabLayout.GRAVITY_FILL
            val adapter = PagerAdapter(childFragmentManager, tabCount)
            mainFragmentBinding.fragmewntMainPager.adapter = adapter
            mainFragmentBinding.fragmewntMainPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(this))
        }
    }
}
