package com.example.mordernnotesandtodo.util

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.example.mordernnotesandtodo.R
import com.example.mordernnotesandtodo.model.UserTodo
import com.example.mordernnotesandtodo.viewModel.TodoViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.single_todo_view.view.*

class TodoListAdapter : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {

    private var todoList = emptyList<UserTodo>()
    private lateinit var mUserTodoViewModel: TodoViewModel
    private lateinit var context: Context

    fun setData(todo: List<UserTodo>, mTodoViewModel: TodoViewModel, context: Context?) {
        todoList = todo
        notifyDataSetChanged()

        this.context = context!!
        mUserTodoViewModel = mTodoViewModel
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_todo_view, parent, false))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todoList[position]
        holder.itemView.checkBox.isChecked = currentTodo.todoTaskCompleted

        if (!currentTodo.todoTaskCompleted) {
            holder.itemView.titleTodo.text = currentTodo.todoTask
            holder.itemView.titleTodo.setTextColor(context.getColor(R.color.titleColor))
            holder.itemView.checkBox.buttonTintList = ColorStateList.valueOf(context.getColor(R.color.titleColor))
            holder.itemView.cardBackground.setCardBackgroundColor(context.getColor(R.color.SingleTodobackgroundIncomplete))
        } else {
            holder.itemView.titleTodo.text = currentTodo.todoTask
            holder.itemView.titleTodo.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            holder.itemView.titleTodo.setTextColor(context.getColor(R.color.textHintColor))
            holder.itemView.checkBox.buttonTintList = ColorStateList.valueOf(context.getColor(R.color.textHintColor))
            holder.itemView.cardBackground.setCardBackgroundColor(context.getColor(R.color.SingleTodobackgroundComplete))
        }

        holder.itemView.deleteButton.setOnClickListener {
            MaterialAlertDialogBuilder(context)
                    .setTitle(context.getString(R.string.deleteTodo))
                    .setNegativeButton(context.getText(R.string.No)) { _, _ ->

                    }.setPositiveButton(context.getText(R.string.Yes)) { _, _ ->
                        mUserTodoViewModel.deleteTodo(currentTodo)
                    }.show()
        }

        holder.itemView.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                currentTodo.todoTaskCompleted = true
                mUserTodoViewModel.updateTodo(currentTodo)
            } else {
                currentTodo.todoTaskCompleted = false
                mUserTodoViewModel.updateTodo(currentTodo)
            }
        }

    }


    override fun getItemCount(): Int {
        return todoList.size
    }

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}