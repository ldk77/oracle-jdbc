package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBUtil;
import vo.Member;

public class MemberDao {
	// 로그인  
	public Member selectMemberByIdAndPw(Connection conn, Member member) throws Exception {
		Member returnMember = null;
		String sql ="SELECT member_id memberId,member_name memberName,createdate,updatedate FROM member WHERE member_id = ? AND member_pw = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, member.getMemberId());
		stmt.setString(2, member.getMemberPw());		
		ResultSet rs = stmt.executeQuery();				
		if(rs.next()) {
			returnMember = new Member();
			returnMember.setMemberId(rs.getString("memberId"));
			returnMember.setMemberName(rs.getString("memberName"));
			returnMember.setCreatedate(rs.getString("createdate"));
			returnMember.setUpdatedate(rs.getString("updatedate"));
		} 
		rs.close();
		stmt.close();
		
		return returnMember;
	}
	// 회원가입 
	public int insertMember(Connection conn, Member member) throws Exception {
		int result = 0;	
		String sql = "INSERT INTO member (member_id, member_pw, member_name, updatedate, createdate) VALUES (?, ?, ?, SYSDATE, SYSDATE)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, member.getMemberId());
		stmt.setString(2, member.getMemberPw());
		stmt.setString(3, member.getMemberName());			
		result = stmt.executeUpdate();
		return result;
	}
	// 회원정보변경 
	public int updateMember(Connection conn, Member member) throws Exception {
		int result = 0; 
		String sql = "update member SET member_name =?, member_pw=?,updatedate=SYSDATE WHERE member_id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, member.getMemberName());
		stmt.setString(2, member.getMemberPw());	
		stmt.setString(3, member.getMemberId());
		result = stmt.executeUpdate();
		return result;
	}
	//회원탈퇴 
	public int deleteMember(Connection conn, Member member) throws Exception{
		int result = 0; 
		String sql = "DELETE FROM MEMBER WHERE member_id=? AND member_pw =?";
		PreparedStatement stmt = conn.prepareStatement(sql);	
		stmt.setString(1, member.getMemberId());
		stmt.setString(2, member.getMemberPw());	
		result = stmt.executeUpdate();
		return result;
	}
}
