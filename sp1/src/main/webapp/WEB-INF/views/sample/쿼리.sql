SELECT b.bno, title, content, writer, regDate, b.updatedate,  COUNT(r.rno) replyCnt 


FROM tbl_board  AS b 

LEFT  OUTER 

JOIN tbl_reply  AS r 
  ON b.bno = r.bno 


WHERE b.delflag =  FALSE 


GROUP  BY b.bno 


ORDER  BY b.bno  DESC; 
 


CREATE  TABLE tbl_product (
pno  INT  AUTO_INCREMENT  PRIMARY  KEY, -- 상품 번호(고유 식별자)                       
pname 

VARCHAR(200)  NOT  NULL, -- 상품명                                              
pdesc 

VARCHAR(1000)  NOT  NULL, -- 상품설명                                           
price 

INT  NOT  NULL, -- 상품가격                                                     
sale BOOLEAN 

DEFAULT  FALSE, -- 판매여부 (false)                                     
writer 

VARCHAR(100)  NOT  NULL, -- 상품 등록자                                        
regdate 

DATETIME  DEFAULT  CURRENT_TIMESTAMP, -- 상품 등록일                          
moddate 

DATETIME  DEFAULT  CURRENT_TIMESTAMP  ON 

UPDATE  CURRENT_TIMESTAMP -- 상품 수정
); 
 


CREATE  TABLE tbl_product_image (
ino  INT  AUTO_INCREMENT  PRIMARY  KEY, -- 이미지 번호(고유식별자)                      
pno 

INT  NOT  NULL, -- 상품번호 (외래키)                                              
filename 

VARCHAR(300)  NOT  NULL, -- 실제 파일명 또는 저장된 경로                     


UUID  CHAR(36)  NOT  NULL, -- 파일명 중복 방지를 위한 UUID                             


ORD  INT  DEFAULT 0, -- 이미지 정렬순서                                               
regdate 

DATETIME  DEFAULT  CURRENT_TIMESTAMP, -- 등록일                               


FOREIGN  KEY (pno)  REFERENCES tbl_product(pno)  ON 

DELETE  CASCADE 
); 
 


CREATE  INDEX idx_product_image_pno  ON tbl_product_image(pno,  ORD); 
 


SELECT * FROM tbl_product;
SELECT * FROM tbl_product_image;
 


SELECT p.pno, pname, pdesc, price, sale, writer, p.regdate, ino, UUID, filename, ORD
FROM tbl_product AS p LEFT OUTER JOIN tbl_product_image AS pimg
ON p.pno = pimg.pno
WHERE p.pno = 1;
