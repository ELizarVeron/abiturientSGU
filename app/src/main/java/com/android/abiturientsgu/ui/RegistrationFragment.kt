package com.android.abiturientsgu.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.android.abiturientsgu.R
import com.android.abiturientsgu.presentation.viewmodels.RegistrationViewModel
import kotlinx.android.synthetic.main.fragment_registration.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment() {

    private val viewModel by viewModel<RegistrationViewModel>()
    private val themes = emptyList<String>()
    private var login = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.registerResult.observe(viewLifecycleOwner) { result ->
            result.onSuccess {
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()


                val bundle = bundleOf("newlogin" to login)
                requireView().findNavController()
                    .navigate(R.id.action_registrationFragment2_to_loginFragment2, bundle)
            }
            result.onFailure {
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            }

        }


        buttonRegister.setOnClickListener {
            validation(RegisterPassword.text.toString(), RegisterPassword2.text.toString())
                .onSuccess {
                    login = et_newlogin.text.toString()

                    viewModel.createNewUser(
                        et_newlogin.text.toString(),
                        et_log.text.toString(),
                        et_name.text.toString(),
                        et_patr.text.toString(),
                        et_school.text.toString(),
                        et_class.text.toString(),
                        et_tel.text.toString(),
                        themes,
                        RegisterPassword.text.toString(),

                        )
                }
                .onFailure {
                    Toast.makeText(requireContext(), "Пароли не совпадют", Toast.LENGTH_SHORT)
                        .show()
                }

        }

        buttonInteres.setOnClickListener {


        }


    }

    private fun validation(pass1: String, pass2: String): Result<String> =
        if (pass1 != pass2) Result.failure(exception = Throwable("Пароли не совпадают"))
        else Result.success("ok")
}


