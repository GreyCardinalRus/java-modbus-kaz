package mvc;
	import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.UIManager;

import mvc.cntrollers.PenFormController;
import mvc.models.PenFormModel;
import mvc.views.PenFormView;

	/**
	 *
	 * @author Escalade
	 */
	public class PenFormMvc extends JFrame {
	 
	    /**
		 * 
		 */
		private static final long serialVersionUID = 5285107536608064782L;
		private PenFormModel model = null;
	    private PenFormView view = null;
	    private PenFormController controller = null;
	 
	    public PenFormMvc() {
	        controller = new PenFormController(this);
	        model = new PenFormModel(this);
	        view = new PenFormView(this);
	        getContentPane().add(view);
	    }
	 
	    public PenFormModel getModel() {
	        return model;
	    }
	 
	    public PenFormView getView() {
	        return view;
	    }
	 
	    public PenFormController getController() {
	        return controller;
	    }
	 
	 
	    public static void main(String[] args) {
	 
	        try {
	            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	 
	        EventQueue.invokeLater(new Runnable() {
	 
	            public void run() {
	                JFrame frame = new PenFormMvc();              
	                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                frame.setTitle("MVC Super puper form");
	                frame.setBounds(100, 100, 700, 514);
	                frame.setLocationByPlatform(true);
	                frame.setVisible(true);
	            }
	        });
	    }
	}

