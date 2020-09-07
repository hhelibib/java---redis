package work;

import java.util.Map;

public class Task {
    private String tid;
    private String tname;
    private String tpath;
    private String tclass;
    private String tteacher;
    private String tdiscribe;


    @Override
    public String toString() {
        return tid + " " + tname + " " + tpath + " " + tclass + " " +tteacher +  " " + tdiscribe ;
    }

    public Task(Map map){
        this.tid = map.get("tid").toString();
        this.tname = map.get("tname").toString();
        this.tpath = map.get("tpath").toString();
        this.tclass = map.get("tclass").toString();
        this.tteacher = map.get("tteacher").toString();
        this.tdiscribe = map.get("tdiscribe").toString();

    }

    public Task() {
    }

    public Task(String tid, String tname, String tpath, String tclass, String tteacher, String tdiscribe) {
        this.tid = tid;
        this.tname = tname;
        this.tpath = tpath;
        this.tclass = tclass;
        this.tteacher = tteacher;
        this.tdiscribe = tdiscribe;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTpath() {
        return tpath;
    }

    public void setTpath(String tpath) {
        this.tpath = tpath;
    }

    public String getTclass() {
        return tclass;
    }

    public void setTclass(String tclass) {
        this.tclass = tclass;
    }

    public String getTteacher() {
        return tteacher;
    }

    public void setTteacher(String tteacher) {
        this.tteacher = tteacher;
    }

    public String getTdiscribe() {
        return tdiscribe;
    }

    public void setTdiscribe(String tdiscribe) {
        this.tdiscribe = tdiscribe;
    }
}
