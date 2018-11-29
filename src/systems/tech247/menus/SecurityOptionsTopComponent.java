/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.menus;

import java.io.IOException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.spi.actions.AbstractSavable;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.HrsSecurityOptions;
import systems.tech247.util.NotifyUtil;


/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.security//SecurityOptions//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "SecurityOptionsTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false,roles={"Security"})
@ActionID(category = "Window", id = "systems.tech247.security.SecurityOptionsTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_SecurityOptionsAction",
        preferredID = "SecurityOptionsTopComponent"
)
@Messages({
    "CTL_SecurityOptionsAction=Security Options",
    "CTL_SecurityOptionsTopComponent=Security Options",
    "HINT_SecurityOptionsTopComponent="
})
public class SecurityOptionsTopComponent extends TopComponent {
    HrsSecurityOptions hso = DataAccess.getSecurityOptions();
    HrsSecurityOptions updateable;
    InstanceContent content = new InstanceContent();
            


    
    public SecurityOptionsTopComponent() {
        initComponents();
        setName(Bundle.CTL_SecurityOptionsTopComponent());
        updateable = DataAccess.entityManager.find(HrsSecurityOptions.class, hso.getSecOptionID());
        
        jtMaxLoginAttempts.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try{
                    short max = Short.valueOf(jtMaxLoginAttempts.getText());
                    updateable.setMaxLogin(max);
                    modify();
                }catch(NumberFormatException ex){
                    //NotifyUtil.error("Only Numbers are expected", "Only Numbers are expected", ex, false);
                }
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try{
                    short max = Short.valueOf(jtMaxLoginAttempts.getText());
                    updateable.setMaxLogin(max);
                    modify();
                }catch(NumberFormatException ex){
                    //NotifyUtil.error("Only Numbers are expected", "Only Numbers are expected", ex, false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try{
                    short max = Short.valueOf(jtMaxLoginAttempts.getText());
                    updateable.setMaxLogin(max);
                    modify();
                }catch(NumberFormatException ex){
                    //NotifyUtil.error("Only Numbers are expected", "Only Numbers are expected", ex, false);
                }
            }
        });
        
        jtMinPasswordLength.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try{
                    short max = Short.valueOf(jtMinPasswordLength.getText());
                    updateable.setPswdMinLength(max);
                    modify();
                }catch(NumberFormatException ex){
                    //NotifyUtil.error("Only Numbers are expected", "Only Numbers are expected", ex, false);
                }//To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try{
                    short max = Short.valueOf(jtMinPasswordLength.getText());
                    updateable.setPswdMinLength(max);
                    modify();
                }catch(NumberFormatException ex){
                    //NotifyUtil.error("Only Numbers are expected", "Only Numbers are expected", ex, false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try{
                    short max = Short.valueOf(jtMinPasswordLength.getText());
                    updateable.setPswdMinLength(max);
                    modify();
                }catch(NumberFormatException ex){
                    //NotifyUtil.error("Only Numbers are expected", "Only Numbers are expected", ex, false);
                }
            }
        });
        
        jtPasswordMaxAge.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try{
                    short max = Short.valueOf(jtPasswordMaxAge.getText());
                    updateable.setPswdExpiry(max);
                    modify();
                }catch(NumberFormatException ex){
                    //NotifyUtil.error("Only Numbers are expected", "Only Numbers are expected", ex, false);
                } //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try{
                    short max = Short.valueOf(jtPasswordMaxAge.getText());
                    updateable.setPswdExpiry(max);
                    modify();
                }catch(NumberFormatException ex){
                    //NotifyUtil.error("Only Numbers are expected", "Only Numbers are expected", ex, false);
                } //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try{
                    short max = Short.valueOf(jtPasswordMaxAge.getText());
                    updateable.setPswdExpiry(max);
                    modify();
                }catch(NumberFormatException ex){
                    //NotifyUtil.error("Only Numbers are expected", "Only Numbers are expected", ex, false);
                }
            }
        });
        
        jtPasswordPromptDays.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try{
                    short max = Short.valueOf(jtPasswordPromptDays.getText());
                    updateable.setPswdPromptChange(max);
                    modify();
                }catch(NumberFormatException ex){
                    //NotifyUtil.error("Only Numbers are expected", "Only Numbers are expected", ex, false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try{
                    short max = Short.valueOf(jtPasswordPromptDays.getText());
                    updateable.setPswdPromptChange(max);
                    modify();
                }catch(NumberFormatException ex){
                    //NotifyUtil.error("Only Numbers are expected", "Only Numbers are expected", ex, false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try{
                    short max = Short.valueOf(jtPasswordPromptDays.getText());
                    updateable.setPswdPromptChange(max);
                    modify();
                }catch(NumberFormatException ex){
                    //NotifyUtil.error("Only Numbers are expected", "Only Numbers are expected", ex, false);
                }
            }
        });
        
       jtLockOutPeriod.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try{
                    short max = Short.valueOf(jtLockOutPeriod.getText());
                    updateable.setLockoutPeriod(max);
                    modify();
                }catch(NumberFormatException ex){
                    //NotifyUtil.error("Only Numbers are expected", "Only Numbers are expected", ex, false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try{
                    short max = Short.valueOf(jtLockOutPeriod.getText());
                    updateable.setLockoutPeriod(max);
                    modify();
                }catch(NumberFormatException ex){
                    //NotifyUtil.error("Only Numbers are expected", "Only Numbers are expected", ex, false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try{
                    short max = Short.valueOf(jtLockOutPeriod.getText());
                    updateable.setLockoutPeriod(max);
                    modify();
                }catch(NumberFormatException ex){
                    //NotifyUtil.error("Only Numbers are expected", "Only Numbers are expected", ex, false);
                }
            }
        });
       
       jTpasswordHistory.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try{
                    short max = Short.valueOf(jTpasswordHistory.getText());
                    updateable.setPswdHist(max);
                    modify();
                }catch(NumberFormatException ex){
                    //NotifyUtil.error("Only Numbers are expected", "Only Numbers are expected", ex, false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try{
                    short max = Short.valueOf(jTpasswordHistory.getText());
                    updateable.setPswdHist(max);
                    modify();
                }catch(NumberFormatException ex){
                    //NotifyUtil.error("Only Numbers are expected", "Only Numbers are expected", ex, false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try{
                    short max = Short.valueOf(jTpasswordHistory.getText());
                    updateable.setPswdHist(max);
                    modify();
                }catch(NumberFormatException ex){
                    //NotifyUtil.error("Only Numbers are expected", "Only Numbers are expected", ex, false);
                }
            }
        });
        
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
        jLabel1 = new javax.swing.JLabel();
        passwordComplexitySlider = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        jtPasswordMaxAge = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtPasswordPromptDays = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTpasswordHistory = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtLockOutPeriod = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtMinPasswordLength = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jtMaxLoginAttempts = new javax.swing.JTextField();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(SecurityOptionsTopComponent.class, "SecurityOptionsTopComponent.jPanel1.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(SecurityOptionsTopComponent.class, "SecurityOptionsTopComponent.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(SecurityOptionsTopComponent.class, "SecurityOptionsTopComponent.jLabel2.text")); // NOI18N

        jtPasswordMaxAge.setText(org.openide.util.NbBundle.getMessage(SecurityOptionsTopComponent.class, "SecurityOptionsTopComponent.jtPasswordMaxAge.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(SecurityOptionsTopComponent.class, "SecurityOptionsTopComponent.jLabel3.text")); // NOI18N

        jtPasswordPromptDays.setText(org.openide.util.NbBundle.getMessage(SecurityOptionsTopComponent.class, "SecurityOptionsTopComponent.jtPasswordPromptDays.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(SecurityOptionsTopComponent.class, "SecurityOptionsTopComponent.jLabel4.text")); // NOI18N

        jTpasswordHistory.setText(org.openide.util.NbBundle.getMessage(SecurityOptionsTopComponent.class, "SecurityOptionsTopComponent.jTpasswordHistory.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(SecurityOptionsTopComponent.class, "SecurityOptionsTopComponent.jLabel5.text")); // NOI18N

        jtLockOutPeriod.setText(org.openide.util.NbBundle.getMessage(SecurityOptionsTopComponent.class, "SecurityOptionsTopComponent.jtLockOutPeriod.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(SecurityOptionsTopComponent.class, "SecurityOptionsTopComponent.jLabel7.text")); // NOI18N

        jtMinPasswordLength.setText(org.openide.util.NbBundle.getMessage(SecurityOptionsTopComponent.class, "SecurityOptionsTopComponent.jtMinPasswordLength.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(SecurityOptionsTopComponent.class, "SecurityOptionsTopComponent.jLabel9.text")); // NOI18N

        jtMaxLoginAttempts.setText(org.openide.util.NbBundle.getMessage(SecurityOptionsTopComponent.class, "SecurityOptionsTopComponent.jtMaxLoginAttempts.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(passwordComplexitySlider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jtPasswordMaxAge)
                    .addComponent(jtPasswordPromptDays, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(jTpasswordHistory, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(jtLockOutPeriod, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(jtMinPasswordLength, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(jtMaxLoginAttempts, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addComponent(passwordComplexitySlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtPasswordMaxAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtPasswordPromptDays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTpasswordHistory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtMinPasswordLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtLockOutPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jtMaxLoginAttempts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

        @Override
    public void componentOpened() {
        fillFields();
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTpasswordHistory;
    private javax.swing.JTextField jtLockOutPeriod;
    private javax.swing.JTextField jtMaxLoginAttempts;
    private javax.swing.JTextField jtMinPasswordLength;
    private javax.swing.JTextField jtPasswordMaxAge;
    private javax.swing.JTextField jtPasswordPromptDays;
    private javax.swing.JSlider passwordComplexitySlider;
    // End of variables declaration//GEN-END:variables

    void fillFields(){
        if(hso!=null){
            
            jtPasswordMaxAge.setText(" "+hso.getPswdExpiry());
            jtPasswordPromptDays.setText(" "+hso.getPswdPromptChange());
            jTpasswordHistory.setText(" "+hso.getPswdHist());
            jtLockOutPeriod.setText(" "+hso.getLockoutPeriod());
            jtMinPasswordLength.setText(" "+hso.getPswdMinLength());
            jtMaxLoginAttempts.setText(" "+hso.getMaxLogin());
            
        }else{
            NotifyUtil.warn("Cannot Get The Options", "Cannot Get The Options", false);
        }
        
    }
    
    private class UserSavable extends AbstractSavable{

        public UserSavable() {
            register();
        }

        @Override
        protected void handleSave() throws IOException {
            
            if(updateable==null){//new User
                

            }else{//Updating a user
                DataAccess.entityManager.getTransaction().begin();
                DataAccess.entityManager.getTransaction().commit();
                
            }
            
            tc().close();
            
        }

        @Override
        protected String findDisplayName() {
            return "Security Options";
        }

        @Override
        public int hashCode() {
            return tc().hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof UserSavable){
                UserSavable e =(UserSavable)obj;
                return tc()==e.tc();
            }
            return false;
        }
        
        SecurityOptionsTopComponent tc(){
            return SecurityOptionsTopComponent.this;
        }
        
        
        
    }
    
    void  modify(){
        if(getLookup().lookup(UserSavable.class)==null){
            content.add(new UserSavable());
        }
    }



}
