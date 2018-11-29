/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.security;

import javax.swing.JLabel;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import systems.tech247.hr.HrsUsers;
import systems.tech247.loginapi.UserProvider;

/**
 *
 * @author Admin
 */
public class LoginLabel extends JLabel implements LookupListener{
    
    Lookup.Result<UserProvider> result;
    
    HrsUsers user;

    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result<UserProvider> results = (Lookup.Result<UserProvider>)le.getSource();
        
        for(UserProvider users : results.allInstances()){
            
            user = users.getUser();
            this.setText(user.getFullNames());
            
        }
    }
    
    public LoginLabel(){
        result = Lookup.getDefault().lookupResult(UserProvider.class);
        result.addLookupListener(this);
        resultChanged(new LookupEvent(result));
    }
    
}
