package oracle.exam2;

import java.sql.Connection;

import oracle.db.DBManager;

public class DBTest {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		DBManager dbManager = DBManager.getInstance();
		
		Connection conn = dbManager.getConnection();
		System.out.println(conn);
	}

}
