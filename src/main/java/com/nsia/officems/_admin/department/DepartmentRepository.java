package com.nsia.officems._admin.department;

import java.util.List;

import com.nsia.officems._admin.department.proj.DepartmentLookupProj;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Procedure("add_department")
    public Long addDepartment(Long parent_id, String dep_name_en, String dep_name_dr, String dep_name_ps,String header,String footer);

    @Query(value="SELECT dep.id, dep.name_dr as name, dep.parent_id as parent FROM department dep", nativeQuery = true) 
    public List<DepartmentLookupProj> findAllDepartmentsDr();
    @Query(value="SELECT dep.id, dep.name_ps as name, dep.parent_id as parent FROM department dep", nativeQuery = true) 
    public List<DepartmentLookupProj> findAllDepartmentsPs();
    @Query(value="SELECT dep.id, dep.name_en as name, dep.parent_id as parent FROM department dep", nativeQuery = true) 
    public List<DepartmentLookupProj> findAllDepartmentsEn();
    
    @Query(value="SELECT dep.id, dep.name_dr as name, dep.parent_id as parent FROM department dep where parent_id is null", nativeQuery = true) 
    public List<DepartmentLookupProj> findByParentIsNullDr();
    @Query(value="SELECT dep.id, dep.name_ps as name, dep.parent_id as parent FROM department dep where parent_id is null", nativeQuery = true) 
    public List<DepartmentLookupProj> findByParentIsNullPs();
    @Query(value="SELECT dep.id, dep.name_en as name, dep.parent_id as parent FROM department dep where parent_id is null", nativeQuery = true) 
    public List<DepartmentLookupProj> findByParentIsNullEn();

    @Query(value="SELECT dep.id, dep.name_dr as name, dep.parent_id as parent FROM department dep where parent_id=?1", nativeQuery = true) 
    public List<DepartmentLookupProj> findDirectChildrenByParentIdDr(Long departmentId);
    @Query(value="SELECT dep.id, dep.name_ps as name, dep.parent_id as parent FROM department dep where parent_id=?1", nativeQuery = true) 
    public List<DepartmentLookupProj> findDirectChildrenByParentIdPs(Long departmentId);
    @Query(value="SELECT dep.id, dep.name_en as name, dep.parent_id as parent FROM department dep where parent_id=?1", nativeQuery = true) 
    public List<DepartmentLookupProj> findDirectChildrenByParentIdEn(Long departmentId);

    @Query(value="SELECT dep.id FROM department dep JOIN department_closure depc ON (dep.id = depc.descendant_id) WHERE depc.ancestor_id = ?1", nativeQuery = true) 
    public List<Long> findDescendantIds(Long departmentId);
    @Query(value="SELECT dep.id FROM Department dep JOIN department_closure depc ON (dep.id = depc.ancestor_id) WHERE depc.descendant_id = ?1", nativeQuery = true) 
    public List<Long> findAncestorIds(Long departmentId);

    @Query(value="SELECT dep.id, dep.name_dr as name, dep.parent_id as parent FROM department dep JOIN department_closure depc ON (dep.id = depc.descendant_id) WHERE depc.ancestor_id = ?1", nativeQuery = true) 
    public List<DepartmentLookupProj> findDescendantsDr(Long departmentId);
	@Query(value="SELECT dep.id, dep.name_ps as name, dep.parent_id as parent FROM department dep JOIN department_closure depc ON (dep.id = depc.descendant_id) WHERE depc.ancestor_id = ?1", nativeQuery = true) 
    public List<DepartmentLookupProj> findDescendantsPs(Long departmentId);
	@Query(value="SELECT dep.id, dep.name_en as name, dep.parent_id as parent FROM department dep JOIN department_closure depc ON (dep.id = depc.descendant_id) WHERE depc.ancestor_id = ?1", nativeQuery = true) 
    public List<DepartmentLookupProj> findDescendantsEn(Long departmentId);




    @Query(value="SELECT dep.id, dep.name_ps, dep.name_dr, dep.name_en FROM Department dep JOIN department_closure depc ON (dep.id = depc.ancestor_id) WHERE depc.descendant_id = ?1", nativeQuery = true) 
    public List<Department> findAncestors(Long departmentId);

    // @Query(value="INSERT INTO department_closure(ancestor_id, descendant_id) SELECT ancestor_id, 1? FROM department_closure where descendant_id=?2 UNION ALL ?1, ?1", nativeQuery = true) 
    // public List<Department> insertClosure(Long newDepartmentId, Long directParentDepartmentId);

    
}   
