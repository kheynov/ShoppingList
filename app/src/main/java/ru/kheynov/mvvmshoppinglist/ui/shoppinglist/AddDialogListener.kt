package ru.kheynov.mvvmshoppinglist.ui.shoppinglist

import ru.kheynov.mvvmshoppinglist.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClick(item: ShoppingItem)
}