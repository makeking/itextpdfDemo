//package com.bete.pdfutilks.newUtils;
// todo 使用 itext7 付费版本 8.0 版本
//
//import com.itextpdf.text.PageSize;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;
//
//import org.xutils.db.annotation.Table;
//
///**
// * @author Wulc
// * @date 2023/8/10 17:08
// * @description
// */
//@Component
//public class MyItextpdfUtils {
//
//    public void createPDF() throws java.io.IOException {
//        Resource resource = new ClassPathResource("/");
//        String path = resource.getFile().getPath();
//        //设置中文字体 C:\Windows\Fonts
//        //PdfFont chineseFont =getFont();
//        //PdfFont chineseFont = PdfFontFactory.createFont(this.getClass().getClassLoader().getResource("simsun.ttf").getPath());
//        PdfFont chineseFontForTemplate = PdfFontFactory.createFont("D:\\学习资料\\后端\\STSONG.TTF");
//        PdfFont chineseFontForContent = PdfFontFactory.createFont("D:\\学习资料\\后端\\STSONG.TTF");
//        //创建每页的共有模板
//        //*********************每页的共有模板*********************************
//        String templatePath = path + "\\template.pdf";
//        PdfDocument pdfDocumentTemplate = new PdfDocument(new PdfWriter(templatePath));
//        //Document documentTemplate = new Document(pdfDocumentTemplate, PageSize.A4).setFont(chineseFontForTemplate);
//        Document documentTemplate = new Document(pdfDocumentTemplate, PageSize.A4);
//        //插入logo图片
//        Table logoTemplateTable = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth().setBorder(Border.NO_BORDER);
//        ImageData imageData = ImageDataFactory.create(this.getClass().getClassLoader().getResource("logo.png"));
//        Image image = new Image(imageData);
//        image.setHeight(50);
//        image.setWidth(100);
//        logoTemplateTable.addCell(new Cell().setBorder(Border.NO_BORDER).add(image));
//        //插入logo图片下方的一些信息
//        Table logoInfoTable = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth().setBorder(Border.NO_BORDER);
//        logoInfoTable.addCell(new Cell().setBorder(Border.NO_BORDER).setPadding(1).setFontSize(10).add(new Paragraph("Description1")));
//        logoInfoTable.addCell(new Cell().setBorder(Border.NO_BORDER).setPadding(1).setFontSize(10).add(new Paragraph("Description2")));
//        logoInfoTable.addCell(new Cell().setBorder(Border.NO_BORDER).setPadding(1).setFontSize(10).add(new Paragraph("Description3")));
//        //插入标题
//        Table titleTable = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth().setBorder(Border.NO_BORDER);
//        titleTable.addCell(new Cell(1, 4).setBorder(Border.NO_BORDER).setPadding(1).setFontSize(15).add(new Paragraph("TITLE")).setTextAlignment(TextAlignment.CENTER));
//        //插入标题下的一些信息
//        Table titleInfoTable = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();
//        titleInfoTable.addCell(new Cell().setPadding(1).setFontSize(10).add(new Paragraph("QuestionA")));
//        titleInfoTable.addCell(new Cell().setPadding(1).setFontSize(10).add(new Paragraph("AnswerA")));
//        titleInfoTable.addCell(new Cell().setPadding(1).setFontSize(10).add(new Paragraph("QuestionB")));
//        titleInfoTable.addCell(new Cell().setPadding(1).setFontSize(10).add(new Paragraph("AnswerB")));
//        titleInfoTable.addCell(new Cell().setPadding(1).setFontSize(10).add(new Paragraph("QuestionC")));
//        titleInfoTable.addCell(new Cell().setPadding(1).setFontSize(10).add(new Paragraph("AnswerC")));
//        titleInfoTable.addCell(new Cell().setPadding(1).setFontSize(10).add(new Paragraph("QuestionD")));
//        titleInfoTable.addCell(new Cell().setPadding(1).setFontSize(10).add(new Paragraph("AnswerD")));
//        titleInfoTable.addCell(new Cell().setPadding(1).setFontSize(10).add(new Paragraph("QuestionE")));
//        titleInfoTable.addCell(new Cell().setPadding(1).setFontSize(10).add(new Paragraph("AnswerE")));
//        titleInfoTable.addCell(new Cell().setPadding(1).setFontSize(10).add(new Paragraph("QuestionF")));
//        titleInfoTable.addCell(new Cell().setPadding(1).setFontSize(10).add(new Paragraph("AnswerF")));
//
//        documentTemplate.add(logoTemplateTable);
//        documentTemplate.add(logoInfoTable);
//        documentTemplate.add(titleTable);
//        documentTemplate.add(titleInfoTable);
//        //*********************每页的共有模板*********************************
//
//
//        //*********************每页的内容************************************
//        String contentPath = path + "\\content.pdf";
//        PdfDocument pdfDocumentContent = new PdfDocument(new PdfWriter(contentPath));
//        //把内容使用共有模板
//        pdfDocumentContent.addEventHandler(PdfDocumentEvent.END_PAGE, new PaginationEventHandler(pdfDocumentTemplate.getFirstPage().copyAsFormXObject(pdfDocumentContent)));
//        Document documentContent = new Document(pdfDocumentContent, PageSize.A4).setFont(chineseFontForContent);
//        //每页的content距离上面的template的距离
//        documentContent.setTopMargin(250);
//        Table contentTable = new Table(UnitValue.createPercentArray(6)).useAllAvailableWidth();
//        //插入清单表格标题
//        contentTable.addHeaderCell(new Cell().setFontSize(8).add(new Paragraph("No")));
//        contentTable.addHeaderCell(new Cell().setFontSize(8).add(new Paragraph("title1")));
//        contentTable.addHeaderCell(new Cell().setFontSize(8).add(new Paragraph("title2")));
//        contentTable.addHeaderCell(new Cell().setFontSize(8).add(new Paragraph("title3")));
//        contentTable.addHeaderCell(new Cell().setFontSize(8).add(new Paragraph("title4")));
//        contentTable.addHeaderCell(new Cell().setFontSize(8).add(new Paragraph("title5")));
//        for (int i = 0; i < 300; i++) {
//            contentTable.addCell(new Cell().setFontSize(8).add(new Paragraph(String.valueOf(i))));
//            contentTable.addCell(new Cell().setFontSize(8).add(new Paragraph("content1")));
//            contentTable.addCell(new Cell().setFontSize(8).add(new Paragraph("content2")));
//            contentTable.addCell(new Cell().setFontSize(8).add(new Paragraph("content3")));
//            contentTable.addCell(new Cell().setFontSize(8).add(new Paragraph("content4")));
//            contentTable.addCell(new Cell().setFontSize(8).add(new Paragraph("content5")));
//        }
//        //尾页
//        Table lastInfoTable = new Table(UnitValue.createPercentArray(3)).setWidth(300);
//        lastInfoTable.addCell(new Cell(1, 3).setPadding(1).setFontSize(8).add(new Paragraph("Total：")));
//        lastInfoTable.addCell(new Cell(1, 1).setPadding(1).setFontSize(8).add(new Paragraph("统计A：")));
//        lastInfoTable.addCell(new Cell(1, 2).setPadding(1).setFontSize(8).add(new Paragraph("1234567")));
//        lastInfoTable.addCell(new Cell(1, 1).setPadding(1).setFontSize(8).add(new Paragraph("统计B：")));
//        lastInfoTable.addCell(new Cell(1, 2).setPadding(1).setFontSize(8).add(new Paragraph("7654321")));
//        //*********************每页的内容************************************
//
//        documentContent.add(contentTable);
//        //尾页新开一页
//        documentContent.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
//        documentContent.add(lastInfoTable);
//        documentTemplate.close();
//        documentContent.close();
//    }
//
//
//}
