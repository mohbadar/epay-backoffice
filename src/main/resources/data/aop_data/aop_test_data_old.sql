-- INSERT INTO public.department(id, parent_id, deleted, name_dr, name_en, name_ps) VALUES 
--      (1, null, false,'ریاست عمومی اداره امور ریاست جمهوری', 'Administrative Office of the President','د چارو ادارې لوی ریاست'),
-- (2, 1, false, 'ریاست تفتیش داخلی','Directorate of Internal Audit', 'د داخلي پلټنې ریاست'),
--     (22, 2, false, 'معاون','Directorate of Internal Audit', 'د داخلي پلټنې ریاست'),
--         (23, 22, false, 'سر مفتش ارشد','Directorate of Internal Audit', 'د داخلي پلټنې ریاست'),
--             (26, 23, false, 'سر مفتش تکنالوژی معلوماتی','Directorate of Internal Audit', 'د داخلي پلټنې ریاست'),
--             (27, 23, false, 'سر مفتش بخش ساختمانی','Directorate of Internal Audit', 'د داخلي پلټنې ریاست'),
--             (28, 23, false, 'سر مفتش','Directorate of Internal Audit', 'د داخلي پلټنې ریاست'),
--                 (31, 28, false, 'مفتش','Directorate of Internal Audit', 'د داخلي پلټنې ریاست'),
--         (24, 22, false, 'آمریت تحلیل گزارش ها','Directorate of Internal Audit', 'د داخلي پلټنې ریاست'),
--             (29, 24, false, 'کارشناس تحلیل گزارش ها','Directorate of Internal Audit', 'د داخلي پلټنې ریاست'),
--                 (32, 29, false, 'همکار کارشناس','Directorate of Internal Audit', 'د داخلي پلټنې ریاست'),
--         (25, 22, false, 'مدیریت اجرائیه','Directorate of Internal Audit', 'د داخلي پلټنې ریاست'),
--             (30, 25, false, 'کارمند اجرائیه','Directorate of Internal Audit', 'د داخلي پلټنې ریاست'),
-- (3, 1, false,'مشاورین رئیس عمومی اداره امور ریاست جمهوری ا.ا', 'Administrative Office of the President','د چارو ادارې لوی ریاست'),
-- (4, 1, false,'مشاورین رئیس عمومی اداره امور ریاست جمهوری ا.ا', 'Administrative Office of the President','د چارو ادارې لوی ریاست'),
-- (5, 1, false,'ریاست دفتر ریاست عمومی اداره امور ریاست جمهوری', 'Office of the Chief of Staff of Administrative Office of the President', 'د جمهوري ریاست د چارو ادارې د دفتر ریاست'),
--     (9, 5, false,'ریاست مطبوعات', 'Directorate of Media', 'د مطبوعاتو ریاست'),
--     (10, 5, false, 'ریاست اسناد و ارتباط', 'Directorate of Documents and Communication', 'د اسنادو او اړیکو ریاست'),
--     (33, 5, false, 'معاون ریاست دفتر', 'Directorate of Documents and Communication', 'د اسنادو او اړیکو ریاست'),
--         (34, 33, false, 'سکرتر رئیس عمومی اداره امور ریاست جمهوری ا.ا', 'Directorate of Documents and Communication', 'د اسنادو او اړیکو ریاست'),
--             (39, 34, false, 'سکرتر اجرائیوی رئیس عمومی اداره امور ریاست جمهوری', 'Directorate of Documents and Communication', 'د اسنادو او اړیکو ریاست'),
--                 (46, 34, false, 'سکرتر اجرائیوی رئیس عمومی اداره امور ریاست جمهوری', 'Directorate of Documents and Communication', 'د اسنادو او اړیکو ریاست'),
--         (35, 33, false, 'آمریت احکام و فرامین', 'Directorate of Documents and Communication', 'د اسنادو او اړیکو ریاست'),
--             (40, 35, false, 'مدیریت تکثیر احکام و فرامین', 'Directorate of Documents and Communication', 'د اسنادو او اړیکو ریاست'),
--             (41, 35, false, 'مدیر عمومی تحلیل و تثبیت احکام و فرامین', 'Directorate of Documents and Communication', 'د اسنادو او اړیکو ریاست'),
--         (36, 33, false, 'آمریت پلان و گزارش دهی', 'Directorate of Documents and Communication', 'د اسنادو او اړیکو ریاست'),
--             (42, 36, false, 'کارشناس پلان و گزارش  دهی', 'Directorate of Documents and Communication', 'د اسنادو او اړیکو ریاست'),
--         (37, 33, false, 'آمریت تعقیب اوامر و هدایات', 'Directorate of Documents and Communication', 'د اسنادو او اړیکو ریاست'),
--             (43, 37, false, 'مدیر عمومی تعقیب اوامر و هدایات', 'Directorate of Documents and Communication', 'د اسنادو او اړیکو ریاست'),
--         (38, 33, false, 'آمریت تحریرات', 'Directorate of Documents and Communication', 'د اسنادو او اړیکو ریاست'),
--             (44, 38, false, 'مدیر عمومی ثبت و تنظیم اسناد خاص', 'Directorate of Documents and Communication', 'د اسنادو او اړیکو ریاست'),
--             (45, 38, false, 'مدیر عمومی تحریرات', 'Directorate of Documents and Communication', 'د اسنادو او اړیکو ریاست'),
--     (34, 5, false, 'متخصص', 'Directorate of Documents and Communication', 'د اسنادو او اړیکو ریاست'),
--     (35, 5, false, 'کارشناس', 'Directorate of Documents and Communication', 'د اسنادو او اړیکو ریاست'),
-- (6, 1, false,'معاونیت منابع و اداره', 'Administrative Office of the President','د چارو ادارې لوی ریاست'),
--     (11, 6, false, 'ریاست امور کادری، اصلاحات و بازنگری تشکیلات دولت','Directorate of Personnel Affairs, Reforms and Review of Government Structures','د کادري چارو، اصلاحاتو او دولتي تشکیلاتو د بیا کتنې ریاست'),
--     (12, 6, false, 'ریاست انسجام امور مشاورین و کمیسیون های حقیقت یاب','Coordination Directorate of Advisors and Fact-Finding Commissions Affairs','د سلاکارانو او حقیقت موندونکو کمېسیونونو چارو د انسجام ریاست'),
--     (12, 6, false, 'ریاست انسجام امور مشاورین و کمیسیون های حقیقت یاب','Coordination Directorate of Advisors and Fact-Finding Commissions Affairs','د سلاکارانو او حقیقت موندونکو کمېسیونونو چارو د انسجام ریاست'),
--     (12, 6, false, 'ریاست انسجام امور مشاورین و کمیسیون های حقیقت یاب','Coordination Directorate of Advisors and Fact-Finding Commissions Affairs','د سلاکارانو او حقیقت موندونکو کمېسیونونو چارو د انسجام ریاست'),
-- (7, 1, false, 'معاونیت انسجام امور دولت', 'General Deputy Directorate of Coordination of State Affairs', 'د دولت چارو د انسجام مرستیالي'),
--     (13, 7, false, 'ریاست انسجام روابط خارجه', 'Directorate of Coordination of Foreign Relations', 'د بهرنیو اړیکو د انسجام ریاست'),
--     (14, 7, false,'ریاست اقتصاد ملی و زیربناها', 'Directorate of National Economy and Infrastructures', 'د ملي اقتصاد او زیربناوو ریاست'),
--     (15, 7, false,'ریاست انسجام امور کابینه', 'Directorate of Cabinate Coordination Affairs', 'د کابینې چارو د انسجام ریاست'),
--     (16, 7, false,'ریاست انسجام امور شوراهای عالی ریاست جمهوری', 'Directorate of High Councils Coordination Affairs of AOP Republic Presidency', 'د جمهوري ریاست د عالي شوراګانو چارو د انسجام ریاست'),
--     (17, 7, false,'ریاست امور فرهنگی و اجتماعی', 'Directorate of Cultural and Social Affairs', 'د فرهنګي او ټولنیزو چارو ریاست'),
--     (18, 7, false,'ریاست دفاع و امنیت', 'Directorate of Defence and Security', 'د دفاع او امنیت ریاست'),
-- (8, 1, false,'معاونیت نظارت و حراست از قانون', 'Administrative Office of the President','د چارو ادارې لوی ریاست'),
--     (19, 8, false,'ریاست نظارت و ارزیابی', 'Directorate of Monitoring and Evaluation', 'د څارنې او ارزونې ریاست'),
--     (20, 8, false,'ریاست امور حقوقی، تقئینی و قضائی', 'Directorate of Legal, Legislative and Judicial affaires', 'د حقوقي، تقنیني، او قضايي چارو ریاست'),
--     (21, 8, false,'ریاست تعقیب تعهدات، هدایات، فرامین و احکام', 'Directorate of Follow-up of Agreements, Guidances, Commands and Decrees', 'د تعهداتو، هدایاتو، فرامینو او احکامو د تعقیب ریاست'),


