package org.skypro.skyshop.model.search;


public class BestResultNotFound extends Exception {
    public BestResultNotFound(String query) {
        super("Не найден лучший результат для поискового запроса: \"" + query + "\"");
    }
}
