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
public class TestTransfer {

  /**
   * 单个文件迁移
   * @throws FileNotFoundException
   */
  @Test
  public void transferFile_single() throws FileNotFoundException {
    String excelFilePath = "D:/transfer/三亚工单迁移给职能局提示.xlsx";
    //工作空间3进行迁移
    FileTransferUtil.tansferFile3(excelFilePath,false);
  }

  /**
   * 多个文件迁移
   * @throws FileNotFoundException
   */
  @Test
  public void transferFile() throws FileNotFoundException {
    //String excelFilePath = "D:/transfer/12345区政府流程代码更新路径.xlsx";
    //FileTransferUtil.tansferFile(excelFilePath);

    String paths[] = {"D:/transfer/20180621/v1.xlsx"};

    for (int i=0;i<paths.length;i++) {
      System.out.println("文件迁移开始---模块版本号：V"+(i+1) +"---begin-----------");
      FileTransferUtil.tansferFile3(paths[i],false);
      System.out.println("文件迁移结束---模块版本号：V"+(i+1) +"---eng-----------");
    }

  }

  @Test
  public void transferFile_mh() throws FileNotFoundException {
    String excelFilePath = "D:/transfer/20180607/t1.xlsx";
    FileTransferUtil.tansferFile_mh(excelFilePath,false,"sanya2");
  }


  @Test
  public void transferFilePhonePage() throws FileNotFoundException {
    String excelFilePath = "D:/transfer/手机端路径代码 - 第三阶段.xlsx";
    FileTransferUtil.tansferFilePhonePage(excelFilePath,false,"sanya2");
  }

  @Test
  public void transferFilePhoneInterface() throws FileNotFoundException {
    String excelFilePath = "D:/transfer/手机端路径代码 - 第四阶段.xlsx";
    FileTransferUtil.tansferFilePhoneInterface(excelFilePath,false,"sanya2");
  }

  @Test
  public void transferFileHaixin() throws FileNotFoundException {
    String excelFilePath = "D:/transfer/海信项目代码路径6.xlsx";
    FileTransferUtil.tansferFile_haixin(excelFilePath,false,"");

/*
    String excelFilePath = "D:/transfer/海信项目代码路径-回访任务监控crm.xlsx";
    FileTransferUtil.tansferFile_haixin(excelFilePath,false,"");


    String excelFilePath2 = "D:/transfer/海信项目代码路径-数据同步crm.xlsx";
    FileTransferUtil.tansferFile_haixin(excelFilePath2,false,"");*/
/*
    String excelFilePath3 = "D:/transfer/海信项目代码路径-回访任务监控css.xlsx";
    FileTransferUtil.tansferFile_haixin(excelFilePath3,false,"");

    String excelFilePath4 = "D:/transfer/海信项目代码路径-分公司CSS.xlsx";
    FileTransferUtil.tansferFile_haixin(excelFilePath4,false,"");


*/
  }

  @Test
  public void transferFilehk12345() throws FileNotFoundException {
    String excelFilePath = "D:/transfer/海口工单.xlsx";
    FileTransferUtil.transferFilehk12345(excelFilePath,false,"other");
  }


  @Test
  public void transferFilehkCenter() throws FileNotFoundException {
    String excelFilePath = "D:/transfer/center项目.xlsx";
    FileTransferUtil.transferFilehkCenter(excelFilePath,false,"other");
  }



  @Test
  public void transferFileHNWebInterface() throws FileNotFoundException {
    String excelFilePath = "D:/transfer/海南省网站优化-提交工单过滤重复提交.xlsx";
//    String excelFilePath = "D:/transfer/海南省网站手机验证码.xlsx";
    FileTransferUtil.tansferFileHNWeb(excelFilePath,false,"other");
  }

  @Test
  public void transferFileHN12345Interface() throws FileNotFoundException {
    String excelFilePath = "D:/transfer/海南省12345工单六级分类开发.xlsx";

    FileTransferUtil.transferFileHN12345Interface(excelFilePath,false,"HNGD");
  }

  /**
   * 放在海口工作空间的--新方法
   * @throws FileNotFoundException
   */
  @Test
  public void transferFileHN12345Interface_New() throws FileNotFoundException {
    String excelFilePath = "D:/CBR/transfer/新版海南省12345工单测评上线.xlsx";
//    String excelFilePath = "D:/CBR/transfer/新版海南省12345工单V2.15上线内容.xlsx";

    //海南
    String classSourceLocation1 = "C:/myInstall/professional/tomcat/tomcat7_origin/wtpwebapps/";

    String classTargetLocation1 = "D:/CBR/transfer/target/class/";
    String classSourceLocation2 = "D:/CBR/代码备份目录/2019年7月/18日，省工单V2.14版本上线/bak/47";
    String classTargetLocation2 = "D:/CBR/transfer/target/class/returnBack/20190718/";
    FileTransferUtil.transferFilehn12345(excelFilePath,classSourceLocation1,classTargetLocation1);
    //回退
    //FileTransferUtil.transferFilehn12345(excelFilePath,classSourceLocation2,classTargetLocation2);
  }

}



