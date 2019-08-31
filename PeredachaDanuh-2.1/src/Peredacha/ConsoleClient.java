package Peredacha;

public class ConsoleClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String location;
		String ipAddress;
		String serverPort;			
		if(args.length==0) {							
			GraphicalClient client = new GraphicalClient();		
			client.createGraphicalClient();
		}
		else if(args.length == 3) {			
			location=args[0];
			ipAddress=args[1];
			serverPort=args[2];
			CheckForErrors check= new CheckForErrors();
			String msg = check.checkForErrors(location, ipAddress, serverPort);
			if(msg != null) {
	    		System.err.print(msg);;
	    	}
	    	else {	    		
	    		Sender sender = new Sender();
	    		msg = sender.send(location, ipAddress, Integer.parseInt(serverPort));
	    		if(msg != null) {
	    			System.err.print(msg);
				}
	    		else {
	    			System.out.print("Данные отправлены)");
	    		}
	    	}
		}
		else{
			System.out.println();
			System.out.print("Не хватает аргументов:"+(3-args.length));		
		}		
	}
}
