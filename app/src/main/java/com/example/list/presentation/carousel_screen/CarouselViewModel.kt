package com.example.list.presentation.carousel_screen

import com.example.list.data.remote.ItemsApi
import com.example.list.presentation.ItemsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "CarouselViewModel"

@HiltViewModel
class CarouselViewModel @Inject constructor(
    itemsApi: ItemsApi
) : ItemsViewModel(itemsApi) {

    fun onActiveItemChanged(index: Int) {
        setSelectedItemAtIndex(index)
    }

}