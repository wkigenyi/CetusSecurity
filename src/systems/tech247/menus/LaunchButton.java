/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import javax.swing.JButton;
import org.openide.awt.StatusDisplayer;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import systems.tech247.security.HRModule;
import systems.tech247.security.UtilitySecurity;

/**
 *
 * @author WKigenyi
 */
public class LaunchButton extends JButton implements LookupListener {

    /**
     * @return the instance
     */
    public static LaunchButton getInstance() {
        if(instance==null){
            instance= new LaunchButton();
        }
        return instance;
    }

    Lookup.Result<HRModule> result;
    private static LaunchButton instance;
    
    
    protected LaunchButton() {
        setText("No Application Selected");
        setEnabled(false);
        TopComponent tc = WindowManager.getDefault().findTopComponent("AppMenuTopComponent");
        
        result = tc.getLookup().lookupResult(HRModule.class);
        resultChanged(new LookupEvent(result));
        result.addLookupListener(this);
    }
    
    
    
    

    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result<HRModule> r = (Lookup.Result<HRModule>)le.getSource();
        Collection<HRModule> c = (Collection<HRModule>) r.allInstances();
        if(!c.isEmpty()){
            for(final HRModule app : c){
                StatusDisplayer.getDefault().setStatusText(app.getModule().getModuleCode());
                setEnabled(true);
                setText("Run "+ app.getModule().getModuleName());
                
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int moduleID = app.getModule().getModuleID();
                        
                            UtilitySecurity.setChosenModuleID(moduleID);
                        
                    }
                });
                
                
            }
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
