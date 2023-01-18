package de.cronn.cryptobookapp.activities.dashboard.ui.Wallet;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import de.cronn.cryptobookapp.activities.dashboard.DashboardActivity;
import de.cronn.cryptobookapp.databinding.FragmentWalletBinding;
import de.cronn.cryptobookapp.model.Wallet;
import de.cronn.cryptobookapp.price.Currency;

public class WalletFragment extends Fragment {

    private FragmentWalletBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        WalletViewModel walletViewModel =
                new ViewModelProvider(this).get(WalletViewModel.class);

        binding = FragmentWalletBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ListView listView = binding.listView;

        listView.setAdapter(new WalletListAdapter(getActivity(), getWallets()));
        return root;
    }

    public List<Wallet> getWallets() {
        List<Wallet> wallets = new ArrayList<>();
        wallets.add(new Wallet(1, Currency.BTC, new BigDecimal("1")));
        wallets.add(new Wallet(2, Currency.DOGE, new BigDecimal("1512312")));
        wallets.add(new Wallet(2, Currency.ETH, new BigDecimal("151231231")));
        wallets.add(new Wallet(2, Currency.USD, new BigDecimal("15123113")));

        return wallets;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}