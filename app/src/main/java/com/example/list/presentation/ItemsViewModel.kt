package com.example.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.list.data.remote.ItemsApi
import com.example.list.domain.model.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ListViewModel"
private const val INITIAL_SELECTED_INDEX = 0

@HiltViewModel
open class ItemsViewModel @Inject constructor(
    private val itemsApi: ItemsApi
) : ViewModel() {

    private val _state = MutableStateFlow(ItemsViewState())
    val state = _state.asStateFlow()

    init {
        getItems()
    }

    private fun getItemAtIndex(index: Int): Item? {
        if (_state.value.items.isEmpty()) return null
        return _state.value.items[index]
    }

    fun setSelectedItemAtIndex(index: Int) {
        _state.update {
            it.copy(
                selectedItem = getItemAtIndex(index)
            )
        }
    }

    private fun selectInitialItem() {
        _state.update { currentState ->
            val items = currentState.items
            if (items.isNotEmpty()) {
                val updatedItems = items.mapIndexed { index, item ->
                    if (index == INITIAL_SELECTED_INDEX) {
                        item.copy(isSelected = true)
                    } else {
                        item
                    }
                }
                currentState.copy(
                    items = updatedItems,
                    selectedItem = items[INITIAL_SELECTED_INDEX]
                )
            } else {
                currentState
            }
        }
    }

    private fun getItems() {
        viewModelScope.launch {

            _state.update { state ->
                state.copy(
                    isLoading = true
                )
            }

            _state.update {
                it.copy(
                    items = itemsApi.getItems()
                )
            }

            selectInitialItem()

            _state.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }

}