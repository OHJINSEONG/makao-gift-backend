package megaptera.makaogift.exceptions;

import megaptera.makaogift.models.UserName;

public class SignUpFaild extends RuntimeException {
    public SignUpFaild(String password) {
        super("비밀번호가 일치하지 않습니다.");
    }

    public SignUpFaild(UserName userName) {
        super("해당 아이디는 사용할수 없습니다. " + userName);
    }
}
