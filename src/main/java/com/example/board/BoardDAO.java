package com.example.board;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDAO {
    @Autowired
    SqlSession sqlSession;

    public int insertBoard(BoardVO vo){
        return sqlSession.insert("Board.insertBoard",vo);
    }

    public BoardVO getBoard(int seq) {
        return sqlSession.selectOne("Board.getBoard",seq);
    }

    public int deleteBoard(int seq){
        return sqlSession.delete("Board.deleteBoard",seq);
    }

    public int updateBoard(BoardVO vo){
        return sqlSession.update("Board.updateBoard",vo);
    }


    public List<BoardVO> getBoardList(){
        List<BoardVO> list = sqlSession.selectList("Board.getBoardList");
        return list;
    }

}
