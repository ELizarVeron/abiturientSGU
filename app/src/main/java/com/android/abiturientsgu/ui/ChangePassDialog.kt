package com.android.abiturientsgu.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.android.abiturientsgu.domain.models.User


class ChangePassDialog(
    context: Context,
    val onCreateNewPass: (old: User.Password, new: User.Password) -> Unit
) : DialogFragment() {
    val et_old = EditText(context).apply {
        hint = "Старый пароль"
        transformationMethod = PasswordTransformationMethod.getInstance();


    }
    val et_new = EditText(context).apply {
        hint = "Новый пароль"
        transformationMethod = PasswordTransformationMethod.getInstance();

    }

    val et_new2 = EditText(context).apply {
        hint = "Еще разик"
        transformationMethod = PasswordTransformationMethod.getInstance()
    }

    private val linearLayout = LinearLayout(context).apply {
        orientation = LinearLayout.VERTICAL
        addView(et_old)
        addView(et_new)
        addView(et_new2)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Смена пароля")

        builder.setView(
            linearLayout
        )

        builder.setPositiveButton("OK") { _, _ ->
            if (et_new.text.toString() != et_new2.text.toString()) {
                Toast.makeText(requireContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }

            run {
                onCreateNewPass(
                    User.Password.createTESTFUN(et_old.text.toString()),
                    User.Password.createTESTFUN(et_new.text.toString()),
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