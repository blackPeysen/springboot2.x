package com.org.peysen.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 操作excel的工具类
 * Created by mengmeng.Pei
 * 2019/7/4 13:59
 */
public class ExcelUtil {

    /**
     * 解析excel文档（支持xls、xlsx格式）
     * @param path - 文件路径
     * @param formula - 是否获取公式结果
     * @param sdf - 日期格式
     * @return List - 结果表
     * @throws Exception - 打开文件失败
     */
    public static List<Map> parse(String path, boolean formula, SimpleDateFormat sdf) throws Exception {
        List<Map> list = null;
        File file = null;
        Workbook wb = null;

        if(StringUtils.isNotBlank(path)){
            file = new File(path);

            if (file.isFile()) {
                wb= createWorkbook(file);

                if(wb!=null){
                    System.out.println("当前活动sheet" + wb.getActiveSheetIndex());
                    System.out.println("当前几个文档"+wb.getNumberOfSheets());
                    list=new ArrayList<Map>();
                    int max=wb.getNumberOfSheets();
                    for(int sheetNum=0;sheetNum<max;sheetNum++){
                        //解析sheet表
                        list.add(getSheet(wb, Integer.valueOf(sheetNum), formula, sdf));
                    }
                }
            } else {
                throw new Exception("文件不存在");
            }
        }

        return list;
    }

