package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notes.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding:ActivityUpdateBinding
    private lateinit var db:DatabaseHelper
    private  var noteId:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        noteId=intent.getIntExtra("note_id",-1)
        if(noteId==-1){
            finish()
            return
        }
        val note=db.getNoteById(noteId)
        binding.UpdatetitleEditText.setText(note.title)
        binding.UpdatecontentEditText.setText(note.content)

        binding.UpdateSaveButton.setOnClickListener{
            val newTitle=binding.UpdatetitleEditText.text.toString()
            val newContent=binding.UpdatecontentEditText.text.toString()
            val updatedNote=Note(noteId,newTitle,newContent)
            db.updateNote(updatedNote)
            finish()
            Toast.makeText(this,"change saved",Toast.LENGTH_SHORT).show()
        }

    }
}