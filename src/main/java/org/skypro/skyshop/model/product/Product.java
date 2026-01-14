package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;


public abstract class Product implements Searchable {
    private final UUID id;
    private final String name;

    protected Product( UUID id, String name) {
        this.id = id;
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым или null");
        }
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    // ✅ сравнение продуктов только по имени
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return name.equalsIgnoreCase(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }

    @Override
    public String getSearchTerm() {
        return name;
    }

    @Override
    public String getSearchType() {
        return "PRODUCT";
    }

    @Override
    public String getStringRepresentation() {
        return name + " — " + getSearchType();
    }

    public UUID getId() {
        return id;
    }
}
