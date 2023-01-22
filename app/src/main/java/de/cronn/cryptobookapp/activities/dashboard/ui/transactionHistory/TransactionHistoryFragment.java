package de.cronn.cryptobookapp.activities.dashboard.ui.transactionHistory;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Optional;

import de.cronn.cryptobookapp.activities.dashboard.ui.Wallet.WalletListAdapter;
import de.cronn.cryptobookapp.databinding.FragmentTransactionHistoryBinding;
import de.cronn.cryptobookapp.db.DatabaseFacade;
import de.cronn.cryptobookapp.db.model.UserWithWallets;

public class TransactionHistoryFragment extends Fragment {

    private FragmentTransactionHistoryBinding binding;
    private final DatabaseFacade databaseFacade = new DatabaseFacade();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTransactionHistoryBinding.inflate(inflater, container, false);

        View root = binding.getRoot();
        final ListView listView = binding.listView;

        UserWithWallets user = databaseFacade.findByUserId(getLoggedInUserId());
        TransactionHistoryListAdapter walletListAdapter = new TransactionHistoryListAdapter(getActivity(), user);
        listView.setAdapter(walletListAdapter);
        return root;
    }

    private long getLoggedInUserId() {
        return Optional.ofNullable(getActivity())
                .map(activity -> Optional.ofNullable(activity.getIntent())).orElseThrow()
                .map(intent -> intent.getExtras().getLong("USER-ID")).orElseThrow();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}