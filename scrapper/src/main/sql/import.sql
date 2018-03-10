create table host
(
	ID bigint auto_increment
		primary key,
	DATA_TYPE varchar(255) null,
	URL varchar(255) null
)
;

create table link
(
	ID bigint auto_increment
		primary key,
	LINK varchar(255) null,
	TYPE varchar(255) null,
	HOST_ID bigint null,
	constraint FK5djdoxk5pspjrkq3ul30maogg
		foreign key (HOST_ID) references host (ID)
)
;

create index FK5djdoxk5pspjrkq3ul30maogg
	on link (HOST_ID)
;

