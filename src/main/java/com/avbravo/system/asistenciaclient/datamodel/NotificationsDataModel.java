/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.system.asistenciaclient.datamodel;


import com.avbravo.autentificacionclient.entity.Notifications;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author avbravo
 */
public class NotificationsDataModel extends ListDataModel<Notifications> implements SelectableDataModel<Notifications> {

    public NotificationsDataModel() {
    }

    public NotificationsDataModel(List<Notifications> data) {
        super(data);
    }

    @Override
    public Notifications getRowData(String rowKey) {
        List<Notifications> list = (List<Notifications>) getWrappedData();

        for (Notifications notificationso : list) {
            if (notificationso.getIdnotifications().equals(rowKey)) {
                return notificationso;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Notifications notificationso) {
        return notificationso.getIdnotifications();
    }

}
