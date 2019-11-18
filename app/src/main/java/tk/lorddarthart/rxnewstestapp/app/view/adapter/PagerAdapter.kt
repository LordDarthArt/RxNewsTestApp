package tk.lorddarthart.rxnewstestapp.app.view.adapter

import android.os.Parcelable

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import tk.lorddarthart.rxnewstestapp.app.view.fragment.general.main.pages.main.MainPage
import tk.lorddarthart.rxnewstestapp.app.view.fragment.general.main.pages.vip.VipPage

class PagerAdapter internal constructor(fm: FragmentManager, private val mNumOfTabs: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return MainPage()
            1 -> return VipPage()
            else -> return MainPage()
        }
    }

    override fun getCount(): Int {
        return mNumOfTabs
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {
        super.restoreState(state, loader)
    }
}
