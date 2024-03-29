/* jSSC-Terminal - serial port terminal.
 * © Alexey Sokolov (scream3r), 2011.
 *
 * This file is part of jSSC-Terminal.
 *
 * jSSC-Terminal is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * jSSC-Terminal is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *
 * e-mail: scream3r.org@gmail.com
 * web-site: www.scream3r.org
 */
package modbusandserialtests.trash;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import jssc.SerialPortList;

/**
 *
 * @author scream3r
 */
public class DialogSettings extends javax.swing.JPanel {

    private Form parent;
    private GlassPane glassPane;

    private String portName;
    private int baudRate;
    private int dataBits;
    private int stopBits;
    private int parity;
    
    /** Creates new form Dialog */
    public DialogSettings(Form parent, String portName, int baudRate, int dataBits, int stopBits, int parity) {
        this.parent = parent;
        this.portName = portName;
        this.baudRate = baudRate;
        this.dataBits = dataBits;
        this.stopBits = stopBits;
        this.parity = parity;
        initComponents();
        setSize(480, 129);

        parent.setControlsFocusable(false);
        glassPane = new GlassPane();
        glassPane.add(this);
        int x = (parent.getWidth()/2) - getWidth()/2;
        int y = (parent.getHeight()/2) - getHeight()/2;
        setBounds(x, y, getWidth(), getHeight());

        String[] ports = SerialPortList.getPortNames();
        for(String port : ports){
            jComboBoxPortName.addItem(port);
        }

        setSettings();

        parent.add(glassPane, 0);
        glassPane.setBounds(parent.getBounds());
        parent.validate();
        parent.repaint();
        jComboBoxPortName.requestFocus();
    }

   
	/**
	 * @wbp.parser.constructor
	 */
	public DialogSettings(JFrame parent, String portName, int baudRate, int dataBits, int stopBits, int parity) {
	//       this.parent = parent;
	        this.portName = portName;
	        this.baudRate = baudRate;
	        this.dataBits = dataBits;
	        this.stopBits = stopBits;
	        this.parity = parity;
	        initComponents();
	        setSize(480, 129);

//	        parent.setControlsFocusable(false);
	        glassPane = new GlassPane();
	        glassPane.add(this);
	        int x = (parent.getWidth()/2) - getWidth()/2;
	        int y = (parent.getHeight()/2) - getHeight()/2;
	        setBounds(x, y, getWidth(), getHeight());

	        String[] ports = SerialPortList.getPortNames();
	        for(String port : ports){
	            jComboBoxPortName.addItem(port);
	        }

	        setSettings();

	        parent.getContentPane().add(glassPane, 0);
	        glassPane.setBounds(parent.getBounds());
	        parent.validate();
	        parent.repaint();
	        jComboBoxPortName.requestFocus();
	 	}

	private void closeDialog() {
        parent.remove(glassPane);
        parent.setControlsFocusable(true);
        parent.validate();
        parent.repaint();
    }

    private void setSettings(){
        jComboBoxPortName.setSelectedItem(portName);
        jComboBoxBaudrate.setSelectedItem("" + baudRate);
        jComboBoxDataBits.setSelectedItem("" + dataBits);
        if(stopBits == 1){
            jComboBoxStopBits.setSelectedItem("1");
        }
        else if(stopBits == 3) {
            jComboBoxStopBits.setSelectedItem("1.5");
        }
        else if(stopBits == 2) {
            jComboBoxStopBits.setSelectedItem("2");
        }
        jComboBoxParity.setSelectedIndex(parity);
    }

