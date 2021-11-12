package com.moub.perday.ui.donationdetail
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moub.perday.databinding.ListDonationDetailBinding
import com.moub.perday.model.CategoryData
import com.moub.perday.model.DonationData



class DonationDetailAdapter(private val modelList: List<DonationData>,
                            val context: Context
) :
    RecyclerView.Adapter<DonationDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListDonationDetailBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(modelList[position])


    }



    override fun getItemCount(): Int = modelList.size

    inner class ViewHolder(private var binding: ListDonationDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: DonationData) {
            binding.let {
                it.model = model; it.viewModel = DonationDetailActivity.viewModel
            }
            binding.executePendingBindings()
        }
    }
}
