package Library.manage.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Book {
    int bid;
    String title,desc;
     double price;
     public Book(String title,String desc,double price){
         this.title=title;
         this.desc=desc;
         this.price=price;
     }
}
