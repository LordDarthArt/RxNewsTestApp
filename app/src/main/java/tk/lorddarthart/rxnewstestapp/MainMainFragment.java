package tk.lorddarthart.rxnewstestapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.widget.PullRefreshLayout;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainMainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainMainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter rvAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private OnItemTouchListener itemTouchListener;
    private PullRefreshLayout pullRefreshLayout;

    public MainMainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainMainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainMainFragment newInstance(String param1, String param2) {
        MainMainFragment fragment = new MainMainFragment();
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

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_main, container, false);

        pullRefreshLayout = view.findViewById(R.id.pull2refresh);
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                HttpServiceHelper.getInstance()
                        .getJSONApi()
                        .getPostWithID("1wozWr5swgtdV9PLyo2b09mtjaOD6sS2I")
                        .enqueue(new Callback<News>() {
                            @Override
                            public void onResponse(@NonNull Call<News> call, @NonNull Response<News> response) {
                                final News news = response.body();
                                if (news!=null&&news.getNews().size()>0) {
                                    itemTouchListener = new OnItemTouchListener() {
                                        @Override
                                        public void onCardViewTap(View view, int position) {
                                            Bundle bundle = new Bundle();
                                            bundle.putString("date", news.getNews().get(position).getDate());
                                            bundle.putString("title", news.getNews().get(position).getTitle());
                                            bundle.putString("desc", news.getNews().get(position).getDesc());
                                            bundle.putString("pic", news.getNews().get(position).getPic());
                                            Fragment fr = new FullInfoFragment();
                                            FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                                            fr.setArguments(bundle);
                                            transaction.replace(R.id.mainFragment, fr).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null);
                                            transaction.commit();
                                        }

                                        @Override
                                        public void onButtonCvMenuClick(View view, int position) {

                                        }
                                    };
                                    rvAdapter = new RecyclerViewAdapter(Objects.requireNonNull(getActivity()).getApplicationContext(), news.getNews(), itemTouchListener);
                                    recyclerView.setAdapter(rvAdapter);
                                    pullRefreshLayout.setRefreshing(false);
                                } else {
                                    Fragment fr = new ErrorFragment();
                                    FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("werror", "nonews");
                                    fr.setArguments(bundle);
                                    transaction.replace(R.id.mainFragment, fr);
                                    transaction.commit();
                                    pullRefreshLayout.setRefreshing(false);
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<News> call, @NonNull Throwable t) {
                                Fragment fr = new ErrorFragment();
                                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                Bundle bundle = new Bundle();
                                bundle.putString("werror", "noconnection");
                                fr.setArguments(bundle);
                                transaction.replace(R.id.mainFragment, fr);
                                transaction.commit();
                                t.printStackTrace();
                                pullRefreshLayout.setRefreshing(false);
                            }
                        });
            }
        });
        recyclerView = view.findViewById(R.id.rvMainMain);
        layoutManager = new LinearLayoutManager(Objects.requireNonNull(getActivity()).getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        HttpServiceHelper.getInstance()
                .getJSONApi()
                .getPostWithID("1wozWr5swgtdV9PLyo2b09mtjaOD6sS2I")
                .enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(@NonNull Call<News> call, @NonNull Response<News> response) {
                        final News news = response.body();
                        if (news!=null&&news.getNews().size()>0) {
                            itemTouchListener = new OnItemTouchListener() {
                                @Override
                                public void onCardViewTap(View view, int position) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("date", news.getNews().get(position).getDate());
                                    bundle.putString("title", news.getNews().get(position).getTitle());
                                    bundle.putString("desc", news.getNews().get(position).getDesc());
                                    bundle.putString("pic", news.getNews().get(position).getPic());
                                    Fragment fr = new FullInfoFragment();
                                    FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                                    fr.setArguments(bundle);
                                    transaction.replace(R.id.mainFragment, fr).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null);
                                    transaction.commit();
                                }

                                @Override
                                public void onButtonCvMenuClick(View view, int position) {

                                }
                            };
                            rvAdapter = new RecyclerViewAdapter(Objects.requireNonNull(getActivity()).getApplicationContext(), news.getNews(), itemTouchListener);
                            recyclerView.setAdapter(rvAdapter);
                        } else {
                            Fragment fr = new ErrorFragment();
                            FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                            Bundle bundle = new Bundle();
                            bundle.putString("werror", "nonews");
                            fr.setArguments(bundle);
                            transaction.replace(R.id.mainFragment, fr);
                            transaction.commit();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<News> call, @NonNull Throwable t) {
                        Fragment fr = new ErrorFragment();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        Bundle bundle = new Bundle();
                        bundle.putString("werror", "noconnection");
                        fr.setArguments(bundle);
                        transaction.replace(R.id.mainFragment, fr);
                        transaction.commit();
                        t.printStackTrace();
                    }
                });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
