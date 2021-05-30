package com.sadzbr.utils.model;

import com.sadzbr.model.Newsletter;
import com.sadzbr.model.Table;
import com.sadzbr.service.Database;

import java.util.ArrayList;
import java.util.List;

/**
 * Metody dla tabeli newsletter
 */
public class NewsletterUtils {
    /**
     * Wykonuje polecenie insert
     * @param x Newsletter
     * @return Zwraca listę newsletterów z jednym elementem którzy przechowuje w swoim polu ID id nowo utworzonego wiersza.
     */
    static public List<Table> insertNewsletterList(Newsletter x) {
        List<Table> tables = new ArrayList<>();

        Database database = Database.getDatabase();
        String query = String.format("insert into newsletter values (NULL, '%s', %d)",
                x.getEmail(), x.getId_client());
        database.executeQuery(query);

        x = (Newsletter) TableUtils.setInsertedId(x);
        tables.add(x);

        return tables;
    }
}
