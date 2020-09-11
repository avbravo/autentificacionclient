/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.asistenciaclient.datamodel;


import com.avbravo.jmoordb.pojos.JmoordbEmailMaster;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author avbravo
 */
public class JmoordbEmailMasterDataModel extends ListDataModel<JmoordbEmailMaster> implements SelectableDataModel<JmoordbEmailMaster> {

    public JmoordbEmailMasterDataModel() {
    }

    public JmoordbEmailMasterDataModel(List<JmoordbEmailMaster> data) {
        super(data);
    }

    @Override
    public JmoordbEmailMaster getRowData(String rowKey) {
        List<JmoordbEmailMaster> list = (List<JmoordbEmailMaster>) getWrappedData();

        for (JmoordbEmailMaster jmoordbEmailMastero : list) {
            if (jmoordbEmailMastero.getEmail().equals(rowKey)) {
                return jmoordbEmailMastero;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(JmoordbEmailMaster jmoordbEmailMastero) {
        return jmoordbEmailMastero.getEmail();
    }

}
