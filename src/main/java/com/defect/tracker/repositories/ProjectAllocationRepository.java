package com.defect.tracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.defect.tracker.entities.ProjectAllocation;

public interface ProjectAllocationRepository extends JpaRepository<ProjectAllocation, Long> {
  boolean existsByEmployeeId(Long employeeid);

  boolean existsByRoleId(Long roleId);

  List<ProjectAllocation> findByProjectId(Long projectId);

  boolean existsByProjectId(Long id);

  boolean existsByEmployeeIdAndProjectId(Long employeeId, Long projectId);  
}
