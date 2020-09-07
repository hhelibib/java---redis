package server;

import redis.clients.jedis.Jedis;
import redis.redisMain;
import work.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ServerSlave extends Thread implements Runnable {
    private Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ServerSlave(Socket socket) {
        super();
        this.socket = socket;
    }

    @Override
    public void run() {
        PrintStream mytxt = null;

        DataInputStream in = null;
        DataOutputStream out = null;
        String ans = new String();
        String[] args = new String[10];
        Jedis jedisWrite = new Jedis("192.168.232.129",6379);
        Jedis jedisRead = new Jedis("192.168.232.129",6380);
        PrintStream out1=System.out;
        List<Work> works = null;
        Work work = null;
        List<Task> tasks = null;
        Task task = null;
        String order = "";
        String[] orderTmp = null;
        List<String> stuClass = null;

        try{
//            works = redisMain.selectAllWorks(jedisRead);
            tasks = redisMain.selectAllTask(jedisRead);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            //验证登录
            String role = in.readUTF();
            String userid = in.readUTF();
            String password = in.readUTF();

            try{
                mytxt = new PrintStream("src/resource/log/" + role + userid + ".txt");
                System.setOut(mytxt);
                System.out.println("文档执行的日期是："+new Date());
                System.out.println("日期保存完毕。\n");
                System.setOut(out1);
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }

            System.out.println(role+userid+password);
            if (role.equals("stu")){
                Student student = redisMain.selectStudentById(userid,jedisRead);
                if (student.password.equals(password)){
                    out.writeUTF("1");
                    String taskInfo = "";
                    works = redisMain.selectAllWorks(jedisRead);
                    for (int i = 0; i < tasks.size(); i++){
                        if (tasks.get(i).getTclass().equals(student.stuclass)){
//                            System.out.println(tasks.size());
                            if (works.size() !=0){
                                for (int j = 0; j < works.size(); j++){
                                    if (works.get(j).workName.equals(tasks.get(i).getTname()) && works.get(j).status.equals("已提交")){
//                                        System.out.println(works.get(j).toString());
                                    } else {
                                        taskInfo += tasks.get(i).toString();
                                        taskInfo +=",";
                                    }
                                }
                            }else {
                                taskInfo += tasks.get(i).toString();
                                taskInfo +=",";
                            }


                        }
                    }
                    out.writeUTF(taskInfo);
                    order = in.readUTF();
                        while (!order.equals("exit")){
                            orderTmp = order.split(" ");
                            if (orderTmp[0].equals("save")){
                                System.setOut(mytxt);
                                System.setOut(out1);
                                //生成workid
                                int workNum = Math.toIntExact(jedisRead.scard("work"));
                                String workId = String.format("%07d",workNum+1);
                                work = new Work(workId,orderTmp[1],orderTmp[2],orderTmp[3],student.name,orderTmp[5],student.getStuclass(),orderTmp[7],orderTmp[8]);
                                redisMain.insertWork(work,jedisWrite);
                                order = "";
                            }
                            try{
                                order = in.readUTF();
                            }catch (IOException e){
                                System.out.println("客户端已断开连接");
                                ServerMain.connecteNum --;
                                return;
                            }
                        }
                }else {
                    out.writeUTF("0");
                }
            } else if (role.equals("tea")){
                Teacher teacher = redisMain.selectTeacherById(userid,jedisRead);
                if (teacher.password.equals(password)){
                    out.writeUTF("1");
                    stuClass = redisMain.selectAllStuClass(jedisRead);
                    String tmp = "";
                    for (int i = 0; i < stuClass.size(); i++){
                        tmp += stuClass.get(i)+" ";
                    }
                    out.writeUTF(tmp);
                    order = in.readUTF();
                    while (!order.equals("exit")){
                        orderTmp = order.split(" ");
                        //发布任务
                        if (orderTmp[0].equals("task")){
                            System.setOut(mytxt);
                            System.out.println(order+orderTmp.length);
                            System.setOut(out1);
                            int taskNum = Math.toIntExact(jedisRead.scard("task"));
                            String taskId = String.format("t%04d",taskNum+1);
                            task = new Task(taskId, orderTmp[2], orderTmp[3], orderTmp[4], teacher.getName(), orderTmp[6]);
                            redisMain.insertTask(task,jedisWrite);
                            order = "";
                        }
                        order = in.readUTF();
                    }
                }else {
                    out.writeUTF("0");
                }
            }
            socket.close();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("连接失败！");
        }
    }
}



