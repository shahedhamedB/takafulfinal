package com.moub.perday.ui.employee.publicEmployers

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moub.perday.databinding.ItemPublicBinding
import com.moub.perday.model.AccoiatData

import com.moub.perday.ui.donationdetail.DonationDetailActivity
import com.moub.perday.utils.start

class PublicAdapter(
    private val items: List<AccoiatData>,
    val context: Context
) : RecyclerView.Adapter<PublicAdapter.DeveloperViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeveloperViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        //val binding = ItemsBinding.inflate(layoutInflater, parent, false)
        val binding=ItemPublicBinding.inflate(layoutInflater, parent, false)



        return DeveloperViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeveloperViewHolder, position: Int) {

        holder.bind(items[position])

    }

    override fun getItemCount(): Int = items.size


    inner class DeveloperViewHolder(private var binding:ItemPublicBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(model: AccoiatData) {
            binding.let {
                it.model = model; it.viewModel = Public.viewModel; it.fragment =
                Public()
            }
            binding.model = model
            binding.executePendingBindings()


        }
        }
}