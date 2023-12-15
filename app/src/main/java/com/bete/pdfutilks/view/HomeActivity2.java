//package com.bete.pdfutilks.view;
//
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.Toast;
//
//import com.bete.pdfutilks.R;
//import com.bete.pdfutilks.databinding.ActivityHomeBinding;
//import com.bete.pdfutilks.newUtils.ITextUtils;
//import com.bete.pdfutilks.utils.LogUtils;
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Chapter;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.Image;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.Section;
//import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPRow;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.net.URL;
//
//
//public class HomeActivity extends AppCompatActivity {
//    ActivityHomeBinding homeBinding;
//    public static String savae_path = "/sdcard/data/itext.pdf";
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        View view = LayoutInflater.from(this).inflate(R.layout.activity_home, null);
//        homeBinding = ActivityHomeBinding.bind(view);
//        setContentView(homeBinding.getRoot());
//        initView();
//    }
//
//    private void initView() {
//        homeBinding.fab.setOnClickListener(v ->
//        {// 创建 pdf 文件
////            createPdf();
//
//            useItextUtils();
//        });
//
//    }
//
//    private void useItextUtils() {
//        // 0. 删除文件
//        File file = new File(savae_path);
//        if (file.exists()) {
//            file.delete();
//        }
//        //创建Document 对象
//        try {
//            Document document = new Document();
//            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(savae_path));
//            //打开文件
//            document.open();
//            ITextUtils.addTitile(document, "Analysis in case of 1 tube Assay");
//
//
//            //使用工具类关闭 document 与 writer
//            ITextUtils.getAllClose(document, writer);
//
//
//            Toast.makeText(this, "pdf 生成 成功 !", Toast.LENGTH_SHORT).show();
//        } catch (Exception exception) {
//            exception.printStackTrace();
//            LogUtils.e("ex : " + exception);
//        }
//        // 1. 创建内容
//    }
//
//    /**
//     * 1. 创建pdf 文件
//     */
//    public void createPdf() {
//        // 0. 删除文件
//        File file = new File(savae_path);
//        if (file.exists()) {
//            file.delete();
//        }
//        // 1 hello word 创建pdf 写入文本内容
//        try {
////            helloWord();
//            // 2 设置文件属性 创建pdf 写入文本内容
//            setFileAttribute();
//
//            // 3 添加图片 创建pdf 写入文本内容
//            // addIMage();
//
//            // 4 制表 创建pdf 写入文本内容
//            // addTable();
//
//            // 5 列表 创建pdf 写入文本内容
//            // addList();
//
//            // 5 中文、格式、样式 创建pdf 写入文本内容
////        formate();
//            Toast.makeText(this, "文件保存成功!", Toast.LENGTH_SHORT).show();
//        } catch (Exception exception) {
//            exception.printStackTrace();
//            LogUtils.e("ex : " + exception);
//        }
//
//
//    }
//
//    public void formate() throws Exception {
//        //创建文件
//        Document document = new Document();
//        //建立一个书写器
//        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(savae_path));
//        //打开文件
//        document.open();
//
//        //中文字体,解决中文不能显示问题
//        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//
//        //蓝色字体
//        Font blueFont = new Font(bfChinese);
//        blueFont.setColor(BaseColor.BLUE);
//        //段落文本
//        Paragraph paragraphBlue = new Paragraph("hello word, formate", blueFont);
//        document.add(paragraphBlue);
//
//        //绿色字体
//        Font greenFont = new Font(bfChinese);
//        greenFont.setColor(BaseColor.GREEN);
//        //创建章节
//        Paragraph chapterTitle = new Paragraph("第一段", greenFont);
//        Chapter chapter1 = new Chapter(chapterTitle, 1);
//        chapter1.setNumberDepth(0);
//
//        Paragraph sectionTitle = new Paragraph("第二段", greenFont);
//        Section section1 = chapter1.addSection(sectionTitle);
//
//        Paragraph sectionContent = new Paragraph("第三段", blueFont);
//        section1.add(sectionContent);
//
//        //将章节添加到文章中
//        document.add(chapter1);
//
//        //关闭文档
//        document.close();
//        //关闭书写器
//        writer.close();
//    }
//
//    public static void addList() throws Exception {
////        //创建文件
////        Document document = new Document();
////        //建立一个书写器
////        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:/pdf/addList.pdf"));
////        //打开文件
////        document.open();
////        //添加内容
////        document.add(new Paragraph("hello Word, addList"));
////
////        //添加有序列表
////        List orderedList = new List(List.ORDERED);
////        orderedList.add(new ListItem("one 1"));
////        orderedList.add(new ListItem("two 2"));
////        orderedList.add(new ListItem("three 3"));
////        document.add(orderedList);
////
////        //关闭文档
////        document.close();
////        //关闭书写器
////        writer.close();
//    }
//
//    public static void addTable() throws Exception {
//        //创建文件
//        Document document = new Document();
//        //建立一个书写器
//        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:/pdf/addTable.pdf"));
//        //打开文件
//        document.open();
//        //添加内容
//        document.add(new Paragraph("hello Word, addTable"));
//
//        // 3列的表.
//        PdfPTable table = new PdfPTable(3);
//        table.setWidthPercentage(100); // 宽度100%填充
//        table.setSpacingBefore(10f); // 前间距
//        table.setSpacingAfter(10f); // 后间距
//
//        java.util.List<PdfPRow> listRow = table.getRows();
//        //设置列宽
//        float[] columnWidths = {1f, 2f, 3f};
//        table.setWidths(columnWidths);
//
//        //行1
//        PdfPCell cells1[] = new PdfPCell[3];
//        PdfPRow row1 = new PdfPRow(cells1);
//
//        //单元格
//        cells1[0] = new PdfPCell(new Paragraph("name"));//单元格内容
//        //cells1[0].setBorderColor(BaseColor.BLUE);//边框验证
//        //cells1[0].setPaddingLeft(20);//左填充20
//        //cells1[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
//        //cells1[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
//
//        cells1[1] = new PdfPCell(new Paragraph("age"));
//        cells1[2] = new PdfPCell(new Paragraph("sex"));
//
//        //行2
//        PdfPCell cells2[] = new PdfPCell[3];
//        PdfPRow row2 = new PdfPRow(cells2);
//        cells2[0] = new PdfPCell(new Paragraph("张三"));
//        cells2[1] = new PdfPCell(new Paragraph("23"));
//        cells2[2] = new PdfPCell(new Paragraph("man"));
//
//        //把第一行添加到集合
//        listRow.add(row1);
//        listRow.add(row2);
//        //把表格添加到文件中
//        document.add(table);
//
//        //关闭文档
//        document.close();
//        //关闭书写器
//        writer.close();
//    }
//
//    public static void addIMage() throws Exception {
//        //创建文件
//        Document document = new Document();
//        //建立一个书写器
//        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:/pdf/addIMage.pdf"));
//        //打开文件
//        document.open();
//        //添加内容
//        document.add(new Paragraph("Hello World, addIMage"));
//        document.add(new Paragraph("Hello World, content"));
//
//        //图片1
//        Image image1 = Image.getInstance(new URL("https://t7.baidu.com/it/u=4162611394,4275913936&fm=193&f=GIF"));
////        //设置图片位置的x轴和y周
////        image1.setAbsolutePosition(100f, 550f);
////        //设置图片的宽度和高度
////        image1.scaleAbsolute(200, 200);
//        //将图片1添加到pdf文件中
//        document.add(image1);
//
////        //图片2
////        Image image2 = Image.getInstance(new URL("https://hbimg.b0.upaiyun.com/b52e9caed47b7b4a1dd7837c2495e77556822fee3bee67-hDpzFI_fw658"));
////        //将图片2添加到pdf文件中
////        document.add(image2);
//
//        //关闭文档
//        document.close();
//        //关闭书写器
//        writer.close();
//    }
//
//
//    public static void setFileAttribute() throws Exception {
//        //创建文件
//        Document document = new Document();
//        //建立一个书写器
//        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:/pdf/setFileAttribute.pdf"));
//        //打开文件
//        document.open();
//        //添加内容
//        document.add(new Paragraph("Hello World, setFileAttribute"));
//
//        //设置属性
//        //标题
//        document.addTitle("this is a title");
//        //作者
//        document.addAuthor("xxx");
//        //主题
//        document.addSubject("this is subject");
//        //关键字
//        document.addKeywords("this is Keywords");
//        //创建时间
//        document.addCreationDate();
//        //应用程序
//        document.addCreator("xxx.com");
//
//        //关闭文档
//        document.close();
//        //关闭书写器
//        writer.close();
//    }
//
//    public void helloWord() throws Exception {
//        // 1.新建document对象
//        Document document = new Document();
//
//        // 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
//        // 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
//        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(savae_path));
//
//        // 3.打开文档
//        document.open();
//
//        // 4.添加一个内容段落
//        document.add(new Paragraph("Hello World!"));
//
//        // 5.关闭文档
//        document.close();
//        //关闭书写器
//        writer.close();
//    }
//
//}
