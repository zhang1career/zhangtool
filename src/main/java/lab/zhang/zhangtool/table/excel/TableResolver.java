package lab.zhang.zhangtool.table.excel;

import cn.hutool.core.util.StrUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TableResolver {
    /**
     * Integer.MAX_VALUE is 2147483647, which is the max row index supported by excel
     */
    static private final int EXCEL_MAX_ROW_INDEX = Integer.MAX_VALUE;


    @Resource
    TablesConfig tablesConfig;


    public Table resolve(Sheet sheet, String title) throws ClassNotFoundException {
        Table table = new Table();

        int titleRowIndex = searchTitle(sheet, title);
        if (titleRowIndex < 0) {
            return table;
        }

        TableTypeEnum type = getTypeConfig(tablesConfig, title);
        switch (type) {
            case INFO:
                table.setType(type);
                table.setClazz(Class.forName(getClassConfig(tablesConfig, title)));
                table.setTitleRowIndex(titleRowIndex);
                table.setHeadRowIndex(titleRowIndex + 1);
                table.setStartRowIndex(titleRowIndex + 1);
                table.setStopRowIndex(searchEmptyRow(sheet, titleRowIndex) - 1);
                table.setHeadMap(getHeadConfig(tablesConfig, title));
                break;
            case DATA:
                table.setType(type);
                table.setClazz(Class.forName(getClassConfig(tablesConfig, title)));
                table.setTitleRowIndex(titleRowIndex);
                table.setHeadRowIndex(titleRowIndex + 1);
                table.setStartRowIndex(titleRowIndex + 2);
                table.setStopRowIndex(searchEmptyRow(sheet, titleRowIndex) - 1);
                table.setHeadMap(getHeadConfig(tablesConfig, title));
                break;
            default:
                break;
        }

        return table;
    }

    private int searchTitle(Sheet sheet, String title) {
        for (int i = 0; i < EXCEL_MAX_ROW_INDEX; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            if (title.equals(row.getCell(0).getStringCellValue())) {
                return i;
            }
        }
        return -1;
    }

    private int searchEmptyRow(Sheet sheet, int startRowIndex) {
        for (int i = startRowIndex; i < EXCEL_MAX_ROW_INDEX; i++) {
            Row row = sheet.getRow(i);
            if (row == null || row.getCell(0).getStringCellValue().isEmpty()) {
                return i;
            }
        }
        return EXCEL_MAX_ROW_INDEX;
    }

    private TableTypeEnum getTypeConfig(TablesConfig tablesConfig, String title) {
        TablesConfig.TableConfig tableConfig = getTableConfig(tablesConfig, title);
        if (tableConfig == null) {
            return TableTypeEnum.DATA;
        }
        String type = tableConfig.getType();
        if (StrUtil.isEmpty(type)) {
            return TableTypeEnum.DATA;
        }
        return TableTypeEnum.valueOf(type.trim().toUpperCase());
    }

    private String getClassConfig(TablesConfig tablesConfig, String title) {
        TablesConfig.TableConfig tableConfig = getTableConfig(tablesConfig, title);
        if (tableConfig == null) {
            return "";
        }
        return tableConfig.getClazz();
    }

    private Map<String, String> getHeadConfig(TablesConfig tablesConfig, String title) {
        Map<String, String> ret = new HashMap<>();

        TablesConfig.TableConfig tableConfig = getTableConfig(tablesConfig, title);
        if (tableConfig == null) {
            return ret;
        }

        List<TablesConfig.MappingConfig> headList = tableConfig.getHead();
        for (TablesConfig.MappingConfig config : headList) {
            ret.put(config.getKey(), config.getVal());
        }

        return ret;
    }

    private TablesConfig.TableConfig getTableConfig(TablesConfig tablesConfig, String title) {
        String tableIndex = "";
        for (TablesConfig.MappingConfig content : tablesConfig.getContents()) {
            if (title.equals(content.getKey())) {
                tableIndex = content.getVal();
            }
        }

        if (tableIndex.isEmpty()) {
            return null;
        }
        return tablesConfig.getTables().get(tableIndex);
    }


}
