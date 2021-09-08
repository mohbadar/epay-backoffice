INSERT INTO public.doc_mng_document_type_template(id, header, template, footer, created_by, department_id, document_type_id, entity_id) VALUES 
(1, '<!DOCTYPE html><html><style>@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontBoldUri}}}"); font-weight: bold;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontLightUri}}}"); font-weight: lighter;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontRomanUri}}}"); font-weight: normal;}h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6, div, p{font-family: "Bahij Halvetica";}</style><head> <meta charset="UTF-8"> <meta name="viewport" content="width=device-width, initial-scale=1.0"> <meta http-equiv="X-UA-Compatible" content="ie=edge"></head><body dir="rtl" style="margin: 0; padding: 0;"> <div style="padding-bottom: 30px;"> <div class="header" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; -webkit-box-pack: justify; -ms-flex-pack: justify; justify-content: space-between;"> <div class="right-logo" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; width:58%; -webkit-box-pack: end; -ms-flex-pack: end; justify-content: flex-end;"> <img src="{{serverApi}}/api/public/download-image/1" height="150" width="150"/> </div><div style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column;"> <div>ګنه/شماره:{{document.documentNo}}</div><div>نیټه/تاریخ:{{documentDate}}</div></div></div><div style=" display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column; -webkit-box-align: center; -ms-flex-align: center; align-items: center; font-size: 20px; font-weight: 600; line-height: 30px;"> <div>{{document.title}}په اړه د{{document.issuingAuthority.nameDr}}</div><div style="margin-top: 30px; font-size: 40px;">حکم</div></div></div></body></html>',
'<!DOCTYPE html><html><style>@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontBoldUri}}}"); font-weight: bold;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontLightUri}}}"); font-weight: lighter;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontRomanUri}}}"); font-weight: normal;}h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6, div, p{font-family: "Bahij Halvetica";}</style><head> <meta charset="UTF-8"> <meta name="viewport" content="width=device-width, initial-scale=1.0"> <meta http-equiv="X-UA-Compatible" content="ie=edge"> <title>Document</title></head><body dir="rtl"> <div style="margin-top: 10px;">{{{content}}}</div></body></html>',
'<!DOCTYPE html><html><style>@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontBoldUri}}}"); font-weight: bold;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontLightUri}}}"); font-weight: lighter;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontRomanUri}}}"); font-weight: normal;}h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6, div, p{font-family: "Bahij Halvetica";}</style><head> <meta charset="UTF-8"> <meta name="viewport" content="width=device-width, initial-scale=1.0"> <meta http-equiv="X-UA-Compatible" content="ie=edge"></head><body dir="rtl" style="margin: 0; padding: 0;"> <div class="QR-code" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; -webkit-box-pack: end; -ms-flex-pack: end; justify-content: flex-end;"> <div> <img src="{{serverApi}}/api/public/documents/{{documentId}}/genQRCode/150/150" height="150" width="150"/> </div></div></div></body></html>',
1, 1, 1, 1),
(2, '<!DOCTYPE html><html><style>@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontBoldUri}}}"); font-weight: bold;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontLightUri}}}"); font-weight: lighter;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontRomanUri}}}"); font-weight: normal;}h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6, div, p{font-family: "Bahij Halvetica";}</style><head> <meta charset="UTF-8"> <meta name="viewport" content="width=device-width, initial-scale=1.0"> <meta http-equiv="X-UA-Compatible" content="ie=edge"></head><body dir="rtl" style="margin: 0; padding: 0;"> <div style="padding-bottom: 30px;"> <div class="header" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; -webkit-box-pack: justify; -ms-flex-pack: justify; justify-content: space-between;"> <div class="right-logo" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; width:58%; -webkit-box-pack: end; -ms-flex-pack: end; justify-content: flex-end;"> <img src="{{serverApi}}/api/public/download-image/1" height="150" width="150"/> </div><div style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column;"> <div>ګنه/شماره:{{document.documentNo}}</div><div>نیټه/تاریخ:{{documentDate}}</div></div></div><div style=" display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column; -webkit-box-align: center; -ms-flex-align: center; align-items: center; font-size: 20px; font-weight: 600; line-height: 30px;"> <div>{{document.title}}په اړه د{{document.issuingAuthority.nameDr}}</div><div style="margin-top: 30px; font-size: 40px;">فرمان</div></div></div></body></html>', 
'<!DOCTYPE html><html><style>@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontBoldUri}}}"); font-weight: bold;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontLightUri}}}"); font-weight: lighter;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontRomanUri}}}"); font-weight: normal;}h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6, div, p{font-family: "Bahij Halvetica";}</style><head> <meta charset="UTF-8"> <meta name="viewport" content="width=device-width, initial-scale=1.0"> <meta http-equiv="X-UA-Compatible" content="ie=edge"> <title>Document</title></head><body dir="rtl"> <div style="margin-top: 10px;">{{{content}}}</div></body></html>',
'<!DOCTYPE html><html><style>@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontBoldUri}}}"); font-weight: bold;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontLightUri}}}"); font-weight: lighter;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontRomanUri}}}"); font-weight: normal;}h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6, div, p{font-family: "Bahij Halvetica";}</style><head> <meta charset="UTF-8"> <meta name="viewport" content="width=device-width, initial-scale=1.0"> <meta http-equiv="X-UA-Compatible" content="ie=edge"></head><body dir="rtl" style="margin: 0; padding: 0;"> <div class="QR-code" style="display: -webkit-box; display: -ms-flexbox; display: flex;-webkit-box-orient: horizontal;-webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row;-webkit-box-pack: end; -ms-flex-pack: end; justify-content: flex-end;"> <div> <img src="{{serverApi}}/api/public/documents/{{documentId}}/genQRCode/150/150" height="150" width="150"/> </div></div></body></html>',
1, 1, 5, 1),
(3, '<!DOCTYPE html><html><style>@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontBoldUri}}}"); font-weight: bold;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontLightUri}}}"); font-weight: lighter;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontRomanUri}}}"); font-weight: normal;}h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6, div, p{font-family: "Bahij Halvetica"; padding: 2px; margin: 2px;}</style><head> <meta charset="UTF-8"> <meta name="viewport" content="width=device-width, initial-scale=1.0"> <meta http-equiv="X-UA-Compatible" content="ie=edge"></head><body dir="rtl" style="margin: 0; padding: 0;"> <div style="padding-bottom: 30px"> <div class="header" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column;"> <div class="title-text" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; -webkit-box-pack: justify; -ms-flex-pack: justify; justify-content: space-between; -webkit-box-align: center; -ms-flex-align: center; align-items: center;"> <div class="title-pashto" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column; -webkit-box-align: center; -ms-flex-align: center; align-items: center;"> <div>د افغانستان اسلامي جمهوري دولت</div><div>د جمهوري ریاست د چارو ادارې لوی ریاست</div></div><div class="title-logo" style=" display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column; -webkit-box-align: center; -ms-flex-align: center; align-items: center;"> <img src="{{serverApi}}/api/public/download-image/1" height="150" width="150"/> <div style="margin-top: 10px;"><strong>Islamic Republic of Afghanistan</strong></div><div>Administrative Office of the President</div><div class="department-header" style="line-height: 1 !important;">{{{departmentHeader}}}</div></div><div class="title-dari" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; flex-direction: column; -webkit-box-align: center; -ms-flex-align: center; align-items: center;"> <div>دولت جمهوری اسلامی افغانستان</div><div>ریاست عمومی اداره امور ریاست جمهوری</div></div></div></div></div></body></html>',
'<!DOCTYPE html><html><style>@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontBoldUri}}}"); font-weight: bold;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontLightUri}}}"); font-weight: lighter;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontRomanUri}}}"); font-weight: normal;}h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6, div, p{font-family: "Bahij Halvetica";}</style><head> <meta charset="UTF-8"> <meta name="viewport" content="width=device-width, initial-scale=1.0"> <meta http-equiv="X-UA-Compatible" content="ie=edge"> <title>Maktub</title></head><body dir="rtl"> <div style="margin-top: 10px;">{{{content}}}</div></body></html>',
'<!DOCTYPE html><html><style>@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontBoldUri}}}"); font-weight: bold;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontLightUri}}}"); font-weight: lighter;}@font-face{font-family: "Bahij Halvetica"; src: url("{{{fontRomanUri}}}"); font-weight: normal;}h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6, div, p{font-family: "Bahij Halvetica";}</style><head> <meta charset="UTF-8"> <meta name="viewport" content="width=device-width, initial-scale=1.0"> <meta http-equiv="X-UA-Compatible" content="ie=edge"></head><body dir="rtl" style="margin: 0; padding: 0;"> <div style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; -webkit-box-pack: justify; -ms-flex-pack: justify; justify-content: space-between; -webkit-box-align: center; -ms-flex-align: center; align-items: center;"> <div>آدرس: ریاست عمومی اداره امور ریاست ج.ا.ا، قصر مرمرین، کابل - افغانستان</div><div>Councils.Coordination@arg.gov.af</div><div>Tel: 0202143880 / 02022143882</div><div> <img src="{{serverApi}}/api/public/documents/{{documentId}}/genQRCode/150/150" height="150" width="150"/> </div></div></body></html>',
1, 1, 4, 1),
(4, '<!DOCTYPE html><html lang="en"><style>@font-face{font-family:"Bahij Halvetica";src:url("{{{fontBoldUri}}}");font-weight:bold}@font-face{font-family:"Bahij Halvetica";src:url("{{{fontLightUri}}}");font-weight:lighter}@font-face{font-family:"Bahij Halvetica";src:url("{{{fontRomanUri}}}");font-weight:normal}h1,h2,h3,h4,h5,h6,.h1,.h2,.h3,.h4,.h5,.h6,div,p{font-family:"Bahij Halvetica"}.table-content{position:relative;width:100%;table-layout:fixed !important;overflow-wrap:break-word !important;border:1px solid gray;border-collapse:collapse}.table-content td{border:1px solid grey;vertical-align:center;width:fit-content}.table-content th{border:1px solid grey;vertical-align:center}</style><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><meta http-equiv="X-UA-Compatible" content="ie=edge"><title>Document</title></head><body dir="rtl"><div class="header" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; -webkit-box-pack: justify; -ms-flex-pack: justify; justify-content: space-between;"><div class="right-logo"> <img src="{{serverApi}}/api/public/download-image/1" height="150" width="150" /></div><div><div class="text" style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: horizontal; -webkit-box-direction: normal; -ms-flex-direction: row; flex-direction: row; -webkit-box-pack: justify; -ms-flex-pack: justify; justify-content: center;"><div style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; padding: 10px; flex-direction: column;"><div>د افغانستان اسلامي جمهوري دولت</div><div>د احصائیي او معلوماتو ملی اداره</div></div><div style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; padding: 10px; -ms-flex-direction: column; flex-direction: column;"><div>دولت جمهوری اسلامی افغانستان</div><div>ادارۀ ملی احصائیه و معلومات</div></div></div><div style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; -webkit-box-align: center; -ms-flex-align: center; align-items: center; flex-direction: column;"><div>Islamic Republic of Afghanistan</div><div>National Statistics and Information Authority</div></div><div style="display: -webkit-box; display: -ms-flexbox; display: flex; -webkit-box-orient: vertical; -webkit-box-direction: normal; -ms-flex-direction: column; -webkit-box-align: center; -ms-flex-align: center; align-items: center; flex-direction: column;"><div>ریاست دفتر</div></div></div><div class="left-logo"> <img src="{{serverApi}}/api/public/download-image/2" height="150" width="150" /></div></div></body></html>',
'<!DOCTYPE html><html lang="en"><style>@font-face{font-family:"Bahij Halvetica";src:url("{{{fontBoldUri}}}");font-weight:bold}@font-face{font-family:"Bahij Halvetica";src:url("{{{fontLightUri}}}");font-weight:lighter}@font-face{font-family:"Bahij Halvetica";src:url("{{{fontRomanUri}}}");font-weight:normal}h1,h2,h3,h4,h5,h6,.h1,.h2,.h3,.h4,.h5,.h6,div,p{font-family:"Bahij Halvetica"}.table-content{position:relative;width:100%;table-layout:fixed !important;overflow-wrap:break-word !important;border:1px solid gray;border-collapse:collapse}.table-content td{border:1px solid grey;vertical-align:center;width:fit-content}.table-content th{border:1px solid grey;vertical-align:center}</style><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><meta http-equiv="X-UA-Compatible" content="ie=edge"><title>Document</title></head><body dir="rtl"><div style="margin-top: 20px; display: flex; flex-direction: column; justify-content: space-between; align-content: space-between;"><table class="table-content"><thead><tr><th>پیشنهاد</th><th>احکام</th></tr></thead><tbody><tr><td style="padding: 10px;">{{{content}}}</td><td></td></tr></tbody><table></div></body></html>',
'<!DOCTYPE html><html lang="en"><style>@font-face{font-family:"Bahij Halvetica";src:url("{{{fontBoldUri}}}");font-weight:bold}@font-face{font-family:"Bahij Halvetica";src:url("{{{fontLightUri}}}");font-weight:lighter}@font-face{font-family:"Bahij Halvetica";src:url("{{{fontRomanUri}}}");font-weight:normal}h1,h2,h3,h4,h5,h6,.h1,.h2,.h3,.h4,.h5,.h6,div,p{font-family:"Bahij Halvetica"}.table-content{position:relative;width:100%;table-layout:fixed !important;overflow-wrap:break-word !important;border:1px solid gray;border-collapse:collapse}.table-content td{border:1px solid grey;vertical-align:center;width:fit-content}.table-content th{border:1px solid grey;vertical-align:center}</style><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><meta http-equiv="X-UA-Compatible" content="ie=edge"><title>Document</title></head><body dir="rtl"> <footer><div class="QR-code"> <img src="{{serverApi}}/api/public/documents/{{documentId}}/genQRCode/150/150" height="150" width="150" /></div> </footer></body></html>',
10, 2, 3, 2039);