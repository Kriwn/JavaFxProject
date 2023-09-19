package cs211.project.models;

public class Activity {
    private String name;
    private String detail;

    private int id;

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getId(){
        return id;
    }
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {

        this.detail = detail;
    }

    public Activity(String name, String detail,int id) {
        this.name = name;
        this.detail = detail;
        this.id = id;
    }

    public boolean isName(String name){
        return  this.name.equals(name);
    }

    public boolean isId(int id){
        return  this.id == id;
    }

//    public  boolean isDetail(String detail){
//        return  this
//    }

}
