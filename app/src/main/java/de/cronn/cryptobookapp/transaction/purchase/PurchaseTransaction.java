package de.cronn.cryptobookapp.transaction.purchase;


import de.cronn.cryptobookapp.http.Currencies;
import de.cronn.cryptobookapp.model.Offer;
import de.cronn.cryptobookapp.model.User;
import de.cronn.cryptobookapp.model.Wallet;
import de.cronn.cryptobookapp.price.Currency;
import de.cronn.cryptobookapp.price.Price;
import de.cronn.cryptobookapp.transaction.Transaction;

public class PurchaseTransaction extends Transaction<PurchaseContext> {

    public PurchaseTransaction(PurchaseContext context) {
        super(context);
    }

    @Override
    protected void execute(User user) {
        Wallet wallet = user.getWallet(Currency.USD);
        Offer offer = getContext().getOffer();

        Price offeredPrice = new Price(offer.getCurrency(), offer.getAmount());
        Price offeredCurrencyInUsd = Currencies.getUsdPrice(offer.getCurrency());

        Price totalUsdCost = offeredPrice.multiply(offeredCurrencyInUsd).getAs(Currency.USD);
        wallet.setBalance(wallet.getBalance().subtract(totalUsdCost.getValue()));

        //walletDao.saveWallet(wallet) TODO
        //walletDao.removeOffer(offer) TODO
    }

}
