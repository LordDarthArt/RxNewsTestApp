package tk.lorddarthart.rxnewstestapp.util.adapter

import android.os.Parcelable

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import tk.lorddarthart.rxnewstestapp.application.view.fragment.MainMainFragment
import tk.lorddarthart.rxnewstestapp.application.view.fragment.MainVipFragment

class PagerAdapter internal constructor(fm: FragmentManager, private val mNumOfTabs: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return MainMainFragment()
            1 -> return MainVipFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return mNumOfTabs
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {
        super.restoreState(state, loader)
    }
}
