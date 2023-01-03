package de.cronn.cryptobookapp.transaction;

public abstract class Transaction<T extends Context> {
    private final T context;

    public Transaction(T context) {
        this.context = context;
    }
    
    protected abstract void execute(User user);

    public T getContext() {
        return context;
    }
}
