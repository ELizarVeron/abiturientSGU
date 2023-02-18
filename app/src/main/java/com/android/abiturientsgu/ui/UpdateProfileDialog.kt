package com.android.abiturientsgu.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import com.android.abiturientsgu.domain.models.Profile


class UpdateProfileDialog(
    context: Context,
    currentProfile: Profile,
    val onUpdate: (f: String, n: String, p: String, s: String, c: String, t: String) -> Unit
) : DialogFragment() {
    val et_family = EditText(context).apply {
        setText(currentProfile.lastname)
        hint = "Фамилия"
    }
    val et_name = EditText(context).apply {
        hint = "Имя"
        setText(currentProfile.name)
    }

    val et_patr = EditText(context).apply {
        hint = "Отчество"
        setText(currentProfile.patronymic)
    }

    val et_school = EditText(context).apply {
        hint = "Школа"
        setText(currentProfile.school)
    }
    val et_class = EditText(context).apply {
        hint = "Класс"
        setText(currentProfile.classs)
    }

    val et_tel = EditText(context).apply {
        hint = "Телефон"
        setText(currentProfile.tel)
        inputType = InputType.TYPE_CLASS_PHONE
    }

    val linearLayout = LinearLayout(context).apply {
        orientation = LinearLayout.VERTICAL
        addView(et_family)
        addView(et_name)
        addView(et_patr)
        addView(et_school)
        addView(et_class)
        addView(et_tel)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Изменение информации")
        builder.setView(
            linearLayout
        )

        builder.setPositiveButton("OK") { _, _ ->
            run {
                onUpdate(
                    et_family.text.toString(),
                    et_name.text.toString(),
                    et_patr.text.toString(),
                    et_school.text.toString(),
                    et_class.text.toString(),
                    et_tel.text.toString(),
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