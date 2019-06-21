package spring.dto;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: yz<br/>
 * Date: 6/13/2019<br/>
 * Time: 8:28 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
@Data
@RedisHash("Comment")
public class Comment implements Serializable {

    public Comment() {

    }

    public enum Status {
        ON, OFF
    }

    @Id
    private int id;

    private String url;

    private List<String> cmts;
}
