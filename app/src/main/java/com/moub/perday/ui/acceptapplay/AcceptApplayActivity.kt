package com.moub.perday.ui.acceptapplay

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moub.perday.R
import com.moub.perday.databinding.ActivityAcceptApplayBinding
import com.moub.perday.model.AppliedData
import com.moub.perday.model.CategoryData
import com.moub.perday.ui.donationdetail.DonationDetailActivity
import com.moub.perday.ui.donationdetail.DonationDetailAdapter
import com.moub.perday.ui.donationdetail.DonationDetailViewModel

class AcceptApplayActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAcceptApplayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_accept_applay)
       instance = this
       viewModel = ViewModelProvider(this).get(AcceptApplayViewModel::class.java)
        viewModel.getSavedAddresses().observe(this, Observer {
            val adapter = AcceptAppliedAdapter(it, this)
            binding.rvapplay.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            binding.rvapplay.adapter = adapter
            adapter.notifyDataSetChanged()
        })
    }
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, AcceptApplayActivity::class.java)
            context.startActivity(intent)
        }
        lateinit var instance: AcceptApplayActivity
        lateinit var applied: AppliedData

        lateinit var viewModel: AcceptApplayViewModel

    }
}