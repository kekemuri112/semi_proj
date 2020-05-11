CREATE TABLE USERS
(
	users_num number(4) primary key,
	users_id varchar2(30) unique,
	users_pwd varchar2(30),
	users_name varchar2(12),
	users_email varchar2(40) unique,
	users_birth date,
	users_phone varchar2(22)
);
CREATE SEQUENCE USERS_SEQ;

CREATE TABLE CAFE
(
	cafe_num number(4) primary key,
	cafe_name varchar2(30) unique,
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
	notice_name varchar2(30),
	notice_lev number(10),
	notice_ref number(5),
	notice_step number(5)
);
CREATE SEQUENCE BOARD_LIST_SEQ;

CREATE TABLE CONTENT
(
	content_num number(4) primary key,
	notice_num number(4) references NOTICE(NOTICE_num),
	users_num number(4) references users(users_num),
	content_title varchar2(30),
	content_post varchar2(1024),
	content_regdate date,
	contents_modifydate date
)
CREATE SEQUENCE CONTENT_SEQ;

CREATE TABLE COMMENTS
(
	comment_num number(4) primary key,
	content_num number(4) references content(content_num),
	users_num number(4) references users(users_num),
	comment_content varchar2(100),
	comment_lev number(5),
	comment_ref number(5),
	comment_step number(5)
)
       CREATE SEQUENCE COMMENT_SEQ;

CREATE TABLE CAFEREG
(
	CAFEREG_NUM NUMBER(4) PRIMARY KEY,
	CAFE_NUM NUMBER(4) REFERENCES CAFE(CAFE_NUM),
	CAFEREG_QUESTION VARCHAR2(50)
);
CREATE SEQUENCE CAFEREG_SEQ;

CREATE TABLE ANSWER
(
	CAFEREG_NUM NUMBER(4) REFERENCES CAFEREG(CAFEREG_NUM),
	USERS_CAFE_NUM NUMBER(4) REFERENCES USERS_CAFE(USERS_CAFE_NUM),
	ANSWER_CONTENTS VARCHAR2(50),
	CONSTRAINT PK_CAFEREG_NUM PRIMARY KEY(CAFEREG_NUM, USERS_CAFE_NUM)
);

ぞしぞしぞしし