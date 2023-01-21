package de.cronn.cryptobookapp.activities.dashboard.ui.Wallet;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.common.collect.MoreCollectors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import de.cronn.cryptobookapp.activities.dashboard.DashboardActivity;
import de.cronn.cryptobookapp.databinding.FragmentWalletBinding;
import de.cronn.cryptobookapp.http.Currencies;
import de.cronn.cryptobookapp.model.Wallet;
import de.cronn.cryptobookapp.price.Currency;

public class WalletFragment extends Fragment {

    private FragmentWalletBinding binding;
    private final ArrayList<Wallet> wallets = new ArrayList<>(List.of(
            new Wallet(1, Currency.BTC, new BigDecimal("100")),
            new Wallet(2, Currency.DOGE, new BigDecimal("100")),
            new Wallet(2, Currency.ETH, new BigDecimal("100")),
            new Wallet(2, Currency.USD, new BigDecimal("100"))
    ));


    private WalletListAdapter walletListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentWalletBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ListView listView = binding.listView;

        if (savedInstanceState != null) {
            Log.i("SAVED", savedInstanceState.getParcelableArrayList("wallets").toString());
            walletListAdapter = new WalletListAdapter(getActivity(), savedInstanceState.getParcelableArrayList("wallets"));
            listView.setAdapter(walletListAdapter);
        } else {
            walletListAdapter = new WalletListAdapter(getActivity(), wallets);
            listView.setAdapter(walletListAdapter);
        }
        return root;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("wallets", new ArrayList<>(walletListAdapter.getWallets()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}