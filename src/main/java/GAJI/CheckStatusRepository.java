package GAJI;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CheckStatusRepository extends CrudRepository<CheckStatus, Long> {


        void deleteByProductId(Long productId);
}