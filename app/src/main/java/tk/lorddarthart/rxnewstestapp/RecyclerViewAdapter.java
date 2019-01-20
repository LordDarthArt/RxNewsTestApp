package tk.lorddarthart.rxnewstestapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Item> listItems;
    View view;
    private ViewHolder viewHolder;
    private OnItemTouchListener onItemTouchListener;

    RecyclerViewAdapter(Context context, List<Item> listItems, OnItemTouchListener onItemTouchListener) {
        this.context = context;
        this.listItems = listItems;
        this.onItemTouchListener = onItemTouchListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.singleitem_news, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Item item = listItems.get(position);
        holder.tvDate.setText(item.getDate());
        holder.tvTitle.setText(item.getTitle());
        holder.tvDesc.setText(item.getDesc());
        try {
            new UploadImageToItem(item.getPic(),holder).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvDate, tvTitle, tvDesc;
        ImageView ivNewsPic;

        ViewHolder(View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tvDate);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            ivNewsPic = itemView.findViewById(R.id.ivNewsPic);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemTouchListener.onCardViewTap(v, getLayoutPosition());
                }
            });
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class UploadImageToItem extends AsyncTask<Void, Void, Void> {
        // Загрузка предоставленного к новости изображения отдельно от основного потока
        String urlString;
        ViewHolder holder;

        UploadImageToItem(String urlString, ViewHolder holder) {
            this.urlString = urlString;
            this.holder = holder;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL(urlString);
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                holder.ivNewsPic.setImageBitmap(bmp);
            } catch (Exception e) {
                holder.ivNewsPic.setImageDrawable(context.getResources().getDrawable(R.drawable.no_image_available));
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}