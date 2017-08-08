CREATE DATABASE id_28198972;

CREATE TABLE T_USER (ID INTEGER NOT NULL, ADDRESS VARCHAR(255), EMAIL VARCHAR(255), FIRSTNAME VARCHAR(255), LASTNAME VARCHAR(255), PASSWORD VARCHAR(255), PHONENUMBER VARCHAR(255), "TYPE" VARCHAR(255), PRIMARY KEY (ID));

CREATE TABLE T_BOOK (ID INTEGER NOT NULL, AUTHOR VARCHAR(255), CALLNUMBER VARCHAR(255), DESCRIPTION VARCHAR(255), ISBN VARCHAR(255), PREVIEWURL VARCHAR(255), PUBLISHER VARCHAR(255), THUMBNAIL VARCHAR(255), TITLE VARCHAR(255), "TYPE" VARCHAR(255), PRIMARY KEY (ID));

CREATE TABLE T_LOAN (ID INTEGER NOT NULL, DUEDATE TIMESTAMP, STARTDATE TIMESTAMP, BOOKID_ID INTEGER, MEMBERID_ID INTEGER, PRIMARY KEY (ID));


INSERT INTO FIT5192.T_USER (ID, ADDRESS, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, PHONENUMBER, "TYPE") 
	VALUES (237, 'ROOM 301,ZIJINHUAYUAN', 'ywan0005@student.monash.edu', 'Wang', 'Yongzhi', '4297f44b13955235245b2497399d7a93', '0123456789', 'Librarian');
INSERT INTO FIT5192.T_USER (ID, ADDRESS, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, PHONENUMBER, "TYPE") 
	VALUES (238, 'Suzhou, Wenhui Square', 'luokai@student.monash.edu', 'Luo', 'Kai', '4297f44b13955235245b2497399d7a93', '0123456789', 'Member');
INSERT INTO FIT5192.T_USER (ID, ADDRESS, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, PHONENUMBER, "TYPE") 
	VALUES (241, 'Jiang su,Xuzhou', '849451478@qq.com', 'Zhao', 'Si', 'e10adc3949ba59abbe56e057f20f883e', '0123456789', 'Member');
INSERT INTO FIT5192.T_USER (ID, ADDRESS, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, PHONENUMBER, "TYPE") 
	VALUES (350, 'ROOM 301,ZIJINHUAYUAN', '625640156@qq.com', 'Wang', 'San', '4297f44b13955235245b2497399d7a93', '91234567', 'Member');

INSERT INTO FIT5192.T_BOOK (ID, AUTHOR, CALLNUMBER, DESCRIPTION, ISBN, PREVIEWURL, PUBLISHER, THUMBNAIL, TITLE, "TYPE") 
	VALUES (240, '[John Goerzen]', '7121044951-2152386', '��Python�����̻�����������Ϊ�����Python��Web���������Ŀ�����Ա�Ĳο��飬��ʵ�ʹ�����ʹ�����еļ�����Ч�����ѡ�', '7121044951', 'https://api.douban.com/v2/book/2152386', '���ӹ�ҵ������', 'https://img3.doubanio.com/mpic/s2604186.jpg', 'Python�����̻���', 'General');
INSERT INTO FIT5192.T_BOOK (ID, AUTHOR, CALLNUMBER, DESCRIPTION, ISBN, PREVIEWURL, PUBLISHER, THUMBNAIL, TITLE, "TYPE") 
	VALUES (242, '[craig.Richardson]', '711539248X-26607568', '����һ���ǳ���Ȥ��Python����ѧϰ�飬����һ����ð�մ�����Python����֪ʶ�ĸ������ڣ����汾���ÿһ��ð�ս����Ķ���ʵ��������ͬ����Ϸһ��ͨ�أ����ѧ����Python����ؼ��ܣ�����Python�İ�װ��������һ������ѧϰif������whileѭ����ʹ�÷�����ʹ��Turtleͼ�κ�����ͼ��������ť���ı�����Ƶ�һ����Ϸ����Ӹ���Ķ���������Ч���ȡ������ʺ�Python�ĳ�ѧ���Ķ������ĸ�¼��רҵ������ձ�Ҳ����������õ������ʱ�Ĳ��ĺ�ѧϰ��', '711539248X', 'https://api.douban.com/v2/book/26607568', '', 'https://img3.doubanio.com/mpic/s28294041.jpg', '�����ѧpythonͼ�İ�', 'General');
