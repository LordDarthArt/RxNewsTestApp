package tk.lorddarthart.rxnewstestapp.application.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import tk.lorddarthart.rxnewstestapp.R
import tk.lorddarthart.rxnewstestapp.application.view.base.BaseFragment


/**
 * A simple [Fragment] subclass.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountFragment : BaseFragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { arguments ->
            param1 = arguments.getString(ARG_PARAM1)
            param2 = arguments.getString(ARG_PARAM2)
        }
    }

    /**
     * Inflate the layout for Account fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        mainView = inflater.inflate(R.layout.fragment_account, container, false)

        initViews()
        setContent()

        return mainView
    }

    override fun initViews() {
        super.initViews()
        with(mainView) {
            toolbar = findViewById(R.id.toolbarAccount)
        }
    }

    override fun setContent() {
        super.setContent()
        try {
            (mainActivity as AppCompatActivity).setSupportActionBar(toolbar)
        } catch (e: NullPointerException) {
            e.message?.let {
                Snackbar.make(mainView, it, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AccountFragment.
         */
        fun newInstance(param1: String, param2: String): AccountFragment {
            val fragment = AccountFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
