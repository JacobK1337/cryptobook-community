package de.cronn.cryptobookapp.activities.dashboard.ui.Wallet;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.cronn.cryptobookapp.R;
import de.cronn.cryptobookapp.activities.dashboard.DashboardActivity;
import de.cronn.cryptobookapp.model.Wallet;

public class WalletListAdapter extends BaseAdapter {
    private List<Wallet> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public WalletListAdapter(Context context, List<Wallet> listData) {
        this.context = context;
        this.listData = listData;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_layout, null);
            holder = new ViewHolder();
            holder.iconView = (ImageView) convertView.findViewById(R.id.imageView2);
            holder.currencyNameView = (TextView) convertView.findViewById(R.id.textView3);
            holder.amountView = (TextView) convertView.findViewById(R.id.textView4);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Wallet wallet = this.listData.get(position);

        int imageId = this.getMipmapResIdByName(wallet.getCurrency().name().toLowerCase());
        holder.currencyNameView.setText(wallet.getCurrency().name());
        holder.amountView.setText("Balance: " + wallet.getBalance());

        holder.iconView.setImageResource(imageId);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("WalletListAdapter", "You clicked " + wallet.getCurrency().name());
            }
        });
        return convertView;
    }

    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }




    static class ViewHolder {
        ImageView iconView;
        TextView currencyNameView;
        TextView amountView;
    }

}
