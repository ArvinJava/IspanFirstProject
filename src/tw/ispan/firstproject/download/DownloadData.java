package tw.ispan.firstproject.download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadData {

	private static String path = "	https://data.fda.gov.tw/opendata/exportDataList.do?method=ExportData&InfoId=106&logType=2";
	
	public static void main(String[] args) throws IOException {
		
		URL url = new URL(path);
		InputStream stream = url.openStream();
		
		
		BufferedInputStream bis = new BufferedInputStream(stream);
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("C:/Action/gov1.csv"));
		byte[] data = new byte[bis.available()];
		int i;
		while ((i=bis.read())!= -1) {
			bos.write(data);
		}
		bos.close();
		bis.close();
		System.out.println("success");
		
		

	}

}
