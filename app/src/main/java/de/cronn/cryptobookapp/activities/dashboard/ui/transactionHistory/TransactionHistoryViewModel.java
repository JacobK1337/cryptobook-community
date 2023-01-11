package de.cronn.cryptobookapp.activities.dashboard.ui.transactionHistory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TransactionHistoryViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TransactionHistoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Transaction history fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}