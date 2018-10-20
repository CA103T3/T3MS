## T3MS
### Usage
-  Setup Java, Eclispse, Tomcat (refer to "Eclipse import project from github-Unified Path.txt" on Google Drive)
-  Autoextend Oracle SYSTEM.DBF for ORA-01691: unable to extend lob segment T3MS.SYS_LOB0000039253C00005$$ by 1024 in tablespace SYSTEM
-  The path of SYSTEM.DBF is according to your ORACLE_HOME
```
alter database datafile 'C:\oraclexe\app\oracle\oradata\XE\SYSTEM.DBF' autoextend on next 100m maxsize 2000M;
```
-  SQL for database
-  Execute /T3MS/SerUtilServlet for importing data
-  Change Redis to be started manually
-  Redis redis.windows.conf
```
requirepass 123456
```
-  Execute Redis manually
```
redis-server.exe redis.windows.conf
```
-  Forestage
```
/T3MS/index.jsp
```
-  Backstage
```
/T3MS/backstage/staff/backstage_login.jsp
```
