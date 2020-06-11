package com.example.fastfooddiet.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.example.fastfooddiet.R
import com.example.fastfooddiet.viewmodels.SharedViewModel

class TextInputDialog : DialogFragment() {

    //**** PROPERTIES ****
    private val args : TextInputDialogArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        //Get ViewModel
        val sharedViewModel : SharedViewModel by navGraphViewModels(args.viewModelGraphID)

        return activity?.let {

            val view = it.layoutInflater.inflate(R.layout.dialog_text_input, null)
            val editText = view.findViewById<EditText>(R.id.dialog_editText)

            val builder = AlertDialog.Builder(it)
            builder.setTitle(args.title)
                .setView(view)
                .setPositiveButton("Ok",
                    DialogInterface.OnClickListener { dialog, id ->
                        val text = editText.text.toString()
                        sharedViewModel.textChanged = true
                        sharedViewModel.textInput.value = text
                    })
                .setNegativeButton("Cancel") { dialog, id -> }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    //**** METHODS ****
}