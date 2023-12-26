package com.bete.pdfutilks.view;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.bete.pdfutilks.R;
import com.bete.pdfutilks.bean.CellFontBean;
import com.bete.pdfutilks.bean.NewMergedCellBean;
import com.bete.pdfutilks.databinding.ActivityHomeBinding;
import com.bete.pdfutilks.newUtils.NewITextUtils;
import com.bete.pdfutilks.utils.FileCommonUtil;
import com.bete.pdfutilks.utils.LogUtils;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ActivityHomeBinding homeBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_home, null);
        homeBinding = ActivityHomeBinding.bind(view);
        setContentView(homeBinding.getRoot());
        initView();
    }

    private void initView() {
        NewITextUtils instace = NewITextUtils.getInstace();
        try {
            FileCommonUtil.createOrExistsDir("/sdcard/data");
            // 1. 将图片保存到sd卡中
            File file = new File("/sdcard/data/itext" + System.currentTimeMillis() + ".pdf");
            if (file.exists()) {
                file.delete();
            }
            //创建Document 对象
            Document document = new Document(PageSize.A4, 10, 10, 30, 30);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            PdfWriter writer = PdfWriter.getInstance(document, fileOutputStream);
            //打开文件
            document.open();

            /*             文字相关的内容                          */
            // 1. 将所有的对象全部都放置到列表中，然后将列表写入对象中
            instace.addFont(document, "Report Tilte", 18,
                    Element.ALIGN_CENTER, 26, false, false);
            /*             表格1                          */
            // 数据1
            List<List<String>> inputValue = new LinkedList<>();
            for (int i = 0; i < 2; i++) {
                LinkedList<String> linkedList = new LinkedList<>();
                for (int j = 0; j < 4; j++) {
                    linkedList.add("i=" + i + " j=" + j);
                }
                inputValue.add(linkedList);
            }
            instace.initCellLists(4, 2);
            // 合并内容
            instace.setCellListsText(inputValue, 12, BaseColor.BLACK, false);
            instace.saveCellListsToDoc(document, 10, 10, Element.ALIGN_LEFT);


            /*             表格2                          */

            instace.initCellLists(9, 25);
            // 2. 设置内容
            List<List<String>> titleInputValue1 = new LinkedList<>();
            for (int i = 0; i < 25; i++) {
                LinkedList<String> linkedList = new LinkedList<>();
                for (int j = 0; j < 9; j++) {
                    linkedList.add("i" + i + "j" + j);
                }
                titleInputValue1.add(linkedList);
            }

            /*
             合并单元格的规律 ：
             1. 在第一列中 从第一行开始，每4行合并一次
             2. 在第四列中 从第一行开始，每4行合并一次
             3. 在第五列中 从第一行开始，每4行合并一次
             4. 在第八列中 从第一行开始，每4行合并一次
             5. 在第九列中 从第一行开始，每8行合并一次
             */
            LinkedList<NewMergedCellBean> newMergedCellBeanLinkedList = new LinkedList<>();
            newMergedCellBeanLinkedList.add(new NewMergedCellBean(1, 2, 4, 6));
            newMergedCellBeanLinkedList.add(new NewMergedCellBean(4, 2, 4, 6));
            newMergedCellBeanLinkedList.add(new NewMergedCellBean(5, 2, 4, 6));
            newMergedCellBeanLinkedList.add(new NewMergedCellBean(8, 2, 4, 6));
            newMergedCellBeanLinkedList.add(new NewMergedCellBean(9, 2, 8, 3));
            for (int i = 0; i < newMergedCellBeanLinkedList.size(); i++) {
                NewMergedCellBean newMergedCellBean = newMergedCellBeanLinkedList.get(i);
                for (int j = 0; j < newMergedCellBean.getCurrentCell(); j++) {
                    int cell = newMergedCellBean.getCurrentCell();
                    int startRow = newMergedCellBean.getStartRow();
                    int mergeNum = newMergedCellBean.getMergedNum();
                    int num = newMergedCellBean.getNum();
                    for (int k = 0; k < num; k++) {
                        int startRow1 = startRow + (mergeNum * k);
                        instace.mergeCellLists(cell, startRow1, 1, mergeNum);
                    }
                }
            }

            LinkedList<CellFontBean> cellFontBeans = new LinkedList<>();
            Font font1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
            cellFontBeans.add(new CellFontBean(font1, 1, 0));
            instace.setCellListsText(cellFontBeans, titleInputValue1
                    , 12, BaseColor.BLACK, false);

//            instace.setCellListsTextAndMerge(cellFontBeans, titleInputValue1, newMergedCellBeanLinkedList);


            int spaceWidth = 180 / 16;
            float[] floats = new float[9];
            for (int i = 0; i < 9; i++)
                floats[i] = (i == 0 || i == 5) ? spaceWidth : (2 * spaceWidth);
            instace.saveCellListsToDoc(document, 10, 10, Element.ALIGN_CENTER, floats);

            /*                       图片                 */
//            List<Image> images = new LinkedList<>();

            Image image = Image.getInstance("/sdcard/data/gfgimage.jpg");
//            image.scaleToFit(120f, 120f);            //图片大小
            image.setAlignment(Image.MIDDLE);        //图片居中
//            images.add(image);
//            instace.addImageList(document, images, true);
            instace.initCellLists(2, 1);
            instace.setCellListsImg(0, 0, image);
            Font font12 = new Font(instace.getBaseFont(), 18, Font.NORMAL, BaseColor.BLACK);
            instace.setCellListsText(1, 0, "撒旦发多少个发动广大粉丝公司的人风格的大师傅敢死队风格合适的", font12);


//            instace.setCellListsText(1, 0, "222222", font1);
            instace.saveCellListsToDoc(document, 10, 10, Element.ALIGN_LEFT);
            /*                       文字                 */
            instace.addFont(document, "  ", 18,
                    Element.ALIGN_CENTER, 18, false, false);
            /*                       文字                 */
            // 添加文字
            instace.addFont(document, "When the Ct value of the sample to be tested is outside the reference range and there is a typical S-shapedamplification curve, the test result is positive; \n When the Ct value of the sample to be tested is within the reference range, or the Ct value is outside the referencerange but there is no typical S-shaped amplification curve, the test result is negative.",
                    6f, Element.ALIGN_LEFT, 26, true, false);

            instace.getAllClose(document, writer);
            fileOutputStream.close();

        } catch (Exception exception) {
            exception.printStackTrace();
            LogUtils.e("ex: " + exception);
        }


    }


}
