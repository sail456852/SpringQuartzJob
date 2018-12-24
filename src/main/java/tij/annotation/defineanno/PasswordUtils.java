package tij.annotation.defineanno;

import java.util.List;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2018/12/24<br/>
 * Time: 16:37<br/>
 * To change this template use File | Settings | File Templates.
 */
public class PasswordUtils {
    @UseCase(id = 47, description =
            "Passwords must contain at least one numeric")
    public boolean validatePassword(String password) {
        return (password.matches("\\w*\\d\\w*"));
    }
    @UseCase(id = 48)
    public String encryptPassword(String password) {
        return new StringBuilder(password).reverse().toString();
    }
    @UseCase(id = 49, description =
            "New passwords can’t equal previously used ones")
    public boolean checkForNewPassword(
            List<String> prevPasswords, String password) {
        return !prevPasswords.contains(password);
    }
}
