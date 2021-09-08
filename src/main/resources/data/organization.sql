INSERT INTO public.organization(
	id, active, org_type, created_by, deleted, name_dr, name_en, name_ps)
	VALUES 
	(1, 't', 'MINISTRY', 'admin', 'f', 'وزارت عدلیه', 'Ministry of Justice','د عدلیې وزارت'),
	(2, 't', 'MINISTRY', 'admin', 'f', 'وزارت مالیه', 'Ministry of Finance','د مالیی وزارت '),
	(3, 't', 'MINISTRY', 'admin', 'f', 'وزارت زراعت، آبیاری و مالداری ', '  MINISTRY OF AGRICULTURE, IRRIGATION, AND LIVESTOCK (MAIL)',' دکرنې، اوبولګولو او مالدارۍ وزارت '),
	(4, 't', 'MINISTRY', 'admin', 'f', 'وزارت صنعت و تجارت', 'Ministry of Industry and Commerce','د صنعت او سوداگری وزارت'),
	(5, 't', 'MINISTRY', 'admin', 'f', 'وزارت مخابرات و تکنالوژی معلوماتی', 'Ministry of Communications and Information Technology','د مخابراتو او معلوماتي ټکنالوجۍ وزارت'),
	(6, 't', 'MINISTRY', 'admin', 'f', 'وزارت دفاع ملی', 'Ministry of Defense ','د ملي دفاع وزارت'),
	(7, 't', 'MINISTRY', 'admin', 'f', 'وزارت معارف', 'Ministry of Education','د پوهنې وزارت'),
	(8, 't', 'MINISTRY', 'admin', 'f', 'وزارت فواید عامه', 'Ministry of Public Works','د ټولګټو وزارت'),
	(9, 't', 'MINISTRY', 'admin', 'f', 'وزارت امور خارجه', 'Ministry of Foreign Affairs','د بهرنیو چارو وزارت'),
	(10, 't', 'MINISTRY', 'admin', 'f', 'وزارت صحت عامه', 'Ministry of Public Health','د عامې روغتیا وزارت'),
	(11, 't', 'MINISTRY', 'admin', 'f', 'وزارت تحصیلات عالی', 'Ministry of Higher Education','د لوړو زده کړو وزارت'),
	(12, 't', 'MINISTRY', 'admin', 'f', 'وزارت اطلاعات و فرهنگ', 'Ministry of Information and Culture','د اطلاعاتو او فرهنګ وزارت'),
	(13, 't', 'MINISTRY', 'admin', 'f', 'وزارت کار و امور اجتماعی', 'Ministry of Labor and Social Affairs','د کار او ټولنیزو چارو وزارت'),
	(14, 't', 'MINISTRY', 'admin', 'f', 'وزارت معادن و پترولیم', 'Ministry of Mines and Petroleum','د کانونو او  پټرولیم وزارت'),
	(15, 't', 'MINISTRY', 'admin', 'f', 'وزارت احیاء و انکشاف دهات', 'Ministry of Rural Rehabilitation and Development (MRRD)','د کليو د بيارغونې او پراختيا وزارت'),
	(16, 't', 'MINISTRY', 'admin', 'f', 'وزارت ترانسپورت', 'Ministry of Transport','د ترانسپورت وزارت'),
	(17, 't', 'MINISTRY', 'admin', 'f', 'وزارت شهرسازی و اراضی', 'Ministry of Urban Development and Land','د ښار جوړولو او ځمکو وزارت'),
	(18, 't', 'MINISTRY', 'admin', 'f', 'وزارت امور شهداء و معلولین', 'Ministry of Martyrs and Disabled Affairs','د شهیدانو او معلولینو چارو وزارت'),
	(19, 't', 'MINISTRY', 'admin', 'f', 'وزارت امور مهاجرین و عودت کنندگان', 'Ministry of Refugees and Repatriations','د کډوالو او راستنېدونکو چارو وزارت'),
	(20, 't', 'MINISTRY', 'admin', 'f', 'وزارت امور داخله', 'Ministry of Interior Affairs','د کورنیو چارو وزارت'),
	(21, 't', 'MINISTRY', 'admin', 'f', 'وزارت امور سرحدات و قبایل', 'Ministry of Borders and Tribal Affairs','د سرحدونو او قبایلو چارو وزارت'),
	(22, 't', 'MINISTRY', 'admin', 'f', 'وزارت اقتصاد', 'Ministry of Economy','د اقتصاد وزارت'),
	(23, 't', 'MINISTRY', 'admin', 'f', 'وزارت ارشاد، حج و اوقاف', 'Ministry of Hajj and Religious Affairs','د ارشاد، حج او اوقافو وزارت'),
	(24, 't', 'MINISTRY', 'admin', 'f', 'وزارت امور زنان', 'Ministry of Women Affairs','د ښځو چارو وزارت'),
	(25, 't', 'MINISTRY', 'admin', 'f', 'دفتر وزیر دولت در امور پارلمانی', 'Ministry for Parliamentary Affairs','په پارلماني چارو کې د دولت وزارت'),
	(26, 't', 'MINISTRY', 'admin', 'f', 'دفتر وزیر دولت در امور رسیدگی به حوادث ', 'Office of State Minister for Disaster Management','پیښو ته د رسیدو په چارو کې د دولت وزارت'),
	(27, 't', 'MINISTRY', 'admin', 'f', 'دفتر وزیر دولت در امور صلح', 'State Ministry for Peace','د سولې په چارو کې د دولت وزارت'),
    
    (28, 't', 'COMMISSION', 'admin', 'f', 'کمیسیون مستقل انتخابات','Independent Election Commission of Afghanistan','د ټاکنو خپلواک کمیسیون'),
	(29, 't', 'COMMISSION', 'admin', 'f', 'کمیسیون مستقل اصلاحات اداری و خدمات ملکی', 'Independent Administrative Reform and Civil Service Commission','د اداري اصلاحاتو او ملکي خدمتونو خپلواک کمېسیون'),
	(30, 't', 'COMMISSION', 'admin', 'f', 'کمیسیون مستقل نظارت بر تطبیق قانون اساسی', 'Independent Commission for Overseeing the Implementation of the Constitution (ICOIC)','د اساسي قانون پر تطبیق د څارنې خپلواک کمیسیون'),
	(31, 't', 'COMMISSION', 'admin', 'f', 'کمیسیون مستقل حقوق بشر افغانستان', 'Afghanistan Independent Human Rights Commission ','د افغانستان د بشری حقونو خپلواک کمیسیون'),
	(32, 't', 'COMMISSION', 'admin', 'f', 'کمیسیون مبارزه با فساد اداری', 'Anti-Corruption Commission','د فساد وضد  کمیسیون'),
	(33, 't', 'COMMISSION', 'admin', 'f', 'کمیسیون شکایات انتخاباتی', 'Electoral Complaints Commission','د ټاکنیزو شکایتونو کمیسیون'),
	(34, 't', 'COMMISSION', 'admin', 'f', 'کمیسیون دسترسی به اطلاعات', 'Access to Information Commission','اطلاعاتو ته د لاسرسي کمیسیون'),

	(35, 't', 'AUTHORITY', 'admin', 'f', 'اداره عالی تفتیش','Supreme Audit Office',' د پلټنې عالي اداره'),
	(36, 't', 'AUTHORITY', 'admin', 'f', 'اداره ملی ستندرد', 'Afghanistan National Standards Authority (ANSA)','د افغانستان د سټنډر ملي اداره (انسا)'),
	(37, 't', 'AUTHORITY', 'admin', 'f', 'اداره ملی حفاظت محیط زیست', 'National Environmental Protection Agency (NEPA)','د چاپیریال ساتنی ملي اداره'),
	(38, 't', 'AUTHORITY', 'admin', 'f', 'اداره ارگانهای محلی', 'Directorate of Local Governance','د سیمه ییزه اورګانونو اداره'),
	(39, 't', 'AUTHORITY', 'admin', 'f', 'اداره انکشاف زون پایتخت', 'CAPITAL REGION DEVELOPMENT AUTHORITY (CRIDA)','د پلازمېنې زون پراختیا اداره'),
	(40, 't', 'AUTHORITY', 'admin', 'f', 'اداره ملی احصائیه و معلومات', 'National Statistics and Information Authority (NSIA)','د احصایې او معلوماتو ملي اداره'),
	(41, 't', 'AUTHORITY', 'admin', 'f', 'داره انرژی هستوی', 'Afghanistan Nuclear Energy Agency (ANEA)','د هستوي انرژی اداره'),
	(42, 't', 'AUTHORITY', 'admin', 'f', 'اداره تعلیمات تخنیکی و مسلکی', 'Technical Vocational Education and Training (TVET)','د تخنیکي او مسلکي زده کړو اداره'),
	(43, 't', 'AUTHORITY', 'admin', 'f', 'اداره ملی امتحانات', 'National Examination Authority ','د ازموینو ملي اداره'),
	(44, 't', 'AUTHORITY', 'admin', 'f', 'اداره تنظیم خدمات مخابراتی افغانستان (اترا)', 'Afghanistan Telecom Regulatory Authority (ATRA)','د مخابراتي خدمتونو د تنظیم اداره'),
	(45, 't', 'AUTHORITY', 'admin', 'f', 'اداره تنظیم خدمات انرژی ', 'Energy Services Regulatory Authority','د انرژی د خدماتو د تنظیم اداره'),
	(46, 't', 'AUTHORITY', 'admin', 'f', 'اداره هوانوردی ملکی', 'Civil Aviation Authority of Afghanistan','د ملکی هوایی چلند اداره'),
	(47, 't', 'AUTHORITY', 'admin', 'f', 'اداره خط آهن افغانستان', 'Afghanistan Railway Authority','د افغانستان د اوسپنې پټلۍ اداره'),
	(48, 't', 'AUTHORITY', 'admin', 'f', 'اداره ملی تنظیم امور آب', 'National Water Affairs Regulation Authority (NWARA)','د اوبو د چارو د تنظیم ملي اداره'),
	(49, 't', 'AUTHORITY', 'admin', 'f', 'اداره تنظیم امور زندانها', 'Prison Administration','د زندانونو د چارو سمون اداره'),
	(50, 't', 'AUTHORITY', 'admin', 'f', 'اکادمی علوم افغانستان', 'Academy Science of Afghanistan','د افغانستان د علومو اکاډمي'),
	(51, 't', 'AUTHORITY', 'admin', 'f', 'اداره تدارکات ملی', 'National Procurement Authority','د ملی تدارکاتو اداره'),
	(52, 't', 'AUTHORITY', 'admin', 'f', 'اداره عملیاتی و حمایوی انکشاف ملی ریاست جمهوری', 'Operation and Support Office if the President For National Development','د افغانستان د اسلامي جمهوریت د ملي پراختیا عملیاتي او ملاتړه اداره'),
	(53, 't', 'AUTHORITY', 'admin', 'f', 'لوی څارنوالی', 'Attorney General','لویه څارنوالي'),
	(54, 't', 'AUTHORITY', 'admin', 'f', 'اداره تنظیم نفت و گاز افغانستان', 'Afghanistan Oil and Gas Regulatory Authority','د افغانستان د نفت او گازو اداره'),
	(55, 't', 'AUTHORITY', 'admin', 'f', 'ریاست عمومی اداره امور ریاست جمهوری', 'Administrative Office of the President','د جمهوری ریاست د چارو ادارې لوی ریاست'),
	(56, 't', 'AUTHORITY', 'admin', 'f', 'ریاست عمومی دارالانشای ولسی جرگه', 'General Chairman of the Secretariat of the Wolesi Jirga','د ولسی جرگی د دارالانشاء لوی ریاست'),
	(57, 't', 'AUTHORITY', 'admin', 'f', 'ریاست عمومی دارالانشای مشرانو جرگه', 'House of Elders Secretariat General','د مشرانوجرگی د دارالانشاء لوی ریاست'),
	(58, 't', 'AUTHORITY', 'admin', 'f', 'ریاست عمومی تربیت بدنی و ورزش', 'General Directorate of Physical Education and Sports','د افغانستان د بدني روزنې او سپورت عمومي ریاست'),
	(59, 't', 'AUTHORITY', 'admin', 'f', 'ریاست عمومی انسجام امور کوچی ها', 'General Directorate of Cohesion of Kuchi Affairs','د کوچیانو چارو د سمون لوی ریاست'),
	(60, 't', 'AUTHORITY', 'admin', 'f', 'ریاست عمومی امنیت ملی', 'National Directorate of Security','د ملي امنیت لوی ریاست'),
	(61, 't', 'AUTHORITY', 'admin', 'f', 'ریاست عمومی محافظت ریاست جمهوری', 'General Directorate of Presidential Protection','د ولسمشرۍ د ساتنې ریاست عمومی ریاست'),
	(62, 't', 'AUTHORITY', 'admin', 'f', 'د افغانستان بانک', 'Da Afghanistan Bank','د افغانستان بانک'),
	(63, 't', 'AUTHORITY', 'admin', 'f', 'ریاست عمومی دفتر مقام عالی ریاست جمهوری ', 'Office of Chief of Staff to the President','د جمهوري ریاست لوړ مقام د دفتر لوی ریاست'),
	(64, 't', 'AUTHORITY', 'admin', 'f', 'ریاست عمومی رادیو تلویزیون ملی', 'General Directorate of National Radio Television','د افغانستان ملي راډیو تلویزیون عمومی ریاست'),
	(65, 't', 'AUTHORITY', 'admin', 'f', 'معاونیت اول ریاست جمهوری', ' Office of the First Vice President','د جمهوري ریاست لومړی معاونیت'),
	(66, 't', 'AUTHORITY', 'admin', 'f', 'معاونیت دوم ریاست جمهوری', 'Office of the Second Vice President','د جمهوري ریاست دوهم معاونیت'),
	(67, 't', 'AUTHORITY', 'admin', 'f', 'د آریانا افغان هوایي شرکت ', 'Ariana Afghan Airlines','د آریانا افغان هوایي شرکت '),
	(68, 't', 'AUTHORITY', 'admin', 'f', 'شرکت آبرسانی و کانالیزاسیون شهری ', 'Urban Water Supply and Sewerage Company','د ښاري اوبو رسولو او فاضله اوبو رسولو شرکت'),
	(69, 't', 'AUTHORITY', 'admin', 'f', 'د افغانستان برښنا شرکت', 'Da Afghanistan Breshna Sherkat (DABS)','د افغانستان برښنا شرکت'),
	(70, 't', 'AUTHORITY', 'admin', 'f', 'شاروالی کابل', 'Kabul Municipality','کابل ښاروالی'),
	(71, 't', 'AUTHORITY', 'admin', 'f', 'دفتر شورای امنیت ملی', 'Office of the National Security Council ','د ملي امنیت شورا دفتر'),
	(72, 't', 'AUTHORITY', 'admin', 'f', 'شورای طبی افغانستان', 'Afghanistan Medical Council','د افغانستان طبي شورا'),
	(73, 't', 'AUTHORITY', 'admin', 'f', 'شورای عالی مصالحه ملی', 'High Council National Reconciliation','د ملی مصالحی عالی شورا'),
	(74, 't', 'AUTHORITY', 'admin', 'f', 'شرکت انکشاف ملی', 'National Development Corporation','د ملي پراختیا شرکت'),
	(75, 't', 'AUTHORITY', 'admin', 'f', 'تاق تجارت و سرمایه گذاری', 'Afghanistan Chamber of Commerce and Investment (ACCI)','د افغانستان د سوداګرۍ او پانګونې اتاق'),
	(76, 't', 'AUTHORITY', 'admin', 'f', 'اتاق صنایع و معادن افغانستان', 'Afghanistan Chamber of Industries and Mines','د افغانستان صنایعو او معدنونو خونه'),
	(77, 't', 'AUTHORITY', 'admin', 'f', 'اتاق تجارت بین المللی', 'International Chamber of Commerce','د سوداګرۍ نړیوال خونه'),
	(78, 't', 'AUTHORITY', 'admin', 'f', 'اتاق زنان تجارت پیشه', 'Afghanistan Women Chamber of Commerce and Industry (AWCCI)','د افغانستان د ښځو د سوداګرۍ او صنایعو خونه'),
	(79, 't', 'AUTHORITY', 'admin', 'f', 'فدراسیون اتاق های افغانستان', 'Afghan Chambers Federation','د افغانستان د اطاقونو فدراسیون'),
	(80, 't', 'AUTHORITY', 'admin', 'f', 'ستره محکمه', 'The Supreme Court','ستره محکمه'),
	(81, 't', 'AUTHORITY', 'admin', 'f', 'جمعیت هلال احمر افغانی (سره میاشت)', 'Afghan Red Crescent Society ','د افغاني سرې میاشتی ټولنه'),
	(82, 't', 'AUTHORITY', 'admin', 'f', 'بورد کرکت افغانستان', 'Da Afghanistan Cricket Board','د افغانستان کرکټ بورډ');