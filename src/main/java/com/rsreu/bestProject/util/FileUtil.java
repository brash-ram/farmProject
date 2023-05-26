package com.rsreu.bestProject.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class FileUtil {
    
    public String save(MultipartFile image, String absolutePathToImages) {
        String orgName = image.getOriginalFilename();
        String filePath = absolutePathToImages + orgName;
        File dest = new File(filePath);
        if(!dest.exists())
        {
            new File(dest.toString()).mkdir();
        }
        try {
            image.transferTo(dest);
        }
        catch (IllegalStateException | IOException e)
        {
            e.printStackTrace();
        }

        return filePath;
    }
}
