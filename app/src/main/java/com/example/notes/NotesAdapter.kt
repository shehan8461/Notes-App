package com.example.notes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private var notes:List<Note>,context: Context): RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {


    private val db:DatabaseHelper= DatabaseHelper(context)

    class NoteViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        val titleTextView:TextView=itemView.findViewById(R.id.titleTextView)
        val contextTextView:TextView=itemView.findViewById(R.id.contentTextView)
        val Updatebutton:ImageView=itemView.findViewById(R.id.updateButton)
        val deletebutton:ImageView=itemView.findViewById(R.id.deleteButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int =notes.size



    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note=notes[position]
        holder.titleTextView.text=note.title
        holder.contextTextView.text=note.content

        holder.Updatebutton.setOnClickListener {
            val intent=Intent(holder.itemView.context,UpdateActivity::class.java).apply {
                putExtra("note_id",note.id)
            }
            holder.itemView.context.startActivity(intent)
        }
        holder.deletebutton.setOnClickListener{
            db.deleteNote(note.id)
            refreshData(db.getallnotes())
            Toast.makeText(holder.itemView.context,"Note Deleted",Toast.LENGTH_SHORT).show()
        }
    }

fun refreshData(newNotes:List<Note>){
    notes=newNotes
    notifyDataSetChanged()
}
}