package tk.lorddarthart.rxnewstestapp.application.view.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import tk.lorddarthart.rxnewstestapp.R
import tk.lorddarthart.rxnewstestapp.application.view.base.BaseFragment

import java.net.URL
import java.util.Objects
import java.util.concurrent.ExecutionException


/**
 * A simple [Fragment] subclass.
 * Use the [FullInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FullInfoFragment : BaseFragment() {
    private var mParam1: String? = null
    private var mParam2: String? = null

    private var tvDateFI: TextView? = null
    private var tvTitleFI: TextView? = null
    private var tvDescFI: TextView? = null
    internal lateinit var ivNewsPicFI: ImageView
    internal lateinit var ivNearDateFI: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_full_info, container, false)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbarFullInfo)
        (Objects.requireNonNull(activity) as AppCompatActivity).setSupportActionBar(toolbar)
        (Objects.requireNonNull(activity) as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        return view
    }

    override fun onResume() {
        super.onResume()
        val bundle = arguments

        val view = view

        tvDateFI = view!!.findViewById(R.id.tvDateFI)
        tvTitleFI = view.findViewById(R.id.tvTitleFI)
        tvDescFI = view.findViewById(R.id.tvDescFI)
        ivNearDateFI = view.findViewById(R.id.ivNearDateFI)
        ivNewsPicFI = view.findViewById(R.id.ivNewsPicFI)

        if (bundle != null) {
            tvDateFI!!.text = bundle.getString("date")
            tvTitleFI!!.text = bundle.getString("title")
            tvDescFI!!.text = bundle.getString("desc")
            try {
                bundle.getString("pic")?.let { UploadImageToItem(it).execute().get() }
            } catch (e: ExecutionException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    /**
     * Загрузка предоставленного к новости изображения отдельно от основного потока
     */
    @SuppressLint("StaticFieldLeak")
    inner class UploadImageToItem internal constructor(
            private var urlString: String
    ) : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg voids: Void): Void? {
            try {
                val url = URL(urlString)
                val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                ivNewsPicFI.setImageBitmap(bmp)
            } catch (e: Exception) {
                ivNewsPicFI.setImageDrawable(activity!!.resources.getDrawable(R.drawable.no_image_available))
                e.printStackTrace()
            }

            ivNearDateFI.setImageDrawable(activity!!.resources.getDrawable(R.drawable.ic_m_1))
            return null
        }
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FullInfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): FullInfoFragment {
            val fragment = FullInfoFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
