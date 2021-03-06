-- 保养类别 -------------------------
insert into t_maintenance_type
      (code,  name1,  name2,  periodValue, periodUnit, fitToCity, fitToVehicleScope, fitToMinKm, fitToMaxKm, fitToEmissionVolume,          fitToMinDaysUsed, fitToMaxDaysUsed, description, actived, deleted, createdDatetime,       updatedDatetime)
values('001', '小保', '大保', 12000,        'km',       '*',       ',RW-550*,',          0,          10000000,   ',1.4L,1.4T,1.8L,1.8T,',   0,                100000,           '测试用',    1,       0,       '2014-09-04 10:42:00', '2014-09-04 10:42:00' ),
     ('002', '小保 A', '大保 A', 365,        'day',       '*',       ',RW-750*,',          0,          10000000,   '1.8L,1.8T,2.0L,2.0T,',   0,                100000,           '测试用',    1,       0,       '2014-09-04 10:42:00', '2014-09-04 10:42:00' )
;

-- 车辆 -----------------------------
insert into t_vehicle
      (code,           name,                       gear, emissionVolume, onMarketDatetime,      color,  turbo, description, actived, deleted, createdDatetime,        updatedDatetime)
values('RW-550-s-MAT', '荣威550s 1.8T 2013 尊享版', 'A',  '1.8T',         '2013-10-01 10:00:00', 'red',  1,     'test only', 1,       0,       '2014-09-04 16:48:00', '2014-09-04 16:48:00' ),
      ('RW-750D--20T', '荣威750D 2.0T 2012 尊享版', 'A',  '2.0T',         '2012-12-01 10:00:00', 'blue',  1,     'test only', 1,       0,       '2014-09-04 16:48:00', '2014-09-04 16:48:00' )
;

-- 经销商 ---------------------------
insert into t_dealer
      (code,        name,      shortName, cityCode, address,   phone,         mobilePhone,   email,         jingDu,     weiDu,     description, opinionRating, serviceRating, actived, deleted, createdDatetime,       updatedDatetime)
values('dealer001', '经销商 A', 'A',       '021',    '上海浦东', '13800138000', '1234567890',  'aaa@test.com', 12.34567, 10.98765,   '测试用',    4.5,           4.0,           1,       0,       '2014-09-04 16:48:00', '2014-09-04 16:48:00'),
      ('dealer002', '经销商 B', 'B',       '021',    '上海浦东', '13800138000', '1234567890',  'bbb@test.com', 12.34567, 10.98765,   '测试用',    4.5,           4.0,           1,       0,       '2014-09-04 16:48:00', '2014-09-04 16:48:00')
;

-- 经销车辆 -------------------------
insert into t_dealer_vehicle
      (dealerCode,  vehicleCode,    actived, deleted, createdDatetime,       updatedDatetime) 
values('dealer001', 'RW-550-s-MAT', 1,        0,      '2014-09-04 16:48:00', '2014-09-04 16:48:00'),
      ('dealer002', 'RW-750D--20T', 1,        0,      '2014-09-04 16:48:00', '2014-09-04 16:48:00')
;

-- 经销商商品 -----------------------
insert into t_dealer_product
      (code,  dealerCode,  laborHourDiscount, priceDiscount, startDatetime,         endDatetime,           workStationAvailable, workStationCount, fitToMinKm, fitToMaxKm, fitToMinDaysUsed, fitToMaxDaysUsed, fitToVehicleScope, fitToEmissionVolume,    hasDoc, description, operatorCode, actived, deleted, createdDatetime,       updatedDatetime)
