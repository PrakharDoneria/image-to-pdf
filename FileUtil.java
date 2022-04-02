/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.Context
 *  android.database.Cursor
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$CompressFormat
 *  android.graphics.Bitmap$Config
 *  android.graphics.BitmapFactory
 *  android.graphics.BitmapFactory$Options
 *  android.graphics.Canvas
 *  android.graphics.ColorFilter
 *  android.graphics.ColorMatrix
 *  android.graphics.ColorMatrixColorFilter
 *  android.graphics.LightingColorFilter
 *  android.graphics.Matrix
 *  android.graphics.Paint
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.PorterDuffXfermode
 *  android.graphics.Rect
 *  android.graphics.RectF
 *  android.graphics.Xfermode
 *  android.media.ExifInterface
 *  android.net.Uri
 *  android.os.Environment
 *  android.provider.DocumentsContract
 *  android.provider.MediaStore
 *  android.provider.MediaStore$Audio
 *  android.provider.MediaStore$Audio$Media
 *  android.provider.MediaStore$Images
 *  android.provider.MediaStore$Images$Media
 *  android.provider.MediaStore$Video
 *  android.provider.MediaStore$Video$Media
 *  android.text.TextUtils
 *  java.io.File
 *  java.io.FileOutputStream
 *  java.io.FileWriter
 *  java.io.IOException
 *  java.io.OutputStream
 *  java.lang.CharSequence
 *  java.lang.Exception
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.net.URLDecoder
 *  java.text.SimpleDateFormat
 *  java.util.ArrayList
 *  java.util.Date
 */
