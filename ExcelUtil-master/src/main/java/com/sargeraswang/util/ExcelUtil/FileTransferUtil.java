package com.sargeraswang.util.ExcelUtil;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Collection;
import java.util.Map;


public class FileTransferUtil {

    private static int allnum = 0;

    /**
     * 文件迁移--本地  工作空间 sanya2
     * 12345与12301文件迁移
     */
    public static void tansferFile2(String excelFilePath,boolean isNeedJava){
      String fileSourceLocation = "D:/workspace/eclipse/sanya2/";
      String fileTargetLocation = "D:/transfer/target/file/";
      String classSourceLocation = "D:/myInstall/profession/tomcat7_1/wtpwebapps/";
      String classTargetLocation = "D:/transfer/target/class/";
        FileTransferUtil.tansferFileUtil(excelFilePath,classSourceLocation,classTargetLocation,fileSourceLocation,fileTargetLocation,isNeedJava,"sanya2");
  }

    /**
     * 文件迁移--本地  工作空间 sanya3
     * 12345与12301文件迁移
     */
    public static void tansferFile3(String excelFilePath,boolean isNeedJava){
        String fileSourceLocation = "D:/workspace/eclipse/sanya3/";
        String fileTargetLocation = "D:/transfer/target/file/";
        String classSourceLocation = "D:/myInstall/profession/tomcat7_2/wtpwebapps/";
        String classTargetLocation = "D:/transfer/target/class/";
        FileTransferUtil.tansferFileUtil(excelFilePath,classSourceLocation,classTargetLocation,fileSourceLocation,fileTargetLocation,isNeedJava,"sanya3");
    }

    /**
     * 文件迁移
     * 门户网站
     */
    public static void tansferFile_mh(String excelFilePath,boolean isNeedJava,String method){
        String fileSourceLocation = "D:/workspace/eclipse/sanya/";
        String fileTargetLocation = "D:/transfer/target/file/";
        String classSourceLocation = "D:/myInstall/profession/tomcat7/wtpwebapps/";
        String classTargetLocation = "D:/transfer/target/class/";
        FileTransferUtil.tansferFileUtil(excelFilePath,classSourceLocation,classTargetLocation,fileSourceLocation,fileTargetLocation,isNeedJava,method);
    }

    /**
     * 文件迁移
     * 门户网站
     */
    public static void tansferFile_haixin(String excelFilePath,boolean isNeedJava,String method){
        String fileSourceLocation = "D:/workspace/eclipse/sanya/";
        String fileTargetLocation = "D:/transfer/target/file/";
        String classSourceLocation = "D:/workspace/tomcat_all/tomcat7_origin/webapps";
        String classTargetLocation = "D:/transfer/target/class/";
        FileTransferUtil.tansferFileUtil(excelFilePath,classSourceLocation,classTargetLocation,fileSourceLocation,fileTargetLocation,isNeedJava,method);
    }

    /**
     * 文件迁移
     * 手机端，网页
     */
    public static void tansferFilePhonePage(String excelFilePath,boolean isNeedJava,String method){
        String classSourceLocation = "D:/workspace/tomcat_all/tomcat7_weixin/wtpwebapps/";
        String classTargetLocation = "D:/transfer/target/class/";
        FileTransferUtil.tansferFileUtil(excelFilePath,classSourceLocation,classTargetLocation,method);
    }

    /**
     * 文件迁移
     * 手机端，接口
     */
    public static void tansferFilePhoneInterface(String excelFilePath,boolean isNeedJava,String method) {
        String classSourceLocation = "D:/workspace/tomcat_all/tomcat7_weixin_interface/webapps/";
        String classTargetLocation = "D:/transfer/target/class/";
        FileTransferUtil.tansferFileUtil(excelFilePath, classSourceLocation, classTargetLocation,method);
    }

