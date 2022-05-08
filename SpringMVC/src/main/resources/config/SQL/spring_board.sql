drop table spring_board;

create table spring_board(
    idx number(8) constraint spring_board_pk primary key, --�۹�ȣ
    name varchar2(30) not null, --�ۼ���
    pwd varchar2(20) not null, --���
    subject varchar2(200), --����
    content varchar2(2000), --�� ����
    wdate timestamp default systimestamp, --�ۼ���
    readnum number(8) default 0, --��ȸ��
    filename varchar2(300), --÷�����ϸ�[����Ͻú���_file.png] =>������ ���ϸ�
    originFilename varchar2(300), --�������ϸ� [file.png]
    filesize number(8), --÷������ũ��
    refer number(8) , --�۱׷� ��ȣ [�亯�� �Խ����� �� ���]
    lev number(8), --�亯 ���� [�亯�� �Խ����� �� ���] 
    sunbun number(8)--���� �۱׷� �������� ���� ���� [�亯�� �Խ����� �� ���]
);

drop sequence spring_board_seq;

create sequence spring_board_seq
start with 1
increment by 1
nocache;
