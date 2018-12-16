/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.security;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.swing.JButton;
import org.openide.DialogDisplayer;
import org.openide.LifecycleManager;
import org.openide.NotifyDescriptor;
import org.openide.awt.StatusDisplayer;
import org.openide.util.Exceptions;
import systems.tech247.dbaccess.DataAccess;




import systems.tech247.hr.HrsSecurityOptions;
import systems.tech247.hr.HrsUsers;


/**
 *
 * @author WKigenyi
 */
public class PasswordChanger {
    JButton change = new JButton("Change Password");
    JButton cancel = new JButton("Exit");
    PasswordPanel passwordChangeForm = new PasswordPanel();
    
    
    HrsSecurityOptions options =  DataAccess.getSecurityOptions();

    public PasswordChanger(final HrsUsers user) {
        
        
        
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    if(!comparePasswords()){
                        presentPasswordScreen(user);
                    }else{
                        //Save the password
                        
                        //Get the Password
                        String password = new String(UtilitySecurity.encryptPasswordMD5(passwordChangeForm.getConfirmPassword()));
                        //da.getEntityManagerHR().getTransaction().begin();
                       
                        //save the password
                        DataAccess.savePassword(user, password);
                        
                        
                        
                        
                        
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
    
    public boolean comparePasswords() throws NoSuchAlgorithmException{
        //Dismiss Empty Passwords
        if(passwordChangeForm.getOldPassword().length==0 
                        || passwordChangeForm.getNewPassword().length== 0
                        || passwordChangeForm.getConfirmPassword().length==0){
                  StatusDisplayer.getDefault().setStatusText("Empty Passwords, Password has not changed.");
                  return false;
                }
                
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            else if(!Arrays.equals(passwordChangeForm.getNewPassword(),passwordChangeForm.getConfirmPassword())){
                   StatusDisplayer.getDefault().setStatusText("New Passwords Do not Match");
                   return false;
            }else if(!UtilitySecurity.getInstance().checkOldPassword(passwordChangeForm.getOldPassword())){
                   StatusDisplayer.getDefault().setStatusText("Incorrect Password");
                   return false;
            }else if(passwordChangeForm.getNewPassword().length<options.getPswdMinLength()){
                StatusDisplayer.getDefault().setStatusText("Password is too short, Must be atleast "+options.getPswdMinLength()+" characters");
                return false;
            }else if(UtilitySecurity.checkPasswordComplexity(passwordChangeForm.getNewPassword())<3){
                StatusDisplayer.getDefault().setStatusText("Password Not Complex enough");
                return false;
            }else{
                
                return true;
                
            }
        
        
        
    }
    
    
    
    
    public void presentPasswordScreen(HrsUsers user){
        
        passwordChangeForm.resetFields();
            NotifyDescriptor nd = new NotifyDescriptor(
                passwordChangeForm,
                "Change Your Password",
                NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE,
                new Object[]{change,cancel},
                null
            );
            nd.setNoDefaultClose(false);
            DialogDisplayer.getDefault().notifyLater(nd);
        
        
    }

public void presentExpiredPasswordScreen(HrsUsers user){
        
        passwordChangeForm.resetFields();
            NotifyDescriptor nd = new NotifyDescriptor(
                passwordChangeForm,
                "Password Expired, Must be changed",
                NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE,
                new Object[]{change,cancel},
                null
            );
    nd.setNoDefaultClose(true);
        
            DialogDisplayer.getDefault().notifyLater(nd);
        
        
    }



}
