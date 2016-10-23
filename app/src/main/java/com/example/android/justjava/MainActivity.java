package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {
    int quantity = 0;
    boolean checkbox = false;
    boolean checkbox2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "hola soy un mensaje");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
            displayMessage(createOrderSummary());
        }
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.checkBox:
                if (checked)
                    checkbox = true;
                else
                    checkbox = false;
                break;
        }
        boolean checked2 = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.checkBox2:
                if (checked2)
                    checkbox2 = true;
                else
                    checkbox2 = false;
                break;
        }
    }

    public String createOrderSummary() {
        EditText editText = (EditText) findViewById(R.id.editText);
        String name = editText.getText().toString();
        String mensaje = "Nombre: " + name + " \nCantidad: " + quantity + "\nPrecio: " + calculatePrice(quantity) + "\n" + getString(R.string.Thank_you);
        return mensaje;
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void increment(View view) {
        quantity++;
        display(quantity);
    }

    public void decrement(View view) {
        quantity--;
        if (quantity <= 0) {
            quantity = 0;
        }
        display(quantity);
    }

    public int calculatePrice(int numberOfCoffes) {
        int price = 5;
        if (checkbox) {
            price = price + 1;
        }
        if (checkbox2) {
            price = price + 2;
        }
        price = price * numberOfCoffes;
        return price;
    }

    public void printToLogs(View view) {
        // Find first menu item TextView and print the text to the logs
        TextView textViewItem1 = (TextView) findViewById(R.id.menu_item_1);
        String menuItem1 = textViewItem1.getText().toString();
        Log.v("MainActivity", menuItem1);
        // Find second menu item TextView and print the text to the logs
        TextView textViewItem2 = (TextView) findViewById(R.id.menu_item_2);
        String menuItem2 = textViewItem2.getText().toString();
        Log.v("MainActivity", menuItem2);
        // Find third menu item TextView and print the text to the logs
        TextView textViewItem3 = (TextView) findViewById(R.id.menu_item_3);
        String menuItem3 = textViewItem3.getText().toString();
        Log.v("mensaje", menuItem3);

    }
}