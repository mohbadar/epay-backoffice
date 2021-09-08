INSERT INTO public.department (id, parent_id, created_at, deleted, deleted_at, deleted_by, name_dr, name_en, name_ps, updated_at) VALUES 
(1, NULL, NULL, false, NULL, NULL, 'اداره ملی احصائیه و معلومات', 'National Statistics and Information Authority', 'اداره ملی احصائیه و معلومات', NULL),
(2, 1, NULL, false, NULL, NULL, 'معاونیت مسلکی ', 'معاونیت مسلکی ', 'معاونیت مسلکی ', NULL),
(3, 1, NULL, false, NULL, NULL, 'معاونیت سیستم های معلوماتی', 'معاونیت سیستم های معلوماتی', 'معاونیت سیستم های معلوماتی', NULL),
(4, 1, NULL, false, NULL, NULL, 'ریاست انسجام ارقام بزرگ', 'ریاست انسجام ارقام بزرگ', 'ریاست انسجام ارقام بزرگ', NULL),
(5, 1, NULL, false, NULL, NULL, 'ریاست انکشاف سیستم های معلوماتی ', 'ریاست انکشاف سیستم های معلوماتی ', 'ریاست انکشاف سیستم های معلوماتی ', NULL),
(6, NULL, NULL, false, NULL, NULL, 'وزارت عدلیه', 'Ministry of Justice','د عدلیې وزارت', NULL),
(7, NULL, NULL, false, NULL, NULL, 'وزارت مالیه', 'Ministry of Finance','د مالیی وزارت ', NULL),
(8, NULL, NULL, false, NULL, NULL, 'وزارت دفاع ملی', 'Ministry of Defense ','د ملي دفاع وزارت', NULL),
(9, NULL, NULL, false, NULL, NULL, 'وزارت معارف', 'Ministry of Education','د پوهنې وزارت', NULL);

INSERT INTO public.job (id, active, name_en, name_dr, name_ps) VALUES 
(1, true, 'President', 'ریس جمهور', 'ریس جمهور'),
(2, true, 'Minister', 'وزیر', 'وزیر'),
(3, true, 'Deputy Minister', 'معین', 'معین '),
(4, true, 'General Director', 'رئیس عمومی', 'رئیس عمومی'),
(5, true, 'Deputy General Director', 'معاون', 'معاون '),
(6, true, 'Director', 'رئیس', 'رئیس'),
(7, true, 'Deputy Director', 'معاون رئیس', 'معاون رئیس'),
(8, true, 'Executive Manager', 'مدیر اجرایه', 'مدیر اجرایه'),
(9, true, 'Manager', 'مدیر', 'مدیر'),
(10, true, 'Officer', 'مامور', 'مامور'),
(11, true, 'Specialist', 'کارشناس', 'کارشناس'),
(12, true, 'Advisor', 'مشاور', 'مشاور'),
(13, true, 'Other', 'سایر', 'سایر');

