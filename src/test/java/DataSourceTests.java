import com.spring.RootConfig;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;

@RunWith(SpringJUnit4ClassRunner.class)
// xml = RootConfig
// xml 을 사용할 경우
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
// RootConfig를 사용할 경우
@ContextConfiguration(classes = RootConfig.class)
@Log4j
public class DataSourceTests {

    @Setter(onMethod_ = { @Autowired })
    private DataSource dataSource;

    @Test
    public void testConnection() {
        try (Connection con = dataSource.getConnection()){
            log.info(con);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    @Setter(onMethod_ = { @Autowired })
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testMyBatis() {
        try (SqlSession session = sqlSessionFactory.openSession();
             Connection con = session.getConnection();
        ) {
            log.info(session);
            log.info(con);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

}
