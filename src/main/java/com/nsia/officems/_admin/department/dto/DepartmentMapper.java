package com.nsia.officems._admin.department.dto;

import com.nsia.officems._admin.department.Department;
import com.nsia.officems._util.DateTimeChange;

public class DepartmentMapper {
    public static Department map(DepartmentDto dto, Department department)
    {
        DateTimeChange changeDate = new DateTimeChange();
        if(department == null)
            department = new Department();
        
        department.setId(dto.getId());
        
        return department;
    }

}
