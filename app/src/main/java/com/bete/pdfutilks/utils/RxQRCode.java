package com.bete.pdfutilks.utils;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;

/**
 * @author vondear
 * @date 2017/2/17.
 */

public class RxQRCode {

    /**
     * 获取建造者
     *
     * @param text 样式字符串文本
     * @return {@link Builder}
     */
    public static Builder builder(@NonNull CharSequence text) {
        return new Builder(text);
    }

    public static class Builder {

        private int backgroundColor = 0xffffffff;

        private int codeColor = 0xff000000;

        private int codeSide = 800;

        private CharSequence content;

        public Builder backColor(int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder codeColor(int codeColor) {
            this.codeColor = codeColor;
            return this;
        }

        public Builder codeSide(int codeSide) {
            this.codeSide = codeSide;
            return this;
        }

        public Builder(@NonNull CharSequence text) {
            this.content = text;
        }

        public Bitmap into(ImageView imageView) {
            Bitmap bitmap = RxQRCode.creatQRCode(content, codeSide, codeSide, backgroundColor, codeColor);
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
            return bitmap;
        }
    }

    //----------------------------------------------------------------------------------------------以下为生成二维码算法

    public static Bitmap creatQRCode(CharSequence content, int QR_WIDTH, int QR_HEIGHT, int backgroundColor, int codeColor) {
        Bitmap bitmap = null;
        try {
            // 判断URL合法性
            if (content == null || "".equals(content) || content.length() < 1) {
                return null;
            }
            //Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            Hashtable hints = new Hashtable();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L); //容错率
//            hints.put(MARGIN, -1);
            // 图像数据转换，使用了矩阵转换
            BitMatrix bitMatrix = new QRCodeWriter().encode(content + "", BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
            //调用去除白边方法
            bitMatrix = deleteWhite(bitMatrix);
            QR_WIDTH = bitMatrix.getWidth();
            QR_HEIGHT = bitMatrix.getHeight();
            int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
            // 下面这里按照二维码的算法，逐个生成二维码的图片，
            // 两个for循环是图片横列扫描的结果
            for (int y = 0; y < QR_HEIGHT; y++) {
                for (int x = 0; x < QR_WIDTH; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * QR_WIDTH + x] = codeColor;
                    } else {
                        pixels[y * QR_WIDTH + x] = backgroundColor;
                    }
                }
            }
            // 生成二维码图片的格式，使用ARGB_8888
            bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
        } catch (WriterException e) {
            LogUtils.d(e);
        }
        return bitmap;
    }

    private static BitMatrix deleteWhite(BitMatrix matrix) {
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHeight = rec[3] + 1;

        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (matrix.get(i + rec[0], j + rec[1]))
                    resMatrix.set(i, j);
            }
        }
        return resMatrix;
    }

    public static Bitmap creatQRCode(CharSequence content, int QR_WIDTH, int QR_HEIGHT) {
        return creatQRCode(content, QR_WIDTH, QR_HEIGHT, 0xffffffff, 0xff000000);
    }

    public static Bitmap creatQRCode(CharSequence content) {
        return creatQRCode(content, 800, 800);
    }

    //==============================================================================================二维码算法结束


    /**
     * @param content   需要转换的字符串
     * @param QR_WIDTH  二维码的宽度
     * @param QR_HEIGHT 二维码的高度
     * @param iv_code   图片空间
     */
    public static void createQRCode(String content, int QR_WIDTH, int QR_HEIGHT, ImageView iv_code) {
        iv_code.setImageBitmap(creatQRCode(content, QR_WIDTH, QR_HEIGHT));
    }

    /**
     * QR_WIDTH  二维码的宽度
     * QR_HEIGHT 二维码的高度
     *
     * @param content 需要转换的字符串
     * @param iv_code 图片空间
     */
    public static void createQRCode(String content, ImageView iv_code) {
        iv_code.setImageBitmap(creatQRCode(content));
    }
}
