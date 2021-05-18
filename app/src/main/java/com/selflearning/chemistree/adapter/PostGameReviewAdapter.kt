package com.selflearning.chemistree.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.selflearning.chemistree.adapter.PostGameReviewAdapter.PostGameReviewViewHolder
//import com.selflearning.chemistree.databinding.RvPostGameReportBinding
import com.selflearning.chemistree.models.ReportModels
import java.util.*

class PostGameReviewAdapter(private val arrayList: ArrayList<ReportModels>) : RecyclerView.Adapter<PostGameReviewViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostGameReviewViewHolder {
//        val binding = RvPostGameReportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostGameReviewViewHolder(parent)
    }

    override fun onBindViewHolder(holder: PostGameReviewViewHolder, position: Int) {
        val reportModels = arrayList[position]
        holder.bind(reportModels)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class PostGameReviewViewHolder(private val parent: ViewGroup) : RecyclerView.ViewHolder(parent) {

        fun bind(model: ReportModels){
//            binding.tvReportDesc.text = HtmlCompat.fromHtml(model.correctDesc, HtmlCompat.FROM_HTML_MODE_LEGACY)
//            binding.tvReportName.text = Html.fromHtml(model.correctName)
        }
    }
}