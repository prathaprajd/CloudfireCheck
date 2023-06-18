package com.orangehrm.Regression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	public String[][] getCellData(String path, String sheetName) throws InvalidFormatException, IOException {
		FileInputStream stream = new FileInputStream(path);
		Workbook workbook = WorkbookFactory.create(stream);
		Sheet s = workbook.getSheet(sheetName);
		int rowcount = s.getLastRowNum();
		int cellcount = s.getRow(0).getLastCellNum();
		String data[][] = new String[rowcount][cellcount];
		for (int i = 1; i <= rowcount; i++) {
			Row r = s.getRow(i);
			for (int j = 0; j < cellcount; j++) {
				Cell c = r.getCell(j);
				try {
					if (c.getCellType() == CellType.STRING) {
						data[i - 1][j] = c.getStringCellValue();
					} else {
						data[i - 1][j] = String.valueOf(c.getNumericCellValue());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}

	public void generateExcelReports(String FilePath, String fileName, int sheetIndex, int ROW_NUM, int cellValue,
			String status) throws IOException {
		Workbook workbook_1 = null;
		FileOutputStream fout_1 = null;
		String fileExtension = fileName.substring(fileName.indexOf("."));

		if (fileExtension.equals(".xlsx")) {
			try {
				workbook_1 = new XSSFWorkbook(FilePath + fileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (fileExtension.equals(".xls")) {
			try {
				workbook_1 = new HSSFWorkbook();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Sheet sheet = workbook_1.getSheetAt(sheetIndex);

		Row row_1 = sheet.getRow(ROW_NUM);
		Cell cell_1 = row_1.getCell(cellValue);
		cell_1.setCellValue(status);

		try {
			fout_1 = new FileOutputStream(FilePath + File.separator + fileName);
			workbook_1.write(fout_1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		workbook_1.write(fout_1);
		workbook_1.close();

	}
}
