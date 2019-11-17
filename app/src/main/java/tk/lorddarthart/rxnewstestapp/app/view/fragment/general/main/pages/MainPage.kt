package tk.lorddarthart.rxnewstestapp.app.view.fragment.general.main.pages

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baoyz.widget.PullRefreshLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import tk.lorddarthart.rxnewstestapp.R
import tk.lorddarthart.rxnewstestapp.app.model.request.News
import tk.lorddarthart.rxnewstestapp.app.view.base.BaseFragment
import tk.lorddarthart.rxnewstestapp.app.view.fragment.general.main.error.ErrorFragment
import tk.lorddarthart.rxnewstestapp.app.view.fragment.general.main.item.FullInfoFragment
import tk.lorddarthart.rxnewstestapp.util.helper.OnItemTouchListener
import tk.lorddarthart.rxnewstestapp.app.view.adapter.RecyclerViewAdapter
import tk.lorddarthart.rxnewstestapp.util.network.retrofit.GetNewsList
import tk.lorddarthart.rxnewstestapp.util.network.retrofit.RetrofitClient


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MainPage.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MainPage.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainPage : BaseFragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var listener: OnFragmentInteractionListener
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var itemTouchListener: OnItemTouchListener
    private lateinit var pullRefreshLayout: PullRefreshLayout
    private lateinit var compositeDisposable: CompositeDisposable
    private lateinit var myApi: GetNewsList
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { arguments ->
            param1 = arguments.getString(ARG_PARAM1)
            param2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        mainView = inflater.inflate(R.layout.page_main, container, false)

        initViews()
        setContent()

        return mainView
    }

    override fun initViews() {
        super.initViews()
        with(mainView) {
            pullRefreshLayout = findViewById(R.id.pull2refresh)
            recyclerView = findViewById(R.id.rvMainMain)
        }
    }

    override fun setContent() {
        super.setContent()
        layoutManager = LinearLayoutManager(baseActivity.applicationContext)
        retrofit = RetrofitClient.getInstance()
        compositeDisposable = CompositeDisposable()
        if (::retrofit.isInitialized) {
            myApi = retrofit.create(GetNewsList::class.java)
        }
        pullRefreshLayout.setOnRefreshListener {
            fetchData()
        }
        if (::recyclerView.isInitialized) {
            recyclerView.layoutManager = layoutManager
        }

        pullRefreshLayout.setRefreshing(true)
        fetchData()
    }

    private fun fetchData() {
        if (::compositeDisposable.isInitialized&&::myApi.isInitialized) {
            compositeDisposable.add(myApi.getPostWithID("1wozWr5swgtdV9PLyo2b09mtjaOD6sS2I")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            !!.subscribe({ news ->
                displayData(news)
            }, { error ->
                displayError(error)
            }, {

            }))
        }
    }

    private fun displayData(news: News) {
        news.news?.let {
            if (it.isNotEmpty()) {
                itemTouchListener = object : OnItemTouchListener {
                    override fun onCardViewTap(view: View, position: Int) {
                        val bundle = Bundle()
                        bundle.putString("date", news.news!![position].date)
                        bundle.putString("title", news.news!![position].title)
                        bundle.putString("desc", news.news!![position].desc)
                        bundle.putString("pic", news.news!![position].pic)
                        val fr = FullInfoFragment()
                        val transaction = baseActivity
                                .supportFragmentManager.beginTransaction()
                        fr.arguments = bundle
                        transaction.add(R.id.general_nav_host_main, fr).addToBackStack(null)
                        transaction.commit()
                    }

                    override fun onButtonCvMenuClick(view: View, position: Int) {

                    }
                }
                if (::itemTouchListener.isInitialized) {
                    recyclerViewAdapter = RecyclerViewAdapter(baseActivity, it, itemTouchListener)
                }
                if (::recyclerView.isInitialized) {
                    recyclerView.adapter = recyclerViewAdapter
                }
                if (::pullRefreshLayout.isInitialized) {
                    pullRefreshLayout.setRefreshing(false)
                }
            } else {
                val fr = ErrorFragment()
                val transaction = baseActivity
                        .supportFragmentManager.beginTransaction()
                val bundle = Bundle()
                bundle.putString("werror", "nonews")
                fr.arguments = bundle
                transaction.replace(R.id.general_nav_host_main, fr)
                transaction.commit()

                if (::pullRefreshLayout.isInitialized) {
                    pullRefreshLayout.setRefreshing(false)
                }
            }
        }
    }

    private fun displayError(e: Throwable) {
        val fr = ErrorFragment()
        val transaction = baseActivity.supportFragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putString("werror", "noconnection")
        fr.arguments = bundle
        transaction.replace(R.id.general_nav_host_main, fr)
        transaction.commit()
        e.message?.let {
            Log.e(TAG, it)
        }

        if (::pullRefreshLayout.isInitialized) {
            pullRefreshLayout.setRefreshing(false)
        }
    }

    override fun onStop() {
        if (::compositeDisposable.isInitialized) {
            compositeDisposable.clear()
        }
        super.onStop()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
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
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        private const val TAG = "MainFragmentMainPage"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragmentMainPage.
         */
        fun newInstance(param1: String, param2: String): MainPage {
            val fragment = MainPage()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
