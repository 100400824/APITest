package com.demo.assist;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelTest {

	/**
	 * @throws Exception
	 * @return获得EXCEL表中的元素
	 * 
	 */

	public static void main(String[] args) throws Exception {

	}

	//获取sheet总行数
	public static int getRowNum(String filePath, String sheet) throws Exception {

		String path = filePath;

		XSSFWorkbook excelWBook;

		XSSFSheet excelWSheet;

		FileInputStream ExcelFile = new FileInputStream(path);

		excelWBook = new XSSFWorkbook(ExcelFile);

		excelWSheet = excelWBook.getSheet(sheet);

        return excelWSheet.getPhysicalNumberOfRows();

	}


    //获取sheet总列数
    public static int getColumnNum(String filePath, String sheet) throws Exception {

        String path = filePath;

        XSSFWorkbook excelWBook;

        XSSFSheet excelWSheet;

        FileInputStream ExcelFile = new FileInputStream(path);

        excelWBook = new XSSFWorkbook(ExcelFile);

        excelWSheet = excelWBook.getSheet(sheet);

        return excelWSheet.getRow(0).getPhysicalNumberOfCells();

    }

    //获取某一行的值
    public static String[] getRowValue(String filePath, String sheet, int getCell) throws Exception {

        String path = filePath;

        String cellValue = null;

        XSSFWorkbook excelWBook;

        XSSFSheet excelWSheet;

        FileInputStream ExcelFile = new FileInputStream(path);

        excelWBook = new XSSFWorkbook(ExcelFile);

        excelWSheet = excelWBook.getSheet(sheet);

        int columnNum = excelWSheet.getRow(0).getPhysicalNumberOfCells();;

        String[] rowAll = new String[columnNum];

        for (int i = 0; i < columnNum; i++) {

            try {
                try {

                    cellValue = excelWSheet.getRow(getCell).getCell(i).getStringCellValue();

                } catch (Exception e) {

                    cellValue = "" + (int) excelWSheet.getRow(getCell).getCell(i).getNumericCellValue();

                }
            } catch (Exception e) {

            }

            rowAll[i] = cellValue;
        }

        return rowAll;
    }


	//获取某一列的值
	public static String[] getColumnValue(String filePath, String sheet, int getCell) throws Exception {

		String path = filePath;

		String cellValue = null;

		XSSFWorkbook excelWBook;

		XSSFSheet excelWSheet;

		FileInputStream ExcelFile = new FileInputStream(path);

		excelWBook = new XSSFWorkbook(ExcelFile);

		excelWSheet = excelWBook.getSheet(sheet);

		int rows = excelWSheet.getPhysicalNumberOfRows();

		String[] rowAll = new String[rows];

		for (int i = 0; i < rows; i++) {

			try {
				try {

					cellValue = excelWSheet.getRow(i).getCell(getCell).getStringCellValue();

				} catch (Exception e) {

					cellValue = "" + (int) excelWSheet.getRow(i).getCell(getCell).getNumericCellValue();

				}
			} catch (Exception e) {

			}
			rowAll[i] = cellValue;
		}

		return rowAll;
	}
	
	//获取某一个单元格的元素
	public static String[] getCellValue(String filePaht, String sheet, int cell, int column) throws Exception {

		String path = filePaht;

		String cellValue = null;

		XSSFWorkbook excelWBook;

		XSSFSheet excelWSheet;

		FileInputStream ExcelFile = new FileInputStream(path);

		excelWBook = new XSSFWorkbook(ExcelFile);

		excelWSheet = excelWBook.getSheet(sheet);

		String[] rowAll = new String[column];

		for (int i = 0; i < column; i++) {

			try {

				cellValue = excelWSheet.getRow(cell).getCell(i).getStringCellValue();

			} catch (Exception e) {

				// cellValue = "" + (int)
				// excelWSheet.getRow(1).getCell(i).getNumericCellValue();

				System.out.println(e);
			}

			rowAll[i] = cellValue;
		}

		return rowAll;

	}

}
