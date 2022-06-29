package com.genxaswhiz.notbiance.Lib

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import com.genxaswhiz.notbiance.R
import com.genxaswhiz.notbiance.databinding.DialogLibBinding
import kotlinx.android.synthetic.main.dialog_lib.view.*

class InitialDialogFragment : DialogFragment() {

    companion object {
        fun newInstance() = InitialDialogFragment()
    }

    private val binding by lazy {
        DialogLibBinding.inflate(LayoutInflater.from(context))
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        }
    //        val screenSize = this.resources.displayMetrics
//        dialog?.window?.setLayout(screenSize.widthPixels - 32.convertDPtoPX(screenSize), (screenSize.heightPixels - (screenSize.heightPixels / 2.5).toInt()))
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = createViewFragment()

        return AlertDialog.Builder(context).create().apply {
            setView(view)
            setCanceledOnTouchOutside(false)
            setCancelable(false)
            setDialogAnimation(window!!)
        }
    }

    private fun createViewFragment(): View {

        val dialogView = binding.root
        with(dialogView) {
            tvHello.setOnClickListener {
                dialog?.dismiss()
            }
        }

        return dialogView
    }

    private fun setDialogAnimation(window: Window) {
        window.apply {
            setBackgroundDrawableResource(R.drawable.bg_rec_insec_dialog)
            setGravity(Gravity.BOTTOM)
            attributes.windowAnimations = R.style.DialogAnimation
        }
    }
}