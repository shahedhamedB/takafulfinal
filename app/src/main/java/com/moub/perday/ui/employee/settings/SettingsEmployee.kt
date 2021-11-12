package com.moub.perday.ui.employee.settings

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.moub.perday.R
import com.moub.perday.ui.acceptapplay.AcceptApplayActivity
import com.moub.perday.ui.adddonation.AddDonationActivity
import com.moub.perday.ui.appliedform.AppliedFormActivity
import com.moub.perday.ui.employee.associations.AssociationActivity

import kotlinx.android.synthetic.main.settings_employee_fragment.*

class SettingsEmployee : Fragment() {



    private lateinit var viewModel: SettingsEmployeeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_employee_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingsEmployeeViewModel::class.java)
        signOutBtn.setOnClickListener{
            FirebaseAuth.getInstance().signOut()


        }

        addDonation.setOnClickListener {
            AddDonationActivity.start(requireContext())
        }
        appliedform.setOnClickListener {
            AppliedFormActivity.start(requireContext())
        }
        acceptapplied.setOnClickListener {
            AcceptApplayActivity.start(requireContext())
        }
        associationsbtn.setOnClickListener {
            AssociationActivity.start(requireContext())
        }


    }

}