package com.asBros.converter.PDF;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileUtil {
    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int n, int n2) {
        int n3 = options.outWidth;
        int n4 = options.outHeight;
        int n5 = 1;
        if (n4 <= n2 && n3 <= n) return n5;
        int n6 = n4 / 2;
        int n7 = n3 / 2;
        while (n6 / n5 >= n2 && n7 / n5 >= n) {
            n5 *= 2;
        }
        return n5;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String convertUriToFilePath(Context var0, Uri var1_1) {
        block10 : {
            block8 : {
                block11 : {
                    block9 : {
                        if (!DocumentsContract.isDocumentUri((Context)var0, (Uri)var1_1)) break block8;
                        if (!FileUtil.isExternalStorageDocument(var1_1)) break block9;
                        var11_2 = DocumentsContract.getDocumentId((Uri)var1_1).split(":");
                        if (!"primary".equalsIgnoreCase(var11_2[0])) ** GOTO lbl-1000
                        var2_3 = (Object)Environment.getExternalStorageDirectory() + "/" + var11_2[1];
                        break block10;
                    }
                    if (!FileUtil.isDownloadsDocument(var1_1)) break block11;
                    var10_6 = DocumentsContract.getDocumentId((Uri)var1_1);
                    if (!TextUtils.isEmpty((CharSequence)var10_6) && var10_6.startsWith("raw:")) {
                        return var10_6.replaceFirst("raw:", "");
                    }
                    var2_3 = FileUtil.getDataColumn(var0, ContentUris.withAppendedId((Uri)Uri.parse((String)"content://downloads/public_downloads"), (long)Long.valueOf((String)var10_6)), null, null);
                    break block10;
                }
                if (FileUtil.isMediaDocument(var1_1)) {
                    var6_7 = DocumentsContract.getDocumentId((Uri)var1_1).split(":");
                    var7_8 = var6_7[0];
                    var8_9 /* !! */  = "image".equals((Object)var7_8) != false ? MediaStore.Images.Media.EXTERNAL_CONTENT_URI : ("video".equals((Object)var7_8) != false ? MediaStore.Video.Media.EXTERNAL_CONTENT_URI : ("audio".equals((Object)var7_8) != false ? MediaStore.Audio.Media.EXTERNAL_CONTENT_URI : null));
                }
                ** GOTO lbl-1000
            }
            if ("content".equalsIgnoreCase(var1_1.getScheme())) {
                var2_3 = FileUtil.getDataColumn(var0, var1_1, null, null);
            } else if ("file".equalsIgnoreCase(var1_1.getScheme())) {
                var2_3 = var1_1.getPath();
            } else lbl-1000: // 3 sources:
            {
                var2_3 = null;
            }
            break block10;
            var9_10 = new String[]{var6_7[1]};
            var2_3 = FileUtil.getDataColumn(var0, var8_9 /* !! */ , "_id=?", var9_10);
        }
        var3_4 = null;
        if (var2_3 == null) return var3_4;
        try {
            var5_5 = URLDecoder.decode((String)var2_3, (String)"UTF-8");
            return var5_5;
        }
        catch (Exception var4_11) {
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void copyDir(String string2, String string3) {
        File[] arrfile = new File(string2).listFiles();
        File file = new File(string3);
        if (!file.exists()) {
            file.mkdirs();
        }
        int n = arrfile.length;
        int n2 = 0;
        while (n2 < n) {
            File file2 = arrfile[n2];
            if (file2.isFile()) {
                FileUtil.copyFile(file2.getPath(), String.valueOf((Object)string3) + "/" + file2.getName());
            } else if (file2.isDirectory()) {
                FileUtil.copyDir(file2.getPath(), String.valueOf((Object)string3) + "/" + file2.getName());
            }
            ++n2;
        }
        return;
    }

    /*
     * Exception decompiling
     */
    public static void copyFile(String var0, String var1_1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [2[TRYBLOCK]], but top level block is 23[UNCONDITIONALDOLOOP]
        // org.benf.cfr.reader.b.a.a.j.a(Op04StructuredStatement.java:432)
        // org.benf.cfr.reader.b.a.a.j.d(Op04StructuredStatement.java:484)
        // org.benf.cfr.reader.b.a.a.i.a(Op03SimpleStatement.java:607)
        // org.benf.cfr.reader.b.f.a(CodeAnalyser.java:692)
        // org.benf.cfr.reader.b.f.a(CodeAnalyser.java:182)
        // org.benf.cfr.reader.b.f.a(CodeAnalyser.java:127)
        // org.benf.cfr.reader.entities.attributes.f.c(AttributeCode.java:96)
        // org.benf.cfr.reader.entities.g.p(Method.java:396)
        // org.benf.cfr.reader.entities.d.e(ClassFile.java:890)
        // org.benf.cfr.reader.entities.d.b(ClassFile.java:792)
        // org.benf.cfr.reader.b.a(Driver.java:128)
        // org.benf.cfr.reader.a.a(CfrDriverImpl.java:63)
        // com.njlabs.showjava.decompilers.JavaExtractionWorker.decompileWithCFR(JavaExtractionWorker.kt:61)
        // com.njlabs.showjava.decompilers.JavaExtractionWorker.doWork(JavaExtractionWorker.kt:130)
        // com.njlabs.showjava.decompilers.BaseDecompiler.withAttempt(BaseDecompiler.kt:108)
        // com.njlabs.showjava.workers.DecompilerWorker$b.run(DecompilerWorker.kt:118)
        // java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1162)
        // java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:636)
        // java.lang.Thread.run(Thread.java:764)
        throw new IllegalStateException("Decompilation failed");
    }

    private static void createNewFile(String string2) {
        int n = string2.lastIndexOf(File.separator);
        if (n > 0) {
            FileUtil.makeDir(string2.substring(0, n));
        }
        File file = new File(string2);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            return;
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            return;
        }
    }

    public static File createNewPictureFile(Context context) {
        String string2 = String.valueOf((Object)new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())) + ".jpg";
        return new File(String.valueOf((Object)context.getExternalFilesDir(Environment.DIRECTORY_DCIM).getAbsolutePath()) + File.separator + string2);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void cropBitmapFileFromCenter(String string2, String string3, int n, int n2) {
        int n3;
        Bitmap bitmap;
        int n4;
        block7 : {
            block6 : {
                if (!FileUtil.isExistFile(string2)) break block6;
                bitmap = BitmapFactory.decodeFile((String)string2);
                n4 = bitmap.getWidth();
                n3 = bitmap.getHeight();
                if (n4 >= n || n3 >= n2) break block7;
            }
            return;
        }
        int n5 = n4 > n ? (n4 - n) / 2 : 0;
        int n6 = 0;
        if (n3 > n2) {
            n6 = (n3 - n2) / 2;
        }
        if (n > n4) {
            n = n4;
        }
        if (n2 > n3) {
            n2 = n3;
        }
        FileUtil.saveBitmap(Bitmap.createBitmap((Bitmap)bitmap, (int)n5, (int)n6, (int)n, (int)n2), string3);
    }

    public static Bitmap decodeSampleBitmapFromPath(String string2, int n, int n2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile((String)string2, (BitmapFactory.Options)options);
        options.inSampleSize = FileUtil.calculateInSampleSize(options, n, n2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile((String)string2, (BitmapFactory.Options)options);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void deleteFile(String string2) {
        File file = new File(string2);
        if (!file.exists()) {
            return;
        }
        if (file.isFile()) {
            file.delete();
            return;
        }
        File[] arrfile = file.listFiles();
        if (arrfile != null) {
            for (File file2 : arrfile) {
                if (file2.isDirectory()) {
                    FileUtil.deleteFile(file2.getAbsolutePath());
                }
                if (!file2.isFile()) continue;
                file2.delete();
            }
        }
        file.delete();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static String getDataColumn(Context var0, Uri var1_1, String var2_2, String[] var3_3) {
        var4_4 = new String[]{"_data"};
        var8_5 = var0.getContentResolver().query(var1_1, var4_4, var2_2, var3_3, null);
        if (var8_5 == null) ** GOTO lbl12
        if (!var8_5.moveToFirst()) ** GOTO lbl12
        var10_6 = var8_5.getString(var8_5.getColumnIndexOrThrow("_data"));
        if (var8_5 == null) return var10_6;
        var8_5.close();
        return var10_6;
lbl12: // 2 sources:
        if (var8_5 == null) return null;
        var8_5.close();
        return null;
        catch (Throwable var9_7) {
            var6_8 = var9_7;
            if (var8_5 == null) throw var6_8;
            try {
                var8_5.close();
                throw var6_8;
            }
            catch (Throwable var5_9) {}
            ** GOTO lbl-1000
            catch (Throwable var5_11) {
                var6_8 = null;
            }
lbl-1000: // 2 sources:
            {
                if (var6_8 != null) {
                    if (var6_8 != var5_10) {
                        var6_8.addSuppressed(var5_10);
                    }
                    var5_10 = var6_8;
                }
                try {
                    throw var5_10;
                }
                catch (Exception var7_12) {
                    return null;
                }
            }
        }
    }

    public static String getExternalStorageDir() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static long getFileLength(String string2) {
        if (!FileUtil.isExistFile(string2)) {
            return 0L;
        }
        return new File(string2).length();
    }

    public static int getJpegRotate(String string2) {
        int n;
        try {
            n = new ExifInterface(string2).getAttributeInt("Orientation", -1);
        }
        catch (IOException iOException) {
            return 0;
        }
        switch (n) {
            default: {
                return 0;
            }
            case 6: {
                return 90;
            }
            case 3: {
                return 180;
            }
            case 8: 
        }
        return 270;
    }

    public static String getPackageDataDir(Context context) {
        return context.getExternalFilesDir(null).getAbsolutePath();
    }

    public static String getPublicDir(String string2) {
        return Environment.getExternalStoragePublicDirectory((String)string2).getAbsolutePath();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Bitmap getScaledBitmap(String string2, int n) {
        int n2;
        int n3;
        int n4;
        Bitmap bitmap = BitmapFactory.decodeFile((String)string2);
        int n5 = bitmap.getWidth();
        if (n5 > (n3 = bitmap.getHeight())) {
            n2 = (int)((float)n / (float)n5 * (float)n3);
            n4 = n;
            do {
                return Bitmap.createScaledBitmap((Bitmap)bitmap, (int)n4, (int)n2, (boolean)true);
                break;
            } while (true);
        }
        n4 = (int)((float)n / (float)n3 * (float)n5);
        n2 = n;
        return Bitmap.createScaledBitmap((Bitmap)bitmap, (int)n4, (int)n2, (boolean)true);
    }

    public static boolean isDirectory(String string2) {
        if (!FileUtil.isExistFile(string2)) {
            return false;
        }
        return new File(string2).isDirectory();
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals((Object)uri.getAuthority());
    }

    public static boolean isExistFile(String string2) {
        return new File(string2).exists();
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals((Object)uri.getAuthority());
    }

    public static boolean isFile(String string2) {
        if (!FileUtil.isExistFile(string2)) {
            return false;
        }
        return new File(string2).isFile();
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals((Object)uri.getAuthority());
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void listDir(String string2, ArrayList<String> arrayList) {
        File[] arrfile;
        File file = new File(string2);
        if (file.exists() && !file.isFile() && (arrfile = file.listFiles()) != null && arrfile.length > 0 && arrayList != null) {
            arrayList.clear();
            int n = arrfile.length;
            for (int i = 0; i < n; ++i) {
                arrayList.add((Object)arrfile[i].getAbsolutePath());
            }
        }
    }

    public static void makeDir(String string2) {
        if (!FileUtil.isExistFile(string2)) {
            new File(string2).mkdirs();
        }
    }

    public static void moveFile(String string2, String string3) {
        FileUtil.copyFile(string2, string3);
        FileUtil.deleteFile(string2);
    }

    /*
     * Exception decompiling
     */
    public static String readFile(String var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 14[UNCONDITIONALDOLOOP]
        // org.benf.cfr.reader.b.a.a.j.a(Op04StructuredStatement.java:432)
        // org.benf.cfr.reader.b.a.a.j.d(Op04StructuredStatement.java:484)
        // org.benf.cfr.reader.b.a.a.i.a(Op03SimpleStatement.java:607)
        // org.benf.cfr.reader.b.f.a(CodeAnalyser.java:692)
        // org.benf.cfr.reader.b.f.a(CodeAnalyser.java:182)
        // org.benf.cfr.reader.b.f.a(CodeAnalyser.java:127)
        // org.benf.cfr.reader.entities.attributes.f.c(AttributeCode.java:96)
        // org.benf.cfr.reader.entities.g.p(Method.java:396)
        // org.benf.cfr.reader.entities.d.e(ClassFile.java:890)
        // org.benf.cfr.reader.entities.d.b(ClassFile.java:792)
        // org.benf.cfr.reader.b.a(Driver.java:128)
        // org.benf.cfr.reader.a.a(CfrDriverImpl.java:63)
        // com.njlabs.showjava.decompilers.JavaExtractionWorker.decompileWithCFR(JavaExtractionWorker.kt:61)
        // com.njlabs.showjava.decompilers.JavaExtractionWorker.doWork(JavaExtractionWorker.kt:130)
        // com.njlabs.showjava.decompilers.BaseDecompiler.withAttempt(BaseDecompiler.kt:108)
        // com.njlabs.showjava.workers.DecompilerWorker$b.run(DecompilerWorker.kt:118)
        // java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1162)
        // java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:636)
        // java.lang.Thread.run(Thread.java:764)
        throw new IllegalStateException("Decompilation failed");
    }

    public static void resizeBitmapFileRetainRatio(String string2, String string3, int n) {
        if (!FileUtil.isExistFile(string2)) {
            return;
        }
        FileUtil.saveBitmap(FileUtil.getScaledBitmap(string2, n), string3);
    }

    public static void resizeBitmapFileToCircle(String string2, String string3) {
        if (!FileUtil.isExistFile(string2)) {
            return;
        }
        Bitmap bitmap = BitmapFactory.decodeFile((String)string2);
        Bitmap bitmap2 = Bitmap.createBitmap((int)bitmap.getWidth(), (int)bitmap.getHeight(), (Bitmap.Config)Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap2);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawCircle((float)(bitmap.getWidth() / 2), (float)(bitmap.getHeight() / 2), (float)(bitmap.getWidth() / 2), paint);
        paint.setXfermode((Xfermode)new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        FileUtil.saveBitmap(bitmap2, string3);
    }

    public static void resizeBitmapFileToSquare(String string2, String string3, int n) {
        if (!FileUtil.isExistFile(string2)) {
            return;
        }
        FileUtil.saveBitmap(Bitmap.createScaledBitmap((Bitmap)BitmapFactory.decodeFile((String)string2), (int)n, (int)n, (boolean)true), string3);
    }

    public static void resizeBitmapFileWithRoundedBorder(String string2, String string3, int n) {
        if (!FileUtil.isExistFile(string2)) {
            return;
        }
        Bitmap bitmap = BitmapFactory.decodeFile((String)string2);
        Bitmap bitmap2 = Bitmap.createBitmap((int)bitmap.getWidth(), (int)bitmap.getHeight(), (Bitmap.Config)Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap2);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        float f = n;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode((Xfermode)new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        FileUtil.saveBitmap(bitmap2, string3);
    }

    public static void rotateBitmapFile(String string2, String string3, float f) {
        if (!FileUtil.isExistFile(string2)) {
            return;
        }
        Bitmap bitmap = BitmapFactory.decodeFile((String)string2);
        Matrix matrix = new Matrix();
        matrix.postRotate(f);
        FileUtil.saveBitmap(Bitmap.createBitmap((Bitmap)bitmap, (int)0, (int)0, (int)bitmap.getWidth(), (int)bitmap.getHeight(), (Matrix)matrix, (boolean)true), string3);
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static void saveBitmap(Bitmap bitmap, String string2) {
        FileOutputStream fileOutputStream;
        FileUtil.createNewFile(string2);
        Throwable throwable = null;
        try {
            fileOutputStream = new FileOutputStream(new File(string2));
        }
        catch (Throwable throwable2) {
            Throwable throwable3;
            if (throwable != null) {
                if (throwable != throwable2) {
                    throwable.addSuppressed(throwable2);
                }
                throwable3 = throwable;
            }
            try {
                throw throwable3;
            }
            catch (Exception exception) {
                exception.printStackTrace();
                return;
            }
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, (OutputStream)fileOutputStream);
        if (fileOutputStream == null) return;
        {
            catch (Throwable throwable4) {
                throwable = throwable4;
                if (fileOutputStream == null) throw throwable;
                fileOutputStream.close();
                throw throwable;
            }
        }
        fileOutputStream.close();
    }

    public static void scaleBitmapFile(String string2, String string3, float f, float f2) {
        if (!FileUtil.isExistFile(string2)) {
            return;
        }
        Bitmap bitmap = BitmapFactory.decodeFile((String)string2);
        Matrix matrix = new Matrix();
        matrix.postScale(f, f2);
        FileUtil.saveBitmap(Bitmap.createBitmap((Bitmap)bitmap, (int)0, (int)0, (int)bitmap.getWidth(), (int)bitmap.getHeight(), (Matrix)matrix, (boolean)true), string3);
    }

    public static void setBitmapFileBrightness(String string2, String string3, float f) {
        if (!FileUtil.isExistFile(string2)) {
            return;
        }
        Bitmap bitmap = BitmapFactory.decodeFile((String)string2);
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{1.0f, 0.0f, 0.0f, 0.0f, f, 0.0f, 1.0f, 0.0f, 0.0f, f, 0.0f, 0.0f, 1.0f, 0.0f, f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
        Bitmap bitmap2 = Bitmap.createBitmap((int)bitmap.getWidth(), (int)bitmap.getHeight(), (Bitmap.Config)bitmap.getConfig());
        Canvas canvas = new Canvas(bitmap2);
        Paint paint = new Paint();
        paint.setColorFilter((ColorFilter)new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        FileUtil.saveBitmap(bitmap2, string3);
    }

    public static void setBitmapFileColorFilter(String string2, String string3, int n) {
        if (!FileUtil.isExistFile(string2)) {
            return;
        }
        Bitmap bitmap = BitmapFactory.decodeFile((String)string2);
        Bitmap bitmap2 = Bitmap.createBitmap((Bitmap)bitmap, (int)0, (int)0, (int)(-1 + bitmap.getWidth()), (int)(-1 + bitmap.getHeight()));
        Paint paint = new Paint();
        paint.setColorFilter((ColorFilter)new LightingColorFilter(n, 1));
        new Canvas(bitmap2).drawBitmap(bitmap2, 0.0f, 0.0f, paint);
        FileUtil.saveBitmap(bitmap2, string3);
    }

    public static void setBitmapFileContrast(String string2, String string3, float f) {
        if (!FileUtil.isExistFile(string2)) {
            return;
        }
        Bitmap bitmap = BitmapFactory.decodeFile((String)string2);
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
        Bitmap bitmap2 = Bitmap.createBitmap((int)bitmap.getWidth(), (int)bitmap.getHeight(), (Bitmap.Config)bitmap.getConfig());
        Canvas canvas = new Canvas(bitmap2);
        Paint paint = new Paint();
        paint.setColorFilter((ColorFilter)new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        FileUtil.saveBitmap(bitmap2, string3);
    }

    public static void skewBitmapFile(String string2, String string3, float f, float f2) {
        if (!FileUtil.isExistFile(string2)) {
            return;
        }
        Bitmap bitmap = BitmapFactory.decodeFile((String)string2);
        Matrix matrix = new Matrix();
        matrix.postSkew(f, f2);
        FileUtil.saveBitmap(Bitmap.createBitmap((Bitmap)bitmap, (int)0, (int)0, (int)bitmap.getWidth(), (int)bitmap.getHeight(), (Matrix)matrix, (boolean)true), string3);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    public static void writeFile(String var0, String var1_1) {
        FileUtil.createNewFile(var0);
        var2_2 = null;
        var3_3 = new FileWriter(new File(var0), false);
        var3_3.write(var1_1);
        var3_3.flush();
        if (var3_3 == null) ** GOTO lbl11
        var3_3.close();
lbl11: // 3 sources:
        do {
            return;
            break;
        } while (true);
        catch (IOException var4_4) {
            var3_3 = null;
lbl15: // 2 sources:
            do {
                var4_5.printStackTrace();
                if (var3_3 == null) ** continue;
                try {
                    var3_3.close();
                    return;
                }
                catch (IOException var7_7) {
                    var7_7.printStackTrace();
                    return;
                }
                break;
            } while (true);
        }
        catch (Throwable var5_8) lbl-1000: // 2 sources:
        {
            do {
                if (var2_2 != null) {
                    var2_2.close();
                }
lbl30: // 4 sources:
                do {
                    throw var5_9;
                    break;
                } while (true);
                catch (IOException var6_11) {
                    var6_11.printStackTrace();
                    ** continue;
                }
                break;
            } while (true);
        }
        catch (IOException var8_12) {
            var8_12.printStackTrace();
            return;
        }
        {
            catch (Throwable var5_10) {
                var2_2 = var3_3;
                ** continue;
            }
        }
        catch (IOException var4_6) {
            ** continue;
        }
    }
}

