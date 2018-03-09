
DROP TABLE QABOARD;
DROP SEQUENCE QA_SEQ;
CREATE TABLE QABOARD(  -- 게시판 생성
    NO NUMBER NOT NULL,
    TITLE VARCHAR2(200) NOT NULL,
    CONTENT VARCHAR2(2000),
    WRITER VARCHAR2(100) NOT NULL,
    WRITEDATE DATE DEFAULT SYSDATE NOT NULL,
    GRP NUMBER,  -- 한 게시글과 그 답변들은 한 그룹이며 부모-자식 관계가 된다.
    SEQ NUMBER,  -- 같은 그룹 내 답변글 순서 
    LVL NUMBER   -- 같은 그룹 내 계층 
);

-- 게시글 시퀀스 생성
CREATE SEQUENCE QA_SEQ 
    START WITH 1
    INCREMENT BY 1;  
    
-- NO를 PRIMARY KEY로 만들기 전에 인덱스 생성을 해야한다.
-- REVERSE로 해서 새 글이 나중번호를 갖게 한다.
CREATE INDEX IDX_QABOARD_NO ON QABOARD(NO) REVERSE;

 -- 이제 NO를 PRIMARY KEY로 만든다.
ALTER TABLE QABOARD ADD CONSTRAINT QABOARD_NO_PK PRIMARY KEY(NO); 

-- GRP, SEQ에 인덱스 생성. GRP은 내림차순, SEQ는 오름차순으로.
CREATE INDEX IDX_QABOARD_GRP_SEQ ON QABOARD(GRP DESC, SEQ ASC); 

-- NLS_DATE_FORMAT을 년도-월-일 24시간:분:초 형태로 만듦
ALTER SESSION SET NLS_DATE_FORMAT = 'YYYY-MM-DD HH24:MI:SS';

----- 샘플 게시글 입력 -----

-- 샘플 질문 게시글들 --
INSERT INTO QABOARD 
    VALUES(QA_SEQ.NEXTVAL, '상품에 대한 질문이 있습니다.', '내용 내용 내용', '김철수', TO_DATE('2018-01-02', 'YYYY-MM-DD'), 
    QA_SEQ.CURRVAL, 1, 0);  -- GRP 밸류는 QA_SEQ 밸류이고 SEQ는 1이며 LVL은 0이다. LVL은 패드용이다.

INSERT INTO QABOARD 
    VALUES(QA_SEQ.NEXTVAL, '아이템 질문', '모 아이템의 최고 레벨은 몇인가요?', '이영희', TO_DATE('2018-02-13', 'YYYY-MM-DD'), 
    QA_SEQ.CURRVAL, 1, 0);  
    
INSERT INTO QABOARD 
    VALUES(QA_SEQ.NEXTVAL, '게임 실행이 안되요', '모 제품을 구매해서 다운로드를 했는데 실행이 안됩니다', '박길동', 
    TO_DATE('2018-02-20', 'YYYY-MM-DD'), QA_SEQ.CURRVAL, 1, 0);    

-- 샘플 답변 게시글 --
  -- 첫번째 질문글에 답글1
INSERT INTO QABOARD VALUES (QA_SEQ.NEXTVAL, '질문에 대한 답변입니다.', '답변 답변 답변', '김답변', SYSDATE, 2, 2, 1);
  -- 첫번째 질문글에 답글 2
INSERT INTO QABOARD VALUES (QA_SEQ.NEXTVAL, '질문에 대한 또 다른 답변입니다.', '답변2 답변2 답변2', '김답변', SYSDATE, 2, 2, 2);  
  -- 첫번째 질문글에 답글 3  
INSERT INTO QABOARD VALUES (QA_SEQ.NEXTVAL, '또 다른 답변', '답변3 답변3 답변3', '김답변', SYSDATE, 2, 2, 3); 


    -- 두번째 질문글에 답글
UPDATE QABOARD SET SEQ = SEQ + 1 WHERE GRP = 1 AND SEQ > 2;
INSERT INTO QABOARD VALUES (QA_SEQ.NEXTVAL, '그 아이템의 최고 레벨은 99입니다', NULL, '이답변', SYSDATE, 1, 2, 1);

-- 세번째 질문글에 답글
--UPDATE QABOARD SET SEQ = SEQ + 1 WHERE GRP = 4 AND SEQ > 2;
INSERT INTO QABOARD VALUES (QA_SEQ.NEXTVAL, '다운로드 한 후 실행법', '이 방법을 따라해보시기 바랍니다', '박답변', SYSDATE, 4, 2, 1);

--DELETE FROM QABOARD WHERE NO = 7;

-- 게시판 보기
SELECT * FROM QABOARD ORDER BY GRP, SEQ ASC;

-- 질문, 답글 계층형으로 보기 (간단히)
SELECT CASE WHEN (LVL = 0 OR LVL > 0) THEN NO END 
    NO, GRP, LVL, RPAD('+', LVL, '-') || TITLE "TITLE", WRITER, WRITEDATE 
    FROM QABOARD 
    GROUP BY NO, GRP, LVL, TITLE, WRITER, WRITEDATE 
    ORDER BY GRP;
    
-- 질문, 답글 계층형으로 보기 (자세히)
SELECT CASE WHEN (LVL = 0 OR LVL > 0) THEN NO END 
    NO, GRP, SEQ, LVL,RPAD('+', LVL, '-') || TITLE "TITLE", CONTENT, WRITER, WRITEDATE 
    FROM QABOARD 
    GROUP BY NO, GRP, SEQ, LVL, TITLE, CONTENT, WRITER, WRITEDATE 
    ORDER BY GRP;    
    
SELECT content from qaboard where no = 2;    
COMMIT;   

INSERT INTO QABOARD 
    VALUES(QA_SEQ.NEXTVAL, '게임의 4번째 스테이지에 대한 질문이 있습니다.', '보스 대체 어떻게 깨나요', '김철수', TO_DATE('2018-01-02', 'YYYY-MM-DD'), 
    QA_SEQ.CURRVAL, 1, 0);

INSERT INTO QABOARD VALUES (QA_SEQ.NEXTVAL, '캐릭터 레벨이 어떻게 되나요', '캐릭이 레벨 24가 아니면 넘 약합니다', '김답변', SYSDATE, 2, 2, 1);    
INSERT INTO QABOARD VALUES (QA_SEQ.NEXTVAL, '파티 멤버구성도 중요합니다', '전사 성직자 마법사는 꼭 있어야 합니다', '이답변', SYSDATE, 2, 2, 2);    
INSERT INTO QABOARD VALUES (QA_SEQ.NEXTVAL, '또 다른 답변입니다', '에이터를 쓰세요', '박답변', SYSDATE, 2, 2, 3);    

update qaboard set grp = 43 where no = 44 or no = 45 or no = 46;

commit;