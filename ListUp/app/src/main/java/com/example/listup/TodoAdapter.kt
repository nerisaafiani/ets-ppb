package com.example.listup

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//import kotlinx.android.synthetic.main.item_todo.view.*
import com.example.listup.databinding.ItemTodoBinding

class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

//    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
//        return  TodoViewHolder(
//            LayoutInflater.from(parent.context).inflate(
//                R.layout.item_todo,
//                parent,
//                false
//            )
//        )
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteTodos() {
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
//        holder.itemView.apply {
//            tvTodoTitle.text = curTodo.title
//            cbDone.isChecked = curTodo.isChecked
//            toggleStrikeThrough(tvTodoTitle, curTodo.isChecked)
//            cbDone.setOnCheckedChangeListener { _, isChecked ->
//                toggleStrikeThrough(tvTodoTitle, isChecked)
//                curTodo.isChecked = !curTodo.isChecked
//            }
//        }
        holder.binding.apply {
            tvTodoTitle.text = curTodo.title
            cbDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(tvTodoTitle, curTodo.isChecked)

            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTodoTitle, isChecked)
                curTodo.isChecked = isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}