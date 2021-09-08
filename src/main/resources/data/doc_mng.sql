INSERT INTO public.doc_mng_document_type(id, type, order_col, slug, name_dr,name_en,created_by,name_ps) VALUES 
(1, 1, 1, 'HUKUM', 'حکم','HUKUM', 1, 'حکم'),
(2, 3, 1, 'ESTILAM', 'استعلام','ESTILAM',1, 'استعلام'),
(3, 3, 1, 'PESHNIHAD', 'پیشنهاد','PESHNIHAD', 1,'پیشنهاد'),
(4, 3, 1, 'MAKTOB', 'مکتوب','MAKTOB', 1, 'مکتوب'),
(5, 1, 1, 'FARMAN', 'فرمان','FARMAN',1, 'فرمان'),
(6, 1, 1, 'HIDAYAT', 'هدایت', 'HIDAYAT', 1, 'هدایت'),
(7, 3, 1, 'NOTE', 'یاداشت','NOTE', 1,'یاداشت'),
(8, 2, 1, 'ARCHIVE', 'حفظ','ARCHIVE', 1, 'حفظ'),
(9, 2, 1, 'FORWARD','راجع','FORWARD',1, 'راجع'),
(10, 2, 1, 'REJECT', 'رد','REJECT', 1, 'رد'),
(11, 2, 1, 'DIR_ORDER', 'احکام ریس','DIRECTOR ORDER', 1,'احکام ریس'),
(12, 2, 1, 'NOTE_ORDER', 'حکم یاداشت','NOTE ORDER', 1,'حکم یاداشت'),
(13, 2, 1, 'HUKUM_PESHNIHAD', 'حکم پیشنهاد','HUKUM PESHNIHAD', 1,'حکم پیشنهاد'),
(14, 2, 1, 'ESTILAM_RESPONSE', 'جوابیه استعلام','ESTILAM RESPONSE', 1,'جوابیه استعلام');

-- (5, 'عریضه','ARIZA',1,'عریضه'),
-- (7, 'مصوبه کابینه','MUSAWIBA_KABINA',1,'مصوبه کابینه'),
-- (8, 'مصوبه شورای وزیران ','MUSAWIBA_SHORA_WAZIRAN',1,'مصوبه شورای وزیران'),

-- INSERT INTO public.doc_mng_document_execution_type(id, name_dr,name_en,created_by,name_ps) VALUES 
-- (1,'حفظ','ARCHIVE', 1, 'حفظ'),
-- (2,'راجع','FORWARD',1, 'راجع'),
-- (3, 'رد','REJECT', 1, 'رد'),
-- (4, 'مکتوب','MAKTOB', 1, 'مکتوب'),
-- (5,'استعلام','ESTILAM',1, 'استعلام'),
-- (6, 'پیشنهاد','PESHNIHAD', 1,'پیشنهاد'),
-- (7, 'جوابیه استعلام','ESTILAM RESPONSE', 1,'جوابیه استعلام'),
-- (8, 'یاداشت','NOTE', 1,'یاداشت'),
-- (9, 'احکام ریس','DIRECTOR ORDER', 1,'احکام ریس'),
-- (10, 'حکم یاداشت','NOTE ORDER', 1,'حکم یاداشت'),
-- (11, 'حکم پیشنهاد','HUKUM PESHNIHAD', 1,'حکم پیشنهاد');


INSERT INTO public.doc_mng_document_followup_type(id, active, created_by, deleted, name_dr,name_en, name_ps) VALUES 
(1, 't', 1, 'f', 'اختصاص دادن','Assign To','اختصاص دادن'),
(2, 't', 1, 'f', 'تماس تلیفونی','Phone','تماس تلیفونی'),
(3, 't', 1, 'f', 'سیستم','System','سیستم'),
(4, 't', 1, 'f', 'امیل ادرس','Email','امیل ادرس'),
(5, 't', 1, 'f', 'مکاتب رسمی','Maktub','مکاتب رسمی'),
(6, 't', 1, 'f', 'جلسات مستقیم','Meeting','جلسات مستقیم'),
(7, 't', 1, 'f', 'ارزیابی','ٍEvaluation','ارزونه');

