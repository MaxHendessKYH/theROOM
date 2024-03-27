package com.example.theroom

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.theroom.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

//Room har tre huvudkomponenter , entity, DAO , databas
// ORM object relation mapping
// DAO Data Access Object
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MyViewModel

    var selectedItemPosition = -1
    var currentItem: User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MyViewModel::class.java]
        viewModel.getAllUsers().observe(this){ users ->
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, users)
            println(users)
            binding.lvUsers.adapter = adapter
        }
        binding.lvUsers.onItemClickListener= AdapterView.OnItemClickListener {parent, view, position ,id ->
            if(selectedItemPosition != -1)
            {
                val tempPos = parent.getChildAt(selectedItemPosition)
                tempPos.setBackgroundColor(Color.TRANSPARENT)
            }

            selectedItemPosition = position
            currentItem = parent.getItemAtPosition(position) as User
            view.setBackgroundColor(Color.GREEN)
        }
        binding.lvUsers.onItemLongClickListener = AdapterView.OnItemLongClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as User
            deleteUser(selectedItem)
        }
        binding.btnAdd.setOnClickListener {
            addUser()
        }

        binding.btnUpdate.setOnClickListener {
            updateUser()
        }
    }

    fun updateUser()
    {
        viewModel.viewModelScope.launch {
        try{

            val name = binding.etName.text.toString()
            val score = binding.etScore.text.toString().toDouble()
            val isHuman = binding.switchIshooman.isChecked
            val user = User(currentItem!!.id, name, score, isHuman)
            println(user)
            viewModel.updateUser(user)

            // Clear fields
            binding.etName.text.clear()
            binding.etScore.text.clear()

        }catch (e: Exception)
        {
            print( e.stackTrace)
        }
        }
    }
fun deleteUser(user: User): Boolean
{
    viewModel.viewModelScope.launch {
        viewModel.deleteUser(user)
    }
    return true
}
    fun addUser()
    {
        viewModel.viewModelScope.launch {
        try{
            // Create & Add User Instance
            val name = binding.etName.text.toString()
            val score = binding.etScore.text.toString().toDouble()
            val isHuman = binding.switchIshooman.isChecked
            val user = User(name = name ,score = score, isHuman =  isHuman)
            println(user)
            viewModel.addUser(user)

            // Clear fields
            binding.etName.text.clear()
            binding.etScore.text.clear()

        }catch (e: Exception)
        {
            print( e.stackTrace)
        }
        }
    }
}