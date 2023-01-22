package de.cronn.cryptobookapp.activities.dashboard.ui.Wallet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import de.cronn.cryptobookapp.db.AppDatabase;
import de.cronn.cryptobookapp.databinding.FragmentWalletBinding;
import de.cronn.cryptobookapp.db.DatabaseFacade;
import de.cronn.cryptobookapp.db.model.UserWithWallets;
import de.cronn.cryptobookapp.db.model.Wallet;

public class WalletFragment extends Fragment {

    private final DatabaseFacade databaseFacade = new DatabaseFacade();
    private FragmentWalletBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentWalletBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final ListView listView = binding.listView;

        UserWithWallets user = databaseFacade.findByUserId(getLoggedInUserId());
        WalletListAdapter walletListAdapter = new WalletListAdapter(getActivity(), user);
        listView.setAdapter(walletListAdapter);
        return root;
    }

    private long getLoggedInUserId() {
        return Optional.ofNullable(getActivity())
                .map(activity -> Optional.ofNullable(activity.getIntent())).orElseThrow()
                .map(intent -> intent.getExtras().getLong("USER-ID")).orElseThrow();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}