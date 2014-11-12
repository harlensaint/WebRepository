/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014/11/8 23:18:55                           */
/*==============================================================*/


drop table if exists Department;

drop table if exists Role;

drop table if exists Users;

drop table if exists forum;

drop table if exists privileges;

drop table if exists role_privileges;

drop table if exists role_user;

/*==============================================================*/
/* Table: Department                                            */
/*==============================================================*/
create table Department
(
   id                   bigint not null,
   name                 varchar(254),
   description          varchar(254),
   parent_id            bigint,
   primary key (id)
);

/*==============================================================*/
/* Table: Role                                                  */
/*==============================================================*/
create table Role
(
   id                   bigint not null,
   name                 varchar(254),
   description          varchar(254),
   primary key (id)
);

/*==============================================================*/
/* Table: Users                                                 */
/*==============================================================*/
create table Users
(
   id                   bigint not null,
   name                 varchar(254),
   loginName            varchar(254),
   email                varchar(254),
   sex                  int,
   telephone            varchar(11),
   description          varchar(254),
   department_id        bigint,
   password             varchar(32),
   primary key (id)
);

/*==============================================================*/
/* Table: forum                                                 */
/*==============================================================*/
create table forum
(
   id                   bigint not null,
   name                 varchar(32),
   description          varchar(255),
   position_            int,
   primary key (id)
);

/*==============================================================*/
/* Table: privileges                                            */
/*==============================================================*/
create table privileges
(
   id                   bigint not null,
   name                 varchar(50),
   url                  varchar(255),
   parent_id            bigint,
   primary key (id)
);

/*==============================================================*/
/* Table: role_privileges                                       */
/*==============================================================*/
create table role_privileges
(
   role_id              bigint not null,
   privilege_id         bigint not null,
   primary key (role_id, privilege_id)
);

/*==============================================================*/
/* Table: role_user                                             */
/*==============================================================*/
create table role_user
(
   role_id              bigint not null,
   user_id              bigint not null,
   primary key (role_id, user_id)
);

alter table Department add constraint FK_parent_children foreign key (parent_id)
      references Department (id) on delete restrict on update restrict;

alter table Users add constraint FK_department_user foreign key (department_id)
      references Department (id) on delete restrict on update restrict;

alter table privileges add constraint FK_parent_childern foreign key (parent_id)
      references privileges (id) on delete restrict on update restrict;

alter table role_privileges add constraint FK_privilege_role_privilege foreign key (privilege_id)
      references privileges (id) on delete restrict on update restrict;

alter table role_privileges add constraint FK_role_role_privileges foreign key (role_id)
      references Role (id) on delete restrict on update restrict;

alter table role_user add constraint FK_role_role_user foreign key (role_id)
      references Role (id) on delete restrict on update restrict;

alter table role_user add constraint FK_user_role_user foreign key (user_id)
      references Users (id) on delete restrict on update restrict;

