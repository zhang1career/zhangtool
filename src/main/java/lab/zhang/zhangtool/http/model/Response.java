package lab.zhang.zhangtool.http.model;

import lombok.Data;

/**
 * Http response model
 *
 * @author zhangrj
 */
@Data
public class Response {
    private int code;
    private String msg;
    private Object data;
}
