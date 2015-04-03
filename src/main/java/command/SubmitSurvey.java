package command;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Emp;
import model.Survey;
import connectionprovider.ConnectionProvider;

public class SubmitSurvey {

	public String execute(Survey ep) {

		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("INSERT INTO survey(name, a1, a2, a3, b1, b2, b3, c1, c2, c3, overall, comment) VALUES(?,?,?,?,?,?,?,?,?,?,?,?) Returning id");
			stmt.setString(1, ep.getName());
			stmt.setInt(2, ep.getA1());
			stmt.setInt(3, ep.getA2());
			stmt.setInt(4, ep.getA3());
			stmt.setInt(5, ep.getB1());
			stmt.setInt(6, ep.getB2());
			stmt.setInt(7, ep.getB3());
			stmt.setInt(8, ep.getC1());
			stmt.setInt(9, ep.getC2());
			stmt.setInt(10, ep.getC3());
			stmt.setInt(11, ep.getOverall());
			stmt.setString(12, ep.getComment());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				return rs.getString("id");

			}

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "-1";
	}

	public static void main(String args[]) {
		SubmitSurvey demo = new SubmitSurvey();
		Survey dem=new  Survey();
		dem.setA1(5);
		dem.setA2(3);
		dem.setA3(7);
		
		dem.setB1(5);
		dem.setB2(3);
		dem.setB3(7);
		
		dem.setC1(5);
		dem.setC2(3);
		dem.setC3(7);
		
		dem.setName("vibbs");
		dem.setComment("this hardcoding suckes");
		
		System.out.println(demo.execute(dem));
	}

}
