package tk.lorddarthart.rxnewstestapp.app.view.fragment.general.main.pages.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import tk.lorddarthart.rxnewstestapp.app.App
import tk.lorddarthart.rxnewstestapp.app.view.base.BaseFragment
import tk.lorddarthart.rxnewstestapp.app.viewmodel.fragment.general.main.pages.main.MainPageViewModel
import tk.lorddarthart.rxnewstestapp.databinding.PageMainBinding

class MainPage : BaseFragment() {
    private lateinit var mainPageBinding: PageMainBinding

    private val mainPageViewModel: MainPageViewModel by lazy {
        ViewModelProvider(
                this
        )[MainPageViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        mainPageBinding = PageMainBinding.inflate(inflater, container, false)

        initialization()

        return mainPageBinding.root
    }

    override fun hangObservers() {
        // do something
    }

    override fun initListeners() {
        // do something
    }

    override fun start() {
        val layoutManager = LinearLayoutManager(App.instance)
        // TODO: separate logic and presentation
//        retrofit = RetrofitClient.getInstance()
//        compositeDisposable = CompositeDisposable()
//        if (::retrofit.isInitialized) {
//            myApi = retrofit.create(GetNewsList::class.java)
//        }
//        pullRefreshLayout.setOnRefreshListener {
//            fetchData()
//        }
//        if (::recyclerView.isInitialized) {
//            recyclerView.layoutManager = layoutManager
//        }
//
//        pullRefreshLayout.setRefreshing(true)
//        fetchData()
    }

//    private fun fetchData() {
//        if (::compositeDisposable.isInitialized&&::myApi.isInitialized) {
//            compositeDisposable.add(myApi.getPostWithID("1wozWr5swgtdV9PLyo2b09mtjaOD6sS2I")
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//            !!.subscribe({ news ->
//                displayData(news)
//            }, { error ->
//                displayError(error)
//            }, {
//
//            }))
//        }
//    }
//
//    private fun displayData(news: News) {
//        news.news?.let {
//            if (it.isNotEmpty()) {
//                itemTouchListener = object : OnItemTouchListener {
//                    override fun onCardViewTap(view: View, position: Int) {
//                        val bundle = Bundle()
//                        bundle.putString("date", news.news!![position].date)
//                        bundle.putString("title", news.news!![position].title)
//                        bundle.putString("desc", news.news!![position].desc)
//                        bundle.putString("pic", news.news!![position].pic)
//                        val fr = FullInfoFragment()
//                        val transaction = baseActivity
//                                .supportFragmentManager.beginTransaction()
//                        fr.arguments = bundle
//                        transaction.add(R.id.general_nav_host_main, fr).addToBackStack(null)
//                        transaction.commit()
//                    }
//
//                    override fun onButtonCvMenuClick(view: View, position: Int) {
//
//                    }
//                }
//                if (::itemTouchListener.isInitialized) {
//                    recyclerViewAdapter = RecyclerViewAdapter(baseActivity, it, itemTouchListener)
//                }
//                if (::recyclerView.isInitialized) {
//                    recyclerView.adapter = recyclerViewAdapter
//                }
//                if (::pullRefreshLayout.isInitialized) {
//                    pullRefreshLayout.setRefreshing(false)
//                }
//            } else {
//                val fr = ErrorFragment()
//                val transaction = baseActivity
//                        .supportFragmentManager.beginTransaction()
//                val bundle = Bundle()
//                bundle.putString("werror", "nonews")
//                fr.arguments = bundle
//                transaction.replace(R.id.general_nav_host_main, fr)
//                transaction.commit()
//
//                if (::pullRefreshLayout.isInitialized) {
//                    pullRefreshLayout.setRefreshing(false)
//                }
//            }
//        }
//    }
//
//    private fun displayError(e: Throwable) {
//        val fr = ErrorFragment()
//        val transaction = baseActivity.supportFragmentManager.beginTransaction()
//        val bundle = Bundle()
//        bundle.putString("werror", "noconnection")
//        fr.arguments = bundle
//        transaction.replace(R.id.general_nav_host_main, fr)
//        transaction.commit()
//        e.message?.let {
//            Log.e(TAG, it)
//        }
//
//        if (::pullRefreshLayout.isInitialized) {
//            pullRefreshLayout.setRefreshing(false)
//        }
//    }
//
//    override fun onStop() {
//        if (::compositeDisposable.isInitialized) {
//            compositeDisposable.clear()
//        }
//        super.onStop()
//    }
}
