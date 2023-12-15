package com.bete.pdfutilks.bean;

public class MergedCellBean {
    public int mergedRow;
    public int mergedCol;

    public int getMergedRow() {
        return mergedRow;
    }

    public MergedCellBean(int mergedRow, int mergedCol) {
        this.mergedRow = mergedRow;
        this.mergedCol = mergedCol;
    }

    public void setMergedRow(int mergedRow) {
        this.mergedRow = mergedRow;
    }

    public int getMergedCol() {
        return mergedCol;
    }

    public void setMergedCol(int mergedCol) {
        this.mergedCol = mergedCol;
    }

}
