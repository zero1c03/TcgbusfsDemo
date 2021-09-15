package com.weber.tcgbusfsdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.weber.tcgbusfsdemo.R
import com.weber.tcgbusfsdemo.data.NewsDetail
import com.weber.tcgbusfsdemo.databinding.ListItemTcgbusBinding

class TcgbusfsAdapter :
    ListAdapter<NewsDetail, TcgbusfsAdapter.TcgbusfsViewHolder>(TcgbusfsDiffCallback()) {

    var mContext: Context? = null

    class TcgbusfsViewHolder(
        private val binding: ListItemTcgbusBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val chtmessage = binding.tvChtmessage
        val starttime = binding.starttime
        val endtime = binding.endtime
        val updatetime = binding.updatetime
        val chtcontent = binding.content
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TcgbusfsViewHolder {
        mContext = parent.context
        return TcgbusfsViewHolder(
            ListItemTcgbusBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TcgbusfsViewHolder, position: Int) {
        holder.chtmessage.text =
            mContext?.getString(R.string.chtmessage, getItem(position)?.chtmessage)
        holder.starttime.text =
            mContext?.getString(R.string.starttime, getItem(position)?.starttime)
        holder.endtime.text =
            mContext?.getString(R.string.endtime, getItem(position)?.endtime)
        holder.updatetime.text =
            mContext?.getString(R.string.updatetime, getItem(position)?.updatetime)
        holder.chtcontent.text =
            mContext?.getString(R.string.content, getItem(position)?.content)
    }


    private class TcgbusfsDiffCallback : DiffUtil.ItemCallback<NewsDetail>() {
        override fun areItemsTheSame(oldItem: NewsDetail, newItem: NewsDetail): Boolean {
            return oldItem.chtmessage == newItem.chtmessage
        }

        override fun areContentsTheSame(oldItem: NewsDetail, newItem: NewsDetail): Boolean {
            return oldItem == newItem
        }

    }


}