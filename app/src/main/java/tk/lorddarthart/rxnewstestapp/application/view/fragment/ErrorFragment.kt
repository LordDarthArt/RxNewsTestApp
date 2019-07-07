package tk.lorddarthart.rxnewstestapp.application.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import tk.lorddarthart.rxnewstestapp.R
import tk.lorddarthart.rxnewstestapp.application.view.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 * Use the [ErrorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ErrorFragment : BaseFragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var textViewErrorDescription: TextView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { arguments ->
            param1 = arguments.getString(ARG_PARAM1)
            param2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mainView = inflater.inflate(R.layout.fragment_error, container, false)
        return mainView
    }

    override fun initViews() {
        super.initViews()
        with(mainView) {
            toolbar = findViewById(R.id.toolbarError)
            textViewErrorDescription = findViewById(R.id.tvError)
        }
    }

    override fun setContent() {
        super.setContent()
        mainActivity.setSupportActionBar(toolbar)
        arguments?.let { arguments ->
            if (arguments.getString("werror") == "noconnection") {
                textViewErrorDescription.text = resources.getString(R.string.errorText_noConnection)
            } else if (arguments.getString("werror") == "nonews") {
                textViewErrorDescription.text = resources.getString(R.string.errorText_noNews)
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
//         * @param param2 Parameter 2.
         * @return A new instance of fragment ErrorFragment.
         */
        fun newInstance(param1: String): ErrorFragment {
            val fragment = ErrorFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
//            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
