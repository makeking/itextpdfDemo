package com.bete.pdfutilks.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by pengdongyuan491 on 16/11/15.
 */

public class AESHelper {
    public static final String VIPARA = "0102030405060708";
    private static final String TAG = AESHelper.class.getSimpleName();

    /**
     * 初始化 AES Cipher
     *
     * @param sKey
     * @param cipherMode
     * @return
     */
    private static Cipher initAESCipher(String sKey, int cipherMode) {
        //创建Key gen
        KeyGenerator keyGenerator = null;
        Cipher cipher = null;
        try {
            IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
            LogUtils.e(VIPARA.getBytes());
            LogUtils.e(sKey.getBytes());
            SecretKeySpec key = new SecretKeySpec(sKey.getBytes(), "AES");
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(cipherMode, key, zeroIv);
            LogUtils.e(cipher.getIV());
            LogUtils.e(key.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            LogUtils.d(e);
        } catch (NoSuchPaddingException e) {
            LogUtils.d(e);
        } catch (InvalidKeyException e) {
            LogUtils.d(e);
        } catch (InvalidAlgorithmParameterException e) {
            LogUtils.d(e);
        }
        return cipher;
    }


    /**
     * 对文件进行AES加密
     *
     * @param key
     * @param sourceFilePath
     * @param destFilePath
     * @return
     */
    public static File encryptFile(String key, String sourceFilePath, String destFilePath) {
        System.out.printf(sourceFilePath);
        FileInputStream in = null;
        FileOutputStream out = null;
        File destFile = null;
        File sourceFile = null;
        try {
            sourceFile = new File(sourceFilePath);

            System.out.printf( sourceFilePath + "---" + sourceFile.getAbsolutePath());
            destFile = new File(destFilePath);
            if (sourceFile.exists() && sourceFile.isFile()) {
                if (!destFile.getParentFile().exists()) {
                    destFile.getParentFile().mkdirs();
                }
                destFile.createNewFile();
                in = new FileInputStream(sourceFile);
                out = new FileOutputStream(destFile);
                Cipher cipher = initAESCipher(key, Cipher.ENCRYPT_MODE);
                //以加密流写入文件
                CipherInputStream cipherInputStream = new CipherInputStream(in, cipher);
                byte[] cache = new byte[1024];
                int nRead = 0;
                while ((nRead = cipherInputStream.read(cache)) != -1) {
                    out.write(cache, 0, nRead);
                    out.flush();
                }
                cipherInputStream.close();
            }
        } catch (FileNotFoundException e) {
            LogUtils.d(e);
        } catch (IOException e) {
            LogUtils.d(e);
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                LogUtils.d(e);
            }
            try {
                in.close();
            } catch (IOException e) {
                LogUtils.d(e);
            }
        }
        return destFile;
    }


    /**
     * AES方式解密文件
     *
     * @param key
     * @param sourceFilePath
     * @param destFilePath
     * @return
     */
    public static File decryptFile(String key, String sourceFilePath, String destFilePath) {
        FileInputStream in = null;
        FileOutputStream out = null;
        File destFile = null;
        File sourceFile = null;
        try {
            sourceFile = new File(sourceFilePath);
            destFile = new File(destFilePath);
            if (sourceFile.exists() && sourceFile.isFile()) {
                if (!destFile.getParentFile().exists()) {
                    destFile.getParentFile().mkdirs();
                }
                destFile.createNewFile();
                in = new FileInputStream(sourceFile);
                out = new FileOutputStream(destFile);
                Cipher cipher = initAESCipher(key, Cipher.DECRYPT_MODE);
                CipherOutputStream cipherOutputStream = new CipherOutputStream(out, cipher);
                byte[] buffer = new byte[1024];
                int r;
                while ((r = in.read(buffer)) >= 0) {
                    cipherOutputStream.write(buffer, 0, r);
                }
                cipherOutputStream.close();
            }
        } catch (IOException e) {
            LogUtils.d(e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                LogUtils.d(e);
            }
            try {
                out.close();
            } catch (IOException e) {
                LogUtils.d(e);
            }
        }
        return destFile;
    }
}
