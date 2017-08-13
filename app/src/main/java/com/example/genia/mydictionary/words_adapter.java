package com.example.genia.mydictionary;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by YAUHENI on 09.08.2017.
 */

public class words_adapter extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<text_list> objects;

    words_adapter(Context context, ArrayList<text_list> text_list) {
        Log.i("words_adapter", "Конструктор");
        ctx = context;
        objects = text_list;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    // кол-во элементов
    @Override
    public int getCount() {
        Log.i("prof_adapter", "Количество элементов");
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        Log.i("prof_adapter", "Элементо по позиции");
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        Log.i("prof_adapter", "id по позиции");
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        Log.i("prof_adapter", "public View getView");
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.list_words, parent, false);
        }
        Log.i("prof_adapter", "Time_Table p = getProduct");
        text_list p = getProduct(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) view.findViewById(R.id.eng_view)).setText(p.orig_text);
        ((TextView) view.findViewById(R.id.rus_view)).setText(p.transl_text);

        Log.i("TimeAdapter", "заполняем View");
        return view;
    }

    // товар по позиции
    private text_list getProduct(int position) {
        Log.i("prof_adapter", "Слово по позиции");
        return ((text_list) getItem(position));
    }
}
