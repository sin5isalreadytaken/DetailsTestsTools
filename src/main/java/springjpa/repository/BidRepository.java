package springjpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import springjpa.model.Bid;


/**
 * Created by wenxiangzhou214164 on 2017/9/15
 */
public interface BidRepository extends CrudRepository<Bid, Long> {

    @Query("select min(b.id) from Bid b")
    long findMinId();
}
