/*
 * Remind users of several points for attention.
 */
package paperclassifier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


/**
 *
 * @author Yu Ling
 */
public class ReminderWindow extends JFrame{
    Container container1 = getContentPane();           //设置一个容器
    //private JPanel panel = new JPanel(new GridLayout(2,1));
    private JPanel p1 = new JPanel();
    private JPanel p2 = new JPanel();
   

    //JLabel l1 = new JLabel();
    JTextArea jt = new JTextArea(10, 50);
    JButton jb = new JButton("确定");    

    public ReminderWindow() {
        //从一个文本文件中读出提醒文字
        String str1 = "";
        try{
            File file = new File("resource/caution.txt");//定义一个file对象，用来初始化FileReader
            FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
            try (BufferedReader bReader = new BufferedReader(reader)) //new一个BufferedReader对象，将文件内容读取到缓存
            {
                StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
                String s = "";
                while ((s = bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
                    sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
                    System.out.println(s);
                } 
                str1 = sb.toString();
            } //定义一个字符串缓存，将字符串存放缓存中
        }catch (Exception e) {
	// TODO Auto-generated catch block
            e.printStackTrace();
	}
    
        container1.setLayout(new GridLayout(2,1));
        //l1.setText(str1); 
        jt.setLineWrap(true);// 如果内容过长。自动换行
        jt.setText(str1);
        //panel.add(l1);
        
        p1.add(jt);
        container1.add(p1);
        //p2.setLayout(null);
        
        jb.setPreferredSize(new Dimension(100,40));
        p2.add(jb);
               
        container1.add(p2);
        
        jb.addActionListener(new ButtonAction());
        
        this.setSize(600, 300);
        this.setLocation(150,75);
        this.setVisible(true);
        // 实现按掉右上角的×后整个程序自动退出
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public class ButtonAction implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            new FileSelector(); 
        }
    }
}
