package spk.dto.User;

public class UserRequestLoginDTO{
    private String nickname;
    private String password;

    public UserRequestLoginDTO(){}

    public UserRequestLoginDTO(String nickname,String password){
        this.nickname = nickname;
        this.password = password;
    }


    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
