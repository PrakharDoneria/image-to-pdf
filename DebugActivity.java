/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.os.Bundle
 *  android.util.Log
 *  java.lang.CharSequence
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.asBros.converter.PDF;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class DebugActivity
extends Activity {
    private String[] exceptionMessages = new String[]{"Invalid string operation\n", "Invalid list operation\n", "Invalid arithmetical operation\n", "Invalid toNumber block operation\n", "Invalid intent operation"};
    private String[] exceptionTypes = new String[]{"StringIndexOutOfBoundsException", "IndexOutOfBoundsException", "ArithmeticException", "NumberFormatException", "ActivityNotFoundException"};

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void onCreate(Bundle bundle) {
        String string2;
        block6 : {
            String string3;
            block5 : {
                int n = 0;
                super.onCreate(bundle);
                Intent intent = this.getIntent();
                string3 = "";
                if (intent == null) break block5;
                string2 = intent.getStringExtra("error");
                String[] arrstring = string2.split("\n");
                try {
                    do {
                        block8 : {
                            boolean bl;
                            block7 : {
                                String string4;
                                if (n >= this.exceptionTypes.length) break block7;
                                if (!arrstring[0].contains((CharSequence)this.exceptionTypes[n])) break block8;
                                string3 = this.exceptionMessages[n];
                                int n2 = arrstring[0].indexOf(this.exceptionTypes[n]) + this.exceptionTypes[n].length();
                                string3 = String.valueOf((Object)string3) + arrstring[0].substring(n2, arrstring[0].length());
                                string3 = string4 = String.valueOf((Object)string3) + "\n\nDetailed error message:\n" + string2;
                            }
                            if (!(bl = string3.isEmpty())) break block5;
                            break block6;
                        }
                        ++n;
                    } while (true);
                }
                catch (Exception exception) {
                    string2 = String.valueOf((Object)string3) + "\n\nError while getting error: " + Log.getStackTraceString((Throwable)exception);
                    break block6;
                }
            }
            string2 = string3;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)this);
        builder.setTitle((CharSequence)"An error occurred");
        builder.setMessage((CharSequence)string2);
        builder.setPositiveButton((CharSequence)"End Application", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                DebugActivity.this.finish();
            }
        });
        builder.create().show();
    }

}

