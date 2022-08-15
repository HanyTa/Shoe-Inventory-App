package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoesListViewModel : ViewModel() {

    private val _addShoeNavigated = MutableLiveData<Boolean>()
    val addShoeNavigated: LiveData<Boolean>
        get() = _addShoeNavigated
    private val _shoeListNavigated = MutableLiveData<Boolean>()
    val shoeListNavigated: LiveData<Boolean>
        get() = _shoeListNavigated
    private val _shoesList = MutableLiveData<MutableList<Shoe>>()
    val shoesList: LiveData<MutableList<Shoe>>
        get() = _shoesList
    private lateinit var shoes: MutableList<Shoe>


    val nameText = MutableLiveData<String>()
    val sizeText = MutableLiveData<Int>()
    val companyText = MutableLiveData<String>()
    val descriptionText = MutableLiveData<String>()


    fun onSaveShoe() {
        addShoe(
            Shoe(
                nameText.value!!,
                sizeText.value!!.toDouble(),
                companyText.value!!,
                descriptionText.value!!,
                emptyList()
            )
        )
    }

    fun onCancelShoe() {
        _shoeListNavigated.value = true
    }

    init {
        _addShoeNavigated.value = false
        _shoeListNavigated.value = false
        nameText.value = ""
        sizeText.value = 0
        companyText.value = ""
        descriptionText.value = ""
        getShoesList()
    }

    private fun getShoesList() {
        shoes = mutableListOf()
        _shoesList.value = shoes
    }

    private fun addShoe(shoe: Shoe) {
        shoes.add(shoe)
        _shoesList.value = shoes
        _shoeListNavigated.value = true
    }


    fun navigateToAddShoe() {
        _addShoeNavigated.value = true
    }

    fun shoeAddedComplete() {
        _addShoeNavigated.value = false
    }

    fun shoeListComplete() {
        _shoeListNavigated.value = false
    }
}