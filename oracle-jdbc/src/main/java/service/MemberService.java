package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.BoardDao;
import dao.MemberDao;
import util.DBUtil;
import vo.Board;
import vo.Member;

public class MemberService {
private MemberDao memberDao;
	
	// 1) 로그인 서비스
	public Member loginService(Member member) {
		Member returnMember = null;
		Connection conn = null;		
		memberDao = new MemberDao();		
		try {
			conn = DBUtil.getConnection();
			returnMember = memberDao.selectMemberByIdAndPw(conn, member);
			conn.commit();
		}catch (Exception e) {
			try {
				conn.rollback(); 
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return returnMember;
	}
	
	// 회원가입
	public int insertMember(Member member) {
		int result = 0;
		Connection conn = null;
		memberDao = new MemberDao();
	try {
		conn = DBUtil.getConnection();		
		result = memberDao.insertMember(conn, member);
		conn.commit(); 
	} catch (Exception e) {
		try {
			conn.rollback(); 
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();
	} finally {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	return result;
	}
	
	// 수정
	public int modifyMember(Member member) {
		int result = 0;
		Connection conn = null;
		memberDao = new MemberDao();
	try {
		conn = DBUtil.getConnection();		
		result = memberDao.updateMember(conn, member);
		conn.commit(); 
	} catch (Exception e) {
		try {
			conn.rollback(); 
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();
	} finally {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	return result;
	}
	
	// 탈퇴 
	public int removeMember(Member member) {
		int result = 0;
		Connection conn = null; 
		memberDao = new MemberDao();
		try {
			conn = DBUtil.getConnection();		
			result = memberDao.deleteMember(conn, member);
			conn.commit(); 
		} catch (Exception e) {
			try {
				conn.rollback(); 
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return result;		
	}	
	
}
