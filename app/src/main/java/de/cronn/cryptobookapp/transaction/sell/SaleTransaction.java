package de.cronn.cryptobookapp.transaction.sell;


import android.util.Log;

import java.time.LocalDateTime;
import java.util.List;

import de.cronn.cryptobookapp.db.model.TransactionEntry;
import de.cronn.cryptobookapp.db.model.UserWithWallets;
import de.cronn.cryptobookapp.db.model.Wallet;
import de.cronn.cryptobookapp.http.Currencies;
import de.cronn.cryptobookapp.price.Price;
import de.cronn.cryptobookapp.transaction.Transaction;
import de.cronn.cryptobookapp.transaction.TransactionResult;
import de.cronn.cryptobookapp.transaction.TransactionType;

public class SaleTransaction extends Transaction<SaleContext> {

    public SaleTransaction(SaleContext saleContext) {
        super(saleContext);
    }

    @Override
    protected TransactionResult execute(UserWithWallets user) {
        Wallet currencyToSellWallet = user.getWallet(getContext().getToSell());
        Wallet currencyToReceiveAfterWallet = user.getWallet(getContext().getReceiveAfter());

        Log.i("TRANSACTION", String.format("Selling %s %s for %s",
                getContext().getAmount(), getContext().getToSell(), getContext().getReceiveAfter()));

        Price converted = Currencies.convert(new Price(getContext().getToSell(), getContext().getAmount()),
                getContext().getReceiveAfter());

        currencyToSellWallet.subtract(getContext().getAmount());
        currencyToReceiveAfterWallet.add(converted.getValue());

        Log.i("TRANSCATION", String.format(String.format("%s %s, %s, %s",
                currencyToSellWallet.getBalance(), currencyToSellWallet.getCurrency(),
                currencyToReceiveAfterWallet.getBalance(), currencyToReceiveAfterWallet.getBalance())));

        return new TransactionResult(TransactionType.SALE,
                currencyToSellWallet,
                getContext().getAmount(),
                currencyToReceiveAfterWallet);
    }
}
