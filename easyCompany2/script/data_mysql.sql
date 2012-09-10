insert into IDS (TABLE_NAME,NEXT_ID) values ('RTETNBBS',3);
insert into IDS (TABLE_NAME,NEXT_ID) values ('RTETNCART',3);
insert into IDS (TABLE_NAME,NEXT_ID) values ('RTETNCTGRY',6);
insert into IDS (TABLE_NAME,NEXT_ID) values ('RTETNGOODS',4);
insert into IDS (TABLE_NAME,NEXT_ID) values ('RTETNGOODSIMAGE',7);
insert into IDS (TABLE_NAME,NEXT_ID) values ('RTETNMBER',3);
insert into IDS (TABLE_NAME,NEXT_ID) values ('RTETNPURCHSLIST',3);

insert into RTETCCODE (CODE_ID,CODE_NM,DC) values ('CODE01','ROLE_ADMIN','관리자 코드');
insert into RTETCCODE (CODE_ID,CODE_NM,DC) values ('CODE02','ROLE_USER','사용자 코드');

insert into RTETNAUTH (URL,MNGR_SE) values ('\\A/dlv/selectAllListPurchase.do\\Z','CODE01');
insert into RTETNAUTH (URL,MNGR_SE) values ('\\A/gds/insertGoodsView.do\\Z','CODE01');
insert into RTETNAUTH (URL,MNGR_SE) values ('\\A/springrest/.*\\Z','CODE01');
insert into RTETNAUTH (URL,MNGR_SE) values ('\\A/brd/.*\\Z','CODE02');
insert into RTETNAUTH (URL,MNGR_SE) values ('\\A/dlv/.*\\Z','CODE02');
insert into RTETNAUTH (URL,MNGR_SE) values ('\\A/pcs/.*\\Z','CODE02');
insert into RTETNAUTH (URL,MNGR_SE) values ('\\A/mbr/updateMemberView.do\\Z','CODE02');

insert into RTETNDLVYINFO (DLVY_SE,DLVY_STTUS) values ('10','s.order');
insert into RTETNDLVYINFO (DLVY_SE,DLVY_STTUS) values ('11','s.deliver');
insert into RTETNDLVYINFO (DLVY_SE,DLVY_STTUS) values ('12','s.finish');

insert into RTETNCTGRY (CTGRY_ID,CTGRY_NM,DC) values ('CATEGORY-00000000000','미지정','미지정');
insert into RTETNCTGRY (CTGRY_ID,CTGRY_NM,DC) values ('CATEGORY-00000000001','토목/건축','경계석/판석/블록/벽돌/타일/스틸그레이팅/배수로자재/돌망태/건설장비/맨홀/고무바닥재');
insert into RTETNCTGRY (CTGRY_ID,CTGRY_NM,DC) values ('CATEGORY-00000000002','사무/교육','사무기기/가전제품/지류/사물함/인쇄물/필기구류/교육용품 및 교구/학생용책상 및 의자/어학 및 학습기자재');
insert into RTETNCTGRY (CTGRY_ID,CTGRY_NM,DC) values ('CATEGORY-00000000003','전자/정보/통신','개인용컴퓨터/노트북/프린터 및 스캐너/모니터 및 주변기기/전산 및 통신용품/보안용감시카메라');
insert into RTETNCTGRY (CTGRY_ID,CTGRY_NM,DC) values ('CATEGORY-00000000004','식물류','꽃묘/식물매트/덩쿨식물/관엽류/화목류/씨앗');
insert into RTETNCTGRY (CTGRY_ID,CTGRY_NM,DC) values ('CATEGORY-00000000005','철도/도로시설/기타','레일 및 침목/간판/도로분리대/난간/철도 및 도로용품/일반용역/철도차량/부속품/기타');

insert into RTETNGOODSIMAGE (GOODS_IMAGE_ID,FILE_NM) values ('IMAGE-0000000001.jpg','증명발급기.jpg');
insert into RTETNGOODSIMAGE (GOODS_IMAGE_ID,FILE_NM) values ('IMAGE-0000000002.jpg','증명발급기.jpg');
insert into RTETNGOODSIMAGE (GOODS_IMAGE_ID,FILE_NM) values ('IMAGE-0000000003.jpg','무선인식리더기.jpg');
insert into RTETNGOODSIMAGE (GOODS_IMAGE_ID,FILE_NM) values ('IMAGE-0000000004.jpg','무선인식리더기.jpg');
insert into RTETNGOODSIMAGE (GOODS_IMAGE_ID,FILE_NM) values ('IMAGE-0000000005.jpg','표지판.jpg');
insert into RTETNGOODSIMAGE (GOODS_IMAGE_ID,FILE_NM) values ('IMAGE-0000000006.jpg','표지판.jpg');

