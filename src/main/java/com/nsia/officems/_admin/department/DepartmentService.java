package com.nsia.officems._admin.department;

import java.util.List;
import java.util.Map;

import com.nsia.officems._admin.department.dto.DepartmentDto;
import com.nsia.officems._admin.department.proj.DepartmentLookupProj;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

public interface DepartmentService {
    public Object getList(DataTablesInput input, Map<String, String> filters);
    public List<Department> findAll();
    public List<DepartmentLookupProj> findAllDepartments();
    public List<DepartmentLookupProj> findEntities();
    public List<DepartmentLookupProj> findDirectChildDepartments(Long departmentId);
    public List<DepartmentLookupProj> findChildDepartments(Long departmentId);

    public List<Long> findAncestorIds(Long departmentId);
    public List<Long> findDescendantIds(Long departmentId);
    public Department findById(Long id);

    public Department create(Department department);
    public Long createWithClosure(DepartmentDto departmentDto);

    public Boolean delete(Long id);

    public boolean update(Long id, Department department);

}
