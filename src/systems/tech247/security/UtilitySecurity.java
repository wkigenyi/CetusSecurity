/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.security;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Query;
import javax.swing.SwingUtilities;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.LifecycleManager;
import org.openide.NotifyDescriptor;
import org.openide.awt.StatusDisplayer;
import org.openide.explorer.ExplorerManager;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.HrsPasswordHistory;
import systems.tech247.hr.HrsSecurityOptions;
import systems.tech247.hr.HrsUsers;
import systems.tech247.menus.LaunchButton;
import systems.tech247.loginapi.LoginProvider;
import systems.tech247.util.CetusUTL;
import systems.tech247.util.MessageType;
import systems.tech247.util.NotifyUtil;

/**
 *
 * @author Wilfred
 */
@ServiceProvider(service=LoginProvider.class)
public class UtilitySecurity implements LoginProvider,Lookup.Provider{
    
    static HrsUsers user;
    static HrsSecurityOptions hso = DataAccess.getSecurityOptions();
    
    public static InstanceContent content= new InstanceContent();
    Lookup lookup = new AbstractLookup(content);
    
    private static UtilitySecurity instance = null;
    
    
   
    
    
    
    
    
               
    /*Security Setup*/
    public static InstanceContent editorSecurityIC = new InstanceContent();
    /* Users */
    
    

    
    
    /* Apps */
    public static ExplorerManager emSCRApps = new ExplorerManager();
    
    /* Audit Trail */
    public static ExplorerManager emSCRAuditTrail = new ExplorerManager();
    public static void loadAuditTrail(String sql){
        emSCRAuditTrail.setRootContext(new RootNodeAuditTrail(sql));
    }
    
    
    /* Modules */
    public static ExplorerManager emSCRModules = new ExplorerManager();
    public static void loadHRModulesRights(int groupID){
        emSCRModules.setRootContext(new RootNodeHRModules(groupID));
    }
    
    
    
    

    
    public static boolean comparePasswords(char[] newPass, char[] confPass) {
        //Dismiss Empty Passwords
        if( newPass.length== 0 || confPass.length==0){
            StatusDisplayer.getDefault().setStatusText("Empty Passwords, Password has not changed.");
            return false;
        }else if(!Arrays.equals(newPass,confPass)){
            StatusDisplayer.getDefault().setStatusText("New Passwords Do not Match");
            return false;
        }else if(newPass.length<DataAccess.getSecurityOptions().getPswdMinLength()){
            StatusDisplayer.getDefault().setStatusText("Password is too short, Must be atleast "+DataAccess.getSecurityOptions().getPswdMinLength()+" characters");
            return false;
        }else if(checkPasswordComplexity(newPass)<3){
            StatusDisplayer.getDefault().setStatusText("Password Not Complex enough");
            return false;
        }else{
            return true;    
        }
    }
    
    public static long getLastPasswordAge(HrsUsers user){
        try{
        long diff= (new Date()).getTime()-(getLastPasswordChange(user).getDateSet()).getTime();
        
        long age = diff/(1000*60*60*24);
        return age;
        }catch(NullPointerException ex){
            return new Long(0);
        }
    }
    
    public static HrsPasswordHistory getLastPasswordChange(HrsUsers user) {
        List<HrsPasswordHistory> hist = DataAccess.getPasswordHistory(user.getUserID());
        
        HrsPasswordHistory[] histArray = hist.toArray(new HrsPasswordHistory[hist.size()]);
        //If the the last password change is 0
        if(histArray.length>0){
            return histArray[hist.size()-1];
        }
        return null;
    }
    
    public static int checkPasswordComplexity(char[] password){
        String sPassword = new String(password);
        int metConditions =0;
        Pattern[] patterns;
        
        //contains Number(s)
        Pattern numberPattern = Pattern.compile(".*\\d.*");
        //contains lower case
        Pattern lowercasePattern = Pattern.compile(".*[a-z].*");
        //contains upper case
        Pattern upperPattern = Pattern.compile(".*[A-Z].*");
        //contains special characters
        Pattern specialPattern = Pattern.compile(".*[@#$%!].*");
        //Is between 8-40 characters long
        Pattern lengthCondition = Pattern.compile(".{8,40}");
        
        patterns = new Pattern[]{numberPattern,lowercasePattern,upperPattern,specialPattern,lengthCondition};
        
        for(int i=0; i<= patterns.length-1; i++){
            Matcher matcher = patterns[i].matcher(sPassword);
            if(matcher.matches()){
                metConditions++;
                
            }
        }
        
        
        return metConditions;
    }
    
