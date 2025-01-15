package com.example.bininfo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bininfo.databinding.RequestLayoutBinding
import com.example.bininfo.model.Request

class RequestAdapter : RecyclerView.Adapter<RequestAdapter.RequestViewHolder>() {
    class RequestViewHolder(val itemBinding: RequestLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Request>() {
        override fun areItemsTheSame(oldItem: Request, newItem: Request): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.bin == newItem.bin &&
                    oldItem.scheme == newItem.scheme &&
                    oldItem.type == newItem.type
        }

        override fun areContentsTheSame(oldItem: Request, newItem: Request): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        return RequestViewHolder(
            RequestLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        val currentRequest = differ.currentList[position]

        holder.itemBinding.binNumber.text = "BIN: ${currentRequest.bin}"
        holder.itemBinding.generalData.text = "Счёт: ${currentRequest.scheme}; ${currentRequest.type}"
        holder.itemBinding.countryData.text = "Страна: ${currentRequest.countryName}; ${currentRequest.countryCurrency}"
        holder.itemBinding.bankData.text = "Банк: ${currentRequest.bankName}"
    }
}