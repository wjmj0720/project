package com.ohgirafers.dao;

import com.ohgirafers.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    /*사용자 상세조회*/
    public String findUser(String username) {
        User user = null;
        // 1. 쿼리 생성 --> 쿼리 조건을 이용해 원하는 사용자 선택하기
        String sql = "select * from users where username = ?";
        // 2. statement 객체 생성
        try(PreparedStatement pstmt = connection.prepareStatement(sql)){
            // 3. 쿼리 매핑
            pstmt.setString(1, username);
            // 4. 쿼리 실행
            ResultSet rs = pstmt.executeQuery();
            String name = null; //변수 초기화
            while(rs.next()){
                // 6. 결과 담기
                name = rs.getString("username");

            }
            // 7. 반환
            return name;

        }catch(SQLException e){
            e.printStackTrace();
        }
       return null;
    }

    //전체 사용자 조회
    public List<String> findAll() throws SQLException{ //오류를 내가 해결x -> 서비스에서 해결해
        //1. 쿼리문 생성
        String query = "select * from users";
        //2. statement 객체 생성 -> connection 객체에서 만드는 거구나
        PreparedStatement pstmt = connection.prepareStatement(query);
        //4. 쿼리 실행 & // 쿼리 결과 담기
        ResultSet rs = pstmt.executeQuery();

        //반환 값 만들기 --> 반복문 밖에
        List<String> users = new ArrayList<>();

        //6. 쿼리 결과'들' 확인 <- 반복한다
        while(rs.next()){
            //7. 쿼리 값 담기
            users.add(rs.getString("username"));
        }
        //8. 반환
        return users;
    }

    /*사용자 추가*/
    public boolean addUser(User user) throws SQLException {
    //1.쿼리문 작성
    String query = "insert into users(email,password_hash,username,role_id) values(?,?,?,?)";
    //2.statement 객체 생성
        try(PreparedStatement pstmt = connection.prepareStatement(query)){
    //쿼리에 값 넣기 (쿼리 매핑)
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getRoleId());
    //3.쿼리 실행 & 쿼리가 잘 담겼는지 확인하기 -> executeUpdate() 값이 증가 -> 성공/값이 증가x -> 실패
            int affectedRows = pstmt.executeUpdate();
    //7.service에 결과값 반환  ---> 메서드 addUser가 boolean타입인 이유 --> 추가 성공/실패를 넘겨주기 위함
            return affectedRows > 0; //0보다 크면 true 반환

        }catch (SQLException e){
            e.printStackTrace();
        }

        return false; //그렇지 않으면 false

    }

    /*사용자 삭제*/
    public boolean deleteUser(String email1) throws SQLException {
        // 1. 쿼리문 작성
            String query = "DELETE FROM users WHERE email = ?";
        // 2. statement 객체 생성
       try(PreparedStatement pstmt = connection.prepareStatement(query)){
        // 3. 쿼리매핑
        pstmt.setString(1, email1);
        // 4. 쿼리실행 & 실행 확인 --> excuteUpdate() 값이 증가 -> 성공 /값 증가x -> 실패
        int affectedRows = pstmt.executeUpdate();
        return affectedRows > 0; //0보다 크면 true 반환
        // 5. service에 값 반환 --> 성공 : true / 실패 : false 반환

    }catch (SQLException e){
           e.printStackTrace();
       }
       return false;
    }

    /*사용자 수정*/
    public boolean updateUser(int userId,String email,String password,String name,String roleId) throws SQLException {
        //1. 쿼리문 작성 --->> 데이터베이스에 이름 맞춰주기
        String query = "UPDATE users set user_id =?,email = ?, password_hash = ?, username = ?, role_id =?  WHERE user_id =?";
        //2.statement객체 생성
        try(PreparedStatement pstmt = connection.prepareStatement(query)) {
            //3. 쿼리 매핑
            pstmt.setInt(1, userId);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setString(4, name);
            pstmt.setString(5, roleId);
            pstmt.setInt(6, userId);
            //4.쿼리 실행 & 실행확인
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
            //5.service에 반환
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
