package Peredacha;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author Vasypu
 * 
 * у класса Sender есть метод send с возврщаемым типом String, этот метод принемает 3 аргумента,
 * в конструкции try пытается считать файл и установить соединение с
 * сервером по переданным аргументам методу send, если соединение с сервером проходит успешно и удалось найти 
 * указаный файл, то в теле метода создается буффер, если нет, то возврщается строку с содержанием об ошибке, 
 * который будет заполнятся байтами из считанного файла и отправлятся серверу буфферами размером в 55 байт.
 */
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
