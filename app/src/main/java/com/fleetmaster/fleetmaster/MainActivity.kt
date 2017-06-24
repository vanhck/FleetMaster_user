package com.fleetmaster.fleetmaster

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import com.fleetmaster.fleetmaster.find_tool_list.ToolListFragment
import com.fleetmaster.fleetmaster.scan.NFCFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val fragment = NFCFragment()
        fragmentTransaction.add(R.id.fragment_container, fragment)
        fragmentTransaction.commit()



        setSupportActionBar(toolbar2)


        //Nothing should happend on reselect
        bottom_navigation.setOnNavigationItemReselectedListener {  }

        bottom_navigation.setOnNavigationItemSelectedListener {
            item ->  var frag: Fragment?
            when(item.itemId) {
              R.id.action_search -> {
                  frag = ToolListFragment()

              }
                R.id.action_nfc -> {
                    frag = NFCFragment()
                }
            else -> {
                frag = null
            }
        }

            frag?.let {

                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, frag).commit()
                true
            }
            true
        }
    }
}