insert into RTETNGOODS (GOODS_ID,GOODS_NM,PRICE,CTGRY_ID,MAKR,DETAIL_INFO_IMAGE_ID,GOODS_IMAGE_ID,USE_AT) values ('GOODS-00000000000001','증명발급기',7800000,'CATEGORY-00000000002','증명test','IMAGE-0000000002.jpg','IMAGE-0000000001.jpg','1');
insert into RTETNGOODS (GOODS_ID,GOODS_NM,PRICE,CTGRY_ID,MAKR,DETAIL_INFO_IMAGE_ID,GOODS_IMAGE_ID,USE_AT) values ('GOODS-00000000000002','무선인식리더기',1760000,'CATEGORY-00000000003','디지털test','IMAGE-0000000004.jpg','IMAGE-0000000003.jpg','1');
insert into RTETNGOODS (GOODS_ID,GOODS_NM,PRICE,CTGRY_ID,MAKR,DETAIL_INFO_IMAGE_ID,GOODS_IMAGE_ID,USE_AT) values ('GOODS-00000000000003','도로표지판',3916200,'CATEGORY-00000000005','test 건설','IMAGE-0000000006.jpg','IMAGE-0000000005.jpg','1');

insert into RTETNMBER(MBER_NO,MBER_ID,NM,PASSWORD,EMAIL,TELNO,MBTLNUM,DETAIL_ADRES,LAST_CHANGE_DT,MNGR_SE,ZIP,ADRES) values ('MEMBER-0000000000001','admin','관리자','21232f297a57a5a743894a0e4a801fc3','admin@easyCompany2.com','070-777-7777','010-123-4567','','11/08/26','CODE01','134000','서울시 송파구 풍납동');
insert into RTETNMBER(MBER_NO,MBER_ID,NM,PASSWORD,EMAIL,TELNO,MBTLNUM,DETAIL_ADRES,LAST_CHANGE_DT,MNGR_SE,ZIP,ADRES) values ('MEMBER-0000000000002','user','일반사용자','ee11cbb19052e40b07aac0ca060c23ee','user@easyCompany2.com','02-413-0000','010-987-6543','','11/08/26','CODE02','111111','서울시 중구 무교동');

insert into RTETNCART (CART_ID,MBER_NO,GOODS_ID,QY) values ('CART-000000000000001','MEMBER-0000000000002','GOODS-00000000000003',5);
insert into RTETNCART (CART_ID,MBER_NO,GOODS_ID,QY) values ('CART-000000000000002','MEMBER-0000000000001','GOODS-00000000000002',198);

insert into RTETNPURCHSLIST (PURCHS_ID,GOODS_ID,MBER_NO,QY,PURCHS_DE,DLVY_SE) values ('PURCHS-0000000000001','GOODS-00000000000003','MEMBER-0000000000001',334,'20110923','11');
insert into RTETNPURCHSLIST (PURCHS_ID,GOODS_ID,MBER_NO,QY,PURCHS_DE,DLVY_SE) values ('PURCHS-0000000000002','GOODS-00000000000001','MEMBER-0000000000002',10,'20110924','10');

insert into RTETNBBS (NO,REGIST_DT,CN,SJ,MBER_NO,MBER_ID) values ('BOARD-00000000000001','2011-08-30','입찰구분: 공사  
입찰종류: 전자입찰  
공고번호: 20110901535  
입찰건명 및 품명: 도곡역 등 2개역 E S 스텝체인 교체공사  
입찰방법: 수의(소액)  
입찰서제출마감일시: 2011-09-07 10:00  
입찰일시: 2011-09-07 11:00  
','[입찰공고] 도곡역 등 2개역 스텝체인','MEMBER-0000000000002','user');

insert into RTETNBBS (NO,REGIST_DT,CN,SJ,MBER_NO,MBER_ID) values ('BOARD-00000000000002','2011-09-01','구분: [폐기물수집.운반] [폐기물처리업] [폐기물종합처리업] 
지역제한: 전국 
입찰(개찰)일시: 2011-09-22 11:00:00  
장소: 입찰집행담당 PC  
투찰마감일시: 2011-09-22 10:00:00  
참가신청마감일: 2011-09-21 20:00:00  
 
 
----------------------------------------------------- 
개찰관련사항: 취소 공고합니다. 
  
 
','[취소공고]2011년 폐기물 위탁처리','MEMBER-0000000000001','admin');

commit;