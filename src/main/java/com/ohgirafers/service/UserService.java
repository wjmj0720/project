package com.ohgirafers.service;

import com.ohgirafers.config.JDBCConnection;
import com.ohgirafers.dao.UserDao;
import com.ohgirafers.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDao userDao;
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService() {
        try {
            this.userDao = new UserDao(JDBCConnection.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }//JDBCConection을 통해 데이터베이스와 연결

    /* 사용자 상세조회 */
    public String findUser(String username) { //view에서 받은 id값이 username으로 들어감


        // 1. 아이디 빈 값인지 확인
        if (username == null || username == "" || username == " ") {
            // 만약 빈값이면 아이디 입력 요구
            return "아이디를 입력해주세요";
        }
        // 2. Dao에 전달하고 유저가 있으면 유저 반환
        String user = userDao.findUser(username);

        // 3. 유저를 view에 반환
        return user;
    }

    //전체 조회
    public List<String> findAll() throws SQLException {
        //Dao에 사용자 전체 데이터 요청하기 & 반환 받기
        List<String> users = userDao.findAll();
        if(users == null){ //만약 users가 비어있다면 오류 출력
            logger.error("조회한 사용자의 정보가 없거나 DB와 연결하는 과정에서 오류가 발생했습니다.");
            return null;
        }
        //반환하기 -->dao에서 받은 값을 view로 전달해주기 위해
        return users;

    }

    /*회원 등록하기*/
    public boolean addUser(User user) throws SQLException {//User user -> user를 가져와서 빈칸 확인

            // 아이디 검증 (아이디 빈값 확인) & 비밀번호 검증 (비밀번호 빈값 확인)

            //email과 password가 빈칸이라면
            //만약 email과 비밀번호가 null이거나 빈값
            if(user.getEmail().isBlank()|| user.getEmail()==null || user.getPassword().isBlank() || user.getPassword()==null) {
                //이메일과 패스워드 작성해달라 요청
                System.out.println("이메일과 패스워드를 작성해주세요");
            }else{
            /*중복확인*/
            //전체조회 메서드 가져와서 모든 이메일과 비교 -> 리스트 값을 순회 (for-each 사용?)
            //--입력에 대한 새로운 list<String> 배열을 만들고 사용자 전체조회 메서드를 대입 --> 입력값이 이미 존재하는 값인지 확인하기 위함
            List<String> emails = findAll();
            //리스트emails를 순회해서 email에 하나씩 값을 담아줌
            for(String email : emails){
            //만약 email값과 입력받은 값이 같을 경우
                if(email.equals(user.getEmail())){
                    throw new IllegalArgumentException("이미 존재하는 이메일입니다");
                }
            }
                }
        //dao의 등록메서드로 값 반환
        return userDao.addUser(user);
    }

    /*회원 삭제하기*/
    public boolean deleteUser(String email1) throws SQLException {
        //특정 ID 조회메서드를 search에 담아주기
            String search = findUser(email1); //email1을 대입해줌
        if(search == null) {
            throw new IllegalArgumentException("삭제할 사용자를 찾을 수 없습니다.");
        }
        //DAO로 값 넘겨주기
            return userDao.deleteUser(email1);
    }

    /*회원 수정하기*/
    public boolean updateUser(int userId,String email,String password,String name,String roleId) throws SQLException {

       //받은 email값을 유저 조회 메서드에 넣어서 String search에 대입해줌
            String search = findUser(email);
            if(search == null) { //email값이 비어있다면 오류 발생
                throw new IllegalArgumentException("수정할 사용자를 찾을 수 없습니다.");
            }//DAO로 값 넘겨주기
            return userDao.updateUser(userId,email,password,name,roleId);
    }
}




