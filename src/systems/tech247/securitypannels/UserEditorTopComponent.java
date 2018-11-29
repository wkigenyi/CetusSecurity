/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.securitypannels;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.persistence.Query;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.spi.actions.AbstractSavable;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.StatusDisplayer;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.WindowManager;
import systems.tech247.dbaccess.DataAccess;
import static systems.tech247.dbaccess.DataAccess.entityManager;
import systems.tech247.hr.Employees;
import systems.tech247.hr.HrsGroups;
import systems.tech247.hr.HrsSecurityOptions;
import systems.tech247.hr.HrsUsers;
import systems.tech247.security.RefreshUsersEvent;
import systems.tech247.security.UtilitySecurity;
import systems.tech247.util.NodeRefreshProvider;
import systems.tech247.util.NotifyUtil;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.securitypannels//UserEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "UserEditorTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "systems.tech247.securitypannels.UserEditorTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_UserEditorAction",
        preferredID = "UserEditorTopComponent"
)
@Messages({
    "CTL_UserEditorAction=User Editor",
    "CTL_UserEditorTopComponent=User Editor",
    "HINT_UserEditorTopComponent="
})
public final class UserEditorTopComponent extends TopComponent implements LookupListener {
    InstanceContent content = new InstanceContent();
    HrsUsers updateable;
    HrsUsers user;
    Boolean accountLocked=false;
    Boolean mustChangePass = true;
    Boolean cannotChangePass =false;
    Boolean passwordNotExpire = false;
    Boolean accountDisabled=false;
    HrsGroups group;
    Employees employee;
    char[] pass1;
    char[] pass2;
    String username="";
    String encodedPassword;
    String fullName="";
    TopComponent employeeTC = WindowManager.getDefault().findTopComponent("EmployeesTopComponent");
    TopComponent rolesTC = WindowManager.getDefault().findTopComponent("RolesTopComponent");
    Lookup.Result<Employees> empRslt = employeeTC.getLookup().lookupResult(Employees.class);
    Lookup.Result<HrsGroups> groupRslt = rolesTC.getLookup().lookupResult(HrsGroups.class);
    HrsSecurityOptions options = DataAccess.getSecurityOptions();
    
