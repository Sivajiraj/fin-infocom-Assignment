package com.fin.assignment.views.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fin.assignment.BR
import com.fin.assignment.R
import com.fin.assignment.databinding.ActivityMainBinding
import com.fin.assignment.model.response.DetailsResponseItem
import com.fin.assignment.viewmodel.ListViewModel
import com.fin.assignment.views.adapters.DetailsAdapter

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val listViewModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpContentView(R.layout.activity_main)
        setupObservers()
        getDetailsHistory()
        fetchDetails()
    }

    private fun fetchDetails() {
        val email = mainViewDatBinding.emailET.text
        val number = mainViewDatBinding.mobileET.text

        mainViewDatBinding.addBTN.setOnClickListener {
            if (email.isNullOrEmpty()) {
                mainViewDatBinding.emailET.error = "Enter Email"
                if (number.isNullOrEmpty()){
                    mainViewDatBinding.mobileET.error = "Emter Mobile Number"
                }
            }else{
            Toast.makeText(this, ""+email+number, Toast.LENGTH_LONG).show()
        }
    }
    }

    private fun getDetailsHistory() {

        listViewModel.getDetailsHistoryMock(applicationContext, "details.json")

    }

    private fun setupObservers() {
        listViewModel.handleSuccess.observe(this, {
            if (!it.isNullOrEmpty()) {
                mainViewDatBinding.detailsRV.visibility = View.VISIBLE
                mainViewDatBinding.errorTxt.visibility = View.GONE
                val adapter = DetailsAdapter()
                adapter.setData(it as ArrayList<DetailsResponseItem>)
                mainViewDatBinding.detailsRV.layoutManager = LinearLayoutManager(this)
                mainViewDatBinding.detailsRV.adapter = adapter
                adapter.notifyDataSetChanged()
            } else {
                mainViewDatBinding.detailsRV.visibility = View.GONE
                mainViewDatBinding.errorTxt.visibility = View.VISIBLE
            }
        })
    }

    override fun setupViewModel() {
        mainViewDatBinding.setVariable(BR._all, listViewModel)
        mainViewDatBinding.lifecycleOwner = this
    }
}