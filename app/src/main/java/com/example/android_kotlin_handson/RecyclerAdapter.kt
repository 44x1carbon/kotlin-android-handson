package com.example.android_kotlin_handson

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.android_kotlin_handson.model.Repository

class RecyclerAdapter internal constructor(
        mContext: Context,
        private val mRepositoryList: List<Repository>
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private val mInflater: LayoutInflater = LayoutInflater.from(mContext)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(mInflater.inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mRepositoryList.size > position) {
            val (fullName) = mRepositoryList[position]
            holder.textView.text = fullName
        }
    }

    override fun getItemCount(): Int {
        return mRepositoryList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById<View>(R.id.repositoryName) as TextView
    }
}
