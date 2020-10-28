package com.project1.repo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project1.config.EnvironmentConnectionUtil;
import com.project1.model.User;
import com.project1.model.UserRole;

public class UserDao implements DaoContract<User,Integer>{
	
	public UserDao() {
		super();
	}

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		try(Connection con = EnvironmentConnectionUtil.getInstance().getConnection()){
			String sql = "select * from ers_users;";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				users.add(new User(rs.getInt(1),rs.getString(2), rs.getString(4), rs.getString(5),rs.getString(6),new UserRole(rs.getInt(7))));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User findById(Integer i) {
		User user = null;
		try(Connection con = EnvironmentConnectionUtil.getInstance().getConnection()){
			String sql = "select * from ers_users where ers_users_id = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,  i);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user = new User(rs.getInt(1),rs.getString(2), rs.getString(4), rs.getString(5),rs.getString(6),new UserRole(rs.getInt(7)));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int update(User t) {
		int result = 0;
		try(Connection con = EnvironmentConnectionUtil.getInstance().getConnection()){
			String sql = "update ers_users set ers_username = ?, user_first_name= ?, user_last_name = ?, user_email = ?, user_role_id = ? where ers_users_id = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, t.getUsername());
			ps.setString(2, t.getFirst());
			ps.setString(3,  t.getLast());
			ps.setString(4, t.getEmail());
			ps.setInt(5, t.getRole().getTypeId());
			ps.setInt(6, t.getUserId());
			result = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int create(User t) {
		int result = 0;
		try(Connection con = EnvironmentConnectionUtil.getInstance().getConnection()){
			String sql = "insert into ers_users (ers_username, user_first_name, user_last_name, user_email, user_role_id) values (?,?,?,?,?);";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, t.getUsername());
			ps.setString(2, t.getFirst());
			ps.setString(3,  t.getLast());
			ps.setString(4, t.getEmail());
			ps.setInt(5, t.getRole().getTypeId());
			result = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(Integer i) {
		int result = 0;
		try(Connection con = EnvironmentConnectionUtil.getInstance().getConnection()){
			String sql = "delete from ers_users where ers_users_id = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, i);
			result = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int login(String username, String password) {
		int result = 0;
		try(Connection con = EnvironmentConnectionUtil.getInstance().getConnection()){
			String sql = "call login(?,?,0)";
			CallableStatement cs = con.prepareCall(sql);
			cs.setString(1,  username);
			cs.setString(2,  password);
			ResultSet rs = cs.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			rs.close();
			cs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	

}
