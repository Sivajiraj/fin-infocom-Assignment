package com.fin.assignment.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fin.assignment.helpers.AssetHelper
import com.fin.assignment.model.response.DetailsResponseItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListViewModel : ViewModel() {
    private val _handleSuccess = MutableLiveData<List<DetailsResponseItem>>()
    val handleSuccess: LiveData<List<DetailsResponseItem>> = _handleSuccess

    fun setHandleSuccess(value: List<DetailsResponseItem>?) {
        _handleSuccess.value = value!!
    }


    fun getDetailsHistoryMock(applicationContext: Context, fileName: String) {
        val jsonFileString = AssetHelper.getJsonDataFromAsset(applicationContext, fileName)
        val gson = Gson()
        val pointsHistoryData = object : TypeToken<List<DetailsResponseItem>>() {}.type
        val response: List<DetailsResponseItem> = gson.fromJson(jsonFileString, pointsHistoryData)
        setHandleSuccess(response)
    }
}