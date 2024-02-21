package org.example.schoolapi.child.repository;

import org.example.schoolapi.domain.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {

    List<Child> findAllBySchoolId(Long schoolId);
    List<Child> findAllByParentId(Long parentId);
}