    /**
     * @param pHistoy the pHistoy to set
     */
    

    
    /**
     * @param user
     * @return the passwordExpiryWarning
     */
    public static boolean isPasswordExpired(HrsUsers user) {
        return getLastPasswordAge(user)>= hso.getPswdExpiry();
    }
    
    public static void showPasswordWarning(final HrsUsers user){
        
        if(isPasswordExpired(user)){
            NotifyUtil.show("Password Expired"," Your Password is expired, Click here to change it.",MessageType.WARNING ,new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showPasswordPrompt(user);
                }
            },false);
        }
    }
    
    public static void showPasswordPrompt(HrsUsers user){
        
        PasswordChanger changer = new PasswordChanger(user);
        if(isPasswordExpired(user)){
            changer.presentExpiredPasswordScreen(user);
        }else{
            changer.presentPasswordScreen(user);
            
        }
    }
    
    public static char[] encryptPasswordMD5(char[] enteredPassword) throws NoSuchAlgorithmException{
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String sEnteredPassword = new String(enteredPassword);
        byte[] tmp = sEnteredPassword.getBytes();
        md5.update(tmp);
        char[] enteredPasswordEncr = byteArrToString(md5.digest()).toCharArray();
        return enteredPasswordEncr;
    }
    
    private static String byteArrToString(byte[] b){ 
        String res = null; 
        StringBuffer sb = new StringBuffer(b.length * 2); 
        for (int i = 0; i < b.length; i++){ 
            int j = b[i] & 0xff; 
            if (j < 16) { 
                sb.append('0'); 
            } 
            sb.append(Integer.toHexString(j)); 
        } 
        res = sb.toString(); 
        return res;
    }
    
    public void chooseModule(){
        
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                
                //ModulePanel md = new ModulePanel();
       TopComponent tc = WindowManager.getDefault().findTopComponent("AppMenuTopComponent");
                try {
                    loadAuthorisedApps();
                } catch (NoSuchAlgorithmException ex) {
                    Exceptions.printStackTrace(ex);
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
        NotifyDescriptor nd = new NotifyDescriptor(tc, "Choose An Application", NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE, new Object[]{LaunchButton.getInstance(),NotifyDescriptor.CANCEL_OPTION}, null);
        nd.setNoDefaultClose(true);
        DialogDisplayer.getDefault().notifyLater(nd);
                
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        
    }
    /* Users */
    
    public void loadAuthorisedApps() throws NoSuchAlgorithmException, IOException{
        requireLogin();
        
        
        emSCRApps.setRootContext(new AbstractNode(Children.create(new ChildFactoryHRModuleApp(getInstance().getLoginDetail().getUser().getGroupID().getGroupID()), true)));
    }

public static HrsUsers requireLogin() throws NoSuchAlgorithmException, IOException{
        short i=1;
        
        while(null==user && i<hso.getMaxLogin()){
            //System.out.println(i+", Login Attempt");
            showLoginScreen();
            i+=1;
        }
        if(i==hso.getMaxLogin()){
            //Lock the account
            Object result=DialogDisplayer.getDefault().notify(new DialogDescriptor("Maximum Login Attempts have been reached", "Account Locked"));
            if(result==NotifyDescriptor.OK_OPTION){
                LifecycleManager.getDefault().exit();
            }
        }
        return user;
    }

public static UtilitySecurity getInstance() {
        if(instance==null){
            instance=new UtilitySecurity();
        }
        return instance;
    }

public static HrsUsers showLoginScreen() throws NoSuchAlgorithmException, IOException{
        LoginDisplayer ld = new LoginDisplayer();
        user=ld.presentLoginScreen();
        return user;
    }


    
    public static boolean compareMD5Passwords(char[] storedEncrPassword, char[] enteredPassword) throws NoSuchAlgorithmException{        
        return Arrays.equals(storedEncrPassword,encryptPasswordMD5(enteredPassword));
    }
    

    
  public static void updateHistory(HrsUsers user){
        String sqlString = 
                "UPDATE hrsLoginHistory " +
                "SET LogOffTime = CURRENT_TIMESTAMP,IsOnLine=0 " +
                "WHERE LoginHistoryID = (SELECT TOP 1 LoginHistoryID FROM hrsLoginHistory WHERE UserID='"+user.getUserID()+"' ORDER BY LoginHistoryID DESC )";
        
        
                Query q = DataAccess.entityManager.createNativeQuery(sqlString);
                DataAccess.entityManager.getTransaction().begin();
                q.executeUpdate();
                DataAccess.entityManager.getTransaction().commit();
                //DialogDisplayer.getDefault().notifyLater(new NotifyDescriptor.Message("Update is: "+ i));
                
    }
  
  public static void setChosenModuleID(final int chosenModuleID) {
      final WindowManager wm = WindowManager.getDefault();
        switch(chosenModuleID){
            case 13:
                
              //wm.setRole("Security");
                 TopComponent tc13 = WindowManager.getDefault().findTopComponent("SecurityDashBoardTopComponent");
                tc13.open();
         
                
                break;
            case 1:
                
              //wm.setRole("Personnel");
                TopComponent tc1 = WindowManager.getDefault().findTopComponent("PersonnelDashboardTopComponent");
                tc1.open();
          
                
                break;
            case 2:
                
              //wm.setRole("leave");
                TopComponent tc2 = wm.findTopComponent("DashBoardTopComponent");
                tc2.open();
                
                
                break;
            case 3:
                //wm.setRole("Payroll");
                TopComponent tc3 = wm.findTopComponent("PayrollDashBoardTopComponent");
                tc3.open();
                break;
            case 10:
                //wm.setRole("ta");
                TopComponent tc4 = wm.findTopComponent("TADashBoardTopComponent");
                tc4.open();
                break;    
            default:
                wm.setRole(null);
                break;
        }
        
        /*RequestProcessor.getDefault().post(new Runnable() {
          @Override
          public void run() {
              
                HrsUsers user = getInstance().getLoginDetail().getUser();
                DataAccess.updateLoginModule(user.getUserID(),chosenModuleID);
          }
      }
        );*/
                
        
        
    }
  
   
  
  

  

  
  
  
  /**
     * @return the passwordWarning
     */
    public  boolean isPasswordWarning(HrsUsers user) {
     return   DataAccess.getSecurityOptions().getPswdExpiry()-getLastPasswordAge(user)<=DataAccess.getSecurityOptions().getPswdPromptChange();
            
        
        
    }
    
    public long daysToExpiry(HrsUsers user){
        return DataAccess.getSecurityOptions().getPswdExpiry()-getLastPasswordAge(user);
    }
    
    public boolean checkOldPassword(char[] oldPass) throws NoSuchAlgorithmException{
        if(!UtilitySecurity.compareMD5Passwords(getInstance().getLoginDetail().getUser().getPassword().toCharArray(),oldPass)){
            return false;
        }else{
            return true;
        }
    }

    /**
     * @return the userDescription
     */
    public String getUserDescription() {
        return getInstance().getLoginDetail().getUser().getFullNames();
    }

    @Override
    public LoginDetail getLoginDetail() {
        InetAddress localMachine = null;
        while(user==null){
            
            try {
                user=requireLogin();
                
            } catch (NoSuchAlgorithmException | IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        try {
            localMachine = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            Exceptions.printStackTrace(ex);
        }
        CetusUTL.content.add(user);
        //Save the login
        //DataAccess.saveLogin(user.getUserID(), localMachine.getHostName());
        return new LoginDetail(user, localMachine.getHostName());
    }

    @Override
    public Lookup getLookup() {
        return lookup;
    }

    

    

  
  
  
  
  
  


    
    
    
    
    
    
    
    
    
    
    
    

}
