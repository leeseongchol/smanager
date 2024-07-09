-- 학생정보(학생번호, 이름, 연락처, 주소, 생년월일)
--tbl_student 테이블명. 
create table tbl_student (
    std_no varchar2(10) primary key,
    std_name varchar2(100) not null,
    std_phone varchar2(20),-- 010-1111-2222
    address varchar(100),
    birth_date date,
    creation_date date default sysdate
);


--sample data.
--입력 
insert into tbl_student (std_no, std_name, std_phone)
values('S2024-01', '홍길동', '010-1234-5678' );

insert into tbl_student (std_no, std_name, std_phone, address)
values('S2024-02', '김길동', '010-3333-4444', '서울 100번지' );

insert into tbl_student (std_no, std_name, std_phone)
values('S2024-03', '이창호', '010-5555-7777');


select *
from tbl_student;

update tbl_student
set address = '대전 100'
where std_no = 'S2024-01';

insert into tbl_student (std_no, std_name, std_phone)
values('S2024-04', '김민규', '010-2222-5678');

delete from tbl_student
where std_no = '1';

update tbl_student
set birth_date = to_date ('1900-04-04','yyyy-mm-dd'),
    address = '화성'
   
where std_no = 'S2024-01';


commit;



