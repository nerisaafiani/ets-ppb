package com.example.listup

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listup.databinding.ActivityMainBinding
import com.example.listup.databinding.DialogAddTodoBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        todoAdapter = TodoAdapter(mutableListOf())
        binding.rvTodoItems.adapter = todoAdapter
        binding.rvTodoItems.layoutManager = LinearLayoutManager(this)

        // FAB klik → buka form dialog
        binding.fabAddTodo.setOnClickListener {
            showAddTodoDialog()
        }
    }

    private fun showAddTodoDialog() {
        val dialogBinding = DialogAddTodoBinding.inflate(layoutInflater)
        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setCancelable(true)

        val alertDialog = dialogBuilder.create()

        var selectedDeadline = "Belum dipilih"

        dialogBinding.btnPickDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this, { _, y, m, d ->
                selectedDeadline = "$d/${m + 1}/$y"
                dialogBinding.tvSelectedDeadline.text = "Deadline: $selectedDeadline"
            }, year, month, day)

            datePicker.show()
        }

        dialogBinding.btnSaveTask.setOnClickListener {
            val taskDescription = dialogBinding.etTaskDescription.text.toString()
            if (taskDescription.isNotEmpty()) {
                val todo = Todo(taskDescription, selectedDeadline)
                todoAdapter.addTodo(todo)
                alertDialog.dismiss()
            }
        }

        alertDialog.show()
    }
}
