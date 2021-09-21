package com.abora.nagwatask.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abora.nagwatask.databinding.ItemHomeMediaBinding
import com.abora.nagwatask.retrofitDataModel.MediaDataModel


class HomeMediaAdapter constructor(val list: List<MediaDataModel>, val itemClick: (Int,MediaDataModel,String) -> Unit) : RecyclerView.Adapter<HomeMediaAdapter.MyHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context = parent.context
        return MyHolder(
                ItemHomeMediaBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.mediaItemBinding.data = list[position]



    }


    inner class MyHolder(val mediaItemBinding: ItemHomeMediaBinding) :
        RecyclerView.ViewHolder(mediaItemBinding.root) {

        init {

            mediaItemBinding.root.setOnClickListener {
                itemClick.invoke(adapterPosition,list[adapterPosition],"root")
            }

        }

    }



}