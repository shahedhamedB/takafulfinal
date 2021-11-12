package com.moub.perday.ui.employee.donation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moub.perday.databinding.FragmentDonationBinding

class DonationFragment : Fragment() {
    private lateinit var binding: FragmentDonationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentDonationBinding.inflate(inflater, container, false)
        instance = this
        viewModel= ViewModelProvider(this).get(DonationViewModel::class.java)
        viewModel.getSavedAddresses().observe(viewLifecycleOwner, Observer {
            val adapter = DonationAdapter(it, requireContext())
            binding.recycler.layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
            binding.recycler.adapter = adapter
            adapter.notifyDataSetChanged()
        })


       // binding.donationFragment = this


        return binding.root
    }
    companion object {
        lateinit var instance: DonationFragment
        lateinit var viewModel: DonationViewModel
    }






}


