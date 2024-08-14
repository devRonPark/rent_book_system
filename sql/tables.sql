create database mybatis_proj_db default character set = 'utf8mb4' collate = 'utf8mb4_0900_ai_ci';

use mybatis_proj_db;

-- 사용자 테이블
create table user_tbl (
	id int auto_increment primary key comment '사용자 번호',
	name varchar(50) not null comment '사용자 이름',
	email varchar(100) unique not null comment '사용자 이메일',
	created_at datetime default current_timestamp comment '사용자 가입일',
	is_deleted char(1) default 'n' not null comment '사용자 삭제 여부'
);

-- 도서 정보 테이블
create table book_tbl (
	id int auto_increment primary key comment '책 번호',
	title varchar(255) not null comment '책 제목',
	category varchar(100) comment '책 카테고리',
	writer_id int not null comment '작가',
	publisher varchar(255) not null comment '출판사',
	totalPage int default 0 comment '책 전체 페이지 수',
	published_at date not null comment '출판일',
	summary text comment '주요 내용',
	foreign key (writer_id) references user_tbl(id)
);

-- 도서 이미지 테이블
create table book_thumbnail_tbl (
	id int primary key auto_increment comment '파일 번호',
	book_id int not null comment '책 번호',
	origin_file_name varchar(255) not null comment '원본 파일명',
	stored_file_path varchar(255) not null comment '파일 저장 경로',
	file_size int not null comment '파일 크기',
	foreign key (book_id) references book_tbl(id)
);

-- 책 대여내역 테이블
create table rent_record_tbl (
	id int not null auto_increment primary key comment '대여 기록 고유 번호',
	user_id int not null comment '사용자 번호',
	book_id int not null comment '책 번호',
	started_at datetime not null default current_timestamp comment '책 대여일자',
	ended_at datetime comment '책 대여 종료일자',
	foreign key (user_id) references user_tbl(id),
	foreign key (book_id) references book_tbl(id)
);