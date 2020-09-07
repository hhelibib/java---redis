package UI;

import work.Task;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class TeaMain extends JFrame {

    private JPanel contentPane;
    private JTextField JTFP;
    private JTextField JTN;
    private JComboBox CC;
    //访问信号量
    private int status = 0;
    //班级列表
    private ArrayList<String> stuClass = new ArrayList<String>();
    //需要发布的作业
    private Task task = new Task();
    //执行 信号量
    private int single = 1;

    public int getSingle() {
        return single;
    }

    public void setSingle(int single) {
        this.single = single;
    }

    public JComboBox getCC() {
        return CC;
    }

    public void setCC(JComboBox CC) {
        this.CC = CC;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<String> getStuClass() {
        return stuClass;
    }

    public void setStuClass(ArrayList<String> stuClass) {
        this.stuClass = stuClass;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TeaMain frame = new TeaMain();
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
    public TeaMain() {
        setTitle("\u8BBE\u7F6E\u4F5C\u4E1A");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 529, 416);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("设置作业保存文件夹");
        label.setBounds(10, 25, 133, 15);
        contentPane.add(label);

        //学生上传作业保存的文件夹名称
        JTFP = new JTextField();
        JTFP.setBounds(10, 50, 197, 21);
        contentPane.add(JTFP);
        JTFP.setColumns(10);

        JLabel label_1 = new JLabel("消息通知");
        label_1.setBounds(10, 127, 73, 15);
        contentPane.add(label_1);

        //作业描述 JTT
        JTextPane JTT = new JTextPane();
        JTT.setText("\u8F93\u5165\u9700\u8981\u901A\u77E5\u7684\u4FE1\u606F\uFF0C\u6216\u662F\u8F93\u5165\u4F5C\u4E1A\u63CF\u8FF0");
        JTT.setBounds(10, 152, 197, 161);
        contentPane.add(JTT);

        CC = new JComboBox();
        CC.setModel(new DefaultComboBoxModel(new String[] {"选择班级", "电商1801", "电商1802"}));
        CC.setBounds(354, 82, 100, 21);
        contentPane.add(CC);

        JButton BC = new JButton("清空");
        BC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //清空
                JTT.setText("");
                JTFP.setText("");
                JTN.setText("");
            }
        });
        BC.setBounds(114, 323, 93, 23);
        contentPane.add(BC);

        JButton BOK = new JButton("发布");
        BOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //发送
                String taskTip = JTT.getText();
                String taskPath = JTFP.getText();
                String taskName = JTN.getText();
                String taskStuClass = CC.getSelectedItem().toString();
                taskPath = "src/resource/file/" + taskPath;
                task = new Task("",taskName,taskPath,taskStuClass,"",taskTip);
                status = 1;
                JTT.setText("");
                JTFP.setText("");
                JTN.setText("");
                JOptionPane.showConfirmDialog(null,"发布成功！");
            }
        });
        BOK.setBounds(275, 323, 93, 23);
        contentPane.add(BOK);



        JLabel label_2 = new JLabel("选择班级");
        label_2.setBounds(282, 85, 73, 15);
        contentPane.add(label_2);

        JLabel label_3 = new JLabel("作业名称");
        label_3.setBounds(282, 25, 73, 15);
        contentPane.add(label_3);

        JTN = new JTextField();
        JTN.setBounds(354, 22, 100, 21);
        contentPane.add(JTN);
        JTN.setColumns(10);
    }
}
