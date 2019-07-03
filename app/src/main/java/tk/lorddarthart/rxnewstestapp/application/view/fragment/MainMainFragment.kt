package tk.lorddarthart.rxnewstestapp.application.view.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.baoyz.widget.PullRefreshLayout

import java.util.Objects

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tk.lorddarthart.rxnewstestapp.*
import tk.lorddarthart.rxnewstestapp.util.network.HttpServiceHelper
import tk.lorddarthart.rxnewstestapp.util.adapter.RecyclerViewAdapter
import tk.lorddarthart.rxnewstestapp.application.model.News
import tk.lorddarthart.rxnewstestapp.application.view.base.BaseFragment
import tk.lorddarthart.rxnewstestapp.util.OnItemTouchListener


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MainMainFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MainMainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainMainFragment : BaseFragment() {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    private var mListener: OnFragmentInteractionListener? = null
    private var recyclerView: RecyclerView? = null
    private var rvAdapter: RecyclerViewAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var itemTouchListener: OnItemTouchListener? = null
    private var pullRefreshLayout: PullRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_main_main, container, false)

        pullRefreshLayout = view.findViewById(R.id.pull2refresh)
        pullRefreshLayout!!.setOnRefreshListener {
            HttpServiceHelper.instance
                    .jsonApi
                    .getPostWithID("1wozWr5swgtdV9PLyo2b09mtjaOD6sS2I")
                    .enqueue(object : Callback<News> {
                        override fun onResponse(call: Call<News>, response: Response<News>) {
                            val news = response.body()
                            if (news != null && news.news!!.isNotEmpty()) {
                                itemTouchListener = object : OnItemTouchListener {
                                    override fun onCardViewTap(view: View, position: Int) {
                                        val bundle = Bundle()
                                        bundle.putString("date", news.news!![position].date)
                                        bundle.putString("title", news.news!![position].title)
                                        bundle.putString("desc", news.news!![position].desc)
                                        bundle.putString("pic", news.news!![position].pic)
                                        val fr = FullInfoFragment()
                                        val transaction = Objects.requireNonNull(activity)?.supportFragmentManager?.beginTransaction()
                                        fr.arguments = bundle
                                        transaction?.replace(R.id.mainFragment, fr)?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)?.addToBackStack(null)
                                        transaction?.commit()
                                    }

                                    override fun onButtonCvMenuClick(view: View, position: Int) {

                                    }
                                }
                                rvAdapter = RecyclerViewAdapter(Objects.requireNonNull(activity)?.applicationContext!!, news.news!!, itemTouchListener!!)
                                recyclerView!!.adapter = rvAdapter
                                pullRefreshLayout!!.setRefreshing(false)
                            } else {
                                val fr = ErrorFragment()
                                val transaction = activity?.supportFragmentManager?.beginTransaction()
                                val bundle = Bundle()
                                bundle.putString("werror", "nonews")
                                fr.arguments = bundle
                                transaction?.replace(R.id.mainFragment, fr)
                                transaction?.commit()
                                pullRefreshLayout!!.setRefreshing(false)
                            }
                        }

                        override fun onFailure(call: Call<News>, t: Throwable) {
                            val fr = ErrorFragment()
                            val transaction = activity!!.supportFragmentManager.beginTransaction()
                            val bundle = Bundle()
                            bundle.putString("werror", "noconnection")
                            fr.arguments = bundle
                            transaction.replace(R.id.mainFragment, fr)
                            transaction.commit()
                            t.printStackTrace()
                            pullRefreshLayout!!.setRefreshing(false)
                        }
                    })
        }
        recyclerView = view.findViewById(R.id.rvMainMain)
        layoutManager = LinearLayoutManager(activity?.applicationContext)
        recyclerView!!.layoutManager = layoutManager

        HttpServiceHelper.instance
                .jsonApi
                .getPostWithID("1wozWr5swgtdV9PLyo2b09mtjaOD6sS2I")
                .enqueue(object : Callback<News> {
                    override fun onResponse(call: Call<News>, response: Response<News>) {
                        val news = response.body()
                        if (news != null && news.news!!.isNotEmpty()) {
                            itemTouchListener = object : OnItemTouchListener {
                                override fun onCardViewTap(view: View, position: Int) {
                                    val bundle = Bundle()
                                    bundle.putString("date", news.news!![position].date)
                                    bundle.putString("title", news.news!![position].title)
                                    bundle.putString("desc", news.news!![position].desc)
                                    bundle.putString("pic", news.news!![position].pic)
                                    val fr = FullInfoFragment()
                                    val transaction = activity?.supportFragmentManager?.beginTransaction()
                                    fr.arguments = bundle
                                    transaction?.replace(R.id.mainFragment, fr)?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)?.addToBackStack(null)
                                    transaction?.commit()
                                }

                                override fun onButtonCvMenuClick(view: View, position: Int) {

                                }
                            }
                            rvAdapter = RecyclerViewAdapter(activity?.applicationContext!!, news.news!!, itemTouchListener!!)
                            recyclerView!!.adapter = rvAdapter
                        } else {
                            val fr = ErrorFragment()
                            val transaction = activity?.supportFragmentManager?.beginTransaction()
                            val bundle = Bundle()
                            bundle.putString("werror", "nonews")
                            fr.arguments = bundle
                            transaction?.replace(R.id.mainFragment, fr)
                            transaction?.commit()
                        }
                    }

                    override fun onFailure(call: Call<News>, t: Throwable) {
                        val fr = ErrorFragment()
                        val transaction = activity!!.supportFragmentManager.beginTransaction()
                        val bundle = Bundle()
                        bundle.putString("werror", "noconnection")
                        fr.arguments = bundle
                        transaction.replace(R.id.mainFragment, fr)
                        transaction.commit()
                        t.printStackTrace()
                    }
                })
        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
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
         * @return A new instance of fragment MainMainFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): MainMainFragment {
            val fragment = MainMainFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
