package com.example.projectapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button btnAddItem, btnDelete;
    ArrayList<ShoppingItem> itemList;
    ArrayAdapter<ShoppingItem> adapter;
    int selectedPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listView);
        btnAddItem = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);

        itemList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(adapter);

        btnAddItem.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
            startActivityForResult(intent, 1);
        });
        listView.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            for (int i = 0; i < listView.getChildCount(); i++) {
                listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
            }
            view.setBackgroundColor(Color.parseColor("#D3D3D3"));
        });
        btnDelete.setOnClickListener(v -> {
            if (selectedPosition != -1 && selectedPosition < itemList.size()) {
                itemList.remove(selectedPosition);
                adapter.notifyDataSetChanged();
                selectedPosition = -1;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            String amount = data.getStringExtra("amount");
            itemList.add(new ShoppingItem(name, amount));
            adapter.notifyDataSetChanged();
        }
    }
}