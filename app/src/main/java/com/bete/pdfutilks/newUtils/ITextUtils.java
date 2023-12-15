package com.bete.pdfutilks.newUtils;

import static com.itextpdf.text.pdf.BaseFont.IDENTITY_H;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.bete.pdfutilks.R;
import com.bete.pdfutilks.bean.MergedCellBean;
import com.bete.pdfutilks.utils.FileCommonUtil;
import com.bete.pdfutilks.utils.LogUtils;
import com.bete.pdfutilks.utils.StringUtils;
import com.bete.pdfutilks.utils.Utils;
import com.itextpdf.text.AccessibleElementId;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * itext5工具类
 *
 * @author tsp
 */
public class ITextUtils {

    public static BaseFont mBaseFont = null;
    public static LinkedList<AccessibleElementId> usrcelllists = new LinkedList<AccessibleElementId>();
    public static LinkedList<MergedCellBean> mergedCellList = new LinkedList<>();
    public static LinkedList<LinkedList<PdfPCell>> celllists;

    static {
        mergedCellList.clear();
        celllists = new LinkedList<>();
        celllists.add(new LinkedList<>());
        for (LinkedList<PdfPCell> cells : celllists) {
            cells.clear();
        }
    }

    public static void setBaseFont(BaseFont baseFont) {
        mBaseFont = baseFont;

    }

    /**
     * 设置java适应字体
     *
     * @throws DocumentException
     * @throws IOException
     */
    public BaseFont getBaseFont() throws Exception {
        if (mBaseFont == null) {

            BaseFont baseFont = null;
            try {
//                baseFont = BaseFont.createFont("assets/fonts/brandon_medium.otf", "UTF-8", BaseFont.EMBEDDED);
                // 默认使用免费商用 免费个人使用的字体 站酷仓耳渔阳体
//                baseFont = BaseFont.createFont("assets/fonts/zkceyyt.ttf", IDENTITY_H, BaseFont.EMBEDDED);
                // 测试使用 ass默认的字体
                baseFont = BaseFont.createFont("assets/fonts/zkceyyt.ttf", IDENTITY_H, BaseFont.EMBEDDED);
//                baseFont = BaseFont.createFont("STSong-Light", IDENTITY_H, BaseFont.EMBEDDED);

                // todo 使用 方正宋体需要版权授予
//                baseFont = BaseFont.createFont("assets/fonts/FZYTK.TTF", IDENTITY_H, BaseFont.EMBEDDED);
//                baseFont = BaseFont.createFont("assets/fonts/brandon_medium.otf", IDENTITY_H, BaseFont.EMBEDDED);
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Font font1 = new Font(baseFont, 12, Font.NORMAL);
            font1.setColor(BaseColor.BLACK);
            return baseFont;
        } else
            return mBaseFont;

    }

    /**
     * 删除图片
     *
     * @param path
     */
    public void getDeleteFile(String path) {
        try {
            File file = new File(path);
            file.delete();
        } catch (Exception e) {
            System.out.println("Exception occured");
            e.printStackTrace();
        }
    }

    /**
     * 添加标题类型
     *
     * @param document document 对象
     * @param value    要显示的内容
     * @param titile   要设置额的字体大小
     * @throws Exception
     */
    public void addTitile(Document document, String value, int... titile) throws Exception {
        if (document == null)
            throw new OperateExcetion("Document is null ");
        if (StringUtils.isEmpty(value))
            value = "";
        int titileSize = 18;
        if (titile.length > 0)
            titileSize = titile[0];
        // 设置一级标题样式
        Font font1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, titileSize, BaseColor.BLACK);
        Paragraph para1 = new Paragraph(value, font1);
        para1.setAlignment(Element.ALIGN_CENTER);
        document.add(para1);

    }