INSERT INTO FIT5192.T_BOOK (ID, AUTHOR, CALLNUMBER, DESCRIPTION, ISBN, PREVIEWURL, PUBLISHER, THUMBNAIL, TITLE, "TYPE") 
	VALUES (243, '[����:������//���]', '7302472556-27101397', '', '7302472556', 'https://api.douban.com/v2/book/27101397', '�廪��ѧ', 'https://img1.doubanio.com/f/shire/5522dd1f5b742d1e1394a17f44d590646b63871d/pics/book-default-medium.gif', 'Python�������--�ӻ���������(21���͸ߵ�ѧУ�����Ӧ�ü����滮�̲�)', 'Reserve');
INSERT INTO FIT5192.T_BOOK (ID, AUTHOR, CALLNUMBER, DESCRIPTION, ISBN, PREVIEWURL, PUBLISHER, THUMBNAIL, TITLE, "TYPE") 
	VALUES (244, '[Magnus Lie Hetland]', '7115230277-4866934', '�����Ǿ���̵̳�ȫ�¸İ棬���߸���Python 3.0�汾�����ֱ仯��ȫ���д���������ݣ��������ܡ�հǰ��Ҳ�ܡ��˺󡱡��������������ṹ�Ͻ���������ʵ���ر���������£����߽�ǰ�潲��������Ӧ�õ���10��������ʤ����Ŀ�У�����ģ�����ʽ��������Ŀ�Ŀ������̡�������ʺϳ�ѧ�ߺ�ʵ���������ܰ���Python����Ա�������ܣ���ʹ�� Python����ļ���ר�ң�Ҳ�ܴ������ҵ������Ŀһ�µĶ�����', '7115230277', 'https://api.douban.com/v2/book/4866934', '�����ʵ������', 'https://img3.doubanio.com/mpic/s4387251.jpg', 'Python�����̳�', 'General');
INSERT INTO FIT5192.T_BOOK (ID, AUTHOR, CALLNUMBER, DESCRIPTION, ISBN, PREVIEWURL, PUBLISHER, THUMBNAIL, TITLE, "TYPE") 
	VALUES (245, '[Josh Patterson, Adam Gibson]', '1491914254-26379661', '', '1491914254', 'https://api.douban.com/v2/book/26379661', 'O''Reilly Media', 'https://img3.doubanio.com/mpic/s28061930.jpg', 'Deep Learning', 'General');
INSERT INTO FIT5192.T_BOOK (ID, AUTHOR, CALLNUMBER, DESCRIPTION, ISBN, PREVIEWURL, PUBLISHER, THUMBNAIL, TITLE, "TYPE") 
	VALUES (247, '[J. R. Parker]', '1944534652-26917066', '', '1944534652', 'https://api.douban.com/v2/book/26917066', 'Mercury Learning & Information', 'https://img3.doubanio.com/mpic/s29157682.jpg', 'Python', 'Reserve');
INSERT INTO FIT5192.T_BOOK (ID, AUTHOR, CALLNUMBER, DESCRIPTION, ISBN, PREVIEWURL, PUBLISHER, THUMBNAIL, TITLE, "TYPE") 
	VALUES (249, '[Nikhil Buduma]', '1491925612-26425877', '', '1491925612', 'https://api.douban.com/v2/book/26425877', 'O''Reilly Media', 'https://img3.doubanio.com/mpic/s29474106.jpg', 'Fundamentals of Deep Learning', 'General');
INSERT INTO FIT5192.T_BOOK (ID, AUTHOR, CALLNUMBER, DESCRIPTION, ISBN, PREVIEWURL, PUBLISHER, THUMBNAIL, TITLE, "TYPE") 
	VALUES (250, '[Phuong Vo.T.H, Martin Czygan, Ashish Kumar]', '1788290097-27036778', '', '1788290097', 'https://api.douban.com/v2/book/27036778', 'Packt Publishing', 'https://img1.doubanio.com/mpic/s29442528.jpg', 'Python', 'General');

INSERT INTO FIT5192.T_LOAN (ID, DUEDATE, STARTDATE, BOOKID_ID, MEMBERID_ID) 
	VALUES (342, NULL, '2017-08-08 20:32:33.274', 243, 238);
INSERT INTO FIT5192.T_LOAN (ID, DUEDATE, STARTDATE, BOOKID_ID, MEMBERID_ID) 
	VALUES (344, NULL, '2017-08-08 21:01:12.86', 247, 350);
