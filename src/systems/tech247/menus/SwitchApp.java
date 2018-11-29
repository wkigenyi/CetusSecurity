/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import systems.tech247.security.UtilitySecurity;


@ActionID(
        category = "File",
        id = "systems.tech247.menus.SwitchApp"
)
@ActionRegistration(
        iconBase = "systems/tech247/menus/changeapp.png",
        displayName = "#CTL_SwitchApp"
        
)
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 0),
    @ActionReference(path = "Toolbars/File", position = 0)
})
@Messages("CTL_SwitchApp=Switch Application")
public final class SwitchApp implements ActionListener {

    
    
    public SwitchApp() {
        
        
        
        //login = new Login();
        //login.addPropertyChangeListener(this);
        //query.addPropertyChangeListener(this);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        UtilitySecurity.getInstance().chooseModule();
    }
}
