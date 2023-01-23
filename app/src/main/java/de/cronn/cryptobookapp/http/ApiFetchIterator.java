package de.cronn.cryptobookapp.http;

import java.util.Iterator;

import de.cronn.cryptobookapp.price.Currency;

public class ApiFetchIterator implements Iterator<Currency> {
    private final Currency[] currencies = Currency.values();
    private int index = 0;

    @Override
    public boolean hasNext() {
       return index < currencies.length;
    }

    @Override
    public Currency next() {
        return currencies[index++];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