    public UserEditorTopComponent(){
        this(null);
    }
    public UserEditorTopComponent(HrsUsers user) {
        initComponents();
        setName(Bundle.CTL_UserEditorTopComponent());
        setToolTipText(Bundle.HINT_UserEditorTopComponent());
        this.user = user;
        groupRslt.addLookupListener(this);
        empRslt.addLookupListener(this);
        try{
            updateable = DataAccess.entityManager.find(HrsUsers.class, user.getUserID());
        }catch(NullPointerException ex){
            
        }
        fillFields();
        
        jPpass1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                preparePassword();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                preparePassword();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                preparePassword();
            }
        });
        jPpass2.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                preparePassword();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                preparePassword();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                preparePassword();
            }
        });
        jtUserName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                username=jtUserName.getText();
                modify();
                        
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                username=jtUserName.getText();
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                username=jtUserName.getText();
                modify();
            }
        });
        
        jtFullNames.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fullName=jtFullNames.getText();
                try{
                    updateable.setFullNames(fullName);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fullName=jtFullNames.getText();
                try{
                    updateable.setFullNames(fullName);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                fullName=jtFullNames.getText();
                try{
                    updateable.setFullNames(fullName);
                }catch(NullPointerException ex){
                    
                }
                modify();
                        
            }
        });
                
        

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jcbAccountLocked = new javax.swing.JCheckBox();
        jcbAccountDisabled = new javax.swing.JCheckBox();
        jcbUserCantChangePass = new javax.swing.JCheckBox();
        jcbPasswordDoesNotExpire = new javax.swing.JCheckBox();
        jcbChangePasswordOnNextLogin = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jtUserName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtGroup = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPpass1 = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jPpass2 = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jdcLastLogin = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jdcLockoutDate = new com.toedter.calendar.JDateChooser();
        jcbUserIsEmployee = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jtFullNames = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jcbAccountLocked, org.openide.util.NbBundle.getMessage(UserEditorTopComponent.class, "UserEditorTopComponent.jcbAccountLocked.text")); // NOI18N
        jcbAccountLocked.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbAccountLockedActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbAccountDisabled, org.openide.util.NbBundle.getMessage(UserEditorTopComponent.class, "UserEditorTopComponent.jcbAccountDisabled.text")); // NOI18N
        jcbAccountDisabled.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbAccountDisabledActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbUserCantChangePass, org.openide.util.NbBundle.getMessage(UserEditorTopComponent.class, "UserEditorTopComponent.jcbUserCantChangePass.text")); // NOI18N
        jcbUserCantChangePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbUserCantChangePassActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbPasswordDoesNotExpire, org.openide.util.NbBundle.getMessage(UserEditorTopComponent.class, "UserEditorTopComponent.jcbPasswordDoesNotExpire.text")); // NOI18N
        jcbPasswordDoesNotExpire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbPasswordDoesNotExpireActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbChangePasswordOnNextLogin, org.openide.util.NbBundle.getMessage(UserEditorTopComponent.class, "UserEditorTopComponent.jcbChangePasswordOnNextLogin.text")); // NOI18N
        jcbChangePasswordOnNextLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbChangePasswordOnNextLoginActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(UserEditorTopComponent.class, "UserEditorTopComponent.jLabel1.text")); // NOI18N

        jtUserName.setText(org.openide.util.NbBundle.getMessage(UserEditorTopComponent.class, "UserEditorTopComponent.jtUserName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(UserEditorTopComponent.class, "UserEditorTopComponent.jLabel2.text")); // NOI18N

        jtGroup.setText(org.openide.util.NbBundle.getMessage(UserEditorTopComponent.class, "UserEditorTopComponent.jtGroup.text")); // NOI18N
        jtGroup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtGroupKeyTyped(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(UserEditorTopComponent.class, "UserEditorTopComponent.jLabel3.text")); // NOI18N

        jPpass1.setText(org.openide.util.NbBundle.getMessage(UserEditorTopComponent.class, "UserEditorTopComponent.jPpass1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(UserEditorTopComponent.class, "UserEditorTopComponent.jLabel4.text_3")); // NOI18N

        jPpass2.setText(org.openide.util.NbBundle.getMessage(UserEditorTopComponent.class, "UserEditorTopComponent.jPpass2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(UserEditorTopComponent.class, "UserEditorTopComponent.jLabel5.text_3")); // NOI18N

        jdcLastLogin.setDateFormatString(org.openide.util.NbBundle.getMessage(UserEditorTopComponent.class, "UserEditorTopComponent.jdcLastLogin.dateFormatString")); // NOI18N
        jdcLastLogin.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(UserEditorTopComponent.class, "UserEditorTopComponent.jLabel6.text_3")); // NOI18N

        jdcLockoutDate.setDateFormatString(org.openide.util.NbBundle.getMessage(UserEditorTopComponent.class, "UserEditorTopComponent.jdcLockoutDate.dateFormatString")); // NOI18N
        jdcLockoutDate.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(jcbUserIsEmployee, org.openide.util.NbBundle.getMessage(UserEditorTopComponent.class, "UserEditorTopComponent.jcbUserIsEmployee.text")); // NOI18N
        jcbUserIsEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbUserIsEmployeeActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(UserEditorTopComponent.class, "UserEditorTopComponent.jLabel7.text_1")); // NOI18N

        jtFullNames.setText(org.openide.util.NbBundle.getMessage(UserEditorTopComponent.class, "UserEditorTopComponent.jtFullNames.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtUserName)
                            .addComponent(jtGroup, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                            .addComponent(jPpass1)
                            .addComponent(jPpass2)
                            .addComponent(jdcLastLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jdcLockoutDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtFullNames))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcbUserIsEmployee))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbChangePasswordOnNextLogin)
                            .addComponent(jcbPasswordDoesNotExpire)
                            .addComponent(jcbUserCantChangePass)
                            .addComponent(jcbAccountDisabled)
                            .addComponent(jcbAccountLocked))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbUserIsEmployee))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtFullNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jPpass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jPpass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jdcLastLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jdcLockoutDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcbAccountLocked)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbChangePasswordOnNextLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbPasswordDoesNotExpire)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbUserCantChangePass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbAccountDisabled)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jcbAccountLockedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbAccountLockedActionPerformed
        accountLocked=jcbAccountLocked.isSelected();
        try{
            updateable.setAccLocked(accountLocked);
        }catch(NullPointerException ex){
            
        }
        modify();
    }//GEN-LAST:event_jcbAccountLockedActionPerformed

    private void jcbAccountDisabledActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbAccountDisabledActionPerformed
        accountDisabled=jcbAccountDisabled.isSelected();
        try{
            updateable.setAccDisabled(accountDisabled);
        }catch(NullPointerException ex){
            
        }
        modify();
    }//GEN-LAST:event_jcbAccountDisabledActionPerformed

    private void jcbChangePasswordOnNextLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbChangePasswordOnNextLoginActionPerformed
        mustChangePass=jcbChangePasswordOnNextLogin.isSelected();
        try{
            updateable.setMustChangePwd(mustChangePass);
        }catch(NullPointerException ex){
            
        }
        modify();
    }//GEN-LAST:event_jcbChangePasswordOnNextLoginActionPerformed

    private void jcbPasswordDoesNotExpireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbPasswordDoesNotExpireActionPerformed
        passwordNotExpire=jcbPasswordDoesNotExpire.isSelected();
        try{
            updateable.setPwdNeverExp(passwordNotExpire);
        }catch(NullPointerException ex){
            
        }
        modify();
    }//GEN-LAST:event_jcbPasswordDoesNotExpireActionPerformed

    private void jcbUserCantChangePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbUserCantChangePassActionPerformed
        cannotChangePass=jcbUserCantChangePass.isSelected();
        try{
            updateable.setCantChangePwd(cannotChangePass);
        }catch(NullPointerException ex){
            
        }
        modify();
    }//GEN-LAST:event_jcbUserCantChangePassActionPerformed

    private void jtGroupKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtGroupKeyTyped
        DialogDisplayer.getDefault().notify(new DialogDescriptor(rolesTC, "Select A Role"));
    }//GEN-LAST:event_jtGroupKeyTyped

    private void jcbUserIsEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbUserIsEmployeeActionPerformed
        if(jcbUserIsEmployee.isSelected()){
            DialogDisplayer.getDefault().notify(new DialogDescriptor(employeeTC, "Select A Role"));
        }
    }//GEN-LAST:event_jcbUserIsEmployeeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPasswordField jPpass1;
    private javax.swing.JPasswordField jPpass2;
    private javax.swing.JCheckBox jcbAccountDisabled;
    private javax.swing.JCheckBox jcbAccountLocked;
    private javax.swing.JCheckBox jcbChangePasswordOnNextLogin;
    private javax.swing.JCheckBox jcbPasswordDoesNotExpire;
    private javax.swing.JCheckBox jcbUserCantChangePass;
    private javax.swing.JCheckBox jcbUserIsEmployee;
    private com.toedter.calendar.JDateChooser jdcLastLogin;
    private com.toedter.calendar.JDateChooser jdcLockoutDate;
    private javax.swing.JTextField jtFullNames;
    private javax.swing.JTextField jtGroup;
    private javax.swing.JTextField jtUserName;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
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
    
    private class UserSavable extends AbstractSavable{

        public UserSavable() {
            register();
        }

        @Override
        protected void handleSave() throws IOException {
            
            if(updateable==null){//new User
                
                                        String insertSQL = "INSERT INTO [dbo].[hrsUsers]\n" +
"           ([UserName]\n" +
"           ,[Password]\n" +
"           ,[GroupID]\n" +
"           ,[EmployeeID]\n" +
"           ,[FullNames]\n" +
"           ,[Deleted]\n" +
"           ,[MustChangePwd]\n" +
"           ,[PwdNeverExp]\n" +
"           ,[CantChangePwd]\n" +
"           ,[AccDisabled]\n" +
"           ,[AccLocked]\n" +
"           ,[LockoutDate])\n" +
"     VALUES\n" +
"           (?,?,?,?,?,?,?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(1,username);
            query.setParameter(2,encodedPassword);
            query.setParameter(3,group.getGroupID());
            if(employee!=null){
                query.setParameter(4,employee.getEmployeeID());
            }else{
                query.setParameter(4,0);
            }
            
            query.setParameter(5,fullName);
            query.setParameter(6,0);
            query.setParameter(7,mustChangePass);
            query.setParameter(8,passwordNotExpire);
            query.setParameter(9,cannotChangePass);
            query.setParameter(10,accountDisabled);
            query.setParameter(11,accountLocked);
                DataAccess.entityManager.getTransaction().begin();
                query.executeUpdate();
                DataAccess.entityManager.getTransaction().commit();
            }else{//Updating a user
                DataAccess.entityManager.getTransaction().begin();
                DataAccess.entityManager.getTransaction().commit();
                
            }
            NodeRefreshProvider.content.set(Arrays.asList(new RefreshUsersEvent()), null);
            tc().close();
            
        }

        @Override
        protected String findDisplayName() {
            return "User";
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
        
        UserEditorTopComponent tc(){
            return UserEditorTopComponent.this;
        }
        
        
        
    }
    
    void modify(){
        //New Users
        if(updateable==null){
            if(username.equals("")){
                StatusDisplayer.getDefault().setStatusText("Specify a username");
            }else if(fullName.equals("")){
                StatusDisplayer.getDefault().setStatusText("Specify The Name For The User");
            }else if(null==encodedPassword){
                StatusDisplayer.getDefault().setStatusText("Specify A Password");
            }else if(group==null){
                StatusDisplayer.getDefault().setStatusText("Specify A Group");
            }else{
                if(getLookup().lookup(UserSavable.class)==null){
                    content.add(new UserSavable());
                }
            }    
        }else{ //updating user
            if(updateable.getFullNames().length()>=4){
                if(getLookup().lookup(UserSavable.class)==null){
                    content.add(new UserSavable());
                }
            }
        }
        
    }
    
    void fillFields(){
        if(user!=null){
            jtUserName.setText(user.getUserName());
            jtFullNames.setText(user.getFullNames());
            jtGroup.setText(user.getGroupID().getGroupName());
            jdcLastLogin.setDate(DataAccess.getLastLoginDate(user.getUserID()));
            try{
                jdcLockoutDate.setDate(user.getLockoutDate());
            }catch(NullPointerException ex){
                
            }
            jcbAccountDisabled.setSelected(user.getAccDisabled());
            jcbAccountLocked.setSelected(user.getAccLocked());
            jcbChangePasswordOnNextLogin.setSelected(user.getMustChangePwd());
            jcbPasswordDoesNotExpire.setSelected(user.getPwdNeverExp());
            jcbUserCantChangePass.setSelected(user.getCantChangePwd());
            jcbUserIsEmployee.setEnabled(false);
            jtUserName.setEditable(false);
            if(user.getEmployeeID()>0){
                jcbUserIsEmployee.setSelected(true);
            }
                    
        }
    }

    @Override
    public void resultChanged(LookupEvent ev) {
        Lookup.Result rslt = (Lookup.Result)ev.getSource();
        for(Object obj: rslt.allInstances()){
            if(obj instanceof HrsGroups){
                group = (HrsGroups)obj;
                jtGroup.setText(group.getGroupName());
                try{
                    updateable.setGroupID(group);
                }catch(NullPointerException ex){
                    
                }
                modify();
                
            }else if(obj instanceof Employees){
                employee = (Employees)obj;
                username = computeUserName(employee);
                jtUserName.setText(username);
                jtFullNames.setText(employee.getSurName()+" "+employee.getOtherNames());
                modify();
                
            }
            //NotifyUtil.info("We saw something", obj.getClass().getName(), false);
        }
        
    }
    
    void preparePassword(){
        pass2=jPpass2.getPassword();
        pass1=jPpass1.getPassword();
        
        if(!Arrays.equals(pass1, pass2)){
            StatusDisplayer.getDefault().setStatusText("Passwords are not the same");
        }else if(pass1.length<options.getPswdMinLength()){
            StatusDisplayer.getDefault().setStatusText("Password must be atleast "+options.getPswdMinLength()+" characters");
        }else{
            try{
                encodedPassword = new String(UtilitySecurity.encryptPasswordMD5(pass1));
                updateable.setPassword(encodedPassword);
                
            }catch(NullPointerException ex){
                        
            } catch (NoSuchAlgorithmException ex) {
                NotifyUtil.error("Cannot Encrypt Password", "Unable to encrypt password", ex, false);
            }
            modify();
        }    
    }
    
    String computeUserName(Employees emp){
        String lName = emp.getSurName();
        String fName = emp.getOtherNames();
        String computedUsername;
                      
        if (fName.contains(" ")){
            int spaceIndex = fName.indexOf(" ");
            fName = fName.substring(0, spaceIndex);
        }
                    
        if (lName.contains(" ")){
            int spaceIndex = lName.indexOf(" ");
            lName = lName.substring(0, spaceIndex);            
        }
        computedUsername = fName.substring(0, 1)+lName;
                        
                    
        return computedUsername;               
        
    }
    
}
