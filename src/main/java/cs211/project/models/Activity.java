package cs211.project.models;

public class Activity {
    private String name;
    private String detail;

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {

        this.detail = detail;
    }

    public Activity(String name, String detail) {
        this.name = name;
        this.detail = detail;
    }

    public boolean isName(String name){
        return  this.name.equals(name);
    }

}
