create table sys_user(
		user_id bigint(20) not null auto_increment,
		username varchar(30) not null,
		password varchar(40),
		salt varchar(40),
		user_status varchar(20),
		is_admin varchar(1),
		hasdel varchar(1),
		primary key (user_id)
) engine=InnoDB AUTO_INCREMENT=10000 default charset=utf8;


create table sys_role(
	role_id bigint(20) not null auto_increment,
	role_name varchar(20),
	role varchar(20),
	description varchar(200),
	is_available varchar(1),
	primary key (role_id)
)engine=InnoDB default charset=utf8;

create table user_role(
	user_role_id bigint(20) not null auto_increment,
	user_id bigint(20),
	role_id bigint(20),
	primary key (user_role_id)
) engine=InnoDB default charset=utf8;

create table sys_permission(
	permission_id bigint(20) not null auto_increment,
	permission_name varchar(20),
	permission varchar(20),
	description varchar(200),
	is_available varchar(1),
	primary key (permission_id)
) engine=InnoDB default charset=utf8;

create table role_permission(
	role_permission_id bigint(20) not null auto_increment,
	role_id bigint(20),
	permission_id bigint(20),
	primary key (role_permission_id)
) engine=InnoDB default charset=utf8;