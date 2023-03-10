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

import de.cronn.cryptobookapp.R;
import de.cronn.cryptobookapp.db.model.UserWithWallets;
import de.cronn.cryptobookapp.db.model.Wallet;
import de.cronn.cryptobookapp.http.Currencies;
import de.cronn.cryptobookapp.observer.TextViewObservableWalletStateDecorator;
import de.cronn.cryptobookapp.price.Currency;
import de.cronn.cryptobookapp.price.Price;
import de.cronn.cryptobookapp.transaction.TransactionType;
import de.cronn.cryptobookapp.transaction.Transactions;

public class WalletListAdapter extends BaseAdapter {
    private final UserWithWallets userWithWallets;
    private final LayoutInflater layoutInflater;
    private final Context context;

    public WalletListAdapter(Context context, UserWithWallets userWithWallets) {
        this.context = context;
        this.userWithWallets = userWithWallets;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return userWithWallets.getWallets().size();
    }

    @Override
    public Object getItem(int i) {
        return userWithWallets.getWallets().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView == null ? createWalletView(position) : convertView;
        WalletViewHolder holder = (WalletViewHolder) view.getTag();

        Wallet wallet = this.userWithWallets.getWallets().get(position);
        int imageId = this.getMipmapResIdByName(wallet.getCurrency().name().toLowerCase());
        holder.currencyNameView.setText(wallet.getCurrency().name());
        holder.textViewObservableWalletStateDecorator.updateText();
        holder.iconView.setImageResource(imageId);

        view.setOnClickListener(v -> showTransactionDialog(getItem(position)));
        return view;
    }

    private View createWalletView(int position) {
        View convertView = layoutInflater.inflate(R.layout.list_item_layout, null);
        WalletViewHolder holder = new WalletViewHolder();
        holder.iconView = convertView.findViewById(R.id.imageView2);
        holder.currencyNameView = convertView.findViewById(R.id.textView3);
        holder.textViewObservableWalletStateDecorator =
                new TextViewObservableWalletStateDecorator(convertView.findViewById(R.id.textView4), (Wallet) getItem(position));
        convertView.setTag(holder);
        return convertView;
    }

    public int getMipmapResIdByName(String resName) {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName, "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;
    }

    void showTransactionDialog(Object walletObject) {
        final Dialog dialog = createTransactionDialog();
        Button submitButton = dialog.findViewById(R.id.submit_button);

        Spinner transactionTypeSpinner = createEnumeratedSpinner(dialog, R.id.spinner1, TransactionType.class);
        Spinner currencySpinner = createEnumeratedSpinner(dialog, R.id.spinner2, Currency.class);
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
                    Currency currency = (Currency) currencySpinner.getSelectedItem();
                    BigDecimal amountTyped = new BigDecimal(amount.getText().toString());
                    Price price = Currencies.convert(new Price(wallet.getCurrency(), amountTyped), currency);
                    if (transactionTypeSpinner.getSelectedItem().equals(TransactionType.PURCHASE)) {
                        finalResultSign.setTextColor(Color.RED);
                        finalResultSign.setText("You pay: " + price.getValue() + " " + price.getCurrency());
                    } else {
                        finalResultSign.setTextColor(Color.GREEN);
                        finalResultSign.setText("You receive: " + price.getValue() + " " + price.getCurrency());
                    }
                } else {
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
                Currency currencyToConvert = (Currency) currencySpinner.getSelectedItem();
                BigDecimal amountOfCurrency = new BigDecimal(amount.getText().toString());
                if (transactionTypeSpinner.getSelectedItem().equals(TransactionType.PURCHASE)) {
                    Transactions.purchase(wallet.getCurrency(), amountOfCurrency, currencyToConvert)
                            .performOn(userWithWallets);
                } else {
                    Transactions.sell(wallet.getCurrency(), amountOfCurrency, currencyToConvert)
                            .performOn(userWithWallets);
                }
                this.notifyDataSetChanged();
                dialog.cancel();
            }
        });

        dialog.show();
    }

    private Dialog createTransactionDialog(){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog);
        return dialog;
    }

    private <T extends Enum<?>> Spinner createEnumeratedSpinner(Dialog dialog, int id, Class<T> enumType){
        Spinner spinner = dialog.findViewById(id);
        ArrayAdapter<T> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, enumType.getEnumConstants());
        spinner.setAdapter(adapter);
        return spinner;
    }

    private static class WalletViewHolder {
        ImageView iconView;
        TextView currencyNameView;
        TextViewObservableWalletStateDecorator textViewObservableWalletStateDecorator;
    }

}
