package de.cronn.cryptobookapp.activities.dashboard.ui.social;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.stream.Collectors;

import de.cronn.cryptobookapp.R;
import de.cronn.cryptobookapp.db.model.TransactionEntry;
import de.cronn.cryptobookapp.db.model.UserWithWallets;

public class SocialWallListAdapter extends BaseAdapter {

    private final List<TransactionEntry> transactionEntries;
    private final LayoutInflater layoutInflater;
    private final Context context;

    public SocialWallListAdapter(Context context, List<UserWithWallets> users) {
        this.context = context;
        this.transactionEntries = users.stream().map(UserWithWallets::getTransactionEntries).flatMap(List::stream).collect(Collectors.toList());
        this.layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return transactionEntries.size();
    }

    @Override
    public Object getItem(int i) {
        return transactionEntries.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView == null ? createTransactionEntryView(position) : convertView;
        TransactionEntryViewHolder holder = (TransactionEntryViewHolder) view.getTag();
        TransactionEntry transactionEntry = this.transactionEntries.get(position);
        holder.transactionIdView.setText("Transaction id: " + transactionEntry.getId());
        holder.transactionDateView.setText("Date: " + transactionEntry.getTimestamp());
        return view;
    }

    private View createTransactionEntryView(int position){
        View convertView = layoutInflater.inflate(R.layout.list_item_layout, null);
        TransactionEntryViewHolder holder = new TransactionEntryViewHolder();
        holder.transactionIdView = convertView.findViewById(R.id.textView3);
        holder.transactionDateView = convertView.findViewById(R.id.textView4);
        convertView.setTag(holder);
        return convertView;
    }

    static class TransactionEntryViewHolder {
        TextView transactionIdView;
        TextView transactionDateView;
    }
}