values('dP01', 'dealer001', 0.85,              0.95,          '2014-09-01 09:30:00', '2014-09-11 09:30:00', 10,                   20,               0,          1000000,     0,               100000,           ',RW-550*,',       ',1.4L,1.4T,1.8L,1.8T,', 1,      'test only', '01',         1,       0,      '2014-09-04 10:42:00', '2014-09-04 10:42:00' ),
      ('dP02', 'dealer002', 0.88,              0.90,          '2014-09-01 09:30:00', '2014-09-11 09:30:00', 10,                   20,               0,          1000000,     0,               100000,           ',RW-750*,',       ',1.8L,1.8T,,2.0L,2.0T,', 1,      'test only', '01',         1,       0,      '2014-09-04 10:42:00', '2014-09-04 10:42:00' )
;    
      
      
-- 品牌 -----------------------
INSERT INTO `t_brand` VALUES ('1', 'pb56', '阿斯顿·马丁', '1', null, 'pb56', '阿斯顿·马丁', '1', '0', '2014-09-03 10:11:13', '2014-09-03 10:09:19');
INSERT INTO `t_brand` VALUES ('2', 'b81', '阿斯顿·马丁', '2', 'pb56', 'pb56-b81', '阿斯顿·马丁', '1', '0', '2014-09-03 10:11:47', '2014-09-03 10:12:55');
INSERT INTO `t_brand` VALUES ('3', '343', 'DB9', '3', 'pb56-b81', 'pb56-b81-343', 'DB9', '1', '0', '2014-09-04 10:55:09', '2014-09-03 10:15:02');
INSERT INTO `t_brand` VALUES ('4', '1394', 'Zagato', '3', 'pb56-b81', 'pb56-b81-1394', 'Zagato', '1', '0', '2014-09-04 10:55:11', '2014-09-03 10:16:31');
INSERT INTO `t_brand` VALUES ('5', '517', 'V8 Vantage', '3', 'pb56-b81', 'pb56-b81-517', 'V8 Vantage', '1', '0', '2014-09-04 10:55:11', '2014-09-03 10:17:15');
INSERT INTO `t_brand` VALUES ('6', '1617', 'Vanquish', '3', 'pb56-b81', 'pb56-b81-1617', 'Vanquish', '1', '0', '2014-09-04 10:55:13', '2014-09-03 10:18:04');
INSERT INTO `t_brand` VALUES ('7', '342', 'DBS', '3', 'pb56-b81', 'pb56-b81-324', 'DBS', '1', '0', '2014-09-04 10:55:14', '2014-09-03 10:18:46');
INSERT INTO `t_brand` VALUES ('8', '938', 'V12 Vantage', '3', 'pb56-b81', 'pb56-b81-938', 'V12 Vantage', '1', '0', '2014-09-04 10:55:16', '2014-09-03 10:20:34');


-- 车辆 -----------------------
INSERT INTO `t_vehicle` VALUES ('1', 'pb56-b81-343-m23037', '2014款DB9 Carbon Black Volante', 'A', '6.0L', '2014-09-03 14:06:59', '黑色', '0', '2014款DB9 Carbon Black Volante', '1', '0', '2014-09-03 10:52:29', '2014-09-03 10:54:32');
INSERT INTO `t_vehicle` VALUES ('2', 'pb56-b81-343-m22661', '2014款DB9 Carbon Black Coupe', 'b', '6.0L', '2014-09-03 14:07:15', '黑色', '0', '2014款DB9 Carbon Black Coupe', '1', '0', '2014-09-03 10:55:19', '2014-09-03 10:56:23');
INSERT INTO `t_vehicle` VALUES ('3', 'pb56-b81-343-m20580', '2014款DB9 6.0L Coupe百年纪念版', 'b', '6.0L', '2014-09-03 14:08:22', '黑色', '0', '2014款DB9 6.0L Coupe百年纪念版', '1', '0', '2014-09-03 10:59:08', '2014-09-03 10:59:10');

