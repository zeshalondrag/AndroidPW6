package com.example.database;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookDetailsActivity extends AppCompatActivity {
    private DataBaseHelper dbHelper;
    private EditText editTextName, editTextAuthor;
    private int bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        dbHelper = new DataBaseHelper(this);

        editTextName = findViewById(R.id.editTextName);
        editTextAuthor = findViewById(R.id.editTextAuthor);
        Button btnDelete = findViewById(R.id.btnDelete);

        Intent intent = getIntent();
        bookId = intent.getIntExtra("BOOK_ID", -1);
        String bookName = intent.getStringExtra("BOOK_NAME");
        String bookAuthor = intent.getStringExtra("BOOK_AUTHOR");

        editTextName.setText(bookName);
        editTextAuthor.setText(bookAuthor);

        btnDelete.setOnClickListener(v -> {
            dbHelper.deleteBookById(bookId);
            Toast.makeText(this, "Книга удалена", Toast.LENGTH_SHORT).show();
            finish();
        });

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(v -> {
            String updatedName = editTextName.getText().toString();
            String updatedAuthor = editTextAuthor.getText().toString();

            if (!updatedName.isEmpty() && !updatedAuthor.isEmpty()) {
                dbHelper.updateBook(bookId, updatedName, updatedAuthor);
                Toast.makeText(this, "Данные обновлены", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
            }
        });
    }
}