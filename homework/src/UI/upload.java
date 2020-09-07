package UI;

import work.Work;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class upload extends JFrame {


    private JPanel contentPane;
    private JTextField JT1;
    private String filepath;
    private int single = 1;
    public  int status = 0;
    public  JComboBox L1 = new JComboBox();
    public JTextPane textPane;
    public ArrayList<String> workList = new ArrayList<String>();
    public ArrayList<String> workPath = new ArrayList<String>();
    public ArrayList<String> wordDiscribe = new ArrayList<String>();
    public Work work = new Work();


    @Override
    public String toString() {
        return this.work.toString();
    }

    public int getSingle() {
        return single;
    }

    public void setSingle(int single) {
        this.single = single;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    upload frame = new upload();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public upload() {
        setTitle("\u63D0\u4EA4\u4F5C\u4E1A");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 533, 426);
        contentPane = new JPanel();
        contentPane.setMinimumSize(new Dimension(800, 600));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("\u9009\u62E9\u8981\u53D1\u9001\u7684\u6587\u4EF6");
        label.setBounds(10, 10, 133, 15);
        contentPane.add(label);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setBounds(10, 107, 463, 21);
        progressBar.setMaximum(100);
        progressBar.setValue(0);
        progressBar.setString("0%");
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.PINK);
        progressBar.setVisible(true);
        Dimension d = progressBar.getSize();
        Rectangle rect = new Rectangle(0, 0, d.width, d.height);
//        progressBar.set
        contentPane.add(progressBar);

        JLabel label_1 = new JLabel("\u4F20\u8F93\u8FDB\u5EA6\uFF1A");
        label_1.setBounds(10, 70, 73, 15);
        contentPane.add(label_1);

        JT1 = new JTextField();
        JT1.setBounds(10, 39, 288, 21);
        contentPane.add(JT1);
        JT1.setColumns(10);
        L1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0) {
                //要判断 下拉框中是否有值
                if (L1.getSelectedItem() != null){
                    String workName = L1.getSelectedItem().toString();
                    for (int i =0; i < workList.size(); i++){
                        if (workName.equals(workList.get(i))){
                            textPane.setText(wordDiscribe.get(i));
                            break;
                        }
                    }
                }

            }
        });

        L1.setModel(new DefaultComboBoxModel(new String[]{"选择提交的作业", "java"}));
        L1.setBounds(358, 7, 115, 21);
        contentPane.add(L1);


        JButton B1 = new JButton("\u9009\u62E9\u6587\u4EF6");
        B1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               //弹出文件选择框
                String path;
                JFileChooser jf = new JFileChooser();
                jf.setSelectedFile(new File("*.zip|*.doc|*.docx"));
                int value = jf.showSaveDialog(null);
                jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                jf.setFileHidingEnabled(false);
                if (value == JFileChooser.APPROVE_OPTION) {
                    File getPath = jf.getSelectedFile();
                    // TODO
                    System.out.println(getPath.getPath());
                    JT1.setText(getPath.getPath());

                } else {
                    // TODO
                }

            }
        });
        B1.setBounds(308, 38, 81, 23);
        contentPane.add(B1);

        JButton B2 = new JButton("\u63D0\u4EA4");
        B2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //上传文件的代码
//                status = 1;
                String filePath1 = JT1.getText();
                System.out.println(filePath1);
                //获取文件保存路径
                //文件保存的路径
                String workname = "";
                if (L1.getSelectedItem() != null){
                    workname = L1.getSelectedItem().toString();
                }else {
                    System.out.println("没有作业可以进行上传");
                    status = 1;
                    single = 0;
                    return;
                }
                System.out.println("157" + workname);

                System.out.println("文件保存路径："+workname);
                String savePath = "";
                for (int i =0; i < workList.size(); i++){
                    if (workname.equals(workList.get(i))){
                        savePath = workPath.get(i);
                        break;
                    }
                }

                savePath += "\\";
                savePath += workname;
                savePath += "\\";
                String tmp = filePath1.substring(filePath1.lastIndexOf("\\")+1);
                savePath +=tmp;
                File file1 = new File(filePath1);
                File saveFile = new File(savePath);

                if (!file1.exists()){
                    System.out.println("文件不存在，上传失败！");
                    return;
                }
                //判断文件夹是否存在 不存在则新建
                if (!saveFile.getParentFile().getParentFile().exists()){
                    saveFile.getParentFile().getParentFile().mkdirs();
//                    System.out.println("文件夹创建成功");
                }

                if (!saveFile.getParentFile().exists()){
                    saveFile.getParentFile().mkdirs();
                    System.out.println("文件夹创建成功");
                }
                if (!saveFile.exists()){
                    try {
                        saveFile.createNewFile();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

                //二进制读文件和写文件
                InputStream inputStream= null;
                OutputStream outputStream = null;
                try {
                    inputStream = new FileInputStream( JT1.getText());

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                try {
                    outputStream = new FileOutputStream(savePath);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

                int byteRead = -1;
                long fileSize=new File(filePath1).length();
                progressBar.setMaximum((int) fileSize);
                progressBar.setValue(1);

                float pre = 0;
                System.out.println("文件长度："+fileSize);
                System.out.println(progressBar.getMaximum());
                try {
                    byteRead = inputStream.read();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                while(byteRead != -1){
                    try {
                        outputStream.write(byteRead);
                        try {
                            byteRead = inputStream.read();
                            progressBar.setValue(progressBar.getValue()+1);

                            pre = (float)progressBar.getValue() / (float)progressBar.getMaximum();
                            pre = pre*100;
                            progressBar.setString(pre+"%");
                            progressBar.paintImmediately(rect);

                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                try {
                    inputStream.close();
                    outputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.out.println("文件上传成功！");
                //文件上传成功之后的操作
                //将作业从列表中移除
                for (int i =0; i < workList.size(); i++){
                    if (workname.equals(workList.get(i))){
                        workList.remove(L1.getSelectedItem().toString());
                        workPath.remove(i);
                        wordDiscribe.remove(i);
                        L1.removeItemAt(i);
                        break;
                    }
                }

                //将上传记录传给服务器
                Double filesize = 0d;
                String size = "";
                if (fileSize/1024 <= 0 ){
                    size = fileSize + "B";
                }else if (fileSize/1024/1024 <= 0 && fileSize / 1024 > 0){
                    filesize = (int)fileSize / 1024.0;
                    size = filesize + "KB";
                }else if (fileSize/1024/1024/1024 <= 0 && fileSize /1024/1024 > 0){
                    filesize = (int)fileSize / 1024.0 /1024.0;
                    size = filesize + "MB";
                }else {
                    filesize = (int)fileSize/1024.0/1024.0/1024.0;
                    size = filesize + "GB";
                }
                Date date = new Date();
                //时间格式化
                SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss" );
                System.out.println(sdf.format(date));
                work = new Work("", tmp, savePath, size, "", "已提交", "" ,sdf.format(date), workname);
                status = 1;
                }
        });
        B2.setBounds(407, 38, 66, 23);
        contentPane.add(B2);

        textPane = new JTextPane();
        textPane.setBounds(10, 156, 463, 190);
        contentPane.add(textPane);

    }
}