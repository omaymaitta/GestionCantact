package com.example.annuaire2;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.annuaire2.db.Appdatabase;

public class CustomDialog extends Dialog {


    public Context context;

    private Button buttonOK;
    private Button buttonCancel;

    private CustomDialog.FullNameListener listener;
    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    interface FullNameListener {
        public void fullNameEntered(String fullName);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_custom_dialog);
        this.buttonOK = (Button) findViewById(R.id.button_ok);
        this.buttonCancel  = (Button) findViewById(R.id.button_cancel);

        this.buttonOK .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOKClick();
            }
        });
        this.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonCancelClick();
            }
        });
    }


    private void buttonOKClick()  {

        this.dismiss();


    }


    private void buttonCancelClick()  {
        this.dismiss();
    }
}
