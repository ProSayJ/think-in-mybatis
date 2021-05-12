package com.prosayj.io;

import java.io.InputStream;

/**
 * 获取配文件流资源
 *
 * @author yangjian
 * @date 2021-05-11
 */
public class Resources {

    /**
     * 根据配置文件的路径，将配置文件加载成字节输入流，存储在内存中
     *
     * @param path 路径
     * @return InputStream 输出流
     */
    public static InputStream getResourceAsSteam(String path) {
        return Resources.class.getClassLoader().getResourceAsStream(path);
    }

}
