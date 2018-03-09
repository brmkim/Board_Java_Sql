
DROP TABLE QABOARD;
DROP SEQUENCE QA_SEQ;
CREATE TABLE QABOARD(  -- �Խ��� ����
    NO NUMBER NOT NULL,
    TITLE VARCHAR2(200) NOT NULL,
    CONTENT VARCHAR2(2000),
    WRITER VARCHAR2(100) NOT NULL,
    WRITEDATE DATE DEFAULT SYSDATE NOT NULL,
    GRP NUMBER,  -- �� �Խñ۰� �� �亯���� �� �׷��̸� �θ�-�ڽ� ���谡 �ȴ�.
    SEQ NUMBER,  -- ���� �׷� �� �亯�� ���� 
    LVL NUMBER   -- ���� �׷� �� ���� 
);

-- �Խñ� ������ ����
CREATE SEQUENCE QA_SEQ 
    START WITH 1
    INCREMENT BY 1;  
    
-- NO�� PRIMARY KEY�� ����� ���� �ε��� ������ �ؾ��Ѵ�.
-- REVERSE�� �ؼ� �� ���� ���߹�ȣ�� ���� �Ѵ�.
CREATE INDEX IDX_QABOARD_NO ON QABOARD(NO) REVERSE;

 -- ���� NO�� PRIMARY KEY�� �����.
ALTER TABLE QABOARD ADD CONSTRAINT QABOARD_NO_PK PRIMARY KEY(NO); 

-- GRP, SEQ�� �ε��� ����. GRP�� ��������, SEQ�� ������������.
CREATE INDEX IDX_QABOARD_GRP_SEQ ON QABOARD(GRP DESC, SEQ ASC); 

-- NLS_DATE_FORMAT�� �⵵-��-�� 24�ð�:��:�� ���·� ����
ALTER SESSION SET NLS_DATE_FORMAT = 'YYYY-MM-DD HH24:MI:SS';

----- ���� �Խñ� �Է� -----

-- ���� ���� �Խñ۵� --
INSERT INTO QABOARD 
    VALUES(QA_SEQ.NEXTVAL, '��ǰ�� ���� ������ �ֽ��ϴ�.', '���� ���� ����', '��ö��', TO_DATE('2018-01-02', 'YYYY-MM-DD'), 
    QA_SEQ.CURRVAL, 1, 0);  -- GRP ����� QA_SEQ ����̰� SEQ�� 1�̸� LVL�� 0�̴�. LVL�� �е���̴�.

INSERT INTO QABOARD 
    VALUES(QA_SEQ.NEXTVAL, '������ ����', '�� �������� �ְ� ������ ���ΰ���?', '�̿���', TO_DATE('2018-02-13', 'YYYY-MM-DD'), 
    QA_SEQ.CURRVAL, 1, 0);  
    
INSERT INTO QABOARD 
    VALUES(QA_SEQ.NEXTVAL, '���� ������ �ȵǿ�', '�� ��ǰ�� �����ؼ� �ٿ�ε带 �ߴµ� ������ �ȵ˴ϴ�', '�ڱ浿', 
    TO_DATE('2018-02-20', 'YYYY-MM-DD'), QA_SEQ.CURRVAL, 1, 0);    

-- ���� �亯 �Խñ� --
  -- ù��° �����ۿ� ���1
INSERT INTO QABOARD VALUES (QA_SEQ.NEXTVAL, '������ ���� �亯�Դϴ�.', '�亯 �亯 �亯', '��亯', SYSDATE, 2, 2, 1);
  -- ù��° �����ۿ� ��� 2
INSERT INTO QABOARD VALUES (QA_SEQ.NEXTVAL, '������ ���� �� �ٸ� �亯�Դϴ�.', '�亯2 �亯2 �亯2', '��亯', SYSDATE, 2, 2, 2);  
  -- ù��° �����ۿ� ��� 3  
INSERT INTO QABOARD VALUES (QA_SEQ.NEXTVAL, '�� �ٸ� �亯', '�亯3 �亯3 �亯3', '��亯', SYSDATE, 2, 2, 3); 


    -- �ι�° �����ۿ� ���
UPDATE QABOARD SET SEQ = SEQ + 1 WHERE GRP = 1 AND SEQ > 2;
INSERT INTO QABOARD VALUES (QA_SEQ.NEXTVAL, '�� �������� �ְ� ������ 99�Դϴ�', NULL, '�̴亯', SYSDATE, 1, 2, 1);

-- ����° �����ۿ� ���
--UPDATE QABOARD SET SEQ = SEQ + 1 WHERE GRP = 4 AND SEQ > 2;
INSERT INTO QABOARD VALUES (QA_SEQ.NEXTVAL, '�ٿ�ε� �� �� �����', '�� ����� �����غ��ñ� �ٶ��ϴ�', '�ڴ亯', SYSDATE, 4, 2, 1);

--DELETE FROM QABOARD WHERE NO = 7;

-- �Խ��� ����
SELECT * FROM QABOARD ORDER BY GRP, SEQ ASC;

-- ����, ��� ���������� ���� (������)
SELECT CASE WHEN (LVL = 0 OR LVL > 0) THEN NO END 
    NO, GRP, LVL, RPAD('+', LVL, '-') || TITLE "TITLE", WRITER, WRITEDATE 
    FROM QABOARD 
    GROUP BY NO, GRP, LVL, TITLE, WRITER, WRITEDATE 
    ORDER BY GRP;
    
-- ����, ��� ���������� ���� (�ڼ���)
SELECT CASE WHEN (LVL = 0 OR LVL > 0) THEN NO END 
    NO, GRP, SEQ, LVL,RPAD('+', LVL, '-') || TITLE "TITLE", CONTENT, WRITER, WRITEDATE 
    FROM QABOARD 
    GROUP BY NO, GRP, SEQ, LVL, TITLE, CONTENT, WRITER, WRITEDATE 
    ORDER BY GRP;    
    
SELECT content from qaboard where no = 2;    
COMMIT;   

INSERT INTO QABOARD 
    VALUES(QA_SEQ.NEXTVAL, '������ 4��° ���������� ���� ������ �ֽ��ϴ�.', '���� ��ü ��� ������', '��ö��', TO_DATE('2018-01-02', 'YYYY-MM-DD'), 
    QA_SEQ.CURRVAL, 1, 0);

INSERT INTO QABOARD VALUES (QA_SEQ.NEXTVAL, 'ĳ���� ������ ��� �ǳ���', 'ĳ���� ���� 24�� �ƴϸ� �� ���մϴ�', '��亯', SYSDATE, 2, 2, 1);    
INSERT INTO QABOARD VALUES (QA_SEQ.NEXTVAL, '��Ƽ ��������� �߿��մϴ�', '���� ������ ������� �� �־�� �մϴ�', '�̴亯', SYSDATE, 2, 2, 2);    
INSERT INTO QABOARD VALUES (QA_SEQ.NEXTVAL, '�� �ٸ� �亯�Դϴ�', '�����͸� ������', '�ڴ亯', SYSDATE, 2, 2, 3);    

update qaboard set grp = 43 where no = 44 or no = 45 or no = 46;

commit;