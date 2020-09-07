package work;

import java.util.Map;

public class Teacher {
    String id;
    public String name;
    public String password;




    public Teacher(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Teacher(Map map){
        this.id = (String) map.get("id");
        this.name = (String) map.get("name");
        this.password = (String) map.get("password");
    }

    public Teacher() {

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
}
