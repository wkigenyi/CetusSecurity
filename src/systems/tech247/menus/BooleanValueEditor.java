/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.menus;

import java.beans.PropertyEditorSupport;

/**
 *
 * @author Admin
 */
public class BooleanValueEditor extends PropertyEditorSupport{

    @Override
    public String getAsText() {
        return super.getAsText(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(text);
    }
    
    
}
