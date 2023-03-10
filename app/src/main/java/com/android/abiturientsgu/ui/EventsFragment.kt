package com.android.abiturientsgu.ui

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.CheckBox
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.abiturientsgu.R
import com.android.abiturientsgu.presentation.viewmodels.EventsViewModel
import kotlinx.android.synthetic.main.fragment_events.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class EventsFragment : Fragment() {
    private var eventsAdapter: EventsAdapter? = null
    private var tagsAdapter: HashTagAdapter? = null
    private val viewModel by viewModel<EventsViewModel>()
    // var filter:MutableList<String> =  enumValues<Interests>().map { it.value } as MutableList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        createRecyclerForEvents()
        createRecyclerForTags()

        viewModel.events.observe(viewLifecycleOwner) {
            if (it != null) {
                eventsAdapter?.itemList = it
                Log.d("OLOLO", it.toString())
            }
        }

        viewModel.tags.observe(viewLifecycleOwner) {
            if (it != null) {
                tagsAdapter?.itemList = it

                Log.d("OLOLO", "OLOLO + " + it.toString())
            }
        }

        burger_button.setOnClickListener {
            showDialog(viewModel.tags.value ?: listOf())
        }


    }

    private fun createRecyclerForEvents() {
        eventsAdapter = EventsAdapter(requireContext(),
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
        recycler_events.adapter = eventsAdapter
    }

    private fun createRecyclerForTags() {
        tagsAdapter = HashTagAdapter(requireContext(),
            //функция удаления тега из верзнего реацйклера
            fun(deletedInterest: String) {
                viewModel.deleteTag(deletedInterest)

                /* viewModel.getSpeciality(id).let {
                     val stringSpeciality = GsonBuilder().create().toJson(it)
                     val bundle = bundleOf("spec" to stringSpeciality)
                     requireView().findNavController()
                         .navigate(R.id.action_specialtyFragment_to_specialityInfoFragment, bundle)
                 }*/

            })

        recycler_tags.setHasFixedSize(true)
        recycler_tags.setItemViewCacheSize(50);

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = RecyclerView.HORIZONTAL
        recycler_tags.layoutManager = layoutManager
        recycler_tags.setPadding(10, 30, 10, 30)
        recycler_tags.adapter = tagsAdapter
    }


    private fun showDialog(tags: List<String>) {
        val dialog = Dialog(requireActivity())

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)

        dialog.setContentView(R.layout.tags_dialog)
        dialog.window?.setDimAmount(0.9F)


        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(true)

        dialog.setOnShowListener {
            for (view in dialog.findViewById<ConstraintLayout>(R.id.constraint_tags).children) {
                if (view is CheckBox) {
                    if (tags.contains(view.text)) {
                        view.isChecked = true
                    }
                }
            }
        }


        dialog.setOnCancelListener {

            Log.d("OLOLO", dialog.findViewById<CheckBox>(R.id.cb_it).isChecked.toString())

            val mutableListOfTags = mutableListOf<String>()

            for (view in dialog.findViewById<ConstraintLayout>(R.id.constraint_tags).children) {
                if (view is CheckBox) {
                    if (view.isChecked) {
                        mutableListOfTags.add(view.text as String)
                    }
                }
            }
            viewModel.setTags(mutableListOfTags)
            // viewModel.filterForTags(mutableListOfTags)

        }


        /*val body = dialog.findViewById(R.id.body) as TextView
        body.text = title
        val yesBtn = dialog.findViewById(R.id.yesBtn) as Button
        val noBtn = dialog.findViewById(R.id.noBtn) as Button*/
        /*   yesBtn.setOnClickListener {
               dialog.dismiss()
           }
           noBtn.setOnClickListener {
               dialog.dismiss()
           }*/
        dialog.show()

    }



}