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
import net.sf.dynamicreports.adhoc.AdhocManager;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.editorpanels.ModuleEditorPanel;
import systems.tech247.security.reports.AuditTrailReport;
import systems.tech247.util.CapPrint;



/**
 *
 * @author WKigenyi
 */
public class RootNodeAuditTrail extends AbstractNode {
    private final InstanceContent instanceContent;
    public RootNodeAuditTrail(String sql) {
        this(sql,new InstanceContent());
    }
    
    private RootNodeAuditTrail(String sql,InstanceContent ic){
        super(Children.create(new ChildFactoryAuditTrail(sql), true),new AbstractLookup(ic));
        instanceContent=ic;
        instanceContent.add(new CapPrint() {
            @Override
            public void print() {
                AuditTrailReport report = new AuditTrailReport();
                
            }
        });
        setDisplayName("Audit Trail");
        
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
