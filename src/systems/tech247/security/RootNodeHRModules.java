/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.security;

import java.awt.event.ActionEvent;
import javax.persistence.Query;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.editorpanels.ModuleEditorPanel;



/**
 *
 * @author WKigenyi
 */
public class RootNodeHRModules extends AbstractNode {
    
    public RootNodeHRModules(int groupID) {
        super(Children.create(new ChildFactoryHRModule(groupID), true));
    }
    
    @Override
    public  Action[] getActions(boolean context){
        final ModuleEditorPanel moduleEditor = new ModuleEditorPanel();
        AbstractAction createNewApp = new AbstractAction ("Create A New Module"){
            @Override
            public void actionPerformed(ActionEvent e) {
                NotifyDescriptor nd = new NotifyDescriptor(moduleEditor, "New Module", NotifyDescriptor.PLAIN_MESSAGE, NotifyDescriptor.PLAIN_MESSAGE, new Object[]{NotifyDescriptor.OK_OPTION,NotifyDescriptor.CANCEL_OPTION}, null);
                
                Object result = DialogDisplayer.getDefault().notify(nd);
                
                if(result != NotifyDescriptor.OK_OPTION){
                    
                }else{
                    String sqlString = "INSERT INTO hrsHRModules" +
"           (ModuleCode" +
"           ,ModuleName" +
"           ,Enabled)" +
"     VALUES" +
"           ('"+moduleEditor.getModuleCode()+"'," +
"           '"+moduleEditor.getModuleDescription()+"'," +
"           '"+moduleEditor.getEnabled()+"')";
                    
                    //Query query = getEntityManagerHR().createNativeQuery(sqlString);
                    
                   
                try{
                Query q = DataAccess.getEntityManager().createNativeQuery(sqlString);
                    
                int i = q.executeUpdate();
                DataAccess.getEntityManager().getTransaction().commit();
                //DialogDisplayer.getDefault().notifyLater(new NotifyDescriptor.Message("Update is: "+ i));
                }catch(Exception exp){
                    DialogDisplayer.getDefault().notifyLater(new NotifyDescriptor.Message(exp.getLocalizedMessage()+"\n Not Saved"));
                    
                }
                }
                
                
            }
            
        };
        return new Action[]{createNewApp};
        
    }
}
