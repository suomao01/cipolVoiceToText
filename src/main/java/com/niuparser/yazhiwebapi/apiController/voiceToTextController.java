package com.niuparser.yazhiwebapi.apiController;

import com.niuparser.yazhiwebapi.util.TestLfasrUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 思拓演示
 *
 * @author 87951
 */
@RestController
@RequestMapping("/voiceToText")
public class voiceToTextController {

    @PostMapping("/upload")
    @ResponseBody
    public Object upload(@RequestParam("file") MultipartFile file) {
        HashMap<Object, Object> map = new HashMap<>();
        if (file.isEmpty()) {
            map.put("code", 504);
            map.put("message", "上传失败，请选择文件");
        }
        String fileName = file.getOriginalFilename();
        String relativelyPath=System.getProperty("user.dir");
        String filePath = relativelyPath+"/wav-source/files/";
        File fileLu = new File(filePath);
        File dest = new File(filePath + fileName);
        if (!fileLu.exists() && !fileLu.isDirectory()) {
            fileLu.mkdirs();
        }
        try {
            file.transferTo(dest);
            Map<String, String> mapText = TestLfasrUtils.getVoiceToText(filePath, fileName);
            if (!CollectionUtils.isEmpty(mapText)) {
                for (String voicText : mapText.values()) {
                    if (StringUtils.isNotBlank(voicText)) {
                        map.put("code", 200);
                        map.put("message", "上传成功");
                        map.put("voicText", voicText);
                        System.out.println("上传成功");
                    }
                }
            }
        } catch (IOException e) {
            System.out.print(e.toString());
        }
        return map;
    }


}
