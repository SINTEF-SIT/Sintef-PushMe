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

create table goal (
  goal_number               integer auto_increment not null,
  steps                     double,
  start                     datetime,
  end                       datetime,
  type                      varchar(255),
  activity_level_id         integer,
  constraint pk_goal primary key (goal_number))
;

create table tips (
  tip_number                integer auto_increment not null,
  title                     varchar(255),
  description               varchar(255),
  constraint pk_tips primary key (tip_number))
;

create table trophy (
  trophy_number             integer auto_increment not null,
  points                    integer,
  description               varchar(255),
  end_date                  datetime,
  user_email                varchar(255),
  constraint pk_trophy primary key (trophy_number))
;

create table user (
  email                     varchar(255) not null,
  password                  varchar(255),
  name                      varchar(255),
  dob                       datetime,
  weight                    integer,
  height                    integer,
  gender                    varchar(255),
  current_al                varchar(255),
  target_al                 varchar(255),
  is_admin                  tinyint(1) default 0,
  constraint pk_user primary key (email))
;

create table user_activity (
  id                        bigint auto_increment not null,
  belongs_to_email          varchar(255),
  activity_name             varchar(255),
  intensity                 integer,
  steps                     double,
  date                      datetime,
  constraint pk_user_activity primary key (id))
;

create table user_steps (
  id                        integer auto_increment not null,
  belongs_to_email          varchar(255),
  steps                     double,
  date                      datetime,
  constraint pk_user_steps primary key (id))
;

alter table goal add constraint fk_goal_activityLevel_1 foreign key (activity_level_id) references activity_level (id) on delete restrict on update restrict;
create index ix_goal_activityLevel_1 on goal (activity_level_id);
alter table trophy add constraint fk_trophy_user_2 foreign key (user_email) references user (email) on delete restrict on update restrict;
create index ix_trophy_user_2 on trophy (user_email);
alter table user_activity add constraint fk_user_activity_belongsTo_3 foreign key (belongs_to_email) references user (email) on delete restrict on update restrict;
create index ix_user_activity_belongsTo_3 on user_activity (belongs_to_email);
alter table user_activity add constraint fk_user_activity_activity_4 foreign key (activity_name) references activity (name) on delete restrict on update restrict;
create index ix_user_activity_activity_4 on user_activity (activity_name);
alter table user_steps add constraint fk_user_steps_belongsTo_5 foreign key (belongs_to_email) references user (email) on delete restrict on update restrict;
create index ix_user_steps_belongsTo_5 on user_steps (belongs_to_email);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table activity;

drop table activity_level;

drop table goal;

drop table tips;

drop table trophy;

drop table user;

drop table user_activity;

drop table user_steps;

SET FOREIGN_KEY_CHECKS=1;

