package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.MeetingVO;

public class MeetingJDBCDAO implements MeetingDAO{

	public List<MeetingVO> listAll() {
		List<MeetingVO> list = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbctest",
				"jdbctest");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select id, name, title, to_char(meetingdate, "
						+ "'yyyy\"년\" mm\"월\" dd\"일\" HH\"시\" MI\"분\"') from meeting");) {
			MeetingVO vo;
			while (rs.next()) {
				vo = new MeetingVO();
				vo.setId(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setMeetingDate(rs.getString(4));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public List<MeetingVO> search(String keyword) {
		List<MeetingVO> list = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbctest",
				"jdbctest");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"select id, name, title, to_char(meetingdate, 'yyyy\"년\" mm\"월\" dd\"일\" HH\"시\" MI\"분\"')"
								+ "from meeting where title like '%" + keyword + "%'");) {
			MeetingVO vo;
			while (rs.next()) {
				vo = new MeetingVO();
				vo.setId(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setMeetingDate(rs.getString(4));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean insert(MeetingVO vo) {
		boolean result = true;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbctest",
				"jdbctest");
				PreparedStatement pstmt = conn.prepareStatement("insert into meeting values(meeting_seq.nextval, ?, ?, to_date(?, 'yyyy-mm-dd\"T\"hh24:mi'))");) {
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getMeetingDate());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(int eNo) {
		// TODO Auto-generated method stub
		
		boolean result = true;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbctest",
				"jdbctest");
				PreparedStatement pstmt = conn.prepareStatement("delete from meeting where id=?");) {
			pstmt.setInt(1, eNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}
}
