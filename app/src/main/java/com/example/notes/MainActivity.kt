package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var db:DatabaseHelper
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.addButton.setOnClickListener {
            val intent=Intent(this,AddNoteActivity::class.java)
            startActivity(intent)
        }

        //read
        db= DatabaseHelper(this)
        notesAdapter= NotesAdapter(db.getallnotes(),this)

        binding.notesRecycleview.layoutManager=LinearLayoutManager(this)
        binding.notesRecycleview.adapter=notesAdapter

            }
            override fun onResume() {
                super.onResume()
                notesAdapter.refreshData(db.getallnotes())
            }
}