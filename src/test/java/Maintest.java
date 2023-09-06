import Library.manage.sql.SqlUtil;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class Maintest {

    @Test
    void test1(){
        int sid,bid;
        Scanner scanner=new Scanner(System.in);
        SqlUtil.doSqlwork(bookMapper -> {
            bookMapper.getBorrowList().forEach(System.out::println);
        });
    }
    @Test
    void test2(){
        int sid,bid;
        Scanner scanner=new Scanner(System.in);
        SqlUtil.doSqlwork(bookMapper -> {
            bookMapper.getBorrowList().forEach(System.out::println);
        });
    }
}
