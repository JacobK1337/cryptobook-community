package de.cronn.cryptobookapp.activities.dashboard.ui.transactionHistory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import de.cronn.cryptobookapp.R;
import de.cronn.cryptobookapp.db.model.TransactionEntry;
import de.cronn.cryptobookapp.db.model.UserWithWallets;

public class TransactionHistoryListAdapter extends BaseAdapter {

    private final UserWithWallets userWithWallets;
    private final LayoutInflater layoutInflater;
    private final Context context;

    public TransactionHistoryListAdapter(Context context, UserWithWallets userWithWallets) {
        this.context = context;
        this.userWithWallets = userWithWallets;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return userWithWallets.getTransactionEntries().size();
    }

    @Override
    public Object getItem(int i) {
        return userWithWallets.getTransactionEntries().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView == null ? createTransactionEntryView(position) : convertView;
        TransactionEntryViewHolder holder = (TransactionEntryViewHolder) view.getTag();
        TransactionEntry transactionEntry = this.userWithWallets.getTransactionEntries().get(position);
        holder.transactionIdView.setText("Transaction id: " + transactionEntry.getId());
        holder.transactionDateView.setText("Date: " + transactionEntry.getTimestamp());
        return view;
    }

    private View createTransactionEntryView(int position){
        View convertView = layoutInflater.inflate(R.layout.list_item_layout, null);
        TransactionEntryViewHolder holder = new TransactionEntryViewHolder();
        holder.transactionIdView = convertView.findViewById(R.id.textView4);
        holder.transactionDateView = convertView.findViewById(R.id.textView4);
        convertView.setTag(holder);
        return convertView;
    }

    static class TransactionEntryViewHolder {
        TextView transactionIdView;
        TextView transactionDateView;
    }
}
