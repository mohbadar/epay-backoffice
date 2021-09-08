INSERT INTO public.doc_mng_document_type_template(id, template, created_by, department_id, document_type_id, entity_id) VALUES 
(1, '<!DOCTYPE html><html><style>@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontBoldUri}}}"); font-weight: bold;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontLightUri}}}"); font-weight: lighter;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontRomanUri}}}"); font-weight: normal;}h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6, div, p{font-family: "Bahij Halvetica";}</style><head> <meta charset="UTF-8"> <meta name="viewport" content="width=device-width, initial-scale=1.0"> <meta http-equiv="X-UA-Compatible" content="ie=edge"> <title>Document</title></head><body dir="rtl"> <div style="padding-bottom: 30px;"> <div class="header" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; -webkit-box-pack: justify; -ms-flex-pack: justify; justify-content: space-between;"> <div class="right-logo" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; width:58%; -webkit-box-pack: end; -ms-flex-pack: end; justify-content: flex-end;"> <img src="{{serverApi}}/api/public/download-image/1" height="150" width="150"/> </div><div style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column;"> <div>ګنه/شماره:{{document.documentNo}}</div><div>نیټه/تاریخ:{{documentDate}}</div></div></div><div style=" display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column; -webkit-box-align: center; -ms-flex-align: center; align-items: center; font-size: 20px; font-weight: 600; line-height: 30px;"> <div>{{document.title}}په اړه د{{document.issuingAuthority.nameDr}}</div><div style="margin-top: 30px; font-size: 40px;">حکم</div></div></div><div style="margin-top: 10px;">{{{content}}}</div><div class="QR-code" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; -webkit-box-pack: end; -ms-flex-pack: end; justify-content: flex-end;"> <div> <img src="{{serverApi}}/api/public/documents/{{documentId}}/genQRCode/150/150" height="150" width="150"/> </div></div></div></body></html>',
1, 1, 1, 1),
(2, '<!DOCTYPE html><html><style>@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontBoldUri}}}"); font-weight: bold;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontLightUri}}}"); font-weight: lighter;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontRomanUri}}}"); font-weight: normal;}h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6, div, p{font-family: "Bahij Halvetica";}</style><head> <meta charset="UTF-8"> <meta name="viewport" content="width=device-width, initial-scale=1.0"> <meta http-equiv="X-UA-Compatible" content="ie=edge"> <title>Document</title></head><body dir="rtl"> <div style="padding-bottom: 30px;"> <div class="header" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; -webkit-box-pack: justify; -ms-flex-pack: justify; justify-content: space-between;"> <div class="right-logo" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; width:58%; -webkit-box-pack: end; -ms-flex-pack: end; justify-content: flex-end;"> <img src="{{serverApi}}/api/public/download-image/1" height="150" width="150"/> </div><div style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column;"> <div>ګنه/شماره:{{document.documentNo}}</div><div>نیټه/تاریخ:{{documentDate}}</div></div></div><div style=" display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column; -webkit-box-align: center; -ms-flex-align: center; align-items: center; font-size: 20px; font-weight: 600; line-height: 30px;"> <div>{{document.title}}په اړه د{{document.issuingAuthority.nameDr}}</div><div style="margin-top: 30px; font-size: 40px;">فرمان</div></div></div><div style="margin-top: 10px;">{{{content}}}</div><div class="QR-code" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; -webkit-box-pack: end; -ms-flex-pack: end; justify-content: flex-end;"> <div> <img src="{{serverApi}}/api/public/documents/{{documentId}}/genQRCode/150/150" height="150" width="150"/> </div></div></body></html>',
1, 1, 5, 1),
(3, '<!DOCTYPE html><html><style>@font-face{font-family:"Bahij Halvetica";src:url("{{{fontBoldUri}}}");font-weight:bold}@font-face{font-family:"Bahij Halvetica";src:url("{{{fontLightUri}}}");font-weight:lighter}@font-face{font-family:"Bahij Halvetica";src:url("{{{fontRomanUri}}}");font-weight:normal}h1,h2,h3,h4,h5,h6,.h1,.h2,.h3,.h4,.h5,.h6,div,p{font-family:"Bahij Halvetica"}</style><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><meta http-equiv="X-UA-Compatible" content="ie=edge"><title>Maktub</title></head><body dir="rtl"><div style="padding-bottom: 30px"><div class="header" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column;"><div class="title-text" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; -webkit-box-pack: justify; -ms-flex-pack: justify; justify-content: space-between; -webkit-box-align: center; -ms-flex-align: center; align-items: center;"><div class="title-pashto" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column; -webkit-box-align: center; -ms-flex-align: center; align-items: center;"><div>د افغانستان اسلامي جمهوري دولت</div><div>د جمهوري ریاست د چارو ادارې لوی ریاست</div></div><div class="title-logo" style=" display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column; -webkit-box-align: center; -ms-flex-align: center; align-items: center;"> <img src="{{serverApi}}/api/public/download-image/1" height="150" width="150" /><div style="margin-top: 10px;"><strong>Islamic Republic of Afghanistan</strong></div><div>Administrative Office of the President</div><div class="department-header" style="line-height: 1 !important;"> {{{departmentHeader}}}</div></div><div class="title-dari" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column; -webkit-box-align: center; -ms-flex-align: center; align-items: center;"><div>دولت جمهوری اسلامی افغانستان</div><div>ریاست عمومی اداره امور ریاست جمهوری</div></div></div></div><div style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column;"><div>ګنه/شماره&nbsp;:&nbsp; {{document.documentNo}}</div><div>نیټه/تاریخ&nbsp;:&nbsp; {{documentDate}}<div style="float: left">درجه استعجالیت: {{#eq document.documentPriorityType "NORMAL"}}عادی{{/eq}} {{#eq document.documentPriorityType "MEDIUM"}}متوسط{{/eq}} {{#eq document.documentPriorityType "URGENT"}}عاجل{{/eq}}</div></div></div><hr style="border: 1px solid black" /></div><div style="margin-top: 10px;"> {{{content}}}</div><div style="margin-top: 20px;"> {{{directorOrder}}}</div><div style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; -webkit-box-pack: justify; -ms-flex-pack: justify; justify-content: space-between; -webkit-box-align: center; -ms-flex-align: center; align-items: center;"><div style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column;"><div>آدرس: ریاست عمومی اداره امور ریاست ج.ا.ا، قصر مرمرین، کابل - افغانستان</div><div>Councils.Coordination@arg.gov.af</div><div>Tel: 0202143880 / 02022143882</div></div><div> <img src="{{serverApi}}/api/public/documents/{{documentId}}/genQRCode/150/150" height="150" width="150" /></div></div></body></html>',
1, 1, 4, 1),
(4, '<!DOCTYPE html><html lang="en"><style>@font-face{font-family: "Bahij Halvetica";src: url("{{{fontBoldUri}}}");font-weight: bold;}@font-face{font-family: "Bahij Halvetica";src: url("{{{fontLightUri}}}");font-weight: lighter;}@font-face{font-family: "Bahij Halvetica";src: url("{{{fontRomanUri}}}");font-weight: normal;}h1,h2,h3,h4,h5,h6,.h1,.h2,.h3,.h4,.h5,.h6,div,p{font-family: "Bahij Halvetica";}.table-content{position: relative;width: 100%;table-layout: fixed !important;overflow-wrap: break-word !important;border: 1px solid gray;border-collapse: collapse;}.table-content td{border: 1px solid grey;vertical-align: center;width: fit-content;}.table-content th{border: 1px solid grey;vertical-align: center;}</style><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><meta http-equiv="X-UA-Compatible" content="ie=edge"><title>Document</title></head><body dir="rtl"><div class="header" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row;-webkit-box-pack: justify; -ms-flex-pack: justify; justify-content: space-between;"><div class="right-logo"><img src="{{serverApi}}/api/public/download-image/1" height="150" width="150"/></div><div><div class="text" style="display: -webkit-box;display: -ms-flexbox;display: flex;-webkit-box-orient: horizontal;-webkit-box-direction: normal;-ms-flex-direction: row;flex-direction: row;-webkit-box-pack: justify;-ms-flex-pack: justify;justify-content: center;"><div style="display: -webkit-box;display: -ms-flexbox;display: flex;-webkit-box-orient: vertical;-webkit-box-direction: normal;-ms-flex-direction: column;padding: 10px;flex-direction: column;"><div>د افغانستان اسلامي جمهوري دولت</div><div>د احصائیي او معلوماتو ملی اداره</div></div><div style="display: -webkit-box;display: -ms-flexbox;display: flex;-webkit-box-orient: vertical;-webkit-box-direction: normal;padding: 10px;-ms-flex-direction: column;flex-direction: column;"><div>دولت جمهوری اسلامی افغانستان</div><div>ادارۀ ملی احصائیه و معلومات</div></div></div><div style="display: -webkit-box;display: -ms-flexbox;display: flex;-webkit-box-orient: vertical;-webkit-box-direction: normal;-ms-flex-direction: column; -webkit-box-align: center; -ms-flex-align: center; align-items: center;flex-direction: column;"><div>Islamic Republic of Afghanistan</div><div>National Statistics and Information Authority</div></div><div style="display: -webkit-box;display: -ms-flexbox;display: flex;-webkit-box-orient: vertical;-webkit-box-direction: normal;-ms-flex-direction: column; -webkit-box-align: center; -ms-flex-align: center; align-items: center;flex-direction: column;"><div>ریاست دفتر</div></div></div><div class="left-logo" style="margin-top: 20px;"><img src="{{serverApi}}/api/public/download-image/2" height="100" width="200"/></div></div><div style="margin-top: 20px; display: flex; flex-direction: column; justify-content: space-between; align-content: space-between;"><table class="table-content"><thead><tr><th>پیشنهاد</th><th>احکام</th></tr></thead><tbody><tr><td style="padding: 10px;">{{{content}}}</td><td style="padding: 10px;">{{{hukumPeshnehad}}}</td></tr></tbody><table></div><div class="QR-code" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; -webkit-box-pack: end; -ms-flex-pack: end; justify-content: flex-end;"> <div> <img src="{{serverApi}}/api/public/documents/{{documentId}}/genQRCode/150/150" height="150" width="150"/> </div></div></body></html>',
10, 2, 3, 2039),
(5, '<!DOCTYPE html><html lang="en"><style>@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontBoldUri}}}"); font-weight: bold;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontLightUri}}}"); font-weight: lighter;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontRomanUri}}}"); font-weight: normal;}h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6, div, p{font-family: "Bahij Halvetica";}</style><head> <meta charset="UTF-8"> <meta name="viewport" content="width=device-width, initial-scale=1.0"> <meta http-equiv="X-UA-Compatible" content="ie=edge"> <title>Document</title></head><body dir="rtl"> <div class="header" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column; -webkit-box-align: center; -ms-flex-align: center; align-items: center;"> <div class="right-logo"> <img src="{{{serverApi}}}/api/public/download-image/1" height="150" width="150"/> </div><div style=" display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column; -webkit-box-align: center; -ms-flex-align: center; align-items: center; font-size: 16px; font-weight: 600; line-height: 30px;"> <div>د افغانستان اسلامي جمهوریت</div><div>د جمهوری ریاست د چارو ادارې لوی ریاست</div><div>{{{departmentHeader}}}</div></div></div><div style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; -webkit-box-pack: justify; -ms-flex-pack: justify; justify-content: space-between;"> <div>ګنه/شماره&nbsp;:&nbsp;{{document.documentNo}}</div><div>نیټه/تاریخ&nbsp;:&nbsp;{{createdAt}}</div></div><div style="font-weight: 600; font-size: 18px; margin-top: 15px; padding: 5px; width: 30%; color: white; background-color:#009999"> یادداشت</div><div style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row;"><!-- <div style="font-weight: 600; font-size: 18px; padding: 2px; width: 10%; color: white; background-color:#009999"> در مورد</div><div style="width: 90%; padding: 2px; background-color:#d6e3bc"></div>--> </div><div style="margin-top: 5px; font-weight: 600; font-size: 18px;"> جلالتمآب رئیس جمهوری اسلامی افغانستان! </div><div style="margin-bottom: 5px; font-size: 18px;"> با ابراز تمنیات و آرزومندیهای نیک؛ </div>{{#each document.noteSections as |item index|}}<div style="font-weight: 600; padding: 4px; background-color:#d6e3bc">{{item.title}}&nbsp;:&nbsp;</div><div style=" display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column; -webkit-box-pack: justify; -ms-flex-pack: justify; justify-content: space-between;"> <div style="margin-top: 5px; margin-bottom: 10px;">{{{item.content}}}</div>{{/each}}<div style="margin-top: 30px;"> <div style="text-align: center;">با احترام</div><div style="text-align: center;">داکتر فضل محمود فضلی</div><div style="text-align: center;">رئیس عمومی اداره امور ریاس جمهوری ا.ا</div></div><div class="QR-code" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; -webkit-box-pack: end; -ms-flex-pack: end; justify-content: flex-end;"> <div> <img src="{{{serverApi}}}/api/public/documents/{{documentId}}/genQRCode/150/150" height="150" width="150"/> </div></div></div></body></html>',
1, 1, 7, 1),
(6, '<!DOCTYPE html><html><style>@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontBoldUri}}}"); font-weight: bold;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontLightUri}}}"); font-weight: lighter;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontRomanUri}}}"); font-weight: normal;}h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6, div, p{font-family: "Bahij Halvetica";}</style><head> <meta charset="UTF-8"> <meta name="viewport" content="width=device-width, initial-scale=1.0"> <meta http-equiv="X-UA-Compatible" content="ie=edge"> <title>Maktub</title></head><body dir="rtl"> <div style="padding-bottom: 30px"> <div class="header" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column;"> <div class="title-text" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; -webkit-box-pack: justify; -ms-flex-pack: justify; justify-content: space-between; -webkit-box-align: center; -ms-flex-align: center; align-items: center;"> <div class="title-pashto" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column; -webkit-box-align: center; -ms-flex-align: center; align-items: center;"> <div>د افغانستان اسلامي جمهوري دولت</div><div>د جمهوري ریاست د چارو ادارې لوی ریاست</div></div><div class="title-logo" style=" display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column; -webkit-box-align: center; -ms-flex-align: center; align-items: center;"> <img src="{{serverApi}}/api/public/download-image/1" height="150" width="150"/> <div style="margin-top: 10px;"><strong>Islamic Republic of Afghanistan</strong></div><div>Administrative Office of the President</div><div class="department-header" style="line-height: 1 !important;">{{{departmentHeader}}}</div></div><div class="title-dari" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column; -webkit-box-align: center; -ms-flex-align: center; align-items: center;"> <div>دولت جمهوری اسلامی افغانستان</div><div>ریاست عمومی اداره امور ریاست جمهوری</div></div></div></div><div style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column;"> <div>ګنه/شماره&nbsp;:&nbsp;{{document.documentNo}}</div><div>نیټه/تاریخ&nbsp;:&nbsp;{{documentDate}}<div style="float: left">درجه استعجالیت&nbsp;:&nbsp;{{#eq document.documentPriorityType "NORMAL"}}عادی{{/eq}}{{#eq document.documentPriorityType "MEDIUM"}}متوسط{{/eq}}{{#eq document.documentPriorityType "URGENT"}}عاجل{{/eq}}</div></div></div><hr style="border: 1px solid black"/> </div><div style="margin-top: 10px;">{{{content}}}</div><div style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; -webkit-box-pack: justify; -ms-flex-pack: justify; justify-content: space-between; -webkit-box-align: center; -ms-flex-align: center; align-items: center;"> <div style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column;"> <div>آدرس: ریاست عمومی اداره امور ریاست ج.ا.ا، قصر مرمرین، کابل - افغانستان</div><div>Councils.Coordination@arg.gov.af</div><div>Tel: 0202143880 / 02022143882</div></div><div> <img src="{{serverApi}}/api/public/documents/{{documentId}}/genQRCode/150/150" height="150" width="150"/> </div></div></body></html>',
1, 2, 9, 1),
(7, '<!DOCTYPE html><html lang="en"><style>@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontBoldUri}}}"); font-weight: bold;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontLightUri}}}"); font-weight: lighter;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontRomanUri}}}"); font-weight: normal;}h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6, div, p{font-family: "Bahij Halvetica";}.table-content{position: relative; width: 100%; table-layout: fixed !important; overflow-wrap: break-word !important; border: 1px solid gray; border-collapse: collapse;}.table-content td{border: 1px solid grey; vertical-align: center; width: fit-content;}.table-content th{border: 1px solid grey; vertical-align: center;}</style><head> <meta charset="UTF-8"> <meta name="viewport" content="width=device-width, initial-scale=1.0"> <meta http-equiv="X-UA-Compatible" content="ie=edge"> <title>Document</title></head><body dir="rtl"> <div class="header" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row;-webkit-box-pack: justify; -ms-flex-pack: justify; justify-content: space-between;"> <div class="right-logo"> <img src="{{serverApi}}/api/public/download-image/1" height="150" width="150"/> </div><div> <div class="text" style="display: -webkit-box;display: -ms-flexbox;display: flex;-webkit-box-orient: horizontal;-webkit-box-direction: normal;-ms-flex-direction: row;flex-direction: row;-webkit-box-pack: justify;-ms-flex-pack: justify;justify-content: center;"> <div style="display: -webkit-box;display: -ms-flexbox;display: flex;-webkit-box-orient: vertical;-webkit-box-direction: normal;-ms-flex-direction: column;padding: 10px;flex-direction: column;"> <div>د افغانستان اسلامي جمهوري دولت</div><div>د احصائیي او معلوماتو ملی اداره</div></div><div style="display: -webkit-box;display: -ms-flexbox;display: flex;-webkit-box-orient: vertical;-webkit-box-direction: normal;padding: 10px;-ms-flex-direction: column;flex-direction: column;"> <div>دولت جمهوری اسلامی افغانستان</div><div>ادارۀ ملی احصائیه و معلومات</div></div></div><div style="display: -webkit-box;display: -ms-flexbox;display: flex;-webkit-box-orient: vertical;-webkit-box-direction: normal;-ms-flex-direction: column; -webkit-box-align: center; -ms-flex-align: center; align-items: center;flex-direction: column;"> <div>Islamic Republic of Afghanistan</div><div>National Statistics and Information Authority</div></div><div style="display: -webkit-box;display: -ms-flexbox;display: flex;-webkit-box-orient: vertical;-webkit-box-direction: normal;-ms-flex-direction: column; -webkit-box-align: center; -ms-flex-align: center; align-items: center;flex-direction: column;"> <div class="department-header" style="line-height: 1 !important;">{{{departmentHeader}}}</div></div></div><div class="left-logo" style="margin-top: 20px;"> <img src="{{serverApi}}/api/public/download-image/2" height="100" width="200"/> </div></div><div style="display: -webkit-box;display: -ms-flexbox;display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column; margin-top: 10px;"> <div>ګنه/شماره&nbsp;:&nbsp;{{document.documentNo}}</div><div>نیټه/تاریخ&nbsp;:&nbsp;{{documentDate}}<div style="float: left">درجه استعجالیت&nbsp;:&nbsp;{{#eq document.documentPriorityType "NORMAL"}}عادی{{/eq}}{{#eq document.documentPriorityType "MEDIUM"}}متوسط{{/eq}}{{#eq document.documentPriorityType "URGENT"}}عاجل{{/eq}}</div></div></div><hr style="width: 100%; border: 1px solid rgb(0, 0, 0)"/> <div style=" display: -webkit-box; display: -ms-flexbox; display: flex;-webkit-box-orient: vertical;-webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column;-webkit-box-pack: justify; -ms-flex-pack: justify; justify-content: space-between; height: 1000px;"> <div style="margin-top: 20px;">{{{content}}}</div><div class="QR-code" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; -webkit-box-pack: end; -ms-flex-pack: end; justify-content: flex-end;"> <div> <img src="{{serverApi}}/api/public/documents/{{documentId}}/genQRCode/150/150" height="150" width="150"/> </div></div></div></body></html>',
1, 2, 4, 2039),
(8, '<!DOCTYPE html><html><style>@font-face{font-family:"Bahij Halvetica";src:url("{{{fontBoldUri}}}");font-weight:bold}@font-face{font-family:"Bahij Halvetica";src:url("{{{fontLightUri}}}");font-weight:lighter}@font-face{font-family:"Bahij Halvetica";src:url("{{{fontRomanUri}}}");font-weight:normal}h1,h2,h3,h4,h5,h6,.h1,.h2,.h3,.h4,.h5,.h6,div,p{font-family:"Bahij Halvetica"}</style><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><meta http-equiv="X-UA-Compatible" content="ie=edge"><title>Maktub</title></head><body dir="rtl"><div style="padding-bottom: 30px"><div class="header" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column;"><div class="title-text" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; -webkit-box-pack: justify; -ms-flex-pack: justify; justify-content: space-between; -webkit-box-align: center; -ms-flex-align: center; align-items: center;"><div class="title-pashto" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column; -webkit-box-align: center; -ms-flex-align: center; align-items: center;"><div>د افغانستان اسلامي جمهوري دولت</div><div>د جمهوري ریاست د چارو ادارې لوی ریاست</div></div><div class="title-logo" style=" display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column; -webkit-box-align: center; -ms-flex-align: center; align-items: center;"> <img src="{{serverApi}}/api/public/download-image/1" height="150" width="150" /><div style="margin-top: 10px;"><strong>Islamic Republic of Afghanistan</strong></div><div>Administrative Office of the President</div><div class="department-header" style="line-height: 1 !important;"> {{{departmentHeader}}}</div></div><div class="title-dari" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column; -webkit-box-align: center; -ms-flex-align: center; align-items: center;"><div>دولت جمهوری اسلامی افغانستان</div><div>ریاست عمومی اداره امور ریاست جمهوری</div></div></div></div><div style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column;"><div>ګنه/شماره&nbsp;:&nbsp; {{document.documentNo}}</div><div>نیټه/تاریخ&nbsp;:&nbsp; {{documentDate}}<div style="float: left">درجه استعجالیت: {{#eq document.documentPriorityType "NORMAL"}}عادی{{/eq}} {{#eq document.documentPriorityType "MEDIUM"}}متوسط{{/eq}} {{#eq document.documentPriorityType "URGENT"}}عاجل{{/eq}}</div></div></div><hr style="border: 1px solid black" /></div><div style="margin-top: 10px;"> {{{content}}}</div><div style="margin-top: 20px;"> {{{directorOrder}}}</div><div style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; -webkit-box-pack: justify; -ms-flex-pack: justify; justify-content: space-between; -webkit-box-align: center; -ms-flex-align: center; align-items: center;"><div style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column;"><div>آدرس: ریاست عمومی اداره امور ریاست ج.ا.ا، قصر مرمرین، کابل - افغانستان</div><div>Councils.Coordination@arg.gov.af</div><div>Tel: 0202143880 / 02022143882</div></div><div> <img src="{{serverApi}}/api/public/documents/{{documentId}}/genQRCode/150/150" height="150" width="150" /></div></div></body></html>',
1, 2, 11, 1),
(9, '<!DOCTYPE html><html lang="en"><style>@font-face{font-family:"Bahij Halvetica";src:url("{{{fontBoldUri}}}");font-weight:bold}@font-face{font-family:"Bahij Halvetica";src:url("{{{fontLightUri}}}");font-weight:lighter}@font-face{font-family:"Bahij Halvetica";src:url("{{{fontRomanUri}}}");font-weight:normal}h1,h2,h3,h4,h5,h6,.h1,.h2,.h3,.h4,.h5,.h6,div,p{font-family:"Bahij Halvetica"}.table-content{position:relative;width:100%;table-layout:fixed !important;overflow-wrap:break-word !important;border:1px solid gray;border-collapse:collapse}.table-content td{border:1px solid grey;vertical-align:center;width:fit-content}.table-content th{border:1px solid grey;vertical-align:center}</style><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><meta http-equiv="X-UA-Compatible" content="ie=edge"><title>Document</title></head><body dir="rtl"><div class="header" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column; -webkit-box-align: center; -ms-flex-align: center; align-items: center;"><div class="right-logo"> <img src="{{{serverApi}}}/api/public/download-image/1" height="150" width="150" /></div><div style=" display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column; -webkit-box-align: center; -ms-flex-align: center; align-items: center; font-size: 16px; font-weight: 600; line-height: 30px;"><div>د افغانستان اسلامي جمهوریت</div><div>د جمهوری ریاست د چارو ادارې لوی ریاست</div><div> {{{departmentHeader}}}</div></div></div><div style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; -webkit-box-pack: justify; -ms-flex-pack: justify; justify-content: space-between;"><div>ګنه/شماره&nbsp;:&nbsp; {{document.documentNo}}</div><div>نیټه/تاریخ&nbsp;:&nbsp; {{createdAt}}</div></div><div style="margin-top: 20px; display: flex; flex-direction: column; justify-content: space-between; align-content: space-between;"><table class="table-content"><thead><tr><th>پیشنهاد</th><th>احکام</th></tr></thead><tbody><tr><td style="padding: 10px;">{{{content}}}</td><td style="padding: 10px;">{{{hukumPeshnehad}}}</td></tr></tbody><table></div><div class="QR-code" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; -webkit-box-pack: end; -ms-flex-pack: end; justify-content: flex-end;"><div> <img src="{{serverApi}}/api/public/documents/{{documentId}}/genQRCode/150/150" height="150" width="150" /></div></div></body></html>',
1, 2, 3, 1);