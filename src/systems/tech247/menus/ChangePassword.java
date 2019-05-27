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
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import systems.tech247.hr.HrsUsers;
import systems.tech247.security.UtilitySecurity;
import systems.tech247.util.CetusUTL;

@ActionID(
        category = "System",
        
        id = "systems.tech247.menus.ChangePassword"
)
@ActionRegistration(
        displayName = "#CTL_ChangePassword"
)
@ActionReference(path = "Menu/System", position = -200)
@Messages("CTL_ChangePassword=Change Password")
public final class ChangePassword implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        HrsUsers user = CetusUTL.getInstance().getLookup().lookup(HrsUsers.class);
        UtilitySecurity.showPasswordPrompt(user);
    }
}
