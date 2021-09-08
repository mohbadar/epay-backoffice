-- ###########################
CREATE OR REPLACE FUNCTION public.get_user_name(userId bigint) RETURNS varchar(250) AS $$
dECLARE
	user_name varchar(250);
BEGIN
	SELECT name into user_name from user_tbl where user_tbl.id=userId;
	RETURN user_name;
END; $$
LANGUAGE PLPGSQL;

-- ###########################
-- This function will return the visitor count of a visit
CREATE FUNCTION get_visitor_count_by_visit(visitId bigint) RETURNS integer AS $$
DECLARE
	total_visitors integer := 0;
BEGIN
	SELECT count(id) into total_visitors FROM public.recep_visitor rv
    inner join public.recep_visit_visitor rvv on rvv.visitor_id=rv.id where rvv.visit_id = visitId;
	RETURN total_visitors;
END; $$
LANGUAGE PLPGSQL;

-- ###########################
-- This function will return the vehicle count of a visit
CREATE FUNCTION get_vehicle_count_by_visit(visitId bigint) RETURNS integer AS $$
DECLARE
	total_vehicles integer := 0;
BEGIN
	SELECT count(id) into total_vehicles FROM public.recep_vehicle rv
    inner join public.recep_visit_vehicle rvv on rvv.vehicle_id=rv.id where rvv.visit_id = visitId;
	RETURN total_vehicles;
END; $$
LANGUAGE PLPGSQL;


-- ##############################
-- https://www.programmersought.com/article/11304225020/
CREATE OR REPLACE FUNCTION add_department(_parent_id bigint,
										  _dep_name_en varchar(255),
										 _dep_name_dr varchar(255),
										 _dep_name_ps varchar(255),
										 _header varchar(255),
										 _footer varchar(255))
RETURNS bigint AS $$
DECLARE
	_ancestor INT;
	_descendant INT;
-- 	_parent INT;
BEGIN
	IF NOT EXISTS(SELECT id From department WHERE name_en = _dep_name_en)
	THEN
		INSERT INTO department (id, parent_id, name_en, name_dr, name_ps,header,footer) VALUES(nextval('department_tbl_seq'), _parent_id, _dep_name_en, _dep_name_dr, _dep_name_ps,_header,_footer)
		RETURNING id INTO _descendant;
-- 		SELECT id into _descendant FROM department WHERE node_name = _node_name;

		INSERT INTO department_closure (id, ancestor_id, descendant_id, depth) VALUES(nextval('department_closure_tbl_seq'),_descendant, _descendant, 0);
		IF EXISTS (SELECT id FROM department WHERE id = _parent_id)
		THEN
-- 			SELECT id into _parent FROM department WHERE id = parent_id;
			INSERT INTO department_closure(id, ancestor_id, descendant_id, depth) SELECT nextval('department_closure_tbl_seq'), ancestor_id, _descendant, depth+1 from department_closure where descendant_id = _parent_id;
		END IF;
		RETURN _descendant;
	END IF;
	RETURN 0;
END;$$
LANGUAGE plpgsql;