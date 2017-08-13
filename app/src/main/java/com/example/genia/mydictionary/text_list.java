package com.example.genia.mydictionary;

/**
 * Created by YAUHENI on 09.08.2017.
 */

public class text_list {
        String lang, orig_text, transl_text, id_text;

    text_list(String _orig_text, String _transl_text, String _id_text)
    {
        orig_text = _orig_text;
        transl_text = _transl_text;
        id_text = _id_text;
    }

    @Override
    public String toString() {
        return id_text;
    }
}
