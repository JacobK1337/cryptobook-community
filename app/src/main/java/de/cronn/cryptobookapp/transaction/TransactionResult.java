package de.cronn.cryptobookapp.transaction;

import java.math.BigDecimal;

import de.cronn.cryptobookapp.db.model.Wallet;

public class TransactionResult {
    private final TransactionType transactionType;
    private final Wallet walletFrom;
    private final BigDecimal amount;
    private final Wallet walletTo;

    public TransactionResult(TransactionType transactionType,
                             Wallet walletFrom,
                             BigDecimal amount,
                             Wallet walletTo) {
        this.transactionType = transactionType;
        this.walletFrom = walletFrom;
        this.amount = amount;
        this.walletTo = walletTo;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Wallet getWalletFrom() {
        return walletFrom;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Wallet getWalletTo() {
        return walletTo;
    }
}
