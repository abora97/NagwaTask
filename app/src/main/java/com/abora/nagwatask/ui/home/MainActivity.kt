package com.abora.nagwatask.ui.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.abora.nagwatask.R
import com.abora.nagwatask.base.BaseActivity
import com.abora.nagwatask.base.myToast
import com.abora.nagwatask.databinding.ActivityMainBinding
import com.abora.nagwatask.di.MyViewModelProviders
import com.abora.nagwatask.retrofitApi.RetrofitApi
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    @Inject
    lateinit var myViewModelProviders: MyViewModelProviders

    @Inject
    lateinit var retrofitApi: RetrofitApi

    lateinit var homeViewModelImp: HomeViewModelImp

    override fun resourceId(): Int = R.layout.activity_main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModelImp =
            ViewModelProviders.of(this, myViewModelProviders)[HomeViewModelImp::class.java]
        homeViewModelImp.getMoviesData()

        observe()

        clicks()
    }

    private fun clicks() {

    }

    private fun observe() {
        homeViewModelImp.progressStatus.observe(this, Observer {
            toggleLoadingDialog(it)
        })

        homeViewModelImp.showAlert.observe(this, Observer {
            myToast(it)
        })

        homeViewModelImp.moviesDataLoaded.observe(this, Observer {
            tvHome.text=it.toString()
        })
    }

}