package com.android.abiturientsgu.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.abiturientsgu.R
import com.android.abiturientsgu.domain.models.Profile
import com.android.abiturientsgu.domain.models.User
import com.android.abiturientsgu.presentation.viewmodels.AuthViewModel
import com.android.abiturientsgu.presentation.viewmodels.ProfileViewModel
import com.android.abiturientsgu.utils.Interests
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment() {
    private val viewModel by viewModel<ProfileViewModel>()
    private val viewModelAuth by viewModel<AuthViewModel>()

    private var interests: List<Interests> = emptyList()
    private var login = ""
    lateinit var profile: Profile

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val s = (activity as MainActivity).login

        viewModel.getProfile(s)

        b_logout.setOnClickListener {
            logout()
        }

        b_edit_interests.setOnClickListener {
            alertInterests()
        }

        b_updateprofile.setOnClickListener {
            alertUpdateProfile()
        }

        b_edit_pass.setOnClickListener {
            alertChangePass()
        }



        viewModel.profileResult.observe(viewLifecycleOwner) { prof ->

            val filteredThemes =
                prof.themes?.filter { str -> enumValues<Interests>().any { it.value == str } }

            filteredThemes?.map { s -> enumValues<Interests>().first { it.value == s } }
                ?.let { interests = it }


            login = prof.login
            profile = prof

            family.text = prof.lastname
            name.text = prof.name
            patr.text = prof.patronymic
            school.text = prof.school
            classs.text = prof.classs
            tel.text = prof.tel

            inters.text = prof.themes.toString()

        }

        viewModel.resultChangePass.observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.resultUpdateProfile.observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun alertInterests() {
        ChangeInterestsDialog(
            interests,
            ::changeInterests
        ).show(requireActivity().supportFragmentManager, "i_dialog")
    }

    private fun alertChangePass() {
        ChangePassDialog(
            requireContext(),
            ::changePass
        ).show(requireActivity().supportFragmentManager, "pass_dialog")
    }

    private fun alertUpdateProfile() {
        UpdateProfileDialog(
            requireContext(),
            profile,
            ::updateProfile,
        ).show(requireActivity().supportFragmentManager, "update_dialog")
    }

    private fun updateProfile(f: String, n: String, p: String, s: String, c: String, t: String) {
        viewModel.updateProfile(
            Profile(
                login = login,
                lastname = f,
                name = n,
                patronymic = p,
                school = s,
                classs = c,
                tel = t,
                null
            )
        )
    }

    private fun changePass(old: User.Password, new: User.Password) {
        viewModel.updatePass(login, old, new)
    }

    private fun changeInterests(newInterests: List<Interests>) {
        viewModel.updateInterests(login, newInterests)
    }


    fun logout() {
        viewModelAuth.logout()
        requireActivity().startActivity(
            Intent(requireContext(), AuthActivity::class.java)
        )
    }
}