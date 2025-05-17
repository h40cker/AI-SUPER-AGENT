package com.yupi.aisuperagent.tools;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import com.yupi.aisuperagent.constant.FileConstant;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

public class FileOperationTool {
    private final String FILE_DIR = FileConstant.FILE_SAVE_DIR + "file";

    @Tool(description = "Read content from a file")
    public String readFile(@ToolParam(description = "Name of a file to read") String fileName) {
        String filePath = FILE_DIR + "/"+fileName;
        try{
            return FileUtil.readUtf8String(filePath);
        }catch (Exception e) {
            return "Error reading file: " + fileName;
        }
    }
    @Tool(description =  "write content to a file")
    public String writeFile(@ToolParam(description = "Name of the file to write") String fileName,@ToolParam(description = "the content write to the file") String content) {
        String filePath = FILE_DIR + "/"+fileName;
        try {
            FileUtil.mkdir(filePath);
            FileUtil.writeUtf8String(content,filePath);
            return "File written to "+fileName;
        } catch (IORuntimeException e) {
            return "Error writing file: " + fileName;
        }
    }
}
