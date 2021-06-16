/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.autentificacionclient.datamodel;


import com.avbravo.autentificacionclient.entity.Collectionincrementable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author avbravo
 */
public class CollectionincrementableDataModel extends ListDataModel<Collectionincrementable> implements SelectableDataModel<Collectionincrementable> {

    public CollectionincrementableDataModel() {
    }

    public CollectionincrementableDataModel(List<Collectionincrementable> data) {
        super(data);
    }

    @Override
    public Collectionincrementable getRowData(String rowKey) {
        List<Collectionincrementable> list = (List<Collectionincrementable>) getWrappedData();

        for (Collectionincrementable collectionincrementableo : list) {
            if (collectionincrementableo.getCollections().equals(rowKey)) {
                return collectionincrementableo;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Collectionincrementable collectionincrementable) {
        return collectionincrementable.getCollections();
    }

}
