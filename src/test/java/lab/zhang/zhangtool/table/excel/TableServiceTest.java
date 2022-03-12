package lab.zhang.zhangtool.table.excel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TableServiceTest {

    @Autowired
    TableService service;

    @Value("${excel.contents[0].key}")
    private String infoTable;

    @Value("${excel.contents[1].key}")
    private String scoreTable;

    private Resource excel;

    @Before
    public void setUp() {
        excel = new ClassPathResource("table/student_score.xlsx");
    }

    @Test
    public void test_resolve_info() throws Exception {
        Object info;
        Object score;

        info = service.resolveInfo(excel.getInputStream(), 0, infoTable);
        System.out.println(info);

        score = service.resolveData(excel.getInputStream(), 0, scoreTable);
        System.out.println(score);

        info = service.resolveInfo(excel.getInputStream(), 1, infoTable);
        System.out.println(info);

        score = service.resolveData(excel.getInputStream(), 1, scoreTable);
        System.out.println(score);
    }
}