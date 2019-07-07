package tk.lorddarthart.rxnewstestapp.util.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.AnimatedImageDrawable
import android.graphics.drawable.AnimationDrawable
import android.os.AsyncTask
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tk.lorddarthart.rxnewstestapp.R
import tk.lorddarthart.rxnewstestapp.application.model.Item
import tk.lorddarthart.rxnewstestapp.util.OnItemTouchListener
import java.net.URL
import java.util.concurrent.ExecutionException

internal class RecyclerViewAdapter internal constructor(
        private val context: Context,
        private val listItems: List<Item>,
        private val onItemTouchListener: OnItemTouchListener
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    internal lateinit var mainView: View
    private var mainViewHolder: ViewHolder? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mainView = LayoutInflater.from(context).inflate(
                R.layout.singleitem_news,
                parent,
                false
        )
        mainViewHolder = ViewHolder(mainView)
        return mainViewHolder as ViewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItems[position]
        holder.tvDate.text = item.date
        holder.tvTitle.text = item.title
        holder.tvDesc.text = item.desc
        try {
            animatedImgLoad(item.pic!!, holder)
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvDate: TextView = itemView.findViewById(R.id.tvDate)
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var tvDesc: TextView = itemView.findViewById(R.id.tvDesc)
        var ivNewsPic: ImageView = itemView.findViewById(R.id.ivNewsPic)

        init {
            itemView.setOnClickListener { v -> onItemTouchListener.onCardViewTap(v, layoutPosition) }
        }
    }

    private fun animatedImgLoad(urlString: String, holder: ViewHolder) {
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.P) {
            val animPlaceholderPiePlus = ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_preload
            ) as AnimatedImageDrawable
            animPlaceholderPiePlus.start()
            Glide.with(context).load(urlString)
                    .placeholder(animPlaceholderPiePlus)
                    .error(R.drawable.ic_no_image_available)
                    .into(holder.ivNewsPic)
        } else {
            val animPlaceholderOreoMinus = ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_preload
            ) as AnimationDrawable
            animPlaceholderOreoMinus.start()
            Glide.with(context).load(urlString)
                    .placeholder(animPlaceholderOreoMinus)
                    .error(R.drawable.ic_no_image_available)
                    .into(holder.ivNewsPic)
        }
    }
}