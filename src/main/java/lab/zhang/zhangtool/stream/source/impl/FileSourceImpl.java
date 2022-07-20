package lab.zhang.zhangtool.stream.source.impl;

import lab.zhang.zhangtool.stream.source.StreamSource;
import lab.zhang.zhangtool.util.ByteUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * @author zhangrj
 */
@Data
@AllArgsConstructor
public class FileSourceImpl implements StreamSource {

    private String filePath;

    @Override
    public Stream<Byte> getStream() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("file not found, filePath=" + filePath);
        }

        byte[] bytes = FileUtils.readFileToByteArray(file);
        return ByteUtil.stream(bytes);
    }
}
