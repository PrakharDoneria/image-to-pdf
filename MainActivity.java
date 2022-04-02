/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.ActivityNotFoundException
 *  android.content.ClipData
 *  android.content.ClipData$Item
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.pdf.PdfDocument
 *  android.graphics.pdf.PdfDocument$Page
 *  android.graphics.pdf.PdfDocument$PageInfo
 *  android.graphics.pdf.PdfDocument$PageInfo$Builder
 *  android.net.Uri
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.Environment
 *  android.os.Parcelable
 *  android.text.Editable
 *  android.util.DisplayMetrics
 *  android.util.SparseBooleanArray
 *  android.util.TypedValue
 *  android.view.KeyEvent
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.BaseAdapter
 *  android.widget.Button
 *  android.widget.EditText
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.TextView
 *  android.widget.TextView$OnEditorActionListener
 *  android.widget.Toast
 *  androidx.appcompat.app.AppCompatActivity
 *  androidx.core.app.ActivityCompat
 *  androidx.core.content.ContextCompat
 *  androidx.core.content.FileProvider
 *  java.io.File
 *  java.io.FileOutputStream
 *  java.io.OutputStream
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Deprecated
 *  java.lang.Double
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.Random
 */
package com.asBros.converter.PDF;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import com.asBros.converter.PDF.FileUtil;
import com.asBros.converter.PDF.Info1Activity;
import com.asBros.converter.PDF.SketchwareUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity
extends AppCompatActivity {
    public final int REQ_CD_CAM = 102;
    public final int REQ_CD_FP = 101;
    private File _file_cam;
    private Button button_clear;
    private Button button_open;
    private Button button_save;
    private Intent cam = new Intent("android.media.action.IMAGE_CAPTURE");
    private AlertDialog.Builder dialog;
    private EditText edittext1;
    private Intent fp = new Intent("android.intent.action.GET_CONTENT");
    private Intent i = new Intent();
    private ArrayList<HashMap<String, Object>> imagelist = new ArrayList();
    private ImageView imageview1;
    private ImageView imageview2;
    private LinearLayout linear1;
    private LinearLayout linear2;
    private LinearLayout linear4;
    private LinearLayout linear5;
    private ArrayList<String> list = new ArrayList();
    private ListView listview1;
    private double n = 0.0;
    private String path1 = "";
    private String save_path = "";
    private TextView textview1;

    static /* synthetic */ AlertDialog.Builder access$11(MainActivity mainActivity) {
        return mainActivity.dialog;
    }

    static /* synthetic */ void access$4(MainActivity mainActivity, double d) {
        mainActivity.n = d;
    }

    static /* synthetic */ double access$5(MainActivity mainActivity) {
        return mainActivity.n;
    }

    static /* synthetic */ void access$6(MainActivity mainActivity, String string2) {
        mainActivity.path1 = string2;
    }

    static /* synthetic */ String access$7(MainActivity mainActivity) {
        return mainActivity.path1;
    }

    static /* synthetic */ EditText access$8(MainActivity mainActivity) {
        return mainActivity.edittext1;
    }

    static /* synthetic */ void access$9(MainActivity mainActivity, String string2) {
        mainActivity.save_path = string2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void initialize(Bundle bundle) {
        this.linear5 = (LinearLayout)this.findViewById(2131230949);
        this.linear1 = (LinearLayout)this.findViewById(2131230946);
        this.linear4 = (LinearLayout)this.findViewById(2131230948);
        this.linear2 = (LinearLayout)this.findViewById(2131230947);
        this.listview1 = (ListView)this.findViewById(2131230952);
        this.imageview1 = (ImageView)this.findViewById(2131230928);
        this.textview1 = (TextView)this.findViewById(2131231147);
        this.imageview2 = (ImageView)this.findViewById(2131230929);
        this.button_open = (Button)this.findViewById(2131230816);
        this.edittext1 = (EditText)this.findViewById(2131230887);
        this.button_clear = (Button)this.findViewById(2131230815);
        this.button_save = (Button)this.findViewById(2131230817);
        this.fp.setType("image/*");
        this.fp.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
        this.dialog = new AlertDialog.Builder((Context)this);
        this._file_cam = FileUtil.createNewPictureFile(this.getApplicationContext());
        Uri uri = Build.VERSION.SDK_INT >= 24 ? FileProvider.getUriForFile((Context)this.getApplicationContext(), (String)(String.valueOf((Object)this.getApplicationContext().getPackageName()) + ".provider"), (File)this._file_cam) : Uri.fromFile((File)this._file_cam);
        this.cam.putExtra("output", (Parcelable)uri);
        this.cam.addFlags(1);
        this.imageview2.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                MainActivity.this.i.setClass(MainActivity.this.getApplicationContext(), Info1Activity.class);
                MainActivity.this.startActivity(MainActivity.this.i);
            }
        });
        this.button_open.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                MainActivity.this.startActivityForResult(MainActivity.this.fp, 101);
            }
        });
        this.button_clear.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                MainActivity.this.imagelist.clear();
                ((BaseAdapter)MainActivity.this.listview1.getAdapter()).notifyDataSetChanged();
            }
        });
        this.button_save.setOnClickListener(new View.OnClickListener(){

            /*
             * Unable to fully structure code
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             * Lifted jumps to return sites
             */
            public void onClick(View var1_1) {
                var2_2 = 0;
                try {
                    var3_3 = new PdfDocument();
                    var5_4 = new int[MainActivity.access$0(MainActivity.this).size()];
                    MainActivity.access$4(MainActivity.this, 0.0);
                    var6_5 = 0;
                    var7_6 = 0;
                    do {
                        if (var6_5 >= MainActivity.access$0(MainActivity.this).size()) break;
                        MainActivity.access$6(MainActivity.this, ((HashMap)MainActivity.access$0(MainActivity.this).get((int)MainActivity.access$5(MainActivity.this))).get((Object)"pic").toString());
                        var5_4[(int)MainActivity.access$5((MainActivity)MainActivity.this)] = var8_7 = FileUtil.decodeSampleBitmapFromPath(MainActivity.access$7(MainActivity.this), 1024, 1024).getWidth();
                        if (var8_7 <= var7_6) {
                            var8_7 = var7_6;
                        }
                        var9_8 = MainActivity.this;
                        MainActivity.access$4(var9_8, 1.0 + MainActivity.access$5(var9_8));
                        ++var6_5;
                        var7_6 = var8_7;
                    } while (true);
                    MainActivity.access$4(MainActivity.this, 0.0);
                    do {
                        if (var2_2 >= MainActivity.access$0(MainActivity.this).size()) {
                            if (MainActivity.access$8(MainActivity.this).getText().toString().trim().equals((Object)"")) break;
                            MainActivity.access$9(MainActivity.this, FileUtil.getPublicDir(Environment.DIRECTORY_DOWNLOADS).concat("/".concat(MainActivity.access$8(MainActivity.this).getText().toString()).concat(".pdf")));
                            ** break block8
                        }
                        var10_9 = FileUtil.decodeSampleBitmapFromPath(((HashMap)MainActivity.access$0(MainActivity.this).get((int)MainActivity.access$5(MainActivity.this))).get((Object)"pic").toString(), 1024, 1024);
                        var11_10 = var3_3.startPage(new PdfDocument.PageInfo.Builder(var7_6, var10_9.getHeight(), 1 + (int)MainActivity.access$5(MainActivity.this)).create());
                        var12_11 = var11_10.getCanvas();
                        var12_11.drawColor(-1);
                        var12_11.drawBitmap(var10_9, (float)((var7_6 - var5_4[(int)MainActivity.access$5(MainActivity.this)]) / 2), 0.0f, null);
                        var3_3.finishPage(var11_10);
                        var13_12 = MainActivity.this;
                        MainActivity.access$4(var13_12, 1.0 + MainActivity.access$5(var13_12));
                        ++var2_2;
                    } while (true);
                    SketchwareUtil.showMessage(MainActivity.this.getApplicationContext(), "Enter PDF File name");
                }
                catch (Exception var4_14) {
                    MainActivity.this.showMessage(var4_14.toString());
                    return;
                }
lbl-1000: // 2 sources:
                {
                    
                    var14_13 = new FileOutputStream(new File(MainActivity.access$10(MainActivity.this)));
                    var3_3.writeTo((OutputStream)var14_13);
                    var3_3.close();
                    var14_13.close();
                    MainActivity.access$11(MainActivity.this).setTitle((CharSequence)"Saved");
                    MainActivity.access$11(MainActivity.this).setMessage((CharSequence)"Your PDF has been saved");
                    MainActivity.access$11(MainActivity.this).setPositiveButton((CharSequence)"Open File", new DialogInterface.OnClickListener(){

                        public void onClick(DialogInterface dialogInterface, int n) {
                            Intent intent = new Intent("android.intent.action.VIEW");
                            intent.addFlags(1);
                            intent.setDataAndType(FileProvider.getUriForFile((Context)4.this.MainActivity.this, (String)(String.valueOf((Object)4.this.MainActivity.this.getApplicationContext().getPackageName()) + ".provider"), (File)new File(4.this.MainActivity.this.save_path)), "application/pdf");
                            try {
                                4.this.MainActivity.this.startActivity(intent);
                                return;
                            }
                            catch (ActivityNotFoundException activityNotFoundException) {
                                4.this.MainActivity.this.showMessage("No Pdf reader found to open this file");
                                return;
                            }
                        }
                    });
                    MainActivity.access$11(MainActivity.this).setNeutralButton((CharSequence)"Dismiss", new DialogInterface.OnClickListener(){

                        public void onClick(DialogInterface dialogInterface, int n) {
                        }
                    });
                    MainActivity.access$11(MainActivity.this).create().show();
                    return;
                }
            }

        });
    }

    private void initializeLogic() {
        this.edittext1.setSingleLine(true);
        this._set_imeGo_Click((TextView)this.edittext1, (View)this.button_save);
    }

    public void _set_imeGo_Click(TextView textView, final View view) {
        textView.setOnEditorActionListener(new TextView.OnEditorActionListener(){

            public boolean onEditorAction(TextView textView, int n, KeyEvent keyEvent) {
                if (n == 2) {
                    view.performClick();
                    return true;
                }
                return false;
            }
        });
    }

    @Deprecated
    public ArrayList<Double> getCheckedItemPositionsToArray(ListView listView) {
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

    @Deprecated
    public float getDip(int n) {
        return TypedValue.applyDimension((int)1, (float)n, (DisplayMetrics)this.getResources().getDisplayMetrics());
    }

    @Deprecated
    public int getDisplayHeightPixels() {
        return this.getResources().getDisplayMetrics().heightPixels;
    }

    @Deprecated
    public int getDisplayWidthPixels() {
        return this.getResources().getDisplayMetrics().widthPixels;
    }

    @Deprecated
    public int getLocationX(View view) {
        int[] arrn = new int[2];
        view.getLocationInWindow(arrn);
        return arrn[0];
    }

    @Deprecated
    public int getLocationY(View view) {
        int[] arrn = new int[2];
        view.getLocationInWindow(arrn);
        return arrn[1];
    }

    @Deprecated
    public int getRandom(int n, int n2) {
        return n + new Random().nextInt(1 + (n2 - n));
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     */
    protected void onActivityResult(int var1_1, int var2_2, Intent var3_3) {
        var4_4 = 0;
        super.onActivityResult(var1_1, var2_2, var3_3);
        switch (var1_1) {
            case 101: {
                if (var2_2 != -1) ** break;
                var5_5 = new ArrayList();
                if (var3_3 != null) {
                    if (var3_3.getClipData() != null) {
                        var10_6 = 0;
                        break;
                    }
                    var5_5.add((Object)FileUtil.convertUriToFilePath(this.getApplicationContext(), var3_3.getData()));
                }
                do {
                    this.n = 0.0;
                    do {
                        if (var4_4 >= var5_5.size()) {
                            this.listview1.setAdapter((ListAdapter)new Listview1Adapter(this.imagelist));
                            ((BaseAdapter)this.listview1.getAdapter()).notifyDataSetChanged();
                            return;
                        }
                        var6_8 = new HashMap();
                        var6_8.put((Object)"pic", var5_5.get((int)this.n));
                        this.imagelist.add((Object)var6_8);
                        this.n = 1.0 + this.n;
                        ++var4_4;
                    } while (true);
                    break;
                } while (true);
            }
            default: {
                return;
            }
        }
        do {
            if (var10_6 >= var3_3.getClipData().getItemCount()) ** continue;
            var11_7 = var3_3.getClipData().getItemAt(var10_6);
            var5_5.add((Object)FileUtil.convertUriToFilePath(this.getApplicationContext(), var11_7.getUri()));
            ++var10_6;
        } while (true);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131427374);
        this.initialize(bundle);
        if (ContextCompat.checkSelfPermission((Context)this, (String)"android.permission.CAMERA") == -1 || ContextCompat.checkSelfPermission((Context)this, (String)"android.permission.READ_EXTERNAL_STORAGE") == -1 || ContextCompat.checkSelfPermission((Context)this, (String)"android.permission.WRITE_EXTERNAL_STORAGE") == -1) {
            ActivityCompat.requestPermissions((Activity)this, (String[])new String[]{"android.permission.CAMERA", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, (int)1000);
            return;
        }
        this.initializeLogic();
    }

    public void onRequestPermissionsResult(int n, String[] arrstring, int[] arrn) {
        super.onRequestPermissionsResult(n, arrstring, arrn);
        if (n == 1000) {
            this.initializeLogic();
        }
    }

    @Deprecated
    public void showMessage(String string2) {
        Toast.makeText((Context)this.getApplicationContext(), (CharSequence)string2, (int)0).show();
    }

    public class Listview1Adapter
    extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        public Listview1Adapter(ArrayList<HashMap<String, Object>> arrayList) {
            this._data = arrayList;
        }

        public int getCount() {
            return this._data.size();
        }

        public HashMap<String, Object> getItem(int n) {
            return (HashMap)this._data.get(n);
        }

        public long getItemId(int n) {
            return n;
        }

        public View getView(int n, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = (LayoutInflater)MainActivity.this.getBaseContext().getSystemService("layout_inflater");
            if (view == null) {
                view = layoutInflater.inflate(2131427373, null);
            }
            (LinearLayout)view.findViewById(2131230946);
            ((ImageView)view.findViewById(2131230928)).setImageBitmap(FileUtil.decodeSampleBitmapFromPath(((HashMap)MainActivity.this.imagelist.get(n)).get((Object)"pic").toString(), 1024, 1024));
            return view;
        }
    }

}

