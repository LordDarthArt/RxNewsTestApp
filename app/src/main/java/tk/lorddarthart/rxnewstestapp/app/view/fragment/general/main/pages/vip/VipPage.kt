package tk.lorddarthart.rxnewstestapp.app.view.fragment.general.main.pages.vip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tk.lorddarthart.rxnewstestapp.app.view.base.BaseFragment
import tk.lorddarthart.rxnewstestapp.databinding.PageVipBinding

class VipPage : BaseFragment() {
    private lateinit var vipPageBinding: PageVipBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        vipPageBinding = PageVipBinding.inflate(inflater, container, false)

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
        // do something
    }
}
