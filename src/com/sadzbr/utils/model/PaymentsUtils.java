package com.sadzbr.utils.model;

import com.sadzbr.model.Payments;
import com.sadzbr.model.Table;
import com.sadzbr.service.Database;

import java.util.ArrayList;
import java.util.List;

/**
 * Metody dla tabeli payments
 */
public class PaymentsUtils {
    /**
     * Realizuje polecenie insert na tabeli
     * @param x Płatność
     * @return Zwraca listę płatności z jednym elementem którzy przechowuje w swoim polu ID id nowo utworzonego wiersza.
     */
    static public List<Table> insertPaymentsList(Payments x) {
        List<Table> tables = new ArrayList<>();

        Database database = Database.getDatabase();
        String query = String.format("insert into payments values (NULL, '%s', %s)",
                x.getForm(), String.valueOf(x.getAmount()));
        database.executeQuery(query);

        x = (Payments) TableUtils.setInsertedId(x);
        tables.add(x);

        return tables;
    }
}
