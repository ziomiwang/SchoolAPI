package org.example.schoolapi.attendance.repository;

import org.example.schoolapi.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findAllByChildId(Long childId);

    List<Attendance> findAllByEntryDateAfterAndExitDateBeforeAndChild_Id(LocalDateTime before, LocalDateTime after, Long id);
}
