
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
(12, true, 'Assistant Specialist', ' همکار کارشناس', 'همکار کارشناس'),
(13, true, 'Advisor', 'مشاور', 'مشاور'),
(14, true, 'Other', 'سایر', 'سایر');




INSERT INTO public.user_tbl(id, active, entity_id, department_id, job_id, address, email, name, password, username, avatar, reviewer_required) VALUES
(1, true, 1, 3, 10, 'Kabul', 'admin@nsia.gov.af', 'Admin User', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW', 'admin', '2019-02-24_21_22_53_thumbnail.jpg', false),
(3, true, 1, 16, 6, 'Kabul', 'marwais@gmail.ocm', 'Marwais Director', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW', 'director_user', '2019-02-24_21_22_53_thumbnail.jpg', false),
(4, true, 1, 239, 8, 'Kabul', 'sediq@gmail.com', 'Sediq Khan', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW', 'executive_manager', '2019-02-24_21_22_53_thumbnail.jpg', false),
(5, true, 1, 7, 3, 'Kabul', 'dawood@gmail.com', 'Dawood deputy', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW', 'deputy_user', '2019-02-24_21_22_53_thumbnail.jpg', false),
(6, true, 1, 244, 11, 'Kabul', 'sediqa@gmail.com', 'Sediqa Specialist', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW', 'specialist_user', '2019-02-24_21_22_53_thumbnail.jpg', true),
(7, true, 1, 10, 11, 'Kabul', 'ismail@gmail.com', 'Followup Manager User', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW', 'followup_user', '2019-02-24_21_22_53_thumbnail.jpg',true),
(8, true, 1, 257, 13, 'Kabul', 'kabul@gmail.com', 'akbari assistant specialist', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW', 'assistant_specialist', '2019-02-24_21_22_53_thumbnail.jpg',true),
(9, true, 1, 10, 9, 'Kabul', 'karim@gmail.com', 'Karim Evaluation', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW', 'evaluation_user', '2019-02-24_21_22_53_thumbnail.jpg',true),
(10, true, 2039, 2039, 6, 'Kabul', 'kabul@gmail.com', 'Ahmadzai NSIA', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW', 'nsia_rayes_daftar', '2019-02-24_21_22_53_thumbnail.jpg',false);




INSERT INTO public.group_tbl(id, name, description, active) VALUES
(1, 'ADMIN_GROUP', 'ADMIN GROUP', true),
(4, 'DOC_MNG_USER_GROUP', 'access to document management module', true),
(5, 'DOC_MNG_DIRECTOR_GROUP', '	access to directorate data', true),
(6, 'DOC_MNG_SPECIALIST_GROUP', 'specialist group', true),
(7, 'DOC_MNG_FOLLOWUP_GROUP', 'document management followup group', true),
(8, 'DOC_MNG_EVALUATION_GROUP', 'document management evaluation group', true),
(9, 'DOC_MNG_EXTERNAL_RAYES_DAFTAR_GROUP', 'external rayes daftar group', true);


INSERT INTO public.role(id, name, description, active) VALUES
(1, 'ADMIN_ROLE', 'It can manage the application', true),
(4, 'DOC_MNG_USER', 'access to document management module', true),
(5, 'DOC_MNG_SPECIALIST', 'access specialist permission', true),
(6, 'DOC_MNG_EVALUATION', 'access to document management evaluation', true),
(7, 'DOC_MNG_FOLLOWUP', 'access to document followup', true),
(8, 'DOC_MNG_HEAD', 'access to all directorate list', true),
(9, 'DOC_MNG_TYPE_FARMAN', 'can add farman document types', true),
(10, 'DOC_MNG_TYPE_HUKM', 'can add hukm document type', true),
(11, 'DOC_MNG_TYPE_HEDAYAD', 'can add hedayad document type', true),
(12, 'DOC_MNG_TYPE_PESHNIHAD', 'can add peshnihad document type', true),
(13, 'DOC_MNG_TYPE_MAKTUB', 'can add maktub document type', true),
(14, 'DOC_MNG_RAYES_DAFTAR', 'external rayes daftar', true),
(15, 'DOC_MNG_EXECUTION_TYPE_FORWARD', 'can forward document', true);


INSERT INTO public.role_permission(role_id, permission_id)
SELECT 1, id
FROM public.permission;






INSERT INTO public.role_permission(role_id, permission_id) VALUES
(4,	19001),(9, 19042),(10, 19038),(11, 19043),(13, 19041),(15, 19052),(6, 19035),(7	,19016),(5, 19009),(5, 19011),
(5, 19012),(5, 19013),(5, 19014),(5, 19015),(5, 19018),(5, 19019),(5, 19020),(5, 19023),(5, 19029),(5, 19030),
(5, 19031),(5, 19032), (5, 19033),(5, 19034),(5, 19039),(5, 19040),(5, 19041),(5, 19045),(5, 19046),(5, 19047),
(5, 19048),(5, 19049), (5, 19050),(5, 19051),(5, 19052),(5, 19053),(5, 19054),(5, 19055),(5, 19056),(5, 19057),
(5, 19058),(5, 19059), (5, 19060),(5, 19061),(5, 19062),(5, 19063),(12, 19040),(14, 19002),(14, 19040),(14, 19041),
(14, 19054),(14, 19056),(8, 19002),(8, 19063);




INSERT INTO public.user_group(user_id, group_id) VALUES
(1,1),(3, 4),(3, 5),(4, 5),(4, 4),(6, 4),(6, 6),(5, 4),(5, 5),(7, 4),(7, 5),(8, 4),(8, 6),(9, 7),(9, 4), (9, 5),
(10, 9),(10, 4);


INSERT INTO public.group_role(group_id, role_id) VALUES
(1, 1),(4, 4),(6, 5), (6, 12),(6, 13),(8, 7),
(9, 4),(9, 14),(5, 4), (5, 8),(5, 12),(5, 13),
(5, 15),(7, 6),(7, 15);




INSERT INTO public.doc_mng_document_gallery(id, created_by, attachment_name, name, thumbnail_name) VALUES
(1, 1, '2021-07-27_07_57_26_afg_logo.jpeg', 'Afg Logo', 'thumbnail_2021-07-27_07_57_26_afg_logo.jpeg'),
(2, 1, '2021-07-27_08_07_51_nsia_logo.jpeg', 'NSIA Logo', 'thumbnail_2021-07-27_08_07_51_nsia_logo.jpeg'),
(3, 1, '2021-07-27_08_08_14_nsia_logo_2.jpeg', 'NSIA Logo 2', 'thumbnail_2021-07-27_08_08_14_nsia_logo_2.jpeg'),
(4, 1, '2021-07-27_08_02_12_moic_logo.png', 'MOIC Logo', 'thumbnail_2021-07-27_08_02_12_moic_logo.png'),
(5, 1, '2021-07-27_07_53_01_mopw_logo.png', 'MOPW Logo', 'thumbnail_2021-07-27_07_53_01_mopw_logo.png'),
(6, 1, '2021-07-27_08_21_54_morr_logo.png', 'MORR Logo', 'thumbnail_2021-07-27_08_21_54_morr_logo.png'),
(7, 1, '2021-07-27_12_55_36_mof_logo.jpg', 'MOF Logo', 'thumbnail_2021-07-27_12_55_36_mof_logo.jpg'),
(8, 1, '2021-07-27_12_57_45_moi_logo.png', 'MOI Logo', 'thumbnail_2021-07-27_12_57_45_moi_logo.png'),
(9, 1, '2021-07-27_12_59_54_mod_logo.png', 'MOD Logo', 'thumbnail_2021-07-27_12_59_54_mod_logo.png'),
(10, 1, '2021-07-27_13_01_29_moe_logo.png', 'MOE Logo', 'thumbnail_2021-07-27_13_01_29_moe_logo.png'),
(11, 1, '2021-07-27_13_06_29_aop_logo.png', 'AOP Logo', 'thumbnail_2021-07-27_13_06_29_aop_logo.png');

















