-- INSERT INTO public.department(id, parent_id, deleted, name_dr, name_en, name_ps) VALUES 
-- (63, null, false, 'وزارت عدلیه', 'Ministry of Justice','د عدلیې وزارت'),
-- (64, null, false, 'وزارت مالیه', 'Ministry of Finance','د مالیی وزارت '),
-- (65, null, false, 'وزارت زراعت، آبیاری و مالداری ', '  MINISTRY OF AGRICULTURE, IRRIGATION, AND LIVESTOCK (MAIL)',' دکرنې، اوبولګولو او مالدارۍ وزارت '),
-- (66, null, false, 'وزارت صنعت و تجارت', 'Ministry of Industry and Commerce','د صنعت او سوداگری وزارت'),
-- (67, null, false, 'وزارت مخابرات و تکنالوژی معلوماتی', 'Ministry of Communications and Information Technology','د مخابراتو او معلوماتي ټکنالوجۍ وزارت'),
-- (68, null, false, 'وزارت دفاع ملی', 'Ministry of Defense ','د ملي دفاع وزارت'),
-- (69, null, false, 'وزارت معارف', 'Ministry of Education','د پوهنې وزارت'),
-- (70, null, false, 'وزارت فواید عامه', 'Ministry of Public Works','د ټولګټو وزارت'),
-- (71, null, false, 'وزارت امور خارجه', 'Ministry of Foreign Affairs','د بهرنیو چارو وزارت'),
-- (72, null, false, 'وزارت صحت عامه', 'Ministry of Public Health','د عامې روغتیا وزارت'),
-- (73, null, false, 'وزارت تحصیلات عالی', 'Ministry of Higher Education','د لوړو زده کړو وزارت'),
-- (74, null, false, 'وزارت اطلاعات و فرهنگ', 'Ministry of Information and Culture','د اطلاعاتو او فرهنګ وزارت'),
-- (75, null, false, 'وزارت کار و امور اجتماعی', 'Ministry of Labor and Social Affairs','د کار او ټولنیزو چارو وزارت'),
-- (76, null, false, 'وزارت معادن و پترولیم', 'Ministry of Mines and Petroleum','د کانونو او  پټرولیم وزارت'),
-- (77, null, false, 'وزارت احیاء و انکشاف دهات', 'Ministry of Rural Rehabilitation and Development (MRRD)','د کليو د بيارغونې او پراختيا وزارت'),
-- (78, null, false, 'وزارت ترانسپورت', 'Ministry of Transport','د ترانسپورت وزارت'),
-- (79, null, false, 'وزارت شهرسازی و اراضی', 'Ministry of Urban Development and Land','د ښار جوړولو او ځمکو وزارت'),
-- (80, null, false, 'وزارت امور شهداء و معلولین', 'Ministry of Martyrs and Disabled Affairs','د شهیدانو او معلولینو چارو وزارت'),
-- (81, null, false, 'وزارت امور مهاجرین و عودت کنندگان', 'Ministry of Refugees and Repatriations','د کډوالو او راستنېدونکو چارو وزارت'),
-- (82, null, false, 'وزارت امور داخله', 'Ministry of Interior Affairs','د کورنیو چارو وزارت'),
-- (83, null, false, 'وزارت امور سرحدات و قبایل', 'Ministry of Borders and Tribal Affairs','د سرحدونو او قبایلو چارو وزارت'),
-- (84, null, false, 'وزارت اقتصاد', 'Ministry of Economy','د اقتصاد وزارت'),
-- (85, null, false, 'وزارت ارشاد، حج و اوقاف', 'Ministry of Hajj and Religious Affairs','د ارشاد، حج او اوقافو وزارت'),
-- (86, null, false, 'وزارت امور زنان', 'Ministry of Women Affairs','د ښځو چارو وزارت'),
-- (87, null, false, 'دفتر وزیر دولت در امور پارلمانی', 'Ministry for Parliamentary Affairs','په پارلماني چارو کې د دولت وزارت'),
-- (88, null, false, 'دفتر وزیر دولت در امور رسیدگی به حوادث ', 'Office of State Minister for Disaster Management','پیښو ته د رسیدو په چارو کې د دولت وزارت'),
-- (89, null, false, 'دفتر وزیر دولت در امور صلح', 'State Ministry for Peace','د سولې په چارو کې د دولت وزارت');

