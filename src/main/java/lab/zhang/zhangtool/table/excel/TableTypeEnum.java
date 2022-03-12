package lab.zhang.zhangtool.table.excel;

/**
 * @author zhangrj
 */
public enum TableTypeEnum {
    UNDEFINED("undefined type"),
    DATA("data in a row"),
    INFO("map with col[0] and col[1]"),
    ;

    private String desc;

    TableTypeEnum(String desc) {
        this.desc = desc;
    }
}
