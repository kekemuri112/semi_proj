CREATE TABLE USERS
(
	users_num number(4) primary key,
	users_id varchar2(30) unique,
	users_pwd varchar2(30),
	users_name varchar2(40),
	users_email varchar2(40) unique,
	users_birth varchar2(15),
	users_phone varchar2(22)
);
CREATE SEQUENCE USERS_SEQ;

CREATE TABLE CAFE
(
	cafe_num number(4) primary key,
	cafe_name varchar2(30) unique,
	cafe_desc varchar2(50),
	cafe_intent varchar2(50),
	cafe_admin varchar2(20),
	cafe_approved varchar2(10),
	cafe_image varchar2(1024)
);
CREATE SEQUENCE CAFE_SEQ;

CREATE TABLE USERS_CAFE
( 
	users_cafe_num number(4) primary key,
	cafe_num number(4) references cafe(cafe_num),
	users_num number(4) references users(users_num),
	users_cafe_point number(10),
	users_cafe_approved varchar2(10)
);
CREATE SEQUENCE USERS_CAFE_SEQ;

CREATE TABLE notice 
(
	notice_num number(4) primary key,
	cafe_num number(4) references CAFE(cafe_num),
	notice_name varchar2(60),
	notice_lev number(10),
	notice_ref number(5),
	notice_step number(5)
);
CREATE SEQUENCE notice_SEQ;

CREATE TABLE CONTENTS
(
	contents_num number(4) primary key,
	notice_num number(4) references NOTICE(NOTICE_num),
	users_num number(4) references users(users_num),
	contents_title varchar2(80),
	contents_post varchar2(1024),
	contents_regdate date,
	contents_modifydate date
)
CREATE SEQUENCE CONTENTS_SEQ;

CREATE TABLE COMMENTS
(
	comments_num number(4) primary key,
	contents_num number(4) references content(content_num),
	users_num number(4) references users(users_num),
	comments_content varchar2(250),
	comments_lev number(5),
	comments_ref number(5),
	comments_step number(5)
);
CREATE SEQUENCE COMMENTS_SEQ;

CREATE TABLE CAFEREG
(
	CAFEREG_NUM NUMBER(4) PRIMARY KEY,
	CAFE_NUM NUMBER(4) REFERENCES CAFE(CAFE_NUM),
	CAFEREG_QUESTION VARCHAR2(150)
);
CREATE SEQUENCE CAFEREG_SEQ;

CREATE TABLE ANSWER
(
	CAFEREG_NUM NUMBER(4) REFERENCES CAFEREG(CAFEREG_NUM),
	USERS_CAFE_NUM NUMBER(4) REFERENCES USERS_CAFE(USERS_CAFE_NUM),
	ANSWER_CONTENTS VARCHAR2(150),
	CONSTRAINT PK_CAFEREG_NUM PRIMARY KEY(CAFEREG_NUM, USERS_CAFE_NUM)
);
