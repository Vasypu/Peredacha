package Peredacha;

/**
 * @author Vasypu
 *	класс CheckForErrors проверяет на ошибки полученные данные, у него есть метод checkForErrors, который принимает 
 *	три аргумента и возвращает переменную String с содержанием ошибки, если все аргументы были проверенны и ошибки не выявлены,
 *	то метод возврщает ничего.
 *	
 */
public class CheckForErrors {	
	
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
