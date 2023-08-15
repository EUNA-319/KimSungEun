package sopt.org.sixthSeminar.infrastructure;

import org.springframework.data.repository.Repository;
import sopt.org.sixthSeminar.domain.Board;


public interface BoardRepository extends Repository<Board, Long> {
    //CREATE
    void save(Board board);
}
