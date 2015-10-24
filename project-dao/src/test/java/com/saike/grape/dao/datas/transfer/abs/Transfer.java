package com.saike.grape.dao.datas.transfer.abs;

import java.sql.Connection;

public interface Transfer {

    public void transfer(Connection srcConn);

    public void postTransfer(Connection strConn);
}
