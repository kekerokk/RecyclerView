package com.eiro.recyclerview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


import android.telecom.Connection;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import java.net.Socket;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import java.lang.Thread;

import com.eiro.recyclerview.databinding.ActivityMainBinding;
import com.eiro.recyclerview.databinding.FragmentAddingBinding;
import com.eiro.recyclerview.databinding.FragmentDataBaseBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddingFragment extends Fragment {

    private FragmentAddingBinding __binding;
    private CarItem item;

    private Button      mBtnSend  = null;
    private Connector mConnect  = null;

    private  String     HOST      = "localhost";
    private  int        PORT      = 11000;

    private  String     LOG_TAG   = "SOCKET";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mBtnSend = __binding.addCarButton;
        item = new CarItem();

        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSendClick();
            }
        });
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddingFragment newInstance(String param1, String param2) {
        AddingFragment fragment = new AddingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }*/

    @SuppressLint("SuspiciousIndentation")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        __binding = FragmentAddingBinding.inflate(inflater,container,false);

        __binding.addCarButton.setOnClickListener(view -> {
            if(__binding.carName.getText().toString() != "" && __binding.carNumber.getText().toString() != "")
                //sendData
            Navigation.findNavController(view).navigate(R.id.action_addingFragment_to_dataBase);
        });

        // Inflate the layout for this fragment
        return __binding.getRoot();
        // Inflate the layout for this fragment
    }


    private void onSendClick()
    {
        if (mConnect == null) {
            Log.d(LOG_TAG, "Соединение не установлено");
        }  else {
            Log.d(LOG_TAG, "Отправка сообщения");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String text;

                        item.setCarName(__binding.carName.getText().toString());
                        item.setCarNumber(__binding.carNumber.getText().toString());

                        Message mess = new Message();
                        text = mess.AddDataMessageConvert(item);

                        // отправляем сообщение
                        mConnect.sendData(text.getBytes());
                    } catch (Exception e) {
                        Log.e(LOG_TAG, e.getMessage());
                    }
                }
            }).start();
        }
    }
}