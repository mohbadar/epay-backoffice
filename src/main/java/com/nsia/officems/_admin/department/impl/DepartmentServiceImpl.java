package com.nsia.officems._admin.department.impl;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nsia.officems._admin.department.Department;
import com.nsia.officems._admin.department.DepartmentRepository;
import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems._admin.department.dto.DepartmentDto;
import com.nsia.officems._admin.department.proj.DepartmentLookupProj;
import com.nsia.officems._util.DataTablesUtil;
import com.nsia.officems._identity.authentication.user.CustomUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private CustomUserService customUserService;

    @Autowired
    private DataTablesUtil dataTablesUtil;

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public List<DepartmentLookupProj> findAllDepartments() {
        String lang = customUserService.getCurrentLang();
        switch (lang) {
            case "dr":
                return departmentRepository.findAllDepartmentsDr();
            case "en":
                return departmentRepository.findAllDepartmentsEn();
            default:
                return departmentRepository.findAllDepartmentsPs();
        }
    }

    @Override
    public Object getList(DataTablesInput input, Map<String, String> filters) {
        String joinClause = " ";
        // To have first AND with no error
        String whereClause = dataTablesUtil.whereClause(filters);
        String groupByClause = "";
        return dataTablesUtil.getDataList("public.department dep", null, joinClause, whereClause, groupByClause, input);
    }

    @Override
    public List<DepartmentLookupProj> findEntities() {
        String lang = customUserService.getCurrentLang();
        switch (lang) {
            case "dr":
                return departmentRepository.findByParentIsNullDr();
            case "en":
                return departmentRepository.findByParentIsNullEn();
            default:
                return departmentRepository.findByParentIsNullPs();
        }
    }

    @Override
    public List<DepartmentLookupProj> findDirectChildDepartments(Long departmentId) {
        String lang = customUserService.getCurrentLang();
        switch (lang) {
            case "dr":
                return departmentRepository.findDirectChildrenByParentIdDr(departmentId);
            case "en":
                return departmentRepository.findDirectChildrenByParentIdEn(departmentId);
            default:
                return departmentRepository.findDirectChildrenByParentIdPs(departmentId);
        }
    }

    @Override
    public List<DepartmentLookupProj> findChildDepartments(Long departmentId) {
        String lang = customUserService.getCurrentLang();
        switch (lang) {
            case "dr":
                return departmentRepository.findDescendantsDr(departmentId);
            case "en":
                return departmentRepository.findDescendantsEn(departmentId);
            default:
                return departmentRepository.findDescendantsPs(departmentId);
        }
    }

    @Override
    public List<Long> findAncestorIds(Long departmentId) {
        return departmentRepository.findAncestorIds(departmentId);
    }

    @Override
    public List<Long> findDescendantIds(Long departmentId) {
        return departmentRepository.findDescendantIds(departmentId);
    }

    @Override
    public Department findById(Long id) {
        Optional<Department> optionalObj = departmentRepository.findById(id);
        if (optionalObj.isPresent())
            return optionalObj.get();
        return null;
    }

    @Override
    public Department create(Department department) {
        department.setDeleted(false);
        return departmentRepository.save(department);
    }

    @Override
    public Long createWithClosure(DepartmentDto departmentDto) {
        return departmentRepository.addDepartment(departmentDto.getParentId(), departmentDto.getNameEn(), departmentDto.getNameDr(),
                departmentDto.getNamePs(),departmentDto.getHeader(),departmentDto.getFooter());
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Department> department = departmentRepository.findById(id);

        if (department.isPresent()) {
            Department department2 = department.get();
            department2.setDeleted(true);
            // ethnic.setDeletedBy(userService.getLoggedInUser().getUsername());
            department2.setDeletedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            departmentRepository.save(department2);
            return true;
        }

        return false;
    }

    @Override
    public boolean update(Long id, Department department) {
        Optional<Department> departmentToBeUpdated = departmentRepository.findById(id);
        if (departmentToBeUpdated.isEmpty()) {
            return false;
        }

        Department editedDepartment = departmentToBeUpdated.get();

        editedDepartment.setNameEn(department.getNameEn());
        editedDepartment.setNameDr(department.getNameDr());
        editedDepartment.setNamePs(department.getNamePs());
        editedDepartment.setHeader(department.getHeader());
        editedDepartment.setFooter(department.getFooter());

        departmentRepository.save(editedDepartment);
        return true;
    }

}
