


insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'檢舉管理','#',null,1);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'影評留言管理','/T3MS/backstage/reportMsg/reportMsg_list.jsp','N001',2);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'會員帳號管理','#',null,3);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'影評資格審核','/T3MS/backstage/member/becomeFilmCriticism.jsp','N003',4);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'查詢會員帳號','/T3MS/backstage/member/listAllMember.jsp','N003',5);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'後台帳號管理','#',null,6);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'新增帳號','/T3MS/backstage/staff/backstage_insert.jsp','N006',7);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'修改帳號','/T3MS/backstage/staff/backstage_update.jsp','N006',8);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'刪除帳號','/T3MS/backstage/staff/backstage_delete.jsp','N006',9);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'後臺帳號列表','/T3MS/backstage/staff/backstage_listAll.jsp','N006',10);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'後台角色管理','#',null,11);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'新增角色/權限','/T3MS/backstage/role/role_insert.jsp','N011',12);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'修改角色/權限','/T3MS/backstage/role/role_update.jsp','N011',13);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'刪除角色/權限','/T3MS/backstage/role/role_delete.jsp','N011',14);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'電影院管理','#',null,15);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'影城','/T3MS/backstage/cinema/listAllCinema.jsp','N015',16);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'影廳','/T3MS/backstage/theater/listAllTheater.jsp','N015',17);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'場次','/T3MS/backstage/session/listAllSession.jsp','N015',18);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'票種/票價','/T3MS/backstage/ticketType/listAllTicketType.jsp','N015',19);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'電影資訊管理','#',null,20);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'電影管理','/T3MS/backstage/movie/movie_List.jsp','N020',21);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'票房排行榜管理','#','N020',22);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'電影影評管理','/T3MS/backstage/filmreview/fv_b.jsp','N020',23);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'電影情報管理','/T3MS/backstage/movie_introduce/introduce_List1.jsp','N020',24);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'訂票系統管理','#',null,25);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'查詢場次訂票狀況','#','N025',26);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'查詢退票紀錄','#','N025',27);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'活動公告管理','#',null,28);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'活動公告','/T3MS/backstage/activity/listActivity.jsp','N028',29);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'公告管理','/T3MS/backstage/announcement/listAnnouncement.jsp','N028',30);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'客服系統管理','#',null,31);
insert into NAV_BACKSTAGE(listitem_no,listitem_name,url,parent_id,item_order) values('N'||LPAD(to_char(NAV_BACKSTAGE_seq.NEXTVAL), 3, '0'),'即時客服聊天室','/T3MS/backstage/service_BackChat/chatServer.jsp','N031',32);






