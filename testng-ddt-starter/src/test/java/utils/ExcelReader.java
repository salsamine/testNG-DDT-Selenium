package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;

public class ExcelReader {
    public static Object[][] readExcel (String filePath) {
        List<Object[]> rows = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(filePath);

            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);

            // Row 0 = header
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                if (row == null) {
                    continue;
                }

                String testCase = getCellValue(row.getCell(0));
                String username = getCellValue(row.getCell(1));
                String password = getCellValue(row.getCell(2));
                String expectedResult = getCellValue(row.getCell(3));

                rows.add(new Object[]{
                        testCase,
                        username,
                        password,
                        expectedResult
                });
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to read file excel " + filePath, e);
        }
        return rows.toArray(new Object[0][]);
    }
    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }
}
