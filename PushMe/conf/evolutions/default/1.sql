# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table activity (
  name                      varchar(255) not null,
  low_intensity             integer,
  medium_intensity          integer,
  high_intensity            integer,
  constraint pk_activity primary key (name))
;

create table activity_level (
  id                        integer auto_increment not null,
  level                     integer,
  description               varchar(255),
  constraint pk_activity_level primary key (id))
;

create table tips (
  tip_number                integer auto_increment not null,
  tip                       varchar(255),
  constraint pk_tips primary key (tip_number))
;

create table user (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (email))
;

create table user_activity (
  id                        integer auto_increment not null,
  belongs_to_email          varchar(255),
  activity_name             varchar(255),
  intensity                 integer,
  steps                     double,
  date                      datetime,
  constraint pk_user_activity primary key (id))
;

create table user_steps (
  belongs_to_email          varchar(255),
  steps                     double,
  date                      datetime)
;

create table userinformation (
  id                        integer auto_increment not null,
  name                      varchar(255),
  dob                       datetime,
  weight                    integer,
  height                    integer,
  gender                    varchar(255),
  current_al                varchar(255),
  target_al                 varchar(255),
  belongs_to_email          varchar(255),
  constraint pk_userinformation primary key (id))
;

alter table user_activity add constraint fk_user_activity_belongsTo_1 foreign key (belongs_to_email) references user (email) on delete restrict on update restrict;
create index ix_user_activity_belongsTo_1 on user_activity (belongs_to_email);
alter table user_activity add constraint fk_user_activity_activity_2 foreign key (activity_name) references activity (name) on delete restrict on update restrict;
create index ix_user_activity_activity_2 on user_activity (activity_name);
alter table user_steps add constraint fk_user_steps_belongsTo_3 foreign key (belongs_to_email) references user (email) on delete restrict on update restrict;
create index ix_user_steps_belongsTo_3 on user_steps (belongs_to_email);
alter table userinformation add constraint fk_userinformation_belongsTo_4 foreign key (belongs_to_email) references user (email) on delete restrict on update restrict;
create index ix_userinformation_belongsTo_4 on userinformation (belongs_to_email);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table activity;

drop table activity_level;

drop table tips;

drop table user;

drop table user_activity;

drop table user_steps;

drop table userinformation;

SET FOREIGN_KEY_CHECKS=1;

