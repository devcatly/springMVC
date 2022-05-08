ALTER TABLE Product
	DROP
		CONSTRAINT FK_Category_TO_Product
		CASCADE;

ALTER TABLE Product
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

DROP INDEX PK_Product;

/* ��ǰ */
DROP TABLE Product 
	CASCADE CONSTRAINTS;

/* ��ǰ */
CREATE TABLE Product (
	pnum Number(8) NOT NULL, /* ��ǰ��ȣ */
	pname VARCHAR2(100) NOT NULL, /* ��ǰ�� */
	pcompany VARCHAR2(50) NOT NULL, /* ������ */
	pimage1 VARCHAR2(200), /* ��ǰ�̹���1 */
	pimage2 VARCHAR2(200), /* ��ǰ�̹���2 */
	pimage3 VARCHAR2(200), /* ��ǰ�̹���3 */
	pqty NUMBER(8), /* ���� */
	price NUMBER(8) NOT NULL, /* ��ǰ���� */
	saleprice NUMBER(8) NOT NULL, /* ��ǰ�ǸŰ� */
	pspec VARCHAR2(20), /* ��ǰ���� */
	pcontents VARCHAR2(1000), /* ��ǰ���� */
	point NUMBER(8), /* ����Ʈ */
	pindate DATE, /* ��ǰ�԰��� */
	cg_num Number(8) /* ī�װ���ȣ */
);

COMMENT ON TABLE Product IS '��ǰ';

COMMENT ON COLUMN Product.pnum IS '��ǰ��ȣ';

COMMENT ON COLUMN Product.pname IS '��ǰ��';

COMMENT ON COLUMN Product.pcompany IS '������';

COMMENT ON COLUMN Product.pimage1 IS '��ǰ�̹���1';

COMMENT ON COLUMN Product.pimage2 IS '��ǰ�̹���2';

COMMENT ON COLUMN Product.pimage3 IS '��ǰ�̹���3';

COMMENT ON COLUMN Product.pqty IS '����';

COMMENT ON COLUMN Product.price IS '��ǰ����';

COMMENT ON COLUMN Product.saleprice IS '��ǰ�ǸŰ�';

COMMENT ON COLUMN Product.pspec IS '��ǰ����';

COMMENT ON COLUMN Product.pcontents IS '��ǰ����';

COMMENT ON COLUMN Product.point IS '����Ʈ';

COMMENT ON COLUMN Product.pindate IS '��ǰ�԰���';

COMMENT ON COLUMN Product.cg_num IS 'ī�װ���ȣ';

CREATE UNIQUE INDEX PK_Product
	ON Product (
		pnum ASC
	);

ALTER TABLE Product
	ADD
		CONSTRAINT PK_Product
		PRIMARY KEY (
			pnum
		);

ALTER TABLE Product
	ADD
		CONSTRAINT FK_Category_TO_Product
		FOREIGN KEY (
			cg_num
		)
		REFERENCES Category (
			cg_num
		);