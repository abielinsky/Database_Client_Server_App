package com.dkit.abielLopez.SDA.exceptions;

public class Query {
    private String sql;
    private String parameters;

    public Query(String sql, String parameters) {
        this.sql = sql;
        this.parameters = parameters;
    }
    public Query(){
        this.sql = "";
        this.parameters = "";
    }

    public String getSql() {
        return sql;
    }

    public String getParameters() {
        return parameters;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }


    @Override
    public String toString() {
        return "Query{" +
                "sql='" + sql + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}

