package com.moub.perday.ui.employee.publicEmployers

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.mlsdev.animatedrv.AnimatedRecyclerView
import com.moub.perday.R
import com.moub.perday.databinding.FragmentDonationBinding
import com.moub.perday.databinding.PublicFragmentBinding
import com.moub.perday.ui.employee.donation.DonationAdapter
import com.moub.perday.ui.employee.donation.DonationFragment
import com.moub.perday.ui.employee.donation.DonationViewModel


class Public : Fragment() {
    private lateinit var binding:PublicFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = PublicFragmentBinding.inflate(inflater, container, false)
       instance = this
        viewModel = ViewModelProvider(this).get(PublicViewModel::class.java)
        viewModel.getSavedAddresses().observe(viewLifecycleOwner, Observer {
            val adapter = PublicAdapter(it, requireContext())
            binding.recyclerpublic.layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
            binding.recyclerpublic.adapter = adapter
            adapter.notifyDataSetChanged()
        })


        return binding.root
    }

    companion object {
        lateinit var instance: Public
        lateinit var viewModel: PublicViewModel
    }







}