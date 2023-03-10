package com.android.abiturientsgu.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.abiturientsgu.domain.models.Event
import com.android.abiturientsgu.domain.repository.EventsRepo
import com.android.abiturientsgu.utils.Interests
import kotlinx.coroutines.launch

class EventsViewModel(private var repository: EventsRepo) : ViewModel() {

    var allEvents: MutableLiveData<List<Event>> = MutableLiveData<List<Event>>()

    //flowEvents
    var _events = MutableLiveData<List<Event>>()
    var events = _events as LiveData<List<Event>>

    var _tags = MutableLiveData<List<String>>()
    var tags = _tags as LiveData<List<String>>

    init {
        _tags.value = enumValues<Interests>().map { it.value }
        getEvents(_tags.value!!)
    }

    private fun getEvents(tags: List<String>) {

        viewModelScope.launch {
            repository.getEvents().collect {
                allEvents.value = it
                filterForTags(tags)
            }
        }
        //  return allEvents
    }

    private fun subscribeOnEvent(id: Int, login: String) {


    }

    fun deleteTag(tag: String) {
        _tags.value = _tags.value?.filter { it != tag }
        _tags.value?.let {
            filterForTags(it)
        }

    }

    fun setTags(tags: List<String>) {
        _tags.value = tags
        filterForTags(tags)
    }


    fun filterForTags(tags: List<String>) {

        val newList = mutableListOf<Event>()
        allEvents.value?.forEach { event ->
            tags.forEach { tag ->
                if (event.themes.contains(tag)) {
                    newList.add(event)
                }
            }

        }
        _events.value = newList.distinctBy { it.id }
    }


    /*  fun filterEvents(tags:List<String>){
          Log.d("OLOLO", "events in filterEvents "+_events.value?.joinToString(" "))

          _events.value = allEvents.filter {
              it.themes.containsAll(tags)
          }
          Log.d("OLOLO", "events after filterEvents "+_events.value?.joinToString(" "))

      }
  */


}
