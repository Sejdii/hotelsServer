package com.sadzbr.utils.model;

import com.sadzbr.model.Client;
import com.sadzbr.model.Payments;
import com.sadzbr.model.Table;
import com.sadzbr.service.Database;

import java.util.ArrayList;
import java.util.List;

public class PaymentsUtils {
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
