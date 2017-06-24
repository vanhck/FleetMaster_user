package com.fleetmaster.fleetmaster.scan


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.fleetmaster.fleetmaster.R
import kotlinx.android.synthetic.main.activity_main.*


/**
 * A simple [Fragment] subclass.
 */
class NFCFragment : Fragment() {

    lateinit var textView: TextView
    lateinit var baseText: String

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_nfc, container, false)


        activity?.toolbar2?.removeAllViews()
        activity?.toolbar2?.title = getString(R.string.app_name)

        textView = v.findViewById(R.id.textView) as TextView

        baseText = textView.text.toString()

        textAnimation(0)

        return v
    }

    fun textAnimation(numberOfPoints: Int) {
        textView.postDelayed({
            var n = numberOfPoints % 3
            val builder = StringBuilder()
            for(i in 0..n) {
                builder.append(". ")
            }
            textView.text = "$baseText $builder"
            textAnimation(n + 1)
        }, 600)
    }

}