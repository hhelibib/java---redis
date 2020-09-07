package work;

import java.util.Map;

public class Work {
    public String id;
    public String fileName;
    public String filePath;
    public String  fileSize;
    public String fileOwner;
    public String status;
    public String fileClass;
    public String upDate;
    public String workName;

    public Work(String id, String fileName, String filePath, String fileSize, String fileOwner, String status, String fileClass, String upDate,String workName) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.fileOwner = fileOwner;
        this.status = status;
        this.fileClass = fileClass;
        this.upDate = upDate;
        this.workName = workName;
    }

    public Work(Map map){
        this.id = (String) map.get("id");
        this.fileName = (String) map.get("fileName");
        this.filePath = (String) map.get("filePath");
        this.fileSize = (String) map.get("fileSize");
        this.fileOwner = (String) map.get("fileOwner");
        this.status = (String) map.get("status");
        this.fileClass = (String) map.get("fileClass");
        this.upDate = (String) map.get("update");
        this.workName = (String) map.get("workName");
    }

    public Work() {

    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileOwner() {
        return fileOwner;
    }

    public void setFileOwner(String fileOwner) {
        this.fileOwner = fileOwner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFileClass() {
        return fileClass;
    }

    public void setFileClass(String fileClass) {
        this.fileClass = fileClass;
    }

    public String getUpDate() {
        return upDate;
    }

    public void setUpDate(String upDate) {
        this.upDate = upDate;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    @Override
    public String toString() {
        return this.id + " " + this.fileName + " " + this.filePath + " " + this.fileSize + " " + this.fileOwner + " " + this.status + " " + this.fileClass + " " + this.upDate + " " + this.workName ;
    }


}
