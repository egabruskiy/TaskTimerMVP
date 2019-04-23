package com.egabruskiy.tasktimer.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.egabruskiy.tasktimer.mvp.presenter.DayListPresenter
import com.egabruskiy.tasktimer.mvp.view.DayListView
import com.egabruskiy.tasktimer.R
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_day_list.*



class DayListFragment : MvpAppCompatFragment(), DayListView {

    @InjectPresenter
    lateinit var dayListPresenter: DayListPresenter
    lateinit var rvAdapter : MyItemRecyclerViewAdapter
    lateinit var rV :RecyclerView

    @ProvidePresenter
    fun provideMainPresenter(): DayListPresenter  = DayListPresenter(AndroidSchedulers.mainThread())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_day_list, container, false)

        rV = view.findViewById(R.id.rv_list)
        rV.layoutManager = LinearLayoutManager(context)
        rvAdapter = MyItemRecyclerViewAdapter(dayListPresenter.repoListPresenter)
        rV.setHasFixedSize(true)
        rV.adapter = rvAdapter

        return view
    }

    override fun init() {
        rvAdapter.notifyDataSetChanged()
    }


    override fun updateRepoList() {
        rvAdapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance()= DayListFragment()
        }
}

