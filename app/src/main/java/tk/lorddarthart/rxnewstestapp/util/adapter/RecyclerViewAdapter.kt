package tk.lorddarthart.rxnewstestapp.util.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tk.lorddarthart.rxnewstestapp.R
import tk.lorddarthart.rxnewstestapp.application.model.Item
import tk.lorddarthart.rxnewstestapp.util.OnItemTouchListener
import java.net.URL
import java.util.concurrent.ExecutionException

internal class RecyclerViewAdapter internal constructor(private val context: Context, private val listItems: List<Item>, private val onItemTouchListener: OnItemTouchListener) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    internal lateinit var view: View
    private var viewHolder: ViewHolder? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        view = LayoutInflater.from(context).inflate(R.layout.singleitem_news, parent, false)
        viewHolder = ViewHolder(view)
        return viewHolder as ViewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItems[position]
        holder.tvDate.text = item.date
        holder.tvTitle.text = item.title
        holder.tvDesc.text = item.desc
        try {
            UploadImageToItem(item.pic!!, holder).execute().get()
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
        var ivNearDate: ImageView = itemView.findViewById(R.id.ivNearDate)

        init {
            itemView.setOnClickListener { v -> onItemTouchListener.onCardViewTap(v, layoutPosition) }
        }
    }

    /**
     * Загрузка предоставленного к новости изображения отдельно от основного потока
     */
    @SuppressLint("StaticFieldLeak")
    inner class UploadImageToItem internal constructor(
            private var urlString: String,
            private var holder: ViewHolder
    ) : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg voids: Void): Void? {
            try {
                val url = URL(urlString)
                val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                holder.ivNewsPic.setImageBitmap(bmp)
            } catch (e: Exception) {
                holder.ivNewsPic.setImageDrawable(context.resources.getDrawable(R.drawable.no_image_available))
                e.printStackTrace()
            }

            holder.ivNearDate.setImageDrawable(context.resources.getDrawable(R.drawable.ic_m_1))
            return null
        }
    }
}