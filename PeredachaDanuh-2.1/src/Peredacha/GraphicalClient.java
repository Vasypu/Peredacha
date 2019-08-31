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

public class GraphicalClient {

	//public static void main(String[] args) {
		
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
    file.setText("����:");    
    file.setLayoutData(new GridData(SWT.END,SWT.CENTER,false,false));
	Composite comprow1= new Composite(shell, SWT.BORDER);    
    comprow1.setLayout(new GridLayout(2, false));    
    comprow1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));                  // ������ � comprow1, �� ��� �������� � ������ ������� ����
    Text textfile= new Text(comprow1, SWT.BORDER);    
    textfile.setLayoutData(new GridData(SWT.FILL,SWT.CENTER, true, false));       
    Button review= new Button(comprow1, SWT.PUSH);
    review.setText("�����");        
    review.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
    review.addSelectionListener(new SelectionAdapter() {

	    public void widgetSelected (SelectionEvent e){
	    	FileDialog dlg = new FileDialog(shell, SWT.OPEN);
		       String fname = dlg.open();
		        
		       if(fname != null) {
		    	   textfile.setText(fname);		    		
		    	}
		    	else {				
		    		dlg = new FileDialog(shell, SWT.CLOSE);		    		
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
    labport.setText("����:");		
    Text textport= new Text(comprow2, SWT.BORDER);     
    textport.setLayoutData(new GridData(SWT.FILL,SWT.CENTER, true, false));    
    Label emptylab= new Label(shell,SWT.NONE);    
    Button send= new Button(shell, SWT.PUSH);
    send.setText("���������");    
    send.setLayoutData(new GridData(SWT.END,SWT.END, false, true));   
    send.addSelectionListener(new SelectionAdapter() {

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
					messageBox.setText ("�����");
					messageBox.setMessage ("������ ����������)");
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
	
	static void showError(Shell shell, String message) {
		MessageBox messageBox = new MessageBox (shell);
		messageBox.setText ("������!");
		messageBox.setMessage (message);
		messageBox.open();
	}
}
