//package com.bete.pdfutilks.newUtils;
//
//import android.widget.Toast;
//
//import com.bete.pdfutilks.utils.LogUtils;
//import com.itextpdf.text.AccessibleElementId;
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Chunk;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.FontFactory;
//import com.itextpdf.text.Image;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPRow;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.text.pdf.draw.LineSeparator;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Locale;
//
///**
// * itext5工具类
// *
// * @author tsp
// */
//public class ITextUtils {
//
//    public static BaseFont mBaseFont = null;
//
//
//    /**
//     * 添加图片
//     *
//     * @param charPath 图片路径和名称
//     * @param document
//     * @throws DocumentException
//     * @throws MalformedURLException
//     * @throws IOException
//     */
//    public static void addChart(String charPath, Document document) throws DocumentException, MalformedURLException, IOException {
//        Image image = Image.getInstance(charPath);
//        image.scaleToFit(630f, 730f);            //图片大小
//        image.setAlignment(Image.MIDDLE);        //图片居中
//        document.add(image);
//        getDeleteFile(charPath);                //删除存留在系统里的图片
//    }
//
//    /**
//     * 添加空表段落
//     *
//     * @param document
//     * @throws DocumentException
//     * @throws IOException
//     */
//    public static void getBlank(Document document) throws DocumentException, IOException {
//        Font font = new Font(getBaseFont(), 20, Font.BOLD);
//        Paragraph temp = new Paragraph(" ", font);
//        document.add(temp);
//    }
//
//    /**
//     * 添加虚线
//     *
//     * @param document
//     * @throws DocumentException
//     * @throws IOException
//     */
//    public static void getLineSeparator(Document document) throws DocumentException, IOException {
//        //1.线宽度
//        //2.直线长度，是个百分百，0-100之间
//        //3.直线颜色
//        //4.直线位置
//        //5.上下移动位置
//        LineSeparator line = new LineSeparator(1.2f, 80, new BaseColor(190, 190, 190), 1, 0f);    //虚线
//        Font line_font = new Font(getBaseFont(), 26, Font.BOLD);        //空白字体
//        Paragraph paragraph_temp = new Paragraph(" ", line_font);    //空白段落
//        document.add(paragraph_temp);
//        document.add(line);
//        document.add(paragraph_temp);
//    }
//
//    /**
//     * 将两个图片并列在表格里
//     *
//     * @param document     文本
//     * @param listName     标题数组
//     * @param listPath     路径数组
//     * @param columnWidths 单元格大小数组
//     * @throws DocumentException
//     * @throws IOException
//     */
//    public static void getTableAndImage(Document document, String[] listName, String[] listPath, float[] columnWidths) throws DocumentException, IOException {
//        PdfPTable table = new PdfPTable(4);
//
//        BaseFont bf = getBaseFont();
//        Font table_font = new Font(bf, 14, Font.BOLD);
//        Chunk table_blank = new Chunk(" ", table_font);        //文本空白
//
//        //空白
//        for (int i = 0; i < 4; i++) {
//            PdfPCell cell = new PdfPCell();
//            cell.addElement(table_blank);
//            cell.setBorderWidth(1.1f);
//            cell.setBorderColor(new BaseColor(190, 190, 190));        //边框颜色
//            if (i == 0) {
//                cell.disableBorderSide(14);
//            } else if (i == 1) {
//                cell.disableBorderSide(6);
//            } else if (i == 2) {
//                cell.disableBorderSide(10);
//            } else if (i == 3) {
//                cell.disableBorderSide(14);
//            }
//            table.addCell(cell);
//        }
//
//        Font title_subheading_font = new Font(bf, 17, Font.BOLD);
//        Chunk temp_bule = new Chunk(" ", title_subheading_font);            //文本蓝色小块
//        temp_bule.setBackground(new BaseColor(100, 149, 237));            //文本背景颜色
//        Chunk temp_blank = new Chunk(" ", title_subheading_font);        //文本空白
//        for (int i = 0; i < 4; i++) {
//            PdfPCell cell = new PdfPCell();
//
//            if (i == 0) {
//                Chunk temp_name = new Chunk(listName[0], title_subheading_font);        //文本标题文字
//                Paragraph paragraph = new Paragraph();
//                paragraph.add(temp_bule);
//                paragraph.add(temp_blank);
//                paragraph.add(temp_name);
//                cell.addElement(paragraph);
//                cell.disableBorderSide(15);
//            } else if (i == 3) {
//                Chunk temp_name = new Chunk(listName[1], title_subheading_font);        //文本标题文字
//                Paragraph paragraph = new Paragraph();
//                paragraph.add(temp_bule);
//                paragraph.add(temp_blank);
//                paragraph.add(temp_name);
//                cell.addElement(paragraph);
//                cell.disableBorderSide(15);
//            } else if (i == 1) {
//                cell.addElement(temp_blank);
//                cell.disableBorderSide(7);
//            } else if (i == 2) {
//                cell.addElement(temp_blank);
//                cell.disableBorderSide(11);
//            }
//            cell.setBorderWidth(1.1f);
//            cell.setBorderColor(new BaseColor(190, 190, 190));        //边框颜色
//
//            table.addCell(cell);
//        }
//
//        for (int i = 0; i < 4; i++) {
//            PdfPCell cell = new PdfPCell();
//            cell.addElement(temp_blank);
//            cell.setBorderWidth(1.1f);
//            cell.setBorderColor(new BaseColor(190, 190, 190));        //边框颜色
//            if (i == 0) {
//                cell.disableBorderSide(15);
//            } else if (i == 1) {
//                cell.disableBorderSide(7);
//            } else if (i == 2) {
//                cell.disableBorderSide(11);
//            } else if (i == 3) {
//                cell.disableBorderSide(15);
//            }
//            table.addCell(cell);
//        }
//
//        for (int i = 0; i < 4; i++) {
//            PdfPCell cell = new PdfPCell();
//
//            if (i == 0) {
//                Image image = Image.getInstance(listPath[0]);
//                cell.addElement(image);
//                cell.disableBorderSide(15);
//            } else if (i == 3) {
//                Image image = Image.getInstance(listPath[1]);
//                cell.addElement(image);
//                cell.disableBorderSide(15);
//            } else if (i == 1) {
//                cell.disableBorderSide(7);
//            } else if (i == 2) {
//                cell.disableBorderSide(11);
//            }
//            cell.setBorderWidth(1.1f);
//            cell.setBorderColor(new BaseColor(190, 190, 190));        //边框颜色
//            table.addCell(cell);
//        }
//
//        //空白
//        for (int i = 0; i < 4; i++) {
//            PdfPCell cell = new PdfPCell();
//            cell.addElement(table_blank);
//            cell.setBorderWidth(1.1f);
//            cell.setBorderColor(new BaseColor(190, 190, 190));        //边框颜色
//            if (i == 0) {
//                cell.disableBorderSide(13);
//            } else if (i == 1) {
//                cell.disableBorderSide(5);
//            } else if (i == 2) {
//                cell.disableBorderSide(9);
//            } else if (i == 3) {
//                cell.disableBorderSide(13);
//            }
//            table.addCell(cell);
//        }
//
//        //单元格的大小
//        if (columnWidths.length != 0) {
//            float[] coloum = new float[4];
//            for (int i = 0; i < 4; i++) {
//                if (i == 0) {
//                    coloum[i] = columnWidths[0];
//                } else if (i == 3) {
//                    coloum[i] = columnWidths[1];
//                } else {
//                    coloum[i] = 0.1f;
//                }
//            }
//            table.setWidths(coloum);
//        } else {
//            float[] coloum = {1f, 0.1f, 0.1f, 1f};
//            table.setWidths(coloum);
//        }
//        document.add(table);
//        ITextUtils.getBlank(document);
//    }
//
//
//    /**
//     * 设置java适应字体
//     * todo 暂时使用 这个字体，之后进行修改
//     *
//     * @return
//     * @throws DocumentException
//     * @throws IOException
//     */
//    public static BaseFont getBaseFont() throws DocumentException, IOException {
////        return BaseFont.createFont(PDFUtils.class.getResource("/pdf/simsun.ttc").getFile()+",1",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//        if (mBaseFont == null) {
//            return BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//        } else
//            return mBaseFont;
//
//    }
//
//    public static void setBaseFont(BaseFont baseFont) {
//        mBaseFont = baseFont;
//
//    }
//
//    /**
//     * 删除图片
//     *
//     * @param path
//     */
//    public static void getDeleteFile(String path) {
//        try {
//            File file = new File(path);
//            file.delete();
//        } catch (Exception e) {
//            System.out.println("Exception occured");
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 添加虚线
//     *
//     * @param document
//     * @throws DocumentException
//     * @throws IOException
//     */
//    public static void addLineSeparator(Document document) throws DocumentException, IOException {
//        //1.线宽度
//        //2.直线长度，是个百分百，0-100之间
//        //3.直线颜色
//        //4.直线位置
//        //5.上下移动位置
//        LineSeparator line = new LineSeparator(1.2f, 80, new BaseColor(190, 190, 190), 1, 0f);    //虚线
//        Font line_font = new Font(getBaseFont(), 26, Font.BOLD);        //空白字体
//        Paragraph paragraph_temp = new Paragraph(" ", line_font);    //空白段落
//        document.add(paragraph_temp);
//        document.add(line);
//        document.add(paragraph_temp);
//    }
//
//
//    /**
//     * 添加标题类型
//     *
//     * @param document document 对象
//     * @param value    要显示的内容
//     * @param titile   要设置额的字体大小
//     * @throws Exception
//     */
//    public static void addTitile(Document document, String value, int... titile) throws Exception {
//        int titileSize = 18;
//        if (titile.length > 0)
//            titileSize = titile[0];
//        // 设置一级标题样式
//        Font font1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, titileSize, BaseColor.BLACK);
//        Paragraph para1 = new Paragraph(value, font1);
//        para1.setAlignment(Element.ALIGN_CENTER);
//        document.add(para1);
//
//    }
//
//    /**
//     * 默认版本的列表
//     *
//     * @param document      document 对象
//     * @param mValue        要添加的内容
//     * @param spacingBefore 前间距，如果不需要设置请使用 -1 ，默认为 10
//     * @param spacingAfter  后间距，如果不需要设置请使用 -1 ，默认为 10
//     * @throws Exception
//     */
//    public static void addNormalTable(Document document
//            , LinkedList<LinkedList<String>> mValue, int spacingBefore, int spacingAfter, boolean haseFrame
//
//    ) throws Exception {
//        // 1. 列的数量 2. 行的数量 3. 标题的内容 4.
//        PdfPTable table = new PdfPTable(mValue.get(0).size());
//        table.setWidthPercentage(100); // 宽度100%填充
//        table.setSpacingBefore(spacingBefore == -1 ? 10f : spacingBefore); // 前间距
//        table.setSpacingAfter(spacingAfter == -1 ? 10f : spacingBefore); // 后间距
//        List<PdfPRow> listRow = table.getRows();
//        //设置列宽
//        float[] columnWidths = new float[mValue.get(0).size()];
//        for (int i = 0; i < mValue.get(0).size(); i++)
//            columnWidths[i] = 1;
//        table.setWidths(columnWidths);
//
//        //行1
//        for (int i = 0; i < mValue.size(); i++) {
//            PdfPCell cells[] = new PdfPCell[mValue.get(i).size()];
//            PdfPRow row1 = new PdfPRow(cells);
//            for (int j = 0; j < mValue.get(i).size(); j++) {
//                // 表格内容默认居中
//                cells[j] = new PdfPCell(new Paragraph(mValue.get(i).get(j)));//单元格内容
//                cells[j].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
//                cells[j].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
//                if (!haseFrame)
//                    cells[j].setBorderWidth(0);
//            }
//            listRow.add(row1);
//        }
//        //把表格添加到文件中
//        document.add(table);
//    }
//
//    public static void addNullLine(Document document, int insertLineNum) throws Exception {
//        for (int i = 0; i < insertLineNum; i++)
//            document.add(Chunk.NEWLINE);
//
//    }
//
//
//    /**
//     * 給 pdf 設置文件屬性的方法  ，暂时不需要 ，可以添加到工具类中
//     *
//     * @param document   document 对象
//     * @param titile     标题
//     * @param author     作者
//     * @param subject    主题
//     * @param keywords   关键字
//     * @param createDate 是否需要添加创建时间
//     * @param creator    应用程式
//     * @throws Exception
//     */
//    public static void addFileAttributes(Document document, String titile, String author,
//                                         String subject, String keywords, boolean createDate, String creator)
//            throws Exception {
//        document.addTitle(titile);
//        document.addAuthor(author);
//        document.addSubject(subject);
//        document.addKeywords(keywords);
//        if (createDate)
//            document.addCreationDate();
//        document.addCreator(creator);
//
//    }
//
//    /**
//     * 要设置的内容 todo 测试版本
//     *
//     * @param document
//     * @param value
//     * @throws Exception
//     */
//    public static void addContent(Document document, String value) throws Exception {
//        //添加内容
//        Paragraph elements = new Paragraph(value);
//        document.add(elements);
//
//    }
//
//    /**
//     * 关闭iText
//     *
//     * @param document 文档
//     * @param writer   书写器
//     * @throws DocumentException
//     * @throws FileNotFoundException
//     */
//    public static void getAllClose(Document document, PdfWriter writer) throws DocumentException, FileNotFoundException {
//        //关闭文档
//        document.close();
//        //关闭书写器
//        writer.close();
//    }
//
//
//    /**
//     * 创建表格对象
//     *
//     * @param insertColumn
//     * @return
//     */
//    public static PdfPTable getDefultTable(int insertColumn) {
//        return new PdfPTable(insertColumn);
//    }
//
//    /*
//
//    1. 合并单元格的行数
//    2. 合并单元格的列数
//    3. 要合并的行数
//
//    4. 要设置单元格颜色的行数
//    4. 要设置单元格颜色的列数
//    4. 要设置单元格颜色的颜色
//
//     */
//    public static void addSpecialVerticalContentTable(
//            PdfPTable table,
//            Document document, int insertRow, int insertColumn,
//            ArrayList<String> mergeListCells, List<List<String>> mValue,
//            int spacingBefore, int spacingAfter, boolean hasFrame
//            ) throws Exception {
//        BaseFont baseFont = BaseFont.createFont("assets/fonts/brandon_medium.otf", "UTF-8", BaseFont.EMBEDDED);
//        Font font = new Font(baseFont, 12, Font.NORMAL);
//        font.setColor(BaseColor.BLACK);
//        // 添加空单元格到剩余的位置
//        for (int row = 0; row < insertRow; row++) {
//            for (int column = 0; column < insertColumn; column++) {
//
//                if (shouldMergeCell(mergeListCells, row, column)) {
//                    int[] mergedCellCoordinates = getMergedCellCoordinates(mergeListCells, row, column);
//                    PdfPCell cell = new PdfPCell();
//                    cell.setRowspan(mergedCellCoordinates[2] - mergedCellCoordinates[0] + 1);
//                    cell.setColspan(mergedCellCoordinates[3] - mergedCellCoordinates[1] + 1);
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
//                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
//                    Phrase elements = new Phrase(mValue.get(row).get(column), font);
//                    cell.setPhrase(elements);
//                    table.addCell(cell);
//                } else {
//                    if (!skipeCell(mergeListCells, row, column)) {
//                        Phrase elements = new Phrase(mValue.get(row).get(column), font);
//                        table.addCell(elements);
//                        table.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
//                    }
//                }
//            }
//        }
//
//        document.add(table);
//    }
//
//    /*
//     mergeListCells 合并单元格的填写规则 0_0:0_2 这种
//     */
//    public static void addSpecialVerticalContentTable1(
//            PdfPTable table,
//            Document document, int insertRow, int insertColumn,
//            ArrayList<String> mergeListCells, List<List<String>> mValue,
//            int spacingBefore, int spacingAfter, boolean hasFrame,
//            BaseColor baseColor) throws Exception {
//        BaseFont baseFont = BaseFont.createFont("assets/fonts/brandon_medium.otf", "UTF-8", BaseFont.EMBEDDED);
//        Font font = new Font(baseFont, 12, Font.NORMAL);
//        font.setColor(BaseColor.BLACK);
//
//        // 创建表格对象
////        PdfPTable table = new PdfPTable(insertColumn);
//
//        // 添加空单元格到剩余的位置
//        for (int row = 0; row < insertRow; row++) {
//            for (int column = 0; column < insertColumn; column++) {
//
//                if (shouldMergeCell(mergeListCells, row, column)) {
//                    int[] mergedCellCoordinates = getMergedCellCoordinates(mergeListCells, row, column);
//                    PdfPCell cell = new PdfPCell();
//                    cell.setRowspan(mergedCellCoordinates[2] - mergedCellCoordinates[0] + 1);
//                    cell.setColspan(mergedCellCoordinates[3] - mergedCellCoordinates[1] + 1);
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
//                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
//                    Phrase elements = new Phrase(mValue.get(row).get(column), font);
//                    cell.setPhrase(elements);
////                    LogUtils.d("start row is : " + mergedCellCoordinates[0] + " start col is " + mergedCellCoordinates[1] + "\n"
////                            + "end row is : " + mergedCellCoordinates[2] + " end col is " + mergedCellCoordinates[3] + "\n"
////                            + " show Rowspan " + cell.getRowspan() + "\n"
////                            + " show Colspan " + cell.getColspan() + "\n"
////                            + " show row " + row + " show column " + column + "\n"
////                    );
//                    table.addCell(cell);
//                } else {
//                    if (!skipeCell(mergeListCells, row, column)) {
////                        table.addCell(new Phrase(String.format("row %d colum %d ", row, column), font));
//                        Phrase elements = new Phrase(mValue.get(row).get(column), font);
//
//                        table.addCell(elements);
////                        table.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
////                        table.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
//                    }
////                    table.addCell(new Phrase("0_0", font));
//                }
//            }
//        }
//
//        document.add(table);
//    }
//
//    private static boolean skipeCell(ArrayList<String> mergeListCells, int row, int col) {
//        for (String mergedCell : mergeListCells) {
//            String[] coordinates = mergedCell.split(":");
//            String startCoordinate = coordinates[0];
//            String endCoordinate = coordinates[1];
//            int startRow = Integer.parseInt(startCoordinate.split("_")[0]);
//            int startCol = Integer.parseInt(startCoordinate.split("_")[1]);
//            int endRow = Integer.parseInt(endCoordinate.split("_")[0]);
//            int endCol = Integer.parseInt(endCoordinate.split("_")[1]);
//            if (row >= startRow && row <= endRow && col >= startCol && col <= endCol) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    // 判断是否需要合并单元格
//    private static boolean shouldMergeCell(ArrayList<String> mergedCells, int row, int col) {
//        for (String mergedCell : mergedCells) {
//            String[] coordinates = mergedCell.split(":");
//            String startCoordinate = coordinates[0];
//            int startRow = Integer.parseInt(startCoordinate.split("_")[0]);
//            int startCol = Integer.parseInt(startCoordinate.split("_")[1]);
//            if (row == startRow && col == startCol) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    // 获取合并单元格的起始和结束坐标
//    private static int[] getMergedCellCoordinates(ArrayList<String> mergedCells, int row, int col) {
//        for (String mergedCell : mergedCells) {
//            String[] coordinates = mergedCell.split(":");
//            String startCoordinate = coordinates[0];
//            String endCoordinate = coordinates[1];
//
//            int startRow = Integer.parseInt(startCoordinate.split("_")[0]);
//            int startCol = Integer.parseInt(startCoordinate.split("_")[1]);
//            int endRow = Integer.parseInt(endCoordinate.split("_")[0]);
//            int endCol = Integer.parseInt(endCoordinate.split("_")[1]);
//
//            if (row >= startRow && row <= endRow && col >= startCol && col <= endCol) {
////            if (row == startRow  && col == startCol) {
//                return new int[]{startRow, startCol, endRow, endCol};
//            }
//        }
//        // 默认返回当前坐标
//        return new int[]{row, col, row, col};
//    }
//
//
//
//    /**
//     * 1. 要写入List 的方法
//     * @return
//     */
//    public static LinkedList<LinkedList<String>> saveTableList(
//
//    ) {
//
//        return null;
//    }
//}
//
//
//
