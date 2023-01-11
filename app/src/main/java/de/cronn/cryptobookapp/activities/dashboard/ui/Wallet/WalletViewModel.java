package de.cronn.cryptobookapp.activities.dashboard.ui.Wallet;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WalletViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public WalletViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is wallet fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}