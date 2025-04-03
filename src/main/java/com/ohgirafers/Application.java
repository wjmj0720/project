package com.ohgirafers;


import com.ohgirafers.model.User;
import com.ohgirafers.service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws SQLException {

        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);
        System.out.println("1번 : 특정 아이디 조회");
        System.out.println("2번 : 전체 사용자 조회");
        System.out.println("3번 : 사용자 추가");
        System.out.println("4번 : 사용자 삭제");
        System.out.println("5번 : 사용자 수정");

        while(true){
            int num = scanner.nextInt();
            scanner.nextLine();

            switch(num){
                case 1:

                   //1. 아이디 입력 받기
                    System.out.println("이름을 입력하세요");
                    String username = scanner.nextLine();

                   //2. 서비스에 아이디 찾는 곳으로 아이디 넘겨주기 &  3. 유저 반환
                    String foundUser = userService.findUser(username); //변수 foundUser에 값을 받음 , userService의 findUser 메서드에
                                                                         // 유저를 조회하기 위한 id 값을 담아서 반환
                    System.out.println(foundUser);
                    break;

                /*전체 조회하기*/
                case 2:

                        //서비스 부르기 & 반환받기 --> 여러개 받음 : 리스트? 배열? -> 리스트

                    List<String> users = userService.findAll();
                    System.out.println(users);
                    break;

                    /*사용자 추가하기*/
                case 3:

                    //등록할 email 입력받기, email 자료형은 String으로
                    System.out.println("이메일을 입력하세요 : ");
                    String email = scanner.nextLine(); //nextLine -> 입력 받은 값을 한줄로 읽어오는 메서드
                    //등록할 password 입력받기, String으로
                    System.out.println("비밀번호를 입력하세요 : ");
                    String password = scanner.nextLine();
                    //등록할 name 입력받기, String으로
                    System.out.println("이름을 입력하세요 : ");
                    String name = scanner.nextLine();
                    //등록할 roleId 입력받기, String으로
                    System.out.println("사용자 역할(1,2,3)을 입력하세요 : ");
                    String roleId = scanner.nextLine();

                    //서비스에 이메일과 패스워드 입력해주는 메서드로 넘겨주기 -> 넘겨주기 위해서 생성자에? 값 담아 service로 보내기

                    // -여러개를 받아와야됨 -> 리스트에 담아서 받아와야 되나?
                    // -String result로 받아오기 -> 두 값을 문자열로 결합해서 담아야됨 -> 별로인듯
                    // -리스트 보다 객체를 생성해서 받아오기 : 하나의 사용자에 속하는 두 가지 속성이므로 리스트보다 사용자 정보를 담는 객체를 만들어 담자
                    User user = new User(0,email,password,name,roleId,null); //메서드로 넘겨주기 위해 객체에 값을 담음
                    boolean result = userService.addUser(user); // boolean타입으로 전달을 주고 받아야되므로 boolean변수 사용해서 서비스로 담아서 넘겨주기

                    System.out.println(result);
                    break;

                    /*사용자 삭제하기*/
                case 4:
                    //등록할 email 입력받기, email 자료형은 String으로
                    System.out.println("이메일을 입력하세요 : ");
                    String email1 = scanner.nextLine(); //nextLine -> 입력 받은 값을 한줄로 읽어오는 메서드

                   //User user1 = new User(0,email1,password1,name1,roleId1,null);
                    //삭제 성공 실패를 받기위해 boolean형태의 변수로 담아서 service에 넘기기
                    result = userService.deleteUser(email1);

                    System.out.println(result);
                    break;

                    /*사용자 수정하기*/
                case 5:
                    /*수정할 값 입력받기*/

                    //조회할 userId 입력받기, int형으로
                    System.out.println("유저 아이디를 입력해주세요 : ");
                    int userId = scanner.nextInt();
                    scanner.nextLine();//개행문자 소비
                    //수정할 email 입력받기, String형으로
                    System.out.println("이메일을 입력해주세요 : ");
                    email = scanner.nextLine();
                    //수정할 password 입력받기, String형으로
                    System.out.println("비밀번호를 입력해주세요 : ");
                    password = scanner.nextLine();
                    //수정할 name 입력받기, String형으로
                    System.out.println("이름을 입력해주세요 : ");
                    name = scanner.nextLine();
                    //수정할 roleId 입력받기, String 형으로
                    System.out.println("사용자 역할(1,2,3)을 입력해주세요 : ");
                    roleId = scanner.nextLine();
                    //서비스에 입력받은 값'들'을 수정하는 메서드로 넘겨주기 -> 여러개를 넘겨야하므로 객체?생성자?에 담아서 넘겨주기
                    //User user = new User(0,email,password,name,roleId,null);

                    //수정 성공 실패를 받아야되므로 boolean형태의 변수에 담아서 넘기기
                    result = userService.updateUser(userId,email,password,name,roleId);

                    //사용자에게 보여주기
                    System.out.println(result);
                    break;


                default:
                    break;
            }
        }

//        Connection con= JDBCConnection.getConnection();
//        System.out.println(con);
//        con.close();
//        System.out.println(con.isClosed());
    }
}
