package mvc.cntrollers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sun.org.mozilla.javascript.internal.Context;
import sun.org.mozilla.javascript.internal.ContextFactory.Listener;
import javax.swing.JOptionPane;

import mvc.PenFormMvc;
import mvc.views.*;

public class PenFormController implements Listener, ActionListener, ListSelectionListener{
	// root panel
	JFrame panel;
	 private PenFormMvc mvc = null;	
	/**
	 * @param args
	 */
     public PenFormController(PenFormMvc mvc) {
     	this.mvc = mvc;
   //          initialize(parientFrame);
     }

//	 public static void main(String[] args) {
//		// TODO Auto-generated method stub
//       PenFormController controller = new PenFormController();
//       controller.panel = new JFrame();  
//       PenFormView view = new PenFormView(controller.panel);
//
//
//       controller.panel.setTitle("Super puper form");
//       controller.panel.setBounds(100, 100, 700, 514);
//       controller.panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//       
//       controller.panel.add(view);
//       controller.panel.setVisible(true);
// //      view.main(args);
//	}
	public void contextCreated(Context arg0) {
		// TODO Auto-generated method stub
		
	}
	public void contextReleased(Context arg0) {
		// TODO Auto-generated method stub
		
	}

	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
        
        if(source==mvc.getView().btnStartStop) {
            JOptionPane.showMessageDialog(mvc, "Нажата кнопка OK");
        }
//        else if(source==mvc.getView().cancelButton) {
//            JOptionPane.showMessageDialog(mvc, "Нажата кнопка CANCEL");
//        }
        else System.out.println("ActionPerformed");		
	}

}
