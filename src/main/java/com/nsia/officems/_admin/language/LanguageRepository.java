package com.nsia.officems._admin.language;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {

	public List<Language> findByDeletedFalseOrDeletedIsNullAndActiveTrue(); 

	public List<Language> findByIdIn(List<Long> languages);
}
