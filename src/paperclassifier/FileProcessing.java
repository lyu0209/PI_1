/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paperclassifier;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author kyk
 */
public class FileProcessing {

    private String paperFile;           // paperFile是待处理的论文清单
    private String addressFile;         // addressFile是用来筛选的地址清单
    private String paperStyle;          // paperStyle是论文类型
    
    public FileProcessing(String str1, String str2, String str3) {
        paperFile = str1;
        addressFile = str2;
        paperStyle = str3;
    }

    void paperProcessing() {
        // 定义有用地址列表、无用地址列表和不能确定地址列表
        List<String> usefulAddress = new ArrayList<>();
        List<String> uselessAddress = new ArrayList<>();
        List<String> undeterminedAddress = new ArrayList<>();
        
        /*
        首先打开地址清单文件，将有用的地址（useful）、没用的地址（useless）和不能确定的地址（undetermined）分别读入到列表中
        usefulAddress列表中保存有用的地址
        uselessAddress列表中保存无用的地址
        undeterminedAddress列表中保存不能确定的地址
        关闭地址清单文件
        */ 
        try {
            // 加载Address清单文件
            HSSFWorkbook wbAddress = new HSSFWorkbook(new FileInputStream(addressFile));
            HSSFSheet shUsefulAddress = wbAddress.getSheet("useful");            
            HSSFSheet shUselessAddress = wbAddress.getSheet("useless");
            HSSFSheet shUndeterminedAddress = wbAddress.getSheet("undetermined");
            
            // 将useful地址存放到usefulAddress列表中
            int phyRowNum = shUsefulAddress.getPhysicalNumberOfRows();          // 读出有用地址清单（useful）中的行数
            // 逐个读取每行的地址（都在第2列），并加入usefulAddress中
            for(int i=1;i<phyRowNum;i++){
                String address = shUsefulAddress.getRow(i).getCell(1).getStringCellValue();
                usefulAddress.add(address);
            }
            
            // 将useless地址存放到uselessAddress列表中
            phyRowNum = shUselessAddress.getPhysicalNumberOfRows();             // 读出无用的浙大地址清单（useless）中的行数
            // 逐个读取每行的地址（都在第2列），并加入uselessAddress中
            for(int i=1;i<phyRowNum;i++){
                String address = shUselessAddress.getRow(i).getCell(1).getStringCellValue();
                uselessAddress.add(address);
            }
            
            // 将undetermined地址存放到undeterminedAddress列表中
            phyRowNum = shUndeterminedAddress.getPhysicalNumberOfRows();             // 读出不能确定的地址清单（undetermined）中的行数
            // 逐个读取每行的地址（都在第2列），并加入undeterminedAddress中
            for(int i=1;i<phyRowNum;i++){
                String address = shUndeterminedAddress.getRow(i).getCell(1).getStringCellValue();
                undeterminedAddress.add(address);
            }            
        }catch (Exception e) {
	// TODO Auto-generated catch block
            e.printStackTrace();
	}
        
        /*
        接下来处理论文清单，需要将每条论文记录中的所有作者地址和通讯地址读出
        首先判断是SCI论文还是EI论文
        如果是SCI论文，则逐行搜索所有作者地址（C1）和通讯作者（RP），并将其保存到字符串数组
        如果是EI论文 。。。
        */
        // 加载清单文件的Sheet1
        try{
            // 打开待处理的论文清单文件(excel)，加载指定的sheet
            HSSFWorkbook wbPaper = new HSSFWorkbook(new FileInputStream(paperFile));
            HSSFSheet shPaper = wbPaper.getSheet("Sheet1");
            // 把相关列的数据读入数组中
            switch(paperStyle){
                // 对于SCI清单，找到"C1"项和"RP"项
                case "SCI":{
                    int coloumNum = shPaper.getRow(0).getPhysicalNumberOfCells();         //获得SCI清单第一行的总列数
                    int coloumC1 = 0;         // C1所在的列   
                    int coloumRP = 0;           // RP所在的列
                    
                    for(int i=0;i<coloumNum;i++){
                        switch(shPaper.getRow(0).getCell(i).getCellType()){
                            case HSSFCell.CELL_TYPE_STRING: // 字符串
                                String item = shPaper.getRow(0).getCell(i).getStringCellValue();
                                if(item.equals("C1")){
                                    coloumC1 = i;
                                    break;
                                }
                                if(item.equals("RP")){
                                    coloumRP = i;
                                    break;                        
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    
                }
                case "EI": {
                }
                default:
                    System.out.println("Wrong paper style！"); 
            }
        }catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
            e.printStackTrace();
	} catch (IOException e) {
	// TODO Auto-generated catch block
            e.printStackTrace();
	}        
    }
    
    
    
}
