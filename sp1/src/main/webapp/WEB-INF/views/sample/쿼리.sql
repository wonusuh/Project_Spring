SELECT b.bno, title, content, writer, regDate, b.updatedate, COUNT(r.rno) replyCnt
FROM tbl_board AS b LEFT OUTER JOIN tbl_reply AS r
ON b.bno = r.bno
WHERE b.delflag = FALSE
GROUP BY b.bno
ORDER BY b.bno DESC;
