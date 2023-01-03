package de.cronn.cryptobookapp.transaction.sell;

import android.util.Log;

import java.math.BigDecimal;

import de.cronn.cryptobookapp.transaction.Transaction;
import de.cronn.cryptobookapp.transaction.User;
import de.cronn.cryptobookapp.transaction.Wallet;

public class SaleTransaction extends Transaction<SaleContext> {

    public SaleTransaction(SaleContext saleContext) {
        super(saleContext);
    }

    @Override
    protected void execute(User user) {
        Wallet wallet = user.getWallet(getContext().getCurrency());

        assertHasEnoughCredits(wallet, getContext().getAmount());

        Log.i("TRANSACTION",
                String.format("Putting %s %s to sale", getContext().getAmount(), getContext().getCurrency()));

        //Freeze users credits
        //Create offer & save
    }

    private void assertHasEnoughCredits(Wallet wallet, BigDecimal amount){
        if(wallet.getValue().compareTo(amount) < 0){
            throw new IllegalStateException("Not enough credits");
        }
    }
}
