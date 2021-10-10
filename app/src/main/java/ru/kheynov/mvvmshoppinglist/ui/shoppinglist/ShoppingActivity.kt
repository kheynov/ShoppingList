package ru.kheynov.mvvmshoppinglist.ui.shoppinglist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import ru.kheynov.mvvmshoppinglist.R
import ru.kheynov.mvvmshoppinglist.data.db.ShoppingDatabase
import ru.kheynov.mvvmshoppinglist.data.repositories.ShoppingRepository

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository = repository)
        val viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)
    }
}