DROP USER david;
CREATE USER david IDENTIFIED BY 123456;
GRANT CONNECT, RESOURCE, DBA, EXP_FULL_DATABASE, IMP_FULL_DATABASE TO david;
GRANT UNLIMITED TABLESPACE TO david;
SELECT USERNAME FROM ALL_USERS ORDER BY USERNAME;
exit