INSERT INTO public.doc_mng_document_followup_status(id, active, created_by, deleted, name_dr, name_en, name_ps) VALUES 
(1, 't', 1, 'f', 'نااجرا','No Execution','نااجرا'),
(2, 't', 1, 'f', 'در حال اجرا','Under Execution','در حال اجرا'),
(3, 't', 1, 'f', 'اجرا','Executed','اجرا');

INSERT INTO public.doc_mng_document_status(id, active, created_by, deleted, name_dr, name_en, name_ps) VALUES 
(1, 't', 1, 'f', 'نااجرا','No Execution','نااجرا'),
(2, 't', 1, 'f', 'در حال اجرا','Under Execution','در حال اجرا'),
(3, 't', 1, 'f', 'اجرا','Executed','اجرا');

INSERT INTO public.doc_mng_document_note_type(id, created_by, deleted, name_dr, name_en, name_ps) VALUES 
(1, 1, 'f', 'به مقام عالی','To President','به مقام عالی'),
(2, 1, 'f', 'به مقام اداره','ُTo Authority Head','به مقام اداره'),
(3, 1, 'f', 'به معاونیت','To deputy','به معاونیت');
INSERT INTO public.doc_mng_issuing_authority(id, created_by, deleted, name_dr, name_en, name_ps) VALUES 
(1, 'admin', 'f', 'رئیس صاحب جمهور','President','ولسمشر'),
(2, 'admin', 'f', 'معاون اول رئیس جمهور', 'First Vice President','د ولسمشر لومړی مرستیال'),
(3, 'admin', 'f', 'معاون دوم رئیس جمهور', 'Second Vice President','د ولسمشر دوهم مرستیال'),
(4, 'admin', 'f', 'رئیس عمومی اداره امور', 'General Director of Administration','د ادارې عمومي ریاست'),
(5, 'admin', 'f', 'رئیس عمومی اداره عملیاتی و حمایوی', 'General Director of Operations and Support','د عملیاتو او ملاتړ عمومي رییس'),
(6, 'admin', 'f', 'رئیس عمومی شرکت انکشاف ملی', 'General Chairman of the National Development Company','د ملي پراختیا شرکت عمومي رییس'),
(7, 'admin', 'f', 'معاونیت خدماتی و حمایوی انکشاف ملی', 'Deputy Minister of National Development Services and Support','د ملي پراختیا خدمتونو او ملاتړ معاونیت'),
(8, 'admin', 'f', 'معاونیت عملیاتی و تخنیکی - اداره عملیاتی و حمایوی', 'Operational and Technical Deputy - Operations and Support Department','عملیاتي او تخنیکي مرستیال - د عملیاتو او ملاتړ څانګه');


INSERT INTO public.doc_mng_document_category(id, created_by, deleted, name_dr, name_en, name_ps) VALUES 
(1, 'admin', 'f', 'مالی و بودجه','Finance and budget','مالي او بودیجه'),
(2, 'admin', 'f', 'توشیح/تعدیل', 'Signing / Modifying','لاسلیک کول / تغیر ورکول'),
(3, 'admin', 'f', 'موضوعات مرتبط به محصول گمرکی', 'Customs product issues','د ګمرک محصول مسلې'),
(4, 'admin', 'f', 'تخفیف و عف مجازات', 'Mitigation and pardon of punishment','د مجازاتو کمول او معافیت'),
(5, 'admin', 'f', 'ایجاد کمیسیون/ کمیته ها/توضیف هیئت', 'Creating commissions / committees / appointing a board','د کمیسیونونو / کمیټو رامینځته کول / د بورډ ټاکل'),
(6, 'admin', 'f', 'منظوری تشکیلات و تعیینات', 'Purpose of organizations and appointments','د سازمانونو او ګمارنو هدف'),
(7, 'admin', 'f', 'سفر/سفریه/اعاشه', 'Travel / Travel / Livelihood','سفر / سفر / معیشت'),
(8, 'admin', 'f', 'منظوری معاش و امتیازات مالی', 'Purpose of livelihood and financial benefits','د معیشت او مالي ګټې هدف');


