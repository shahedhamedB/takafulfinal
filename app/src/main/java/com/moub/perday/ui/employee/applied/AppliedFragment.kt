package com.moub.perday.ui.employee.applied

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.moub.perday.R
import com.moub.perday.databinding.FragmentAppliedBinding
import com.moub.perday.databinding.FragmentDonationBinding
import com.moub.perday.ui.employee.donation.DonationAdapter
import com.moub.perday.ui.employee.donation.DonationViewModel


class AppliedFragment : Fragment() {
    private lateinit var binding:FragmentAppliedBinding
    private lateinit var appliedViewModel: AppliedViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentAppliedBinding.inflate(inflater, container, false)
        appliedViewModel= ViewModelProvider(this).get(AppliedViewModel::class.java)
        appliedViewModel.getSavedAddresses().observe(viewLifecycleOwner, Observer {
            val adapter = AppliedAdapter(it, requireContext())
            binding.recycler.layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
            binding.recycler.adapter = adapter
            adapter.notifyDataSetChanged()
        })


        binding.appliedFragment = this
        return binding.root
    }



}