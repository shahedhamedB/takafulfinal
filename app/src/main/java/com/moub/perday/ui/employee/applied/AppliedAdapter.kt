package com.moub.perday.ui.employee.applied

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.moub.perday.databinding.ItemAppliedBinding

import com.moub.perday.model.CategoryData
import com.moub.perday.ui.donationapllied.DonationappliedActivity
import com.moub.perday.ui.donationdetail.DonationDetailActivity
import com.moub.perday.utils.start

class AppliedAdapter(
    private val items: List<CategoryData>,
    val context: Context
) : RecyclerView.Adapter<AppliedAdapter.DeveloperViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeveloperViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        //val binding = ItemsBinding.inflate(layoutInflater, parent, false)
        val binding=ItemAppliedBinding.inflate(layoutInflater, parent, false)



        return DeveloperViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeveloperViewHolder, position: Int) {

        holder.bind(items[position])

    }

    override fun getItemCount(): Int = items.size


    inner class DeveloperViewHolder(private var binding: ItemAppliedBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(model: CategoryData) {
            binding.model = model
            binding.executePendingBindings()
            binding.cardView.setOnClickListener {
                DonationappliedActivity.category = model
                context.start(DonationappliedActivity())
            }

        }
        }
}