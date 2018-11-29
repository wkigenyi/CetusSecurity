/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.security;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.LifecycleManager;
import org.openide.NotifyDescriptor;
import org.openide.util.Exceptions;
import org.openide.util.lookup.InstanceContent;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.HrsSecurityOptions;


import systems.tech247.hr.HrsUsers;
import systems.tech247.util.MessageType;
import systems.tech247.util.NotifyUtil;





/**
 *
 * @author WKigenyi
 */
public class LoginDisplayer {
    
    InstanceContent ic = new InstanceContent();
   
    static List userLoginAttempts= new ArrayList();
    HrsSecurityOptions options = DataAccess.getSecurityOptions();
    HrsUsers user;
    
    static LoginDetail loginDetail;
    
    static LoginDisplayer instance;
    
    JButton login = new JButton("Login");
    JButton cancel = new JButton("Exit");

    public static LoginDisplayer getInstance() {
        if(instance==null){
            instance = new LoginDisplayer();
        }
        return instance;
    }
    
    
    
    
    
    LoginPanel loginForm = new LoginPanel();
    HrsUsers userFromDB = null;
    //DataAccess da = DataAccess.getInstance();
    
    public LoginDisplayer(){
        login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                    try {
                        try {
                            userFromDB=authenticate();
                        } catch (IOException ex) {
                            Exceptions.printStackTrace(ex);
                        }
                    } catch (NoSuchAlgorithmException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                
            }
        });
        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LifecycleManager.getDefault().exit();
            }
        });
        

    }
    
    public HrsUsers authenticate() throws UnknownHostException, NoSuchAlgorithmException, IOException{
        //verifyDBconn();
        //If the username entered has a match in database
        String usernameFromForm = this.loginForm.getUsername();
        char[] passwordFromFrom = this.loginForm.getPassword();
        
        userFromDB = DataAccess.getUser(usernameFromForm, new String(UtilitySecurity.encryptPasswordMD5(passwordFromFrom)));
        
        if(userFromDB != null /*User Exists*/){
            
            if(userFromDB.getAccLocked()){
                
                //Check the time the user was locked
                Date locktime = userFromDB.getLockoutDate();
                Calendar cal = Calendar.getInstance();
                cal.setTime(locktime);
                cal.add(Calendar.MINUTE, options.getLockoutPeriod());
                Date releaseTime = cal.getTime();
                Date now = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                if(now.after(releaseTime)){
                    System.out.println("Access Granted");
                    
                    
                }else{
                    NotifyDescriptor nd = new DialogDescriptor("Your Account is locked until "+sdf.format(releaseTime)+"\nSee your Administrator.", "Account Locked");
                    nd.setNoDefaultClose(true);
                    Object result=DialogDisplayer.getDefault().notify(nd);
                    if(result==NotifyDescriptor.OK_OPTION){
                        LifecycleManager.getDefault().exit();
                    }    
                }
                
                
                
                
                
                
                
                
                
            }else if(userFromDB.getMustChangePwd()){
                NotifyUtil.show("Change Your Password", "Your Password was Generated By Your Adminstrator, Change it.", MessageType.WARNING, new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        UtilitySecurity.showPasswordPrompt(userFromDB);
                    }
                }, false);
                
            }else if(UtilitySecurity.isPasswordExpired(userFromDB)){
                DialogDisplayer.getDefault().notify(new DialogDescriptor("Your Password Expired", "Change Your Password"));
                UtilitySecurity.showPasswordPrompt(user);
            }        
                     
        }else{
                HrsUsers currentAttempt = DataAccess.getUserByUserName(usernameFromForm);
                if(null!=currentAttempt){
                    userLoginAttempts.add(currentAttempt);
                    
                }
                presentLoginWithErrorMessage(currentAttempt);
                
            
            
        }
        
        return userFromDB;
    }
    
    
    
    
    
    private void presentLoginWithErrorMessage(HrsUsers currentAttempt){
        int maximumAttempts = options.getMaxLogin();
        if(null!=currentAttempt){
            DataAccess.saveAuditTrail(currentAttempt, "Wrong Password");
            if(listContains(userLoginAttempts, currentAttempt, maximumAttempts)){
                //Lockup The User
                HrsUsers user = DataAccess.entityManager.find(HrsUsers.class, currentAttempt.getUserID());
                user.setAccLocked(true);
                user.setLockoutDate(new Date());
                DataAccess.entityManager.getTransaction().begin();
                DataAccess.entityManager.getTransaction().commit();
                
                NotifyDescriptor nd = new NotifyDescriptor(
                            "This account has been locked\nAfter "+options.getMaxLogin()+" Failed Login Attempts.",
                            "Account Locked",
                            NotifyDescriptor.OK_CANCEL_OPTION,
                            NotifyDescriptor.PLAIN_MESSAGE,
                            new Object[]{cancel},
                            null
                    );
                nd.setNoDefaultClose(true);
                DialogDisplayer.getDefault().notify(nd);
            }
        }
        
        
        
        loginForm.resetFields();
            NotifyDescriptor nd = new NotifyDescriptor(
                loginForm,
                "Incorrect Login, Try Again",
                NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE,
                new Object[]{login,cancel},
                null
            );
            nd.setNoDefaultClose(true);
            
        
            DialogDisplayer.getDefault().notify(nd);
        
        
    }
    

    
    public HrsUsers presentLoginScreen() throws NoSuchAlgorithmException, IOException{
        
        loginForm.resetFields();
            NotifyDescriptor nd = new NotifyDescriptor(
                loginForm,
                "Cetus System Login",
                NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE,
                new Object[]{login,cancel},
                null
            );
            nd.setNoDefaultClose(true);
        
            DialogDisplayer.getDefault().notify(nd);
            return authenticate();
        
        
    }
    
        boolean listContains(List l,Object o,int n){
            boolean result = false;
            List copy= new ArrayList();
            copy.addAll(l);
            int i=0;
            while(i<=n){
                if(copy.remove(o)){
                    i+=1;  
                }else{
                    break;
                }
        }
        if(i==n)
            result=true;
        return result;
    }
    

    
    





    
    
    
    
    
}