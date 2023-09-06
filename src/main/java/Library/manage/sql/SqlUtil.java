package Library.manage.sql;

import Library.manage.Mapper.BookMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.logging.FileHandler;

public class SqlUtil {
    private SqlUtil(){}
    private static SqlSessionFactory factory;
    static {
        try{
            factory=new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("Mybatis.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void doSqlwork(Consumer<BookMapper> consumer){
         try(SqlSession session=factory.openSession(true)){
             BookMapper mapper=session.getMapper(BookMapper.class);
             consumer.accept(mapper);
         }

    }


}
