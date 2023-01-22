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
        TransactionHistoryListAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_layout, null);
            holder = new TransactionHistoryListAdapter.ViewHolder();
            holder.transactionIdView = (TextView) convertView.findViewById(R.id.textView3);
            holder.transactionDateView = (TextView) convertView.findViewById(R.id.textView4);
            convertView.setTag(holder);
        } else {
            holder = (TransactionHistoryListAdapter.ViewHolder) convertView.getTag();
        }

        TransactionEntry transactionEntry = this.userWithWallets.getTransactionEntries().get(position);

        holder.transactionIdView.setText("Transaction id: " + transactionEntry.getId());
        holder.transactionDateView.setText("Date: " + transactionEntry.getTimestamp());

        return convertView;
    }

    static class ViewHolder {
        TextView transactionIdView;
        TextView transactionDateView;
    }
}
