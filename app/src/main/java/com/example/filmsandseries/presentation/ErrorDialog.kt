package com.example.filmsandseries.presentation

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.ViewGroup
import com.example.filmsandseries.R
import com.example.filmsandseries.databinding.ErrorDialogBinding

class ErrorDialog(private val context: Context) {
    private val dialog: Dialog = Dialog(context)

    fun show(errorMessage: String) {
        val binding = ErrorDialogBinding.inflate((context as Activity).layoutInflater)
        dialog.setContentView(binding.root)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.setCancelable(false)
        binding.errorDescription.text = errorMessage
        dialog.show()

        binding.errorButton.setOnClickListener {
            dialog.dismiss()
        }
    }

}
