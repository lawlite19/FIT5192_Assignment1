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
	VALUES (240, '[John Goerzen]', '7121044951-2152386', '《Python网络编程基础》可以作为各层次Python、Web和网络程序的开发人员的参考书，在实际工作中使用书中的技术，效果更佳。', '7121044951', 'https://api.douban.com/v2/book/2152386', '电子工业出版社', 'https://img3.doubanio.com/mpic/s2604186.jpg', 'Python网络编程基础', 'General');
INSERT INTO FIT5192.T_BOOK (ID, AUTHOR, CALLNUMBER, DESCRIPTION, ISBN, PREVIEWURL, PUBLISHER, THUMBNAIL, TITLE, "TYPE") 
	VALUES (242, '[craig.Richardson]', '711539248X-26607568', '这是一本非常有趣的Python入门学习书，它用一个个冒险串联起Python基础知识的各个环节，跟随本书的每一个冒险进行阅读和实践，就如同玩游戏一样通关，你便学会了Python的相关技能，包括Python的安装、创建第一个程序、学习if声明和while循环的使用方法、使用Turtle图形函数画图、创建按钮和文本框、设计第一个游戏、添加更多的动画和音乐效果等。本书适合Python的初学者阅读，书后的附录和专业术语对照表也将帮助你更好地完成随时的查阅和学习。', '711539248X', 'https://api.douban.com/v2/book/26607568', '', 'https://img3.doubanio.com/mpic/s28294041.jpg', '零基础学python图文版', 'General');
INSERT INTO FIT5192.T_BOOK (ID, AUTHOR, CALLNUMBER, DESCRIPTION, ISBN, PREVIEWURL, PUBLISHER, THUMBNAIL, TITLE, "TYPE") 
	VALUES (243, '[编者:夏敏捷//杨关]', '7302472556-27101397', '', '7302472556', 'https://api.douban.com/v2/book/27101397', '清华大学', 'https://img1.doubanio.com/f/shire/5522dd1f5b742d1e1394a17f44d590646b63871d/pics/book-default-medium.gif', 'Python程序设计--从基础到开发(21世纪高等学校计算机应用技术规划教材)', 'Reserve');
INSERT INTO FIT5192.T_BOOK (ID, AUTHOR, CALLNUMBER, DESCRIPTION, ISBN, PREVIEWURL, PUBLISHER, THUMBNAIL, TITLE, "TYPE") 
	VALUES (244, '[Magnus Lie Hetland]', '7115230277-4866934', '本书是经典教程的全新改版，作者根据Python 3.0版本的种种变化，全面改写了书中内容，做到既能“瞻前”也能“顾后”。本书层次鲜明、结构严谨、内容翔实，特别是在最后几章，作者将前面讲述的内容应用到了10个引人入胜的项目中，并以模板的形式介绍了项目的开发过程。本书既适合初学者夯实基础，又能帮助Python程序员提升技能，即使是 Python方面的技术专家，也能从书里找到令你耳目一新的东西。', '7115230277', 'https://api.douban.com/v2/book/4866934', '人民邮电出版社', 'https://img3.doubanio.com/mpic/s4387251.jpg', 'Python基础教程', 'General');
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
