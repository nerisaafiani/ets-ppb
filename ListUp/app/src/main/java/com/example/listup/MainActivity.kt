package com.example.listup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.recyclerview.widget.LinearLayoutManager
//import kotlinx.android.synthetic.main.activity_main.btnAddTodo
//import kotlinx.android.synthetic.main.activity_main.btnDeleteAllTodos
//import kotlinx.android.synthetic.main.activity_main.etTodoTitle
//import kotlinx.android.synthetic.main.activity_main.rvTodoItems
import com.example.listup.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        todoAdapter = TodoAdapter(mutableListOf())

//        rvTodoItems.adapter = todoAdapter
//        rvTodoItems.layoutManager = LinearLayoutManager(this)
        binding.rvTodoItems.adapter = todoAdapter
        binding.rvTodoItems.layoutManager = LinearLayoutManager(this)

//        btnAddTodo.setOnClickListener {
        binding.btnAddTodo.setOnClickListener {
//            val todoTitle = etTodoTitle.text.toString()
            val todoTitle = binding.etTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
//                etTodoTitle.text.clear()
                binding.etTodoTitle.text.clear()
            }
        }
//        btnDeleteAllTodos.setOnClickListener {
        binding.btnDeleteDoneTodos.setOnClickListener {
            todoAdapter.deleteTodos()
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ListUpTheme {
//        Greeting("Android")
//    }
//}