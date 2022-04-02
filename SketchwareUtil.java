/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.ActivityNotFoundException
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.GradientDrawable
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.net.Uri
 *  android.util.DisplayMetrics
 *  android.util.SparseBooleanArray
 *  android.util.TypedValue
 *  android.view.View
 *  android.view.inputmethod.InputMethodManager
 *  android.widget.ListView
 *  android.widget.TextView
 *  android.widget.Toast
 *  java.io.ByteArrayOutputStream
 *  java.io.File
 *  java.io.IOException
 *  java.io.InputStream
 *  java.lang.CharSequence
 *  java.lang.Double
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.Collections
 *  java.util.Comparator
 *  java.util.HashMap
 *  java.util.Iterator
 *  java.util.Map
 *  java.util.Map$Entry
 *  java.util.Random
 *  java.util.Set
 */
package com.asBros.converter.PDF;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class SketchwareUtil {
    public static int BOTTOM;
    public static int CENTER;
    public static int TOP;

    static {
        TOP = 1;
        CENTER = 2;
        BOTTOM = 3;
    }

    public static void CropImage(Activity activity, String string2, int n) {
        try {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(Uri.fromFile((File)new File(string2)), "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 280);
            intent.putExtra("outputY", 280);
            intent.putExtra("return-data", false);
            activity.startActivityForResult(intent, n);
            return;
        }
        catch (ActivityNotFoundException activityNotFoundException) {
            Toast.makeText((Context)activity, (CharSequence)"Your device doesn't support the crop action!", (int)0).show();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void CustomToast(Context context, String string2, int n, int n2, int n3, int n4, int n5) {
        Toast toast = Toast.makeText((Context)context, (CharSequence)string2, (int)0);
        View view = toast.getView();
        TextView textView = (TextView)view.findViewById(16908299);
        textView.setTextSize((float)n2);
        textView.setTextColor(n);
        textView.setGravity(17);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(n3);
        gradientDrawable.setCornerRadius((float)n4);
        view.setBackgroundDrawable((Drawable)gradientDrawable);
        view.setPadding(15, 10, 15, 10);
        view.setElevation(10.0f);
        switch (n5) {
            case 1: {
                toast.setGravity(48, 0, 150);
                break;
            }
            case 2: {
                toast.setGravity(17, 0, 0);
                break;
            }
            case 3: {
                toast.setGravity(80, 0, 150);
                break;
            }
        }
        toast.show();
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void CustomToastWithIcon(Context context, String string2, int n, int n2, int n3, int n4, int n5, int n6) {
        Toast toast = Toast.makeText((Context)context, (CharSequence)string2, (int)0);
        View view = toast.getView();
        TextView textView = (TextView)view.findViewById(16908299);
        textView.setTextSize((float)n2);
        textView.setTextColor(n);
        textView.setCompoundDrawablesWithIntrinsicBounds(n6, 0, 0, 0);
        textView.setGravity(17);
        textView.setCompoundDrawablePadding(10);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(n3);
        gradientDrawable.setCornerRadius((float)n4);
        view.setBackgroundDrawable((Drawable)gradientDrawable);
        view.setPadding(10, 10, 10, 10);
        view.setElevation(10.0f);
        switch (n5) {
            case 1: {
                toast.setGravity(48, 0, 150);
                break;
            }
            case 2: {
                toast.setGravity(17, 0, 0);
                break;
            }
            case 3: {
                toast.setGravity(80, 0, 150);
                break;
            }
        }
        toast.show();
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String copyFromInputStream(InputStream inputStream) {
        int n;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] arrby = new byte[1024];
        do {
            n = inputStream.read(arrby);
            if (n != -1) break block4;
            byteArrayOutputStream.close();
            inputStream.close();
            break;
        } while (true);
        catch (IOException iOException) {
            return byteArrayOutputStream.toString();
        }
        {
            block4 : {
                return byteArrayOutputStream.toString();
            }
            byteArrayOutputStream.write(arrby, 0, n);
            continue;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void getAllKeysFromMap(Map<String, Object> map, ArrayList<String> arrayList) {
        if (arrayList != null) {
            arrayList.clear();
            if (map != null && map.size() >= 1) {
                Iterator iterator = map.entrySet().iterator();
                while (iterator.hasNext()) {
                    arrayList.add((Object)((String)((Map.Entry)iterator.next()).getKey()));
                }
            }
        }
    }

    public static ArrayList<Double> getCheckedItemPositionsToArray(ListView listView) {
        ArrayList arrayList = new ArrayList();
        SparseBooleanArray sparseBooleanArray = listView.getCheckedItemPositions();
        int n = 0;
        while (n < sparseBooleanArray.size()) {
            if (sparseBooleanArray.valueAt(n)) {
                arrayList.add((Object)sparseBooleanArray.keyAt(n));
            }
            ++n;
        }
        return arrayList;
    }

    public static float getDip(Context context, int n) {
        return TypedValue.applyDimension((int)1, (float)n, (DisplayMetrics)context.getResources().getDisplayMetrics());
    }

    public static int getDisplayHeightPixels(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getDisplayWidthPixels(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getLocationX(View view) {
        int[] arrn = new int[2];
        view.getLocationInWindow(arrn);
        return arrn[0];
    }

    public static int getLocationY(View view) {
        int[] arrn = new int[2];
        view.getLocationInWindow(arrn);
        return arrn[1];
    }

    public static int getRandom(int n, int n2) {
        return n + new Random().nextInt(1 + (n2 - n));
    }

    public static void hideKeyboard(Context context) {
        ((InputMethodManager)context.getSystemService("input_method")).toggleSoftInput(1, 0);
    }

    public static boolean isConnected(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static void showKeyboard(Context context) {
        ((InputMethodManager)context.getSystemService("input_method")).toggleSoftInput(2, 0);
    }

    public static void showMessage(Context context, String string2) {
        Toast.makeText((Context)context, (CharSequence)string2, (int)0).show();
    }

    public static void sortListMap(ArrayList<HashMap<String, Object>> arrayList, final String string2, boolean bl, final boolean bl2) {
        Collections.sort(arrayList, (Comparator)new Comparator<HashMap<String, Object>>(){

            /*
             * Enabled aggressive block sorting
             */
            public int compare(HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2) {
                if (val$isNumber) {
                    int n = Integer.valueOf((String)hashMap.get((Object)string2).toString());
                    int n2 = Integer.valueOf((String)hashMap2.get((Object)string2).toString());
                    if (bl2) {
                        if (n < n2) return -1;
                        return n < n2;
                    }
                    if (n <= n2) return n > n2;
                    return -1;
                }
                if (!bl2) return hashMap2.get((Object)string2).toString().compareTo(hashMap.get((Object)string2).toString());
                return hashMap.get((Object)string2).toString().compareTo(hashMap2.get((Object)string2).toString());
            }
        });
    }

}