-- 城市 -----------------------
INSERT INTO `t_city` VALUES ('793', '310000', '上海市', '2', null, '310000', '上海市', '1', '0', '2014-09-05 17:08:50', '2014-09-03 15:32:02');
INSERT INTO `t_city` VALUES ('794', '310100', '上海市', '3', '310000', '10000-310100', '上海市', '1', '0', '2014-09-05 17:08:50', '2014-09-03 15:32:02');
INSERT INTO `t_city` VALUES ('795', '310101', '黄浦区', '4', '310000-310100', '10000-310100-310101', '黄浦区', '1', '0', '2014-09-05 17:08:50', '2014-09-03 15:32:05');
INSERT INTO `t_city` VALUES ('796', '310104', '徐汇区', '4', '310000-310100', '10000-310100-310104', '徐汇区', '1', '0', '2014-09-05 17:08:50', '2014-09-03 15:32:06');
INSERT INTO `t_city` VALUES ('797', '310105', '长宁区', '4', '310000-310100', '10000-310100-310105', '长宁区', '1', '0', '2014-09-05 17:08:50', '2014-09-03 15:32:09');
INSERT INTO `t_city` VALUES ('798', '310106', '静安区', '4', '310000-310100', '10000-310100-310106', '静安区', '1', '0', '2014-09-05 17:08:50', '2014-09-03 15:32:11');
INSERT INTO `t_city` VALUES ('799', '310107', '普陀区', '4', '310000-310100', '10000-310100-310107', '普陀区', '1', '0', '2014-09-05 17:08:50', '2014-09-03 15:32:14');
INSERT INTO `t_city` VALUES ('800', '310108', '闸北区', '4', '310000-310100', '10000-310100-310108', '闸北区', '1', '0', '2014-09-05 17:08:50', '2014-09-03 15:32:15');
INSERT INTO `t_city` VALUES ('801', '310109', '虹口区', '4', '310000-310100', '10000-310100-310109', '虹口区', '1', '0', '2014-09-05 17:08:50', '2014-09-03 15:32:16');
INSERT INTO `t_city` VALUES ('802', '310110', '杨浦区', '4', '310000-310100', '10000-310100-310110', '杨浦区', '1', '0', '2014-09-05 17:08:50', '2014-09-03 15:32:16');
INSERT INTO `t_city` VALUES ('803', '310112', '闵行区', '4', '310000-310100', '10000-310100-310112', '闵行区', '1', '0', '2014-09-05 17:08:50', '2014-09-03 15:32:19');
INSERT INTO `t_city` VALUES ('804', '310113', '宝山区', '4', '310000-310100', '10000-310100-310113', '宝山区', '1', '0', '2014-09-05 17:08:50', '2014-09-03 10:40:00');
INSERT INTO `t_city` VALUES ('805', '310114', '嘉定区', '4', '310000-310100', '10000-310100-310114', '嘉定区', '1', '0', '2014-09-05 17:08:50', '2014-09-03 15:32:20');
INSERT INTO `t_city` VALUES ('806', '310115', '浦东新区', '4', '310000-310100', '10000-310100-310115', '浦东新区', '1', '0', '2014-09-05 17:08:50', '2014-09-03 15:32:24');
INSERT INTO `t_city` VALUES ('807', '310116', '金山区', '4', '310000-310100', '10000-310100-310116', '金山区', '1', '0', '2014-09-05 17:08:50', '2014-09-03 15:32:25');
INSERT INTO `t_city` VALUES ('808', '310117', '松江区', '4', '310000-310100', '10000-310100-310117', '松江区', '1', '0', '2014-09-05 17:08:50', '2014-09-03 15:32:25');
INSERT INTO `t_city` VALUES ('809', '310118', '青浦区', '4', '310000-310100', '10000-310100-310118', '青浦区', '1', '0', '2014-09-05 17:08:50', '2014-09-03 15:32:26');
INSERT INTO `t_city` VALUES ('810', '310120', '奉贤区', '4', '310000-310100', '10000-310100-310120', '奉贤区', '1', '0', '2014-09-05 17:08:50', '2014-09-03 15:32:27');
INSERT INTO `t_city` VALUES ('811', '310230', '崇明县', '4', '310000-310100', '10000-310100-310230', '崇明县', '1', '0', '2014-09-05 17:08:50',