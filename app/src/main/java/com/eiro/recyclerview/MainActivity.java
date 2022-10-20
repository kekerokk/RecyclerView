package com.eiro.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final List<Item> items = new ArrayList<>();
    private final RecyclerView.Adapter adapter = new ItemAdapter(this.items);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }

    public void add(View view)
    {
        EditText edit = this.findViewById(R.id.editText);
        this.items.add(new Item(edit.getText().toString()));
        edit.setText("");
        adapter.notifyItemInserted(this.items.size()-1);
    }

    private static final class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
    {
        private final List<Item> items;

        public ItemAdapter(List<Item> items)
        {
            this.items = items;
        }

        @NotNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int index)
        {
            return new RecyclerView.ViewHolder (
            LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.items,parent,false)

            ) {};
        }

        @Override
        public void onBindViewHolder (@NotNull RecyclerView.ViewHolder holder, int index)
        {
            TextView name = holder.itemView.findViewById(R.id.name);
            name.setText(String.format("%s. %s",index, this.items.get(index).getName()));
        }

        @Override
        public int getItemCount() {
            return this.items.size();
        }
    }
}