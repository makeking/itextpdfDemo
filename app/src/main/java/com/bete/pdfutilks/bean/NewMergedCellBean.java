package com.bete.pdfutilks.bean;

public class NewMergedCellBean {
    private int currentCell;
    private int startRow;
    private int mergedNum;
    private int num;

    public NewMergedCellBean() {
    }

    public NewMergedCellBean(int currentCell, int startRow, int mergedNum, int num) {
        this.currentCell = currentCell;
        this.startRow = startRow;
        this.mergedNum = mergedNum;
        this.num = num;
    }

    public int getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(int currentCell) {
        this.currentCell = currentCell;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getMergedNum() {
        return mergedNum;
    }

    public void setMergedNum(int mergedNum) {
        this.mergedNum = mergedNum;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