    /**
     * 根据文件后缀创建Workbook
     * @param file
     * @return
     */
    public static Workbook createWorkbook(File file){
        Workbook wb=null;
        String fileName="";

        if(file!=null){
            fileName=file.getName();
            try {
                if (fileName.endsWith("xls")){
                    wb=new HSSFWorkbook(new FileInputStream(file));
                } else if (fileName.endsWith("xlxs")){
                    wb=new XSSFWorkbook(new FileInputStream(file));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return wb;
    }

    /**
     * 获取sheet表内容
     * @param wb - 文档
     * @param sheetNum - 打开那张sheet表
     * @param formula - 是否获得公式结果
     * @param sdf - 日期格式
     * @return Map - 结果表
     */
    public static Map<Integer, Map<Integer, String>> getSheet(Workbook wb, Integer sheetNum, boolean formula, SimpleDateFormat sdf) {
        String sheetName = "";
        Sheet sheet = null;
        Map<Integer, Map<Integer, String>> map=null;

        if(sheetNum!=null){
            sheetName = wb.getSheetName(sheetNum);
            System.out.println("打开了sheet表：" + sheetName);
            sheet = wb.getSheet(sheetName);
            //解析所有单元格
            map = getRowAndCell(sheet, formula, sdf);
        }

        return map;
    }

    /**
     * 从sheet表中获取每行每列的值
     * @param sheet - sheet表
     * @param formula - 是否获取公式结果
     * @param sdf - 日期格式
     * @return Map - 结果表
     */
    public static Map<Integer, Map<Integer, String>> getRowAndCell(Sheet sheet, boolean formula, SimpleDateFormat sdf) {
        Map<Integer,Map<Integer,String>>rowMap=null;
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();

        if(lastRowNum>0){
            rowMap=new HashMap<Integer,Map<Integer,String>>();

            // 遍历行
            for (int rowNum = firstRowNum; rowNum <= lastRowNum; rowNum++ ) {
                Row row = sheet.getRow(rowNum);
                int firstCellNum = row.getFirstCellNum();
                int lastCellNum = row.getLastCellNum();
                Map<Integer,String> cellMap=new HashMap<Integer,String>();
                // 遍历列
                for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++ ) {
                    Cell cell = row.getCell(cellNum);
                    int type = cell.getCellType();
                    String data=getValue(cell, formula, sdf);//根据单元格具体类型获得内容
                    System.out.println("第" + rowNum + "行，第" + cellNum + "列，类型是" + type +"，内容是："+data);
                    cellMap.put(cellNum, data);
                }
                rowMap.put(rowNum, cellMap);
            }
        }
        return rowMap;
    }

    /**
     * 获取单元格内容
     * @param cell - 单元格
     * @param sdf - 日期格式
     * @param formula - 是否得出公式结果
     * @return String - 单元格内容
     */
    private static String getValue(Cell cell, boolean formula, SimpleDateFormat sdf) {
        String data = null;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: // 数字
                data = parseDate(cell, sdf);
                break;
            case Cell.CELL_TYPE_STRING: // 字符串

                data = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN: // Boolean
                data = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: // 公式
                // 解析公式
                data = parseFormula(cell, formula);
                break;
            case Cell.CELL_TYPE_BLANK: // 空格
                System.out.println("遇到一个空格");
                data = null;
                break;
            case Cell.CELL_TYPE_ERROR:// 错误
                System.out.println("遇到一个错误");
                data = null;
                break;
            default:
                data = null;
        }
        return data;
    }

    /** 解析公式
     *
     * @param cell - 单元格
     * @param formula - 是否计算公式结果
     * @return String - 结果
     */
    private static String parseFormula(Cell cell, boolean formula) {
        String data = null;
        if (formula) {
            switch (cell.getCachedFormulaResultType()) {
                case 0:
                    if (0 == cell.getCellStyle().getDataFormat()) {
                        DecimalFormat df = new DecimalFormat("0");
                        data = df.format(cell.getNumericCellValue());
                    } else {
                        data = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                case 1:
                    data = String.valueOf(cell.getRichStringCellValue());
                    break;
                case 4:
                    data = String.valueOf(cell.getBooleanCellValue());
                    break;
                case 5:
                    data = String.valueOf(cell.getErrorCellValue());
                    break;
                default:
                    data = cell.getCellFormula();
            }
        } else {
            data = cell.getCellFormula();
        }
        return data;
    }

    /**
     * 判断数值类型自动解析日期格式等其他特殊类型
     *
     * @param cell - 单元格
     * @param sdf - 日期格式
     * @return String - 结果
     */
    private static String parseDate(Cell cell, SimpleDateFormat sdf) {
        System.out.println("是否是有效的日期格式："+DateUtil.isCellDateFormatted(cell));
        //poi的日期判断仅适用于欧美日期格式，对中文日期不支持，另外增加两个方法判断中文格式日期
        if (DateUtil.isCellDateFormatted(cell)||isReserved(cell.getCellStyle().getDataFormat())||isDateFormat(cell.getCellStyle().getDataFormatString()))
        {
            return sdf.format(cell.getDateCellValue());
        }
        System.out.println("格式："+cell.getCellStyle().getDataFormatString()+"，类型"+cell.getCellStyle().getDataFormat());
        Double d=cell.getNumericCellValue();
        if(cell.getCellStyle().getDataFormat()==0)
        {
            DecimalFormat dfs = new DecimalFormat("0");
            return dfs.format(d);
        }
        return String.valueOf(d);

    }

    /**
     * 判断是否是中文日期格式
     * @param isNotDate
     * @return boolean<ul><li>true - 是日期格式</li><li>false - 不是</li></ul>
     */
    private static boolean isDateFormat(String isNotDate) {
        if(isNotDate.contains("年")||isNotDate.contains("月")||isNotDate.contains("日")) {
            return true;
        }else if(isNotDate.contains("aaa;")||isNotDate.contains("AM")||isNotDate.contains("PM")) {
            return true;
        }

        return false;
    }

    private static boolean isReserved(short reserv) {
        if(reserv>=27&&reserv<=31) {
            return true;
        }
        return false;
    }

    public static void createHSSFFile(String fileName){
        FileOutputStream fileOut=null;

        //1、创建新工作簿
        try (Workbook wb = new HSSFWorkbook()){
            //2、创建新 sheet 页
            Sheet sheet1 = wb.createSheet("first sheet");
            Sheet sheet2 = wb.createSheet("second sheet");

            CreationHelper createHelper = wb.getCreationHelper();
            //3、创建单元行
            Row headRow = sheet1.createRow(0);

            //4、创建行头
            List<String> lineData=new ArrayList<>();
            lineData.add("STU_ID");
            lineData.add("STU_NAME");
            lineData.add("STU_AGE");
            lineData.add("STU_GRADER");
            lineData.add("STU_TIME");
            createFirstLine(headRow,lineData);

            //写入数据
            Row dataRow =null;
            for(int i=1;i<10;i++){
                //创建一行
                dataRow=sheet1.createRow(i);

                //创建单元格并写入数据
                dataRow.createCell(0).setCellValue("STU_ID:"+i);
                dataRow.createCell(1).setCellValue("STU_NAME:"+i);
                dataRow.createCell(2).setCellValue(
                        createHelper.createRichTextString("STU_AGE:"+i));
                dataRow.createCell(3).setCellValue("STU_GRADER:"+i);

                CellStyle cellStyle = wb.createCellStyle();
                cellStyle.setDataFormat(
                        createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
                Cell cell = dataRow.createCell(4);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(new Date());
            }


            //将输出流写入一个文件
            fileOut= new FileOutputStream(fileName);
            wb.write(fileOut);

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(fileOut!=null){
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static Row createFirstLine(Row row, List<String> lineData){

        if(row!=null && lineData!=null){
            for(int i=0;i<lineData.size();i++){
                row.createCell(i).setCellValue(lineData.get(i));
            }
        }

        return row;
    }

    public static void createXSSFFile(){
        //1、创建新工作簿
        try (Workbook wb = new XSSFWorkbook();
             FileOutputStream fileOut = new FileOutputStream("C:\\Users\\lenovo\\Desktop\\poi\\workbook.xlsx")){

            wb.write(fileOut);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        String filePath="C:\\Users\\lenovo\\Desktop\\poi\\workbook.xls";
        ExcelUtil.createHSSFFile(filePath);
        List<Map> parse = ExcelUtil.parse(filePath, false, null);

        System.out.println(parse.size());
    }
}
