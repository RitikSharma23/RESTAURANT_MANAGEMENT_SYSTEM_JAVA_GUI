package Usersinfo;
import java.io.Serializable;

public class Users implements Serializable{
    int id;
    String name;
    String type;
    String phone;
    String password;

    public Users(){}

    public Users(String name,String type,String phone,String password){
        this.name=name;
        this.type=type;
        this.phone=phone;
        this.password=password;
    }

    public Users(int id,String name,String type,String phone,String password){
        this.id=id;
        this.name=name;
        this.type=type;
        this.phone=phone;
        this.password=password;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