    /**
     * 默认版本的列表
     *
     * @param document      document 对象
     * @param mValue        要添加的内容
     * @param spacingBefore 前间距，如果不需要设置请使用 -1 ，默认为 10
     * @param spacingAfter  后间距，如果不需要设置请使用 -1 ，默认为 10
     * @throws Exception
     */
    public void addNormalTable(Document document
            , LinkedList<LinkedList<String>> mValue, int spacingBefore, int spacingAfter, boolean haseFrame

    ) throws Exception {
        // 1. 列的数量 2. 行的数量 3. 标题的内容 4.
        PdfPTable table = new PdfPTable(mValue.get(0).size());
        table.setWidthPercentage(100); // 宽度100%填充
        table.setSpacingBefore(spacingBefore == -1 ? 10f : spacingBefore); // 前间距
        table.setSpacingAfter(spacingAfter == -1 ? 10f : spacingBefore); // 后间距
        List<PdfPRow> listRow = table.getRows();
        //设置列宽
        float[] columnWidths = new float[mValue.get(0).size()];
        for (int i = 0; i < mValue.get(0).size(); i++)
            columnWidths[i] = 1;
        table.setWidths(columnWidths);
        //行1
        for (int i = 0; i < mValue.size(); i++) {
            PdfPCell cells[] = new PdfPCell[mValue.get(i).size()];
            PdfPRow row1 = new PdfPRow(cells);
            for (int j = 0; j < mValue.get(i).size(); j++) {
                // 表格内容默认居中
                cells[j] = new PdfPCell(new Paragraph(mValue.get(i).get(j)));//单元格内容
                cells[j].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
                cells[j].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
                if (!haseFrame)
                    cells[j].setBorderWidth(0);
            }
            listRow.add(row1);
        }
        //把表格添加到文件中
        document.add(table);
    }

    public void addNullLine(Document document, int insertLineNum, int... height) throws Exception {
        if (document == null) throw new OperateExcetion("document is null");
        if (insertLineNum <= 0) throw new OperateExcetion("insertLineNum is " + insertLineNum);
        int LineHeight = 1;
        if (height.length > 0) {
            LineHeight = height[0] <= 0 ? 1 : height[0];
        }
        for (int i = 0; i < insertLineNum; i++)
            document.add(Chunk.NEWLINE.setLineHeight(LineHeight));
    }


    /**
     * 給 pdf 設置文件屬性的方法  ，暂时不需要 ，可以添加到工具类中
     *
     * @param document   document 对象
     * @param titile     标题
     * @param author     作者
     * @param subject    主题
     * @param keywords   关键字
     * @param createDate 是否需要添加创建时间
     * @param creator    应用程式
     * @throws Exception
     */
    public void addFileAttributes(Document document, String titile, String author, String subject, String keywords, boolean createDate, String creator)
            throws Exception {
        document.addTitle(titile);
        document.addAuthor(author);
        document.addSubject(subject);
        document.addKeywords(keywords);
        if (createDate)
            document.addCreationDate();
        document.addCreator(creator);

    }

    /**
     * 要设置的内容 todo 测试版本
     *
     * @param document
     * @param value
     * @throws Exception
     */
    public void addContent(Document document, String value) throws Exception {
        if (document == null) throw new OperateExcetion("addLine method document is null ");
        if (value == null)
            value = "";
        Font font1 = new Font(getBaseFont(), 12, Font.NORMAL);
        font1.setColor(BaseColor.BLACK);
        //添加内容
        Paragraph elements = new Paragraph(value, font1);
        document.add(elements);

    }


    public void addContentUse(String line, Document document) throws Exception {
        Font font = new Font(getBaseFont(), 6.8f);
        Paragraph par = new Paragraph();
        par.setLeading(9);
        char[] aa = line.toCharArray();
        boolean isLastChineseChar = false;
        StringBuilder newLine = new StringBuilder();
        for (int j = 0; j < line.length(); j++) {
            if (isLastChineseChar) {
                par.add(new Phrase(newLine.toString(), font));
                newLine.delete(0, newLine.length());
                isLastChineseChar = false;
            }
            newLine.append(aa[j]);
        }
        par.add(new Phrase(newLine.toString(), font));
        par.setAlignment(Element.ALIGN_LEFT);
        document.add(par);

    }

