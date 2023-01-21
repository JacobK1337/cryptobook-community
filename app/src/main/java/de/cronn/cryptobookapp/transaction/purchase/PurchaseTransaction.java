package de.cronn.cryptobookapp.transaction.purchase;

import android.util.Log;

import de.cronn.cryptobookapp.model.User;
import de.cronn.cryptobookapp.model.Wallet;
import de.cronn.cryptobookapp.price.Price;
import de.cronn.cryptobookapp.transaction.Transaction;

public class PurchaseTransaction extends Transaction<PurchaseContext> {
    public PurchaseTransaction(PurchaseContext context) {
        super(context);
    }

    @Override
    protected void execute(User user) {
        Wallet currencyToPurchaseWallet = user.getWallet(getContext().getToPurchase());
        Wallet currencyToPayWithWallet = user.getWallet(getContext().getPayWith());

        Log.i("TRANSACTION", String.format("Buying %s %s by %s",
                getContext().getAmount(), getContext().getToPurchase(), getContext().getPayWith()));

        Price converted = new Price(getContext().getToPurchase(), getContext().getAmount())
                .convertTo(getContext().getPayWith());

        currencyToPurchaseWallet.setBalance(currencyToPurchaseWallet.getBalance().add(getContext().getAmount()));
        currencyToPayWithWallet.setBalance(currencyToPayWithWallet.getBalance().subtract(converted.getValue()));

        Log.i("TRANSCATION", String.format(String.format("%s %s, %s, %s",
                currencyToPurchaseWallet.getBalance(), currencyToPurchaseWallet.getCurrency(),
                currencyToPayWithWallet.getBalance(), currencyToPayWithWallet.getBalance())));
    }
}