-- INSERT INTO public.department(id, parent_id, deleted, name_dr, name_en, name_ps) VALUES 
-- (90, null, false, 'کمیسیون مستقل انتخابات','Independent Election Commission of Afghanistan','د ټاکنو خپلواک کمیسیون'),
-- (91, null, false, 'کمیسیون مستقل اصلاحات اداری و خدمات ملکی', 'Independent Administrative Reform and Civil Service Commission','د اداري اصلاحاتو او ملکي خدمتونو خپلواک کمېسیون'),
-- (92, null, false, 'کمیسیون مستقل نظارت بر تطبیق قانون اساسی', 'Independent Commission for Overseeing the Implementation of the Constitution (ICOIC)','د اساسي قانون پر تطبیق د څارنې خپلواک کمیسیون'),
-- (93, null, false, 'کمیسیون مستقل حقوق بشر افغانستان', 'Afghanistan Independent Human Rights Commission ','د افغانستان د بشری حقونو خپلواک کمیسیون'),
-- (94, null, false, 'کمیسیون مبارزه با فساد اداری', 'Anti-Corruption Commission','د فساد وضد  کمیسیون'),
-- (95, null, false, 'کمیسیون شکایات انتخاباتی', 'Electoral Complaints Commission','د ټاکنیزو شکایتونو کمیسیون'),
-- (96, null, false, 'کمیسیون دسترسی به اطلاعات', 'Access to Information Commission','اطلاعاتو ته د لاسرسي کمیسیون');

