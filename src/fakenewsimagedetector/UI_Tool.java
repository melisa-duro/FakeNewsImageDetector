/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fakenewsimagedetector;

import manager.DateLabelFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import popup.Information_popUp;

import static fakenewsimagedetector.Main.allUsers;
import static fakenewsimagedetector.Main.Date_ini;
import static fakenewsimagedetector.Main.Date_fin;
import static manager.Utiles.initLogger;
import static manager.Utiles.initPaths;
import static fakenewsimagedetector.Main.logger;
import java.util.logging.Level;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author melisa_duro
 */
public class UI_Tool extends javax.swing.JFrame {

    public static Information_popUp popUp;
    public static JDatePickerImpl datePicker;
    public static JDatePickerImpl datePicker_fin;

    public UI_Tool() {
        initComponents();
        initPaths();
        initLogger();

        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("FAKENEWSDETECTOR");

        Wait.setVisible(false);

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel_Fechaini);
        jPanel_Fechaini.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(datePicker)
                                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(datePicker)
                                .addContainerGap(50, Short.MAX_VALUE))
        );

        UtilDateModel model_fin = new UtilDateModel();
        Properties p_fin = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel_fin = new JDatePanelImpl(model_fin, p_fin);
        datePicker_fin = new JDatePickerImpl(datePanel_fin, new DateLabelFormatter());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel_Fechafin);
        jPanel_Fechafin.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(datePicker_fin)
                                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(datePicker_fin)
                                .addContainerGap(50, Short.MAX_VALUE))
        );

        if (popUp != null) {
            popUp.setVisible(false);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        twitter_elpais = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        twitter_elmundo = new javax.swing.JCheckBox();
        twitter_CNN = new javax.swing.JCheckBox();
        twitter_BBC = new javax.swing.JCheckBox();
        twitter_publico = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton_Exec = new javax.swing.JButton();
        Header_popUpInfo = new javax.swing.JLabel();
        jPanel_Fechaini = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel_Fechafin = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        Wait = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FAKE NEWS DETECTOR");
        setBackground(new java.awt.Color(153, 153, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(102, 102, 255));

        jPanel1.setBackground(new java.awt.Color(0, 172, 237));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 400));

        twitter_elpais.setBackground(new java.awt.Color(0, 172, 237));
        twitter_elpais.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        twitter_elpais.setText("@el_pais");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Cuentas:");

        twitter_elmundo.setBackground(new java.awt.Color(0, 172, 237));
        twitter_elmundo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        twitter_elmundo.setText("@elmundoes");
        twitter_elmundo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                twitter_elpaisStateChanged(evt);
            }
        });

        twitter_CNN.setBackground(new java.awt.Color(0, 172, 237));
        twitter_CNN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        twitter_CNN.setText("@CNN");
        twitter_CNN.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                twitter_elpaisStateChanged(evt);
            }
        });

        twitter_BBC.setBackground(new java.awt.Color(0, 172, 237));
        twitter_BBC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        twitter_BBC.setText("@BBCNews");
        twitter_BBC.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                twitter_elpaisStateChanged(evt);
            }
        });

        twitter_publico.setBackground(new java.awt.Color(0, 172, 237));
        twitter_publico.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        twitter_publico.setText("@publico_es");
        twitter_publico.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                twitter_elpaisStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Fecha inicio:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Fecha fin: ");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/697029-twitter-128.png"))); // NOI18N
        jLabel4.setToolTipText("");
        jLabel4.setMaximumSize(new java.awt.Dimension(60, 40));
        jLabel4.setMinimumSize(new java.awt.Dimension(60, 40));
        jLabel4.setName(""); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(60, 40));

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField1.setToolTipText("");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel6.setText("Indique alguna otra cuenta o cuentas separadas por coma:");

        jButton_Exec.setBackground(new java.awt.Color(153, 153, 255));
        jButton_Exec.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_Exec.setText("EJECUTAR");
        jButton_Exec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ExecActionPerformed(evt);
            }
        });

        Header_popUpInfo.setBackground(new java.awt.Color(192, 222, 237));
        Header_popUpInfo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Header_popUpInfo.setForeground(new java.awt.Color(255, 255, 255));
        Header_popUpInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Header_popUpInfo.setText("FAKE NEWS DETECTOR");
        Header_popUpInfo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), null));
        Header_popUpInfo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Header_popUpInfo.setOpaque(true);

        jPanel_Fechaini.setBackground(new java.awt.Color(0, 172, 237));

        javax.swing.GroupLayout jPanel_FechainiLayout = new javax.swing.GroupLayout(jPanel_Fechaini);
        jPanel_Fechaini.setLayout(jPanel_FechainiLayout);
        jPanel_FechainiLayout.setHorizontalGroup(
            jPanel_FechainiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FechainiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(173, Short.MAX_VALUE))
        );
        jPanel_FechainiLayout.setVerticalGroup(
            jPanel_FechainiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FechainiLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel5)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel_Fechafin.setBackground(new java.awt.Color(0, 172, 237));

        javax.swing.GroupLayout jPanel_FechafinLayout = new javax.swing.GroupLayout(jPanel_Fechafin);
        jPanel_Fechafin.setLayout(jPanel_FechafinLayout);
        jPanel_FechafinLayout.setHorizontalGroup(
            jPanel_FechafinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FechafinLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(173, Short.MAX_VALUE))
        );
        jPanel_FechafinLayout.setVerticalGroup(
            jPanel_FechafinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FechafinLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel8)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        Wait.setBackground(new java.awt.Color(0, 0, 0));
        Wait.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Wait.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/giphy (1).gif"))); // NOI18N
        Wait.setToolTipText("");
        Wait.setMaximumSize(new java.awt.Dimension(60, 40));
        Wait.setMinimumSize(new java.awt.Dimension(60, 40));
        Wait.setName(""); // NOI18N
        Wait.setPreferredSize(new java.awt.Dimension(60, 40));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(twitter_publico)
                                    .addComponent(twitter_elmundo)
                                    .addComponent(twitter_BBC)
                                    .addComponent(twitter_elpais)
                                    .addComponent(twitter_CNN))
                                .addGap(18, 18, 18)
                                .addComponent(Wait, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel_Fechaini, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel_Fechafin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_Exec, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))))
            .addComponent(Header_popUpInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Header_popUpInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(twitter_elpais)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1)
                                .addComponent(twitter_CNN)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(twitter_elmundo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(twitter_BBC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(twitter_publico))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(Wait, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel_Fechaini, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel_Fechafin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 79, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButton_Exec, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void twitter_elpaisStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_twitter_elpaisStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_twitter_elpaisStateChanged

    private void jButton_ExecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ExecActionPerformed
        worker_Exec_Twitter wOUT_Twitter = new worker_Exec_Twitter();
        wOUT_Twitter.start();
    }//GEN-LAST:event_jButton_ExecActionPerformed

    public class worker_Exec_Twitter extends Thread {

        @Override
        public void run() {

            String datePattern = "yyyy-MM-dd";
            SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

            //Date_ini=jPanel_Fechaini.getSelected().toString();
            //Date_fin=jPanel_Fechaini.getSelectedItem().toString();
            Date selectedDate_ini = (Date) datePicker.getModel().getValue();

            if (selectedDate_ini != null) {
                Date_ini = dateFormatter.format(selectedDate_ini);

            }
            Date selectedDate_fin = (Date) datePicker_fin.getModel().getValue();

            if (selectedDate_fin != null) {
                Date_fin = dateFormatter.format(selectedDate_fin);

            }

            allUsers.clear();
            if (twitter_elpais.isSelected()) {
                allUsers.add("el_pais");
            }
            if (twitter_CNN.isSelected()) {
                allUsers.add("CNN");
            }
            if (twitter_elmundo.isSelected()) {
                allUsers.add("elmundoes");
            }
            if (twitter_BBC.isSelected()) {
                allUsers.add("BBCNews");
            }
            if (twitter_publico.isSelected()) {
                allUsers.add("publico_es");
            }
            if (!"".equals(jTextField1.getText())) {
                String[] aux = jTextField1.getText().replace("@", "").split(",");
                for (int i = 0; i < aux.length; i++) {
                    allUsers.add(aux[i]);
                }
            }

            if (Date_ini != "" && Date_fin != "" && !allUsers.isEmpty() && (Date_ini.compareTo(Date_fin) < 0)) {

                Wait.setVisible(true);
                jButton_Exec.setEnabled(false);
                jPanel_Fechaini.setEnabled(false);
                jPanel_Fechafin.setEnabled(false);
                
                //Ejecución main
                fakenewsimagedetector.Main.start(Date_ini,Date_fin,allUsers);
                
                jButton_Exec.setEnabled(true);
                jPanel_Fechaini.setEnabled(true);
                jPanel_Fechafin.setEnabled(true);
                Wait.setVisible(false);

                FinalProcessUI("Procesos completados correctamente");
                logger.logp(Level.INFO, "fakenewsimagedetector ->", "finis", "Finish OK");

            } else if (Date_ini.compareTo(Date_fin) > 0) {  // La fecha de inicio es superior a la de fin
                FinalProcessUI("La fecha de inicio debe ser anterior a la fecha de fin");
            } else {  // Alguno de los campos no se ha rellenado
                FinalProcessUI("Seleccione parámetros de entrada");
            }
        }
    }

    public void FinalProcessUI(String OutputProcess) {

        if (popUp != null) {
            popUp.setVisible(false);
        }

        if (!OutputProcess.isEmpty()) {
            popUp = new Information_popUp("FINISH", OutputProcess, "", "INFORMATION");
            popUp.showPopUp();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI_Tool().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Header_popUpInfo;
    private javax.swing.JLabel Wait;
    private javax.swing.JButton jButton_Exec;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_Fechafin;
    private javax.swing.JPanel jPanel_Fechaini;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JCheckBox twitter_BBC;
    private javax.swing.JCheckBox twitter_CNN;
    private javax.swing.JCheckBox twitter_elmundo;
    private javax.swing.JCheckBox twitter_elpais;
    private javax.swing.JCheckBox twitter_publico;
    // End of variables declaration//GEN-END:variables
}
