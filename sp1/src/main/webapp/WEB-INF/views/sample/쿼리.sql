SELECT b.bno, title, content, writer, regDate, b.updatedate, COUNT(r.rno) replyCnt
FROM tbl_board AS b LEFT OUTER JOIN tbl_reply AS r ON b.bno = r.bno
WHERE b.delflag = FALSE
GROUP  BY b.bno
ORDER BY b.bno DESC;

SELECT * FROM tbl_product;
SELECT * FROM tbl_product_image;

SELECT p.pno, pname, pdesc, price, sale, writer, p.regdate, ino, UUID, filename, ORD
FROM tbl_product AS p LEFT OUTER JOIN tbl_product_image AS pimg ON p.pno = pimg.pno
WHERE p.pno = 1;

SELECT p.pno, pname, pdesc, price, sale, writer, p.regdate, ino, UUID, filename, ORD
FROM tbl_product AS p LEFT OUTER JOIN tbl_product_image AS pimg ON p.pno = pimg.pno
WHERE pimg.ord = 0
ORDER BY p.pno DESC
LIMIT 10 OFFSET 0;