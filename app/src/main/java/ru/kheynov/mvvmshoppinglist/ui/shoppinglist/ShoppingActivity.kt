package ru.kheynov.mvvmshoppinglist.ui.shoppinglist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_shopping.*
import ru.kheynov.mvvmshoppinglist.R
import ru.kheynov.mvvmshoppinglist.adapters.ShoppingItemAdapter
import ru.kheynov.mvvmshoppinglist.data.db.ShoppingDatabase
import ru.kheynov.mvvmshoppinglist.data.db.entities.ShoppingItem
import ru.kheynov.mvvmshoppinglist.data.repositories.ShoppingRepository

class ShoppingActivity : AppCompatActivity() {
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository = repository)
        val viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })
        fab.setOnClickListener {
            AddShoppingItemDialog(this,
                object : AddDialogListener {
                    override fun onAddButtonClick(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }
                }).show()
        }
    }
}