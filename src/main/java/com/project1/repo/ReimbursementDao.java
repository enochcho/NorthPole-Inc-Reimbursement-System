package com.project1.repo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.project1.config.EnvironmentConnectionUtil;
import com.project1.model.Reimbursement;
import com.project1.model.ReimbursementStatus;
import com.project1.model.ReimbursementType;
import com.project1.model.User;
import com.project1.model.UserRole;

public class ReimbursementDao implements DaoContract<Reimbursement, Integer>{
	
	public ReimbursementDao() {
		super();
	}

	@Override
	public List<Reimbursement> findAll() {
		List<Reimbursement> reimbs = new ArrayList<>();
		try(Connection con = EnvironmentConnectionUtil.getInstance().getConnection()){
			String sql = "select * from total;";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				User author = new User(rs.getInt(11),rs.getString(12), rs.getString(13), rs.getString(14),rs.getString(15),new UserRole(rs.getInt(16)));
				User resolver = null;
				if(rs.getInt(18) != 0) {
					resolver = new User(rs.getInt(18), rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22), new UserRole(rs.getInt(23)));
				} 
				ReimbursementStatus status = new ReimbursementStatus(rs.getInt(9));
				ReimbursementType type = new ReimbursementType(rs.getInt(5));
				int id = rs.getInt(1);
				double amount = rs.getDouble(2);
				Timestamp submitTime = rs.getTimestamp(3);
				Timestamp resolveTime = rs.getTimestamp(4);
				String description = rs.getString(7);
				InputStream receipt = null;
				if(rs.getBytes(8) != null) {
					receipt = new ByteArrayInputStream(rs.getBytes(8));
				}
				if(resolver == null) {
					reimbs.add(new Reimbursement(id, amount, submitTime, description, receipt, author, status, type));
				} else {
					reimbs.add(new Reimbursement(id,amount,submitTime,resolveTime,description,receipt, author,resolver,status,type));
				}
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimbs;
	}

	@Override
	public Reimbursement findById(Integer i) {
		Reimbursement reimb = null;
		try(Connection con = EnvironmentConnectionUtil.getInstance().getConnection()){
			String sql = "select * from total where \"Reimbursement ID\"=?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				User author = new User(rs.getInt(11),rs.getString(12), rs.getString(13), rs.getString(14),rs.getString(15),new UserRole(rs.getInt(16)));
				User resolver = null;
				if(rs.getInt(18) != 0) {
					resolver = new User(rs.getInt(18), rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22), new UserRole(rs.getInt(23)));
				} 				ReimbursementStatus status = new ReimbursementStatus(rs.getInt(9));
				ReimbursementType type = new ReimbursementType(rs.getInt(5));
				int id = rs.getInt(1);
				double amount = rs.getDouble(2);
				Timestamp submitTime = rs.getTimestamp(3);
				Timestamp resolveTime = rs.getTimestamp(4);
				String description = rs.getString(7);
				InputStream receipt = null;
				if(rs.getBytes(8) != null) {
					receipt = new ByteArrayInputStream(rs.getBytes(8));
				}
				if(resolver == null) {
					reimb = new Reimbursement(id, amount, submitTime, description, receipt, author, status, type);
				} else {
					reimb = new Reimbursement(id,amount,submitTime,resolveTime,description,receipt, author,resolver,status,type);
				}
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimb;
	}