    /**
     * 关闭iText
     *
     * @param document 文档
     * @param writer   书写器
     * @throws DocumentException
     * @throws FileNotFoundException
     */
    public void getAllClose(Document document, PdfWriter writer) throws DocumentException, FileNotFoundException {
        //关闭文档
        document.close();
        //关闭书写器
        writer.close();
    }


    /**
     * 创建表格对象
     *
     * @param insertColumn
     * @return
     */
    public PdfPTable getDefultTable(int insertColumn) {
        return new PdfPTable(insertColumn);
    }

    public void addSpecialVerticalContentTable(
            PdfPTable table,
            Document document, int insertRow, int insertColumn,
            ArrayList<String> mergeListCells, List<List<String>> mValue,
            int spacingBefore, int spacingAfter, boolean hasFrame
    ) throws Exception {
        Font font = new Font(getBaseFont(), 12, Font.NORMAL);
        font.setColor(BaseColor.BLACK);
        // 添加空单元格到剩余的位置
        for (int row = 0; row < insertRow; row++) {
            for (int column = 0; column < insertColumn; column++) {
                if (shouldMergeCell(mergeListCells, row, column)) {
                    int[] mergedCellCoordinates = getMergedCellCoordinates(mergeListCells, row, column);
                    PdfPCell cell = new PdfPCell();
                    cell.setRowspan(mergedCellCoordinates[2] - mergedCellCoordinates[0] + 1);
                    cell.setColspan(mergedCellCoordinates[3] - mergedCellCoordinates[1] + 1);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
                    Phrase elements = new Phrase(mValue.get(row).get(column), font);
                    cell.setPhrase(elements);
                    table.addCell(cell);
                } else {
                    if (!skipeCell(mergeListCells, row, column)) {
                        PdfPCell cell = new PdfPCell();
                        Phrase elements = new Phrase(mValue.get(row).get(column), font);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
                        cell.setPhrase(elements);
                        table.addCell(cell);
                    }
                }
            }
        }

        document.add(table);
    }

    private boolean skipeCell(ArrayList<String> mergeListCells, int row, int col) {
        for (String mergedCell : mergeListCells) {
            String[] coordinates = mergedCell.split(":");
            String startCoordinate = coordinates[0];
            String endCoordinate = coordinates[1];
            int startRow = Integer.parseInt(startCoordinate.split("_")[0]);
            int startCol = Integer.parseInt(startCoordinate.split("_")[1]);
            int endRow = Integer.parseInt(endCoordinate.split("_")[0]);
            int endCol = Integer.parseInt(endCoordinate.split("_")[1]);
            if (row >= startRow && row <= endRow && col >= startCol && col <= endCol) {
                return true;
            }
        }
        return false;
    }

    // 判断是否需要合并单元格
    private boolean shouldMergeCell(ArrayList<String> mergedCells, int row, int col) {
        for (String mergedCell : mergedCells) {
            String[] coordinates = mergedCell.split(":");
            String startCoordinate = coordinates[0];
            int startRow = Integer.parseInt(startCoordinate.split("_")[0]);
            int startCol = Integer.parseInt(startCoordinate.split("_")[1]);
            if (row == startRow && col == startCol) {
                return true;
            }
        }


        return false;
    }

    // 获取合并单元格的起始和结束坐标
    private int[] getMergedCellCoordinates(ArrayList<String> mergedCells, int row, int col) {
        for (String mergedCell : mergedCells) {
            String[] coordinates = mergedCell.split(":");
            String startCoordinate = coordinates[0];
            String endCoordinate = coordinates[1];

            int startRow = Integer.parseInt(startCoordinate.split("_")[0]);
            int startCol = Integer.parseInt(startCoordinate.split("_")[1]);
            int endRow = Integer.parseInt(endCoordinate.split("_")[0]);
            int endCol = Integer.parseInt(endCoordinate.split("_")[1]);

            if (row >= startRow && row <= endRow && col >= startCol && col <= endCol) {
                return new int[]{startRow, startCol, endRow, endCol};
            }
        }
        // 默认返回当前坐标
        return new int[]{row, col, row, col};
    }


