/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.security;

import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.modules.ModuleInstall;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponentGroup;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.HrsGroupPolicies;
import systems.tech247.hr.HrsGroups;
import systems.tech247.hr.HrsUsers;
import systems.tech247.util.CetusUTL;

public class Installer extends ModuleInstall implements LookupListener{
    Lookup.Result<HrsUsers> rslt = CetusUTL.getInstance().getLookup().lookupResult(HrsUsers.class);
    HrsUsers user;

    @Override
    public void resultChanged(LookupEvent ev) {
        Lookup.Result<HrsUsers> rslt = (Lookup.Result<HrsUsers>)ev.getSource();
        for(HrsUsers u: rslt.allInstances()){
            user = u;
        }
    }
    
   
    @Override
    public void restored() {
        System.setProperty("netbeans.buildnumber", "0.1");
        
        /*WindowManager.getDefault().invokeWhenUIReady(new Runnable() {
            @Override
            public void run() {*/
                LoginDetail detail = UtilitySecurity.getInstance().getLoginDetail();
                DataAccess.saveLogin(detail.getUser().getUserID(), detail.getWorkstation());
                
                //load the rights
                HrsGroups group = detail.getUser().getGroupID();
                for(HrsGroupPolicies p: group.getHrsGroupPoliciesCollection()){
                    if(p.getPolModify()){
                        CetusUTL.userRights.add(p.getPolicyID().getPolicyID());
                    }
                }
            /*}
        });*/
        
        
        
    }



    @Override
    public boolean closing() {
        //look for the logged in user
        rslt.addLookupListener(this);
        resultChanged(new LookupEvent(rslt));
        //Check if there is a loggedin user without forcing a login
        if(null==user){
            return true;
        }else{
            Object result = DialogDisplayer.getDefault().notify(new NotifyDescriptor.Confirmation("Exit The Application?", "Exiting", NotifyDescriptor.YES_NO_OPTION));
            if (result==NotifyDescriptor.YES_OPTION){
                UtilitySecurity.updateHistory(user);
                TopComponentGroup tcg = CetusUTL.currentTCG;
                if(tcg!=null)
                    tcg.close();
                
                resetUserLock(user);
                return true;
            }else{
                return false;
            }
        }    
    }
        void resetUserLock(HrsUsers u){
        //Release the user
                    HrsUsers user = DataAccess.entityManager.find(HrsUsers.class, u.getUserID());
                    user.setAccLocked(false);
                    user.setLockoutDate(null);
                    DataAccess.entityManager.getTransaction().begin();
                    DataAccess.entityManager.getTransaction().commit();
                    System.out.println(user.getUserName()+" Unlocked");
    }
    
    

    
}
