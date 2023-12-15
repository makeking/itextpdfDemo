//package com.bete.pdfutilks.newUtils;
//
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
//import com.itextpdf.text.RectangleReadOnly;
//import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.PdfContentByte;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPRow;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.text.pdf.draw.LineSeparator;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//
///**
// * itext5工具类
// *
// * @author tsp
// */
//public class ITextUtils1 {
//
//    //副标题缩进
//    public static final int TITLE_RETRACT_FIVE = 75;
//    public static final int TITLE_RETRACT_LOSE_ZERO = -30;
//    public static BaseFont mBaseFont = null;
//
//
//    /**
//     * 基础信息
//     *
//     * @param document
//     * @param startDate      开始时间
//     * @param endDate        结束数据		没有就输出1
//     * @param userDeviceType 用户类型
//     * @param areaId         区域
//     * @param countryId      国家
//     * @throws DocumentException
//     * @throws IOException
//     */
//    public static void getConText(Document document, String startDate, String endDate,
//                                  String userDeviceType, String areaId, String countryId, String themeName, int retract) throws DocumentException, IOException {
//        //获取系统日期
//        SimpleDateFormat sFormat = new SimpleDateFormat("yyyy/MM/dd");
//        Date date = new Date(System.currentTimeMillis());
//        String date_time = sFormat.format(date);
//        String title_name = themeName + "_" + date_time;
//
//        BaseFont bfChinese = ITextUtils1.getBaseFont();                //设置中文字体
//        Font title_font = new Font(bfChinese, 20, Font.BOLD);                //标题字体
//        Font normal_font1 = new Font(bfChinese, 12, Font.NORMAL);        //数据字体
//        Font normal_font2 = new Font(bfChinese, 12, Font.BOLD);        //数据字体
//        Paragraph title = new Paragraph(title_name, title_font);        //添加标题字体
//        title.setAlignment(1);                                        //文本居中
//        document.add(title);
//        ITextUtils1.getBlank(document);
//
//        ITextUtils1.getTitleBasic(document, "基础信息", retract);                    //添加副标题
//
//        List<Object> list = new ArrayList<Object>();
//        list.add("时间段");
//        list.add("用户类型");
//        list.add("区域/国家");
//        list.add("生成报表日期");
//        if (endDate.equals("1")) {
//            list.add(startDate);        //查询的日期
//        } else {
//            list.add(startDate + "~" + endDate);        //查询的日期
//        }
//
//        if (!OUtils.isEmpty(userDeviceType)) {
//            String[] type = userDeviceType.split(",");
//            StringBuffer sb = new StringBuffer("");
//            for (int i = 0; i < type.length; i++) {
//                switch (type[i]) {
//                    case "0":
//                        sb.append("光伏用户");
//                        break;
//                    case "1":
//                        sb.append("离网储能用户");
//                        break;
//                    case "2":
//                        sb.append("光伏+储能用户");
//                        break;
//                    case "3":
//                        sb.append("储能用户");
//                        break;
//                    case "4":
//                        sb.append("智慧家庭用户");
//                        break;
//                    case "5":
//                        sb.append("自发自用用户");
//                        break;
//                    default:
//                        if (type[i].equals("16")) {
//                            sb.append("普通逆变器");
//                        } else if (type[i].equals("17")) {
//                            sb.append("MIX");
//                        } else if (type[i].equals("18")) {
//                            sb.append("MAX");
//                        } else if (type[i].equals("19")) {
//                            sb.append("SPA");
//                        } else if (type[i].equals("22")) {
//                            sb.append("TLX");
//                        } else if (type[i].equals("96")) {
//                            sb.append("储能机");
//                        }
//                        break;
//                }
//                sb.append(" \n");
//            }
//            list.add(sb.toString());
//        } else {
//            list.add("不限");
//        }
////
////        if (!OUtils.isEmpty(areaId)||!OUtils.isEmpty(countryId)) {
////            countryId = StaticParamUtils.getCountryText(OUtils.parseInt(countryId), "cn");//获取国家名字
////            areaId = StaticParamUtils.getAreaText(OUtils.parseInt(areaId), "cn");//获取区域名字
////            list.add(areaId+"/"+countryId);
////        }else{
////            list.add("不限");
////        }
//        list.add("不限");
//        list.add(date_time);
//
//        PdfPTable table = new PdfPTable(4);
//        for (int i = 0; i < list.size(); i++) {
//            PdfPCell cell = new PdfPCell();
//            String chunlName = (String) list.get(i);
//            Chunk chunk = null;
//            if (i < 4) {
//                chunk = new Chunk(chunlName, normal_font2);        //文本块
//            } else {
//                chunk = new Chunk(chunlName, normal_font1);        //文本块
//            }
//            cell.addElement(chunk);
//            cell.setBorder(0);
//            cell.setUseDescender(true);
//            cell.setHorizontalAlignment(Element.ALIGN_LEFT);//设置字体水平居中
//
//            table.addCell(cell);
//        }
//        if (retract < 0) {
//            table.setWidthPercentage(108);
//        }
//        float[] columnWidths = {1f, 0.6f, 0.6f, 0.6f};
//        table.setWidths(columnWidths);
//        document.add(table);
//    }
//
//    /**
//     * 添加副标题
//     *
//     * @param document 文档
//     * @param name     副标题名字
//     * @throws DocumentException
//     * @throws IOException
//     */
//    public static void getTitleBasic(Document document, String name, int retract) throws DocumentException, IOException {
//        BaseFont bf = getBaseFont();
//
//        Font title_subheading_font = new Font(bf, 17, Font.BOLD);            //字体
//        Chunk temp_bule = new Chunk(" ", title_subheading_font);            //文本蓝色小块
//        temp_bule.setBackground(new BaseColor(100, 149, 237));            //文本背景颜色
//        Chunk temp_blank = new Chunk(" ", title_subheading_font);        //文本空白
//        Chunk temp_name = new Chunk(name, title_subheading_font);        //文本标题文字
//
//        Paragraph paragraph = new Paragraph();        //段落
//        paragraph.add(temp_bule);
//        paragraph.add(temp_blank);
//        paragraph.add(temp_name);
//        paragraph.setFirstLineIndent(retract);        //缩进
//        document.add(paragraph);
//
//        Font font = new Font(getBaseFont(), 17, Font.BOLD);
//        Paragraph temp = new Paragraph(" ", font);
//        document.add(temp);
//
//        //getBlank(document);			//添加空白段落
//    }
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
//        ITextUtils1.getBlank(document);
//    }
//
//    /**
//     * 创建有边框表格
//     *
//     * @param width    表格的大小 为null则默认大小
//     * @param list     数据
//     * @param count    列数
//     * @param document
//     * @throws DocumentException
//     * @throws IOException
//     */
//    public static void getCreateTable(Document document, Integer width, List<?> list, int count) throws DocumentException, IOException {
//
//        BaseFont bfChinese = getBaseFont();
//        Font normal_font = new Font(bfChinese, 10, Font.NORMAL);//数据字体
//        Font tetle_font = new Font(bfChinese, 10, Font.BOLD);//数据字体
//
//        PdfPTable table = new PdfPTable(count);
//        for (int i = 0; i < list.size(); i++) {
//            PdfPCell cell = new PdfPCell();
//            String chunlName = list.get(i).toString();
//            if (i < count) {
//                Chunk chunk = new Chunk(chunlName, tetle_font);        //标题
//                cell.addElement(chunk);
//                cell.setBackgroundColor(new BaseColor(232, 232, 232));        //背景
//            } else {
//                Chunk chunk = new Chunk(chunlName, normal_font);        //文本块
//                cell.addElement(chunk);
//            }
//            cell.setBorderColor(new BaseColor(190, 190, 190));        //单元格边框颜色
//            cell.setUseDescender(true);            //是否居中
//            table.addCell(cell);
//        }
//        table.setHorizontalAlignment(1);
//        //自动把列扩大缩小
//        if (count >= 10) {
//            float[] columnWidths = new float[count];
//            for (int i = 0; i < count; i++) {
//                if (i > 1) {
//                    String name = (String) list.get(i);
//                    int num = name.length();
//                    if (num == 4) {
//                        columnWidths[i] = 0.75f;
//                    } else if (num > 4 && num < 7) {
//                        columnWidths[i] = 1f;
//                    } else if (num > 6 && num < 9) {
//                        columnWidths[i] = 1.35f;
//                    }
//                } else {
//                    if (i == 0) {
//                        columnWidths[i] = 0.4f;
//                    } else {
//                        columnWidths[i] = 1f;
//                    }
//
//                }
//            }
//            table.setWidths(columnWidths);
//        }
//
//        if (width != null) {    //为null则默认大小
//            table.setWidthPercentage(width);
//        }
//        document.add(table);
//        getBlank(document);
//    }
//
//
//    /**
//     * 用做设置生命周期导出
//     * 添加副标题
//     *
//     * @param document 文档
//     * @param name     副标题名字
//     * @throws DocumentException
//     * @throws IOException
//     */
//    public static void getTitleBasic1(Document document, String name) throws DocumentException, IOException {
//        BaseFont bf = getBaseFont();
//        Font title_subheading_font = new Font(bf, 12, Font.BOLD);
//        Paragraph paragraph = new Paragraph(name, title_subheading_font);
//        paragraph.setFirstLineIndent(10);        //缩进
//        document.add(paragraph);
//        Paragraph temp = new Paragraph(" ", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL));    //空白
//        document.add(temp);
//
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
//
//    /**
//     * 创建无边框表格
//     *
//     * @param document
//     * @param
//     * @param list
//     * @param count
//     * @throws DocumentException
//     * @throws IOException
//     */
//    public static void getCreateFramelessTable(Document document, List<?> list, int count) throws DocumentException, IOException {
//        Font normal_font = new Font(getBaseFont(), 10, Font.NORMAL);//数据字体
//
//        PdfPTable table = new PdfPTable(count);
//        for (int i = 0; i < list.size(); i++) {
//            PdfPCell cell = new PdfPCell();
//            String chunlName = list.get(i).toString();
//            Chunk chunk = new Chunk(chunlName, normal_font);
//            cell.addElement(chunk);
//            cell.setBorderWidth(0);                //边框代销  为0无边框
//            cell.setUseDescender(true);            //是否居中
//            table.addCell(cell);
//        }
//        table.setWidthPercentage(100);
//        document.add(table);
//    }
//
//    /**
//     * 创建无左右边框的表格
//     *
//     * @param document
//     * @param
//     * @param list
//     * @param count
//     * @throws DocumentException
//     * @throws IOException
//     */
//    public static void getCreateFramelessAroundTable(Document document, List<?> list, int count) throws DocumentException, IOException {
//        Font normal_font = new Font(getBaseFont(), 10, Font.NORMAL);//数据字体
//
//        PdfPTable table = new PdfPTable(count);
//        for (int i = 0; i < list.size(); i++) {
//            PdfPCell cell = new PdfPCell();
//            String chunlName = String.valueOf(list.get(i));
//            Chunk chunk = new Chunk(chunlName, normal_font);
//            cell.addElement(chunk);
//            cell.setBorderColor(new BaseColor(176, 196, 222));        //边框颜色
//            cell.disableBorderSide(12);
//            cell.setUseDescender(true);                                //是否居中
//            if (i < count) {
//                cell.setFixedHeight(22);
//                cell.setBackgroundColor(new BaseColor(176, 196, 222));        //背景色
//            }
//            table.addCell(cell);
//        }
//        table.setWidthPercentage(100);
//        document.add(table);
//    }
//
//    /**
//     * 判断为null
//     *
//     * @param
//     * @return
//     */
//    public static String isNull(Object o) {
//        return o == null ? " " : o.toString();
//    }
//
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
//    public static void addSpecialTitleTable(Document document
//            , LinkedList<LinkedList<String>> mValue, int spacingBefore, int spacingAfter, boolean haseFrame
//            , BaseColor baseColor
//    ) throws Exception {
//        // 1. 列的数量 2. 行的数量 3. 标题的内容 4.
//        PdfPTable table = new PdfPTable(mValue.get(0).size());
//        table.setWidthPercentage(100); // 宽度100%填充
//        table.setSpacingBefore(spacingBefore == -1 ? 10f : spacingBefore); // 前间距
//        table.setSpacingAfter(spacingAfter == -1 ? 10f : spacingBefore); // 后间距
//
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
//                if (baseColor != null) {
//                    cells[j].setBackgroundColor(baseColor);
//                    cells[j].setPadding(2);
//                }
//                cells[j].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
//                cells[j].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
//                if (!haseFrame)
//                    cells[j].setBorderWidth(0);
//            }
//            listRow.add(row1);
//        }
//        //把表格添加到文件中
//        document.add(table);
//
//    }
//    /*
//    合并单元的List
//    * */
//
//    /*
//     mergeListCells 合并单元格的填写规则 0_0:0_2 这种
//     */
//
//    public static void addSpecialVerticalContentTable1(Document document,
//                                                       int insertRow, int inertColumn, ArrayList<String> mergeListCells,
//                                                       List<List<String>> mValue, int spacingBefore, int spacingAfter, boolean haseFrame
//            , BaseColor baseColor
//    ) throws Exception {
//        BaseFont baseFont = BaseFont.createFont("assets/fonts/brandon_medium.otf", "UTF-8", BaseFont.EMBEDDED);
//        Font font = new Font(baseFont, 12, Font.NORMAL);
//        font.setColor(BaseColor.BLACK);
//        // 1. 将合并列表的List 取出 开始位置 和 结束位置的 List
//
//        // 创建表格对象
//        PdfPTable table = new PdfPTable(inertColumn);
//        for (String mergeCells : mergeListCells) {
//            String[] split = mergeCells.split(":");
//            String[] startSplit = split[0].split("_");
//            String[] endSplit = split[1].split("_");
//
//            int startRow = Integer.parseInt(startSplit[0]);
//            int startColumn = Integer.parseInt(startSplit[1]);
//            int endRow = Integer.parseInt(endSplit[0]);
//            int endColumn = Integer.parseInt(endSplit[1]);
//
//            PdfPCell cell = new PdfPCell();
//            cell.setRowspan(endRow - startRow + 1);
//            cell.setColspan(endColumn - startColumn + 1);
//            table.addCell(cell);
//        }
//        // 添加空单元格到剩余的位置
//        for (int row = 0; row < insertRow; row++) {
//            for (int column = 0; column < inertColumn; column++) {
//                boolean isMergedCell = false;
//
//                // 检查当前位置是否已经合并过
//                for (String mergeCells : mergeListCells) {
//                    String[] split = mergeCells.split(":");
//                    String[] startSplit = split[0].split("_");
//                    String[] endSplit = split[1].split("_");
//
//                    int startRow = Integer.parseInt(startSplit[0]);
//                    int startColumn = Integer.parseInt(startSplit[1]);
//                    int endRow = Integer.parseInt(endSplit[0]);
//                    int endColumn = Integer.parseInt(endSplit[1]);
//
//                    if (row >= startRow && row <= endRow && column >= startColumn && column <= endColumn) {
//                        isMergedCell = true;
//                        break;
//                    }
//                }
//
//                if (!isMergedCell) {
//                    table.addCell(new Phrase(String.format("Cell %d %d",row,column), font));
//                }
//            }
//        }
//
//        document.add(table);
////        test1();
//
//
//    }
//
//    private static void test1() {
//        //        List<Integer> startRow = new ArrayList<>();
////        List<Integer> startColumn = new ArrayList<>();
////        List<Integer> endRow = new ArrayList<>();
////        List<Integer> endColumn = new ArrayList();
////
////        for (String mergeCells : mergeListCells) {
////            String[] split = mergeCells.split(":");
////            String[] startSplit = split[0].split("_");
////            String[] endSplit = split[1].split("_");
////            startRow.add(Integer.parseInt(startSplit[0]));
////            startColumn.add(Integer.parseInt(startSplit[1]));
////            endRow.add(Integer.parseInt(endSplit[0]));
////            endColumn.add(Integer.parseInt(endSplit[1]));
////        }
////
////        PdfPTable table = new PdfPTable(inertColumn);
////        table.setWidthPercentage(100); // 宽度100%填充
////        table.setSpacingBefore(spacingBefore == -1 ? 10f : spacingBefore); // 前间距
////        table.setSpacingAfter(spacingAfter == -1 ? 10f : spacingBefore); // 后间距
////
////        // 1. 设置内容
////        for (int i = 0; i < inertColumn; i++) {
////            for (int j = 0; j < insertRow; j++) {
////                table.addCell(new Phrase(i + "_" + j, font));
////
////            }
////        }
////        document.add(table);
//    }
//
////    public static void addSpecialVerticalContentTable(Document document
////            , List<List<String>> mValue, int spacingBefore, int spacingAfter, boolean haseFrame
////
////
////            , BaseColor baseColor
////    ) throws Exception {
////        List<List<Integer>> showIntegerValue = new LinkedList<>();
////        // todo 未完成
////        showIntegerValue.add(Arrays.asList(new Integer[]{5, 1, 1, 1, 1, 5, 5}));
////        showIntegerValue.add(Arrays.asList(new Integer[]{5, 1, 1, 1, 1, 5, 5}));
////        showIntegerValue.add(Arrays.asList(new Integer[]{5, 1, 1, 1, 1, 5, 5}));
////        showIntegerValue.add(Arrays.asList(new Integer[]{5, 1, 1, 1, 1, 5, 5}));
////        showIntegerValue.add(Arrays.asList(new Integer[]{5, 1, 1, 1, 1, 5, 5}));
////
////
////        BaseFont baseFont = BaseFont.createFont("assets/fonts/brandon_medium.otf", "UTF-8", BaseFont.EMBEDDED);
////        Font font = new Font(baseFont, 12, Font.NORMAL);
////        font.setColor(BaseColor.BLACK);
////
////        // 1. 创建列的表
////
////        //包含两列的表格
////        PdfPTable table = new PdfPTable(mValue.size());
////        for (int i = 0; i < showIntegerValue.size(); i++) {
////            for (int j = 0; j < showIntegerValue.get(i).size(); j++) {
////                if (showIntegerValue.get(i).get(j) == 1) {
////                    table.addCell(new Phrase(mValue.get(i).get(j), font));
////                } else {
////
////                }
////            }
////        }
////
////////        PdfPTable table = new PdfPTable(2);
//////        table.addCell(new Phrase("cell1", font));
//////        table.addCell(new Phrase("cell2", font));
//////        //跨两行单元格
//////        PdfPCell rowSpanCell = new PdfPCell(new Phrase("11", font));
////////        rowSpanCell.setRowspan(2);
//////        rowSpanCell.setRowspan(7);
//////        //水平居中
//////        rowSpanCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
//////        //垂直居中
//////        rowSpanCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
//////        table.addCell(rowSpanCell);
//////
//////        table.addCell(new Phrase("222", font));
//////        table.addCell(new Phrase("333", font));
//////
//////        //跨两列单元格
//////        PdfPCell columnSpan = new PdfPCell(new Phrase("44", font));
//////        //水平居中
//////        columnSpan.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
//////        columnSpan.setColspan(2);
//////        table.addCell(columnSpan);
////
////
//////        testDemo(table,font);
////        document.add(table);
////    }
//
////    private static void testDemo(PdfPTable table, Font font) {
////        PdfPCell rowSpanCell = new PdfPCell(new Phrase("Condition-1", font));
////        rowSpanCell.setRowspan(5);
////        rowSpanCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
////        rowSpanCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
////        table.addCell(new Phrase("FAM", font));
////        table.addCell(new Phrase("1", font));
////        table.addCell(new Phrase("2", font));
////        table.addCell(new Phrase("3", font));
////        PdfPCell rowSpanCel2 = new PdfPCell(new Phrase("Condition-1", font));
////        rowSpanCel2.setRowspan(5);
////        rowSpanCel2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
////        rowSpanCel2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
////        table.addCell(rowSpanCel2);
////        PdfPCell rowSpanCel3 = new PdfPCell(new Phrase("Condition-1", font));
////        rowSpanCel3.setRowspan(5);
////        rowSpanCel3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
////        rowSpanCel3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
////        table.addCell(rowSpanCel2);
////    }
//
//
//    /**
//     * 特殊默认版本的列表
//     *
//     * @param document      document 对象
//     * @param mValue        要添加的内容
//     * @param spacingBefore 前间距，如果不需要设置请使用 -1 ，默认为 10
//     * @param spacingAfter  后间距，如果不需要设置请使用 -1 ，默认为 10
//     * @throws Exception
//     */
//    public static void addSpecialTable(Document document
//            , LinkedList<LinkedList<String>> mValue, int spacingBefore, int spacingAfter, boolean haseFrame) throws Exception {
//        /**
//         * todo
//         * 1. 如何设置合并单元格的数据 ？
//         * 1.1. 列数在这个版本中可以不需要设置，但是行数需要进行设置
//         */
//        // 1. 列的数量 2. 行的数量 3. 标题的内容 4.
//        PdfPTable table = new PdfPTable(mValue.get(0).size());
//        table.setWidthPercentage(100); // 宽度100%填充
//        table.setSpacingBefore(spacingBefore == -1 ? 10f : spacingBefore); // 前间距
//        table.setSpacingAfter(spacingAfter == -1 ? 10f : spacingBefore); // 后间距
//
//        List<PdfPRow> listRow = table.getRows();
//        //设置列宽
//        float[] columnWidths = new float[mValue.get(0).size()];
//        for (int i = 0; i < mValue.get(0).size(); i++)
//            columnWidths[i] = 1;
//        table.setWidths(columnWidths);
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
//    /**
//     * 可以进行参考
//     *
//     * @param map
//     */
//    public void labelPrinting(Map map) {
//        List<Map> list = (List<Map>) map.get("detailList");
//        PdfContentByte waterMar;
//        try {
//            //文档对象  实现A4纸页面
//            Document document = new Document();
//            //设置文档的页边距就是距离页面边上的距离，分别为：左边距，右边距，上边距，下边距
//            document.setMargins(70, 70, 20, 20);
//            //这个是生成破pdf的位置以及名称
//            File pdfFile = new File("物料标签.pdf");
//            FileOutputStream fileOutputStream = new FileOutputStream(pdfFile);
//            PdfWriter.getInstance(document, fileOutputStream);
//            //打开文档
//            document.open();
//            //使用字体，正文字体
//            Font font = new Font(BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED), 15, Font.BOLD);
//
//            //循环多页
//            for (Map m : list) {
//                //设置每页大小
//                document.setPageSize(new RectangleReadOnly(595.0F, 600.0F));
//                //创建新的一页
//                document.newPage();
//                // -------------------二维码图片 ----------------
//                ByteArrayOutputStream out = new ByteArrayOutputStream();
////                ImageIO.write(QRCodeUtils.drawQRCodeWithContent(getMapValue(m, "materialsLabel")), "png", out);
//                Image image = Image.getInstance(out.toByteArray());
//
//
//                Paragraph title = new Paragraph("物料标签", new Font(BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED), 21, Font.BOLD));
//                title.setAlignment(Element.ALIGN_CENTER);
//                title.setPaddingTop(100f);
//                title.setSpacingAfter(20f);
//                document.add(title);
//
//                PdfPTable table = new PdfPTable(4);
//                table.setWidthPercentage(100); // Width 100%
//                float[] columnWidths = {1f, 2f, 1f, 2f};
//                table.setPaddingTop(205f);
//                table.setWidths(columnWidths);
//                table.addCell(getPdfPCell("名称", font));
//                table.addCell(getPdfPCell(getMapValue(m, "materialsName"), font, 3, 1));
//                document.add(table);
//
//                table = new PdfPTable(4);
//                table.setWidths(columnWidths);
//                table.setWidthPercentage(100);
//                table.addCell(getPdfPCell("数量", font));
//                table.addCell(getPdfPCell(getMapValue(m, "materialsNum"), font));
//                table.addCell(getPdfPCell("日期", font));
//                table.addCell(getPdfPCell(getMapValue(m, "materialsDate"), font));
//                document.add(table);
//
//                table = new PdfPTable(3);
//                float[] columnWidths2 = {1f, 2f, 1f};
//                table.setWidths(columnWidths2);
//                table.setWidthPercentage(100);
//                table.addCell(getPdfPCell("料号", font));
//                table.addCell(getPdfPCell(getMapValue(m, "materialsCode"), font, 2, 1));
//                table.addCell(getPdfPCell("标签号", font));
//                table.addCell(getPdfPCell(getMapValue(m, "materialsLabel"), font, 2, 1));
//                table.addCell(getPdfPCell("SAP批次", font));
//                table.addCell(getPdfPCell(getMapValue(m, "materialsSapBatch"), font, 2, 1));
//
//                PdfPTable mergeTable = new PdfPTable(2);
//                float[] columnWidths3 = {4f, 2f};
//                mergeTable.setWidths(columnWidths3);
//                mergeTable.setWidthPercentage(100);
//                PdfPCell leftCell = new PdfPCell(table);
//                mergeTable.addCell(leftCell);
//
//                //合并3行
//                PdfPCell rightCell = getPdfPCell("", font);
//                rightCell.setBorderColor(BaseColor.LIGHT_GRAY);
//                rightCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                rightCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                rightCell.setPadding(1);
//                rightCell.setFixedHeight(120);
//                rightCell.setImage(image);
//                mergeTable.addCell(rightCell);
//                document.add(mergeTable);
//
//
//                table = new PdfPTable(4);
//                table.setWidths(columnWidths);
//                table.setWidthPercentage(100);
//                table.addCell(getPdfPCell("品保判定", font));
//                table.addCell(getPdfPCell(getMapValue(m, "qualityAssurance"), font));
//                table.addCell(getPdfPCell("校验", font));
//                table.addCell(getPdfPCell(getMapValue(m, "check"), font));
//                document.add(table);
//
//                table = new PdfPTable(4);
//                table.setWidths(columnWidths);
//                table.setWidthPercentage(100);
//                table.addCell(getPdfPCell("有效日期", font));
//                table.addCell(getPdfPCell(getMapValue(m, "effectiveDate"), font));
//                table.addCell(getPdfPCell("配料员签字", font));
//                table.addCell(getPdfPCell(getMapValue(m, "signatureOfBatcher"), font));
//                document.add(table);
//
//                table = new PdfPTable(4);
//                table.setWidths(columnWidths);
//                table.setWidthPercentage(100);
//                table.addCell(getPdfPCell("细度um", font));
//                table.addCell(getPdfPCell(getMapValue(m, "finenessUm"), font));
//                table.addCell(getPdfPCell("外观", font));
//                table.addCell(getPdfPCell(getMapValue(m, "appearance"), font));
//                document.add(table);
//
//                table = new PdfPTable(4);
//                table.setWidths(columnWidths);
//                table.setWidthPercentage(100);
//                table.addCell(getPdfPCell("固含量%标准值", font));
//                table.addCell(getPdfPCell(getMapValue(m, "solidsStandard"), font));
//                table.addCell(getPdfPCell("检测值", font));
//                table.addCell(getPdfPCell(getMapValue(m, "solidsDetection"), font));
//                document.add(table);
//
//                table = new PdfPTable(4);
//                table.setWidths(columnWidths);
//                table.setWidthPercentage(100);
//                table.addCell(getPdfPCell("粘度cps标准值", font));
//                table.addCell(getPdfPCell(getMapValue(m, "viscosityStandard"), font));
//                table.addCell(getPdfPCell("检测值", font));
//                table.addCell(getPdfPCell(getMapValue(m, "viscosityDetection"), font));
//                document.add(table);
//
//                table = new PdfPTable(4);
//                table.setWidths(columnWidths);
//                table.setWidthPercentage(100);
//                table.addCell(getPdfPCell("备注", font));
//                table.addCell(getPdfPCell(getMapValue(m, "remark"), font, 3, 1));
//                document.add(table);
//            }
//
////            document.close();
////            pdfWriter.close();
//
////            FileInputStream in = new FileInputStream(pdfFile);
////            ServletOutputStream outputStream = res.getOutputStream();
////            byte[] buff = new byte[1024];
////            int n;
////            while ((n = in.read(buff)) != -1) {
////                //将字节数组的数据全部写入到输出流中
////                outputStream.write(buff, 0, n);
////            }
////            //强制将缓存区的数据进行输出
////            outputStream.flush();
////            //关流
////            outputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static PdfPCell getPdfPCell(String value, Font font) {
//        return getPdfPCell(value, font, 0, 0, null);
//    }
//
//    public static PdfPCell getPdfPCell(String value, Font font, int colspan, int rowspan) {
//        return getPdfPCell(value, font, colspan, rowspan, null);
//    }
//
//    public static PdfPCell getPdfPCell(String value, Font font, int colspan, int rowspan, Image image) {
//        PdfPCell cell = new PdfPCell(new Phrase(value, font));
//        cell.setUseBorderPadding(true);
//        cell.setBorderColor(BaseColor.LIGHT_GRAY);
//        cell.setFixedHeight(40);
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//        cell.setColspan(colspan);
//        cell.setRowspan(rowspan);
////        if(OUtils.isNotEmpty(image)){
////            cell.setImage(image);
////        }
//        return cell;
//    }
//
//    public String getMapValue(Map map, String key) {
//        return OUtils.isEmpty((String) map.get(key)) ? "" : map.get(key).toString();
//    }
//
//
//}
//
//
//
