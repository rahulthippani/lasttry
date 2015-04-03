package command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Emp;
import model.Survey;
import connectionprovider.ConnectionProvider;

public class GetReport {
	
	public ArrayList<Survey> execute() {
		ArrayList<Survey> ret = new ArrayList<Survey>();
		try {
			Connection connection = ConnectionProvider.getConnection();;
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM survey");
			while (rs.next()) {
				Survey e = new Survey();
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setA1(rs.getInt("a1"));
				e.setA2(rs.getInt("a2"));
				e.setA3(rs.getInt("a3"));
				e.setB1(rs.getInt("b1"));
				e.setB2(rs.getInt("b2"));
				e.setB3(rs.getInt("b3"));
				e.setC1(rs.getInt("c1"));
				e.setC2(rs.getInt("c2"));
				e.setC3(rs.getInt("c3"));
				e.setOverall(rs.getInt("overall"));
				e.setComment(rs.getString("comment"));
				ret.add(e);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	public static void main(String args[])
	{
		
		
		GetReport list=new GetReport();
		ArrayList<Survey> demo = list.execute();
		
		for(Survey li : demo){
			System.out.println(li.getName());
			System.out.println(li.getOverall());
		
		}
		
	}

}
