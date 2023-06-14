package com.defect.tracker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.defect.tracker.common.response.BaseResponse;
import com.defect.tracker.common.response.ContentResponse;
import com.defect.tracker.resquest.dto.ProjectAllocationRequest;
import com.defect.tracker.rest.enums.RequestStatus;
import com.defect.tracker.service.EmployeeService;
import com.defect.tracker.service.ProjectAllocationService;
import com.defect.tracker.service.ProjectService;
import com.defect.tracker.service.RoleService;
import com.defect.tracker.utils.Constants;
import com.defect.tracker.utils.EndpointURI;
import com.defect.tracker.utils.ValidationFailureResponseCode;

@RestController
@CrossOrigin
public class ProjectAllocationController {
  @Autowired
  private ProjectAllocationService projectAllocationService;
  @Autowired
  private ValidationFailureResponseCode validationFailureResponseCode;
  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private ProjectService projectService;
  @Autowired
  private RoleService roleService;
  private static final Logger logger = LoggerFactory.getLogger(ProjectAllocationController.class);

  @PostMapping(value = EndpointURI.PROJECTALLOCATION)
  public ResponseEntity<Object> saveProjectAllocation(
      @RequestBody ProjectAllocationRequest projectAllocationRequest) {
    if (projectAllocationService.existsByEmployeeAndProject(
        projectAllocationRequest.getEmployee_id(), projectAllocationRequest.getProject_id())) {
      return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
          validationFailureResponseCode.getProjectAllocationAlreadyExists(),
          validationFailureResponseCode.getValidationProjectAllocationAlreadyExists()));
    }
    if (!employeeService.existByEmployee(projectAllocationRequest.getEmployee_id())) {
      logger.info("Employee not Extists");
      return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
          validationFailureResponseCode.getEmployeeNotExistsCode(),
          validationFailureResponseCode.getEmployeeNotExistsMessage()));
    }
    if (!projectService.existsByProject(projectAllocationRequest.getProject_id())) {
      logger.info("Project not Extists");
      return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
          validationFailureResponseCode.getProjectNotExistesCode(),
          validationFailureResponseCode.getProjectNotExistsMessage()));
    }
    if (!roleService.existByRole(projectAllocationRequest.getRole_id())) {
      logger.info("Role not Extists");
      return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
          validationFailureResponseCode.getRoleNotExistesCode(),
          validationFailureResponseCode.getRoleNotExistsMessage()));
    }
    logger.info("ProjectAllocation Added successfully");
    projectAllocationService.saveProjectAllocation(projectAllocationRequest);
    return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
        validationFailureResponseCode.getCommonSuccessCode(),
        validationFailureResponseCode.getSaveProjectAllocationSuccessMessage()));
  }

  @GetMapping(value = EndpointURI.PROJECTALLOCATION)
  public ResponseEntity<Object> getAllProjectAllocations() {
    logger.info("All projectAllocation retrieved successfully");
    return ResponseEntity.ok(new ContentResponse<>(Constants.PROJECTALLOCATION,
        projectAllocationService.getAllProjectAllocation(), RequestStatus.SUCCESS.getStatus(),
        validationFailureResponseCode.getCommonSuccessCode(),
        validationFailureResponseCode.getGetAllProjectAllocationSuccessMessage()));
  }

  @GetMapping(value = EndpointURI.PROJECTALLOCATION_BY_ID)
  public ResponseEntity<Object> getProjectAllocationById(@PathVariable Long id) {
    if (!projectAllocationService.existsByProjectAllocation(id)) {
      logger.info("ProjectAllocation is not exists");
      return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
          validationFailureResponseCode.getProjectAllocationNotExistesCode(),
          validationFailureResponseCode.getProjectAllocationNotExistsMessage()));
    }
    logger.info("ProjectAllocation retrieved successfully for given id");
    return ResponseEntity.ok(new ContentResponse<>(Constants.PROJECTALLOCATION,
        projectAllocationService.getProjectAllocationById(id), RequestStatus.SUCCESS.getStatus(),
        validationFailureResponseCode.getCommonSuccessCode(),
        validationFailureResponseCode.getGetProjectAllocationByIdSuccessMessage()));
  }

  @GetMapping(value = EndpointURI.PROJECTALLOCATION_BY_PROJECTID)
  public ResponseEntity<Object> getProjectAllocationByPrijectId(@PathVariable Long id) {
    if (!projectAllocationService.existsByProjectId(id)) {
      logger.info("Project Not Exists");
      return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
          validationFailureResponseCode.getProjectAllocationNotExistesCode(),
          validationFailureResponseCode.getProjectAllocationNotExistsMessage()));
    }
    logger.info("ProjectAllocation retrieved successfully for given Project Id");
    return ResponseEntity.ok(new ContentResponse<>(Constants.PROJECTALLOCATIONS,
        projectAllocationService.getAllProjectAllocationByProjectId(id),
        RequestStatus.SUCCESS.getStatus(), validationFailureResponseCode.getCommonSuccessCode(),
        validationFailureResponseCode.getGetAllProjectAllocationSuccessMessage()));
  }

  @PutMapping(value = EndpointURI.PROJECTALLOCATION)
  public ResponseEntity<Object> updateProjectAllocation(
      @RequestBody ProjectAllocationRequest projectAllocationRequest) {
    if (!projectAllocationService.existsByProjectAllocation(projectAllocationRequest.getId())) {
      logger.info("ProjectAllocation is not exists");
      return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
          validationFailureResponseCode.getProjectAllocationNotExistesCode(),
          validationFailureResponseCode.getProjectAllocationNotExistsMessage()));
    }
    if (!projectService.existsByProject(projectAllocationRequest.getProject_id())) {
      logger.info("Project not Extists");
      return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
          validationFailureResponseCode.getProjectNotExistesCode(),
          validationFailureResponseCode.getProjectNotExistsMessage()));
    }
    if (!employeeService.existByEmployee(projectAllocationRequest.getEmployee_id())) {
      logger.info("Employee not Extists");
      return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
          validationFailureResponseCode.getEmployeeNotExistsCode(),
          validationFailureResponseCode.getEmployeeNotExistsMessage()));
    }
    if (!roleService.existByRole(projectAllocationRequest.getRole_id())) {
      logger.info("Role not Extists");
      return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
          validationFailureResponseCode.getRoleNotExistesCode(),
          validationFailureResponseCode.getRoleNotExistsMessage()));
    }
    logger.info("ProjectAllocation updated successfully");
    projectAllocationService.saveProjectAllocation(projectAllocationRequest);
    return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
        validationFailureResponseCode.getCommonSuccessCode(),
        validationFailureResponseCode.getUpdateProjectAllocationSuccessMessage()));
  }
}
