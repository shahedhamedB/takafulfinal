package com.moub.perday.ui.employee.donation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moub.perday.databinding.ItemCategoryBinding

import com.moub.perday.model.CategoryData
import com.moub.perday.ui.appliedform.AppliedFormActivity
import com.moub.perday.ui.donationdetail.DonationDetailActivity
import com.moub.perday.utils.start

class DonationAdapter(
    private val items: List<CategoryData>,
    val context: Context
) : RecyclerView.Adapter<DonationAdapter.DeveloperViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeveloperViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        //val binding = ItemsBinding.inflate(layoutInflater, parent, false)
        val binding=ItemCategoryBinding.inflate(layoutInflater, parent, false)



        return DeveloperViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeveloperViewHolder, position: Int) {

        holder.bind(items[position])

    }

    override fun getItemCount(): Int = items.size


    inner class DeveloperViewHolder(private var binding:ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(model: CategoryData) {
            binding.let {
                it.model = model; it.viewModel = DonationFragment.viewModel; it.fragment =
                DonationFragment()
            }
            binding.model = model
//            binding.executePendingBindings()
            binding.mediaCardView.setOnClickListener {
                DonationDetailActivity.category = model
                context.start(DonationDetailActivity())
            }

        }
        }
}