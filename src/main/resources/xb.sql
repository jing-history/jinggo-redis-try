/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 10.2.16-MariaDB : Database - xboot
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`xboot` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `xboot`;

/*Table structure for table `qrtz_locks` */

DROP TABLE IF EXISTS `qrtz_locks`;

CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_locks` */

/*Table structure for table `t_department` */

DROP TABLE IF EXISTS `t_department`;

CREATE TABLE `t_department` (
  `id` varchar(255) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `sort_order` decimal(10,2) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `is_parent` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `t_department` */

insert  into `t_department`(`id`,`create_by`,`create_time`,`del_flag`,`update_by`,`update_time`,`parent_id`,`sort_order`,`status`,`title`,`is_parent`) values ('40327253309001728','','2018-08-10 20:58:27',0,'','2018-08-11 17:26:38','40322811096469504','3.00',-1,'人工智能',NULL),('40322777781112832','','2018-08-10 20:40:40',0,'','2018-08-11 00:03:06','0','1.00',0,'总部',''),('40322811096469504','','2018-08-10 20:40:48',0,'','2018-08-11 00:27:05','40322777781112832','1.00',0,'技术部',''),('40322852833988608','','2018-08-10 20:40:58',0,'','2018-08-11 01:29:42','40322811096469504','1.00',0,'研发中心',NULL),('40327204755738624','','2018-08-10 20:58:15',0,'','2018-08-10 22:02:15','40322811096469504','2.00',0,'大数据',NULL),('40343262766043136','','2018-08-10 22:02:04',0,'','2018-08-11 00:02:53','0','2.00',0,'成都分部',''),('40681289119961088','','2018-08-11 20:25:16',0,'','2018-08-11 22:47:48','40652270295060480','2.00',0,'VIP','\0'),('40344005342400512','','2018-08-10 22:05:01',0,'','2018-08-11 17:48:44','40343262766043136','2.00',0,'Vue',NULL),('40652270295060480','','2018-08-11 18:29:57',0,'','2018-08-12 18:45:01','0','3.00',0,'人事部',''),('40389030113710080','','2018-08-11 01:03:56',0,'','2018-08-11 17:50:04','40343262766043136','1.00',0,'JAVA','\0'),('40652338142121984',NULL,'2018-08-11 18:30:13',0,NULL,'2018-08-11 18:30:13','40652270295060480','1.00',0,'游客','\0'),('64517360274378752',NULL,NULL,0,NULL,NULL,'0','1.00',0,'广州分部',''),('64466243544944640',NULL,NULL,0,NULL,NULL,'40322811096469504','3.00',0,'Java开发组','\0'),('64517527232843776',NULL,NULL,0,NULL,NULL,'64517360274378752','2.00',0,'人力资源','\0'),('64517579984605184',NULL,NULL,0,NULL,NULL,'64517360274378752','2.00',0,'开发部','\0');

/*Table structure for table `t_dict` */

DROP TABLE IF EXISTS `t_dict`;

CREATE TABLE `t_dict` (
  `id` varchar(255) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `t_dict` */

insert  into `t_dict`(`id`,`create_by`,`create_time`,`del_flag`,`update_by`,`update_time`,`description`,`title`,`type`) values ('75135930788220928','admin','2018-11-14 22:15:43',0,'admin','2018-11-14 23:34:01','','性别','sex'),('75383353938808832','admin','2018-11-15 14:38:53',0,'admin','2018-11-15 17:49:24','','消息类型','message_type'),('75388696739713024','admin','2018-11-15 15:00:07',0,'admin','2018-11-15 15:00:07','','按钮权限类型','permission_type'),('75392985935646720','admin','2018-11-15 15:17:10',0,'admin','2018-11-15 15:17:10','','腾讯云对象存储区域','tencent_bucket_region');

/*Table structure for table `t_dict_data` */

DROP TABLE IF EXISTS `t_dict_data`;

CREATE TABLE `t_dict_data` (
  `id` varchar(255) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `dict_id` varchar(255) DEFAULT NULL,
  `sort_order` decimal(10,2) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `t_dict_data` */

insert  into `t_dict_data`(`id`,`create_by`,`create_time`,`del_flag`,`update_by`,`update_time`,`description`,`dict_id`,`sort_order`,`status`,`title`,`value`) values ('75158227712479232','admin','2018-11-14 23:44:19',0,'admin','2018-11-14 23:50:23','','75135930788220928','0.00',0,'男','1'),('75159254272577536','admin','2018-11-14 23:48:24',0,'admin','2018-11-14 23:50:14','','75135930788220928','1.00',0,'女','0'),('75159898425397248','admin','2018-11-14 23:50:57',0,'admin','2018-11-15 17:27:15','','75135930788220928','2.00',-1,'保密','2'),('75385648017575936','admin','2018-11-15 14:48:00',0,'admin','2018-11-15 14:48:41','','75383353938808832','0.00',0,'系统公告','0'),('75385706913992704','admin','2018-11-15 14:48:14',0,'admin','2018-11-15 14:48:44','','75383353938808832','1.00',0,'提醒','1'),('75385774274514944','admin','2018-11-15 14:48:30',0,'admin','2018-11-15 14:48:48','','75383353938808832','2.00',0,'私信','2'),('75390787835138048','admin','2018-11-15 15:08:26',0,'admin','2018-11-15 15:08:26','','75388696739713024','0.00',0,'查看操作(view)','view'),('75390886501945344','admin','2018-11-15 15:08:49',0,'admin','2018-11-15 15:08:57','','75388696739713024','1.00',0,'添加操作(add)','add'),('75390993939042304','admin','2018-11-15 15:09:15',0,'admin','2018-11-15 15:09:15','','75388696739713024','2.00',0,'编辑操作(edit)','edit'),('75391067532300288','admin','2018-11-15 15:09:32',0,'admin','2018-11-15 15:09:32','','75388696739713024','3.00',0,'删除操作(delete)','delete'),('75391126902673408','admin','2018-11-15 15:09:46',0,'admin','2018-11-15 15:09:46','','75388696739713024','4.00',0,'清空操作(clear)','clear'),('75391192883269632','admin','2018-11-15 15:10:02',0,'admin','2018-11-15 15:10:02','','75388696739713024','5.00',0,'启用操作(enable)','enable'),('75391251024711680','admin','2018-11-15 15:10:16',0,'admin','2018-11-15 15:10:16','','75388696739713024','6.00',0,'禁用操作(disable)','disable'),('75391297124306944','admin','2018-11-15 15:10:27',0,'admin','2018-11-15 15:10:27','','75388696739713024','7.00',0,'搜索操作(search)','search'),('75391343379091456','admin','2018-11-15 15:10:38',0,'admin','2018-11-15 15:10:38','','75388696739713024','8.00',0,'上传文件(upload)','upload'),('75391407526776832','admin','2018-11-15 15:10:53',0,'admin','2018-11-15 15:10:53','','75388696739713024','9.00',0,'导出操作(output)','output'),('75391475042488320','admin','2018-11-15 15:11:09',0,'admin','2018-11-15 15:11:09','','75388696739713024','10.00',0,'导入操作(input)','input'),('75391522182270976','admin','2018-11-15 15:11:21',0,'admin','2018-11-15 15:11:21','','75388696739713024','11.00',0,'分配权限(editPerm)','editPerm'),('75391576364290048','admin','2018-11-15 15:11:34',0,'admin','2018-11-15 15:11:34','','75388696739713024','12.00',0,'设为默认(setDefault)','setDefault'),('75391798033256448','admin','2018-11-15 15:12:26',0,'admin','2018-11-15 15:12:26','','75388696739713024','13.00',0,'其他操作(other)','other'),('75393605291741184','admin','2018-11-15 15:19:37',0,'admin','2018-11-15 15:19:37','','75392985935646720','0.00',0,'北京一区（华北）','ap-beijing-1'),('75393681254780928','admin','2018-11-15 15:19:55',0,'admin','2018-11-15 15:19:55','','75392985935646720','1.00',0,'北京','ap-beijing'),('75393767619694592','admin','2018-11-15 15:20:16',0,'admin','2018-11-15 15:20:16','','75392985935646720','2.00',0,'上海（华东）','ap-shanghai'),('75393851484803072','admin','2018-11-15 15:20:36',0,'admin','2018-11-15 15:20:36','','75392985935646720','3.00',0,'广州（华南）','ap-guangzhou'),('75393961887272960','admin','2018-11-15 15:21:02',0,'admin','2018-11-15 15:21:02','','75392985935646720','4.00',0,'成都（西南）','ap-chengdu'),('75394053969022976','admin','2018-11-15 15:21:24',0,'admin','2018-11-15 15:21:24','','75392985935646720','5.00',0,'重庆','ap-chongqing'),('75394122474590208','admin','2018-11-15 15:21:41',0,'admin','2018-11-15 15:21:41','','75392985935646720','6.00',0,'新加坡','ap-singapore'),('75394186202845184','admin','2018-11-15 15:21:56',0,'admin','2018-11-15 15:21:56','','75392985935646720','7.00',0,'香港','ap-hongkong'),('75394254255427584','admin','2018-11-15 15:22:12',0,'admin','2018-11-15 15:22:12','','75392985935646720','8.00',0,'多伦多','na-toronto'),('75394309125312512','admin','2018-11-15 15:22:25',0,'admin','2018-11-15 15:22:25','','75392985935646720','9.00',0,'法兰克福','eu-frankfurt'),('75394367044456448','admin','2018-11-15 15:22:39',0,'admin','2018-11-15 15:22:39','','75392985935646720','10.00',0,'孟买','ap-mumbai'),('75394448523005952','admin','2018-11-15 15:22:58',0,'admin','2018-11-15 15:22:58','','75392985935646720','11.00',0,'首尔','ap-seoul'),('75394604765024256','admin','2018-11-15 15:23:36',0,'admin','2018-11-15 15:23:36','','75392985935646720','12.00',0,'硅谷','na-siliconvalley'),('75394659299364864','admin','2018-11-15 15:23:49',0,'admin','2018-11-15 15:23:49','','75392985935646720','13.00',0,'弗吉尼亚','na-ashburn'),('75394705700950016','admin','2018-11-15 15:24:00',0,'admin','2018-11-15 15:24:00','','75392985935646720','14.00',0,'曼谷','ap-bangkok'),('75394759287377920','admin','2018-11-15 15:24:12',0,'admin','2018-11-15 15:24:12','','75392985935646720','15.00',0,'莫斯科','eu-moscow');

/*Table structure for table `t_log` */

DROP TABLE IF EXISTS `t_log`;

CREATE TABLE `t_log` (
  `id` varchar(255) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `cost_time` int(11) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `ip_info` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `request_param` varchar(255) DEFAULT NULL,
  `request_type` varchar(255) DEFAULT NULL,
  `request_url` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `t_log` */

/*Table structure for table `t_music_lrc` */

DROP TABLE IF EXISTS `t_music_lrc`;

CREATE TABLE `t_music_lrc` (
  `id` varchar(255) NOT NULL,
  `name` varchar(50) NOT NULL DEFAULT '',
  `code` varchar(50) NOT NULL DEFAULT '',
  `status` int(11) DEFAULT 0 COMMENT '0 启用 1 禁用',
  `content` longtext NOT NULL,
  `del_flag` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0 不删除 1 软删除',
  `create_by` varchar(32) NOT NULL DEFAULT '',
  `update_by` varchar(32) NOT NULL DEFAULT '',
  `create_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `update_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_music_lrc` */

