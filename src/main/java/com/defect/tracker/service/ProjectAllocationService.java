package com.defect.tracker.service;

import java.util.List;

import com.defect.tracker.entities.ProjectAllocation;
import com.defect.tracker.response.dto.ProjectAllocationResponse;
import com.defect.tracker.resquest.dto.ProjectAllocationRequest;

public interface ProjectAllocationService {
  public boolean existsByEmployee(Long employeeid);

  public boolean existsByRole(Long roleId);

  public void saveProjectAllocation(ProjectAllocationRequest allocationRequest);

  public List<ProjectAllocationResponse> getAllProjectAllocation();

  public ProjectAllocationResponse getProjectAllocationById(Long id);

  public boolean existsByProjectAllocation(Long id);

  public List<ProjectAllocation> getAllProjectAllocationByProjectId(Long id);

  public boolean existsByProjectId(Long id);

  public boolean existsByEmployeeId(Long id);

  public boolean existsByRoleId(Long id);

  boolean existsByEmployeeAndProject(Long employeeId, Long projectId);

  public boolean existByProjectAllocation(Long id);



}
