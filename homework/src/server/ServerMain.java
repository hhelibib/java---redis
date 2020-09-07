package server;

import redis.clients.jedis.Jedis;
import redis.redisMain;
import work.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.List;

public class ServerMain{
        public static int connecteNum = 1;
        public static void main(String[] args) {
            PrintStream mytxt;
            try{
                File file = new File("src/resource/ServerLog.txt");
                if (!file.exists()){
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                mytxt = new PrintStream("src/resource/ServerLog.txt");
                PrintStream out=System.out;
                System.setOut(mytxt);
                System.out.println("文档执行的日期是："+new Date());
                System.setOut(out);
                System.out.println("日期保存完毕。");
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }

            ServerSocket server = null;
            Socket socket = null;
            DataInputStream in = null;
            DataOutputStream out = null;
            Jedis jedisWrite = new Jedis("192.168.232.129",6379);

                try {
                    server = new ServerSocket(9001);
                    System.out.println("创建成功！");
                    jedisWrite.flushAll();
                    redisMain.init();

                } catch (Exception e) {
                    System.out.println("创建失败！2");
                }
            while (connecteNum<300) {
                try {
                    System.out.println("等待第"+connecteNum+"个客户端连接");
                    socket = server.accept();
                    new Thread(new ServerSlave(socket)).start();
                    System.out.println("第" + connecteNum + "连接成功！");
                    connecteNum++;

                } catch (Exception e) {
                    System.out.println("断开连接！");
                    connecteNum--;
                }
            }
            System.out.println("已关闭服务器");

        }

}
