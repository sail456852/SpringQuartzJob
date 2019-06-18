package spring.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.dto.Comment;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: yz<br/>
 * Date: 6/13/2019<br/>
 * Time: 8:26 PM<br/>
 * To change this template use File | Settings | File Templates.
 * Use Spring data framework to manipulate redis
 */
@Repository
public interface RedisRepository extends CrudRepository<Comment, String> {
}
