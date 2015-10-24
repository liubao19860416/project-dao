/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014/10/11 11:38:32                          */
/*==============================================================*/


drop index Index_code on t_basic_maintenance;

drop table if exists t_basic_maintenance;

drop index Index_code on t_basic_spare_part;

drop table if exists t_basic_spare_part;

drop index Index_code on t_basic_spare_part_group;

drop table if exists t_basic_spare_part_group;

drop index Index_code on t_basic_spare_part_grouped;

drop table if exists t_basic_spare_part_grouped;

drop index Index_code on t_brand;

drop table if exists t_brand;

drop index Index_name on t_city;

drop index Index_code on t_city;

drop table if exists t_city;

drop index Index_code on t_coupon;

drop table if exists t_coupon;

drop index Index_name on t_dealer;

drop index Index_code on t_dealer;

drop table if exists t_dealer;

drop index Index_code on t_dealer_employee;

drop index Index_name on t_dealer_employee;

drop table if exists t_dealer_employee;

drop index Index_code on t_dealer_feedback;

drop table if exists t_dealer_feedback;

drop index Index_code on t_dealer_maintenance;

drop table if exists t_dealer_maintenance;

drop index Index_code on t_dealer_product;

drop table if exists t_dealer_product;

drop index Index_name on t_dealer_spare_part;

drop index Index_code on t_dealer_spare_part;

drop table if exists t_dealer_spare_part;

drop index Index_code on t_dealer_spare_part_group;

drop table if exists t_dealer_spare_part_group;

drop index Index_code on t_dealer_spare_part_grouped;

drop table if exists t_dealer_spare_part_grouped;

drop index Index_code on t_dealer_vehicle;

drop table if exists t_dealer_vehicle;

drop index Index_cityCode on t_labor_hour_price;

drop table if exists t_labor_hour_price;

drop index Index_code on t_maintenance_type;

drop table if exists t_maintenance_type;

drop index Index_code on t_spare_part_price;

drop table if exists t_spare_part_price;

drop index Index_phoneNumber on t_user;

drop index Index_name on t_user;

drop index Index_code on t_user;

drop table if exists t_user;

drop index Index_code on t_user_address;

drop table if exists t_user_address;

drop index Index_code on t_user_collect;

drop table if exists t_user_collect;

drop index Index_code2 on t_user_coupon;

drop index Index_code on t_user_coupon;

drop table if exists t_user_coupon;

drop index Index_userCode on t_user_feedback;

drop table if exists t_user_feedback;

drop index Index_dealer on t_user_order;

drop index Index_user on t_user_order;

drop index Index_code on t_user_order;

drop table if exists t_user_order;

drop index Index_code on t_user_order_basic_maintenance;

drop table if exists t_user_order_basic_maintenance;

drop index Index_code on t_user_order_basic_spare_part;

drop table if exists t_user_order_basic_spare_part;

drop table if exists t_user_order_comment;

drop index Index_code on t_user_order_dealer_maintenance;

drop table if exists t_user_order_dealer_maintenance;

drop index Index_code on t_user_order_dealer_spare_part;

drop table if exists t_user_order_dealer_spare_part;

drop index Index_code on t_user_order_history;

drop table if exists t_user_order_history;

drop index Index_code2 on t_user_vehicle;

drop index Index_code1 on t_user_vehicle;

drop table if exists t_user_vehicle;

drop index Index_code on t_user_vehicle_maintenance_history;

drop table if exists t_user_vehicle_maintenance_history;

drop index Index_code on t_vehicle;

drop table if exists t_vehicle;

drop index Index_version on t_version;

drop table if exists t_version;

drop index Index_code on t_version_history;

drop table if exists t_version_history;

/*==============================================================*/
/* Table: t_basic_maintenance                                   */
/*==============================================================*/
create table t_basic_maintenance
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   name                 varchar(255) not null,
   maintenanceOwnerCode varchar(32) not null,
   fitToMinKm           int not null,
   fitToMaxKm           int not null,
   fitToMinDaysUsed     int not null,
   fitToMaxDaysUsed     int not null,
   fitToEmissionVolume  varchar(255) not null,
   fitToVehicleScope    varchar(255) not null,
   fitToCity            varchar(255) not null,
   periodValue          int,
   periodUnit           varchar(10),
   laborHour            float not null,
   docURL               varchar(255),
   description          varchar(500),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