insert  into `t_music_lrc`(`id`,`name`,`code`,`status`,`content`,`del_flag`,`create_by`,`update_by`,`create_time`,`update_time`) values ('1','咱们结婚吧','01',1,'[ti:0]\n[ar:0]\n[al:0]\n[offset:0]\n\n[00:00.00]咱们结婚吧\n[00:03.59]演唱：齐晨\n[00:04.71]\n[00:19.45]洁白的婚纱手捧着鲜花\n[00:23.94]美丽得像童话\n[00:28.11]想起那年初夏我为你牵挂\n[00:32.44]在一起就犯傻\n[00:34.91]丘比特轻轻飞过月光下\n[00:39.25]潘多拉她听到了回答\n[00:43.61]礼堂钟声在敲打幸福的密码\n[00:52.43]哦My Love咱们结婚吧\n[00:56.95]好想和你拥有一个家\n[01:00.87]这一生最美的梦啊\n[01:05.53]有你陪伴我同闯天涯\n[01:09.50]哦My Love咱们结婚吧\n[01:14.10]我会用一生去爱你的\n[01:18.50]我愿把一切都放下\n[01:22.99]给你幸福的家\n[01:27.40]\n[01:47.31]洁白的婚纱手捧着鲜花\n[01:51.78]美丽得像童话\n[01:56.03]想起那年初夏我为你牵挂\n[02:00.41]在一起就犯傻\n[02:02.60]丘比特轻轻飞过月光下\n[02:07.27]潘多拉她听到了回答\n[02:11.46]礼堂钟声在敲打幸福的密码\n[02:20.19]哦My Love咱们结婚吧\n[02:24.85]好想和你拥有一个家\n[02:28.77]这一生最美的梦啊\n[02:33.39]有你陪伴我同闯天涯\n[02:37.35]哦My Love咱们结婚吧\n[02:41.97]我会用一生去爱你的\n[02:46.31]我愿把一切都放下\n[02:50.80]给你幸福的家\n[02:54.88]\n[02:56.46]哦My Love咱们结婚吧\n[03:01.30]好想和你拥有一个家\n[03:05.21]这一生最美的梦啊\n[03:09.92]有你陪伴我同闯天涯\n[03:13.77]哦My Love咱们结婚吧\n[03:18.38]我会用一生去爱你的\n[03:22.66]我愿把一切都放下\n[03:27.35]给你幸福的家\n[03:31.01]\n',0,'JingGo','','2018-11-19 10:09:37','2018-11-22 09:27:24'),('77941631621271552','一事无成','02',0,'[ti:一事无成]\n[ar:周柏豪、郑融]\n[al:音享娱论]\n\n[00:01.07]一事无成\n[00:03.07]作词：黄伟文 作曲：陈光荣\n[00:05.96]演唱：周柏豪、郑融\n[00:08.09]\n[00:16.03]郑：或许这故事\n[00:17.50]亦是谁共你的故事吧\n[00:20.76]周：真的好想精于某事情　\n[00:23.38]好想好好的打拚\n[00:25.84]郑：可惜得到只有劣评　\n[00:28.25]没有半粒星\n[00:30.42]周：真的不想早给你定型　\n[00:32.94]笑我那么的拚命\n[00:35.29]郑：几年来毫无成绩\n[00:37.66]周：地位未有跃升\n[00:39.49]郑：高峰上不成　\n[00:41.56]唯盼爱情顺景\n[00:43.93]成就在平凡里　\n[00:46.32]那份温馨\n[00:48.41]周：我试着生性　\n[00:51.02]但求父母亲高兴\n[00:53.41]假如凡事都失败　\n[00:55.73]也许得爱恋先可以得胜\n[00:58.77]郑：Do do lu do do lu do do lu\n[01:02.50]周：即使险胜\n[01:03.61]郑：Do do do do do do\n[01:06.29]you mean everything to me\n[01:09.55]周：I Say　\n[01:10.68]终于找到些紧要事情\n[01:13.32]施展天生的本领\n[01:15.87]郑：可惜精于恋爱未曾令我变精英\n[01:20.18]周：给鼓掌的都非这类型\n[01:22.77]按世界标准鉴定\n[01:25.11]郑：感情从来如旁枝\n[01:27.62]周：办大事至正经\n[01:29.11]\n[01:29.43]郑：得不到掌声得到温馨\n[01:31.69]我爱这种清静\n[01:34.09]为什么　还提我　\n[01:36.42]事情未完别坐定\n[01:38.50]周：我试着生性　\n[01:40.91]但求父母亲高兴\n[01:43.31]假如凡事都失败　\n[01:45.63]也许得爱恋先可以得胜\n[01:48.55]郑：Listen to my feelings as always\n[01:50.79]listen to my feelings\n[01:52.06]周：都不算差劲\n[01:52.99]郑：I want to scream go for your dream\n[01:55.28]Ah ha ah ha ah ha\n[01:57.44]\n[02:04.11]周：飞不起个个也说我\n[02:05.90]脚又未踏实地\n[02:07.30]爱上你不过为逃避\n[02:09.76]郑：我也告诉你我实在是着地\n[02:12.08]难成大器但我爱得起　\n[02:14.24]Let’s do it\n[02:15.13]\n[02:17.17]周：真的好想精于某事情　\n[02:19.84]好想好好的打拚\n[02:22.26]可惜得到只有劣评　\n[02:24.77]而没有半粒星\n[02:26.79]真的不想早给你定型　\n[02:29.32]笑我那么的拚命\n[02:31.77]几年来毫无成绩　\n[02:34.04]地位未有跃升\n[02:35.85]郑：高峰上不成　\n[02:37.99]唯盼爱情顺景\n[02:40.40]成就在平凡里　那份温馨\n[02:44.75]周：已试着生性　\n[02:47.46]但求大众都尊敬\n[02:49.81]竟然凡事都失败　\n[02:52.25]也许得爱恋先可以得胜\n[02:55.68]\n[02:58.82]即使险胜\n[03:03.29]都不算差劲\n[03:05.09]\n',0,'jinggo','','2018-11-22 16:04:34','2018-11-22 16:10:16'),('78208941019893760','相依为命-陈小春','03',0,'[00:02.69]相依为命\n[00:05.89]演唱：陈小春\n[00:09.3]\n[00:22.82]旁人在 淡出终于只有你共我一起\n[00:30.7]仍然自问幸福虽说有阵时为你生气\n[00:37.49]其实以前和你互相不懂得死心塌地\n[00:44.48]直到共你渡过多灾世纪\n[00:50.63]即使身边世事再毫无道理 与你永远亦连在一起\n[00:58.53]你不放下我 我不放下你\n[01:01.82]我想确定每日挽着同样的手臂\n[01:06.3]不敢早死要来陪住你 我已试够别离并不很凄美\n[01:16.37]我还如何撇下你\n[01:24.56]\n[01:36.60]年华像细水冲走几个爱人与知己\n[01:43.93]抬头命运射灯光柱罩下来是我跟你\n[01:50.7]难道有人离去是想显出好光阴 有限\n[01:58.87]让我学会为你 贪生怕死\n[02:04.92]即使身边世事再毫无道理 与你永远亦连在一起\n[02:11.59]你不放下我 我不放下你\n[02:15.79]我想确定每日挽着同样的手臂\n[02:20.16]不敢早死要来陪住你 我已试够别离并不很凄美\n[02:30.57]见尽了 云涌风起 还怎么舍得放下你\n[02:37.73]\n[02:43.9]证明爱人又爱己 何以要那么悲壮才合理\n[02:52.25]即使身边世事再毫无道理 与你永远亦连在一起\n[02:59.71]你不放下我 我不放下你\n[03:03.5]我想确定每日挽着同样一双臂\n[03:08.61]不必挑选我们成大器\n[03:11.77]当我两个并无冒险的福气\n[03:18.40]见尽了 云涌风起 还怎么舍得放下你\n[03:27.75]我们仍珍惜这啖气\n[03:38.1]',0,'jinggo','','2018-11-23 09:46:45','2018-11-23 09:46:45'),('79429900603232256','Ed Sheeran-Shape of You','04',0,'[ti:Shape of You]\n[ar:Ed Sheeran]\n[al:Shape of You]\n[00:00.10]Shape of You (你的样子) (2018年公告牌音乐奖最佳电台榜歌曲获奖歌曲|第60届格莱美最佳流行表演获奖歌曲|2018年全英音乐奖最佳单曲提名歌曲|《QQ飞车手游》游戏背景歌曲) - Ed Sheeran (艾德·希兰)\n[00:01.38]Written by：Ed Sheeran/Steve Mac/Johnny McDaid\n[00:09.55]The club isn\'t the best place to find a lover^俱乐部不是找另一半的绝佳场所\n[00:11.99]So the bar is where I go^所以酒吧很适合我\n[00:15.00]Me and my friends at the table doing shots^我和朋友们百无聊赖的喝着酒\n[00:17.10]Drinking fast and then we talk slow^我们喝的很急 却漫不经心的聊着\n[00:19.79]You come over and start up a conversation with just me^你向我走来 开始跟我搭讪\n[00:22.31]And trust me I\'ll give it a chance now^相信我 我会给你机会的\n[00:24.53]Take my hand stop put Van The Man on the jukebox^牵起我的手 我们点首Van The Man的歌吧\n[00:27.35]And then we start to dance and now I\'m singing like^然后我们就可以翩然起舞了 此刻我的歌声像是\n[00:29.87]Girl you know I want your love^女孩 你知道我渴望你的爱\n[00:32.47]Your love was handmade for somebody like me^你的爱为我量身打造 专属于我\n[00:35.57]Come on now follow my lead^来吧 听我指挥吧\n[00:37.44]I may be crazy don\'t mind me^或许我有点疯狂 请不要介意\n[00:39.63]Say boy let\'s not talk too much^说小子 不必多说废话\n[00:42.51]Grab on my waist and put that body on me^将你的手搭上我的腰 贴近我吧\n[00:45.48]Come on now follow my lead^来吧 听我指挥吧\n[00:47.16]Come come on now follow my lead^来吧 来吧 听我指挥吧\n[00:50.84]I\'m in love with the shape of you^我爱上了你妙曼的姿态\n[00:53.34]We push and pull like a magnet do^缠绵之间 我们像是磁铁般相互吸引\n[00:55.90]Although my heart is falling too^尽管我的心早已为你沦陷\n[00:58.26]I\'m in love with your body^你的身体让我无比眷恋\n[01:00.86]Last night you were in my room^昨晚你在我房间过夜\n[01:03.30]And now my bedsheets smell like you^今晨床单上你的香气流连\n[01:05.31]Every day discovering something brand new^每一天似乎焕然一新\n[01:08.33]I\'m in love with your body^你的身体让我无比眷恋\n[01:09.99]Oh I oh I oh I oh I^噢 我无法自拔沦陷\n[01:13.35]I\'m in love with your body^你的身体让我无比眷恋\n[01:14.90]Oh I oh I oh I oh I^噢 我无法自拔沦陷\n[01:18.36]I\'m in love with your body^你的身体让我无比眷恋\n[01:20.02]Oh I oh I oh I oh I^噢 我无法自拔沦陷\n[01:23.33]I\'m in love with your body^你的身体让我无比眷恋\n[01:25.37]Every day discovering something brand new^每一天似乎焕然一新\n[01:28.39]I\'m in love with the shape of you^我爱上了你妙曼的姿态\n[01:29.97]One week in we let the story begin^一周时间 就让这爱情故事上演吧\n[01:31.96]We\'re going out on our first date^我们可以开始属于我们的初次约会\n[01:34.70]You and me are thrifty so go all you can eat^我们都很节俭 那就随你喜欢尽情挥霍一次\n[01:36.98]Fill up your bag and I fill up a plate^包包里塞满吃的 我可以装满餐盘\n[01:39.51]We talk for hours and hours about the sweet and the sour^这酸或甜足以让我们聊上几个小时\n[01:41.99]And how your family is doing okay^你的家人还好吗\n[01:44.55]Leave and get in a taxi then kiss in the backseat^坐上的士离开 我们在后座上热吻\n[01:46.94]Tell the driver make the radio play and I\'m singing like^让司机打开收音机 我的歌声像是\n[01:50.07]Girl you know I want your love^女孩 你知道我渴望你的爱\n[01:52.47]Your love was handmade for somebody like me^你的爱为我量身打造 专属于我\n[01:55.46]Come on now follow my lead^来吧 听我指挥吧\n[01:57.45]I may be crazy don\'t mind me^或许我有点疯狂 请不要介意\n[01:59.66]Say boy let\'s not talk too much^说小子 不必多说废话\n[02:02.56]Grab on my waist and put that body on me^将你的手搭上我的腰 贴近我吧\n[02:05.52]Come on now follow my lead^来吧 听我指挥吧\n[02:07.21]Come come on now follow my lead^来吧 来吧 听我指挥吧\n[02:10.82]I\'m in love with the shape of you^我爱上了你妙曼的姿态\n[02:13.31]We push and pull like a magnet do^缠绵之间 我们像是磁铁般相互吸引\n[02:15.84]Although my heart is falling too^尽管我的心早已为你沦陷\n[02:18.36]I\'m in love with your body^你的身体让我无比眷恋\n[02:20.73]And last night you were in my room^昨晚你在我房间过夜\n[02:23.35]And now my bedsheets smell like you^今晨床单上你的香气流连\n[02:25.30]Every day discovering something brand new^每一天似乎焕然一新\n[02:28.37]I\'m in love with your body^你的身体让我无比眷恋\n[02:29.97]Oh I oh I oh I oh I^噢 我无法自拔沦陷\n[02:33.25]I\'m in love with your body^你的身体让我无比眷恋\n[02:34.94]Oh I oh I oh I oh I^噢 我无法自拔沦陷\n[02:38.22]I\'m in love with your body^你的身体让我无比眷恋\n[02:39.87]Oh I oh I oh I oh I^噢 我无法自拔沦陷\n[02:43.29]I\'m in love with your body^你的身体让我无比眷恋\n[02:45.19]Every day discovering something brand new^每一天似乎焕然一新\n[02:48.38]I\'m in love with the shape of you^我爱上了你妙曼的姿态\n[02:50.10]Come on be my baby come on^来吧 宝贝来我身边\n[02:52.80]Come on be my baby come on^来吧 宝贝来我身边\n[02:55.22]Come on be my baby come on^来吧 宝贝来我身边\n[02:57.81]Come on be my baby come on^来吧 宝贝来我身边\n[03:00.23]Come on be my baby come on^来吧 宝贝来我身边\n[03:02.75]Come on be my baby come on^来吧 宝贝来我身边\n[03:05.20]Come on be my baby come on^来吧 宝贝来我身边\n[03:07.71]Come on be my baby come on^来吧 宝贝来我身边\n[03:10.85]I\'m in love with the shape of you^我爱上了你妙曼的姿态\n[03:13.37]We push and pull like a magnet do^缠绵之间 我们像是磁铁般相互吸引\n[03:15.88]Although my heart is falling too^尽管我的心早已为你沦陷\n[03:18.33]I\'m in love with your body^你的身体让我无比眷恋\n[03:20.93]Last night you were in my room^昨晚你在我房间过夜\n[03:23.35]And now my bedsheets smell like you^今晨床单上你的香气流连\n[03:25.32]Every day discovering something brand new^每一天似乎焕然一新\n[03:28.31]I\'m in love with your body^你的身体让我无比眷恋\n[03:30.25]Come on be my baby come on^来吧 宝贝来我身边\n[03:33.40]I\'m in love with your body^你的身体让我无比眷恋\n[03:35.36]Come on be my baby come on^来吧 宝贝来我身边\n[03:38.32]I\'m in love with your body^你的身体让我无比眷恋\n[03:40.25]Come on be my baby come on^来吧 宝贝来我身边\n[03:43.36]I\'m in love with your body^你的身体让我无比眷恋\n[03:45.19]Every day discovering something brand new^每一天似乎焕然一新\n[03:48.36]I\'m in love with the shape of you^我爱上了你妙曼的姿态\n',0,'jinggo','','2018-11-26 18:38:25','2018-11-26 18:38:25'),('80034989877497856','这一路走来_杨宗纬','05',0,'[00:01.00]这一路走来\n[00:03.02]作词：黄婷 作曲：Pan\n[00:04.40]演唱：杨宗纬\n[00:05.94]\n[00:19.36]从来不是他们刻划的那种样子\n[00:25.58]不是谁的王子\n[00:28.74]讲不出煽情的字\n[00:32.28]在喧嚣的王国\n[00:35.39]守护自己的寂寞\n[00:39.10]门外惊心动魄\n[00:41.94]门里我泰然自若\n[00:44.95]\n[00:46.47]这一路走来\n[00:47.37]说不上多辛苦\n[00:50.42]庆幸心里很清楚\n[00:53.11]是因为还有那么一点在乎\n[00:57.05]才执着这段旅途\n[00:59.78]这一路走来\n[01:01.01]还忍得住孤独\n[01:03.90]一个人聊胜于无\n[01:06.69]在滚滚浊世绝不把梦交出\n[01:10.54]尽管过程多残酷\n[01:13.74]\n[01:26.19]心偶尔酸酸的\n[01:28.90]渗出泪水咸咸的\n[01:32.61]总有某个时刻\n[01:35.59]碰触爱是暖暖的\n[01:38.34]\n[01:39.29]心里一直有你\n[01:42.21]为了你我不放弃\n[01:45.98]曲折忐忑崎岖\n[01:48.89]总有一天都抚平\n[01:53.00]\n[01:56.79]这一路走来\n[01:57.28]说不上多辛苦\n[02:00.11]庆幸心里很清楚\n[02:02.98]是因为还有那么一点在乎\n[02:06.76]才执着这段旅途\n[02:09.63]这一路走来\n[02:10.55]还忍得住孤独\n[02:13.47]一个人聊胜于无\n[02:16.37]在滚滚浊世绝不把梦交出\n[02:20.13]尽管过程多残酷\n[02:24.48]\n[02:29.62]越爱的越束手策\n[02:32.70]越痛的越铭心深刻\n[02:35.26]谁来了谁走了\n[02:36.84]谁在天地间 不舍\n[02:41.17]\n[02:44.81]这一路走来\n[02:45.54]难免有些糊涂\n[02:48.50]难免会把谁辜负\n[02:51.36]我们是漂浮沧海中的一粟\n[02:55.18]有什么不能让步\n[02:57.82]\n[02:58.13]这一路走来\n[02:58.98]什么都不算数\n[03:01.87]如果没你的祝福\n[03:04.64]一步又一步在人生中过渡\n[03:08.35]谁会懂谁的全部\n[03:11.67]\n[03:17.12]这一路走来 总最怀念\n[03:21.89]最初的坦白\n[03:26.70]\n[03:51.06]我这一路走来\n[03:53.67]扬起漫天的尘埃\n[03:57.94]\n',0,'jinggo','','2018-11-28 10:42:49','2018-11-28 10:42:49'),('80048296810254336','追光者','06',0,'[ti:追光者]\n[ar:岑宁儿]\n[al:追光者]\n[by:0]\n[offset:0]\n[00:00.00]追光者\n[00:00.22]词：唐恬\n[00:00.35]曲：马敬\n[00:00.48]编曲：黎偌天\n[00:25.06]如果说你是海上的烟火\n[00:29.57]我是浪花的泡沫\n[00:33.27]某一刻你的光照 亮了我\n[00:37.85]如果说你是遥远的星河\n[00:42.85]耀眼得让人想哭\n[00:48.22]我是追逐着你的眼眸\n[00:51.68]总在孤单时候眺望夜空\n[01:01.02]我可以跟在你身后\n[01:04.28]像影子追着光梦游\n[01:07.57]我可以等在这路口\n[01:10.96]不管你会不会经过\n[01:14.21]每当我为你抬起头\n[01:17.59]连眼泪都觉得自由\n[01:21.03]有的爱像阳光倾落\n[01:24.57]边拥有边失去着\n[01:41.10]如果说你是夏夜的萤火\n[01:46.03]孩子们为你唱歌\n[01:50.22]那么我是想要画你的手\n[01:54.54]你看我多么渺小一个我\n[01:59.63]因为你有梦可做\n[02:05.05]也许你不会为我停留\n[02:08.49]那就让我站在你的背后\n[02:14.27]我可以跟在你身后\n[02:17.50]像影子追着光梦游\n[02:20.77]我可以等在这路口\n[02:24.10]不管你会不会经过\n[02:27.42]每当我为你抬起头\n[02:30.92]连眼泪都觉得自由\n[02:34.51]有的爱像大雨滂沱\n[02:38.01]却依然相信彩虹\n[03:08.02]我可以跟在你身后\n[03:10.93]像影子追着光梦游\n[03:14.35]我可以等在这路口\n[03:17.63]不管你会不会经过\n[03:20.94]每当我为你抬起头\n[03:24.23]连眼泪都觉得自由\n[03:27.75]有的爱像大雨滂沱\n[03:31.23]却依然相信彩虹\n[03:36.56]\n',0,'jinggo','','2018-11-28 11:35:42','2018-11-28 11:35:42');

