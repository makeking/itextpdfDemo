package com.bete.pdfutilks.bean;

import com.itextpdf.text.Font;

public class CellFontBean {
    private Font font;
    private int rowOrCell;
    private int type = 0;

    public CellFontBean() {

    }

    // type 中 0 为 row  1 为 cell
    public CellFontBean(Font font, int rowOrCell, int type) {
        this.font = font;
        this.rowOrCell = rowOrCell;
        this.type = type;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public int getRowOrCell() {
        return rowOrCell;
    }

    public void setRowOrCell(int rowOrCell) {
        this.rowOrCell = rowOrCell;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