    public void clearCellLists() throws OperateExcetion {
        if (celllists.size() <= 0)
            throw new OperateExcetion("celllists size below 0 ");

        for (LinkedList<PdfPCell> cells : celllists) {
            if (cells == null)
                throw new OperateExcetion("celllists cotent size is null ");
            cells.clear();
        }
        celllists.clear();
    }

    public void initCellLists(int col, int row) throws OperateExcetion {
        if (col <= 0 || row <= 0)
            throw new OperateExcetion("init celll row or col below 0!");
        // 8 列 4 行
        clearCellLists();
        for (int j = 0; j < row; j++) {
            LinkedList<PdfPCell> a = new LinkedList<>();
            for (int i = 0; i < col; i++) {
                PdfPCell cell = new PdfPCell();
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
                a.add(cell);
            }
            celllists.add(a);
        }

    }

    public static ITextUtils getInstace() {
        return new ITextUtils();
    }

    public void addCellLists(int row) throws OperateExcetion {
        if (celllists.size() == 0) {
            throw new OperateExcetion(Utils.getApp().getString(R.string.operate_fail));
        }
        int cellNum = celllists.get(0).size();
        for (int i = 0; i < row; i++) {
            LinkedList<PdfPCell> cells = new LinkedList<>();
            for (int j = 0; j < cellNum; j++) {
                PdfPCell cell = new PdfPCell();
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
                cells.add(cell);
            }
            celllists.add(cells);
        }
    }


    /**
     * @param col    输入需要操作的列数，列数从 1开始
     * @param row    输入需要操作的行数，行数从 1开始
     * @param collen 输入要合并的列数，列数从 1 开始
     * @param rowlen 输入要合并的行数，行数从 1 开始
     * @throws OperateExcetion
     */
    public void mergeCellLists(int col, int row, int collen, int rowlen) throws OperateExcetion {
        int currentCol = col - 1;
        int currentrow = row - 1;
        int colsize = collen;
        int rowsize = rowlen;
        getInputValueState(col, row, colsize, rowsize);
        boolean cellMergedState = getCellMergedState(col, row, collen, rowlen);
        if (cellMergedState) { // todo 这里是否需要抛出操作异常？
//            throw new OperateExcetion(" input cell is merge . ");
            LogUtils.e(" input cell is merge . ");
            return;
        }
        // 判断单元格是否已经合并
        PdfPCell cell = new PdfPCell();
        cell.setRowspan(rowsize);
        cell.setColspan(colsize);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        for (int j = currentrow; j < currentrow + rowsize; j++) {
            for (int i = currentCol; i < currentCol + colsize; i++) {
//                LogUtils.e(" start value is : " + i + " j : " + j);
//                celllists.get(j).set(i, cell);
                celllists.get(j).set(i, cell);
            }
        }
//        celllists.get(currentrow).set(currentCol, cell);

        for (int i = currentrow; i < currentrow + rowsize; i++) {
            for (int j = currentCol; j < currentCol + colsize; j++)
                mergedCellList.add(new MergedCellBean(i, j));
        }
    }

    private void getInputValueState(int currentCol, int currentrow, int colsize, int rowsize) throws OperateExcetion {
        if (celllists.size() == 0)
            throw new OperateExcetion("mergeCellLists method show celllists size is 0 ");
        if (currentCol < 0 || currentrow < 0 || colsize <= 0 || rowsize <= 0)
            throw new OperateExcetion(" mergeCellLists method operate data < 0  ");
        LinkedList<PdfPCell> pdfPCells = celllists.get(0);
        if (pdfPCells == null || pdfPCells.size() == 0)
            throw new OperateExcetion(" mergeCellLists method operate celllists.get(0) is null or empty  ");
        if (currentCol == celllists.get(0).size() && colsize > 1)
            throw new OperateExcetion(" mergeCellLists method  operate size is end and operate len is :" + colsize);
        if (currentrow == celllists.size() && rowsize > 1)
            throw new OperateExcetion(" mergeCellLists method  operate size is end and operate row is :" + currentrow);
    }

