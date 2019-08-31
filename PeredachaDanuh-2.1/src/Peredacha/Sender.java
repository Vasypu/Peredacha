package Peredacha;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Sender {
	
	  String send (String location, String ipAddress, int port) {
		try (FileInputStream fin = new FileInputStream(location); Socket socket = new Socket(ipAddress, port)) {
		
			byte [] buffer = new byte[55];
			OutputStream out = socket.getOutputStream();
			int readed;
			while((readed = fin.read(buffer)) != -1) {
				out.write(buffer, 0, readed);					
			}				
		} catch (IOException ex) {			
			return "Ошибка ввода вывода(" + ex.getMessage();						
		}
		return null;
	}
}
