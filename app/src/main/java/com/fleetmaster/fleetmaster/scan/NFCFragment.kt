package com.fleetmaster.fleetmaster.scan


import android.content.Context
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.NfcEvent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import com.fleetmaster.fleetmaster.R
import kotlinx.android.synthetic.main.activity_main.*
import android.nfc.NdefRecord.createMime
import android.util.Log


/**
 * A simple [Fragment] subclass.
 */
class NFCFragment : Fragment(), NfcAdapter.CreateNdefMessageCallback{


    lateinit var textView: TextView
    lateinit var baseText: String

    var nfcAdapter: NfcAdapter? = null

    var myId = -1

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_nfc, container, false)


        activity?.toolbar2?.removeAllViews()
        activity?.toolbar2?.title = getString(R.string.app_name)

        textView = v.findViewById(R.id.textView) as TextView

        myId = activity.getSharedPreferences("register", Context.MODE_PRIVATE).getInt("id", -1)

        baseText = textView.text.toString()

        textAnimation(0)

        nfcAdapter = NfcAdapter.getDefaultAdapter(activity)
        if (nfcAdapter == null) {
            Toast.makeText(context, "NFC is not available", Toast.LENGTH_LONG).show()

        }
        // Register callback
        nfcAdapter?.setNdefPushMessageCallback(this, activity)

        return v
    }

    override fun createNdefMessage(event: NfcEvent?): NdefMessage {
        val text = "$myId"
        val msg = NdefMessage(
                arrayOf(createMime(
                        "application/vnd.com.example.android.beam", text.toByteArray()))

        )
        Log.d("lol","hier")
        return msg
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