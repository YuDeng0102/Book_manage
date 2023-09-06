package Library.manage.entity;

import lombok.Data;

@Data
public class Borrow {
    int id;
    Student student;
    Book book;
    public String toString(){
         return ""+id+"\t"+student.getSid()+"\t"+student.getName()+"\t"+book.getBid()+"\t"+book.getTitle();
    }
}