alter table t_basic_maintenance comment '保养管家系统预定义的保养项目';

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create unique index Index_code on t_basic_maintenance
(
   code
);

/*==============================================================*/
/* Table: t_basic_spare_part                                    */
/*==============================================================*/
create table t_basic_spare_part
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   name                 varchar(255) not null,
   price                float not null,
   discountPrice        float not null,
   imageURL             varchar(255),
   description          varchar(500),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create unique index Index_code on t_basic_spare_part
(
   code
);

/*==============================================================*/
/* Table: t_basic_spare_part_group                              */
/*==============================================================*/
create table t_basic_spare_part_group
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   maintenanceCode      varchar(32) not null,
   name                 varchar(255) not null,
   minSelected          int not null,
   maxSelected          int not null,
   description          varchar(255),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

alter table t_basic_spare_part_group comment '配件组包含一个或多个同一种类型的配件';

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create unique index Index_code on t_basic_spare_part_group
(
   code,
   maintenanceCode
);

/*==============================================================*/
/* Table: t_basic_spare_part_grouped                            */
/*==============================================================*/
create table t_basic_spare_part_grouped
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   sparePartGroupCode   varchar(32) not null,
   sparePartCode        varchar(32) not null,
   selectMode           varchar(10) not null,
   amount               int not null,
   description          varchar(255) not null,
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

alter table t_basic_spare_part_grouped comment '配件组所包含配件信息';

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create unique index Index_code on t_basic_spare_part_grouped
(
   sparePartGroupCode,
   sparePartCode
);

