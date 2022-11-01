package com.eiro.recyclerview;

import com.google.gson.Gson;

import java.util.List;

public class Message {

    private Gson gson = new Gson();

    public String AddDataMessageConvert(CarItem item)
    {
        String message = gson.toJson(item);

        return message;
    }
}
