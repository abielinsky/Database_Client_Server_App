package com.dkit.abielLopez.SDA.exceptions;


import java.sql.SQLException;

public class DaoException extends SQLException
{

    public DaoException()
    {
    }

    public DaoException(String reason)
    {
        super(reason);
    }

}
