/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.datamodel;


import com.avbravo.autentificacionclient.entity.Otp;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author avbravo
 */
public class OtpDataModel extends ListDataModel<Otp> implements SelectableDataModel<Otp> {

    public OtpDataModel() {
    }

    public OtpDataModel(List<Otp> data) {
        super(data);
    }

    @Override
    public Otp getRowData(String rowKey) {
        List<Otp> list = (List<Otp>) getWrappedData();

        for (Otp otpo : list) {
            if (otpo.getIdotp().equals(rowKey)) {
                return otpo;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Otp otpo) {
        return otpo.getIdotp();
    }

}
