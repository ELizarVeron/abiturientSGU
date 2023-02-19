package com.android.abiturientsgu.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.abiturientsgu.R
import com.android.abiturientsgu.domain.models.Specialty
import com.android.abiturientsgu.presentation.viewmodels.EventsViewModel
import kotlinx.android.synthetic.main.fragment_events.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class EventsFragment : Fragment() {
    private var adapter: EventsAdapter? = null
    private val viewModel by viewModel<EventsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = EventsAdapter(requireContext(),
            fun(id: Int) {
                /* viewModel.getSpeciality(id).let {
                     val stringSpeciality = GsonBuilder().create().toJson(it)
                     val bundle = bundleOf("spec" to stringSpeciality)
                     requireView().findNavController()
                         .navigate(R.id.action_specialtyFragment_to_specialityInfoFragment, bundle)
                 }*/

            })

        recycler_events.setHasFixedSize(true)
        recycler_events.setItemViewCacheSize(50);

        val layoutManager = LinearLayoutManager(activity)
        recycler_events.layoutManager = layoutManager
        recycler_events.setPadding(10, 30, 10, 30)
        recycler_events.adapter = adapter



        viewModel.eventResult.observe(viewLifecycleOwner) {
            adapter?.itemList = it
        }

    }

    private fun init(specialty: Specialty) {


    }

}