-- INSERT INTO public.department(id, parent_id, deleted, name_dr, name_en, name_ps) VALUES 
-- (97, null, false, 'اداره عالی تفتیش','Supreme Audit Office',' د پلټنې عالي اداره'),
-- (98, null, false, 'اداره ملی ستندرد', 'Afghanistan National Standards Authority (ANSA)','د افغانستان د سټنډر ملي اداره (انسا)'),
-- (99, null, false, 'اداره ملی حفاظت محیط زیست', 'National Environmental Protection Agency (NEPA)','د چاپیریال ساتنی ملي اداره'),
-- (100, null, false, 'اداره ارگانهای محلی', 'Directorate of Local Governance','د سیمه ییزه اورګانونو اداره'),
-- (101, null, false, 'اداره انکشاف زون پایتخت', 'CAPITAL REGION DEVELOPMENT AUTHORITY (CRIDA)','د پلازمېنې زون پراختیا اداره'),
-- (102, null, false, 'اداره ملی احصائیه و معلومات', 'National Statistics and Information Authority (NSIA)','د احصایې او معلوماتو ملي اداره'),
-- (103, null, false, 'داره انرژی هستوی', 'Afghanistan Nuclear Energy Agency (ANEA)','د هستوي انرژی اداره'),
-- (104, null, false, 'اداره تعلیمات تخنیکی و مسلکی', 'Technical Vocational Education and Training (TVET)','د تخنیکي او مسلکي زده کړو اداره'),
-- (105, null, false, 'اداره ملی امتحانات', 'National Examination Authority ','د ازموینو ملي اداره'),
-- (106, null, false, 'اداره تنظیم خدمات مخابراتی افغانستان (اترا)', 'Afghanistan Telecom Regulatory Authority (ATRA)','د مخابراتي خدمتونو د تنظیم اداره'),
-- (107, null, false, 'اداره تنظیم خدمات انرژی ', 'Energy Services Regulatory Authority','د انرژی د خدماتو د تنظیم اداره'),
-- (108, null, false, 'اداره هوانوردی ملکی', 'Civil Aviation Authority of Afghanistan','د ملکی هوایی چلند اداره'),
-- (109, null, false, 'اداره خط آهن افغانستان', 'Afghanistan Railway Authority','د افغانستان د اوسپنې پټلۍ اداره'),
-- (110, null, false, 'اداره ملی تنظیم امور آب', 'National Water Affairs Regulation Authority (NWARA)','د اوبو د چارو د تنظیم ملي اداره'),
-- (111, null, false, 'اداره تنظیم امور زندانها', 'Prison Administration','د زندانونو د چارو سمون اداره'),
-- (112, null, false, 'اکادمی علوم افغانستان', 'Academy Science of Afghanistan','د افغانستان د علومو اکاډمي'),
-- (113, null, false, 'لوی څارنوالی', 'Attorney General','لویه څارنوالي'),
-- (114, null, false, 'اداره تنظیم نفت و گاز افغانستان', 'Afghanistan Oil and Gas Regulatory Authority','د افغانستان د نفت او گازو اداره'),
-- (115, null, false, 'ریاست عمومی دارالانشای ولسی جرگه', 'General Chairman of the Secretariat of the Wolesi Jirga','د ولسی جرگی د دارالانشاء لوی ریاست'),
-- (116, null, false, 'ریاست عمومی دارالانشای مشرانو جرگه', 'House of Elders Secretariat General','د مشرانوجرگی د دارالانشاء لوی ریاست'),
-- (117, null, false, 'ریاست عمومی تربیت بدنی و ورزش', 'General Directorate of Physical Education and Sports','د افغانستان د بدني روزنې او سپورت عمومي ریاست'),
-- (118, null, false, 'ریاست عمومی انسجام امور کوچی ها', 'General Directorate of Cohesion of Kuchi Affairs','د کوچیانو چارو د سمون لوی ریاست'),
-- (119, null, false, 'ریاست عمومی امنیت ملی', 'National Directorate of Security','د ملي امنیت لوی ریاست'),
-- (120, null, false, 'ریاست عمومی محافظت ریاست جمهوری', 'General Directorate of Presidential Protection','د ولسمشرۍ د ساتنې ریاست عمومی ریاست'),
-- (121, null, false, 'د افغانستان بانک', 'Da Afghanistan Bank','د افغانستان بانک'),
-- (122, null, false, 'ریاست عمومی دفتر مقام عالی ریاست جمهوری ', 'Office of Chief of Staff to the President','د جمهوري ریاست لوړ مقام د دفتر لوی ریاست'),
-- (123, null, false, 'ریاست عمومی رادیو تلویزیون ملی', 'General Directorate of National Radio Television','د افغانستان ملي راډیو تلویزیون عمومی ریاست'),
-- (124, null, false, 'معاونیت اول ریاست جمهوری', ' Office of the First Vice President','د جمهوري ریاست لومړی معاونیت'),
-- (125, null, false, 'معاونیت دوم ریاست جمهوری', 'Office of the Second Vice President','د جمهوري ریاست دوهم معاونیت'),
-- (126, null, false, 'د آریانا افغان هوایي شرکت ', 'Ariana Afghan Airlines','د آریانا افغان هوایي شرکت '),
-- (127, null, false, 'شرکت آبرسانی و کانالیزاسیون شهری ', 'Urban Water Supply and Sewerage Company','د ښاري اوبو رسولو او فاضله اوبو رسولو شرکت'),
-- (128, null, false, 'د افغانستان برښنا شرکت', 'Da Afghanistan Breshna Sherkat (DABS)','د افغانستان برښنا شرکت'),
-- (129, null, false, 'شاروالی کابل', 'Kabul Municipality','کابل ښاروالی'),
-- (130, null, false, 'دفتر شورای امنیت ملی', 'Office of the National Security Council ','د ملي امنیت شورا دفتر'),
-- (131, null, false, 'شورای طبی افغانستان', 'Afghanistan Medical Council','د افغانستان طبي شورا'),
-- (132, null, false, 'شورای عالی مصالحه ملی', 'High Council National Reconciliation','د ملی مصالحی عالی شورا'),
-- (133, null, false, 'شرکت انکشاف ملی', 'National Development Corporation','د ملي پراختیا شرکت'),
-- (134, null, false, 'تاق تجارت و سرمایه گذاری', 'Afghanistan Chamber of Commerce and Investment (ACCI)','د افغانستان د سوداګرۍ او پانګونې اتاق'),
-- (135, null, false, 'اتاق صنایع و معادن افغانستان', 'Afghanistan Chamber of Industries and Mines','د افغانستان صنایعو او معدنونو خونه'),
-- (136, null, false, 'اتاق تجارت بین المللی', 'International Chamber of Commerce','د سوداګرۍ نړیوال خونه'),
-- (137, null, false, 'اتاق زنان تجارت پیشه', 'Afghanistan Women Chamber of Commerce and Industry (AWCCI)','د افغانستان د ښځو د سوداګرۍ او صنایعو خونه'),
-- (138, null, false, 'فدراسیون اتاق های افغانستان', 'Afghan Chambers Federation','د افغانستان د اطاقونو فدراسیون'),
-- (139, null, false, 'ستره محکمه', 'The Supreme Court','ستره محکمه'),
-- (140, null, false, 'جمعیت هلال احمر افغانی (سره میاشت)', 'Afghan Red Crescent Society ','د افغاني سرې میاشتی ټولنه'),
-- (141, null, false, 'بورد کرکت افغانستان', 'Da Afghanistan Cricket Board','د افغانستان کرکټ بورډ');


