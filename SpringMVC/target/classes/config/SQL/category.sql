ALTER TABLE Category
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

DROP INDEX PK_Category; 

/* ī�װ� */
DROP TABLE Category 
	CASCADE CONSTRAINTS;

/* ī�װ� */
CREATE TABLE Category (
	cg_num Number(8) NOT NULL, /* ī�װ���ȣ */
	cg_code Varchar2(20) NOT NULL, /* ī�װ��ڵ� */
	cg_name VARCHAR2(50) NOT NULL /* ī�װ��� */
);

drop sequence Category_seq;

create sequence category_seq nocache;
