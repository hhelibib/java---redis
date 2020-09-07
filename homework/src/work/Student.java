package work;

import java.util.Map;

public class Student {
    public String id;
    public String name;
    public String password;
    public String  stuclass;


    public Student(String id, String name, String password, String stuclass) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.stuclass = stuclass;
    }

    public Student(Map map) {
        this.id = (String) map.get("id");
        this.name = (String) map.get("name");
        this.password = (String) map.get("password");
        this.stuclass = (String) map.get("stuclass");
    }

    public Student() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStuclass() {
        return stuclass;
    }

    public void setStuclass(String stuclass) {
        this.stuclass = stuclass;
    }
}
