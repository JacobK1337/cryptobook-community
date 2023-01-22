package de.cronn.cryptobookapp.transaction;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import de.cronn.cryptobookapp.db.DatabaseFacade;
import de.cronn.cryptobookapp.db.model.TransactionEntry;
import de.cronn.cryptobookapp.db.model.UserWithWallets;

public class TransactionExecutor {
    private final Transaction<?> transaction;
    private final static DatabaseFacade databaseFacade = new DatabaseFacade();

    TransactionExecutor(Transaction<?> transaction) {
        this.transaction = transaction;
    }

    public void performOn(UserWithWallets user) {
        TransactionResult transactionResult = transaction.execute(user);
        Stream.of(transactionResult.getWalletFrom(), transactionResult.getWalletTo())
                .forEach(wallet -> databaseFacade.updateWallet(wallet.getId(), wallet.getBalance()));

        TransactionEntry transactionEntry =
                new TransactionEntry(user.getUser().getId(),
                        LocalDateTime.now(),
                        transactionResult.getTransactionType(),
                        transactionResult.getWalletFrom().getCurrency(),
                        transactionResult.getAmount(),
                        transactionResult.getWalletTo().getCurrency());

        databaseFacade.insertTransactionEntry(transactionEntry);
    }
}
