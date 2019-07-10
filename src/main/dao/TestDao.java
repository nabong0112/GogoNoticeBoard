package main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.vo.TestVo;

public class TestDao {

	public void add(TestVo vo) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "1234");
		
		PreparedStatement prep = conn.prepareStatement("insert into users(user_id, user_pw, user_name) value(?, ?, ?)");
		
		prep.setString(1, vo.getName());
		prep.setInt(2, vo.getValue());
		prep.setString(3, vo.getData());
		prep.executeUpdate();
		prep.close();
		
		conn.close();
	}
}
