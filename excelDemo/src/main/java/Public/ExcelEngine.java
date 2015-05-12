package Public;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelEngine {
    public static String filepath;
    //    public static String inputfile;
    public static String sheetname;

    /**
     * 指定要读取的Excel文件
     */
    public static Workbook setExcel() throws IOException, InvalidFormatException {
        Workbook xls = null;
        FileInputStream file = new FileInputStream(new File(filepath));
        if (filepath.endsWith("xlsx")) {
            //2007
            xls = new XSSFWorkbook(file);
        } else if (filepath.endsWith("xls")) {
            //2003

            xls = new HSSFWorkbook(file);
        }
        return xls;
    }

    /**
     * 指定要读取的Excel表
     */
    public static Sheet setSheet() throws IOException, InvalidFormatException {
        Sheet sheet = setExcel().getSheet(sheetname);
        return sheet;
    }

    /**
     * 要读取的表所有行放入迭代器内待用
     */
    public static Iterator<Row> getAllRow() throws IOException, InvalidFormatException {
        Iterator<Row> rowIterator = setSheet().iterator();
        return rowIterator;
    }

    /**
     * @param rowNum：行数
     * @return 指定行数的数据(list)
     */
    @SuppressWarnings("unchecked")
    public static List getRowData(int rowNum) throws IOException, InvalidFormatException {
        List rowDataList = new ArrayList();
        Row row = setSheet().getRow(rowNum);
        int colNum = row.getPhysicalNumberOfCells();
        for (int i = 0; i < colNum; i++) {
            Cell rowData = row.getCell(i);
            if (rowData == null) {
                //System.out.println("[" + rowNum + "," + i + "]该单元格为空，请重新维护后再执行测试!");
                System.exit(0);
            }
            String stringRow = null;
            switch (rowData.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    double numCol = rowData.getNumericCellValue();
                    stringRow = String.valueOf(numCol);
                    break;
                case Cell.CELL_TYPE_STRING:
                    stringRow = rowData.getStringCellValue();
                    break;
            }
            rowDataList.add(stringRow);
        }
        return rowDataList;
    }


    /**
     * @param colNum：列数
     * @return 指定列数的数据(list)
     */
    @SuppressWarnings("unchecked")
    public static List getColData(int colNum) throws IOException, InvalidFormatException {
        int rowNum = setSheet().getLastRowNum();
        List colDataList = new ArrayList();
        Iterator<Row> rowIterator = getAllRow();
        for (int i = 1; i < rowNum; i++) {
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Cell colData = row.getCell(colNum);
                if (colData == null) {
                    //System.out.println("[" + i + "," + colNum + "]该单元格为空，请重新维护后再执行测试!");
                    System.exit(0);
                }
                String stringCol = null;
                switch (colData.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        double numCol = colData.getNumericCellValue();
                        //dataDrivenLog().info("该单元格内容为数字型测试数据"+numCol);
                        stringCol = String.valueOf(numCol);

                        break;
                    case Cell.CELL_TYPE_STRING:
                        //dataDrivenLog().info("该单元格内容为文本型测试数据"+colData);
                        stringCol = colData.getStringCellValue();
                        break;
                }

                colDataList.add(stringCol);
            }
        }
        return colDataList;
    }

    /**
     * 测试结果填入excel
     * Result:测试结果、colNum:测试结果填入的所在列
     */
    public static void returnResult(String Resutl, int colNum, int rowNum) throws IOException, InvalidFormatException {
        try {
            FileInputStream file = new FileInputStream(new File(filepath));
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheet(sheetname);
            Cell resultCell = sheet.getRow(rowNum).getCell(colNum);

            //dataDrivenLog().info("Result单元格初始化不能为null");
            //System.out.println(colNum+" and "+rowNum);
            resultCell.setCellValue(Resutl);
            //System.out.println(resultCell.toString());

            workbook.close();
            file.close();

            String outputfile = filepath;
            FileOutputStream outFile = new FileOutputStream(new File(outputfile));
            workbook.write(outFile);
            outFile.close();

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

/**调试代码
    public static void main(String []args) throws Exception {
        System.out.println(System.getProperty("user.dir"));//user.dir指定了当前的路径
        ExcelEngine.filepath = "F:\\IdeaProject\\excelDemo\\src\\main\\java\\Public\\apiTest.xls";
        //ExcelEngine.inputfile = "test.xls";
        ExcelEngine.sheetname = "api";
        System.out.println(ExcelEngine.getRowData(1).size());
        //returnResult("pass",4,1);
 }
}
*/