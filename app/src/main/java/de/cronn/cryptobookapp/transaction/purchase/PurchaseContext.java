package de.cronn.cryptobookapp.transaction.purchase;

import de.cronn.cryptobookapp.model.Offer;
import de.cronn.cryptobookapp.transaction.Context;

public class PurchaseContext implements Context {
    private final Offer offer;

    public PurchaseContext(Offer offer) {
        this.offer = offer;
    }

    public Offer getOffer() {
        return offer;
    }
}
