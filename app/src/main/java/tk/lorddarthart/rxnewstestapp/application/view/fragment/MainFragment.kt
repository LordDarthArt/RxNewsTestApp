package tk.lorddarthart.rxnewstestapp.application.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import tk.lorddarthart.rxnewstestapp.R
import tk.lorddarthart.rxnewstestapp.application.view.base.BaseFragment
import tk.lorddarthart.rxnewstestapp.util.adapter.PagerAdapter

class MainFragment : BaseFragment() {
    private lateinit var toolbar: Toolbar
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainView = inflater.inflate(R.layout.fragment_main, container, false)

        initViews()
        setContent()

        return mainView
    }

    override fun initViews() {
        super.initViews()
        with(mainView) {
            toolbar = findViewById(R.id.toolbarFullInfo)
            tabLayout = findViewById(R.id.tab_layout)
            viewPager = findViewById(R.id.pager)
        }
    }

    override fun setContent() {
        super.setContent()

        mainActivity.setSupportActionBar(toolbar)

        /**
         * Create an instance of the tab layout from the mainView.
         */
        /**
         * Set the text for each tab.
         */
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label1))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label2))
        /**
         * Set the tabs to fill the entire layout.
         */
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        /**
         *  Using PagerAdapter to manage page views in fragments.
         *  Each page is represented by its own fragment.
         *  This is another example of the adapter pattern.
         **/
        val adapter = PagerAdapter(childFragmentManager, tabLayout.tabCount)
        viewPager.adapter = adapter

        /**
         * Setting a listener for clicks.
         */
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    companion object {

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}
