package com.eiro.recyclerview;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.eiro.recyclerview.databinding.FragmentDataBaseBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataBase#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataBase extends Fragment {

    private FragmentDataBaseBinding __binding;
    private final List<Item> Items = new ArrayList<>();
    private final RecyclerView.Adapter adapter = new ItemAdapter(this.Items);


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DataBase() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataBase.
     */
    // TODO: Rename and change types and number of parameters
    public static DataBase newInstance(String param1, String param2) {
        DataBase fragment = new DataBase();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        __binding = FragmentDataBaseBinding.inflate(inflater,container,false);
        __binding.addButton.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_dataBase_to_addingFragment);
        });
        // Inflate the layout for this fragment
        return __binding.getRoot();
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
                            .inflate(R.layout.finaly,parent,false)
            ) {};
        }

        @Override
        public void onBindViewHolder (@NotNull RecyclerView.ViewHolder holder, int index)
        {
            TextView name = holder.itemView.findViewById(R.id.name);
            TextView created = holder.itemView.findViewById(R.id.created);
            Item item = this.items.get(index);
            name.setText(String.format("%s. %s",index, item.getName()));
            created.setText(format(item.getCreated()));
            CheckBox done = holder.itemView.findViewById(R.id.done);
            done.setOnCheckedChangeListener((view,checked)-> item.setDone(checked));
        }

        private String format (Calendar cal) {
            return String.format(
                    Locale.getDefault(), "%02d. %02d. %d",
                    cal.get(Calendar.DAY_OF_MONTH),cal.get(Calendar.MONTH), cal.get(Calendar.YEAR)
            );
        }


        @Override
        public int getItemCount() {
            return this.items.size();
        }
    }
}
