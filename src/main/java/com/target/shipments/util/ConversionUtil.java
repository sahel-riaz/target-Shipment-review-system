package com.target.shipments.util;



import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

public class ConversionUtil {

    public static File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(multipartFile.getBytes());
        }
        return file;
    }
}
