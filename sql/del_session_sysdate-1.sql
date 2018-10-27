-- select count(*) from movie_session where session_time < to_timestamp('2018-10-15', 'YYYY-MM-DD');
select count(*) from movie_session where session_time < sysdate-1;
delete from movie_session where session_time < sysdate-1;
commit;
-- exit
