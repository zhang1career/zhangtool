package lab.zhang.zhangtool.table.excel;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangrj
 */
@Data
public class Table {
    private TableTypeEnum type;
    private Class<?> clazz;

    private int titleRowIndex;
    private int headRowIndex;
    private int startRowIndex;
    private int stopRowIndex;

    private Map<String, String> headMap = new HashMap<>();

    public Table() {
        this.titleRowIndex = -1;
    }

    public boolean isEmpty() {
        return titleRowIndex < 0;
    }
}
