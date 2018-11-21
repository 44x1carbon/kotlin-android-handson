package com.example.android_kotlin_handson

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.android_kotlin_handson.model.Repository

class RecyclerAdapter internal constructor(private val mContext: Context, private val mRepositoryList: List<Repository>?) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private val mInflater: LayoutInflater

    init {
        mInflater = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(mInflater.inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mRepositoryList != null && mRepositoryList.size > position && mRepositoryList[position] != null) {
            val (fullName) = mRepositoryList[position]
            holder.textView.text = fullName
        }
    }

    override fun getItemCount(): Int {
        return mRepositoryList?.size ?: 0
    }

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textView: TextView

        init {
            textView = itemView.findViewById<View>(R.id.repositoryName) as TextView
        }
    }
}
