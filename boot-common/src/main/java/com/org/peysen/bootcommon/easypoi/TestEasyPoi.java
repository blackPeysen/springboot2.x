package com.org.peysen.bootcommon.easypoi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author Peysen
 * @Date 2020/11/22 15:45
 * @Desc TODO
 */
public class TestEasyPoi {

    private List<User> getUsers(){
        long a = System.currentTimeMillis();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setId((i+1));
            user.setName("peysen-" +i);
            user.setAge(13+i);
            user.setBir(new Date());
            user.setDesc("用户信息-"+i);

            if (i % 2 == 0){
                user.setStatus(0);
            } else{
                user.setStatus(1);
            }
            users.add(user);
        }
        System.out.println("获取耗时：" + (System.currentTimeMillis() - a) + "ms");
        return users;
    }

    private List<User> getBigUsers(){
        long a = System.currentTimeMillis();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 200000; i++) {
            User user = new User();
            user.setId((i+1));
            user.setName("peysen-" +i);
            user.setAge(13+i);
            user.setBir(new Date());
            user.setDesc("用户信息-"+i);

            if (i % 2 == 0){
                user.setStatus(0);
            } else{
                user.setStatus(1);
            }
            users.add(user);
        }
        System.out.println("获取耗时：" + (System.currentTimeMillis() - a) + "ms");
        return users;
    }

    @Test
    public void testExportData() throws IOException {
        List<User> users = getUsers();

        long a = System.currentTimeMillis();
        Workbook sheets = ExcelExportUtil.exportBigExcel(new ExportParams(), User.class, users);
        FileOutputStream outputStream = new FileOutputStream("/Users/peysen/Desktop/easyPoi.xlsx");
        sheets.write(outputStream);
        outputStream.close();
        sheets.close();

        System.out.println("写入耗时：" + (System.currentTimeMillis() - a) + "ms");
    }

    @Test
    public void bigDataExport() throws Exception {
        List<User> users = getBigUsers();

        Workbook workbook = null;
        int size = users.size();
        int page = (size % 10000 == 0) ? size / 10000 : size / 10000 + 1;
        long a = System.currentTimeMillis();
        ExportParams params = new ExportParams("大数据测试", "测试");
        for (int i = 0; i < page; i++) {
            List<User> subList = null;
            if (i != page){
                subList = users.subList(i * 10000, (i + 1) * 10000);
            }

            workbook = ExcelExportUtil.exportBigExcel(params, User.class, subList);
        }
        ExcelExportUtil.closeExportBigExcel();
        FileOutputStream fos = new FileOutputStream("/Users/peysen/Desktop/bigEasyPoi.xlsx");
        workbook.write(fos);
        fos.close();

        System.out.println("写入耗时：" + (System.currentTimeMillis() - a) + "ms");
    }
}