-- Necessary Data
INSERT INTO public.user_tbl(id, active, entity_id, department_id, job_id, address, email, name, password, username, avatar) VALUES
 (1, true, 1, 3, 1, 'Kabul', 'admin@nsia.gov.af', 'Admin User', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW', 'admin', '2019-02-24_21_22_53_thumbnail.jpg'),
 (2, true, 1, 2, 2, 'Kabul', 'test@nsia.gov.af', 'Test User', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW', 'test', '2019-02-24_21_22_53_thumbnail.jpg');

INSERT INTO public.group_tbl(id, name, description, active) VALUES
 (1, 'ADMIN_GROUP', 'ADMIN GROUP', true),
 (2, 'RECEPTION_DATAENTRY_GROUP', 'Reception DataEntry GROUP', true),
 (3, 'RECEPTION_SUPERVISOR_GROUP', 'Reception Supervisor GROUP', true);


INSERT INTO public.role(id, name, description, active) VALUES
 (1, 'ADMIN_ROLE', 'It can manage the application', true),
 (2, 'RECEPTION_DATAENTRY_ROLE', 'It can enter data in Reception Module like visit', true),
 (3, 'RECEPTION_SUPERVISOR_ROLE', 'It can view data in Reception Module like visit', true);

INSERT INTO public.permission(id, name, description, active) VALUES
(1, 'SYS_ADMIN', 'Can have access to everything', true),
(2, 'ADMIN', 'Can access to admin pages of an environment', true),

(3, 'PERMISSION_CREATE', 'Can only create the new record', true),
(4, 'PERMISSION_VIEW', 'Can only view/read the record', true),
(5, 'PERMISSION_EDIT', 'Can only edit/update the record', true),
(6, 'PERMISSION_DELETE', 'Can only delete the record', true),
(7, 'PERMISSION_LIST', 'Can only see the list/table of records', true),

(8, 'USER_CREATE', 'Can only create the new record', true),
(9, 'USER_VIEW', 'Can only view/read the record', true),
(10, 'USER_EDIT', 'Can only edit/update the record', true),
(11, 'USER_DELETE', 'Can only delete the record', true),
(12, 'USER_LIST', 'Can only see the list/table of records', true),

(13, 'GROUP_CREATE', 'Can only create the new record', true),
(14, 'GROUP_VIEW', 'Can only view/read the record', true),
(15, 'GROUP_EDIT', 'Can only edit/update the record', true),
(16, 'GROUP_DELETE', 'Can only delete the record', true),
(17, 'GROUP_LIST', 'Can only see the list/table of records', true),
 
(18, 'ROLE_CREATE', 'Can only create the new record', true),
(19, 'ROLE_VIEW', 'Can only view/read the record', true),
(20, 'ROLE_EDIT', 'Can only edit/update the record', true),
(21, 'ROLE_DELETE', 'Can only delete the record', true),
(22, 'ROLE_LIST', 'Can only see the list/table of records', true),

(23, 'SYS_REG_CREATE', 'Can only create the new record', true),
(24, 'SYS_REG_VIEW', 'Can only view/read the record', true),
(25, 'SYS_REG_EDIT', 'Can only edit/update the record', true),
(26, 'SYS_REG_DELETE', 'Can only delete the record', true),
(27, 'SYS_REG_LIST', 'Can only see the list/table of records', true);

-- TOP LEVEL PERMISSIONS - Modules
INSERT into public.permission (id, name, description, active) values
(11001,'ADMIN_MODULE','', true),
(12001,'CONFIG_MODULE','', true),
(13001,'RECEPTION_MODULE','', true),
(14001,'HELPDESK_MODULE','', true),
(15001,'TRANSPORTATION_MODULE','', true),
(16001,'LIBRARY_MODULE','', true),
(17001,'HR_MODULE','', true),
(18001,'TASK_MANAGEMENT_MODULE','', true),
(19001,'DOC_MANAGEMENT_MODULE','', true),
(20001,'FINANCE_MODULE','', true),
(21001,'GCA_MODULE','', true),
(22001,'GENEVA_MODULE','', true),
(23001,'SHORA_KABINA_MODULE','', true),
(24001,'ASSET_REG_MODULE','', true);

-- LIST OF FILE RECEPTION_MODULE
INSERT into public.permission (id, name, description, active) values 
(13002,'RECEP_VISIT_LIST','', true),
(13003,'RECEP_VISIT_LIST_ALL','', true),
(13004,'RECEP_VISIT_CREATE','', true),
(13005,'RECEP_VISIT_VIEW','', true),
(13006,'RECEP_VISIT_EDIT','', true),
(13007,'RECEP_VISIT_DELETE','', true),
(13008,'RECEP_VISIT_PRINT','', true),

(13009,'RECEP_VISITOR_LIST','', true),
(13010,'RECEP_VISITOR_LIST_ALL','', true),
(13011,'RECEP_VISITOR_CREATE','', true),
(13012,'RECEP_VISITOR_VIEW','', true),
(13013,'RECEP_VISITOR_EDIT','', true),
(13014,'RECEP_VISITOR_DELETE','', true),
(13015,'RECEP_VISITOR_PRINT','', true),

(13016,'RECEP_VEHICLE_LIST','', true),
(13017,'RECEP_VEHICLE_LIST_ALL','', true),
(13018,'RECEP_VEHICLE_CREATE','', true),
(13019,'RECEP_VEHICLE_VIEW','', true),
(13020,'RECEP_VEHICLE_EDIT','', true),
(13021,'RECEP_VEHICLE_DELETE','', true),
(13022,'RECEP_VEHICLE_PRINT','', true),

(13023,'RECEP_VISIT_SCHEDULE_LIST','', true),
(13024,'RECEP_VISIT_SCHEDULE_LIST_ALL','', true),
(13025,'RECEP_VISIT_SCHEDULE_VIEW','', true),
(13026,'RECEP_VISIT_SCHEDULE_CREATE','', true),
(13027,'RECEP_VISIT_SCHEDULE_EDIT','', true),
(13028,'RECEP_VISIT_SCHEDULE_DELETE','', true),
(13029,'RECEP_VISIT_SCHEDULE_PRINT','', true),
(13030,'RECEP_VISIT_SCHEDULE_PROCESS','', true),

(13031,'RECEP_VISITOR_SCHEDULE_LIST','', true),
(13032,'RECEP_VISITOR_SCHEDULE_LIST_ALL','', true),
(13033,'RECEP_VISITOR_SCHEDULE_VIEW','', true),
(13036,'RECEP_VISITOR_SCHEDULE_PROCESS','', true),

(13037,'RECEP_VEHICLE_SCHEDULE_LIST','', true),
(13038,'RECEP_VEHICLE_SCHEDULE_LIST_ALL','', true),
(13039,'RECEP_VEHICLE_SCHEDULE_VIEW','', true),
(13040,'RECEP_VEHICLE_SCHEDULE_PROCESS','', true),

(13041,'RECEP_DASHBOARD','', true),
(13042,'RECEP_WIKI','', true),
(13043,'RECEP_SCHEDULING','', true);

-- LIST OF Permissions for TRANSPORTATION_MODULE
INSERT into public.permission (id, name, description, active) values 
(15002,'TRANS_REQUEST_LIST','', true),
(15003,'TRANS_REQUEST_LIST_ALL','', true),
(15004,'TRANS_REQUEST_CREATE','', true),
(15005,'TRANS_REQUEST_VIEW','', true),
(15006,'TRANS_REQUEST_EDIT','', true),
(15007,'TRANS_REQUEST_DELETE','', true),
(15008,'TRANS_REQUEST_PRINT','', true),
(15009,'TRANS_REQUEST_CANCEL','', true),
(15010,'TRANS_REQUEST_PROCESS','', true),
(15011,'TRANS_REQUEST_LIST_PENDING','', true),
(15012,'TRANS_REQUEST_LIST_CLOSED','', true),

(15013,'TRANS_DRIVER_LIST','', true),
(15014,'TRANS_DRIVER_LIST_ALL','', true),
(15015,'TRANS_DRIVER_CREATE','', true),
(15016,'TRANS_DRIVER_VIEW','', true),
(15017,'TRANS_DRIVER_EDIT','', true),
(15018,'TRANS_DRIVER_DELETE','', true),
(15019,'TRANS_DRIVER_PRINT','', true),

(15020,'TRANS_VEHICLE_LIST','', true),
(15021,'TRANS_VEHICLE_LIST_ALL','', true),
(15022,'TRANS_VEHICLE_CREATE','', true),
(15023,'TRANS_VEHICLE_VIEW','', true),
(15024,'TRANS_VEHICLE_EDIT','', true),
(15025,'TRANS_VEHICLE_DELETE','', true),
(15026,'TRANS_VEHICLE_PRINT','', true),

(15027,'TRANS_DASHBOARD','', true),
(15028,'TRANS_WIKI','', true);

-- LIST OF Permissions for TASK_MANAGEMENT_MODULE
INSERT into public.permission (id, name, description, active) values 
(18002,'TASKMNG_TASKBOARD_LIST','', true),
(18003,'TASKMNG_TASKBOARD_LIST_ALL','', true),
(18004,'TASKMNG_TASKBOARD_CREATE','', true),
(18005,'TASKMNG_TASKBOARD_VIEW','', true),
(18006,'TASKMNG_TASKBOARD_EDIT','', true),
(18007,'TASKMNG_TASKBOARD_DELETE','', true),
(18008,'TASKMNG_TASKBOARD_PRINT','', true),
(18009,'TASKMNG_TASKBOARD_ADD_MEMBER','', true),
(18010,'TASKMNG_TASKBOARD_REMOVE_MEMBER','', true),
(18011,'TASKMNG_TASKBOARD_PUBLIC','Owner of taskboard can make the board public', true),

(18012,'TASKMNG_TASK_LIST','', true),
(18013,'TASKMNG_TASK_LIST_ALL','', true),
(18014,'TASKMNG_TASK_CREATE','', true),
(18015,'TASKMNG_TASK_VIEW','', true),
(18016,'TASKMNG_TASK_EDIT','', true),
(18017,'TASKMNG_TASK_DELETE','', true),
(18018,'TASKMNG_TASK_PRINT','', true),

(18019,'TASKMNG_TASK_ADD_MEMBER','', true),
(18020,'TASKMNG_TASK_REMOVE_MEMBER','', true),
(18021,'TASKMNG_TASK_ADD_ATTACHMENT','', true),
(18022,'TASKMNG_TASK_REMOVE_ATTACHMENT','', true),
(18023,'TASKMNG_TASK_ADD_COMMENT','', true),
(18024,'TASKMNG_TASK_REMOVE_COMMENT','', true),
(18025,'TASKMNG_TASK_ADD_EXECUTION','', true),
(18026,'TASKMNG_TASK_REMOVE_EXECUTION','', true),
(18027,'TASKMNG_TASK_ADD_SUBTASK','', true),
(18028,'TASKMNG_TASK_REMOVE_SUBTASK','', true);

-- LIST OF Permissions for TASK_MANAGEMENT_MODULE
INSERT into public.permission (id, name, description, active) values 
(19002,'DOCMNG_DOCUMENT_LIST','', true),
(19003,'DOCMNG_DOCUMENT_LIST_ALL','', true),
(19004,'DOCMNG_DOCUMENT_CREATE','', true),
(19005,'DOCMNG_DOCUMENT_VIEW','', true),
(19006,'DOCMNG_DOCUMENT_EDIT','', true),
(19007,'DOCMNG_DOCUMENT_DELETE','', true),
(19008,'DOCMNG_DOCUMENT_PRINT','', true),

(19009,'DOCMNG_DOC_EXECUTION_LIST','', true),
(19010,'DOCMNG_DOC_EXECUTION_LIST_ALL','', true),
(19011,'DOCMNG_DOC_EXECUTION_CREATE','', true),
(19012,'DOCMNG_DOC_EXECUTION_VIEW','', true),
(19013,'DOCMNG_DOC_EXECUTION_EDIT','', true),
(19014,'DOCMNG_DOC_EXECUTION_DELETE','', true),
(19015,'DOCMNG_DOC_EXECUTION_PRINT','', true),

(19016,'DOCMNG_DOC_FOLLOWUP_LIST','', true),
(19017,'DOCMNG_DOC_FOLLOWUP_LIST_ALL','', true),
(19018,'DOCMNG_DOC_FOLLOWUP_CREATE','', true),
(19019,'DOCMNG_DOC_FOLLOWUP_VIEW','', true),
(19020,'DOCMNG_DOC_FOLLOWUP_EDIT','', true),
(19021,'DOCMNG_DOC_FOLLOWUP_DELETE','', true),
(19022,'DOCMNG_DOC_FOLLOWUP_PRINT','', true),

(19023,'DOCMNG_FOLLOWUP_DOCUMENT_LIST','', true),
(19024,'DOCMNG_FOLLOWUP_DOCUMENT_LIST_ALL','', true),
(19025,'DOCMNG_FOLLOWUP_DOCUMENT_VIEW','', true),
(19026,'DOCMNG_FOLLOWUP_DOCUMENT_EDIT','', true),
(19027,'DOCMNG_FOLLOWUP_DOCUMENT_DELETE','', true),
(19028,'DOCMNG_FOLLOWUP_DOCUMENT_PRINT','', true),

(19029,'DOCMNG_DOC_COMMENT_LIST','', true),
(19030,'DOCMNG_DOC_COMMENT_LIST_ALL','', true),
(19031,'DOCMNG_DOC_COMMENT_CREATE','', true),
(19032,'DOCMNG_DOC_COMMENT_VIEW','', true),
(19033,'DOCMNG_DOC_COMMENT_EDIT','', true),
(19034,'DOCMNG_DOC_COMMENT_DELETE','', true),
(19035,'DOCMNG_DOC_EVALUATION','', true),
(19036,'DOCMNG_DASHBOARD','', true),
(19037,'DOCMNG_WIKI','', true),
(19038,'DOCMNG_DOC_TYPE_HUKUM','Farman option is shown in document type', true),
(19039,'DOCMNG_DOC_TYPE_ESTILAM','Estilam options is shown in document type', true),
(19040,'DOCMNG_DOC_TYPE_PESHNIHAD','Peshnihad options is shown in document type', true),
(19041,'DOCMNG_DOC_TYPE_MAKTOB','Maktob options is shown in document type', true),
(19042,'DOCMNG_DOC_TYPE_FARMAN','Farman options is shown in document type', true),
(19043,'DOCMNG_DOC_TYPE_HIDAYAT','Hidayat options is shown in document type', true),
(19044,'DOCMNG_DOC_TYPE_NOTE','Note options is shown in document type', true),
(19045,'DOCMNG_FOLLOWUP_TYPE_ASSIGN','Assign To is shown in follow up type', true),
(19046,'DOCMNG_FOLLOWUP_TYPE_PHONE','Phone To is shown in follow up type', true),
(19047,'DOCMNG_FOLLOWUP_TYPE_SYSTEM','System To is shown in follow up type', true),
(19048,'DOCMNG_FOLLOWUP_TYPE_EMAIL','Email To is shown in follow up type', true),
(19049,'DOCMNG_FOLLOWUP_TYPE_MAKTUB','Maktub To is shown in follow up type', true),
(19050,'DOCMNG_FOLLOWUP_TYPE_MEETING','Meeting To is shown in follow up type', true),
(19051,'DOCMNG_DOC_EXECUTION_TYPE_ARCHIVE','Archive is shown in document execution type', true),
(19052,'DOCMNG_DOC_EXECUTION_TYPE_FORWARD','Forward is shown in document execution type', true),
(19053,'DOCMNG_DOC_EXECUTION_TYPE_REJECT','Reject is shown in document execution type', true),
(19054,'DOCMNG_DOC_EXECUTION_TYPE_MAKTOB','Maktob is shown in document execution type', true),
(19055,'DOCMNG_DOC_EXECUTION_TYPE_ESTILAM','Estilam is shown in document execution type', true),
(19056,'DOCMNG_DOC_EXECUTION_TYPE_PESHNIHAD','Peshnihad is shown in document execution type', true),
(19057,'DOCMNG_DOC_EXECUTION_TYPE_ESTILAM_RESPONSE','Estilam response is shown in document execution type', true),
(19058,'DOCMNG_DOC_EXECUTION_TYPE_NOTE','Note is shown in document execution type', true),
(19059,'DOCMNG_DOC_EXECUTION_TYPE_DIRECTOR_ORDER','Director order is shown in document execution type', true),
(19060,'DOCMNG_DOC_EXECUTION_TYPE_NOTE_ORDER','Note order is shown in document execution type', true),
(19061,'DOCMNG_DOC_EXECUTION_TYPE_HUKUM_PESHNIHAD','Hukum Peshnihad is shown in document execution type', true),
(19062,'DOCMNG_FOLLOWUP_TYPE_EVALUATION','Evaluation is shown in follow up type', true),
(19063,'DOCMNG_FOLLOWUP_ASSIGN','Assign a user to the follow up of a document', true);


-- putting VALUES causes error bcuz more fields have been added to the permission
INSERT INTO public.role_permission(role_id, permission_id)
SELECT 1, id
FROM public.permission;

INSERT INTO public.role_permission(role_id, permission_id) VALUES
(2, 13001), (2 ,13002), (2 ,13003), (2 ,13004), (2 ,13008), (2 ,13009), (2 ,13010), (2 ,13014), (2 ,13020), (2 ,13021), (2 ,13026);

INSERT INTO public.role_permission(role_id, permission_id) VALUES
(3, 13001), (3 ,13002), (3 ,13003), (3 ,13004), (3 ,13008), (3 ,13009), (2 ,13010), (2 ,13014), (2 ,13020), (2 ,13021), (2 ,13026);


INSERT INTO public.user_group(user_id, group_id) VALUES
(1, 1),
(2, 2);

INSERT INTO public.group_role(group_id, role_id) VALUES
(1, 1),
(2, 2),
(3, 3);