    /**
     * 文件迁移
     * 海南省网站
     */
    public static void tansferFileHNWeb(String excelFilePath,boolean isNeedJava,String method) {
        String classSourceLocation = "D:/workspace/tomcat_all/tomcat7_haikou/wtpwebapps/";
        String classTargetLocation = "D:/transfer/target/class/";
        FileTransferUtil.tansferFileUtil(excelFilePath, classSourceLocation, classTargetLocation,method);
    }

    /**
     * 文件迁移
     * 海南省12345
     */
    public static void transferFileHN12345Interface(String excelFilePath,boolean isNeedJava,String method) {
        String classSourceLocation = "D:/workspace/tomcat_all/tomcat7_hainan/webapps/";
        String classTargetLocation = "D:/transfer/target/class/";
        FileTransferUtil.tansferFileUtil(excelFilePath, classSourceLocation, classTargetLocation,"");
        //准备回退的
        String classSourceLocation2 = "D:/transfer/target/class/bk/20181116/";
        String classTargetLocation2 = "D:/transfer/target/class/returnBack/20181116/";
        FileTransferUtil.tansferFileUtil(excelFilePath, classSourceLocation2, classTargetLocation2,method);
    }

    /**
     * 文件迁移
     * 海口工单12345
     */
    public static void transferFilehk12345(String excelFilePath,boolean isNeedJava,String method) {
        String classSourceLocation = "D:/workspace/tomcat_all/tomcat7_haikou/wtpwebapps/";
        String classTargetLocation = "D:/transfer/target/class/";
        FileTransferUtil.tansferFileUtil(excelFilePath, classSourceLocation, classTargetLocation,method);
    }

    /**
     * 文件迁移
     * 海南工单12345
     */
    public static void transferFilehn12345(String excelFilePath,String classSourceLocation,String classTargetLocation) {

        FileTransferUtil.tansferFileUtil(excelFilePath, classSourceLocation, classTargetLocation,"");
    }

    /**
     * 文件迁移
     * 海口二期center项目
     */
    public static void transferFilehkCenter(String excelFilePath,boolean isNeedJava,String method) {
        String fileSourceLocation = "D:/workspace/eclipse/hainan1/";
        String fileTargetLocation = "D:/transfer/target/file/";
        FileTransferUtil.tansferFileUtilOnlyJava(excelFilePath, fileSourceLocation, fileTargetLocation,method);
    }

    /**
     * 只导出java文件
     * @param excelFilePath
     * @param fileSourceLocation
     * @param fileTargetLocation
     * @param method
     */
    private static void tansferFileUtilOnlyJava(String excelFilePath,String fileSourceLocation,String fileTargetLocation,String method){
        File f=new File(excelFilePath);
        InputStream inputStream= null;
        try {
            inputStream = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Excel不存在！");
        }

        ExcelLogs logs =new ExcelLogs();
        Collection<Map> importExcel = ExcelUtil.importExcel_cbr(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs , 0);

        String type = "",fileName="",filePath="",className="",classPath="";
        String realFilePath = "";
        String realClassPath = "";

        for(Map m : importExcel){
            type = (String) m.get("类别");
            fileName = (String) m.get("修改文件/表名");
            filePath = (String) m.get("文件路径");
            allnum ++;
            //Java文件迁移
            FileTransferUtil.fileTransfer(filePath,fileName,fileSourceLocation,fileTargetLocation,method);
        }
    }

    private static void tansferFileUtil(String excelFilePath,String classSourceLocation,String classTargetLocation,String fileSourceLocation,String fileTargetLocation,boolean isNeedJava,String method){
        File f=new File(excelFilePath);
        InputStream inputStream= null;
        try {
            inputStream = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Excel不存在！");
        }

        ExcelLogs logs =new ExcelLogs();
        Collection<Map> importExcel = ExcelUtil.importExcel_cbr(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs , 0);

        String type = "",fileName="",filePath="",className="",classPath="";
        String realFilePath = "";
        String realClassPath = "";


        for(Map m : importExcel){
            type = (String) m.get("类别");
            fileName = (String) m.get("修改文件/表名");
            filePath = (String) m.get("文件路径");
            className = (String) m.get("对应class文件");
            classPath = (String) m.get("对应class路径");

            allnum ++;

            if(isNeedJava){
                //Java文件迁移
                FileTransferUtil.fileTransfer(filePath,fileName,fileSourceLocation,fileTargetLocation,method);
            }

            //class文件迁移
            FileTransferUtil.fileTransfer(classPath,className,classSourceLocation,classTargetLocation,method);
        }
    }

