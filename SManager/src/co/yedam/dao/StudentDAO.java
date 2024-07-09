package co.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.vo.StudentVO;

/*
 * 등록(R), 목록(C), 수정(U), 삭제(D) 
 * 주의 : DAO 메세지(System.out.println)가 없음.
 */
public class StudentDAO extends DAO {
	// 등록기능
	public boolean insertStudent(StudentVO svo) {
		String sql = "insert into tbl_student (std_no, std_name, std_phone, address, birth_date)";
		sql += "values(?,?,?,?,?) ";
		conn = getConn();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, svo.getStdNo());
			psmt.setString(2, svo.getStdName());
			psmt.setString(3, svo.getStdPhone());
			psmt.setString(4, svo.getAddress());
			psmt.setString(5, svo.getBirthDate());

			int r = psmt.executeUpdate(); // 쿼리실행.
			if (r == 1) {
				return true; // 정상처리
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 목록반환기능.
	public List<StudentVO> selectList() {
		String sql = "select * from tbl_student order by std_no";
		List<StudentVO> list = new ArrayList<>();
		conn = getConn();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				StudentVO svo = new StudentVO();
				list.add(svo);
				svo.setAddress(rs.getString("address"));
				svo.setBirthDate(rs.getString("birth_date"));
				svo.setStdName(rs.getString("std_name"));
				svo.setStdPhone(rs.getString("std_phone"));
				svo.setStdNo(rs.getString("std_no"));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return list;
	} // end of selectList
}