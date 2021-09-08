package com.nsia.officems.reception.visit.dto;

import com.nsia.officems._admin.department.DepartmentService;
import com.nsia.officems._util.DateTimeChange;
import com.nsia.officems.reception.visit.Visit;

public class VisitMapper {
    public static VisitDto MapVisitToVisitDto(Visit visit, VisitDto visitDto, DepartmentService departmentService){
        DateTimeChange changeDate = new DateTimeChange();
        try {
            visitDto.setId(visit.getId());
            visitDto.setSubject(visit.getSubject());
            visitDto.setHost(visit.getHost());
            visitDto.setSource(visit.getSource());
            visitDto.setVisitDate(visit.getVisitDate() == null ? null
                : changeDate.convertGregorianDateToPersianDate(visit.getVisitDate()));
            visitDto.setVisitTime(visit.getVisitTime().toString());
            visitDto.setRemarks(visit.getRemarks());
            visitDto.setDepartment(departmentService.findById(visit.getDepartment().getId()).getNameDr());
            visitDto.setCategory(visit.getCategory());
            visitDto.setType(visit.getType());
            return visitDto;
        } catch (Exception e) {
            System.out.println("Exception occured in mapping");
            return null;
        }
    }
}
