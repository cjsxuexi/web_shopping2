package com.zrgj.utils;

import java.io.File;
import java.util.UUID;

/**
 * 文件工具类
 */
public final class FileUtils {

	private FileUtils() {

	}

	// 生成唯一的文件名
	public static String generateUniqueFileName(String fileName) {
		String ext = getExt(fileName);
		return UUID.randomUUID().toString().replace("-", "") + ext;
	}

	// 获取文件的扩展名
	public static String getExt(String fileName) {
		String ext = fileName.substring(fileName.lastIndexOf("."));
		return ext;
	}

	// 目录打散
	public static String generateDir(String fileName, String saveDir) {
		int hashCode = fileName.hashCode();

		int dir1 = hashCode & 0x0F;
		int dir2 = (hashCode & 0XF0) >> 4;

		String dir = saveDir + "\\" + dir1 + "\\" + dir2;
		File file = new File(dir);
		if (!file.exists()) {
			file.mkdirs();
		}
		return dir;
	}
	
	// 获取文件的保存位置(相对的路径)
	public static String getRelativeDir(String fileName){
		
		int hashCode = fileName.hashCode();

		int dir1 = hashCode & 0x0F;
		int dir2 = (hashCode & 0XF0) >> 4;
		
		return dir1 + "\\" + dir2;
	}
}
