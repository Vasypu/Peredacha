	package Peredacha;

	import org.eclipse.swt.SWT;
	import org.eclipse.swt.events.SelectionAdapter;
	import org.eclipse.swt.events.SelectionEvent;
	import org.eclipse.swt.layout.GridData;
	import org.eclipse.swt.layout.GridLayout;
	import org.eclipse.swt.widgets.Button;
	import org.eclipse.swt.widgets.Composite;
	import org.eclipse.swt.widgets.Display;
	import org.eclipse.swt.widgets.FileDialog;
	import org.eclipse.swt.widgets.Label;
	import org.eclipse.swt.widgets.MessageBox;
	import org.eclipse.swt.widgets.Shell;
	import org.eclipse.swt.widgets.Text;

/**
 * Занимается отрисовкой графического клиента
 * 
 * @author Vasypu
 */
public class GraphicalClient {	
	 
	//public static void main(String[] args) {
		
	/** Создает графический клиент, выбирает файл и передает данные классу CheckForErrors	 
	 */
	void createGraphicalClient() {
		
	Display display = new Display ();
    Shell shell = new Shell (display);
    shell.setText("GraphicalClient");
    GridLayout gridLayout = new GridLayout();
	gridLayout.marginLeft = 10;
    gridLayout.marginRight = 5;
    gridLayout.numColumns  = 2;        
    shell.setLayout(gridLayout);
    
    Label file= new Label(shell,SWT.NONE);
    file.setText("Файл:");    
    file.setLayoutData(new GridData(SWT.END,SWT.CENTER,false,false));
    
	Composite comprow1= new Composite(shell, SWT.BORDER);    
    comprow1.setLayout(new GridLayout(2, false));    
    comprow1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
       
    Text textfile= new Text(comprow1, SWT.BORDER);    
    textfile.setLayoutData(new GridData(SWT.FILL,SWT.CENTER, true, false));
    
    Button review= new Button(comprow1, SWT.PUSH);
    review.setText("Обзор");        
    review.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
    review.addSelectionListener(new SelectionAdapter() {

	    public void widgetSelected (SelectionEvent e){
	    	FileDialog dlg = new FileDialog(shell, SWT.OPEN);
		       String fname = dlg.open();
		        
		       if(fname != null) {
		    	   textfile.setText(fname);		    		
		    	}		    			       
	    }
	});
    
    Label ip= new Label(shell,SWT.NONE);
    ip.setText("IP:");    
    ip.setLayoutData(new GridData(SWT.END,SWT.CENTER,false,false));           
    
    Composite comprow2= new Composite(shell, SWT.BORDER);    
    comprow2.setLayout(new GridLayout(3, false));
    comprow2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false)); 
    
    Text textip= new Text(comprow2, SWT.BORDER);
    textip.setLayoutData(new GridData(SWT.FILL,SWT.CENTER, true, false));
    
    Label labport= new Label(comprow2,SWT.NONE);
    labport.setText("Порт:");		
    
    Text textport= new Text(comprow2, SWT.BORDER);     
    textport.setLayoutData(new GridData(SWT.FILL,SWT.CENTER, true, false));    
    
    Label emptylab= new Label(shell,SWT.NONE);    
    
    Button send= new Button(shell, SWT.PUSH);
    send.setText("Отправить");    
    send.setLayoutData(new GridData(SWT.END,SWT.END, false, true));   
    send.addSelectionListener(new SelectionAdapter() {
    	
	    /** Отправляет данные классу CheckForErrors
	     *
	     */
	    public void widgetSelected (SelectionEvent e){		    	
	    	
	    	String location = textfile.getText();	    	
	    	String ipAddress = textip.getText();	    	
	    	String serverPort = textport.getText();
	    	
	    	CheckForErrors check = new CheckForErrors();	
	    	String message = check.checkForErrors(location, ipAddress, serverPort);
	    	if(message != null) {
	    		showError(shell, message);
	    	}
	    	else {	    		
	    		Sender sender = new Sender();
	    		message = sender.send(location, ipAddress, Integer.parseInt(serverPort));
	    		if(message != null) {
	    			showError(shell, message);
				}
	    		else{
	    			MessageBox messageBox = new MessageBox (shell);
					messageBox.setText ("Готово");
					messageBox.setMessage ("Данные отправлены)");
					messageBox.open();
	    		}
	    	}	    	
	    }
	});    

    shell.pack ();
    shell.open ();

    while (!shell.isDisposed ()) {
        if (!display.readAndDispatch ()) 
            display.sleep ();
    }
    display.dispose ();
	}
	
	/** Выводит сообщение об ошибке
	 * @param shell окно для ошибки
	 * @param message сообщение с содержимым ошибки
	 */
	static void showError(Shell shell, String message) {
		
		MessageBox messageBox = new MessageBox (shell);
		messageBox.setText ("Ошибка!");
		messageBox.setMessage (message);
		messageBox.open();
	}
}
