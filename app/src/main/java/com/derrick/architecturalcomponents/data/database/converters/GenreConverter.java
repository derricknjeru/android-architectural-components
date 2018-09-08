package com.derrick.architecturalcomponents.data.database.converters;

import android.arch.persistence.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

public class GenreConverter {
    @TypeConverter
    public List<Integer> gettingListFromString(String genreIds) {
        List<Integer> list = new ArrayList<>();

        String[] array = genreIds.split(",");

        for (String s : array) {
            list.add(Integer.parseInt(s));
        }
        return list;
    }

    @TypeConverter
    public String writingStringFromList(List<Integer> list) {
        String genreIds = "";
        for (int i : list) {
            genreIds += "," + i;
        }
        return genreIds;
    }
}
