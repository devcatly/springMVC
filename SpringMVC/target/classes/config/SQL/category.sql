ALTER TABLE Category
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

DROP INDEX PK_Category; 

/* 카테고리 */
DROP TABLE Category 
	CASCADE CONSTRAINTS;

/* 카테고리 */
CREATE TABLE Category (
	cg_num Number(8) NOT NULL, /* 카테고리번호 */
	cg_code Varchar2(20) NOT NULL, /* 카테고리코드 */
	cg_name VARCHAR2(50) NOT NULL /* 카테고리명 */
);

drop sequence Category_seq;

create sequence category_seq nocache;
