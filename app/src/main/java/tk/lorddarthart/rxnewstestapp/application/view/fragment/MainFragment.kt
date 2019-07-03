package tk.lorddarthart.rxnewstestapp.application.view.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager

import com.google.android.material.tabs.TabLayout
import tk.lorddarthart.rxnewstestapp.util.adapter.PagerAdapter
import tk.lorddarthart.rxnewstestapp.R
import tk.lorddarthart.rxnewstestapp.application.view.base.BaseFragment

import java.util.Objects

class MainFragment : BaseFragment(), MainMainFragment.OnFragmentInteractionListener, MainVipFragment.OnFragmentInteractionListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (Objects.requireNonNull(activity) as AppCompatActivity).setSupportActionBar(toolbar)

        // Create an instance of the tab layout from the view.
        // Create an instance of the tab layout from the view.
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        // Set the text for each tab.
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label1))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label2))
        // Set the tabs to fill the entire layout.
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        // Using PagerAdapter to manage page views in fragments.
        // Each page is represented by its own fragment.
        // This is another example of the adapter pattern.
        val viewPager = view.findViewById<ViewPager>(R.id.pager)
        val adapter = PagerAdapter(childFragmentManager, tabLayout.tabCount)
        viewPager.adapter = adapter

        // Setting a listener for clicks.
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

        return view
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onFragmentInteraction(uri: Uri) {

    }

    companion object {

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}
