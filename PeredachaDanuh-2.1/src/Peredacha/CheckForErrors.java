package Peredacha;

/**
 * Занимамется проверкой переданных данных
 * 
 * @author Vasypu
 */
public class CheckForErrors {	
	
	/** Проверяет данные
	 * @param location путь к файлу
	 * @param ipAddress адрес сервера
	 * @param port порт сервера
	 * @return сообщение об ошибке или null, если они верны
	 */
	String checkForErrors(String location, String ipAddress, String port){		
		
		if(location.isEmpty()) {				
			return "Вы не ввели путь файла, повторите попытку!"; 							
		}		
		if(ipAddress.isEmpty()) {				
			return "Вы не ввели IP, повторите попытку�!";						
		}		
		try {
			int serverPort =Integer.parseInt(port);		
			if(serverPort<=0 || serverPort>=65536) {
				return "Неверный порт (1-65536)";
			}			
		}
		catch(Exception e) {
			return "Вы ввели Port c ошибкой, повторите попытку!";
		}		
		return null;
	}	
}
