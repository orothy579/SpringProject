package com.example.board;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;



@Repository
public class BoardDAO{
	@Autowired
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template){
		this.template = template;
	}
	String BOARD_INSERT = "insert into BOARD (title, writer, content) values (?,?,?)";

	private final String BOARD_GET = "select * from BOARD  where seq=?";
	private final String BOARD_LIST = "select * from BOARD order by seq desc";

	public int insertBoard(BoardVO vo){
		return template.update(BOARD_INSERT, new Object[]{vo.getTitle() , vo.getWriter() , vo.getContent()});
	}

	public int deleteBoard(int id){
		String BOARD_DELETE = "delete from BOARD  where seq=?";
		return  template.update(BOARD_DELETE,
				new Object[]{id});

	}

	public int updateBoard(BoardVO vo){
		String BOARD_UPDATE = "update BOARD set title=?, writer=?, content=? where seq=?";
		return template.update(BOARD_UPDATE,
				vo.getTitle(),vo.getWriter(),vo.getContent(),vo.getSeq());
	}




//	public List<BoardVO> getBoardList(){
//		return template.query(BOARD_LIST, new RowMapper<BoardVO>() {
//			@Override
//			public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				BoardVO data = new BoardVO();
//				data.setSeq(rs.getInt("seq"));
//				data.setTitle(rs.getString("title"));
//				data.setContent(rs.getString("content"));
//				data.setWriter(rs.getString("writer"));
//				data.setRegdate(rs.getDate("regdate"));
//				data.setCnt(rs.getInt("cnt"));
//				return data;
//			}
//		});
//	}
//
//	public BoardVO getBoard(int seq) {
//		return template.queryForObject(BOARD_GET,
//				new Object[] {seq},
//				new BeanPropertyRowMapper<>(BoardVO.class));
//	}

	public BoardVO getBoard(int seq){
		String sql = "select * from BOARD where seq=" + seq;
		return template.queryForObject(sql, new BoardRowMapper());
	}

	public List<BoardVO> getBoardList(){
		String sql = "select * from BOARD order by regdate desc";
		return template.query(sql, new BoardRowMapper());
	}



}


class BoardRowMapper implements RowMapper<BoardVO> {
	@Override
	public BoardVO mapRow(ResultSet rs , int rowNum) throws SQLException {
		BoardVO vo = new BoardVO();
		vo.setSeq(rs.getInt("seq"));
		vo.setTitle(rs.getString("title"));
		vo.setContent(rs.getString("content"));
		vo.setWriter(rs.getString("writer"));
		vo.setRegdate(rs.getDate("regdate"));
		vo.setCnt(rs.getInt("cnt"));
		return vo;
	}


}



