package de.cronn.cryptobookapp.activities.dashboard.ui.Wallet;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.List;

import de.cronn.cryptobookapp.R;
import de.cronn.cryptobookapp.http.Currencies;
import de.cronn.cryptobookapp.model.User;
import de.cronn.cryptobookapp.model.Wallet;
import de.cronn.cryptobookapp.price.Currency;
import de.cronn.cryptobookapp.price.Price;
import de.cronn.cryptobookapp.transaction.Transactions;

public class WalletListAdapter extends BaseAdapter {
    private final List<Wallet> wallets;
    private final LayoutInflater layoutInflater;
    private final Context context;

    public WalletListAdapter(Context context, List<Wallet> wallets) {
        this.context = context;
        this.wallets = wallets;
        this.layoutInflater = LayoutInflater.from(context);
        Currencies.init();
    }

    @Override
    public int getCount() {
        return wallets.size();
    }

    @Override
    public Object getItem(int i) {
        return wallets.get(i);
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

        Wallet wallet = this.wallets.get(position);

        int imageId = this.getMipmapResIdByName(wallet.getCurrency().name().toLowerCase());
        holder.currencyNameView.setText(wallet.getCurrency().name());
        holder.amountView.setText("Balance: " + wallet.getBalance());

        holder.iconView.setImageResource(imageId);
        convertView.setOnClickListener(v -> showCustomDialog(getItem(position)));
        return convertView;
    }

    public int getMipmapResIdByName(String resName) {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName, "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;
    }

    void showCustomDialog(Object walletObject) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);

        dialog.setContentView(R.layout.custom_dialog);

        Button submitButton = dialog.findViewById(R.id.submit_button);

        Spinner spinner1 = dialog.findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, List.of("BUY", "SELL"));
        spinner1.setAdapter(adapter1);

        Spinner spinner2 = dialog.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter(context, android.R.layout.simple_spinner_item, Currency.values());
        spinner2.setAdapter(adapter2);

        EditText amount = dialog.findViewById(R.id.currencyAmount);
        TextView finalResultSign = dialog.findViewById(R.id.finalResultSign);

        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!amount.getText().toString().isEmpty()) {
                    Wallet wallet = (Wallet) walletObject;
                    Currency currency = (Currency) spinner2.getSelectedItem();
                    BigDecimal am = new BigDecimal(amount.getText().toString());
                    Price price = new Price(wallet.getCurrency(), am)
                            .convertTo(currency);
                    if (spinner1.getSelectedItem().toString().equals("BUY")) {
                        finalResultSign.setTextColor(Color.RED);
                        finalResultSign.setText("You pay: " + price.getValue() + " " + price.getCurrency());
                    } else {
                        finalResultSign.setTextColor(Color.GREEN);
                        finalResultSign.setText("You receive: " + price.getValue() + " " + price.getCurrency());
                    }
                } else{
                    finalResultSign.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        submitButton.setOnClickListener((view) -> {
            if (!amount.getText().toString().isEmpty()) {
                Wallet wallet = (Wallet) walletObject;
                Currency currencyToConvert = (Currency) spinner2.getSelectedItem();
                User user = new User(1, "Floyd");
                user.setWallets(wallets);
                BigDecimal amountOfCurrency = new BigDecimal(amount.getText().toString());
                if (spinner1.getSelectedItem().toString().equals("BUY")) {
                    Transactions.purchase(wallet.getCurrency(), amountOfCurrency, currencyToConvert)
                            .performOn(user);
                } else {
                    Transactions.sell(wallet.getCurrency(), amountOfCurrency, currencyToConvert)
                            .performOn(user);
                }
                this.notifyDataSetChanged();
                dialog.cancel();
            }
        });

        dialog.show();
    }

    public List<Wallet> getWallets() {
        return wallets;
    }

    static class ViewHolder {
        ImageView iconView;
        TextView currencyNameView;
        TextView amountView;
    }

}
