package spring.dto;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: yz<br/>
 * Date: 6/13/2019<br/>
 * Time: 8:28 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
@RedisHash("Comment")
public class Comment implements Serializable {
    public enum Status {
        ON, OFF
    }
    private String url;

    private String content;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
