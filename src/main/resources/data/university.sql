INSERT INTO public.edu_university(
	id, active, code, name_dr, name_en, name_ps)
	VALUES (1, true,'', 'پوهنتون پولی تخنیک', 'kabul Polytechnic university', 'پوهنتون پولی تخنیک' );


    -- faculty
    INSERT INTO public.edu_university_faculty(
	id, active, name_dr, name_en, name_ps, university_id)
	VALUES (2, true, 'law dr', 'law', 'law ps', 1);

	-- department
	INSERT INTO public.edu_university_department(
	id, active, name_dr, name_en, name_ps, faculty_id)
	VALUES (1, true, 'IT', 'IT', 'IT', 1);

	-- academic position
	INSERT INTO public.edu_academic_position(
	id, active,name_dr, name_en, name_ps)
	VALUES (2, true, 'Head of Department','Head of Department' , 'Head of Department');

	-- academic grade
	INSERT INTO public.edu_academic_grade(
	id, active,name_dr, name_en, name_ps)
	VALUES (2, true, 'Professor','Professor' , 'Professor');

	INSERT INTO public.edu_education_level(
	id, active, name_dr, name_en, name_ps)
	VALUES (1, true, 'لیسانس', 'Bachelor', 'لیسانس');	
	VALUES (2, true, 'ماستر', 'Master', 'ماستر');	
	VALUES (3, true, 'دوکتورا', 'PhD', 'دوکتورا');