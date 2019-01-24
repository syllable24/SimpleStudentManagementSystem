/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author neuholdw14
 */

// Backing Bean fuer ausblenden.xhtml
    @ManagedBean
    @ViewScoped
    public class AusblendenBean {

        private Boolean meineCheckbox1 = Boolean.FALSE;
        private Boolean meineCheckbox2 = Boolean.FALSE;
        private String inputText = "... ...";

        public void checkboxChanged1(ValueChangeEvent vcEvent) {
            Boolean chckbx = (Boolean) vcEvent.getNewValue();
            if (chckbx != null) {
                meineCheckbox1 = chckbx;
            }
            FacesContext.getCurrentInstance().renderResponse();
        }

        public void checkboxChanged2(ValueChangeEvent vcEvent) {
            Boolean chckbx = (Boolean) vcEvent.getNewValue();
            if (chckbx != null) {
                meineCheckbox2 = chckbx;
            }
            FacesContext.getCurrentInstance().renderResponse();
        }

        public String getUhrzeit() {
            return (new SimpleDateFormat("HH:mm:ss.SSS")).format(new Date()) + " Uhr";
        }

        public Boolean getMeineCheckbox1() {
            return meineCheckbox1;
        }

        public Boolean getMeineCheckbox2() {
            return meineCheckbox2;
        }

        public String getInputText() {
            return inputText;
        }

        public void setMeineCheckbox1(Boolean meineCheckbox) {
            this.meineCheckbox1 = meineCheckbox;
        }

        public void setMeineCheckbox2(Boolean meineCheckbox) {
            this.meineCheckbox2 = meineCheckbox;
        }

        public void setInputText(String inputText) {
            this.inputText = inputText;
        }

    }