/*==============================================================*/
/* Table: t_brand                                               */
/*==============================================================*/
create table t_brand
(
   id                   bigint not null auto_increment,
   level                int not null,
   code                 varchar(32) not null,
   name                 varchar(255)  not null,
   parentCode           varchar(32),
   fullPath             varchar(255)  not null,
   description          varchar(255),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create unique index Index_code on t_brand
(
   code,
   parentCode
);

/*==============================================================*/
/* Table: t_city                                                */
/*==============================================================*/
create table t_city
(
   id                   bigint not null auto_increment,
   parentCode           varchar(32),
   level                int not null,
   code                 varchar(32) not null,
   name                 varchar(255) not null,
   fullPath             varchar(255) not null,
   description          varchar(255),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create unique index Index_code on t_city
(
   code,
   parentCode
);

/*==============================================================*/
/* Index: Index_name                                            */
/*==============================================================*/
create index Index_name on t_city
(
   name
);

/*==============================================================*/
/* Table: t_coupon                                              */
/*==============================================================*/
create table t_coupon
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   name                 varchar(255) not null,
   fitToMinKm           int not null,
   fitToMaxKm           int not null,
   fitToMinDaysUsed     int not null,
   fitToMaxDaysUsed     int not null,
   fitToEmissionVolume  varchar(255) not null,
   fitToVehicleScope    varchar(255) not null,
   fitToCity            varchar(255) not null,
   beginDatetime        timestamp not null default '0000-00-00 00:00:00',
   endDatetime          timestamp not null default '0000-00-00 00:00:00',
   validInDays          int,
   moneyAmount          float not null,
   color1               varchar(10) not null,
   color2               varchar(10) not null,
   ruleBrief            varchar(255),
   ruleDetail           text,
   description          varchar(255),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00' comment '创建时间',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00' comment '修改时间',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create unique index Index_code on t_coupon
(
   code
);

/*==============================================================*/
/* Table: t_dealer                                              */
/*==============================================================*/
create table t_dealer
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   name                 varchar(255) not null,
   shortName            varchar(50),
   cityCode             varchar(32) not null,
   address              varchar(255) not null,
   phone                varchar(20),
   mobilePhone          varchar(20),
   email                varchar(100),
   jingDu               double not null,
   weiDu                double not null,
   description          varchar(255),
   opinionRating        float,
   serviceRating        float,
   serviceLevel         varchar(20),
   imageUrl             varchar(255),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create unique index Index_code on t_dealer
(
   code
);

/*==============================================================*/
/* Index: Index_name                                            */
/*==============================================================*/
create index Index_name on t_dealer
(
   shortName,
   name
);

/*==============================================================*/
/* Table: t_dealer_employee                                     */
/*==============================================================*/
create table t_dealer_employee
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   dealerCode           varchar(32) not null,
   name                 varchar(20) not null,
   loginName            varchar(50) not null,
   password             varchar(100) not null,
   mobilePhone          varchar(20),
   email                varchar(100),
   description          varchar(255),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

alter table t_dealer_employee comment '经销商员工指能使用保养管家系统的经销商工作人员';

/*==============================================================*/
/* Index: Index_name                                            */
/*==============================================================*/
create index Index_name on t_dealer_employee
(
   loginName,
   name
);

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create unique index Index_code on t_dealer_employee
(
   code
);

/*==============================================================*/
/* Table: t_dealer_feedback                                     */
/*==============================================================*/
create table t_dealer_feedback
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   dealerCode           varchar(32) not null,
   dealerName           varchar(255) not null,
   dealerEmployeeCode   varchar(32) not null,
   content              text not null,
   description          varchar(255),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create index Index_code on t_dealer_feedback
(
   dealerCode,
   dealerEmployeeCode
);

/*==============================================================*/
/* Table: t_dealer_maintenance                                  */
/*==============================================================*/
create table t_dealer_maintenance
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   name                 varchar(255) not null,
   maintenanceOwnerCode varchar(32) not null,
   fitToMinKm           int not null,
   fitToMaxKm           int not null,
   fitToMinDaysUsed     int not null,
   fitToMaxDaysUsed     int not null,
   fitToEmissionVolume  varchar(255) not null,
   fitToVehicleScope    varchar(255) not null,
   fitToCity            varchar(255) not null,
   periodValue          int,
   periodUnit           varchar(10),
   laborHour            float not null,
   docURL               varchar(255),
   description          varchar(500),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create unique index Index_code on t_dealer_maintenance
(
   maintenanceOwnerCode,
   code
);

/*==============================================================*/
/* Table: t_dealer_product                                      */
/*==============================================================*/
create table t_dealer_product
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   dealerCode           varchar(32) not null,
   laborHourDiscount    float not null,
   priceDiscount        float not null,
   startDatetime        timestamp not null default '0000-00-00 00:00:00',
   endDatetime          timestamp not null default '0000-00-00 00:00:00',
   workStationAvailable int not null,
   workStationCount     int not null,
   fitToMinKm           int not null,
   fitToMaxKm           int not null,
   fitToMinDaysUsed     int not null,
   fitToMaxDaysUsed     int not null,
   fitToVehicleScope    varchar(255) not null,
   fitToEmissionVolume  varchar(255) not null,
   docUrl               varchar(255),
   description          varchar(255),
   operatorCode         varchar(32) not null,
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create unique index Index_code on t_dealer_product
(
   dealerCode,
   code
);

/*==============================================================*/
/* Table: t_dealer_spare_part                                   */
/*==============================================================*/
create table t_dealer_spare_part
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   name                 varchar(255) not null,
   price                float not null,
   discountPrice        float not null,
   imageURL             varchar(255),
   description          varchar(500),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create unique index Index_code on t_dealer_spare_part
(
   code
);

/*==============================================================*/
/* Index: Index_name                                            */
/*==============================================================*/
create index Index_name on t_dealer_spare_part
(
   name
);

/*==============================================================*/
/* Table: t_dealer_spare_part_group                             */
/*==============================================================*/
create table t_dealer_spare_part_group
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   maintenanceCode      varchar(32) not null,
   name                 varchar(255) not null,
   minSelected          int not null,
   maxSelected          int not null,
   description          varchar(255),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create unique index Index_code on t_dealer_spare_part_group
(
   maintenanceCode,
   code
);

/*==============================================================*/
/* Table: t_dealer_spare_part_grouped                           */
/*==============================================================*/
create table t_dealer_spare_part_grouped
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   sparePartGroupCode   varchar(32) not null,
   sparePartCode        varchar(32) not null,
   selectMode           varchar(10) not null,
   amount               int not null,
   description          varchar(255),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create unique index Index_code on t_dealer_spare_part_grouped
(
   sparePartGroupCode,
   sparePartCode
);

/*==============================================================*/
/* Table: t_dealer_vehicle                                      */
/*==============================================================*/
create table t_dealer_vehicle
(
   id                   bigint not null auto_increment,
   dealerCode           varchar(32) not null,
   vehicleCode          varchar(32) not null,
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

alter table t_dealer_vehicle comment '经销商所经销的车辆信息';

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create unique index Index_code on t_dealer_vehicle
(
   vehicleCode,
   dealerCode
);

/*==============================================================*/
/* Table: t_labor_hour_price                                    */
/*==============================================================*/
create table t_labor_hour_price
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   cityCode             varchar(32) not null,
   price                float not null,
   description          varchar(255),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_cityCode                                        */
/*==============================================================*/
create unique index Index_cityCode on t_labor_hour_price
(
   cityCode
);

/*==============================================================*/
/* Table: t_maintenance_type                                    */
/*==============================================================*/
create table t_maintenance_type
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   name1                varchar(255) not null,
   name2                varchar(255) not null,
   periodValue          int not null,
   periodUnit           varchar(10) not null,
   fitToCity            varchar(255) not null,
   fitToVehicleScope    varchar(255) not null,
   fitToMinKm           int not null,
   fitToMaxKm           int not null,
   fitToEmissionVolume  varchar(255) not null,
   fitToMinDaysUsed     int not null,
   fitToMaxDaysUsed     int not null,
   description          varchar(255),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create unique index Index_code on t_maintenance_type
(
   code,
   name1,
   name2
);

/*==============================================================*/
/* Table: t_spare_part_price                                    */
/*==============================================================*/
create table t_spare_part_price
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   cityCode             varchar(32) not null,
   sparePartCode        varchar(32) not null,
   price                float not null,
   description          varchar(255),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create unique index Index_code on t_spare_part_price
(
   cityCode,
   sparePartCode
);

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   name                 varchar(20) not null,
   mobilePhone          varchar(20) not null,
   loginName            varchar(50),
   password             varchar(100) not null,
   email                varchar(100),
   description          varchar(255),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create unique index Index_code on t_user
(
   code
);

/*==============================================================*/
/* Index: Index_name                                            */
/*==============================================================*/
create unique index Index_name on t_user
(
   loginName
);

/*==============================================================*/
/* Index: Index_phoneNumber                                     */
/*==============================================================*/
create index Index_phoneNumber on t_user
(
   mobilePhone
);

/*==============================================================*/
/* Table: t_user_address                                        */
/*==============================================================*/
create table t_user_address
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   userCode             varchar(32) not null,
   provinceCode         varchar(32),
   cityCode             varchar(32),
   areaCode             varchar(32),
   address              varchar(255),
   description          varchar(255),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create unique index Index_code on t_user_address
(
   userCode,
   code
);

/*==============================================================*/
/* Table: t_user_collect                                        */
/*==============================================================*/
create table t_user_collect
(
   id                   bigint not null auto_increment comment '用户收藏ID',
   code                 varchar(32) not null,
   dealerCode           varchar(32) not null comment '经销商ID',
   userCode             varchar(32) not null comment '用户ID',
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00' comment '创建时间',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00' comment '更新时间',
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET=utf8;

alter table t_user_collect comment '用户收藏表';

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create unique index Index_code on t_user_collect
(
   userCode,
   dealerCode
);

/*==============================================================*/
/* Table: t_user_coupon                                         */
/*==============================================================*/
create table t_user_coupon
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   userCode             varchar(32) not null,
   couponCode           varchar(32) not null,
   verifyCode           varchar(32) not null,
   userMobilePhone      varchar(20) not null,
   orderCode            varchar(32),
   status               varchar(32) not null,
   userVehiclePlateNumber varchar(20),
   promotionCode        varchar(32),
   description          varchar(255),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00' comment '创建时间',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00' comment '修改时间',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create index Index_code on t_user_coupon
(
   userCode,
   couponCode
);

/*==============================================================*/
/* Index: Index_code2                                           */
/*==============================================================*/
create unique index Index_code2 on t_user_coupon
(
   code
);

/*==============================================================*/
/* Table: t_user_feedback                                       */
/*==============================================================*/
create table t_user_feedback
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   userCode             varchar(32) not null,
   mobilePhone          varchar(20),
   content              text not null,
   description          varchar(255),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_userCode                                        */
/*==============================================================*/
create index Index_userCode on t_user_feedback
(
   userCode
);

/*==============================================================*/
/* Table: t_user_order                                          */
/*==============================================================*/
create table t_user_order
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   userCode             varchar(32) not null,
   userAddressCode      varchar(32),
   dealerCode           varchar(32) not null,
   dealerName           varchar(255) not null,
   dealerShortName      varchar(255),
   dealerProductCode    varchar(32) not null,
   totalMoney           float not null,
   actualAmount         float not null,
   maintenanceDatetime  timestamp default '0000-00-00 00:00:00',
   beginDatetime        timestamp not null default '0000-00-00 00:00:00',
   endDatetime          timestamp default '0000-00-00 00:00:00',
   couponCode           varchar(32),
   status               varchar(32) not null,
   statusIndex          int not null,
   deviceId             varchar(100),
   maintenanceTypeCode  varchar(32) not null,
   maintenanceTypeName  varchar(255) not null,
   userName             varchar(20),
   userVehicleRecordCode varchar(32) not null,
   userVehicleKmTraveled int not null,
   userVehicleCode      varchar(32) not null,
   vehiclePlateNumber   varchar(20) not null,
   userMobilePhone      varchar(32) not null,
   serviceLevel         varchar(20),
   dealerPhone          varchar(20),
   dealerAddress        varchar(255),
   userRemark           varchar(255),
   dealerRemark         varchar(255),
   source               varchar(255),
   dealerJingDu         double not null,
   dealerWeiDu          double not null,
   laborHourDiscount    float not null,
   sparePartDiscount    float not null,
   description          varchar(255),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create unique index Index_code on t_user_order
(
   code
);

/*==============================================================*/
/* Index: Index_user                                            */
/*==============================================================*/
create index Index_user on t_user_order
(
   userCode,
   code
);

/*==============================================================*/
/* Index: Index_dealer                                          */
/*==============================================================*/
create index Index_dealer on t_user_order
(
   dealerCode,
   code
);

/*==============================================================*/
/* Table: t_user_order_basic_maintenance                        */
/*==============================================================*/
create table t_user_order_basic_maintenance
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   userOrderCode        varchar(32) not null,
   maintenanceCode      varchar(32) not null,
   name                 varchar(255) not null,
   laborHour            float not null,
   laborHourPrice       float not null,
   laborHourDiscountPrice float not null,
   hasDoc               boolean not null,
   totalMoney           float not null,
   totalDiscountMoney   float not null,
   description          varchar(500),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create unique index Index_code on t_user_order_basic_maintenance
(
   userOrderCode,
   maintenanceCode
);

/*==============================================================*/
/* Table: t_user_order_basic_spare_part                         */
/*==============================================================*/
create table t_user_order_basic_spare_part
(
   id                   bigint not null auto_increment,
   maintenanceCode      varchar(32) not null,
   code                 varchar(32) not null comment '基础配件编号',
   name                 varchar(255) not null comment '经销商配件编号',
   price                float not null comment '基础配件价格',
   number               int not null,
   discountPrice        float not null,
   imageUrl             varchar(255),
   description          varchar(500),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00' comment '创建日期',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00' comment '修改日期',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create index Index_code on t_user_order_basic_spare_part
(
   maintenanceCode,
   code
);

/*==============================================================*/
/* Table: t_user_order_comment                                  */
/*==============================================================*/
create table t_user_order_comment
(
   id                   bigint not null auto_increment,
   parentCode           varchar(32),
   code                 varchar(32) not null,
   userOrderCode        varchar(32) not null,
   rating               float not null,
   submitterCode        varchar(32) not null,
   submitterType        varchar(32) not null,
   description          text,
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00' comment '创建时间',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00' comment '修改时间',
   primary key (id)
);

/*==============================================================*/
/* Table: t_user_order_dealer_maintenance                       */
/*==============================================================*/
create table t_user_order_dealer_maintenance
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   userOrderCode        varchar(32) not null,
   maintenanceCode      varchar(32) not null,
   name                 varchar(255) not null,
   laborHour            float not null,
   laborHourPrice       float not null,
   laborHourDiscountPrice float not null,
   hasDoc               boolean not null,
   totalMoney           float not null,
   totalDiscountMoney   float not null,
   description          varchar(500),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create unique index Index_code on t_user_order_dealer_maintenance
(
   userOrderCode,
   maintenanceCode
);

/*==============================================================*/
/* Table: t_user_order_dealer_spare_part                        */
/*==============================================================*/
create table t_user_order_dealer_spare_part
(
   id                   bigint not null auto_increment,
   maintenanceCode      varchar(32) not null,
   code                 varchar(32) not null comment '经销商配件编号',
   name                 varchar(255) not null comment '经销商配件编号',
   price                float not null comment '经销商配件价格',
   discountPrice        float not null,
   number               int not null,
   imageUrl             varchar(255),
   description          varchar(500),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00' comment '创建日期',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00' comment '修改日期',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create index Index_code on t_user_order_dealer_spare_part
(
   maintenanceCode,
   code
);

/*==============================================================*/
/* Table: t_user_order_history                                  */
/*==============================================================*/
create table t_user_order_history
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   userOrderCode        varchar(32) not null comment '订单编号',
   status               varchar(20) not null comment '订单状态',
   operatorCode         varchar(32) not null comment '操作人编号',
   orderMoney           float,
   maintenanceMoney     float,
   description          varchar(255) comment '描述',
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00' comment '创建时间',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00' comment '修改时间',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create index Index_code on t_user_order_history
(
   userOrderCode
);

/*==============================================================*/
/* Table: t_user_vehicle                                        */
/*==============================================================*/
create table t_user_vehicle
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   userCode             varchar(32) not null,
   vehicleCode          varchar(32) not null,
   vehiclePlateNumber   varchar(20),
   color                varchar(20),
   boughtInYear         timestamp default '0000-00-00 00:00:00',
   kmTraveled           int not null,
   lastMaintainedKm     int not null,
   lastMaintainedDatetime timestamp default '0000-00-00 00:00:00',
   description          varchar(255),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_code1                                           */
/*==============================================================*/
create index Index_code1 on t_user_vehicle
(
   userCode,
   vehicleCode
);

/*==============================================================*/
/* Index: Index_code2                                           */
/*==============================================================*/
create unique index Index_code2 on t_user_vehicle
(
   code
);

/*==============================================================*/
/* Table: t_user_vehicle_maintenance_history                    */
/*==============================================================*/
create table t_user_vehicle_maintenance_history
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   userCode             varchar(32) not null,
   userVehicleRecordCode varchar(32) not null,
   maintenanceCode      varchar(32) not null,
   kmTraveled           int not null,
   maintainedDatetime   timestamp not null default '0000-00-00 00:00:00',
   description          varchar(255),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create index Index_code on t_user_vehicle_maintenance_history
(
   userCode,
   userVehicleRecordCode
);

/*==============================================================*/
/* Table: t_vehicle                                             */
/*==============================================================*/
create table t_vehicle
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   name                 varchar(255) not null,
   gear                 varchar(10) not null,
   emissionVolume       varchar(10) not null,
   onMarketDatetime     timestamp default '0000-00-00 00:00:00',
   turbo                boolean not null,
   description          varchar(255),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create unique index Index_code on t_vehicle
(
   code
);

/*==============================================================*/
/* Table: t_version                                             */
/*==============================================================*/
create table t_version
(
   id                   bigint not null auto_increment comment 'ID',
   code                 varchar(32) not null,
   version              varchar(20) not null comment '版本号',
   appName              varchar(255) not null comment 'app名称',
   downloadUrl          varchar(255) not null comment '下载地址',
   forceToUpgrade       boolean not null comment '是否强制升级',
   description          text comment '描述',
   platform             char(10) comment '平台标识(ios/andriod)',
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00' comment '创建时间',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00' comment '更新时间',
   primary key (id)
)
ENGINE = InnoDB
DEFAULT CHARSET=utf8;

alter table t_version comment '版本号表';

/*==============================================================*/
/* Index: Index_version                                         */
/*==============================================================*/
create index Index_version on t_version
(
   version
);

/*==============================================================*/
/* Table: t_version_history                                     */
/*==============================================================*/
create table t_version_history
(
   id                   bigint not null auto_increment,
   code                 varchar(32) not null,
   version              varchar(20) not null,
   platform             varchar(10) not null,
   content              text,
   description          varchar(255),
   actived              boolean not null,
   deleted              boolean not null,
   createdDatetime      timestamp not null default '0000-00-00 00:00:00' comment '创建时间',
   updatedDatetime      timestamp not null default '0000-00-00 00:00:00' comment '更新时间',
   primary key (id)
);

/*==============================================================*/
/* Index: Index_code                                            */
/*==============================================================*/
create index Index_code on t_version_history
(
   code,
   version
);

