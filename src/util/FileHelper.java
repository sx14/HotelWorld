package util;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class FileHelper {
	public static boolean saveFile(File file,String fileName,String path){
		try {
			File saveFile = new File(new File(path),fileName);
			if (!saveFile.getParentFile().exists()) {
				saveFile.getParentFile().mkdirs();
			}
			FileUtils.copyFile(file,saveFile);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