-- for clusore table shura directorate

-- INSERT INTO public.department(id, parent_id, deleted, name_dr, name_en, name_ps) VALUES 
-- (142, 14, false,'معاونیت انسجام امور شوراهای عالی', 'deputy of shura and cabinate','معاونیت انسجام امور شوراهای عالی'),
-- (143, 142, false,'آمریت انسجام امور شورای عالی اقتصادی', 'deputy of shura and cabinate','آمریت انسجام امور شورای عالی اقتصادی'),
-- (144, 142, false,'آمریت انسجام امور شورای عالی حاکمیت قانون و حکومتداری', 'deputy of shura and cabinate','آمریت انسجام امور شورای عالی حاکمیت قانون و حکومتداری'),
-- (145, 142, false,'آمریت انسجام امور شورای عالی آب، اراضی، توسعه شهری و محیط زیست', 'deputy of shura and cabinate','آمریت انسجام امور شورای عالی آب، اراضی، توسعه شهری و محیط زیست'),
-- (146, 142, false,'آمریت انسجام امور شورا های عالی علمی، فرهنگی و سرمایه بشری، زنان و جوانان', 'deputy of shura and cabinate','آمریت انسجام امور شورا های عالی علمی، فرهنگی و سرمایه بشری، زنان و جوانان'),
-- (147, 142, false,'مدیریت اجرائیه', 'executive manager','مدیریت اجرائیه'),
-- (148, 143, false,'کارشناس امور زیربنا و معادن', 'executive manager','کارشناس امور زیربنا و معادن'),
-- (149, 148, false,'همکار کارشناس امور زیربنا و معادن', 'executive manager','همکار کارشناس امور زیربنا و معادن'),
-- (150, 143, false,'کارشناس تحلیل امور اقتصادی', 'executive manager','کارشناس تحلیل امور اقتصادی'),
-- (151, 150, false,'همکار کارشناس تحلیل امور اقتصادی', 'executive manager','همکار کارشناس تحلیل امور اقتصادی'),
-- (152, 143, false,'کارشناس پیگیری مصوبات شورای اقتصادی', 'executive manager','کارشناس پیگیری مصوبات شورای اقتصادی'),
-- (153, 152, false,'همکار کارشناس پیگیری مصوبات شورای اقتصادی', 'executive manager','همکار کارشناس پیگیری مصوبات شورای اقتصادی'),
-- (154, 144, false,'کارشناس امور حکومتداری', 'executive manager','کارشناس امور حکومتداری'),
-- (155, 154, false,'همکار کارشناس امور حکومتداری', 'executive manager','همکار کارشناس امور حکومتداری'),
-- (156, 144, false,'کارشناس امور حقوقی', 'executive manager','کارشناس امور حقوقی'),
-- (157, 156, false,'همکار کارشناس امور حقوقی', 'executive manager','همکار کارشناس امور حقوقی'),
-- (158, 144, false,'کارشناس پیگیری مصوبات شورای حاکمیت قانون و حکومتداری', 'executive manager','کارشناس پیگیری مصوبات شورای حاکمیت قانون و حکومتداری'),
-- (159, 158, false,'همکار کارشناس پیگیری مصوبات', 'executive manager','همکار کارشناس پیگیری مصوبات'),
-- (160, 145, false,'کارشناس امور آب و اراضی', 'executive manager','کارشناس امور آب و اراضی'),
-- (161, 160, false,'همکار کارشناس امور آب و اراضی', 'executive manager','همکار کارشناس امور آب و اراضی'),
-- (162, 145, false,'کارشناس امور توسعه شهری', 'executive manager','کارشناس امور توسعه شهری'),
-- (163, 162, false,'همکار کارشناس امور توسعه شهری', 'executive manager','همکار کارشناس امور توسعه شهری'),
-- (164, 145, false,'کارشناس پیگیری مصوبات', 'executive manager','کارشناس پیگیری مصوبات'),
-- (165, 164, false,'همکار کارشناس پیگیری مصوبات', 'executive manager','همکار کارشناس پیگیری مصوبات'),
-- (166, 146, false,'کارشناس امور علمی و فرهنگی', 'executive manager','کارشناس امور علمی و فرهنگی'),
-- (167, 146, false,'کارشناس امور اجتماعی', 'executive manager','کارشناس امور اجتماعی'),
-- (168, 167, false,'همکار کارشناس امور اجتماعی', 'executive manager','همکار کارشناس امور اجتماعی'),
-- (169, 146, false,'کارشناس پیگیری مصوبات', 'executive manager','کارشناس پیگیری مصوبات'),
-- (170, 169, false,'همکار کارشناس پیگیری مصوبات', 'executive manager','همکار کارشناس پیگیری مصوبات'),
-- (171, 147, false,'کارمندان اجرائیوی', 'executive manager','کارمندان اجرائیوی');



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


