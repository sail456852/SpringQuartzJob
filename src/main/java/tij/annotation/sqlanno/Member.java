package tij.annotation.sqlanno;

/**
 * Created by Okita.<br/>
 * User: yz<br/>
 * Date: 12/24/18<br/>
 * Time: 10:50 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
@DBTable(name = "MEMBER")
public class Member {
    @SQLString(30)  String firstName;
    @SQLString(50)  String lastName;
    @SQLInteger Integer age;
    @SQLString(value = 30, constrains = @Constraints(primaryKey = true)) String handle;
    static int memeberCount;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getHandle() {
        return handle;
    }

    @Override
    public String toString() {
        return handle;
    }
}
