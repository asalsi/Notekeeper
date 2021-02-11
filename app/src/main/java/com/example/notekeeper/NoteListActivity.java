package com.example.notekeeper;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    private ArrayAdapter<NoteInfo> mAdapterNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener((view) -> {
            startActivity(new Intent (NoteListActivity.this, NoteActivity.class));
        });

        initializeDisplayContent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapterNotes.notifyDataSetChanged();
    }

    private void initializeDisplayContent() {
        // Grab the notes field bny id, final to allow referencing in anonymous class
        final ListView listNotes = findViewById(R.id.list_notes);

        // Grab the notes from data set
        List<NoteInfo> notes = DataManager.getInstance().getNotes();

        // Instantiate adapter
        mAdapterNotes = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);

        // set our adapter
        listNotes.setAdapter(mAdapterNotes);

        // New up an intrerface on click
        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Don't pass the regular 'this' pointer to anon function but the parent Class we want
                Intent intent = new Intent(NoteListActivity.this, NoteActivity.class);
                // Can use the position to get specific note
             //   NoteInfo note = (NoteInfo) listNotes.getItemAtPosition(position);

                // Pass the position in directly to the intent
                intent.putExtra(NoteActivity.NOTE_POSITION, position);

                startActivity(intent);

            }
        });


    }
}