package Util;

import com.sun.javaws.IconUtil;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;  // dependency poi-ooxml
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class XlsReader {

//    public XlsReader() throws FileNotFoundException {
//    }

    String path = ".\\ExcelFiles\\Password.xlsx";  // (.\\) represents current project file path
    XSSFWorkbook wb = null;


    public XSSFWorkbook getWorkbook() {
        // opt 1. put this in a method with try catch block
        //opt 2. use a constructor with exception declaration.

        try {
            FileInputStream file = new FileInputStream(path);
            XSSFWorkbook wb = new XSSFWorkbook(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return wb;  // what is best implementation of this return objetc, in a methos like this or in constructor of class
    }

    public void getData()
    {   //wb = getWorkbook();
        XSSFSheet sheet = getWorkbook().getSheetAt(0);
        // method 1. read using for loop

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();

        for(int r=0;r< rows;r++){

            XSSFRow row = sheet.getRow(r);
            for(int c=0;c< cols;c++){
                XSSFCell cell = row.getCell(c);
                switch (cell.getCellType())    // also include empty cell vaiue
                {
                    case STRING: System.out.println(cell.getStringCellValue().trim());break;
                    case NUMERIC: System.out.println(cell.getNumericCellValue());break;
                    case BOOLEAN : System.out.println(cell.getBooleanCellValue());break;
//                    case FORMULA: System.out.println(cell.getCellFormula());

                }
            }
            System.out.println();

        }
    }


}
