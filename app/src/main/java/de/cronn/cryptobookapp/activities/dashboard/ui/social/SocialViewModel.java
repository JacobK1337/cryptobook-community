package de.cronn.cryptobookapp.activities.dashboard.ui.social;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SocialViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SocialViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Social fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}