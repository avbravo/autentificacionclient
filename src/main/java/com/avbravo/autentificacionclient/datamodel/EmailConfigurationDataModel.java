/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.datamodel;


import com.avbravo.autentificacionclient.entity.EmailConfiguration;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author avbravo
 */
public class EmailConfigurationDataModel extends ListDataModel<EmailConfiguration> implements SelectableDataModel<EmailConfiguration> {

    public EmailConfigurationDataModel() {
    }

    public EmailConfigurationDataModel(List<EmailConfiguration> data) {
        super(data);
    }

    @Override
    public EmailConfiguration getRowData(String rowKey) {
        List<EmailConfiguration> list = (List<EmailConfiguration>) getWrappedData();

        for (EmailConfiguration departamento : list) {
            if (departamento.getIdEmailConfiguration().equals(rowKey)) {
                return departamento;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(EmailConfiguration departament) {
        return departament.getIdEmailConfiguration();
    }

}
