package lab.zhang.zhangtool.table.excel;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix="excel")
@PropertySource(value="classpath:table/excel.properties", encoding="UTF-8")
public class TablesConfig {

    private List<MappingConfig> contents;

    private Map<String, TableConfig> tables;


    public List<MappingConfig> getContents() {
        if (contents == null) {
            contents = new ArrayList<>();
        }
        return contents;
    }

    public void setContents(List<MappingConfig> contents) {
        this.contents = new ArrayList<>(contents);
    }

    public Map<String, TableConfig> getTables() {
        if (tables == null) {
            tables = new HashMap<>();
        }
        return tables;
    }

    public void setTables(Map<String, TableConfig> tables) {
        this.tables = new HashMap<>(tables);
    }


    static public class TableConfig {
        private String type;
        private String clazz;
        private List<MappingConfig> head = new ArrayList<>();

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getClazz() {
            return clazz;
        }

        public void setClazz(String clazz) {
            this.clazz = clazz;
        }

        public List<MappingConfig> getHead() {
            if (head == null) {
                head = new ArrayList<>();
            }
            return head;
        }

        public void setHead(List<MappingConfig> head) {
            this.head = new ArrayList<>(head);
        }
    }


    static public class MappingConfig {
        private String key;
        private String val;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }
    }
}