/*Table structure for table `t_permission` */

DROP TABLE IF EXISTS `t_permission`;

CREATE TABLE `t_permission` (
  `id` varchar(255) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `sort_order` decimal(10,2) DEFAULT NULL,
  `component` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `button_type` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `t_permission` */

insert  into `t_permission`(`id`,`create_by`,`create_time`,`del_flag`,`update_by`,`update_time`,`description`,`name`,`parent_id`,`type`,`sort_order`,`component`,`path`,`title`,`icon`,`level`,`button_type`,`status`,`url`) values ('5129710648430592','','2018-06-04 19:02:29',0,'','2018-08-09 15:25:01','','sys','',0,'1.00','Main','/form','系统管理','ios-settings',1,'',0,NULL),('5129710648430593','','2018-06-04 19:02:32',0,'','2018-08-13 15:15:33','','user-manage','5129710648430592',0,'1.10','sys/user-manage/userManage','user-manage','用户管理','md-person',2,'',0,''),('5129710648430594','','2018-06-04 19:02:35',0,'','2018-08-23 17:31:27','','role-manage','5129710648430592',0,'1.50','sys/role-manage/roleManage','role-manage','角色管理','md-contacts',2,'',0,''),('5129710648430595','','2018-06-04 19:02:37',0,'','2018-08-23 17:31:33','','menu-manage','5129710648430592',0,'1.60','sys/menu-manage/menuManage','menu-manage','菜单权限管理','md-menu',2,'',0,''),('41375330996326400','','2018-08-13 18:23:08',0,'','2018-08-15 17:13:23','','simple-table','41373430515240960',0,'4.10','xboot-vue-template/simple-table/simpleTable','simple-table','简单表格','ios-grid-outline',2,'',0,''),('15701400130424832','','2018-06-03 22:04:06',0,'','2018-08-13 15:15:47','','','5129710648430593',1,'1.11','','/xboot/user/admin/add','添加用户','',3,'add',0,''),('15701915807518720','','2018-06-03 22:06:09',0,'','2018-06-06 14:46:51','','','5129710648430593',1,'1.13','','/xboot/user/admin/disable/**','禁用用户','',3,'disable',0,NULL),('15708892205944832','','2018-06-03 22:33:52',0,'','2018-06-28 16:44:48','','','5129710648430593',1,'1.14','','/xboot/user/admin/enable/**','启用用户','',3,'enable',0,NULL),('16392452747300864','','2018-06-05 19:50:06',0,'','2018-08-13 18:15:39','','access','',0,'4.00','Main','/access','权限按钮测试页','md-lock',1,'',0,''),('16392767785668608','','2018-06-05 19:51:21',0,'','2018-08-13 18:15:46','','access_index','16392452747300864',0,'4.10','access/access','index','权限按钮测试页','md-lock',2,'',0,''),('16438800255291392','','2018-06-05 22:54:18',0,'','2018-08-13 18:15:51','','','16392767785668608',1,'4.11','','test-add','添加按钮测试','',3,'add',0,''),('16438962738434048','','2018-06-05 22:54:55',0,'','2018-08-13 18:16:29','','','16392767785668608',1,'4.12','','edit-test','编辑按钮测试','',3,'edit',0,''),('16439068543946752','','2018-06-05 22:55:20',0,'','2018-08-13 18:16:12','','','16392767785668608',1,'4.13','','delete-test','删除按钮测试','',3,'delete',0,''),('16678126574637056','','2018-06-06 14:45:16',0,'','2018-06-06 14:46:45','','','5129710648430593',1,'1.12','','/xboot/user/admin/edit','编辑用户','',3,'edit',0,NULL),('16678447719911424','','2018-06-06 14:46:32',0,'','2018-08-10 21:41:16','','','5129710648430593',1,'1.15','','/xboot/user/delByIds/**','删除用户','',3,'delete',0,''),('16687383932047360',NULL,'2018-06-06 15:22:03',0,NULL,'2018-06-06 15:22:03',NULL,NULL,'5129710648430594',1,'1.21',NULL,'/xboot/role/save','添加角色',NULL,3,'add',0,NULL),('16689632049631232',NULL,'2018-06-06 15:30:59',0,NULL,'2018-06-06 15:30:59',NULL,NULL,'5129710648430594',1,'1.22',NULL,'/xboot/role/edit','编辑角色',NULL,3,'edit',0,NULL),('16689745006432256','','2018-06-06 15:31:26',0,'','2018-08-10 21:41:23','','','5129710648430594',1,'1.23','','/xboot/role/delAllByIds/**','删除角色','',3,'delete',0,''),('16689883993083904',NULL,'2018-06-06 15:31:59',0,NULL,'2018-06-06 15:31:59',NULL,NULL,'5129710648430594',1,'1.24',NULL,'/xboot/role/editRolePerm/**','分配权限',NULL,3,'editPerm',0,NULL),('16690313745666048',NULL,'2018-06-06 15:33:41',0,NULL,'2018-06-06 15:33:41',NULL,NULL,'5129710648430594',1,'1.25',NULL,'/xboot/role/setDefault','设为默认角色',NULL,3,'setDefault',0,NULL),('16694861252005888',NULL,'2018-06-06 15:51:46',0,NULL,'2018-06-06 15:51:46',NULL,NULL,'5129710648430595',1,'1.31',NULL,'/xboot/permission/add','添加菜单',NULL,3,'add',0,NULL),('16695107491205120',NULL,'2018-06-06 15:52:44',0,NULL,'2018-06-06 15:52:44',NULL,NULL,'5129710648430595',1,'1.32',NULL,'/xboot/permission/edit','编辑菜单',NULL,3,'edit',0,NULL),('16695243126607872','','2018-06-06 15:53:17',0,'','2018-08-10 21:41:33','','','5129710648430595',1,'1.33','','/xboot/permission/delByIds/**','删除菜单','',3,'delete',0,''),('41371711400054784','','2018-08-13 18:08:45',0,'','2018-08-14 12:31:15','','actuator','39915540965232640',0,'2.30','sys/actuator/actuator','actuator','Actuator监控[付费]','logo-angular',2,'',0,''),('41370251991977984',NULL,'2018-08-13 18:02:57',0,NULL,'2018-08-13 18:02:57',NULL,'quartz-job','39915540965232640',0,'2.10','sys/quartz-manage/quartzManage','quartz-job','定时任务','md-time',2,'',0,NULL),('25014528525733888',NULL,'2018-06-29 14:51:09',0,NULL,'2018-06-29 14:51:09',NULL,NULL,'5129710648430593',1,'1.16',NULL,'upload','上传图片',NULL,3,'upload',0,NULL),('39915540965232640',NULL,'2018-08-09 17:42:28',0,NULL,'2018-08-09 17:42:28',NULL,'monitor',NULL,0,'2.00','Main','/monitor','系统监控','ios-analytics',1,NULL,0,NULL),('39916171171991552','','2018-08-09 17:44:57',0,'','2018-08-13 17:58:05','','druid','39915540965232640',0,'2.40','sys/monitor/monitor','druid','SQL监控','md-analytics',2,'',0,'http://localhost:8888/druid/login.html'),('39918482854252544','','2018-08-09 17:54:08',0,'','2018-08-13 17:58:02','','swagger','39915540965232640',0,'2.50','sys/monitor/monitor','swagger','接口文档','md-document',2,'',0,'http://127.0.0.1:8888/swagger-ui.html'),('40238597734928384',NULL,'2018-08-10 15:06:10',0,NULL,'2018-08-10 15:06:10',NULL,'department-manage','5129710648430592',0,'1.20','sys/department-manage/departmentManage','department-manage','部门管理','md-git-branch',2,'',0,NULL),('42082442672082944','','2018-08-15 17:12:57',0,'','2018-08-15 17:13:12','','new-window','41373430515240960',0,'4.50','xboot-vue-template/new-window/newWindow','new-window','新窗口操作[付费]','ios-browsers',2,'',0,''),('41373430515240960','','2018-08-13 18:15:35',0,'','2018-08-15 14:29:48','','xboot-vue-template','',0,'3.00','Main','/xboot-vue-template','前端Vue模版','ios-albums',1,'',0,''),('41363147411427328','','2018-08-13 17:34:43',0,'','2018-08-20 20:05:21','','log-manage','39915540965232640',0,'2.20','sys/log-manage/logManage','log-manage','操作日志管理','md-list-box',2,'',0,''),('41363537456533504','','2018-08-13 17:36:16',0,'','2018-08-13 17:56:11','','','41363147411427328',1,'2.11','','/xboot/log/delByIds/**','删除日志','',3,'delete',0,''),('41364927394353152','','2018-08-13 17:41:48',0,'','2018-08-13 17:56:17','','','41363147411427328',1,'2.12','','/xboot/log/delAll','清空日志','',3,'undefined',0,''),('41376192166629376','','2018-08-13 18:26:33',0,'','2018-08-15 17:13:34','','search-table','41373430515240960',0,'4.20','xboot-vue-template/search-table/searchTable','search-table','搜索表格[付费]','md-grid',2,'',0,''),('41377034236071936','','2018-08-13 18:29:54',0,'','2018-08-15 17:13:40','','complex-table','41373430515240960',0,'4.30','xboot-vue-template/complex-table/complexTable','complex-table','复杂表格[付费]','ios-grid',2,'',0,''),('41378916912336896','','2018-08-13 18:37:23',0,'','2018-08-13 22:20:19','','tree','41373430515240960',0,'4.40','xboot-vue-template/tree/tree','tree','树形结构[付费]','ios-git-network',2,'',0,''),('41469219249852416',NULL,'2018-08-14 00:36:13',0,NULL,'2018-08-14 00:36:13',NULL,'','41371711400054784',1,'2.31','','无','查看数据','',3,'view',0,NULL),('42087054753927168','','2018-08-15 17:31:16',0,'','2018-08-15 17:31:27','','html-edit','41373430515240960',0,'4.60','xboot-vue-template/html-edit/htmlEdit','html-edit','富文本编辑[付费]','ios-create',2,'',0,''),('43117268627886080','','2018-08-18 13:44:58',0,'','2018-08-18 20:55:04','','message-manage','5129710648430592',0,'1.30','sys/message-manage/messageManage','message-manage','消息管理[付费]','md-mail',2,'',0,''),('44986029924421632','','2018-08-23 17:30:46',0,'','2018-08-23 17:31:02','','social-manage','5129710648430592',0,'1.40','sys/social-manage/socialManage','social-manage','社交账号绑定[付费]','md-share',2,'',0,''),('45069342940860416','','2018-08-23 23:01:49',0,'','2018-08-24 10:01:09','','','44986029924421632',1,'1.42','','无','查看社交账号数据','',3,'view',0,''),('45235228800716800',NULL,'2018-08-24 10:01:00',0,NULL,'2018-08-24 10:01:00',NULL,'','44986029924421632',1,'1.41','','/xboot/relate/delByIds','删除解绑','',3,'delete',0,NULL),('45235621697949696',NULL,'2018-08-24 10:02:33',0,NULL,'2018-08-24 10:02:33',NULL,'','40238597734928384',1,'1.21','','/xboot/department/add','添加部门','',3,'add',0,NULL),('45235787867885568',NULL,'2018-08-24 10:03:13',0,NULL,'2018-08-24 10:03:13',NULL,'','40238597734928384',1,'1.22','','/xboot/department/edit','编辑部门','',3,'edit',0,NULL),('45235939278065664',NULL,'2018-08-24 10:03:49',0,NULL,'2018-08-24 10:03:49',NULL,'','40238597734928384',1,'1.23','','/xboot/department/delByIds/*','删除部门','',3,'delete',0,NULL),('45236734832676864',NULL,'2018-08-24 10:06:59',0,NULL,'2018-08-24 10:06:59',NULL,'','43117268627886080',1,'1.31','','/xboot/message/add','添加消息','',3,'add',0,NULL),('45237010692050944',NULL,'2018-08-24 10:08:04',0,NULL,'2018-08-24 10:08:04',NULL,'','43117268627886080',1,'1.32','','/xboot/message/edit','编辑消息','',3,'edit',0,NULL),('45237170029465600',NULL,'2018-08-24 10:08:42',0,NULL,'2018-08-24 10:08:42',NULL,'','43117268627886080',1,'1.33','','/xboot/message/delByIds/*','删除消息','',3,'delete',0,NULL),('45264987354042368',NULL,'2018-08-24 11:59:14',0,NULL,'2018-08-24 11:59:14',NULL,'','41370251991977984',1,'2.11','','/xboot/quartzJob/add','添加定时任务','',3,'add',0,NULL),('45265487029866496',NULL,'2018-08-24 12:01:14',0,NULL,'2018-08-24 12:01:14',NULL,'','41370251991977984',1,'2.12','','/xboot/quartzJob/edit','编辑定时任务','',3,'edit',0,NULL),('45265762415284224','','2018-08-24 12:02:19',0,'','2018-08-24 12:03:00','','','41370251991977984',1,'2.13','','/xboot/quartzJob/pause','暂停定时任务','',3,'disable',0,''),('45265886315024384',NULL,'2018-08-24 12:02:49',0,NULL,'2018-08-24 12:02:49',NULL,'','41370251991977984',1,'2.14','','/xboot/quartzJob/resume','恢复定时任务','',3,'enable',0,NULL),('45266070000373760',NULL,'2018-08-24 12:03:33',0,NULL,'2018-08-24 12:03:33',NULL,'','41370251991977984',1,'2.15','','/xboot/quartzJob/delByIds/*','删除定时任务','',3,'delete',0,NULL),('64637261769084928',NULL,'2018-10-18 10:58:28',0,NULL,'2018-10-18 10:58:29',NULL,'mine',NULL,0,'5.00','Main','/mine','我的空间','md-school',1,NULL,0,NULL),('64650054060740608','',NULL,0,'',NULL,'','time-manage','64637261769084928',0,'5.10','sys/time-manage/timeManage','time-manage','时间轴管理','md-time',2,'',0,'http://love.jinggo.wang'),('65235454873571328',NULL,NULL,0,NULL,NULL,NULL,'income-mange','64637261769084928',0,'5.20','sys/role-manage/roleManage','income-mange','财务管理','ios-cart',2,'',0,NULL),('70008805882073088','',NULL,0,'',NULL,'','music lrc','64637261769084928',0,'5.30','sys/music-manage/musicManage','music-manage','歌曲管理','md-musical-note',2,'',0,'http://love.jinggo.wang'),('77609489301245952',NULL,NULL,0,NULL,NULL,NULL,'dict','5129710648430592',0,'1.80','sys/dict-manage/dictManage','dict','数据字典管理','md-bookmarks',2,'',0,NULL),('77609631882416128',NULL,NULL,0,NULL,NULL,NULL,'','77609489301245952',1,'1.81','','/xboot/dict/add*','添加字典','',3,'add',0,NULL),('77610709332004864',NULL,NULL,0,NULL,NULL,NULL,'','77609489301245952',1,'1.82','','/xboot/dict/edit*','编辑字典','',3,'edit',0,NULL),('77610815087185920',NULL,NULL,0,NULL,NULL,NULL,'','77609489301245952',1,'1.83','','/xboot/dict/delByIds/**','删除字典','',3,'delete',0,NULL),('77610974017753088',NULL,NULL,0,NULL,NULL,NULL,'','77609489301245952',1,'1.84','','/xboot/dictData/add*','添加字典数据','',3,'add',0,NULL),('77611101864333312',NULL,NULL,0,NULL,NULL,NULL,'','77609489301245952',1,'1.85','','/xboot/dictData/edit*','编辑字典数据','',3,'edit',0,NULL),('77611536582971392',NULL,NULL,0,NULL,NULL,NULL,'','77609489301245952',1,'1.86','','/xboot/dictData/delByIds/**','删除字典数据','',3,'delete',0,NULL);

/*Table structure for table `t_quartz_job` */

DROP TABLE IF EXISTS `t_quartz_job`;

CREATE TABLE `t_quartz_job` (
  `id` varchar(255) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `cron_expression` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `job_class_name` varchar(255) DEFAULT NULL,
  `parameter` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `t_quartz_job` */

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` varchar(255) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `default_role` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `data_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`create_by`,`create_time`,`update_by`,`update_time`,`name`,`del_flag`,`default_role`,`description`,`data_type`) values ('496138616573952','','2018-04-22 23:03:49','','2018-08-12 16:14:31','ROLE_ADMIN',0,NULL,'超级管理员 拥有所有权限',NULL),('496138616573953','','2018-05-02 21:40:03','','2018-08-16 13:33:15','ROLE_USER',0,'','普通注册用户 路过看看',NULL),('16457350655250432','','2018-06-06 00:08:00','','2018-08-16 13:33:30','ROLE_TEST',0,NULL,'测试权限按钮显示',NULL),('65008196867067904',NULL,'2018-10-18 10:18:24',NULL,'2018-10-18 10:18:22','ROLE_MINE',0,NULL,'我的权限',NULL);

/*Table structure for table `t_role_permission` */

DROP TABLE IF EXISTS `t_role_permission`;

CREATE TABLE `t_role_permission` (
  `id` varchar(255) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `permission_id` varchar(255) DEFAULT NULL,
  `role_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `t_role_permission` */

insert  into `t_role_permission`(`id`,`create_by`,`create_time`,`del_flag`,`update_by`,`update_time`,`permission_id`,`role_id`) values ('16457624555884544',NULL,'2018-06-06 00:09:04',0,NULL,'2018-06-06 00:09:04','16392452747300864','16457350655250432'),('16457624597827584',NULL,'2018-06-06 00:09:04',0,NULL,'2018-06-06 00:09:04','16392767785668608','16457350655250432'),('16457624643964928',NULL,'2018-06-06 00:09:04',0,NULL,'2018-06-06 00:09:04','16439068543946752','16457350655250432'),('65235584024580097',NULL,NULL,0,NULL,NULL,'16392452747300864','496138616573952'),('65235583710007296',NULL,NULL,0,NULL,NULL,'42087054753927168','496138616573952'),('65235583428988929',NULL,NULL,0,NULL,NULL,'42082442672082944','496138616573952'),('65235583185719296',NULL,NULL,0,NULL,NULL,'41378916912336896','496138616573952'),('65235582992781313',NULL,NULL,0,NULL,NULL,'41377034236071936','496138616573952'),('65235582804037633',NULL,NULL,0,NULL,NULL,'41376192166629376','496138616573952'),('65235582594322433',NULL,NULL,0,NULL,NULL,'41375330996326400','496138616573952'),('65235582367830017',NULL,NULL,0,NULL,NULL,'41373430515240960','496138616573952'),('65235582137143296',NULL,NULL,0,NULL,NULL,'39918482854252544','496138616573952'),('65235581927428097',NULL,NULL,0,NULL,NULL,'39916171171991552','496138616573952'),('65235581696741376',NULL,NULL,0,NULL,NULL,'41469219249852416','496138616573952'),('65235581315059712',NULL,NULL,0,NULL,NULL,'41371711400054784','496138616573952'),('65235581025652737',NULL,NULL,0,NULL,NULL,'41364927394353152','496138616573952'),('65235580643971073',NULL,NULL,0,NULL,NULL,'41363537456533504','496138616573952'),('65235580354564097',NULL,NULL,0,NULL,NULL,'41363147411427328','496138616573952'),('65235580052574209',NULL,NULL,0,NULL,NULL,'45266070000373760','496138616573952'),('44986333730443264',NULL,'2018-08-23 17:31:58',0,NULL,'2018-08-23 17:31:58','16438800255291392','496138616573953'),('44986333738831872',NULL,'2018-08-23 17:31:58',0,NULL,'2018-08-23 17:31:58','16438962738434048','496138616573953'),('44986333747220480',NULL,'2018-08-23 17:31:58',0,NULL,'2018-08-23 17:31:58','16439068543946752','496138616573953'),('65235579742195712',NULL,NULL,0,NULL,NULL,'45265886315024384','496138616573952'),('65235579549257729',NULL,NULL,0,NULL,NULL,'45265762415284224','496138616573952'),('65235579364708353',NULL,NULL,0,NULL,NULL,'45265487029866496','496138616573952'),('65235579180158977',NULL,NULL,0,NULL,NULL,'45264987354042368','496138616573952'),('65235578991415297',NULL,NULL,0,NULL,NULL,'41370251991977984','496138616573952'),('65235578781700097',NULL,NULL,0,NULL,NULL,'39915540965232640','496138616573952'),('65235578571984897',NULL,NULL,0,NULL,NULL,'16695243126607872','496138616573952'),('65235578295160833',NULL,NULL,0,NULL,NULL,'16695107491205120','496138616573952'),('65235578106417153',NULL,NULL,0,NULL,NULL,'16694861252005888','496138616573952'),('65235577896701952',NULL,NULL,0,NULL,NULL,'5129710648430595','496138616573952'),('65235577666015233',NULL,NULL,0,NULL,NULL,'16690313745666048','496138616573952'),('44986333717860352',NULL,'2018-08-23 17:31:58',0,NULL,'2018-08-23 17:31:58','16392767785668608','496138616573953'),('44986333701083136',NULL,'2018-08-23 17:31:58',0,NULL,'2018-08-23 17:31:58','16392452747300864','496138616573953'),('44986333692694528',NULL,'2018-08-23 17:31:58',0,NULL,'2018-08-23 17:31:58','42087054753927168','496138616573953'),('44986333684305920',NULL,'2018-08-23 17:31:58',0,NULL,'2018-08-23 17:31:58','42082442672082944','496138616573953'),('44986333675917312',NULL,'2018-08-23 17:31:58',0,NULL,'2018-08-23 17:31:58','41378916912336896','496138616573953'),('44986333671723008',NULL,'2018-08-23 17:31:58',0,NULL,'2018-08-23 17:31:58','41377034236071936','496138616573953'),('44986333659140096',NULL,'2018-08-23 17:31:58',0,NULL,'2018-08-23 17:31:58','41376192166629376','496138616573953'),('44986333650751488',NULL,'2018-08-23 17:31:58',0,NULL,'2018-08-23 17:31:58','41375330996326400','496138616573953'),('44986333629779968',NULL,'2018-08-23 17:31:58',0,NULL,'2018-08-23 17:31:58','41373430515240960','496138616573953'),('44986333625585664',NULL,'2018-08-23 17:31:58',0,NULL,'2018-08-23 17:31:58','39918482854252544','496138616573953'),('44986333617197056',NULL,'2018-08-23 17:31:58',0,NULL,'2018-08-23 17:31:58','39916171171991552','496138616573953'),('44986333608808448',NULL,'2018-08-23 17:31:58',0,NULL,'2018-08-23 17:31:58','41371711400054784','496138616573953'),('44986333604614144',NULL,'2018-08-23 17:31:58',0,NULL,'2018-08-23 17:31:58','41363147411427328','496138616573953'),('65235577334665217',NULL,NULL,0,NULL,NULL,'16689883993083904','496138616573952'),('44986333592031232',NULL,'2018-08-23 17:31:58',0,NULL,'2018-08-23 17:31:58','41370251991977984','496138616573953'),('44986333583642624',NULL,'2018-08-23 17:31:58',0,NULL,'2018-08-23 17:31:58','39915540965232640','496138616573953'),('44986333550088192',NULL,'2018-08-23 17:31:58',0,NULL,'2018-08-23 17:31:58','5129710648430595','496138616573953'),('65235576898457601',NULL,NULL,0,NULL,NULL,'16689745006432256','496138616573952'),('65235576684548097',NULL,NULL,0,NULL,NULL,'16689632049631232','496138616573952'),('65235576235757568',NULL,NULL,0,NULL,NULL,'16687383932047360','496138616573952'),('65235575992487937',NULL,NULL,0,NULL,NULL,'5129710648430594','496138616573952'),('65235575686303745',NULL,NULL,0,NULL,NULL,'45069342940860416','496138616573952'),('65235575489171457',NULL,NULL,0,NULL,NULL,'45235228800716800','496138616573952'),('65235575300427777',NULL,NULL,0,NULL,NULL,'44986029924421632','496138616573952'),('65235575090712577',NULL,NULL,0,NULL,NULL,'45237170029465600','496138616573952'),('65235574880997377',NULL,NULL,0,NULL,NULL,'45237010692050944','496138616573952'),('44986333541699584',NULL,'2018-08-23 17:31:58',0,NULL,'2018-08-23 17:31:58','5129710648430594','496138616573953'),('44986333533310976',NULL,'2018-08-23 17:31:58',0,NULL,'2018-08-23 17:31:58','44986029924421632','496138616573953'),('44986333524922368',NULL,'2018-08-23 17:31:58',0,NULL,'2018-08-23 17:31:58','43117268627886080','496138616573953'),('44986333508145152',NULL,'2018-08-23 17:31:58',0,NULL,'2018-08-23 17:31:58','40238597734928384','496138616573953'),('44986333482979328',NULL,'2018-08-23 17:31:58',0,NULL,'2018-08-23 17:31:58','5129710648430593','496138616573953'),('44986333470396416',NULL,'2018-08-23 17:31:58',0,NULL,'2018-08-23 17:31:58','5129710648430592','496138616573953'),('65235574650310657',NULL,NULL,0,NULL,NULL,'45236734832676864','496138616573952'),('65235574323154944',NULL,NULL,0,NULL,NULL,'43117268627886080','496138616573952'),('65235574000193537',NULL,NULL,0,NULL,NULL,'45235939278065664','496138616573952'),('65235573643677697',NULL,NULL,0,NULL,NULL,'45235787867885568','496138616573952'),('65235573312327681',NULL,NULL,0,NULL,NULL,'45235621697949696','496138616573952'),('65235572918063104',NULL,NULL,0,NULL,NULL,'40238597734928384','496138616573952'),('65235572695764992',NULL,NULL,0,NULL,NULL,'25014528525733888','496138616573952'),('77613569679560705',NULL,NULL,0,NULL,NULL,'41363537456533504','65008196867067904'),('77613569402736641',NULL,NULL,0,NULL,NULL,'41363147411427328','65008196867067904'),('77613569209798657',NULL,NULL,0,NULL,NULL,'45266070000373760','65008196867067904'),('77613568941363201',NULL,NULL,0,NULL,NULL,'45265886315024384','65008196867067904'),('77613568580653057',NULL,NULL,0,NULL,NULL,'45265762415284224','65008196867067904'),('77613568333189121',NULL,NULL,0,NULL,NULL,'45265487029866496','65008196867067904'),('77613568094113793',NULL,NULL,0,NULL,NULL,'45264987354042368','65008196867067904'),('77613567825678337',NULL,NULL,0,NULL,NULL,'41370251991977984','65008196867067904'),('77613567578214401',NULL,NULL,0,NULL,NULL,'39915540965232640','65008196867067904'),('77613567221698561',NULL,NULL,0,NULL,NULL,'77611536582971392','65008196867067904'),('77613566911320065',NULL,NULL,0,NULL,NULL,'77611101864333312','65008196867067904'),('77613566642884609',NULL,NULL,0,NULL,NULL,'77610974017753088','65008196867067904'),('77613566240231425',NULL,NULL,0,NULL,NULL,'77610815087185920','65008196867067904'),('77613565938241537',NULL,NULL,0,NULL,NULL,'77610709332004864','65008196867067904'),('77613565644640257',NULL,NULL,0,NULL,NULL,'77609631882416128','65008196867067904'),('77613565246181377',NULL,NULL,0,NULL,NULL,'77609489301245952','65008196867067904'),('77613564973551617',NULL,NULL,0,NULL,NULL,'16695243126607872','65008196867067904'),('77613564700921857',NULL,NULL,0,NULL,NULL,'16695107491205120','65008196867067904'),('77613564524761089',NULL,NULL,0,NULL,NULL,'16694861252005888','65008196867067904'),('77613564302462977',NULL,NULL,0,NULL,NULL,'5129710648430595','65008196867067904'),('77613564029833217',NULL,NULL,0,NULL,NULL,'16690313745666048','65008196867067904'),('77613563841089537',NULL,NULL,0,NULL,NULL,'16689883993083904','65008196867067904'),('77613563505545217',NULL,NULL,0,NULL,NULL,'16689745006432256','65008196867067904'),('77613563258081281',NULL,NULL,0,NULL,NULL,'16689632049631232','65008196867067904'),('77613563086114816',NULL,NULL,0,NULL,NULL,'16687383932047360','65008196867067904'),('77613562901565441',NULL,NULL,0,NULL,NULL,'5129710648430594','65008196867067904'),('77613562712821761',NULL,NULL,0,NULL,NULL,'25014528525733888','65008196867067904'),('77613562532466688',NULL,NULL,0,NULL,NULL,'16678447719911424','65008196867067904'),('77613562331140097',NULL,NULL,0,NULL,NULL,'15708892205944832','65008196867067904'),('77613562100453377',NULL,NULL,0,NULL,NULL,'15701915807518720','65008196867067904'),('77613561878155265',NULL,NULL,0,NULL,NULL,'16678126574637056','65008196867067904'),('65235572238585857',NULL,NULL,0,NULL,NULL,'16678447719911424','496138616573952'),('65235572007899137',NULL,NULL,0,NULL,NULL,'15708892205944832','496138616573952'),('65235571634606081',NULL,NULL,0,NULL,NULL,'15701915807518720','496138616573952'),('65235571315838977',NULL,NULL,0,NULL,NULL,'16678126574637056','496138616573952'),('65235570976100353',NULL,NULL,0,NULL,NULL,'15701400130424832','496138616573952'),('65235570653138944',NULL,NULL,0,NULL,NULL,'5129710648430593','496138616573952'),('65235570120462336',NULL,NULL,0,NULL,NULL,'5129710648430592','496138616573952'),('77613561681022977',NULL,NULL,0,NULL,NULL,'15701400130424832','65008196867067904'),('77613561492279297',NULL,NULL,0,NULL,NULL,'5129710648430593','65008196867067904'),('77613561156734976',NULL,NULL,0,NULL,NULL,'5129710648430592','65008196867067904'),('65235584217518081',NULL,NULL,0,NULL,NULL,'16392767785668608','496138616573952'),('65235584418844673',NULL,NULL,0,NULL,NULL,'16438800255291392','496138616573952'),('65235584712445952',NULL,NULL,0,NULL,NULL,'16438962738434048','496138616573952'),('65235585056378881',NULL,NULL,0,NULL,NULL,'16439068543946752','496138616573952'),('65235585387728896',NULL,NULL,0,NULL,NULL,'64637261769084928','496138616573952'),('65235585614221313',NULL,NULL,0,NULL,NULL,'64650054060740608','496138616573952'),('65235585823936513',NULL,NULL,0,NULL,NULL,'65235454873571328','496138616573952'),('77613569885081601',NULL,NULL,0,NULL,NULL,'41364927394353152','65008196867067904'),('77613570082213888',NULL,NULL,0,NULL,NULL,'39916171171991552','65008196867067904'),('77613570367426560',NULL,NULL,0,NULL,NULL,'39918482854252544','65008196867067904'),('77613570782662657',NULL,NULL,0,NULL,NULL,'64637261769084928','65008196867067904'),('77613571046903809',NULL,NULL,0,NULL,NULL,'64650054060740608','65008196867067904'),('77613571395031041',NULL,NULL,0,NULL,NULL,'65235454873571328','65008196867067904'),('77613571625717761',NULL,NULL,0,NULL,NULL,'70008805882073088','65008196867067904');

/*Table structure for table `t_time_axis` */

DROP TABLE IF EXISTS `t_time_axis`;

CREATE TABLE `t_time_axis` (
  `id` varchar(255) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `content` varchar(6000) DEFAULT NULL,
  `figureImg` varchar(255) DEFAULT NULL,
  `figureMsg` varchar(2000) DEFAULT NULL,
  `figcaption` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `t_time_axis` */

insert  into `t_time_axis`(`id`,`create_by`,`create_time`,`update_by`,`update_time`,`title`,`del_flag`,`content`,`figureImg`,`figureMsg`,`figcaption`) values ('67512290575912957','jinggo','2018-05-13 01:13:38','jinggo','2018-10-29 15:15:46','今天是母亲节',0,'第一条后台发布的内容,很巧，今天刚好是母亲节，宝宝也准备当妈妈了，我真是幸福，希望妈妈们都能健健康康，开心快乐。','http://pd2kdimru.bkt.clouddn.com/6071efaeedff429f9e9c9f1578b2c51b.jpg','母亲节快乐','时光飞逝，希望能记录下宝宝的点点滴滴！！！'),('67512290575912960','jinggo','2018-05-17 10:27:04','jinggo','2018-11-14 18:27:05','下班大暴雨',0,'下班前来了个大暴雨，想我宝宝和贝贝了，赶紧收拾好回去陪他们 O(∩_∩)O~','http://pd2kdimru.bkt.clouddn.com/e48ffc1f5d1b4053a1a45ed78b372664.jpg','香格里拉','妹子，你在想啥呢？'),('67513177826398208','jinggo','2018-05-20 03:49:01','jinggo','2018-10-24 22:57:47','520（我爱你）',0,'全世界都知道我爱你！！！','http://pd2kdimru.bkt.clouddn.com/b3f48f66a28749ddb179c4613fb290fe.jpg','520','我陪你走过的路你不能忘  (O^~^O) '),('67514245742333952','jinggo','2018-06-01 07:37:22','admin','2018-10-25 09:14:25','贝贝，六一儿童节快乐',0,'小贝贝，虽然你还在妈妈的肚子里面，不能感受到节日的气氛，但是很快你就会和这个世界见面了，爸爸妈妈希望你健健康康。','http://pd2kdimru.bkt.clouddn.com/0f93bb9acb054405bb441b9edabdd9a0.png','六一快乐','妈妈也是个小宝宝，节日快乐！！！'),('67514575364296704','jinggo','2018-07-02 15:17:10','jinggo','2018-10-24 22:57:03','我的生日',0,'happy birthday~~~ 生日快乐，很高兴收到宝宝的礼物，谢谢有宝宝的陪伴！！！','http://pd2kdimru.bkt.clouddn.com/7db59ffaf66d4377a07b57ba888997a6.jpg','大礼物','很酷的耳机，爱你么么哒！！！'),('76775600643117056','jinggo','2018-11-19 10:51:10','jinggo','2018-11-19 10:51:32','折腾的小贝贝',0,'贝啊，你能不这么折腾你爸妈吗？天天在闹的，心累。。。','http://pd2kdimru.bkt.clouddn.com/3e42b84075a8401dbdfc46a5c9a3f35f.jpg','萌贝贝','是你缺乏安全感呢？还是爸妈带你的方式有问题？'),('68981222042243072','jinggo','2018-10-08 17:00:00',NULL,NULL,'我的小天使',0,'很激动，很幸福，宝宝孕育10个月的小宝宝到来了。','http://pd2kdimru.bkt.clouddn.com/b42458b9613946b3b4cea727ec711047.jpg','小天使','欢迎小天使的到来，希望你健健康康，老婆辛苦了！！！'),('68988553408811008','jinggo','2018-10-10 10:10:10','jinggo','2018-10-29 15:15:35','结婚一周年纪念日',0,'老婆辛苦了，因为小贝贝的到来，一周年纪念日我只能呆在医院陪你，这也是个特殊的纪念日，因为我们上天送给我们一份独一无二的礼物 ...','http://pd2kdimru.bkt.clouddn.com/53119f993b1c44679f0dc417003ef601.jpg','一周年纪念日','我们相遇，于是有了爱情。我们相恋，于是有了婚姻。我们结婚，于是有了家庭。老婆，结婚纪念日快乐，让我们携手共创美好生活！'),('69231891370217472','jinggo','2018-10-29 15:15:10',NULL,NULL,'辣么萌！！',0,'贝贝，你的小眼睛咋瞪的这么大呢，太有喜感了。','http://pd2kdimru.bkt.clouddn.com/020d3cf53b8547c7b7d6dd447fa0d4e3.jpg','小贝贝','不知道咋的，老爸看到这个照片就好想笑^_^'),('69602051423735808','jinggo','2018-10-30 15:46:03','jinggo','2018-10-30 18:35:59','贝贝表情包',0,'啥都不说，mark 一下<(￣3￣)>','http://pd2kdimru.bkt.clouddn.com/4f0d53eb2d81473dbfb4ce9774a5a6de.jpg','表情多图','我的精神粮食，哇哈哈 ^_^'),('70686225421307904','jinggo','2018-11-02 15:34:10','jinggo','2018-11-03 15:41:00','我会笑了^_^',0,'本宝宝拉完喝饱之后很满足 ...','http://pd2kdimru.bkt.clouddn.com/9bf3eb3a666b414dbbf4b7a8a463f92d.jpg','我可爱吧','看我嘚瑟满意的笑容！！！'),('72156245728956416','jinggo','2018-11-06 16:55:30',NULL,NULL,'小家伙准备满月了',0,'贝贝，你终于有大名了，哈哈','http://pd2kdimru.bkt.clouddn.com/bb65b25dd2d04f75a0141bca6580a37f.jpg','吃饱喝足还不睡！！！','希望小家伙健健康康。。。'),('74326356082561024','jinggo','2018-11-10 11:11:11','jinggo','2018-11-26 16:01:13','华宝宝生日快乐',0,'祝年轻漂亮的华宝宝生日快乐，健健康康！！！','http://pd2kdimru.bkt.clouddn.com/9014a2fb53124affb5f5a27c1cf2ac06.jpg','最美girl','请记住有个人永远爱你、需要你。用我全身心的爱祝你生日快乐!'),('74363780510781440','jinggo','2016-11-02 19:07:27',NULL,NULL,'第一次陪宝宝过生日',0,'小仙女生日快乐，年年18 ^_^','http://pd2kdimru.bkt.clouddn.com/1a058dfc91fe4e419a5af70bfe74d244.jpg','小寿星生日','健健康康，天天开心 O(∩_∩)O~'),('75073072176041984','jinggo','2017-04-22 18:05:56',NULL,NULL,'我们的婚纱照',0,'今天的婚纱照拍的真是激情，有大暴雨，又是大太阳！！！','http://pd2kdimru.bkt.clouddn.com/9172f83b9f8f4f908555627eacc9490d.jpg','植物园','遇见你，是最幸运的事。。'),('79388970269544448','jinggo','2017-10-10 10:10:10','jinggo','2018-11-26 15:56:19','我们领证了',0,'宝宝，站在爱情的面前不一定得到平等，不管如何我都会认真在我的部分。','http://pd2kdimru.bkt.clouddn.com/b1d2dbfc92144052bf7705d018f419c3.jpg','小红照','王太太，你好，请多多关照！！！');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` varchar(255) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `avatar` varchar(1000) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `department_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`create_by`,`create_time`,`update_by`,`update_time`,`address`,`avatar`,`description`,`email`,`mobile`,`nick_name`,`password`,`sex`,`status`,`type`,`username`,`del_flag`,`department_id`) values ('682265633886209','','2018-04-30 23:28:42','','2018-08-11 22:59:27','','https://s1.ax1x.com/2018/05/19/CcdVQP.png','','Exrick2@qq.com','18782059033','','$2a$10$PS04ecXfknNd3V8d.ymLTObQciapMU4xU8.GADBZZsuTZr7ymnagy',1,-1,0,'Exrick',0,'40322777781112832'),('4363087427670016','','2018-05-03 15:09:42','','2018-08-11 20:17:11','[\"510000\",\"510100\",\"510114\"]','https://s1.ax1x.com/2018/05/19/CcdVQP.png','','1012139570@qq.com','18782059038','','$2a$10$PS04ecXfknNd3V8d.ymLTObQciapMU4xU8.GADBZZsuTZr7ymnagy',1,0,0,'test',0,'40652338142121984'),('682265633886208','','2018-05-01 16:13:51','','2018-08-11 20:16:58','[\"510000\",\"510100\",\"510104\"]','http://p77xsahe9.bkt.clouddn.com/788eb3e78206429eb34fc7cd3e1e46f4.jpg','','1012139570@qq.com','18782059038','','$2a$10$c3RpqB4syYTcrlMls7LYcO6mwAvMuq/uGeIvzJ6BBLRl.xn8THQaW',1,0,1,'admin',0,'40322777781112832'),('60478981706616832','','2018-10-05 11:35:20','',NULL,'[\"440000\",\"440100\",\"440106\"]','http://pd2kdimru.bkt.clouddn.com/f08db7571b434c199ae8ebaa25459dce.jpg',NULL,'jinggo@sohu.com','13556106751',NULL,'$2a$10$FZyaY7LP5GSSO15kvUAi5.sCun4aHWE5WDN.MlJBjkA5a6cyhsCLq',1,0,1,'jinggo',0,'40322811096469504');

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `id` varchar(255) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `role_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `t_user_role` */

insert  into `t_user_role`(`id`,`create_by`,`create_time`,`del_flag`,`update_by`,`update_time`,`role_id`,`user_id`) values ('16056421829316608',NULL,'2018-06-04 21:34:50',0,NULL,'2018-06-04 21:34:50','496138616573953','6118792149078016'),('63154545722658817',NULL,NULL,0,NULL,NULL,'496138616573953','682265633886209'),('40679199995858945',NULL,'2018-08-11 20:16:58',0,NULL,'2018-08-11 20:16:58','496138616573952','682265633886208'),('40679257113890816',NULL,'2018-08-11 20:17:11',0,NULL,'2018-08-11 20:17:11','496138616573953','4363087427670016'),('63154545512943616',NULL,NULL,0,NULL,NULL,'496138616573952','682265633886209'),('66616334720438272',NULL,NULL,0,NULL,NULL,'65008196867067904','60478981706616832');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
