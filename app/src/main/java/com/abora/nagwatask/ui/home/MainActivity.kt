package com.abora.nagwatask.ui.home

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.abora.nagwatask.R
import com.abora.nagwatask.base.BaseActivity
import com.abora.nagwatask.base.myToast
import com.abora.nagwatask.databinding.ActivityMainBinding
import com.abora.nagwatask.di.MyViewModelProviders
import com.abora.nagwatask.retrofitApi.RetrofitApi
import com.abora.nagwatask.retrofitDataModel.MediaDataModel
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.content.Intent

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.webkit.URLUtil


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
            recHomeMedia.adapter = HomeMediaAdapter(it) { pos, data, type ->
                when (type) {
                    "root" -> {
                        if (URLUtil.isValidUrl(data.url)){
                            progressBar.visibility = View.VISIBLE
                            takePermission(data)
                        }else{
                            myToast("File Url Not Valid")
                        }

                    }
                }
            }
        })
    }


    private fun takePermission(data: MediaDataModel) {
        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) { /*
                        if... */
                    if (report.areAllPermissionsGranted()) {
                        downloadVideo(data)
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) { /* ... */
                    token.continuePermissionRequest()
                }
            }).check()
    }


    fun downloadVideo(data: MediaDataModel) {
        val downloadUri = Uri.parse(data.url)
        val request: DownloadManager.Request = DownloadManager.Request(downloadUri)
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        request.setAllowedOverRoaming(false)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setTitle(data.name)
        request.setDescription(data.type)

        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_MOVIES, data.name)
        val downloadManager: DownloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager

        registerReceiver(onComplete, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

        if (isDownloading(this, downloadManager.enqueue(request))) {
            downloadManager.remove(downloadManager.enqueue(request))
            Toast.makeText(this, "Start Download", Toast.LENGTH_SHORT).show()
            return
        } else {
            Toast.makeText(this, "Start Download", Toast.LENGTH_SHORT).show()
        }
    }

    var onComplete: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(ctxt: Context, intent: Intent) {
            // your code
            Log.d("video download", "onComplete")
            progressBar.visibility = View.GONE

        }
    }

    fun isDownloading(context: Context, downloadId: Long): Boolean {
        return getStatus(context, downloadId) == DownloadManager.STATUS_RUNNING
    }

    fun getStatus(context: Context, downloadId: Long): Int {
        val downloadManager = context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        val query = DownloadManager.Query()
        query.setFilterById(downloadId) // filter your download by download Id
        val c: Cursor = downloadManager.query(query)
        if (c.moveToFirst()) {
            val status: Int = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS))
            c.close()
            Log.d("DOWNLOAD_STATUS", status.toString())
            return status
        }
        Log.d("AUTOMATION_DOWNLOAD", "DEFAULT")
        return -1
    }


}