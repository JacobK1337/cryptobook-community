package de.cronn.cryptobookapp.activities.dashboard.ui.transactionHistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import de.cronn.cryptobookapp.databinding.FragmentTransactionHistoryBinding;

public class TransactionHistoryFragment extends Fragment {

    private FragmentTransactionHistoryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TransactionHistoryViewModel transactionHistoryViewModel =
                new ViewModelProvider(this).get(TransactionHistoryViewModel.class);

        binding = FragmentTransactionHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        transactionHistoryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}