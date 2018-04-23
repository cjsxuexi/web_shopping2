package com.zrgj.utils;

import java.io.File;
import java.util.UUID;

/**
 * �ļ�������
 */
public final class FileUtils {

	private FileUtils() {

	}

	// ����Ψһ���ļ���
	public static String generateUniqueFileName(String fileName) {
		String ext = getExt(fileName);
		return UUID.randomUUID().toString().replace("-", "") + ext;
	}

	// ��ȡ�ļ�����չ��
	public static String getExt(String fileName) {
		String ext = fileName.substring(fileName.lastIndexOf("."));
		return ext;
	}

	// Ŀ¼��ɢ
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
	
	// ��ȡ�ļ��ı���λ��(��Ե�·��)
	public static String getRelativeDir(String fileName){
		
		int hashCode = fileName.hashCode();

		int dir1 = hashCode & 0x0F;
		int dir2 = (hashCode & 0XF0) >> 4;
		
		return dir1 + "\\" + dir2;
	}
}
