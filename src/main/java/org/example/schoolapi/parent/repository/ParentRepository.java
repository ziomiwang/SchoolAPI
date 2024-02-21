package org.example.schoolapi.parent.repository;

import java.util.List;

import org.example.schoolapi.domain.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {

    List<Parent> findAllBySchool_Id(final long id);

}
