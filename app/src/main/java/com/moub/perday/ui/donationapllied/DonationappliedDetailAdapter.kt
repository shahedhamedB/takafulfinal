package com.moub.perday.ui.donationapllied
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moub.perday.databinding.ListDonationAppliedDetailBinding
import com.moub.perday.databinding.ListDonationDetailBinding
import com.moub.perday.model.AppliedData
import com.moub.perday.model.CategoryData
import com.moub.perday.model.DonationData



class DonationappliedDetailAdapter(private val modelList: List<AppliedData>,
                                   val context: Context
) :
    RecyclerView.Adapter<DonationappliedDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListDonationAppliedDetailBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(modelList[position])


    }



    override fun getItemCount(): Int = modelList.size

    inner class ViewHolder(private var binding: ListDonationAppliedDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: AppliedData) {
            binding.let {
                it.model = model; it.viewModel = DonationappliedActivity.viewModel
            }
            binding.executePendingBindings()
        }
    }
}
