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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
            holder.iconView = convertView.findViewById(R.id.imageView2);
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
        convertView.setOnClickListener(v -> showCustomDialog());
        return convertView;
    }

    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    void showCustomDialog() {
        final Dialog dialog = new Dialog(context);
        //We have added a title in the custom layout. So let's disable the default title.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //The user will be able to cancel the dialog bu clicking anywhere outside the dialog.
        dialog.setCancelable(true);
        //Mention the name of the layout of your custom dialog.

        dialog.setContentView(R.layout.custom_dialog);

        //Initializing the views of the dialog.
        final EditText nameEt = dialog.findViewById(R.id.name_et);
        final EditText ageEt = dialog.findViewById(R.id.age_et);
        final CheckBox termsCb = dialog.findViewById(R.id.terms_cb);
        Button submitButton = dialog.findViewById(R.id.submit_button);

        dialog.show();
    }

    static class ViewHolder {
        ImageView iconView;
        TextView currencyNameView;
        TextView amountView;
    }

}
