package com.android.abiturientsgu.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.abiturientsgu.R
import com.android.abiturientsgu.domain.models.Specialty
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_speciality_info.*


class SpecialityInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speciality_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val stringSpec = arguments?.getString("spec")
        val specialty = GsonBuilder().create().fromJson(stringSpec, Specialty::class.java)
        init(specialty)
    }

    private fun init(specialty: Specialty) {
        tv_code.text = specialty.code
        tv_title.text = specialty.specialty
        tv_form.text = specialty.form
        tv_years.text = specialty.years
        tv_descr.text = specialty.description

        tv_exams.text = specialty.exams.joinToString("\n\n")
        tv_profs.text = specialty.professions.joinToString("\n\n")
        tv_spher.text = specialty.spheres.joinToString("\n\n")
        tv_dis.text = specialty.subjects.joinToString("\n\n")


    }


}