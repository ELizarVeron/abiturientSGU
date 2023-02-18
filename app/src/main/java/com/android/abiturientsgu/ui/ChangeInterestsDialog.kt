package com.android.abiturientsgu.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.android.abiturientsgu.utils.Interests
import com.android.abiturientsgu.utils.enumToBooleanArray
import com.android.abiturientsgu.utils.fromBooleanArrayToInterestsArray


class ChangeInterestsDialog(
    private val currentInterests: List<Interests>,
    val onSelectedInterests: (interests: List<Interests>) -> Unit
) : DialogFragment() {

    private val flags = enumToBooleanArray<Interests>(currentInterests)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Интересы")
        val interests = (enumValues<Interests>().map { it.value }).toTypedArray()

        builder.setMultiChoiceItems(
            interests, flags
        ) { dialog, which, isCheked ->

            flags[which] = isCheked
        }

        builder.setPositiveButton("OK") { _, _ ->
            run {

                onSelectedInterests(
                    fromBooleanArrayToInterestsArray(flags)
                )
            }

        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            run {
                dialog.cancel()
            }
        }
        return builder.create()
    }
}