INSERT INTO public.user_tbl(id, active, entity_id, department_id, job_id, address, email, name, password, username, avatar) VALUES
(1, true, 1, 3, 9, 'Kabul', 'admin@nsia.gov.af', 'Admin User', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW', 'admin', '2019-02-24_21_22_53_thumbnail.jpg'),
(2, true, 1, 2, 5, 'Kabul', 'deputy@nsia.gov.af', 'Deputy User', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW', 'deputy_user', '2019-02-24_21_22_53_thumbnail.jpg'),
(3, true, 1, 2, 6, 'Kabul', 'test@nsia.gov.af', 'Director User', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW', 'director_user', '2019-02-24_21_22_53_thumbnail.jpg'),
(4, true, 1, 2, 11, 'Kabul', 'test@nsia.gov.af', 'Specialist User', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW', 'specialist_user', '2019-02-24_21_22_53_thumbnail.jpg'),
(5, true, 1, 4, 6, 'Kabul', 'test@nsia.gov.af', 'AOP Rayes Daftar User', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW', 'aop_rayesdaftar_user', '2019-02-24_21_22_53_thumbnail.jpg'),
(6, true, 1, 2, 8, 'Kabul', 'test@nsia.gov.af', 'Followup Manager User', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW', 'followup_manager_user', '2019-02-24_21_22_53_thumbnail.jpg'),
(7, true, 102, 102, 6, 'Kabul', 'test@nsia.gov.af', 'NSIA Rayes Daftar User', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW', 'nsia_rayesdaftar_user', '2019-02-24_21_22_53_thumbnail.jpg'),
(8, true, 1, 2, 8, 'Kabul', 'test@nsia.gov.af', 'Cabinate director', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW', 'cabinat_director', '2019-02-24_21_22_53_thumbnail.jpg');


INSERT INTO public.group_tbl(id, name, description, active) VALUES
(1, 'ADMIN_GROUP', 'ADMIN GROUP', true),
(2, 'DOCMNG_USER_GROUP', 'Doc Mng user GROUP', true),
(3, 'DOCMNG_DIRECTOR_GROUP', 'Doc Mng specialist GROUP', true),
(4, 'DOCMNG_SPECIALIST_GROUP', 'Doc Mng specialist GROUP', true),
(5, 'DOCMNG_DOC_EVALUATION_GROUP', 'Doc Mng document evaluation GROUP', true),
(6, 'DOCMNG_DOC_FOLLOWUP_GROUP', 'Doc Mng document followup GROUP', true),
(7, 'TASKMNG_USER_GROUP', 'Task Mng User GROUP', true);


INSERT INTO public.role(id, name, description, active) VALUES
(1, 'ADMIN_ROLE', 'It can manage the application', true),
(2, 'DOCMNG_INTERNAL_USER_ROLE', 'It can draft and send document to only internal entities', true),
(3, 'DOCMNG_EXTERNAL_USER_ROLE', 'It can draft and send document to only external entities', true),
(4, 'DOCMNG_SPECIALIST_ROLE', 'It can have access and add followup details', true),
(5, 'DOCMNG_DOC_EVALUATION_ROLE', 'It can have access and evaluate document', true),
(6, 'DOCMNG_DOC_FOLLOWUP_ROLE', 'It can add followup details to a document', true),
(7, 'TASKMNG_USER_ROLE', 'It access to task mng', true);

-- Doc Mng Roles
INSERT INTO public.role_permission(role_id, permission_id)
SELECT 1, id
FROM public.permission;

INSERT INTO public.role_permission(role_id, permission_id) VALUES
(2, 19001), (2 ,19004), (2 ,19005), (2 ,19006), (2 ,19007), (2 ,19008);
INSERT INTO public.role_permission(role_id, permission_id) VALUES
(3, 19001), (3 ,19004), (3 ,19005), (3 ,19006), (3 ,19007), (3 ,19008);
INSERT INTO public.role_permission(role_id, permission_id) VALUES
(4, 19001), (4 ,19004), (4 ,19005), (4 ,19006), (4 ,19007), (4 ,19008);
INSERT INTO public.role_permission(role_id, permission_id) VALUES
(5, 19001), (5 ,19004), (5 ,19005), (5 ,19006), (5 ,19007), (5 ,19008), (5, 19035), (5, 19016);
INSERT INTO public.role_permission(role_id, permission_id) VALUES
(6, 19001), (6 ,19004), (6 ,19005), (6 ,19006), (6 ,19007), (6 ,19008);

-- Task Mng Roles
INSERT INTO public.role_permission(role_id, permission_id) VALUES
(7 ,18001), (7 ,18002), (7 ,18004), (7 ,18005),(7 ,18006),(7 ,18007),(7 ,18008), (7 ,18009), (7 ,18010), (7 ,18011),
(7 ,18012), (7 ,18014), (7 ,18015), (7 ,18016),(7 ,18017),(7 ,18018),(7 ,18019), 
(7 ,18020), (7 ,18021), (7 ,18022), (7 ,18023), (7 ,18024), (7 ,18025),(7 ,18026),(7 ,18027),(7 ,18028);



INSERT INTO public.user_group(user_id, group_id) VALUES
(1, 1),
(2, 2),
(3, 2),
(4, 2), (4, 3),
(5, 2), (5, 5),
(6, 2), (6, 5),
(7, 2);

INSERT INTO public.group_role(group_id, role_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6);

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



-- INSERT INTO public.doc_mng_document_type_template(id, created_by, entity_id, department_id, document_type_id, template) VALUES
-- (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);





















