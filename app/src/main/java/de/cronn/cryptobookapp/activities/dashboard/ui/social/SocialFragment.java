package de.cronn.cryptobookapp.activities.dashboard.ui.social;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import de.cronn.cryptobookapp.databinding.FragmentTransactionHistoryBinding;
import de.cronn.cryptobookapp.db.DatabaseFacade;

public class SocialFragment extends Fragment {

    private FragmentTransactionHistoryBinding binding;
    private final DatabaseFacade databaseFacade = new DatabaseFacade();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTransactionHistoryBinding.inflate(inflater, container, false);

        View root = binding.getRoot();
        final ListView listView = binding.listView;

        var user = databaseFacade.findAll();
        SocialWallListAdapter walletListAdapter = new SocialWallListAdapter(getActivity(), user);
        listView.setAdapter(walletListAdapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}