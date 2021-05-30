package com.sadzbr.utils;

/**
 * Klasa z metodami dotycząca zasobów
 */
public class Resource {
    /**
     * Zwraca ścieżkę do widoku
     * @param name Nazwa widoku
     * @return ścieżka
     */
    static public String view(String name) {
        return  "/resources/view/" + name + ".fxml";
    }
}
