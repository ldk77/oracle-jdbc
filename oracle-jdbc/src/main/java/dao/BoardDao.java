package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Board;
import vo.Member;

public class BoardDao {
	//수정 
	public int updateBoard(Connection conn, Board board, Member member) throws Exception {
		int result = 0;
		String sql = "update board SET board_title =?, board_content=? WHERE board_no=? and member_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, board.getBoardTitle());
		stmt.setString(2, board.getBoardContent());	
		stmt.setInt(3, board.getBoardNo());
		stmt.setString(4, member.getMemberId());
		result = stmt.executeUpdate();
		return result;
	}	
	
	//삭제 
	public int deleteBoard(Connection conn, int boardNo, Member member) throws Exception {
	int result = 0;	
	String sql = "DELETE FROM board WHERE board_no = ? and member_id = ?";
	PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.setInt(1, boardNo);
	stmt.setString(2, member.getMemberId());
	result = stmt.executeUpdate();
	return result;
	}
	
	// 상세보기 
	public Board selectBoardOne(Connection conn, int boardNo) throws Exception {
		Board board = new Board();
		String sql = "SELECT board_no boardNo, board_title boardTitle, board_content boardContent,createdate,updatedate,member_id memberId FROM board WHERE board_no = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, boardNo);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			board.setBoardNo(rs.getInt("boardNo"));
			board.setBoardTitle(rs.getString("boardTitle"));
			board.setBoardContent(rs.getString("boardContent"));
			board.setCreatedate(rs.getString("createdate"));
			board.setUpdatedate(rs.getString("updatedate"));
			board.setMemberId(rs.getString("memberId"));
		}
		return board;
		
	}
	
	// 페이징
	public ArrayList<Board> selectBoardListByPage(Connection conn, int beginRow, int endRow, String searchTitle) throws Exception {
		ArrayList<Board> list = new ArrayList<Board>();
		String sql = "SELECT board_no boardNo, board_title boardTitle, createdate"
				+ " FROM (SELECT rownum rnum, board_no, board_title, createdate"
				+ "			FROM (SELECT board_no, board_title, createdate"
				+ "					FROM board WHERE board_title LIKE ? ORDER BY TO_NUMBER(board_no) DESC))"
				+ " WHERE rnum BETWEEN ? AND ? "; // WHERE rnum >=? AND rnum <=?;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + searchTitle + "%");
		stmt.setInt(2, beginRow);
		stmt.setInt(3, endRow);		
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Board b = new Board();
			b.setBoardNo(rs.getInt("boardNo"));
			b.setBoardTitle(rs.getString("boardTitle"));
			b.setCreatedate(rs.getString("createdate"));
			list.add(b);
		}
		return list;
	}
	// 게시판 추가
	public int insertBoard(Connection conn, Board board) throws Exception {
		int result = 0;
		String sql = "	insert into board(board_no, board_title, board_content, member_id, updatedate, createdate) "
				+ "values (board_seq.nextval, ? , ?, ?, sysdate, sysdate)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, board.getBoardTitle());
		stmt.setString(2, board.getBoardContent());
		stmt.setString(3, board.getMemberId());
		result = stmt.executeUpdate();
		return result;
	}	
	

}
