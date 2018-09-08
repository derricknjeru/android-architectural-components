package com.derrick.architecturalcomponents.data.database.converters;

import android.arch.persistence.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

public class LanguageConverter {
    @TypeConverter
    public List<String> gettingListFromString(String languages) {
        List<String> list = new ArrayList<>();

        String[] array = languages.split(",");

        for (String s : array) {
            list.add(s);
        }
        return list;
    }

    @TypeConverter
    public String writingStringFromList(List<String> list) {
        String languages = "";
        for (String i : list) {
            languages += "," + i;
        }
        return languages;
    }
}
