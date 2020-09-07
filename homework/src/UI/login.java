package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login extends JFrame{
    private JPanel main;
    private JTextField Tname;
    private JPasswordField Tpassword;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private String userid;
    private String password;
    private String role;
    private int status = 0;
    public  final  Object obj = new Object();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public JPanel getMain() {
        return main;
    }

    public void setMain(JPanel main) {
        this.main = main;
    }

    public JTextField getTname() {
        return Tname;
    }

    public void setTname(JTextField tname) {
        Tname = tname;
    }

    public JPasswordField getTpassword() {
        return Tpassword;
    }

    public void setTpassword(JPasswordField tpassword) {
        Tpassword = tpassword;
    }

    public ButtonGroup getButtonGroup() {
        return buttonGroup;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    login frame = new login();
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
    public login() {
        setAutoRequestFocus(false);
        setTitle("\u767B\u5F55");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        main = new JPanel();
        main.setToolTipText("");
        main.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(main);
        main.setLayout(null);

        JLabel JL1 = new JLabel("\u7528\u6237\u540D\uFF1A");
        JL1.setBounds(new Rectangle(100, 53, 54, 15));
        main.add(JL1);

        Tname = new JTextField();
        Tname.setToolTipText("");
        Tname.setBounds(180, 50, 126, 21);
        main.add(Tname);
        Tname.setColumns(10);

        JLabel lblNewLabel = new JLabel("\u5BC6  \u7801\uFF1A");
        lblNewLabel.setBounds(100, 95, 54, 15);
        main.add(lblNewLabel);

        JLabel label = new JLabel("\u8EAB   \u4EFD\uFF1A");
        label.setBounds(100, 139, 54, 15);
        main.add(label);

        JRadioButton RB1 = new JRadioButton("\u5B66\u751F");
        buttonGroup.add(RB1);
        RB1.setBounds(175, 135, 61, 23);
        main.add(RB1);

        JRadioButton RB2 = new JRadioButton("\u6559\u5E08");
        buttonGroup.add(RB2);
        RB2.setBounds(252, 134, 54, 23);
        main.add(RB2);

        Tpassword = new JPasswordField();
        Tpassword.setBounds(180, 92, 126, 21);
        main.add(Tpassword);

        JButton button = new JButton("\u6CE8\u518C");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        button.setBounds(123, 180, 70, 23);
        main.add(button);

        JButton button_1 = new JButton("\u767B\u5F55");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userid = Tname.getText();
                password = new String(Tpassword.getPassword());

                if (RB1.isSelected()) {
                    role = "stu";
                } else if (RB2.isSelected()){
                    role = "tea";
                }
                status =1;

            }
        });
        button_1.setBounds(217, 180, 62, 23);
        main.add(button_1);

        JButton button_2 = new JButton("\u5FD8\u8BB0\u5BC6\u7801");
        button_2.setBounds(316, 91, 93, 23);
        main.add(button_2);
    }
}
