package lab.zhang.zhangtool.table.csv;

import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvRow;
import lab.zhang.zhangtool.MapUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangrj
 */
public class CsvUtil {

    static public <T> List<T> parseObject(CsvData csvData, Class<T> clazz) throws Exception {
        List<CsvRow> rows = csvData.getRows();

        List<T> objList = new ArrayList<>();
        for (CsvRow row : rows) {
            T obj = MapUtil.mapToObject(row.getFieldMap(), clazz);
            if (obj == null) {
                continue;
            }
            objList.add(obj);
        }
        return objList;
    }

    private CsvUtil() {
        throw new AssertionError();
    }
}
