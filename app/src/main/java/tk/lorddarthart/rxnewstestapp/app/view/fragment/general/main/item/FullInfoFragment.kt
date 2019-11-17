package tk.lorddarthart.rxnewstestapp.app.view.fragment.general.main.item

import android.graphics.drawable.AnimatedImageDrawable
import android.graphics.drawable.AnimationDrawable
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
import retrofit2.Retrofit
import tk.lorddarthart.rxnewstestapp.R
import tk.lorddarthart.rxnewstestapp.app.view.base.BaseFragment
import tk.lorddarthart.rxnewstestapp.databinding.FragmentFullInfoBinding
import tk.lorddarthart.rxnewstestapp.util.network.retrofit.RetrofitClient
import java.util.concurrent.ExecutionException

class FullInfoFragment : BaseFragment() {

    private lateinit var fullInfoFragmentBinding: FragmentFullInfoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mainView = inflater.inflate(R.layout.fragment_full_info, container, false)

        initialization()

        return mainView
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        baseActivity.onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun hangObservers() {
        // do something
    }

    override fun initListeners() {
        // do something
    }

    override fun start() {
        fullInfoFragmentBinding.fragmentFullInfoToolbar.setNavigationOnClickListener {
            baseActivity.onBackPressed()
        }
        baseActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        baseActivity.setSupportActionBar(fullInfoFragmentBinding.fragmentFullInfoToolbar)
    }

    private fun animatedImgLoad(urlString: String) {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.P) {
            val animPlaceholderPiePlus = ContextCompat.getDrawable(
                    baseActivity,
                    R.drawable.ic_preload
            ) as AnimatedImageDrawable
            animPlaceholderPiePlus.start()
            Glide.with(this).load(urlString)
                    .placeholder(animPlaceholderPiePlus)
                    .error(R.drawable.ic_no_image_available)
                    .into(fullInfoFragmentBinding.fragmentFullInfoImage)
        } else {
            val animPlaceholderOreoMinus = ContextCompat.getDrawable(
                    baseActivity,
                    R.drawable.ic_preload
            ) as AnimationDrawable
            animPlaceholderOreoMinus.start()
            Glide.with(this).load(urlString)
                    .placeholder(animPlaceholderOreoMinus)
                    .error(R.drawable.ic_no_image_available)
                    .into(fullInfoFragmentBinding.fragmentFullInfoImage)
        }
    }
}
