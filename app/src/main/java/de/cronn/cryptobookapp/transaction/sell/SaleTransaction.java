package de.cronn.cryptobookapp.transaction.sell;


import android.util.Log;

import de.cronn.cryptobookapp.model.User;
import de.cronn.cryptobookapp.model.Wallet;
import de.cronn.cryptobookapp.price.Price;
import de.cronn.cryptobookapp.transaction.Transaction;

public class SaleTransaction extends Transaction<SaleContext> {

    public SaleTransaction(SaleContext saleContext) {
        super(saleContext);
    }

    @Override
    protected void execute(User user) {
        Wallet currencyToSellWallet = user.getWallet(getContext().getToSell());
        Wallet currencyToReceiveAfterWallet = user.getWallet(getContext().getReceiveAfter());

        Log.i("TRANSACTION", String.format("Selling %s %s for %s",
                getContext().getAmount(), getContext().getToSell(), getContext().getReceiveAfter()));

        Price converted = new Price(getContext().getToSell(), getContext().getAmount())
                .convertTo(getContext().getReceiveAfter());

        currencyToSellWallet.setBalance(currencyToSellWallet.getBalance().subtract(getContext().getAmount()));
        currencyToReceiveAfterWallet.setBalance(currencyToReceiveAfterWallet.getBalance().add(converted.getValue()));

        Log.i("TRANSCATION", String.format(String.format("%s %s, %s, %s",
                currencyToSellWallet.getBalance(), currencyToSellWallet.getCurrency(),
                currencyToReceiveAfterWallet.getBalance(), currencyToReceiveAfterWallet.getBalance())));
    }
}
