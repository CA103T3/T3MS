


insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'檢舉管理','#',null,1);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'影評留言管理','/T3MS/backstage/reportMsg/reportMsg_list.jsp','N001',2);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'會員帳號管理','#',null,3);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'影評資格審核','/T3MS/backstage/member/becomeFilmCriticism.jsp','N003',4);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'查詢會員帳號','/T3MS/backstage/member/listAllMember.jsp','N003',5);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'後台帳號管理','#',null,6);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'新增帳號','/T3MS/backstage/staff/staff_insert.jsp','N006',7);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'修改帳號','/T3MS/backstage/staff/staff_update.jsp','N006',8);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'刪除帳號','/T3MS/backstage/staff/staff_delete.jsp','N006',9);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'後台角色管理','#',null,10);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'新增角色/權限','/T3MS/backstage/role/role_insert.jsp','N010',11);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'修改角色/權限','/T3MS/backstage/role/role_update.jsp','N010',12);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'刪除角色/權限','/T3MS/backstage/role/role_delete.jsp','N010',13);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'電影院管理','#',null,14);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'影城','/T3MS/backstage/cinema/listAllCinema.jsp','N014',15);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'影廳','/T3MS/backstage/theater/listAllTheater.jsp','N014',16);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'場次','/T3MS/backstage/session/listAllSession.jsp','N014',17);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'票種/票價','/T3MS/backstage/ticketType/listAllTicketType.jsp','N014',18);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'電影資訊管理','#',null,19);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'電影管理','/T3MS/backstage/movie/movie_List.jsp','N019',20);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'票房旁行榜管理','#','N019',21);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'電影影評管理','/T3MS/backstage/filmreview/fv_b.jsp','N019',22);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'電影情報管理','#','N019',23);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'訂票系統管理','#',null,24);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'查詢場次訂票狀況','#','N024',25);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'查詢退票紀錄','#','N024',26);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'活動公告管理','#',null,27);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'活動公告','/T3MS/backstage/announcement/listAnnouncement.jsp','N027',28);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'公告管理','#','N027',29);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'客服系統管理','#',null,30);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'即時客服聊天室','#','N030',31);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'歷史訊息','#','N030',32);




