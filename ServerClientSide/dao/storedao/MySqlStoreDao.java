package com.dkit.abielLopez.SDA.dao.storedao;



import com.dkit.abielLopez.SDA.dao.MySqlDao;
import com.dkit.abielLopez.SDA.exceptions.DaoException;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class MySqlStoreDao extends MySqlDao implements StoreDaoInterface
{



    @Override
    public Set<Store> findAllStores() throws DaoException
    {
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            Set<Store> stores = new HashSet<>();

            try
            {
                //Get a connection to the database
                con = this.getConnection();
                String query = "SELECT * FROM stores";
                ps = con.prepareStatement(query);

                //Use the prepared statement to execute the sql
                rs = ps.executeQuery();

                while (rs.next())
                {
                    int storeID = rs.getInt("fleet_id");
                    String storeName = rs.getString("fleet_name");

                    Store store = new Store(storeID, storeName);

                    stores.add(store);
                }

            }
            catch (SQLException sqe)
            {
                throw new DaoException("findAllFleets() " + sqe.getMessage());
            }
            finally
            {
                try
                {
                    if (rs != null)
                    {
                        rs.close();
                    }
                    if (ps != null)
                    {
                        ps.close();
                    }
                    if (con != null)
                    {

                        freeConnection(con);
                    }
                } catch (SQLException sqe)
                {
                    throw new DaoException("findAllFleets() " + sqe.getMessage());
                }
            }
            return stores;
    }

    @Override
    public String findAllStoresJson() throws DaoException
    {
        Set <Store> fleets = findAllStores();

        String jsonStringFleets = gsonParser.toJson(fleets);

        return jsonStringFleets;
    }






}
