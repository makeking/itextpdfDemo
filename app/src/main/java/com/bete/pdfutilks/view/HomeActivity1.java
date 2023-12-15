//package com.bete.pdfutilks.view;
//
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.Button;
//
//import com.bete.pdfutilks.R;
//import com.bete.pdfutilks.databinding.ActivityHomeBinding;
//
//
//public class HomeActivity1 extends AppCompatActivity {
//    ActivityHomeBinding homeBinding;
//    // variables for our buttons.
//    Button generatePDFbtn;
//
//    // declaring width and height
//    // for our PDF file.
//    int pageHeight = 1120;
//    int pagewidth = 792;
//
//    // creating a bitmap variable
//    // for storing our images
//    Bitmap bmp, scaledbmp;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        View view = LayoutInflater.from(this).inflate(R.layout.activity_home, null);
//        homeBinding = ActivityHomeBinding.bind(view);
//        setContentView(homeBinding.getRoot());
//
//
//        initView();
//    }
//
//    private void initView() {
//        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.gfgimage);
//        scaledbmp = Bitmap.createScaledBitmap(bmp, 140, 140, false);
//        homeBinding.fab.setOnClickListener(v ->
//        {// 创建 pdf 文件
//            // calling method to
//            // generate our PDF file.
//            generatePDF();
//
////            SavePdfDocument savePdfDocument = new SavePdfDocument();
////            savePdfDocument.exportPdf("/sdcard/00/11.pdf");
//
//
//        });
//
//    }
//
////    private void generatePDF() {
////        try {
////            // 1. 添加 PDDocument 的 对象
////            PDDocument document = new PDDocument();
////            // 2. 设置pdf 的大小为 A4 纸的大小
////            PDPage page = new PDPage(PDRectangle.A4);
////            document.addPage(page);
////            PDPageContentStream contentStream = new PDPageContentStream(document, page);
////
////            float margin = 50;
////            float yStart = page.getMediaBox().getHeight() - (2 * margin);
////            float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
////            float yPosition = yStart;
////            float bottomMargin = 70;
////            int numberOfRows = 5;
////            int numberOfColumns = 3;
////            float rowHeight = 20f;
////            float tableRowHeight = rowHeight + 5;
////            float tableHeight = tableRowHeight * numberOfRows;
////            float cellMargin = 5f;
////
////            // 绘制表头
//////            drawCell(contentStream, margin, yPosition, tableWidth, rowHeight, cellMargin, "Header 1", PDType1Font.HELVETICA_BOLD);
////            drawCell(contentStream, margin, yPosition, tableWidth, rowHeight, cellMargin, "Header 1", PDType1Font.HELVETICA_BOLD);
////            yPosition -= rowHeight;
////            drawCell(contentStream, margin, yPosition, tableWidth, rowHeight, cellMargin, "Header 2", PDType1Font.HELVETICA_BOLD);
////            yPosition -= rowHeight;
////
////            // 绘制数据行
////            for (int i = 0; i < numberOfRows; i++) {
////                if (i % 2 == 0) { // 偶数行添加背景颜色
////                    contentStream.setNonStrokingColor(230, 230, 230); // 设置灰色背景
////                    contentStream.fillRect(margin, yPosition, tableWidth, rowHeight);
////                    contentStream.setNonStrokingColor(0, 0, 0); // 恢复默认颜色
////                }
////                drawCell(contentStream, margin, yPosition, tableWidth, rowHeight, cellMargin, "Data " + (i + 1) + ",1", PDType1Font.HELVETICA);
////                drawCell(contentStream, margin + (tableWidth / 3), yPosition, tableWidth, rowHeight, cellMargin, "Data " + (i + 1) + ",2", PDType1Font.HELVETICA);
////                yPosition -= rowHeight;
////            }
////
////            contentStream.close();
////
////            document.save(Environment.getExternalStorageDirectory() + "/output.pdf");
////            document.close();
////        } catch (Exception exception) {
////            exception.printStackTrace();
////            LogUtils.e("ex : " + exception);
////        }
////    }
////
////    private static void drawCell(PDPageContentStream contentStream, float x, float y, float width, float height, float margin, String text, PDType1Font font) throws IOException {
////        contentStream.setFont(font, 12);
////        contentStream.beginText();
////        contentStream.newLineAtOffset(x + margin, y + margin);
////        contentStream.showText(text);
////        contentStream.endText();
////    }
//
//
////    private void generatePDF1() {
////        try {
////            // creating an object variable
////            // for our PDF document.
////            PdfDocument pdfDocument = new PdfDocument();
////
////            // two variables for paint "paint" is used
////            // for drawing shapes and we will use "title"
////            // for adding text in our PDF file.
////            Paint paint = new Paint();
////            Paint title = new Paint();
////
////            // we are adding page info to our PDF file
////            // in which we will be passing our pageWidth,
////            // pageHeight and number of pages and after that
////            // we are calling it to create our PDF.
////            PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(pagewidth, pageHeight, 1).create();
////
////            // below line is used for setting
////            // start page for our PDF file.
////            PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);
////
////            // creating a variable for canvas
////            // from our page of PDF.
////            Canvas canvas = myPage.getCanvas();
////
////            // below line is used to draw our image on our PDF file.
////            // the first parameter of our drawbitmap method is
////            // our bitmap
////            // second parameter is position from left
////            // third parameter is position from top and last
////            // one is our variable for paint.
////            canvas.drawBitmap(scaledbmp, 56, 40, paint);
////
////            // below line is used for adding typeface for
////            // our text which we will be adding in our PDF file.
////            title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
////
////            // below line is used for setting text size
////            // which we will be displaying in our PDF file.
////            title.setTextSize(15);
////
////            // below line is sued for setting color
////            // of our text inside our PDF file.
////            title.setColor(ContextCompat.getColor(this, R.color.purple_200));
////
////            // below line is used to draw text in our PDF file.
////            // the first parameter is our text, second parameter
////            // is position from start, third parameter is position from top
////            // and then we are passing our variable of paint which is title.
////            canvas.drawText("A portal for IT professionals.", 209, 100, title);
////            canvas.drawText("Geeks for Geeks", 209, 80, title);
////
////            // similarly we are creating another text and in this
////            // we are aligning this text to center of our PDF file.
////            title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
////            title.setColor(ContextCompat.getColor(this, R.color.purple_200));
////            title.setTextSize(15);
////
////            // below line is used for setting
////            // our text to center of PDF.
////            title.setTextAlign(Paint.Align.CENTER);
////            canvas.drawText("This is sample document which we have created.", 396, 560, title);
////
////            // after adding all attributes to our
////            // PDF file we will be finishing our page.
////            pdfDocument.finishPage(myPage);
////
////            // below line is used to set the name of
////            // our PDF file and its path.
////            File file = new File(Environment.getExternalStorageDirectory(), "GFG.pdf");
////
////            try {
////                // after creating a file name we will
////                // write our PDF file to that location.
////                pdfDocument.writeTo(new FileOutputStream(file));
////
////                // below line is to print toast message
////                // on completion of PDF generation.
////                Toast.makeText(this, "PDF file generated successfully.", Toast.LENGTH_SHORT).show();
////            } catch (IOException e) {
////                // below line is used
////                // to handle error
////                e.printStackTrace();
////            }
////            // after storing our pdf to that
////            // location we are closing our PDF file.
////            pdfDocument.close();
////        } catch (Exception exception) {
////            exception.printStackTrace();
////            LogUtils.e("ex : " + exception);
////        }
////    }
//
//    public void generatePDF(){
//
//    }
//
//
//}
