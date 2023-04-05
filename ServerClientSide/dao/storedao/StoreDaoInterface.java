package com.dkit.abielLopez.SDA.dao.storedao;


import com.dkit.abielLopez.SDA.dto.Store;
import com.dkit.abielLopez.SDA.exceptions.DaoException;
import com.google.gson.Gson;

import java.util.Set;

public interface StoreDaoInterface
{
    Gson gsonParser = new Gson();

    String findAllStoresJson() throws DaoException;

    public Set<Store> findAllStores() throws DaoException;


}
