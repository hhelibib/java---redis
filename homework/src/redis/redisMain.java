package redis;

import redis.clients.jedis.Jedis;
import redis.clients.util.RedisInputStream;
import work.*;

import java.io.*;
import java.util.*;

public class redisMain {
        //初始化redis
        public static void init(){
            Jedis jedisWrite = new Jedis("192.168.232.129",6379);
            Jedis jedisRead = new Jedis("192.168.232.129",6380);
            if (jedisRead.scard("port") == 0) {
                String path = "src/resource/init.txt";
                File file = new File(path);
                FileInputStream in;
                String str = "";
                BufferedReader bufferedReader;
                String[] values;
                Student student;
                Teacher teacher;
                Work work;
                Task task;

                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("文件初始化失败");
                    return;
                }
                try {
                    in = new FileInputStream(path);
                    //删除redis中存储的stu tea work
                    jedisWrite.flushAll();

                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF8"));
                        while ((str = bufferedReader.readLine()) != null) {
                            values = str.split(" ");
                            if (values[0].equals("stu")) {
                                student = new Student(values[1], values[2], values[3], values[4]);
                                insertStudent(student,jedisWrite);
                            } else if (values[0].equals("tea")) {
                                teacher = new Teacher(values[1], values[2], values[3]);
                                insertTeacher(teacher, jedisWrite);
                            } else if (values[0].equals("work")) {
                                work = new Work(values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9]);
                                insertWork(work, jedisWrite);
                            } else if (values[0].equals("task")){
                                task = new Task(values[1],values[2],values[3],values[4],values[5],values[6]);
                                insertTask(task,jedisWrite);
                            }
                        }
                        System.out.println("redis 初始化成功");

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            jedisRead.close();;
            jedisWrite.close();
        }

        //添加学生
        public static void insertStudent(Student student, Jedis jedisWrite){
            jedisWrite.hset("stu:"+student.getId(),"id",student.getId());
            jedisWrite.hset("stu:"+student.getId(),"name",student.getName());
            jedisWrite.hset("stu:"+student.getId(),"password",student.getPassword());
            jedisWrite.hset("stu:"+student.getId(),"stuclass",student.getStuclass());
            jedisWrite.sadd("stu",student.getId());
            jedisWrite.sadd("stuclass",student.getStuclass());
            jedisWrite.close();
        }

        //查询学生
        public static Student selectStudentById(String id, Jedis jedisRead){
            Student student = null;
            Map ans = jedisRead.hgetAll("stu:"+id);
            student = new Student(ans);
            jedisRead.close();
            return student;
        }

        //添加老师
        public  static void insertTeacher(Teacher teacher, Jedis jedisWrite){
            jedisWrite.hset("tea:"+teacher.getId(),"id",teacher.getId());
            jedisWrite.hset("tea:"+teacher.getId(),"name",teacher.getName());
            jedisWrite.hset("tea:"+teacher.getId(),"password",teacher.getPassword());
            jedisWrite.sadd("tea",teacher.getId());

        }

        //查询老师
        public static Teacher selectTeacherById(String id,Jedis jedisRead){
            Teacher teacher = null;
            Map ans = jedisRead.hgetAll("tea:"+id);
            teacher = new Teacher(ans);
            return teacher;
        }

        //添加作业
        public static void insertWork(Work work, Jedis jedisWrite){
            jedisWrite.hset("work:"+work.getId(),"id",work.getId());
            jedisWrite.hset("work:"+work.getId(),"fileName",work.getFileName());
            jedisWrite.hset("work:"+work.getId(),"filePath",work.getFilePath());
            jedisWrite.hset("work:"+work.getId(),"fileSize",work.getFileSize());
            jedisWrite.hset("work:"+work.getId(),"fileOwner",work.getFileOwner());
            jedisWrite.hset("work:"+work.getId(),"status",work.getStatus());
            jedisWrite.hset("work:"+work.getId(),"fileClass",work.getFileClass());
            jedisWrite.hset("work:"+work.getId(),"upDate",work.getUpDate());
            jedisWrite.hset("work:"+work.getId(),"workName",work.getWorkName());
            jedisWrite.sadd("work",work.getId());
        }

        //查询作业
        public static  Work selectWork(String id, Jedis jedisRead){
            Work work = null;
            Map map = jedisRead.hgetAll("work:"+id);
            work = new Work(map);
            return work;
        }

        //返回所有的作业
        public static List selectAllWorks(Jedis jedisRead){
            List<Work> list = new ArrayList<>();
            int num = Math.toIntExact(jedisRead.scard("work"));
            List<String> workid = jedisRead.srandmember("work",num);
           Work work =null;
           for (int i = 0; i < num; i++ ){
               work = selectWork(workid.get(i),jedisRead);
               list.add(work);
//                System.out.println(list.get(i).toString());
           }
            return list;
        }

        //返回指定学生的所有作业
        public  static List selectStudentWorksById(String id, Jedis jedisRead){
        List<Work> list = selectAllWorks(jedisRead);
        String stuName = jedisRead.hget("stu:"+id, "name");
        System.out.println(stuName);
        List<Work> works =new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
//            System.out.println(list.get(i).toString());
            if (list.get(i).fileOwner.equals(stuName)){
                works.add(list.get(i));
            }
        }
        return works;
        }

        //添加任务
        public static void insertTask(Task task, Jedis jedisWrite){
            jedisWrite.hset("task:"+task.getTid(),"tid",task.getTid());
            jedisWrite.hset("task:"+task.getTid(),"tname",task.getTname());
            jedisWrite.hset("task:"+task.getTid(),"tpath",task.getTpath());
            jedisWrite.hset("task:"+task.getTid(),"tclass",task.getTclass());
            jedisWrite.hset("task:"+task.getTid(),"tteacher",task.getTteacher());
            jedisWrite.hset("task:"+task.getTid(),"tdiscribe",task.getTdiscribe());
            jedisWrite.sadd("task",task.getTid());
        }

        //查询任务
        public static Task selectTaskByTid(String tid, Jedis jedisRead){
            Task task = null;
            Map ans = jedisRead.hgetAll("task:"+tid);
            task = new Task(ans);

            return task;
        }

        //返回所有任务
        public static List selectAllTask(Jedis jedisRead){
            Set task = jedisRead.keys("task:*");
//            System.out.println("A"+task.size());
            List<Task> taskList = new ArrayList<>();
            Task task1  = null;
            Iterator<String> iterator = task.iterator();
            while (iterator.hasNext()){
                String taskid = iterator.next();
                taskid = taskid.substring(5);
                task1 = selectTaskByTid(taskid,jedisRead);
                taskList.add(task1);
            }
            return taskList;
        }

        //返回所有的班级
        public static List selectAllStuClass(Jedis jedisRead){
            List<String> stuClassList =jedisRead.srandmember("stuclass", Math.toIntExact(jedisRead.scard("stuclass")));
            return stuClassList;
        }

}