	@Override
	public int update(Reimbursement t) {
		int result = 0;
		try(Connection con = EnvironmentConnectionUtil.getInstance().getConnection()){
			//if the reimbursement has been resolved
			if(t.getResolveTime() != null) {
				String sql = "update ers_reimbursement set "
						+ "reimb_amount = ?,"
						+ "reimb_submitted = ?,"
						+ "reimb_resolved = ?,"
						+ "reimb_description = ?,"
						+ "reimb_receipt = ?,"
						+ "reimb_author = ?,"
						+ "reimb_resolver = ?,"
						+ "reimb_status_id = ?,"
						+ "reimb_type_id = ? where reimb_id = ?;";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setDouble(1, t.getAmount());
				ps.setTimestamp(2, t.getSubmitTime());
				ps.setTimestamp(3, t.getResolveTime());
				ps.setString(4,t.getDescription());
				ps.setBytes(5, IOUtils.toByteArray(t.getReceipt()));
				ps.setInt(6,t.getAuthor().getUserId());
				ps.setInt(7, t.getResolver().getUserId());
				ps.setInt(8, t.getStatus().getStatusId());
				ps.setInt(9, t.getType().getTypeId());
				ps.setInt(10, t.getId());
				result = ps.executeUpdate();
				ps.close();
			} else {
				String sql = "update ers_reimbursement set "
						+ "reimb_amount = ?,"
						+ "reimb_submitted = ?,"
						+ "reimb_resolved = ?,"
						+ "reimb_description = ?,"
						+ "reimb_receipt = ?,"
						+ "reimb_author = ?,"
						+ "reimb_status_id = ?,"
						+ "reimb_type_id = ? where reimb_id = ?;";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setDouble(1, t.getAmount());
				ps.setTimestamp(2, t.getSubmitTime());
				ps.setTimestamp(3, t.getResolveTime());
				ps.setString(4,t.getDescription());
				if(t.getReceipt() != null) {
					ps.setBytes(5, IOUtils.toByteArray(t.getReceipt()));	
				} else {
					ps.setBytes(5, null);
				}
				ps.setInt(6,t.getAuthor().getUserId());
				ps.setInt(7, t.getStatus().getStatusId());
				ps.setInt(8, t.getType().getTypeId());
				ps.setInt(9, t.getId());
				result = ps.executeUpdate();				
				ps.close();
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	//assuming that all created reimbursements will not have been approved. 
	//time submitted is whenever its created in the db. 
	@Override
	public int create(Reimbursement t) {
		int result = 0;
		try(Connection con = EnvironmentConnectionUtil.getInstance().getConnection()){
			String sql = "insert into ers_reimbursement "
					+ "(reimb_amount,"
					+ "reimb_description,"
					+ "reimb_receipt,"
					+ "reimb_author,"
					+ "reimb_status_id,"
					+ "reimb_type_id) values (?,?,?,?,?,?) ;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, t.getAmount());
			ps.setString(2,t.getDescription());
			if(t.getReceipt() != null) {
				ps.setBytes(3, IOUtils.toByteArray(t.getReceipt()));	
			} else {
				ps.setBytes(3, null);
			}
			ps.setInt(4,t.getAuthor().getUserId());
			ps.setInt(5, t.getStatus().getStatusId());
			ps.setInt(6, t.getType().getTypeId());
			result = ps.executeUpdate();
			ps.close();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} return result;
	}

	@Override
	public int delete(Integer i) {
		int result = 0;
		try(Connection con = EnvironmentConnectionUtil.getInstance().getConnection()){
			String sql = "delete from ers_reimbursement where reimb_id = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, i);
			result = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Reimbursement> getReimbursementByEmployee(int userId) {
		List<Reimbursement> myReimbs = new ArrayList<>();
		try(Connection con = EnvironmentConnectionUtil.getInstance().getConnection()){
			String sql = "select * from total where \"Employee ID\" = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,  userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				User author = new User(rs.getInt(11),rs.getString(12), rs.getString(13), rs.getString(14),rs.getString(15),new UserRole(rs.getInt(16)));
				User resolver = null;
				if(rs.getInt(18) != 0) {
					resolver = new User(rs.getInt(18), rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22), new UserRole(rs.getInt(23)));
				} 
				ReimbursementStatus status = new ReimbursementStatus(rs.getInt(9));
				ReimbursementType type = new ReimbursementType(rs.getInt(5));
				int id = rs.getInt(1);
				double amount = rs.getDouble(2);
				Timestamp submitTime = rs.getTimestamp(3);
				Timestamp resolveTime = rs.getTimestamp(4);
				String description = rs.getString(7);
				InputStream receipt = null;
				if(rs.getBytes(8) != null) {
					receipt = new ByteArrayInputStream(rs.getBytes(8));
				}
				if(resolver == null) {
					myReimbs.add(new Reimbursement(id, amount, submitTime, description, receipt, author, status, type));
				} else {
					myReimbs.add(new Reimbursement(id,amount,submitTime,resolveTime,description,receipt, author,resolver,status,type));
				}
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myReimbs;
	}

	/*
	 * called when the financial manager approves/denies the request
	 */
	public int updateStatus(Reimbursement r) {
		int result = 0;
		try(Connection con = EnvironmentConnectionUtil.getInstance().getConnection()){
			String sql = "update ers_reimbursement set reimb_resolver = ?, reimb_status_id = ? where reimb_id = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,  r.getResolver().getUserId());
			ps.setInt(2,  r.getStatus().getStatusId());
			ps.setInt(3, r.getId());
			result = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
