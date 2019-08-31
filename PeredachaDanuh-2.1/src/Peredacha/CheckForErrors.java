package Peredacha;

public class CheckForErrors {	
	
	String checkForErrors(String location, String ipAddress, String port){		
		
		if(location.isEmpty()) {				
			return "Вы не ввели путь файла, поворите попытку!"; 							
		}		
		if(ipAddress.isEmpty()) {				
			return "Вы не ввели IP, повторите попытку!";						
		}		
		try {
			int serverPort =Integer.parseInt(port);		
			if(serverPort<0 || serverPort>65536) {
				return "Неверный порт(1-65536)";
			}			
		}
		catch(Exception e) {
			return "Вы ввели Port с ошибкой, повторите попытку!";
		}		
		return null;
	}	
}
