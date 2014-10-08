/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmm.woodicebridge;

import com.icesoft.faces.component.ext.UIColumn;
import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;

@FacesComponent(value = "com.mmm.woodicebridge.MyColumn")
public class MyColumn extends UIColumn implements NamingContainer{

    @Override
    public String getFamily() {
        return "custom";
    }

}
