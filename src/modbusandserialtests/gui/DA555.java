package modbusandserialtests.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.util.ResourceBundle;
import javax.swing.JButton;

import com.sun.prism.paint.Stop;

import modbusandserialtests.trash.DialogAbout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DA555 {

	private JFrame frmDaSpecailFor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DA555 window = new DA555();
					window.frmDaSpecailFor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DA555() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDaSpecailFor = new JFrame();
		frmDaSpecailFor.setTitle(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.frmDaSpecailFor.title")); //$NON-NLS-1$ //$NON-NLS-2$
		frmDaSpecailFor.setBounds(100, 100, 450, 300);
		frmDaSpecailFor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmDaSpecailFor.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mntmNewMenuItem.text")); //$NON-NLS-1$ //$NON-NLS-2$
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmExit = new JMenuItem(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mntmExit.text")); //$NON-NLS-1$ //$NON-NLS-2$
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmDaSpecailFor.setVisible(false);	
			}
		});
		mnNewMenu.add(mntmExit);
		
		JButton btnConnect = new JButton(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.btnConnect.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JMenu mnSettings = new JMenu(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mnSettings.text")); //$NON-NLS-1$ //$NON-NLS-2$
		menuBar.add(mnSettings);
			
			JMenuItem mntmPort = new JMenuItem(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mntmPort.text_1")); //$NON-NLS-1$ //$NON-NLS-2$
			mnSettings.add(mntmPort);
		
			JMenuItem mntmPort_1 = new JMenuItem(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mntmPort_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
		mnSettings.add(mntmPort_1);
		
		JMenuItem mntmPort_2 = new JMenuItem(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mntmPort_2.text")); //$NON-NLS-1$ //$NON-NLS-2$
		mnSettings.add(mntmPort_2);
		
		JMenuItem mntmPort_3 = new JMenuItem(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mntmPort_3.text")); //$NON-NLS-1$ //$NON-NLS-2$
		mnSettings.add(mntmPort_3);
		
		JMenuItem mntmPort_4 = new JMenuItem(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mntmPort_4.text")); //$NON-NLS-1$ //$NON-NLS-2$
		mnSettings.add(mntmPort_4);
		
		JMenuItem mntmPort_5 = new JMenuItem(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mntmPort_5.text")); //$NON-NLS-1$ //$NON-NLS-2$
		mnSettings.add(mntmPort_5);
		
		JMenuItem mntmPort_6 = new JMenuItem(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mntmPort_6.text")); //$NON-NLS-1$ //$NON-NLS-2$
		mnSettings.add(mntmPort_6);
		
		JMenuItem mntmPort_7 = new JMenuItem(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mntmPort_7.text")); //$NON-NLS-1$ //$NON-NLS-2$
		mnSettings.add(mntmPort_7);
		
		JMenu mnNewMenu_1 = new JMenu(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mnNewMenu_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mntmNewMenuItem_1.text"));
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmAbout = new JMenuItem(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mntmAbout.text")); //$NON-NLS-1$ //$NON-NLS-2$
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutDialog dialogAbout = new AboutDialog();
				dialogAbout.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmAbout);
		menuBar.add(btnConnect);
	}

}
