package com.moub.perday.ui.donationapllied

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moub.perday.R
import com.moub.perday.databinding.ActivityDonationDetail2Binding
import com.moub.perday.model.CategoryData
import com.moub.perday.ui.donationdetail.DonationDetailActivity
import com.moub.perday.ui.donationdetail.DonationDetailAdapter
import com.moub.perday.ui.donationdetail.DonationDetailViewModel
import kotlinx.android.synthetic.main.activity_accept_applay.*

class DonationappliedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDonationDetail2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_donation_detail2)
       instance = this
        viewModel = ViewModelProvider(this).get( DonationappliedDetailViewModel::class.java)
       viewModel.getSavedAddresses(category.name!!).observe(this, Observer {
            val adapter = DonationappliedDetailAdapter(it, this)
            binding.rvdonationappli.layoutManager = LinearLayoutManager(this,  RecyclerView.VERTICAL, false)
            binding.rvdonationappli.adapter = adapter
            adapter.notifyDataSetChanged()
        })
    }
    companion object {
        lateinit var category: CategoryData
        lateinit var instance: DonationappliedActivity
        lateinit var viewModel: DonationappliedDetailViewModel
    }
}