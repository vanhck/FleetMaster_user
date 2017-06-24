package com.fleetmaster.fleetmaster.register

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.fleetmaster.fleetmaster.MainActivity

import com.fleetmaster.fleetmaster.R
import com.fleetmaster.fleetmaster.find_tool_list.network.FleetMasterService
import kotlinx.android.synthetic.main.activity_register_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterUserActivity : AppCompatActivity() {

    var service: FleetMasterService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        setSupportActionBar(toolbar3)

        val adapter = PersonAdapter(listOf())


        val retrofit = Retrofit.Builder()
                .baseUrl("http://martinshare.com/api/van.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(FleetMasterService::class.java)

        service?.getUser()?.enqueue(object: Callback<List<Person>> {
            override fun onResponse(call: Call<List<Person>>?, response: Response<List<Person>>?) {
                adapter.setData(response!!.body()!!)
            }

            override fun onFailure(call: Call<List<Person>>?, t: Throwable?) {

            }
        })

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        adapter.setOnPersonClickListener {
            person -> run{
            val pref = this.getSharedPreferences("register", Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.putInt("id", person.id)
            editor.putString("name", person.name)
            editor.apply()
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
            finish()

          }
        }
    }
}
