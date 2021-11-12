package com.moub.perday.ui.donationdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moub.perday.R
import com.moub.perday.databinding.ActivityDonationDetailBinding
import com.moub.perday.model.CategoryData
import com.moub.perday.ui.employee.donation.DonationFragment

class DonationDetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDonationDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_donation_detail)
        instance = this
        viewModel = ViewModelProvider(this).get(DonationDetailViewModel::class.java)
        viewModel.getSavedAddresses(category.name!!).observe(this, Observer {
            val adapter = DonationDetailAdapter(it, this)
            binding.rvList.layoutManager = LinearLayoutManager(this,  RecyclerView.VERTICAL, false)
            binding.rvList.adapter = adapter
            adapter.notifyDataSetChanged()
        })

    }
    companion object {
        lateinit var category: CategoryData
        lateinit var instance: DonationDetailActivity
        lateinit var viewModel: DonationDetailViewModel
    }

}