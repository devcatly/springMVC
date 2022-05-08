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

/* 상품 */
DROP TABLE Product 
	CASCADE CONSTRAINTS;

/* 상품 */
CREATE TABLE Product (
	pnum Number(8) NOT NULL, /* 상품번호 */
	pname VARCHAR2(100) NOT NULL, /* 상품명 */
	pcompany VARCHAR2(50) NOT NULL, /* 제조사 */
	pimage1 VARCHAR2(200), /* 상품이미지1 */
	pimage2 VARCHAR2(200), /* 상품이미지2 */
	pimage3 VARCHAR2(200), /* 상품이미지3 */
	pqty NUMBER(8), /* 수량 */
	price NUMBER(8) NOT NULL, /* 상품정가 */
	saleprice NUMBER(8) NOT NULL, /* 상품판매가 */
	pspec VARCHAR2(20), /* 상품스펙 */
	pcontents VARCHAR2(1000), /* 상품설명 */
	point NUMBER(8), /* 포인트 */
	pindate DATE, /* 상품입고일 */
	cg_num Number(8) /* 카테고리번호 */
);

COMMENT ON TABLE Product IS '상품';

COMMENT ON COLUMN Product.pnum IS '상품번호';

COMMENT ON COLUMN Product.pname IS '상품명';

COMMENT ON COLUMN Product.pcompany IS '제조사';

COMMENT ON COLUMN Product.pimage1 IS '상품이미지1';

COMMENT ON COLUMN Product.pimage2 IS '상품이미지2';

COMMENT ON COLUMN Product.pimage3 IS '상품이미지3';

COMMENT ON COLUMN Product.pqty IS '수량';

COMMENT ON COLUMN Product.price IS '상품정가';

COMMENT ON COLUMN Product.saleprice IS '상품판매가';

COMMENT ON COLUMN Product.pspec IS '상품스펙';

COMMENT ON COLUMN Product.pcontents IS '상품설명';

COMMENT ON COLUMN Product.point IS '포인트';

COMMENT ON COLUMN Product.pindate IS '상품입고일';

COMMENT ON COLUMN Product.cg_num IS '카테고리번호';

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