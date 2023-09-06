package Library.manage.Mapper;

import Library.manage.entity.Book;
import Library.manage.entity.Borrow;
import Library.manage.entity.Student;
import org.apache.ibatis.annotations.*;

import javax.annotation.Resource;
import java.util.List;

public interface BookMapper {

    @Insert("insert into student(name,sex,grade) values(#{name},#{sex},#{grade})")
    int addStudent(Student s);

    @Insert("insert into book(title,`desc`,price) values(#{title},#{desc},#{price})")
    int addBook(Book b);

    @Insert("insert into borrow(sid,bid) values(#{sid},#{bid})")
    int addBorrow(@Param("sid") int sid,@Param("bid") int bid);


    @Results({
            @Result(id = true, column = "sid", property = "sid"),
            @Result(column = "sex", property = "name"),
            @Result(column = "name", property = "sex"),
            @Result(column = "grade", property = "grade")
    })
    @Select("select * from student")
    List<Student> getAllStudent();

    @Results({
            @Result(id = true, column = "bid", property = "bid"),
            @Result(column = "title", property = "title"),
            @Result(column = "desc", property = "desc"),
            @Result(column = "price", property = "price")
    })
    @Select("select * from book")
    List<Book> getAllBook();

    @Results({
            @Result(column = "id",property ="id",id=true),
            @Result(column = "sid",property = "student", one =@One(select = "selectStudentBySid")),
            @Result(column = "bid",property = "book",one=@One(select = "selectBookByBid"))
    })
    @Select("select * from borrow")
    List<Borrow> getBorrowList();


    @Select("select * from student where sid=#{sid}")
    Student  selectStudentBySid(int sid);

    @Select("select * from book where bid=#{bid}")
    Book selectBookByBid(int bid);
}
