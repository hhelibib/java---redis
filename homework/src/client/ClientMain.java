package client;


import UI.TeaMain;
import UI.login;
import UI.upload;
import server.ServerMain;
import work.Task;
import work.Work;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ClientMain {

        public  Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ClientMain(Socket socket) {
        super();
        this.socket = socket;

    }

    public static void main(String[] args){

//        Socket socket = this.socket;
        Socket socket =null;
        DataInputStream in;
        DataOutputStream out;
        Scanner input = new Scanner(System.in) ;
        List<Work> works = null;
        List<Task> tasks = null;
        Work work = null;
//        Student student ;


        try {

            socket = new Socket("127.0.0.1",9001);
//            socket = new Socket("")
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            System.out.println("server：连接服务器成功");
            String ans = new String();

            System.out.println(socket.getPort());
            try{
                login login1 = new login();
                login1.setVisible(true);
                upload uploadView = new upload();
                TeaMain teaMain = new TeaMain();
                String userid = "";
                String password = "";
                String role = "";
                while (login1.getStatus() == 0){
                    //一直等待
                    Thread.sleep(1500);
                    System.out.println("等待登录事件发生");
                }

                userid = login1.getUserid();
                password = login1.getPassword();
                role = login1.getRole();

                System.out.println(role+userid+password);
//                login1.setVisible(false);
                out.writeUTF(role);
                out.writeUTF(userid);
                out.writeUTF(password);
                if (role.equals("stu")){
                    if (in.readUTF().equals("1")){
                        System.out.println("登录成功");
                        login1.setVisible(false);
                        uploadView.setVisible(true);
                        //将作业列表添加到下拉框中
                        String tmp = in.readUTF();

                        String[] list = tmp.split(",");
                        String[] listtmp = null;

                        for (int i = 0; i<list.length; i++){
                            System.out.println(list[i].toString());
                            listtmp = list[i].split(" ");
                            uploadView.workList.add(listtmp[1]);
                            uploadView.workPath.add(listtmp[2]);
                            uploadView.wordDiscribe.add(listtmp[5]);
                        }
                        uploadView.L1.setModel(new DefaultComboBoxModel(uploadView.workList.toArray()));
                        uploadView.textPane.setText(uploadView.wordDiscribe.get(0));
                    }else {
                        System.out.println("登录失败");
                        JOptionPane.showConfirmDialog(null,"用户名或密码错误");

                    }
                    //学生端
                    if (role.equals("stu")) {
                        //等待文件上传成功   这里会一直循环
                        while (uploadView.getSingle() == 1){
                            while (uploadView.status == 0){
                                Thread.sleep(3000);
//                                synchronized ()
                                System.out.println("等待文件上传成功");
                            }
                            uploadView.status = 0;
                            //上传文件的id只能在服务器端生成
                            //最后一次退出不能向服务器发送消息
//                            System.out.println("120" + uploadView.work.toString());
                            out.writeUTF("save"+uploadView.toString());
                        }

                    }
                } else if (role.equals("tea")){
                        if (in.readUTF().equals("1")){
                            System.out.println("教师用户登录成功");
                            login1.setVisible(false);
                            teaMain.setVisible(true);
                        } else{
                            JOptionPane.showConfirmDialog(null,"用户名或密码错误");
                            System.out.println("教师用户登录失败");
                        }
                        //返回班级列表
                            String stuClassTmp = in.readUTF();
                            String[] classList = stuClassTmp.split(" ");
                            for (int i = 0; i < classList.length; i++){
                                teaMain.getStuClass().add(classList[i]);
                            }
                            teaMain.getCC().setModel(new DefaultComboBoxModel(classList));

                         while (teaMain.getSingle() == 1){
                             //等待教室发布作业任务
                             while (teaMain.getStatus() == 0){
                                 Thread.sleep(3000);
                                 System.out.println("等待教师发布任务！");
                             }
                             //处理任务信息 之后传给服务端
                             String taskInfo = teaMain.getTask().toString();
                             System.out.println(taskInfo);
                             out.writeUTF("task "+taskInfo);

                             teaMain.setStatus(0);
                         }
                }




            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e){
            System.out.println("断开连接！");
            ServerMain.connecteNum --;
        } finally {
            ServerMain.connecteNum --;
        }

    }




}