package com.impactprogrammer.decimaltohex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.widget.EditText;

public class DecimalToHexActivity extends AppCompatActivity {

    private EditText mDecimalEditText;
    private EditText mHexEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decimal_to_hex);

        mDecimalEditText = (EditText) findViewById(R.id.decimal_text);
        mDecimalEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (mDecimalEditText.hasFocus()) {
                    String decString = editable.toString();
                    if (decString.length() > 0) {
                        Long number = Long.parseLong(decString);
                        mHexEditText.setText(Long.toHexString(number));
                    } else {
                        mHexEditText.setText("");
                    }
                }
            }
        });

        mHexEditText = (EditText) findViewById(R.id.hex_text);
        mHexEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (mHexEditText.hasFocus()) {
                    String hexString = charSequence.toString();
                    if (hexString.length() > 0) {
                        Long number = Long.parseLong(hexString, 16);
                        mDecimalEditText.setText(Long.toString(number));
                    } else {
                        mDecimalEditText.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mHexEditText.setFilters(
                new InputFilter[]{
                        // this filter will only allow hexadecimal characters to be input in this EditText.
                        new InputFilter() {
                            @Override
                            public CharSequence filter(CharSequence charSequence, int start,
                                                       int end, Spanned spanned, int i2, int i3) {
                                String validChars = "0123456789abcdef";
                                StringBuilder result = new StringBuilder(end - start);
                                for (int i = start; i < end; ++i) {
                                    final char c = Character.toLowerCase(charSequence.charAt(i));
                                    if (validChars.indexOf(c) != -1) {
                                        result.append(c);
                                    }
                                }
                                return result;
                            }
                        }
                }
        );
    }
}
