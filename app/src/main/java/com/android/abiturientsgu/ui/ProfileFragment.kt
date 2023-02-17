package com.android.abiturientsgu.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.abiturientsgu.R
import com.android.abiturientsgu.presentation.viewmodels.AuthViewModel
import com.android.abiturientsgu.presentation.viewmodels.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {
    private val viewModel by viewModel<ProfileViewModel>()
    private val viewModelAuth by viewModel<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("OLOLO", "ProfileFragment onViewCreated")

        val s = (activity as MainActivity).login
        Log.d("OLOLO", s)
        viewModel.getProfile(s)

        b_logout.setOnClickListener {
            logout()
        }


        viewModel.profileResult.observe(viewLifecycleOwner) {

        }

        viewModel.profileResult.observe(viewLifecycleOwner) {

            family.text = it.lastname
            name.text = it.name
            patr.text = it.patronymic
            school.text = it.school
            classs.text = it.classs
            tel.text = it.tel

        }


    }


    fun logout() {
        viewModelAuth.logout()
        requireActivity().startActivity(
            Intent(requireContext(), AuthActivity::class.java)
        )
    }
}