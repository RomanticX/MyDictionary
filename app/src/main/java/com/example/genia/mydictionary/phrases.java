package com.example.genia.mydictionary;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class phrases extends AppCompatActivity {

    EditText ed_eng, ed_rus;
    Dialog dialog;
    String s_eng, s_rus;
    String RELEASE = "release";
    String release;
    SharedPreferences sPref;
    LinearLayout l_layout;
    LinearLayout view;
    final int DIALOG = 1;
    DBHelper dbHelper;
    SQLiteDatabase db;
    ArrayList<text_list> phrases_tabl = new ArrayList<text_list>();
    words_adapter adapter;
    ListView lv_phrase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        l_layout = (LinearLayout) findViewById(R.id.ll_out_1);

        set_up_list();

        lv_phrase = (ListView) findViewById(R.id.phrase_list);
        registerForContextMenu(lv_phrase);


    }

    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 1, 0, "Удалить");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId()==1)
        {
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Log.i("phrases", "Позиция - " + acmi.position);

            Object obj = adapter.getItem(acmi.position);

            Log.i("phrases", "getItemId - " + obj);

            String s= obj.toString();

            int i_id_phr = Integer.parseInt(s);
            Log.i("phrases", "i_id_word - " + i_id_phr);

            db = dbHelper.getWritableDatabase();
            int delCount = db.delete(DBHelper.TABLE_PHRASES, DBHelper.KEY_ID + "= " + i_id_phr, null);
            Log.d("phrases", DBHelper.TABLE_PHRASES +","+ DBHelper.KEY_ID + "= " + i_id_phr);
            Log.d("phrases", "deleted rows count = " + delCount);
            dbHelper.close();
            set_up_list();

        }
        return super.onContextItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout_words, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    protected Dialog onCreateDialog(int id) {

        if (id == DIALOG)
        {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            // создаем view из dialog.xml
            view = (LinearLayout) getLayoutInflater()
                    .inflate(R.layout.add_phrases, null);
            // устанавливаем ее, как содержимое тела диалога
            adb.setView(view);

            ed_eng = (EditText) view.findViewById(R.id.ed_orig_phr);
            ed_rus = (EditText) view.findViewById(R.id.ed_transl_phr);
            dialog = adb.create();

            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialogInterface) {
                    Log.i("phrases", "Dialog Show");
                }
            });

            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    Log.i("phrases", "Dialog Dismiss");
                }
            });
            return  dialog;
        }
        return super.onCreateDialog(id);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.to_main:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.add_text:
                showDialog(DIALOG);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void add_new_word(View view) {
        s_eng = ed_eng.getText().toString();
        s_rus = ed_rus.getText().toString();

        ed_eng.setText("");
        ed_eng.requestFocus();
        ed_rus.setText("");

        if (!s_eng.equals("")  && !s_rus.equals(""))
        {
            Log.i("phrases", "Check input text: Eng text: "
                    + s_eng + "Rus text: " + s_rus);

            dbHelper = new DBHelper(this);
            //object dbHelper class

            //connect to db
            db = dbHelper.getWritableDatabase();

            Cursor c = db.query(DBHelper.TABLE_PHRASES, null, null, null, null, null, null);
            Log.d("phrases", "Number of records in the database: " + c.getCount());

            Log.d("phrases", "Put new data in DB...");
            ContentValues cv = new ContentValues();
            //write in table new data

            cv.put(DBHelper.LANGUAGE, "Eng");
            cv.put(DBHelper.PHRASE, s_eng);
            cv.put(DBHelper.TRANSLATE, s_rus);
            db.insert(DBHelper.TABLE_PHRASES, null, cv);
            Log.d("phrases", "New data is was put in database.");

            set_up_list();
            Log.d("phrases", "Data  from DB ended");


            c.close();
            dbHelper.close();

            dialog.dismiss();
        }
        else
        {
            Toast.makeText(phrases.this, "Введите слова", Toast.LENGTH_SHORT).show();
        }




    }

    void set_up_list() {
        //Method of updating the list
        dbHelper = new DBHelper(this);
        //object dbHelper class

        //connect to db
        db = dbHelper.getWritableDatabase();

        Cursor c = db.query(DBHelper.TABLE_PHRASES, null, null, null, null, null, null);

        if (c.getCount() != 0) {
            if (c.moveToFirst()) {
                int idIndex = c.getColumnIndex(DBHelper.KEY_ID);
                int langIndex = c.getColumnIndex(DBHelper.LANGUAGE);
                int phrIndex = c.getColumnIndex(DBHelper.PHRASE);
                int translateIndex = c.getColumnIndex(DBHelper.TRANSLATE);
                Log.d("phrases", "Data output from DB: ");
                phrases_tabl.clear();
                do {
                    phrases_tabl.add(new text_list(c.getString(phrIndex), c.getString(translateIndex),
                            c.getString(idIndex)));
                    Log.d("phrases", "ID = " + c.getInt(idIndex) + ", Language = " + c.getString(langIndex)
                            + ", Word = " + c.getString(phrIndex) + ", Translate = " + c.getString(translateIndex));
                    Log.d("phrases", "____________________________________________________________________________________________");
                } while (c.moveToNext());

            }
            adapter = new words_adapter(this, phrases_tabl);
            Log.i("phrases", "Crate adapter");
            lv_phrase = (ListView) findViewById(R.id.phrase_list);
            lv_phrase.setAdapter(adapter);
            Log.i("phrases", "Set up a list");

        }
        else
        {
            phrases_tabl.clear();
            adapter = new words_adapter(this, phrases_tabl);
            Log.i("phrases", "Crate adapter");
            lv_phrase = (ListView) findViewById(R.id.phrase_list);
            lv_phrase.setAdapter(adapter);
            Toast.makeText(phrases.this, "Список фраз пуст", Toast.LENGTH_LONG).show();
        }

    }
}
