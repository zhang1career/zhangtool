package lab.zhang.zhangtool.table.excel;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author zhangrj
 */
@Service
public class TableService {

    @Resource
    TableResolver tableResolver;

    public Object resolveInfo(InputStream file, int sheetIndex, String title) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ExcelReader reader = ExcelUtil.getReader(file, sheetIndex);
        Sheet sheet = reader.getSheet();
        Table table = tableResolver.resolve(sheet, title);

        Map<String, String> headMap = table.getHeadMap();
        Object obj = table.getClazz().getDeclaredConstructor().newInstance();
        DataFormatter formatter = new DataFormatter();
        for (int i = table.getStartRowIndex(); i <= table.getStopRowIndex(); i++) {
            Row row = sheet.getRow(i);
            String fieldDesc = row.getCell(0).getStringCellValue();
            if (!headMap.containsKey(fieldDesc)) {
                continue;
            }
            String fieldName = headMap.get(fieldDesc);
            if (fieldName.isEmpty()) {
                continue;
            }
            ReflectUtil.setFieldValue(obj, fieldName, formatter.formatCellValue(row.getCell(1)));
        }
        return obj;
    }

    public Object resolveData(InputStream file, int sheetIndex, String title) throws ClassNotFoundException {
        ExcelReader reader = ExcelUtil.getReader(file, sheetIndex);
        Sheet sheet = reader.getSheet();
        Table table = tableResolver.resolve(sheet, title);

        for (Map.Entry<String, String> entry : table.getHeadMap().entrySet()) {
            reader.addHeaderAlias(entry.getKey(), entry.getValue());
        }
        return reader.read(table.getHeadRowIndex(), table.getStartRowIndex(), table.getStopRowIndex(), table.getClazz());
    }
}
