package org.skypro.skyshop.model.search;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class SearchEngine {
    // 🔹 теперь используем Set — убираем дубликаты
    private final Set<Searchable> items = new HashSet<>();

    public void add(Searchable searchable) {
        items.add(searchable); // HashSet автоматически не добавит дубликат
    }

    public Set<Searchable> search(String search) {
        return items.stream()
                .filter(item -> item.getSearchTerm().contains(search))
                .collect(Collectors.toCollection(
                        () -> new TreeSet<>(new SearchableComparator())
                ));
    }


    public Searchable findBestMatch(String query) throws BestResultNotFound {
        Searchable best = null;
        int maxOccurrences = 0;

        for (Searchable item : items) {
            int count = countOccurrences(item.getSearchTerm().toLowerCase(), query.toLowerCase());
            if (count > maxOccurrences) {
                maxOccurrences = count;
                best = item;
            }
        }

        if (best == null) {
            throw new BestResultNotFound(query);
        }
        return best;
    }

    private int countOccurrences(String text, String sub) {
        int count = 0;
        int index = text.indexOf(sub);
        while (index != -1) {
            count++;
            index = text.indexOf(sub, index + sub.length());
        }
        return count;
    }
}
