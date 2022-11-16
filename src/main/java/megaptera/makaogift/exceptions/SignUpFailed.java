package megaptera.makaogift.exceptions;

import megaptera.makaogift.models.UserName;

public class SignUpFailed extends RuntimeException {
    private Integer code;

    public SignUpFailed(String password) {
        super("비밀번호가 일치하지 않습니다.");
        this.code = 1000;
    }

    public SignUpFailed(UserName userName) {
        super("해당 아이디는 사용할수 없습니다.");
        this.code = 1001;
    }

    public Integer getCode() {
        return code;
    }
}
