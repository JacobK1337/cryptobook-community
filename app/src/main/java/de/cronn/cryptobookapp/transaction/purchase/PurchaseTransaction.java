package de.cronn.cryptobookapp.transaction.purchase;

import android.util.Log;

import java.util.List;

import de.cronn.cryptobookapp.db.model.UserWithWallets;
import de.cronn.cryptobookapp.db.model.Wallet;
import de.cronn.cryptobookapp.http.Currencies;
import de.cronn.cryptobookapp.price.Price;
import de.cronn.cryptobookapp.transaction.Transaction;
import de.cronn.cryptobookapp.transaction.TransactionResult;
import de.cronn.cryptobookapp.transaction.TransactionType;

public class PurchaseTransaction extends Transaction<PurchaseContext> {
    public PurchaseTransaction(PurchaseContext context) {
        super(context);
    }

    @Override
    protected TransactionResult execute(UserWithWallets user) {
        Wallet currencyToPurchaseWallet = user.getWallet(getContext().getToPurchase());
        Wallet currencyToPayWithWallet = user.getWallet(getContext().getPayWith());

        Log.i("TRANSACTION", String.format("Buying %s %s by %s",
                getContext().getAmount(), getContext().getToPurchase(), getContext().getPayWith()));

        Price converted = Currencies.convert(
                new Price(getContext().getToPurchase(), getContext().getAmount()), getContext().getPayWith());

        currencyToPurchaseWallet.add(getContext().getAmount());
        currencyToPayWithWallet.subtract(converted.getValue());

        Log.i("TRANSCATION", String.format(String.format("%s %s, %s, %s",
                currencyToPurchaseWallet.getBalance(), currencyToPurchaseWallet.getCurrency(),
                currencyToPayWithWallet.getBalance(), currencyToPayWithWallet.getBalance())));

        return new TransactionResult(TransactionType.PURCHASE,
                currencyToPurchaseWallet,
                getContext().getAmount(),
                currencyToPayWithWallet);
    }
}
