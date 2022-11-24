package com.api.helper;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.api.entity.Answersheet;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Helper {


    //check that file is of excel type or not
    public static boolean checkExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();

        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }

    }

    //convert excel to list of products

    public static List<Answersheet> convertExcelToListOfProduct(InputStream is) {
        List<Answersheet> list = new ArrayList<>();

        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("data"); 

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cid = 0;

              Answersheet a = new Answersheet();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cid) {
                        case 0:
                           a.setQuestionId((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            a.setQuestion(cell.getStringCellValue());
                            break;
                        case 2:
                           a.setOption1(cell.getStringCellValue());
                            break;
                        case 3:
                          a.setOption2(cell.getStringCellValue());
                            break;
                        case 4:
                        	a.setOption3(cell.getStringCellValue());
                        	break;
                        case 5:
                        	a.setOption4(cell.getStringCellValue());
                        	break;
                        case 6:
                        	a.setAnswer(cell.getStringCellValue());
                        	break;
                        default:
                            break;
                    }
                    cid++;

                }

                list.add(a);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }


}
