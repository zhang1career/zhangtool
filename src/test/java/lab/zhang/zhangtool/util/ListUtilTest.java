package lab.zhang.zhangtool.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ListUtilTest {

    @Data
    @AllArgsConstructor
    static class Entity {
        private String name;
    }

    @Rule
    public ExpectedException ee = ExpectedException.none();

    private List<Entity> entityList;

    @Before
    public void setUp() {
        entityList = new ArrayList<>();
        entityList.add(new Entity("result"));
        entityList.add(new Entity("msg"));
        entityList.add(new Entity("code"));
        entityList.add(new Entity("data"));
        entityList.add(new Entity("data.amount"));
        entityList.add(new Entity("data.pin"));
        entityList.add(new Entity("data.class"));
    }

    @Test
    public void test_ordered() {
        Map<String, Entity> entityMap = ListUtil.indexById(entityList, Entity::getName);

        int i = 0;
        for (Map.Entry<String, Entity> entry : entityMap.entrySet()) {
            assertThat("The output order of indexById should be as same as the input order",
                    entry.getKey(),
                    equalTo(entityList.get(i).getName()));
            i++;
        }
    }

    @Test
    public void test_null_convertor() {
        ee.expect(IllegalArgumentException.class);
        ee.expectMessage("convertor should not be null");

        ListUtil.indexById(entityList, null);
    }
}