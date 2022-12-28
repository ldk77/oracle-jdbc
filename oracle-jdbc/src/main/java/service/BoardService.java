package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.BoardDao;
import util.DBUtil;
import vo.Board;
import vo.Member;

public class BoardService {
	private BoardDao boardDao;
	
	//목록
	public ArrayList<Board> getBoardListByPage(int currentPage, int rowPerPage) {
		/*
		 	1) connection 생성 <- DBUtil.class
		 	2) beginRow, endRow 생성 <- currentPage,rowPerPage를 가공
		 */
		ArrayList<Board> list = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			int beginRow = (currentPage-1)*rowPerPage+1;
			int endRow = beginRow + rowPerPage - 1;
			boardDao = new BoardDao();
			list = boardDao.selectBoardListByPage(conn, beginRow, endRow);
			conn.commit(); // DBUtil.class에서 conn.setAutoCommit(false);
		} catch (Exception e) {
			try {
				conn.rollback(); // DBUtil.class에서 conn.setAutoCommit(false);
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
		return list;
	}
	//삭제 
	public int deleteBoard(int boardNo, Member member) {
		int result = 0;
		Connection conn = null; 
		boardDao = new BoardDao();		
		try {
			conn = DBUtil.getConnection();
			result = boardDao.deleteBoard(conn, boardNo, member);
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
	// 추가
	public int insertBoard(Board board) {
		int result = 0;
		Connection conn = null;
		boardDao = new BoardDao();
	try {
		conn = DBUtil.getConnection();		
		result = boardDao.insertBoard(conn, board);
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
	public int updateBoard(Board board) {
		int result = 0;
		Connection conn = null;
		boardDao = new BoardDao();
	try {
		conn = DBUtil.getConnection();		
		result = boardDao.updateBoard(conn, board);
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
	// 상세보기 
	public Board getBoardOne(int boardNo) {
		Connection conn =null; 
		boardDao = new BoardDao();
		Board board = null; 
		try {
			conn = DBUtil.getConnection();
			board = boardDao.selectBoardOne(conn, boardNo);
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
		return board;	
	}
}