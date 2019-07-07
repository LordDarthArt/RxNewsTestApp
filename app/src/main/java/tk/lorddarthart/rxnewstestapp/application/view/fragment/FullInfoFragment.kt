package tk.lorddarthart.rxnewstestapp.application.view.fragment

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.AnimatedImageDrawable
import android.graphics.drawable.AnimationDrawable
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import tk.lorddarthart.rxnewstestapp.R
import tk.lorddarthart.rxnewstestapp.application.view.base.BaseFragment
import tk.lorddarthart.rxnewstestapp.util.network.retrofit.RetrofitClient
import java.util.concurrent.ExecutionException

/**
 * A simple [Fragment] subclass.
 * Use the [FullInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FullInfoFragment : BaseFragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var textViewDate: TextView
    private lateinit var textViewTitle: TextView
    private lateinit var textViewDescription: TextView
    private lateinit var toolbar: Toolbar
    private lateinit var imageViewNewsPic: ImageView
    private lateinit var imageViewUnreadMark: ImageView
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { arguments ->
            param1 = arguments.getString(ARG_PARAM1)
            param2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mainView = inflater.inflate(R.layout.fragment_full_info, container, false)

        initViews()
        setContent()

        return mainView
    }

    override fun initViews() {
        super.initViews()
        with(mainView) {
            toolbar = findViewById(R.id.toolbarFullInfo)
            textViewDate = findViewById(R.id.tvDateFI)
            textViewTitle = findViewById(R.id.tvTitleFI)
            textViewDescription = findViewById(R.id.tvDescFI)
            imageViewUnreadMark = findViewById(R.id.ivNearDateFI)
            imageViewNewsPic = findViewById(R.id.ivNewsPicFI)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        mainActivity.onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun setContent() {
        super.setContent()
        toolbar.setNavigationOnClickListener {
            mainActivity.onBackPressed()
        }
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mainActivity.setSupportActionBar(toolbar)
        arguments?.let { arguments ->
            textViewDate.text = arguments.getString("date")
            textViewTitle.text = arguments.getString("title")
            textViewDescription.text = arguments.getString("desc")
            try {
                retrofit = RetrofitClient.getInstance()
                arguments.getString("pic")?.let {
                    animatedImgLoad(it)
                }
            } catch (e: ExecutionException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    private fun animatedImgLoad(urlString: String) {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.P) {
            val animPlaceholderPiePlus = ContextCompat.getDrawable(
                    mainActivity,
                    R.drawable.ic_preload
            ) as AnimatedImageDrawable
            animPlaceholderPiePlus.start()
            Glide.with(this).load(urlString)
                    .placeholder(animPlaceholderPiePlus)
                    .error(R.drawable.ic_no_image_available)
                    .into(imageViewNewsPic)
        } else {
            val animPlaceholderOreoMinus = ContextCompat.getDrawable(
                    mainActivity,
                    R.drawable.ic_preload
            ) as AnimationDrawable
            animPlaceholderOreoMinus.start()
            Glide.with(this).load(urlString)
                    .placeholder(animPlaceholderOreoMinus)
                    .error(R.drawable.ic_no_image_available)
                    .into(imageViewNewsPic)
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
         * @return A new instance of fragment FullInfoFragment.
         */
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
