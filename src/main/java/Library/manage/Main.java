package Library.manage;

import Library.manage.Mapper.BookMapper;
import Library.manage.entity.Book;
import Library.manage.entity.Student;
import Library.manage.sql.SqlUtil;
import lombok.extern.java.Log;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import sun.util.logging.resources.logging;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.LogManager;

@Log
public class Main {
    public static void main(String[]argv) throws IOException {

            try(Scanner scanner=new Scanner(System.in)) {
                LogManager manager=LogManager.getLogManager();
                manager.readConfiguration(Resources.getResourceAsStream("logging.properties"));
                while (true) {
                    System.out.println("***************");
                    System.out.println("1:录入学生信息");
                    System.out.println("2:录入书籍信息");
                    System.out.println("3:查询所有学生");
                    System.out.println("4:查询所有书籍");
                    System.out.println("5:查阅借阅列表");
                    System.out.println("6:加入借阅信息");
                    System.out.println("输入任意键退出");
                    int input;
                    try {
                        input = scanner.nextInt();
                    } catch (Exception e) {
                        return;
                    }
                    scanner.nextLine();
                    switch (input) {
                        case 1:
                            addStudent(scanner);
                            break;
                        case 2:
                            addBook(scanner);
                            break;
                        case 3:
                            getAllStudent();
                            break;
                        case 4:
                            getAllBook();
                            break;
                        case 5:
                            getBorrowList();
                            break;
                        case 6:
                            addBorrow(scanner);
                            break;
                        default:
                            return;
                    }

                }
            }
    }
    public static void addStudent(Scanner scanner) {
         String name,sex;
         int grade;
        System.out.println("请输入学生姓名");
         name=scanner.nextLine();
        System.out.println("请输入学生性别");
         sex=scanner.nextLine();
        System.out.println("请输入学生年级");
         grade=Integer.parseInt(scanner.nextLine());

         Student student=new Student(name,sex,grade);
        SqlUtil.doSqlwork(bookMapper -> {
            int i=bookMapper.addStudent(student);
            if(i>0) {
                System.out.println("输入学生数据成功！");
                log.info("新加入一条学生信息" + student);
            }
            else System.out.println("输入学生数据失败！");
        });
    }
    public static void addBook(Scanner scanner) {
        String title,desc;
        double price;
        System.out.println("请输入书本标题");
        title=scanner.nextLine();
        System.out.println("请输入书本描述");
        desc=scanner.nextLine();
        System.out.println("请输入书本价格");
        price=Double.parseDouble(scanner.nextLine());

        Book b=new Book(title,desc,price);
        SqlUtil.doSqlwork(bookMapper -> {
            int i=bookMapper.addBook(b);
            if(i>0) {
                System.out.println("输入书籍数据成功！");
                log.info("新加入一条书籍信息" + b);
            }
            else System.out.println("输入书籍数据失败！");
        });
    }

    public static void addBorrow(Scanner scanner){
         int sid,bid;
        System.out.println("请输入学号");
        sid=Integer.parseInt(scanner.nextLine());
        System.out.println("请输入书籍号");
        bid=Integer.parseInt(scanner.nextLine());
        SqlUtil.doSqlwork(bookMapper -> {
           int i= bookMapper.addBorrow(sid,bid);
            if(i>0) {
                System.out.println("输入借阅数据成功！");
                log.info("新加入一条书籍信息:" +sid+bid);
            }
            else System.out.println("输入书籍数据失败!");
        });

    }
    public static void getAllStudent(){
        System.out.println("所有学生信息如下");
        SqlUtil.doSqlwork(bookMapper -> {
            bookMapper.getAllStudent().forEach((item)->{
                System.out.println(item);
            });
        });
    }

    public static void getAllBook(){
        System.out.println("所有书籍信息如下");
        SqlUtil.doSqlwork(bookMapper -> {
            bookMapper.getAllBook().forEach((item)->{
                System.out.println(item);
            });
        });
    }

    public static void getBorrowList(){
        System.out.println("所有借阅信息如下");
        System.out.println("借阅编号\t学号\t姓名\t书籍号\t书籍名");
        SqlUtil.doSqlwork(bookMapper -> {
            bookMapper.getBorrowList().forEach(System.out::println);
        });
    }

}