    private boolean getCellMergedState(int col, int row, int collen, int rowlen) {
        int currentCol = col - 1;
        int currentrow = row - 1;
        try {
            getInputValueState(col, row, collen, rowlen);
            if (mergedCellList.size() == 0) {
//                throw new OperateExcetion("mergedCellList size is 0 ");
                LogUtils.e("mergedCellList size is 0 ");
                return false;
            }
            // 判断单元格是否合并
            for (int i = 0; i < mergedCellList.size(); i++) {
                if (mergedCellList.get(i).mergedRow == currentCol && mergedCellList.get(i).mergedCol == currentrow) {
                    LogUtils.e(" has merge : " + mergedCellList.get(i).mergedRow + mergedCellList.get(i).mergedCol);
                    return true;
                }
            }
            return false;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;

    }

    /**
     * todo 之后再次进行修改
     *
     * @throws Exception
     */
    public void setCellListsText() throws Exception {//int col,int row,String text,Font font
        Font font1 = new Font(getBaseFont(), 12, Font.NORMAL);
        font1.setColor(BaseColor.BLACK);
        int col1 = celllists.get(0).size(); // col 的长度
        int row1 = celllists.size(); // row 的长度
        LogUtils.e(" col1 num : " + celllists.size());
        for (int j = 0; j < row1; j++) {
            for (int i = 0; i < col1; i++) {
                PdfPCell cell = celllists.get(j).get(i);
                Phrase elements = new Phrase(String.format("row %d col %d ", j, i), font1);
                cell.setPhrase(elements);
            }
        }
    }


    public void saveCellListsToDoc(Document document, int beforePading, int afterPading) throws Exception {
        if (celllists.size() == 0) {
            throw new OperateExcetion("saveCellListsToDoc method celllists.size() is 0 ");
        }
        usrcelllists.clear();
        int col = celllists.get(0).size();
        PdfPTable table = new PdfPTable(col);
        int row = celllists.size();
        for (int j = 0; j < row; j++) {
            for (int i = 0; i < col; i++) {
                PdfPCell cell = celllists.get(j).get(i);
                if (usrcelllists.contains(cell.getId())) {
                    LogUtils.d(" show start value is " + i + " j : " + j);
                    LogUtils.d("has use cell");
                } else {
                    table.addCell(cell);
                    usrcelllists.add(cell.getId());
                }
            }
        }
        table.setSpacingBefore(beforePading); // 前间距
        table.setSpacingAfter(afterPading); // 后间距
        document.add(table);
    }

    /**
     * todo 暂时还没有解决的问题
     *  1. 插入 drawable 图片缓慢的问题
     *  2. 是否可以插入中文汉字
     *
     * @return
     */
    public boolean test() {

        FileCommonUtil.createOrExistsDir("/sdcard/data");
        // 1. 将图片保存到sd卡中


        File file = new File("/sdcard/data/itext" + System.currentTimeMillis() + ".pdf");
        if (file.exists()) {
            file.delete();
        }

        try {
            // todo 1. 将 gfgimage保存一下
            File imageFile = new File("/sdcard/data/gfgimage.jpg");
            if (!imageFile.exists()) {
                // 将图片保存到相对应的位置 中
                Drawable drawable = Utils.getApp().getResources().getDrawable(R.drawable.gfgimage);
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                FileOutputStream outputStream = new FileOutputStream(imageFile);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                outputStream.close();
            }

            //创建Document 对象
            Document document = new Document();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            PdfWriter writer = PdfWriter.getInstance(document, fileOutputStream);
            //打开文件
            document.open();

            // 1. 加入 标题
            addTitile(document, "this is my title ");
            initCellLists(8, 4);
            addCellLists(3);
            mergeCellLists(1, 1, 1, 3);
//            mergeCellLists(1, 1, 3, 3);

            setCellListsText();//0,0,0,0
            setCellTextColor(5, 5, BaseColor.BLUE);
            setCellTextColor(1, 1, BaseColor.PINK);
            saveCellListsToDoc(document, 10, 10);

            // 插入图片 如果插入资源图片是否会出现插入时间慢的问题 ？
            addResuceFileImg(Utils.getApp(), R.drawable.dog, Bitmap.CompressFormat.PNG, document);

            addLine(document, BaseColor.BLUE, 1f);

            addNullLine(document, 1);

            addSdCardFileImg("/sdcard/data/gfgimage.jpg", document);

            addNullLine(document, 1);

            addDottedLine(document, BaseColor.PINK, 1f);

            addContent(document, "ceshi");

            addNullLine(document, 1);

            addUnderLineContent(document, "作者名称");

            addNullLine(document, 1);

            addUnderLineContent(document, "           ");

            addNullLine(document, 1);

            addContent(document, "怒发冲冠，凭阑处、潇潇雨歇。抬望眼，仰天长啸，壮怀激烈。\n" +
                    "三十功名尘与土，八千里路云和月。莫等闲，白了少年头，空悲切！\n" +
                    "\n" +
                    "靖康耻，犹未雪；臣子恨，何时灭？驾长车，踏破贺兰山缺。\n" +
                    "壮志饥餐胡虏肉，笑谈渴饮匈奴血。待从头，收拾旧山河，朝天阙。");

            //使用工具类关闭 document 与 writer
            getAllClose(document, writer);
            fileOutputStream.close();
            Toast.makeText(Utils.getApp(), "pdf 生成 成功 !", Toast.LENGTH_SHORT).show();
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            LogUtils.e("ex : " + exception);
        }
        return false;
    }

    private void clearFontColro() {
        PdfPCell cell = celllists.get(0).get(0);
        Phrase phrase = cell.getPhrase();
        if (phrase != null) {
            phrase.getFont().setColor(BaseColor.BLACK);
        }
    }

    private void addSdCardFileImg(String charPath, Document document) throws Exception {
        addSdCardFileImg(charPath, document, 120f, 120f);
    }


    private void addSdCardFileImg(String charPath, Document document, float width, float height) throws Exception {
        if (charPath == null) throw new OperateExcetion("charPath is null");
        if (document == null) throw new OperateExcetion("document is null");
        if (width <= 0 || height <= 0)
            throw new OperateExcetion("width is " + width + " height is  " + height);
        Image image = Image.getInstance(charPath);
        image.scaleToFit(width, height);            //图片大小
        image.setAlignment(Image.MIDDLE);        //图片居中
        document.add(image);
    }


    private void addResuceFileImg(Context context, int drawableImageRes,
                                  Bitmap.CompressFormat bitmapFormat,
                                  Document document) throws Exception {
        addResuceFileImg(context, drawableImageRes, bitmapFormat, document, 120f, 120f);
    }


    private void addResuceFileImg(Context context, int drawableImageRes,
                                  Bitmap.CompressFormat bitmapFormat,
                                  Document document, float width, float height) throws Exception {
        if (context == null) throw new OperateExcetion("Context is null");
        if (bitmapFormat == null) throw new OperateExcetion("bitmapFormat is null");
        if (width <= 0 || height <= 0)
            throw new OperateExcetion("width is " + width + " height is  " + height);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), drawableImageRes);
        if (bitmap == null) throw new OperateExcetion("bitmap is null");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(bitmapFormat, 100, stream);
        Image image = Image.getInstance(stream.toByteArray());
        image.scaleToFit(width, height);            //图片大小
        image.setAlignment(Image.MIDDLE);        //图片居中
        document.add(image);
    }

    private boolean setCellTextColor(int col, int row, BaseColor color) throws Exception {
        int currentCol = col - 1;
        int currentRow = row - 1;
        if (currentCol < 0 || currentRow < 0) {
            LogUtils.e("输入的行数和列数出错!");
            return false;
        }
        if (celllists.size() == 0) {
            LogUtils.e("celllists is empty !");
            return false;
        }
        if (celllists.size() < row) {
            LogUtils.e("celllists size  < row !");
            return false;
        }
        if (celllists.get(row).size() == 0) {
            LogUtils.e("celllists.get(row).size() is empty !");
            return false;
        }
        if (celllists.get(row).size() < col) {
            LogUtils.e(" celllists.get(row).size() < col !");
            return false;
        }
//        PdfPCell cell1 = celllists.get(row).get(col);
        PdfPCell cell1 = celllists.get(currentRow).get(currentCol);
        if (cell1 == null) {
            LogUtils.e("cell1 is null !");
            return false;
        }
        Phrase phrase = cell1.getPhrase();
        if (phrase == null) {
            LogUtils.e("phrase is null !");
            return false;
        }
        // 1. 判断设置字体的内容是否是合并单元格
        boolean cellMergedState = getCellMergedState(col, row, 1, 1);
        LogUtils.e("current cell is : " + row + " col " + col);
        // 创建一个单元格对象
        PdfPCell cell = new PdfPCell();
        // 创建一个字体对象
        Font font1 = new Font(getBaseFont(), 12, Font.NORMAL);
        font1.setColor(color);
        // 创建一个块对象
        Chunk chunk = new Chunk(phrase.getContent(), font1);
        // 将块对象添加到单元格中
        cell.addElement(chunk);
        if (cellMergedState) {
            // 是合并单元格的对象
            cell.setColspan(cell1.getColspan());
            cell.setRowspan(cell1.getRowspan());
            // 获取当前 cell 的位置
            for (int i = 0; i < celllists.size(); i++) {
                for (int j = 0; j < celllists.get(i).size(); j++) {
                    if (celllists.get(i).get(j).getId() == cell1.getId()) {
                        celllists.get(i).set(j, cell);
                        break;
                    }
                }
            }
        } else {
            celllists.get(currentRow).set(currentCol, cell);
        }
        return true;


    }

    public void addLine(Document document, BaseColor baseColor, float linewidth) throws Exception {
        if (document == null) throw new OperateExcetion("addLine method document is null ");
        if (linewidth <= 0) throw new OperateExcetion(" linewidth is  " + linewidth);
        LineSeparator line = new LineSeparator();    //实线
        line.setLineColor(baseColor);
        line.setLineWidth(linewidth);
        Chunk chunk = new Chunk(line);
        document.add(chunk);

    }

    public void addDottedLine(Document document, BaseColor baseColor, float linewidth) throws Exception {
        if (document == null) throw new OperateExcetion("addLine method document is null ");
        if (baseColor == null) throw new OperateExcetion(" baseColor is null ");
        if (linewidth <= 0) throw new OperateExcetion(" linewidth is  " + linewidth);
        DottedLineSeparator dottedLineSeparator = new DottedLineSeparator();//点线
        dottedLineSeparator.setLineColor(baseColor);
        dottedLineSeparator.setLineWidth(linewidth);
        Chunk chunk = new Chunk(dottedLineSeparator);
        document.add(chunk);

    }

    public void addUnderLineContent(Document document, String value) throws Exception {
        if (document == null) throw new OperateExcetion("addLine method document is null ");
        if (value == null)
            value = "";
//        下划线
        Font font14Under = new Font(getBaseFont(), 14, Font.UNDERLINE);
        Paragraph elements = new Paragraph(value, font14Under);
        document.add(elements);

    }


    class OperateExcetion extends Exception {
        public OperateExcetion() {
        }

        public OperateExcetion(String message) {
            super(message);
        }
    }
}



