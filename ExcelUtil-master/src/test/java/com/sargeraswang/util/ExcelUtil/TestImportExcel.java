/**
 * @author SargerasWang
 */
package com.sargeraswang.util.ExcelUtil;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.*;
import java.util.Collection;
import java.util.Map;

/**
 * 测试导入Excel 97/2003
 */
public class TestImportExcel {

  @Test
  public void importXls() throws FileNotFoundException {
    File f=new File("src/test/resources/test.xls");
    InputStream inputStream= new FileInputStream(f);
    
    ExcelLogs logs =new ExcelLogs();
    Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs , 0);
    
    for(Map m : importExcel){
      System.out.println(m);
    }
  }

  @Test
  public void importXlsx() throws FileNotFoundException {
    File f=new File("src/test/resources/test.xlsx");
    InputStream inputStream= new FileInputStream(f);

    ExcelLogs logs =new ExcelLogs();
    Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs , 0);

    for(Map m : importExcel){
      System.out.println(m);
    }
  }



  @Test
  public void transferFile() throws FileNotFoundException {
   /* File f=new File("src/test/resources/transfer.xlsx");
    InputStream inputStream= new FileInputStream(f);

    ExcelLogs logs =new ExcelLogs();
    Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs , 0);

    for(Map m : importExcel){
      System.out.println(m);
    }*/

    String sourcePath="D:/transfer/source/12301/src/main/webapp/wp/jsp/deptUnitDispositionStatistiReportSkip.jsp";
    String targetPath="D:/transfer/target/workorder12301/src/main/webapp/wp/jsp/deptUnitDispositionStatistiReportSkip.jsp";
    File file = new File(targetPath);
    if(!file.exists()){
      file.mkdirs();
    }

    try {
      file.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }



    // 源File对象
    File source = new File(sourcePath);

    // 备份的File对象
    File target = new File(targetPath);

    //通过JVM读取java.io.tmpdir属性取得临时文件夹
    File targetDir = new File(System.getProperty("java.io.tmpdir"));

    try
    {
      //在同一个文件夹复制文件
      //System.out.println("复制 " + source + " 文件到 " + target);
      //FileUtils.copyFile(source, target);

      // 根据指定的文件夹复制
      System.out.println("复制 " + source + " 文件到" + targetDir + "目录");
      FileUtils.copyFileToDirectory(source, target);
    } catch (IOException e)
    {
      e.printStackTrace();
    }

    //FileUtils.copyFile(sourcePath,targetPath);


  }
/*
  // 判断文件是否存在
16     public static void judeFileExists(File file) {
    17
    18         if (file.exists()) {
      19             System.out.println("file exists");
      20         } else {
      21             System.out.println("file not exists, create it ...");
      22             try {
        23                 file.createNewFile();
        24             } catch (IOException e) {
        25                 // TODO Auto-generated catch block
        26                 e.printStackTrace();
        27             }
      28         }
    29
    30     }
31
        32     // 判断文件夹是否存在
        33     public static void judeDirExists(File file) {
    34
    35         if (file.exists()) {
      36             if (file.isDirectory()) {
        37                 System.out.println("dir exists");
        38             } else {
        39                 System.out.println("the same name file exists, can not create dir");
        40             }
      41         } else {
      42             System.out.println("dir not exists, create it ...");
      43             file.mkdir();
      44         }
    45
    46     }*/

}
