package Peredacha;

public class CheckForErrors {	
	
	String checkForErrors(String location, String ipAddress, String port){		
		
		if(location.isEmpty()) {				
			return "�� �� ����� ���� �����, �������� �������!"; 							
		}		
		if(ipAddress.isEmpty()) {				
			return "�� �� ����� IP, ��������� �������!";						
		}		
		try {
			int serverPort =Integer.parseInt(port);		
			if(serverPort<0 || serverPort>65536) {
				return "�������� ����(1-65536)";
			}			
		}
		catch(Exception e) {
			return "�� ����� Port � �������, ��������� �������!";
		}		
		return null;
	}	
}
