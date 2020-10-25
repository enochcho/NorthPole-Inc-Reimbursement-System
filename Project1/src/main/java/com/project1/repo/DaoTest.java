package com.project1.repo;

import java.sql.Timestamp;
import java.time.Instant;

import com.project1.model.Reimbursement;
import com.project1.model.ReimbursementStatus;
import com.project1.model.ReimbursementType;

public class DaoTest {
	private static ReimbursementDao rd;
	private static UserDao ud;
	
	public static void main(String[] args) {
		rd = new ReimbursementDao();
		ud = new UserDao(); 
//		//testing DAOs
//		//UserDao test
//		//Testing find all:
//		List<User> users = ud.findAll();
//		System.out.println("Testing findAll, the size should be 3, it is: " + users.size());
//		//Testing create
//		UserRole empl = new UserRole(2);
//		User dasher = new User(0, "dasher", "Dasher", "Reindeer", "dasher@northpole.inc", empl);
//		System.out.println("Testing create, should be a 1: " + ud.create(dasher));
//		//testing findbyId
//		User isDasher = ud.findById(users.size()+1);
//		System.out.println("This should be Dasher: " + isDasher.getFirst());
//		//testing update
//		isDasher.setFirst("Vixen");
//		System.out.println("Testing update. This should be 1: " + ud.update(isDasher));
//		User isVixen = ud.findById(isDasher.getUserId());
//		System.out.println("Extra update test, to make sure the value got updated. Should be Vixen with a username of dasher: Username:" + isVixen.getUsername() + " should be Vixen: " + isVixen.getFirst());
//		//testing findAll again
//		System.out.println("Testing findAll to see if its 4 now: " + ud.findAll().size());
//		//testing delete
//		System.out.println("Testing the delete, should be 1:" + ud.delete(isVixen.getUserId()));
//		//last findAll test
//		System.out.println("Last findAll test, should be 3 again:" + ud.findAll().size());
//		
		
		//ReimbursementDao Test
//		//Testing findAll
//		int num = rd.findAll().size();
//		System.out.println("Testing findall, should be 2: " + num);
//		//testing create
//		ReimbursementType type = new ReimbursementType(1);
//		ReimbursementStatus status = new ReimbursementStatus(1);
//		Reimbursement reimb = new Reimbursement(0, 200, Timestamp.from(Instant.now()), "going to america", null, ud.findById(2), status, type);
//		System.out.println("Testing create, this should be 1: " + rd.create(reimb));
//		//testing findbyID
//		Reimbursement newReimb = rd.findById(num+1);
//		System.out.println("Testing findbyId, this should be 200 and Rudolph: " + newReimb.getAmount() + " " + newReimb.getAuthor().getUsername());
//		//testing udpate
//		newReimb.setAmount(250);
//		System.out.println("Testing update, should be 1: " +  rd.update(newReimb));
//		reimb = rd.findById(newReimb.getId());
//		System.out.println("Extra update test, should be 250 and rudolph: " + reimb.getAmount() + " " + reimb.getAuthor().getUsername());
//		//testFindAll
//		System.out.println("Testing findall to see if its 3 now : " + rd.findAll().size());
//		//testing the delete
//		System.out.println("Testing the delete, should be 1: " + rd.delete(reimb.getId()));
//		//last findAll
//		System.out.println("Testing the findall, should be 2 again: " + rd.findAll().size());
		
		
		
		
		
		
//		System.out.println("wow");
//		
//		LocalDateTime ldt = LocalDateTime.now();
//		System.out.println("Is it same?: " + ldt);
//		Instant instant = Instant.now();
//		Timestamp ts = Timestamp.from(instant);
//		System.out.println(ts);
//		LocalDateTime ret = null;
		
		
		
//		try(Connection conn = EnvironmentConnectionUtil.getInstance().getConnection()){
//			String sql = "insert into time values (?)";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setObject(1, ldt);
//			ps.executeUpdate();
//			ps.close();
//			sql = "select * from time;";
//			ps = conn.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()) {
//				ret = ((Timestamp) rs.getObject(1)).toLocalDateTime();
//				System.out.println("Got back: " + ret.toString());
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}
