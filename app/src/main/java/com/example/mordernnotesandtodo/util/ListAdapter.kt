package com.example.mordernnotesandtodo.util

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mordernnotesandtodo.R
import com.example.mordernnotesandtodo.fragment.ViewPagerFragmentDirections
import com.example.mordernnotesandtodo.model.UserNotes
import kotlinx.android.synthetic.main.single_note_view.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.NoteViewHolder>() {

    private var notesList = emptyList<UserNotes>()

    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_note_view, parent, false))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = notesList[position]
        holder.itemView.titleNote.text = currentNote.notesTitle
        holder.itemView.noteDate.text = currentNote.date
        if(TextUtils.isEmpty(currentNote.notesDescription)) {
            holder.itemView.descriptionNote.visibility = View.GONE
        } else {
            holder.itemView.descriptionNote.text = currentNote.notesDescription.trim()
        }

        holder.itemView.cardBackground.setOnClickListener {
            val action = ViewPagerFragmentDirections.actionViewPagerFragmentToUpdateNoteFragment(currentNote)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun setData(notes: List<UserNotes>){
        this.notesList = notes
        notifyDataSetChanged()
    }
}