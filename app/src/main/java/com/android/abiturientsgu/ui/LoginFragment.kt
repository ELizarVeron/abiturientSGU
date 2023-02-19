package com.android.abiturientsgu.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.android.abiturientsgu.R
import com.android.abiturientsgu.presentation.viewmodels.AuthViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel by viewModel<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("newlogin")?.let {
            et_login.setText(it)
        }

        b_login.setOnClickListener {
            viewModel.auth(
                et_login.text.toString(),
                et_pass.text.toString()
            )
        }
        b_register.setOnClickListener {
            requireView().findNavController()
                .navigate(R.id.action_loginFragment2_to_registrationFragment2)
        }

        viewModel.authResult.observe(viewLifecycleOwner) {
            it.onSuccess {
                startActivity(
                    Intent(
                        requireActivity(),
                        MainActivity::class.java
                    ).putExtra("login", viewModel.currentLogin)
                )

            }
        }

    }


}