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
     * ָ��Ҫ��ȡ��Excel�ļ�
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
     * ָ��Ҫ��ȡ��Excel��
     */
    public static Sheet setSheet() throws IOException, InvalidFormatException {
        Sheet sheet = setExcel().getSheet(sheetname);
        return sheet;
    }

    /**
     * Ҫ��ȡ�ı������з���������ڴ���
     */
    public static Iterator<Row> getAllRow() throws IOException, InvalidFormatException {
        Iterator<Row> rowIterator = setSheet().iterator();
        return rowIterator;
    }

    /**
     * @param rowNum������
     * @return ָ������������(list)
     */
    @SuppressWarnings("unchecked")
    public static List getRowData(int rowNum) throws IOException, InvalidFormatException {
        List rowDataList = new ArrayList();
        Row row = setSheet().getRow(rowNum);
        int colNum = row.getPhysicalNumberOfCells();
        for (int i = 0; i < colNum; i++) {
            Cell rowData = row.getCell(i);
            if (rowData == null) {
                //System.out.println("[" + rowNum + "," + i + "]�õ�Ԫ��Ϊ�գ�������ά������ִ�в���!");
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
     * @param colNum������
     * @return ָ������������(list)
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
                    //System.out.println("[" + i + "," + colNum + "]�õ�Ԫ��Ϊ�գ�������ά������ִ�в���!");
                    System.exit(0);
                }
                String stringCol = null;
                switch (colData.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        double numCol = colData.getNumericCellValue();
                        //dataDrivenLog().info("�õ�Ԫ������Ϊ�����Ͳ�������"+numCol);
                        stringCol = String.valueOf(numCol);

                        break;
                    case Cell.CELL_TYPE_STRING:
                        //dataDrivenLog().info("�õ�Ԫ������Ϊ�ı��Ͳ�������"+colData);
                        stringCol = colData.getStringCellValue();
                        break;
                }

                colDataList.add(stringCol);
            }
        }
        return colDataList;
    }

    /**
     * ���Խ������excel
     * Result:���Խ����colNum:���Խ�������������
     */
    public static void returnResult(String Resutl, int colNum, int rowNum) throws IOException, InvalidFormatException {
        try {
            FileInputStream file = new FileInputStream(new File(filepath));
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheet(sheetname);
            Cell resultCell = sheet.getRow(rowNum).getCell(colNum);

            //dataDrivenLog().info("Result��Ԫ���ʼ������Ϊnull");
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

/**���Դ���
    public static void main(String []args) throws Exception {
        System.out.println(System.getProperty("user.dir"));//user.dirָ���˵�ǰ��·��
        ExcelEngine.filepath = "F:\\IdeaProject\\excelDemo\\src\\main\\java\\Public\\apiTest.xls";
        //ExcelEngine.inputfile = "test.xls";
        ExcelEngine.sheetname = "api";
        System.out.println(ExcelEngine.getRowData(1).size());
        //returnResult("pass",4,1);
 }
}
*/