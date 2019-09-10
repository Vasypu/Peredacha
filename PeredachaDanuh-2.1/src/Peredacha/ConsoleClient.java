package Peredacha;

/**
 * @author Vasypu
 *
 *	класс ConsoleClient содержит метод main, который принимает три аргумента, в методе мы проверяем на кол-во 
 *	переданных аргументов, если оно равно 0, то создается графический клиент и вызывается его метод .createGraphicalClient()
 *	если аргумента 3, то они передаются классу CheckForErrors для проверки и выводит ошибку, если она есть, если нет
 *	то передает данные классу Sender, если удается установить соединени, то пишет, что данные отправленны, если нет то 
 *	сообщение об ошибке, если кол-во аргументов не равно 0 или не равно 3, то выдается сообщение об ошибке.
 */
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
			System.out.print("Не верное кол-во аргументов!"/*+(3-args.length)*/);		
		}		
	}
}
