package com.moub.perday.ui.acceptapplay
import android.R
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import androidx.recyclerview.widget.RecyclerView
import com.moub.perday.databinding.AcceptedItemBinding
import com.moub.perday.model.AppliedData


class AcceptAppliedAdapter(
    private val modelList: List<AppliedData>,
    val context: Context
) :
    RecyclerView.Adapter<AcceptAppliedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AcceptedItemBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(modelList[position])


    }



    override fun getItemCount(): Int = modelList.size

    inner class ViewHolder(private var binding: AcceptedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: AppliedData) {
            binding.let {
                it.model = model; it.viewModel = AcceptApplayActivity.viewModel
            }
            binding.executePendingBindings()
            binding.acceptBtn.setOnClickListener {


            }
        }
    }
}
