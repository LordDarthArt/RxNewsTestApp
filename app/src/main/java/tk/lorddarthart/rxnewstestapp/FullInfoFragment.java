package tk.lorddarthart.rxnewstestapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.service.autofill.TextValueSanitizer;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.util.Objects;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FullInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FullInfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView tvDateFI, tvTitleFI, tvDescFI;
    ImageView ivNewsPicFI, ivNearDateFI;

    public FullInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FullInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FullInfoFragment newInstance(String param1, String param2) {
        FullInfoFragment fragment = new FullInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_full_info, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbarFullInfo);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = getArguments();

        View view = getView();

        tvDateFI = view.findViewById(R.id.tvDateFI);
        tvTitleFI = view.findViewById(R.id.tvTitleFI);
        tvDescFI = view.findViewById(R.id.tvDescFI);
        ivNearDateFI = view.findViewById(R.id.ivNearDateFI);
        ivNewsPicFI = view.findViewById(R.id.ivNewsPicFI);

        if (bundle!=null) {
            tvDateFI.setText(bundle.getString("date"));
            tvTitleFI.setText(bundle.getString("title"));
            tvDescFI.setText(bundle.getString("desc"));
            try {
                new UploadImageToItem(bundle.getString("pic")).execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    } @SuppressLint("StaticFieldLeak")
    public class UploadImageToItem extends AsyncTask<Void, Void, Void> {
        // Загрузка предоставленного к новости изображения отдельно от основного потока
        String urlString;

        UploadImageToItem(String urlString) {
            this.urlString = urlString;
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
                ivNewsPicFI.setImageBitmap(bmp);
            } catch (Exception e) {
                ivNewsPicFI.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.no_image_available));
                e.printStackTrace();
            }
            ivNearDateFI.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_m_1));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
