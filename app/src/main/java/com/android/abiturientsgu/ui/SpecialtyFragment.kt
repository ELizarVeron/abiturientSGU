package com.android.abiturientsgu.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.abiturientsgu.R
import com.android.abiturientsgu.presentation.viewmodels.SpecialtiesViewModel
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_specialty.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class SpecialtyFragment : Fragment() {
    private var adapter: SpecialityAdapter? = null
    private val viewModel by viewModel<SpecialtiesViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specialty, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SpecialityAdapter(requireContext(),
            fun(id: Int) {
                viewModel.getSpeciality(id).let {
                    val stringSpeciality = GsonBuilder().create().toJson(it)
                    val bundle = bundleOf("spec" to stringSpeciality)
                    requireView().findNavController()
                        .navigate(R.id.action_specialtyFragment_to_specialityInfoFragment, bundle)
                }

            })

        recycler_spec.setHasFixedSize(true)
        recycler_spec.setItemViewCacheSize(50);

        val layoutManager = LinearLayoutManager(activity)
        recycler_spec.layoutManager = layoutManager
        recycler_spec.setPadding(10, 30, 10, 30)
        recycler_spec.adapter = adapter



        viewModel.specialties.observe(viewLifecycleOwner) {
            adapter?.itemList = it
        }
    }


}