    /**
     * 文件迁移
     * @param filePath
     * @param fileName
     * @param fileSourceLocation
     * @param fileTargetLocation
     */
  private static void fileTransfer(String filePath,String fileName,String fileSourceLocation,String fileTargetLocation,String method){
      File sourcefile = null;
      File targetfile = null;
      String realFilePath = "";

      //替换路径
      filePath = filePath.replaceAll("\\\\","/");


      if("sanya2".equals(method)){
          //针对本地文件夹 工作空间二
          realFilePath = filePath.replaceAll("workorder12345","sanya12345");
          realFilePath = realFilePath.replaceAll("workorder12301","sanya12301");
      }else if("sanya3".equals(method)){
          //针对本地文件夹 工作空间三
          realFilePath = filePath.replaceAll("workorder12345","12345");
          realFilePath = realFilePath.replaceAll("workorder12301","12301");
      }else if("HNGD".equals(method)){
          //针对本地文件夹 工作空间三
          realFilePath = filePath.replaceAll("workorder12345-hainan180","workorder12345");
          //realFilePath = filePath.replaceAll("workorder12345-hainan","workorder12345");
      }else{
          realFilePath = filePath;
      }



      //定位到工作空间
      sourcefile = new File(fileSourceLocation+realFilePath + System.getProperty("file.separator") + fileName);

      if(!sourcefile.exists()){
          System.out.println("原文件不存在：：："+fileSourceLocation+realFilePath+"请核查是否填写错误！"+fileName);
      }else{
          //开始进行迁移
          targetfile = new File(fileTargetLocation + filePath);
          //文件夹不存在，则创建文件夹
          if(!targetfile.exists()){
              targetfile.mkdirs();
          }
          targetfile = new File(fileTargetLocation + filePath + System.getProperty("file.separator") + fileName);
          //通过JVM读取java.io.tmpdir属性取得临时文件夹
          File targetDir = new File(System.getProperty("java.io.tmpdir"));
          try
          {
              //在同一个文件夹复制文件
              //System.out.println("复制 " + source + " 文件到 " + target);
              FileUtils.copyFile(sourcefile, targetfile);
              // 根据指定的文件夹复制
              //FileUtils.copyFileToDirectory(sourcefile, targetfile);(sourcefile, targetfile);
              System.out.println("复制第"+allnum + "个文件：" + fileName + " 成功！");
          } catch (IOException e)
          {
              System.out.println("文件"+filePath+"/"+fileName+"迁移失败,失败原因：="+e.getMessage());
          }
      }
  }

    private static void tansferFileUtil(String excelFilePath,String classSourceLocation,String classTargetLocation,String method){
        File f=new File(excelFilePath);
        InputStream inputStream= null;
        try {
            inputStream = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Excel不存在！");
        }

        ExcelLogs logs =new ExcelLogs();
        Collection<Map> importExcel = ExcelUtil.importExcel_cbr(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs , 0);

        String type = "",fileName="",filePath="",className="",classPath="";
        String realFilePath = "";
        String realClassPath = "";


        for(Map m : importExcel){
            className = (String) m.get("对应class文件");
            classPath = (String) m.get("对应class路径");
            allnum ++;
            //class文件迁移
            FileTransferUtil.fileTransfer(classPath,className,classSourceLocation,classTargetLocation,method);
        }
    }

}
