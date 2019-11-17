package tk.lorddarthart.rxnewstestapp.app.view.fragment.general.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import tk.lorddarthart.rxnewstestapp.R
import tk.lorddarthart.rxnewstestapp.app.view.base.BaseFragment
import tk.lorddarthart.rxnewstestapp.app.viewmodel.fragment.general.notifications.NotificationsFragmentViewModel
import tk.lorddarthart.rxnewstestapp.databinding.FragmentNotificationsBinding

class NotificationsFragment : BaseFragment() {
    private lateinit var notificationsFragmentBinding: FragmentNotificationsBinding

    private val notificationsFragmentViewModel: NotificationsFragmentViewModel by lazy {
        ViewModelProvider(
                this
        )[NotificationsFragmentViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        notificationsFragmentBinding = FragmentNotificationsBinding.inflate(inflater, container, false)

        initialization()

        return notificationsFragmentBinding.root
    }

    override fun hangObservers() {
        // do something
    }

    override fun initListeners() {
        // do something
    }

    override fun start() {
        try {
            (baseActivity as AppCompatActivity).setSupportActionBar(notificationsFragmentBinding.toolbarNotifications)
        } catch (e: NullPointerException) {
            e.message?.let {
                Snackbar.make(this.mainView, it, Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
