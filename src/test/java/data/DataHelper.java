package data;

import lombok.Value;

public class DataHelper {


    public static class AuthInfo {
        private String login;
        private String password;

        public AuthInfo(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123" );
    }


    public static class VerificationCode {
        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public VerificationCode(String code) {
            this.code = code;
        }
    }

    public static VerificationCode getVerificationCode(AuthInfo authInfo) { // в скобках аутинфо
        return new VerificationCode("12345");
    }
}