    private void saveSettings() {
        portName = jComboBoxPortName.getSelectedItem().toString();
        baudRate = Integer.valueOf(jComboBoxBaudrate.getSelectedItem().toString());
        dataBits = Integer.valueOf(jComboBoxDataBits.getSelectedItem().toString());
        String stop = jComboBoxStopBits.getSelectedItem().toString();
        if(stop.equals("1")){
            stopBits = 1;
        }
        else if(stop.equals("1.5")) {
            stopBits = 3;
        }
        else if(stop.equals("2")) {
            stopBits = 2;
        }
        parity = jComboBoxParity.getSelectedIndex();
        parent.updatePortSettings(portName, baudRate, dataBits, stopBits, parity);
        closeDialog();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jHeaderPanelHEX = new javax.swing.JPanel(){

            protected void paintComponent(Graphics g){
                GradientPaint paint = new GradientPaint(0, 0, NimbusGui.INFO_PANEL_TOP_COLOR, 0, getHeight(), NimbusGui.INFO_PANEL_BOTTOM_COLOR);
                Graphics2D graphics2D = (Graphics2D)g.create();
                graphics2D.setPaint(paint);
                graphics2D.fillRect(0, 0, getWidth(), getHeight());
            }
        }
        ;
        jHeaderLabelHEX = new javax.swing.JLabel();
        jDataPanel = new javax.swing.JPanel();
        jLabelPortName = new javax.swing.JLabel();
        jComboBoxPortName = new javax.swing.JComboBox();
        jLabelBaudrate = new javax.swing.JLabel();
        jComboBoxBaudrate = new javax.swing.JComboBox();
        jLabelDataBits = new javax.swing.JLabel();
        jComboBoxDataBits = new javax.swing.JComboBox();
        jLabelStopBits = new javax.swing.JLabel();
        jComboBoxStopBits = new javax.swing.JComboBox();
        jLabelParity = new javax.swing.JLabel();
        jComboBoxParity = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jButtonSave = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        jHeaderPanelHEX.setBorder(NimbusGui.DIALOG_PANEL_BORDER);
        jHeaderPanelHEX.setPreferredSize(new java.awt.Dimension(286, 30));

        jHeaderLabelHEX.setFont(new java.awt.Font("Tahoma", 1, 12));
        jHeaderLabelHEX.setForeground(NimbusGui.SECTION_LABEL_FONT_COLOR);
        jHeaderLabelHEX.setText("Serial port settings");

        javax.swing.GroupLayout jHeaderPanelHEXLayout = new javax.swing.GroupLayout(jHeaderPanelHEX);
        jHeaderPanelHEX.setLayout(jHeaderPanelHEXLayout);
        jHeaderPanelHEXLayout.setHorizontalGroup(
            jHeaderPanelHEXLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jHeaderPanelHEXLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jHeaderLabelHEX)
                .addContainerGap(352, Short.MAX_VALUE))
        );
        jHeaderPanelHEXLayout.setVerticalGroup(
            jHeaderPanelHEXLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jHeaderLabelHEX, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jDataPanel.setBackground(new java.awt.Color(231, 233, 237));
        jDataPanel.setBorder(NimbusGui.DIALOG_PANEL_BORDER);
        jDataPanel.setDoubleBuffered(false);
        jDataPanel.setPreferredSize(new java.awt.Dimension(276, 75));

        jLabelPortName.setFont(NimbusGui.DEFAULT_FONT);
        jLabelPortName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPortName.setText("Port name:");
        jLabelPortName.setPreferredSize(new java.awt.Dimension(120, 14));

        jComboBoxPortName.setFont(NimbusGui.DEFAULT_FONT);
        jComboBoxPortName.setPreferredSize(new java.awt.Dimension(120, 28));
        jComboBoxPortName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxPortNameKeyPressed(evt);
            }
        });

        jLabelBaudrate.setFont(NimbusGui.DEFAULT_FONT);
        jLabelBaudrate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBaudrate.setText("Baudrate:");
        jLabelBaudrate.setPreferredSize(new java.awt.Dimension(90, 14));

        jComboBoxBaudrate.setFont(NimbusGui.DEFAULT_FONT);
        jComboBoxBaudrate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "110", "300", "600", "1200", "4800", "9600", "14400", "19200", "38400", "57600", "115200" }));
        jComboBoxBaudrate.setPreferredSize(new java.awt.Dimension(90, 28));
        jComboBoxBaudrate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxBaudrateKeyPressed(evt);
            }
        });

        jLabelDataBits.setFont(NimbusGui.DEFAULT_FONT);
        jLabelDataBits.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDataBits.setText("Data bits:");
        jLabelDataBits.setPreferredSize(new java.awt.Dimension(70, 14));

        jComboBoxDataBits.setFont(NimbusGui.DEFAULT_FONT);
        jComboBoxDataBits.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "5", "6", "7", "8" }));
        jComboBoxDataBits.setPreferredSize(new java.awt.Dimension(70, 28));
        jComboBoxDataBits.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxDataBitsKeyPressed(evt);
            }
        });

        jLabelStopBits.setFont(NimbusGui.DEFAULT_FONT);
        jLabelStopBits.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelStopBits.setText("Stop bits:");
        jLabelStopBits.setPreferredSize(new java.awt.Dimension(70, 14));

        jComboBoxStopBits.setFont(NimbusGui.DEFAULT_FONT);
        jComboBoxStopBits.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "1.5", "2" }));
        jComboBoxStopBits.setPreferredSize(new java.awt.Dimension(70, 28));
        jComboBoxStopBits.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxStopBitsKeyPressed(evt);
            }
        });

        jLabelParity.setFont(NimbusGui.DEFAULT_FONT);
        jLabelParity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelParity.setText("Parity:");
        jLabelParity.setPreferredSize(new java.awt.Dimension(90, 14));

        jComboBoxParity.setFont(NimbusGui.DEFAULT_FONT);
        jComboBoxParity.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "None", "Odd", "Even", "Mark", "Space" }));
        jComboBoxParity.setPreferredSize(new java.awt.Dimension(90, 28));
        jComboBoxParity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxParityKeyPressed(evt);
            }
        });

        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 2));

        jButtonSave.setFont(NimbusGui.DEFAULT_FONT);
        jButtonSave.setText("Save");
        jButtonSave.setPreferredSize(new java.awt.Dimension(90, 28));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        jButtonSave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonSaveKeyPressed(evt);
            }
        });

        jButtonCancel.setFont(NimbusGui.DEFAULT_FONT);
        jButtonCancel.setText("Cancel");
        jButtonCancel.setPreferredSize(new java.awt.Dimension(90, 28));
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });
        jButtonCancel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonCancelKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jDataPanelLayout = new javax.swing.GroupLayout(jDataPanel);
        jDataPanel.setLayout(jDataPanelLayout);
        jDataPanelLayout.setHorizontalGroup(
            jDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
            .addGroup(jDataPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabelPortName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabelBaudrate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabelDataBits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabelStopBits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabelParity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jDataPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jComboBoxPortName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jComboBoxBaudrate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jComboBoxDataBits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jComboBoxStopBits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jComboBoxParity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDataPanelLayout.createSequentialGroup()
                .addContainerGap(288, Short.MAX_VALUE)
                .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        jDataPanelLayout.setVerticalGroup(
            jDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDataPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPortName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBaudrate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDataBits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelStopBits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelParity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxPortName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxBaudrate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxDataBits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxStopBits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxParity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addComponent(jHeaderPanelHEX, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jHeaderPanelHEX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jDataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        closeDialog();
}//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        saveSettings();
}//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonSaveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonSaveKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            closeDialog();
        }
    }//GEN-LAST:event_jButtonSaveKeyPressed

    private void jButtonCancelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonCancelKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            closeDialog();
        }
    }//GEN-LAST:event_jButtonCancelKeyPressed

    private void jComboBoxPortNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxPortNameKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            closeDialog();
        }
    }//GEN-LAST:event_jComboBoxPortNameKeyPressed

    private void jComboBoxBaudrateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxBaudrateKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            closeDialog();
        }
    }//GEN-LAST:event_jComboBoxBaudrateKeyPressed

    private void jComboBoxDataBitsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxDataBitsKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            closeDialog();
        }
    }//GEN-LAST:event_jComboBoxDataBitsKeyPressed

    private void jComboBoxStopBitsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxStopBitsKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            closeDialog();
        }
    }//GEN-LAST:event_jComboBoxStopBitsKeyPressed

    private void jComboBoxParityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxParityKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            closeDialog();
        }
    }//GEN-LAST:event_jComboBoxParityKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox jComboBoxBaudrate;
    private javax.swing.JComboBox jComboBoxDataBits;
    private javax.swing.JComboBox jComboBoxParity;
    private javax.swing.JComboBox jComboBoxPortName;
    private javax.swing.JComboBox jComboBoxStopBits;
    private javax.swing.JPanel jDataPanel;
    static javax.swing.JLabel jHeaderLabelHEX;
    private javax.swing.JPanel jHeaderPanelHEX;
    private javax.swing.JLabel jLabelBaudrate;
    private javax.swing.JLabel jLabelDataBits;
    private javax.swing.JLabel jLabelParity;
    private javax.swing.JLabel jLabelPortName;
    private javax.swing.JLabel jLabelStopBits;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

}
