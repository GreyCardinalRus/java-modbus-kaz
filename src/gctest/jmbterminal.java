package gctest;

import javax.swing.JFrame;



public class jmbterminal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3349553398694039053L;

	public jmbterminal()
	
	    {
	
	        System.out.println("Java");
	
	        init();             // simulate browser call(1)
	
	        setSize(1000,800);           // Set the size of the frame
	
	        setVisible(true);           // Show the frame
	
	    }

	//init
	
	    public void init()
	
	    {
	
	              System.out.println("Applet initializing");
	
	              getContentPane().add(new Form());
//	              getContentPane().add(new Form("ModBus"));
	
//	              method();
	
	     }
	
	        //start
	
	    public void start()
	
	       {
	
	            System.out.println("Applet starting");
	
	        }
	
	        //stop
	
	       public void stop()
	
	        {
	
	            System.out.println("Applet stopping");
	
	        }
	
	        //destroy
	
	        public void destroy()
	
	        {
	
	            System.out.println("Applet destroyed");
	
	        }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        System.out.println("run jmb...");
        new jmbterminal();
	}

}
