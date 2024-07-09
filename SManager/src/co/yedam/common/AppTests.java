package co.yedam.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import co.yedam.vo.StudentVO;

public class AppTests {
	public static Connection getConn() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.oracleDriver";
		String user = "jsp";
		String pass = "jsp";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		// 1) Oracle JDBC Driver 자바라이브러리.
		// 2) Connection 객체.
		Scanner sc = new Scanner(System.in);
		System.out.println(">학생번호 입력.");

		String sno = sc.nextLine();
		System.out.println(">학생이름 입력.");
		String sname = sc.nextLine();
		System.out.println(">연락처 입력.");
		String phon = sc.nextLine();
		System.out.println(">주소 입력.");
		String addr = sc.nextLine();
		System.out.println(">생일 입력.");
		String birth = sc.nextLine();
		
		StudentVO std = new StudentVO();
		std.setStdNo(sno);
		std.setStdName(sname);
		std.setStdPhone(phon);
		std.setAddress(addr);
		std.setBirthDate(birth);
		
		addStudent(sno, sname, phon); // 입력.
		search(); // 목록조회
		removeStudent(sno); //삭제
		

		
	} // 삭제기능

	public static void removeStudent(String std_no) {
//		delete from tbl_student
//		where std_no = '1';
		Connection conn = getConn();
		String sql = "delete from tbl_student";
		sql += " WHERE std_no = '" + std_no + "'";

		try {
			Statement stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql); // insert, update, delete
			System.out.println("처리된 건수는" + r + "건.");
		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	// 수정기능
	public static void modStu(StudentVO svo) {
			
		
		Connection conn = getConn();
		String sql = "update tbl_student ";
		sql += "SET std_name = nvl( '" + svo.getStdName() + "', std_name)";
		sql += "	, std_phone = nvl('" + svo.getStdPhone() + "', std_phone)";
		sql += "	, address = nv1('" + svo.getAddress() + "', address)";
		sql += "	, birth_date = nv1('" + svo.getBirthDate() + "', birth_date)";
		sql += " WHERE std_no = '" + svo.getStdNo() + "'";

		try {
			Statement stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql); // insert, update, delete
			System.out.println("처리된 건수는" + r + "건.");
		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	// 입력기능.
	// insert into tbl_student (std_no, std_name, std_phone)
	// values('S2024-04', '김민규', '010-2222-5678');
	public static void addStudent(String std_no, String std_name, String std_phone) {
		Connection conn = getConn();
		String sql = "insert into tbl_student (std_no, std_name, std_phone)";
		sql += "values('" + std_no + "', '" + std_name + "', '" + std_phone + "')";

		try {
			Statement stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql); // insert, update, delete
			System.out.println("처리된 건수는" + r + "건.");
		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	public static void search() {
		// 조회기능
		try {
			Connection conn = getConn();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from tbl_student");
			// [객체1, 객체2, 객체3]
			while (rs.next()) {
				System.out.println(
						rs.getString("std_no") + "," + rs.getString("std_name") + "," + rs.getString("std_phone"));
			}
			System.out.println("end of data.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 조회기능 끝
	}

}
