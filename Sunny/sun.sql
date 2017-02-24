/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50616
Source Host           : localhost:3306
Source Database       : sun

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2016-06-15 10:42:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ybt_images`
-- ----------------------------
DROP TABLE IF EXISTS `ybt_images`;
CREATE TABLE `ybt_images` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '编号',
  `relation_id` varchar(32) DEFAULT NULL COMMENT ' 关系ID(可以是商户ID、商品ID) ',
  `type` varchar(30) DEFAULT NULL COMMENT ' 类型(存储文件夹名) goods = 商品  ，logo = 商标 ,qrcode = 二维码 ,environment=环境  ',
  `name` varchar(100) DEFAULT NULL COMMENT ' 存储文件名称 ',
  `suffix` varchar(5) DEFAULT NULL COMMENT '图片后缀名',
  `deleted` int(1) DEFAULT NULL COMMENT ' 删除标志(1删除，0正常) ',
  `create_time` datetime DEFAULT NULL COMMENT ' 创建时间 ',
  `update_time` datetime DEFAULT NULL COMMENT ' 修改时间 ',
  `base` varchar(255) DEFAULT NULL,
  `data` tinyblob,
  `img_web_path` varchar(255) DEFAULT NULL,
  `local_path` varchar(255) DEFAULT NULL,
  `title_img_web_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA5BBCAACC2EAD9A1` (`relation_id`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ybt_images
-- ----------------------------
INSERT INTO `ybt_images` VALUES ('4028888c534579f6015345a243a20000', '4028888b52b40c910152b46259510000', 'head', '70964f2e99ca4e50ace1c4b9be710c43.png', 'png', '1', '2016-03-05 15:16:14', '2016-03-07 10:09:31', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884fbb101a014fbb33642e0007', '8a7b87dc4fb01555014fb01fe60d000b', 'ychStorePhotos', '992d1697961e43cca7da49a7ec82b263.png', 'png', '0', '2015-09-14 08:46:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884fce8390014fcf59aff3003f', '8a225b884fce8390014fcf568ad80035', 'ychStorePhotos', '3670dfab339c44a08d15e5247c2f1560.png', 'png', '0', '2015-09-15 12:53:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884fce8390014fcf9b84140053', '8a225b884fce8390014fcf9c716b0058', 'ychStorePhotos', 'e9347f617250416dbd5968f1638d3393.png', 'png', '0', '2015-09-15 14:06:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884fcffba9014fd42e66000038', '8a225b884fcffba9014fd42c032d0035', 'ychStorePhotos', '3ae961bafc984369aa025eeca4faa6d1.png', 'png', '0', '2015-09-16 11:24:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884fcffba9014fd4801a530057', '8a225b884fcffba9014fd47b45b80050', 'ychStorePhotos', '4522ebf3caad408b956e0c21c7e19c5e.png', 'png', '0', '2015-09-16 12:53:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884fcffba9014fd5056d4d0070', '8a225b884fcffba9014fd5034890006a', 'ychStorePhotos', 'f40407e68d7e4e9aaf45e47e9f429124.png', 'png', '0', '2015-09-16 15:19:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884fd961aa014fd9e3e807008c', '8a225b884fd961aa014fd9e189260080', 'ychStorePhotos', '894ead13dbec49eaa58ce14c25fea942.png', 'png', '0', '2015-09-17 14:00:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884fd961aa014fde66193200d0', '8a225b884fd961aa014fde64e89800cd', 'ychStorePhotos', '9caf8a9bdb764c44951418bc8300482f.png', 'png', '0', '2015-09-18 11:01:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884fd961aa014fdeb6b04b00f9', '8a225b884fd961aa014fdeb58c1700f6', 'ychStorePhotos', '2fd1f898291c401b8e60604cc40805e2.png', 'png', '0', '2015-09-18 12:29:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884fd961aa014fded623b90116', '8a225b884fd961aa014fdec84792010d', 'ychStorePhotos', 'd889cc84d40b4952a15abe7c9db6b8a2.png', 'png', '0', '2015-09-18 13:03:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884fd961aa014fdf56a0d40145', '8a225b884fd961aa014fdf54ae3c013d', 'ychStorePhotos', '1782086bc53445af98ca17e4b4f565f4.png', 'png', '0', '2015-09-18 15:24:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884fd961aa014fdf665e99014e', '8a225b884fd961aa014fdf644a0c014b', 'ychStorePhotos', 'edcb5953183a445cae94fd263da7dcd8.png', 'png', '0', '2015-09-18 15:41:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884fd961aa014fed55e92a0213', '8a225b884fd961aa014fed55fbcc0214', 'ychStorePhotos', '70de3b3f3f5c458dabbda4d0b41e02f3.png', 'png', '0', '2015-09-21 08:37:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884feda77d014fedbacfd4001e', '8a225b884fd961aa014fed7256730232', 'ychStorePhotos', '7c0add0e96eb4bc09ae17fb528a66bb4.png', 'png', '0', '2015-09-21 10:28:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884feda77d014fee9fa6f400db', '8a225b884feea02d014feea0df330004', 'ychStorePhotos', '006d342e9c854c9087973165cafbdad3.png', 'png', '0', '2015-09-21 14:39:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884feea02d014feeb336a20012', '8a225b884feea02d014feeb1c39a000f', 'ychStorePhotos', 'fbd7a65fcdb1450eabe327e863a20fcd.png', 'png', '0', '2015-09-21 14:59:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884feea02d014feefb6717003b', '8a225b884feea02d014feefc07fe0040', 'ychStorePhotos', '4dab7ae3d8e44a6f9ebfcc7416e35cdf.png', 'png', '0', '2015-09-21 16:18:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884feea02d014fef00a3fb004b', '8a225b884feea02d014feefeb0620046', 'ychStorePhotos', 'ee0e7910fc1e46e68568fa1b85364908.png', 'png', '0', '2015-09-21 16:24:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884feea02d014fef3737e40059', '8a225b884feea02d014fef378c43005c', 'ychStorePhotos', '3eb37e0e81474a38bfb3bf557f6c6e38.png', 'png', '0', '2015-09-21 17:23:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884feea02d014ff33de0ff0074', '8a225b884feea02d014ff33c2c380071', 'ychStorePhotos', '46dd5f0dbdb94da6b75184b906c4c7ed.png', 'png', '0', '2015-09-22 12:09:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884feea02d014ff345bc9f0080', '8a225b884feea02d014ff3444bc9007d', 'ychStorePhotos', '92d04cfb94ec49d4a2b83da7c61f5a61.png', 'png', '0', '2015-09-22 12:18:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884feea02d014ff394e8f900cf', '8a225b884feea02d014ff393617e00cc', 'ychStorePhotos', '8ce8b06952664d58886e46091d3ce665.png', 'png', '0', '2015-09-22 13:44:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884feea02d014ff3c0a46c00eb', '8a225b884feea02d014ff3bf249a00e2', 'ychStorePhotos', '4540f63a6463436c963ae7ffc7f1c38f.png', 'png', '0', '2015-09-22 14:32:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884feea02d014ff40301ed00fa', '8a225b884feea02d014ff400ddfd00f6', 'ychStorePhotos', 'd37d1d00ae01466ab0ce08410b0f0c24.png', 'png', '0', '2015-09-22 15:44:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884feea02d014ff406eaa70101', '8a225b884feea02d014ff4054bc600fe', 'ychStorePhotos', '5d71f581b9f5493695da8b05631b6dab.png', 'png', '0', '2015-09-22 15:49:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884feea02d014ff9197d530169', '8a225b884feea02d014ff915cc9d0163', 'ychStorePhotos', '01eaef34a53e4ac998a42d667c421430.png', 'png', '0', '2015-09-23 15:27:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884feea02d014ffd97ff2001bd', '8a225b884feea02d014ffd961d9b01ba', 'ychStorePhotos', 'd6746c6bb9414d17969b3541b89710d6.png', 'png', '0', '2015-09-24 12:24:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884feea02d014ffe782e2501e5', '8a225b884feea02d014ffe77200001e2', 'ychStorePhotos', '592644420c664367b0dfc7b00873d99d.png', 'png', '0', '2015-09-24 16:29:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b884feea02d0150025467040230', '8a225b884feea02d0150024c453f0224', 'ychStorePhotos', '63e5d1b5a79244aeba1ce7bba9c221e6.png', 'png', '0', '2015-09-25 10:29:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885003424b01500347bcf5000a', '8a225b885003424b0150034663f50006', 'ychStorePhotos', '73e9af420f5a4724b7e562e86a441c4a.png', 'png', '0', '2015-09-25 14:54:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850036e400150039fe868002e', '8a225b885003424b015003461a210003', 'ychStorePhotos', '625652d066634a0db3ec6a45332647c7.png', 'png', '0', '2015-09-25 16:34:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885003d9d901501800e166013f', '8a225b885003d9d9015018005b9d013c', 'ychStorePhotos', 'f1a1eec1c81c4d0fbbde5ac974662a16.png', 'png', '0', '2015-09-29 15:29:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885003d9d9015044eea1f101fd', '8a7b87dc4fa70635014fac5602e50033', 'ychStorePhotos', '032ab230204a4f47a7cddbb217673cfa.png', 'png', '0', '2015-10-08 08:52:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885003d9d90150468745fa0249', '8a7b87dc4fa70635014fabaee1da001c', 'ychStorePhotos', 'a119207ff6f34d2f9bed6e64d2191b6f.png', 'png', '0', '2015-10-08 16:18:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082be26950020', '8a225b884fcffba9014fd47b45b80050', 'qrcode', 'bfba6d8d2961486bba71e02a98151a03.jpg', 'jpg', '0', '2015-10-20 08:55:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082bec53b0024', '8a225b884fd961aa014fde64e89800cd', 'qrcode', '29d40d4a124c473b9422f4ee0ed1401f.jpg', 'jpg', '0', '2015-10-20 08:55:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082bf0fe20027', '8a225b884fce8390014fcf568ad80035', 'qrcode', '8a037eee3174448f85495c1046febf3c.jpg', 'jpg', '0', '2015-10-20 08:56:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082bf9c230029', '8a225b884feea02d014fef378c43005c', 'qrcode', 'e96d0d7792f0443fadbe17b40aa918d9.jpg', 'jpg', '0', '2015-10-20 08:56:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082c00b7d002b', '8a225b885003424b0150034663f50006', 'qrcode', '020cb301527645e2bae9317c01bdc2b3.jpg', 'jpg', '0', '2015-10-20 08:57:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082c0ac83002e', '8a225b884feea02d014feefc07fe0040', 'qrcode', 'c9dc205efa6a49768c054ad5d2d24c0c.jpg', 'jpg', '0', '2015-10-20 08:57:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082c165a10030', '8a225b884fd961aa014fdeb58c1700f6', 'qrcode', 'b4950be1b5f74e228a6fa5c1597b71b6.jpg', 'jpg', '0', '2015-10-20 08:58:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082c3cd510037', '8a225b884fd961aa014fed55fbcc0214', 'qrcode', '4f7a5679abc94974affdd719adbe9452.jpg', 'jpg', '0', '2015-10-20 09:01:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082c50609003d', '8a225b884feea02d014ff89250300148', 'qrcode', 'bc95262cade5449ca6fc115c1f01e5f9.jpg', 'jpg', '0', '2015-10-20 09:02:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082c58a31003f', '8a225b884fd961aa014fd9e189260080', 'qrcode', 'd511159f44ac499f9ea013e07535d66a.jpg', 'jpg', '0', '2015-10-20 09:03:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082c5fc650042', '8a225b884fd961aa014fdf644a0c014b', 'qrcode', '68e17be99cef4790b49fe668d042d1c1.jpg', 'jpg', '0', '2015-10-20 09:03:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082c65fdc0044', '8a225b884fcffba9014fd5034890006a', 'qrcode', '6c63db5f94924a00825df8846c410f5f.jpg', 'jpg', '0', '2015-10-20 09:04:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082c6ce5b0047', '8a225b884feea02d014ff33c2c380071', 'qrcode', 'd47ea1c6d77247f29f2e70824b904db1.jpg', 'jpg', '0', '2015-10-20 09:04:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082c73e2f0049', '8a225b885003d9d9015018005b9d013c', 'qrcode', '2142417c2097449e80e40ff524544e7b.jpg', 'jpg', '0', '2015-10-20 09:05:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082c7c1a9004b', '8a225b884feea02d014ff4054bc600fe', 'qrcode', 'cc5db3ab882d4c2c9359dafe7d23a135.jpg', 'jpg', '0', '2015-10-20 09:05:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082c8da5f004e', '8a225b884feea02d014ffd961d9b01ba', 'qrcode', 'c71317604bdc47d8bf81e3bc4cec17b1.jpg', 'jpg', '0', '2015-10-20 09:06:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082c93ffc0051', '8a225b884feea02d014ff3bf249a00e2', 'qrcode', '94aedfae29414975b896c60af897b6a0.jpg', 'jpg', '0', '2015-10-20 09:07:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082c9cd180054', '8a7b87dc4fa70635014fac5602e50033', 'qrcode', '5a3af6a6e441412aa5165930f420d5f2.jpg', 'jpg', '0', '2015-10-20 09:07:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082ca71f8005f', '8a7b87dc4fa70635014fabaee1da001c', 'qrcode', '62b01108b7f1457ea9d8cc1d56460153.jpg', 'jpg', '0', '2015-10-20 09:08:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082cacf650061', '8a225b884feea02d0150024c453f0224', 'qrcode', 'b61955c0514c464bb21e05e4a8b76a03.jpg', 'jpg', '0', '2015-10-20 09:09:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082cb2a420066', '8a225b884feea02d014feefeb0620046', 'qrcode', 'be100d1759ff4d7e815c76dcb6d21499.jpg', 'jpg', '0', '2015-10-20 09:09:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082cb93720068', '8a225b884feea02d014feeb1c39a000f', 'qrcode', '842a7aaa397a425f994d6103a5c33495.jpg', 'jpg', '0', '2015-10-20 09:09:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082cca4fa006c', '8a225b884fcffba9014fd42c032d0035', 'qrcode', '251770db36f0401d95fc6bac78a24746.jpg', 'jpg', '0', '2015-10-20 09:11:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082cd4ab2006e', 'bj_jiguosheng7_30chaoyang204', 'qrcode', 'a165802ccabc43a8bbfeb739a1d1f6f7.jpg', 'jpg', '0', '2015-10-20 09:11:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082ce1b600076', '8a225b884fd961aa014fdf54ae3c013d', 'qrcode', '033efd102e854494a99d3765c33b4b86.jpg', 'jpg', '0', '2015-10-20 09:12:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082ceb9c70079', '8a225b884fcffba9014fd5400afe0082', 'qrcode', 'e27e3897d0664629b2163d15f4dfe930.jpg', 'jpg', '0', '2015-10-20 09:13:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082cf2528007c', '8a225b884feea02d014ff915cc9d0163', 'qrcode', '94a80ac094fa43b1b4ad97825fdb47ec.jpg', 'jpg', '0', '2015-10-20 09:13:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082cf76e2007e', '8a225b884fcffba9014fd4f8e5b90065', 'qrcode', '214c4ad3c92b438184d29973b3797d28.jpg', 'jpg', '0', '2015-10-20 09:14:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082cff31d0080', '8a225b885003424b015003461a210003', 'qrcode', 'fe3ba67d6b624c8faca3472f6f4c2f6f.jpg', 'jpg', '0', '2015-10-20 09:14:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082d05d1c0082', '8a225b884feea02d014ff393617e00cc', 'qrcode', '6e165404c95e49cf876ab6c838f491f4.jpg', 'jpg', '0', '2015-10-20 09:15:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082d0cab90087', '8a225b884fd961aa014fdec84792010d', 'qrcode', 'f7095e20af8742bcb352b4fae1181556.jpg', 'jpg', '0', '2015-10-20 09:15:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082d128f00089', '8a225b884feea02d014ff3444bc9007d', 'qrcode', '355444e0eb50464a9dba480f2c1913b1.jpg', 'jpg', '0', '2015-10-20 09:15:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082d1b56e008d', '8a225b884feea02d014ff400ddfd00f6', 'qrcode', '0178f5dc37624b92ba9b2bb321e2d3d1.jpg', 'jpg', '0', '2015-10-20 09:16:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082d293a60091', '8a7b87dc4f87fd68014f8c0e6ee7001a', 'qrcode', '834f382c147e48f79657b6c21e9aa7ce.jpg', 'jpg', '0', '2015-10-20 09:17:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082d305630093', '8a225b884fce8390014fcefdc4570007', 'qrcode', '9b28e163483f4b17b669059b04502b52.jpg', 'jpg', '0', '2015-10-20 09:17:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082d388570096', '8a225b884fce8390014fcf9c716b0058', 'qrcode', 'f441804b3ca14f9984f9f72fa87d02d4.jpg', 'jpg', '0', '2015-10-20 09:18:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88507f6166015082d3f8700099', '8a225b884fce8390014fcf9aa031004e', 'qrcode', 'ce5350a826c846d287f3725fc02260bc.jpg', 'jpg', '0', '2015-10-20 09:19:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850830c3a015083457f2f00c0', '8a225b88507f6166015082f0f51100c6', 'qrcode', 'e1401926476142b891f96b7782018e01.jpg', 'jpg', '0', '2015-10-20 11:23:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850830c3a0150840b85a101cc', '8a225b884feea02d014feea0df330004', 'qrcode', 'd68b85eff6ac4136a78e652e0e380865.jpg', 'jpg', '0', '2015-10-20 14:59:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850830c3a01508453acdf026b', '8a7b87dc4fb01555014fb01fe60d000b', 'qrcode', 'f2833f2b967343fcac10369c4a18f996.jpg', 'jpg', '0', '2015-10-20 16:18:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850b231a50150b237d3c60005', '8a7b87dc4f87fd68014f8c0e6ee7001a', 'ychStorePhotos', 'fb5668ecfce348d4aeb61c4e71d269f0.png', 'png', '0', '2015-10-29 14:10:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850c5beae0150c6e5596f00da', 'bj_jiguosheng7_30chaoyang204', 'ychStorePhotos', 'a8f9246df41446b09bc05412e539f93a.png', 'png', '0', '2015-11-02 14:32:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850c5beae0150cb887da00223', '8a225b884fcffba9014fd5400afe0082', 'ychStorePhotos', '8506addfd409490481c7132982e21b97.png', 'png', '0', '2015-11-03 12:09:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850c5beae0150cb8a50fa0229', '8a225b884fcffba9014fd4f8e5b90065', 'ychStorePhotos', 'ef72946828f04e9fb6c56fc9d20e8742.png', 'png', '0', '2015-11-03 12:11:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850c5beae0150d0e6929e03ea', '8a225b884fce8390014fcefdc4570007', 'ychStorePhotos', '868cd14b13534c4c83823d069fd30b26.png', 'png', '0', '2015-11-04 13:09:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850c5beae0150e9bcc5d807f3', '8a225b884feea02d014ff89250300148', 'ychStorePhotos', '9a955457df6841f39c8ca8a1c6bbf168.png', 'png', '0', '2015-11-09 08:55:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850c5beae0150e9ed77a50813', '8a225b88507f6166015082f0f51100c6', 'ychStorePhotos', '60749e97ebe1466990990c83e2fa859e.png', 'png', '0', '2015-11-09 09:48:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850c5beae0150ea006741082e', '8a225b884fce8390014fcf9aa031004e', 'ychStorePhotos', '2cc3970bccfe45a4877929206b5f5192.png', 'png', '0', '2015-11-09 10:08:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850c5beae0150eb536c620a6e', '8a225b8850c5beae0150eb4fb6b90a69', 'ychStorePhotos', '385ae205906c42839bebac3d05c55462.png', 'png', '0', '2015-11-09 16:19:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850c5beae0150eb5d54390a8c', '8a225b8850c5beae0150eb4fb6b90a69', 'qrcode', '972f71dc94264ccb92b65fd95b7fd6ab.jpg', 'jpg', '0', '2015-11-09 16:29:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec1520150ef50f9070024', '8a225b8850eec1520150ef4f775e0022', 'ychStorePhotos', '627ab7a3c99b40b49503566dc44c73f4.png', 'png', '0', '2015-11-10 10:54:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec1520150ef9b15e9004e', '8a225b8850eec1520150ef9d12f90050', 'ychStorePhotos', 'cf11c1c4b5df4d56984a854c6899c120.png', 'png', '0', '2015-11-10 12:17:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec1520150f072b24400b3', '8a225b8850eec1520150f072c8a600b4', 'ychStorePhotos', '8e45fe188b9541479eebbbaa7f680add.png', 'png', '0', '2015-11-10 16:11:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec1520150f4aa46ab0174', '8a225b8850eec1520150f4a847db0171', 'ychStorePhotos', '49d8960954ed482ebae62b5bed44b0ef.png', 'png', '0', '2015-11-11 11:50:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec1520150f50616c001af', '8a225b8850eec1520150f503f01701ad', 'ychStorePhotos', '8802b42c99504882ba6ec401acb71d60.png', 'png', '0', '2015-11-11 13:30:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec1520150f574db1501bb', '8a225b8850eec1520150f5732cd401ba', 'ychStorePhotos', '07f18ee45b564b9dbe20d3577805d60f.png', 'png', '0', '2015-11-11 15:32:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec1520150f59ceb6301c7', '8a225b8850eec1520150f5732cd401ba', 'qrcode', '8aec9fc0e1ed46b784a7a0c7b1bc10c2.jpg', 'jpg', '0', '2015-11-11 16:15:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec1520150fa265e7a0300', '8a225b8850eec1520150fa15696902e6', 'ychStorePhotos', '04e2d06847fd4008aaac4d8558ac7a18.png', 'png', '0', '2015-11-12 13:23:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec1520150fa6429530346', '8a225b8850eec1520150fa62998e0340', 'ychStorePhotos', 'a087b08d6e004762aa02af199bd750d4.png', 'png', '0', '2015-11-12 14:31:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec1520150fa69be8c0357', '8a225b8850eec1520150fa6093a70339', 'ychStorePhotos', 'e51835c0c1b34d778ea49c5b26553c26.png', 'png', '0', '2015-11-12 14:37:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec1520150fa6e03c8035d', '8a225b8850eec1520150fa6e3a650360', 'ychStorePhotos', 'aeb77f2bf65645b4b0500b20a4e09396.png', 'png', '0', '2015-11-12 14:42:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec1520150fa96c7e403ca', '8a225b8850eec1520150fa95d8d903c8', 'ychStorePhotos', '79322909239b41d3960424ce4937b4e6.png', 'png', '0', '2015-11-12 15:26:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec1520150faa8e8b30438', '8a225b8850eec1520150faa92fa5043b', 'ychStorePhotos', '609f92c97df54ab68a068e046b142efd.png', 'png', '0', '2015-11-12 15:46:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec1520150fac5e264046e', '8a225b8850eec1520150fac6192d0471', 'ychStorePhotos', 'abcb98d8b0fc43d8870f3b47b5a1e7b5.png', 'png', '0', '2015-11-12 16:18:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec1520150fae8399a04ad', '8a225b88513770f101513d27438801d4', 'ychStorePhotos', '5f47e54ffe7a48f78b3f34733517befc.png', 'png', '0', '2015-11-12 16:55:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec1520150ff7d282905e9', '8a225b8850eec1520150ff7bcaa505e7', 'ychStorePhotos', '56b49daad734427f937a719d32647bbb.png', 'png', '0', '2015-11-13 14:16:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec1520150ffbe3af80600', '8a225b8850eec1520150ffbc7baa05fe', 'ychStorePhotos', 'bd5e8e7638da4bf3985460a696ced0ae.png', 'png', '0', '2015-11-13 15:28:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec15201510ef064590818', '8a225b8850eec15201510eef02750816', 'ychStorePhotos', 'ae7b048495e04017b5ad8f093978afa8.png', 'png', '0', '2015-11-16 14:16:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec15201510f060c00087b', '8a225b8850eec15201510f067237087e', 'ychStorePhotos', '3d1740e7c8e64a57be1bb7eb63678980.png', 'png', '0', '2015-11-16 14:40:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec15201511372a80409f4', '8a225b8850eec1520151137115f609f2', 'ychStorePhotos', 'd98ae40183924c67aaebc72495948b01.png', 'png', '0', '2015-11-17 11:18:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec152015113c6f82c0a93', '8a225b8850eec152015113c55a7d0a91', 'ychStorePhotos', 'b618127ea54840e7b6aad4963e38ce00.png', 'png', '0', '2015-11-17 12:50:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec152015113f909300aa6', '8a225b8850eec152015113f7a0ee0aa4', 'ychStorePhotos', 'f22987fa989a4fe5ae3189eeaabb1d38.png', 'png', '0', '2015-11-17 13:44:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec152015118a1c60f0c3f', '8a225b8850eec1520151189fcf980c3d', 'ychStorePhotos', '7a73de72d92f4942aa7fe1577d3085ee.png', 'png', '0', '2015-11-18 11:27:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec152015118f1b2020c56', '8a225b8850eec152015118f0f7a60c54', 'ychStorePhotos', 'c5a030ace2ff463e84765b0e8b82a086.png', 'png', '0', '2015-11-18 12:54:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec15201511991926b0cc5', '8a225b8850eec15201511991b8290cc7', 'ychStorePhotos', 'fd923d3d34744dd19be6ae51948c92b1.png', 'png', '0', '2015-11-18 15:49:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec15201511e81eae00d23', '8a225b8850eec1520150fa15696902e6', 'qrcode', '742a473b6cec4ae5b8e8a40d2620bb8e.jpg', 'jpg', '0', '2015-11-19 14:50:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec15201511eea926b0dcb', '8a225b8850eec15201511eeac80c0dce', 'ychStorePhotos', '7e93bfed76f94a82906bea083c20b95d.png', 'png', '0', '2015-11-19 16:44:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec1520151234307080dff', '8a225b8850eec15201512341b57a0dfd', 'ychStorePhotos', '2ce4e8a77fa945fb85bca37d48880d9c.png', 'png', '0', '2015-11-20 12:59:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8850eec15201512376e7260e0e', '8a225b8850eec15201512375dcec0e0c', 'ychStorePhotos', 'e0db5e77261448cb92b57bfd0acaba4b.png', 'png', '0', '2015-11-20 13:56:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851242f4e01512d00cdfd00f6', '8a225b8850eec15201512341b57a0dfd', 'qrcode', '6b5dffe0519a480080c1d4acec1d77ca.jpg', 'jpg', '0', '2015-12-07 15:41:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851242f4e015131d9f5a30173', '8a225b8850eec152015113f7a0ee0aa4', 'qrcode', '35f39dc3fa6e400ba92b218312c42736.jpg', 'jpg', '0', '2015-12-07 15:42:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851322e6b0151324931510055', '8a225b8850eec152015119d8fc3a0cd2', 'qrcode', 'a04e9633e9b6406c8e86812954de32bd.jpg', 'jpg', '0', '2015-12-07 15:42:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88513258dd015132ec54bb0153', '8a225b8850eec1520150fa95d8d903c8', 'qrcode', 'afc5444c0f6d42b5b3897f5361e131c5.jpg', 'jpg', '0', '2015-12-07 16:20:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88513770f10151382170b90045', '8a225b88513770f10151382170ba0047', 'qrcode', '0c84dd051287433ca1c7d49d52f6f8d7.jpg', 'jpg', '0', '2015-11-24 14:17:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88513770f10151382335920049', '8a225b88513770f10151382170ba0047', 'ychStorePhotos', 'efd8875e9176455d9eb6418647d9b828.png', 'png', '0', '2015-11-24 14:17:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88513770f101513d27438501d2', '8a225b88513770f101513d27438801d4', 'qrcode', '6da686a1c5de4c008c53bc89375d8dd8.jpg', 'jpg', '0', '2015-12-07 16:23:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88513770f101513d363bda01de', '8a225b88513770f101513d363bda01e0', 'qrcode', '048c4da1b36145908ca976a378a4fd46.jpg', 'jpg', '0', '2015-11-25 13:58:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88513770f101513d383ac201e2', '8a225b88513770f101513d363bda01e0', 'ychStorePhotos', 'a62e3f1a485844c9b3593a272a37674b.png', 'png', '0', '2015-11-25 13:58:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88513770f101514250ce6803ac', '8a225b88513770f101514251113b03b0', 'ychStorePhotos', 'a666c9254baa439eaa01909defef23a9.png', 'png', '0', '2015-11-26 13:43:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88513770f101514251113a03ae', '8a225b88513770f101514251113b03b0', 'qrcode', '03350c2be00148b1bc855e97fdf1d271.jpg', 'jpg', '0', '2015-12-07 16:00:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88514282a20151428981600004', '8a225b88514282a20151428981630006', 'qrcode', '62e573451b6741f3a0d6fec1f823cb55.jpg', 'jpg', '0', '2015-11-26 14:47:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88514282a20151428bde0b0021', '8a225b88514282a20151428981630006', 'ychStorePhotos', '068dce37278a4ed4a88aac849b7ff9e3.png', 'png', '0', '2015-11-26 14:47:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88514282a20151429d83a0007a', '8a225b88514282a20151429dc76f007f', 'ychStorePhotos', 'd9db10ebb492468e93f0e1cdcc6c3540.png', 'png', '0', '2015-11-26 15:06:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88514282a20151429dc76c007d', '8a225b88514282a20151429dc76f007f', 'qrcode', '88ab669a387f492d96ce41a612e13f5a.jpg', 'jpg', '0', '2015-11-26 15:07:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88514282a2015142de211c00bb', '8a225b88514282a2015142de211d00bd', 'qrcode', 'd1e22f96a14d4f78b1214189aeea29b6.jpg', 'jpg', '0', '2015-11-26 16:21:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88514282a2015142e213c500bf', '8a225b88514282a2015142de211d00bd', 'ychStorePhotos', 'ec648280fb8b42309e799aa71d2c461b.png', 'png', '0', '2015-11-26 16:21:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88514282a201514682cc760117', '8a225b88514282a2015146831fb2011c', 'ychStorePhotos', 'a38e5e7a00044a4fafc5b60dbcc5615b.png', 'png', '0', '2015-11-27 09:16:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88514282a2015146831fb2011a', '8a225b88514282a2015146831fb2011c', 'qrcode', '9bcc37038c41431b940bedbff7b7b050.jpg', 'jpg', '0', '2015-12-07 16:01:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88514282a201514749a6e20168', '8a225b88514282a20151474af435016d', 'ychStorePhotos', 'f50e012107134232954e643431190e24.png', 'png', '0', '2015-11-27 12:54:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88514282a20151474af435016b', '8a225b88514282a20151474af435016d', 'qrcode', '9950c33774dc43ee8bfd7b0bdca3bd0a.jpg', 'jpg', '0', '2015-12-07 15:45:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88514282a20151477816b7017c', '8a225b88514282a201514778e10e0182', 'ychStorePhotos', '262e5d43bb174656940a1aa024aef935.png', 'png', '0', '2015-11-27 13:44:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88514282a201514778e10e0180', '8a225b88514282a201514778e10e0182', 'qrcode', 'cdf55913a9994f6e88a281e4a9e0cb77.jpg', 'jpg', '0', '2015-11-27 13:45:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885156f2cf015156f56a640015', '8a225b885156f2cf015156f56a6a0017', 'qrcode', 'e0bcd5b085b149538fcf986a02d7c53c.jpg', 'jpg', '0', '2015-11-30 14:00:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885156f2cf015156fa37ed001e', '8a225b885156f2cf015156f56a6a0017', 'ychStorePhotos', '0e1d8661fa73416594d58550eb23f1df.png', 'png', '0', '2015-11-30 14:00:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885156f2cf01515703393d0046', '8a225b885156f2cf01515703393e0048', 'qrcode', 'bbb6897292cd468dbbc8d2681c64c673.jpg', 'jpg', '0', '2015-11-30 14:10:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885156f2cf01515708429f0051', '8a225b885156f2cf01515703393e0048', 'ychStorePhotos', 'b44be16bc5d047a0aa87597f6ec40880.png', 'png', '0', '2015-11-30 14:15:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885156f2cf0151574268d101ae', '8a225b885156f2cf0151574268d201b0', 'qrcode', '512576bb9ba5465288e2f034d5d004ee.jpg', 'jpg', '0', '2015-11-30 15:21:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885156f2cf015157441b0301b2', '8a225b885156f2cf0151574268d201b0', 'ychStorePhotos', '0918a8182db843b687f9fdb2e69748be.png', 'png', '0', '2015-11-30 15:21:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885156f2cf01515751127001d6', '8a225b885156f2cf015157511d0801d9', 'ychStorePhotos', '8cb3a1a15dda4cb284c1f5b01261523b.png', 'png', '0', '2015-11-30 15:35:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885156f2cf015157511d0801d7', '8a225b885156f2cf015157511d0801d9', 'qrcode', '19fcbb108ec544e6b755b9d845158ac1.jpg', 'jpg', '0', '2015-11-30 15:35:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885156f2cf0151578d123e02c8', '8a225b885156f2cf0151578d535202cd', 'ychStorePhotos', '8ee1c1affa1e4d7d8e5d4ea005827a75.png', 'png', '0', '2015-11-30 16:40:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885156f2cf0151578d535202cb', '8a225b885156f2cf0151578d535202cd', 'qrcode', '4c8172e64ebe4e3fa12017dbab452656.jpg', 'jpg', '0', '2015-11-30 16:41:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885157ad8001515ba9bd640259', '8a225b885157ad8001515ba9bd65025b', 'qrcode', '72ef43035edd454fa1414ba5ae129cb1.jpg', 'jpg', '0', '2015-12-01 11:51:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885157ad8001515baf5cc8027a', '8a225b885157ad8001515ba9bd65025b', 'ychStorePhotos', '7a55a03ea22f42c19bd5e2f1467e9b93.png', 'png', '0', '2015-12-01 11:56:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88515bf10701515c21d2ea012a', '8a225b88515bf10701515c21d2ec012c', 'qrcode', '47b0088d6027461c9832a2823241bc98.jpg', 'jpg', '0', '2015-12-01 14:04:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88515bf10701515c2422ed0131', '8a225b88515bf10701515c21d2ec012c', 'ychStorePhotos', '8987561b91c1485e8e40f62111bd0010.png', 'png', '0', '2015-12-01 14:04:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851603fda015160532ed8008f', '8a225b8851603fda01516053d3080094', 'ychStorePhotos', 'f76ad3e6f52246c48c6e9c5457694dcf.png', 'png', '0', '2015-12-02 09:34:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851603fda01516053d3010092', '8a225b8851603fda01516053d3080094', 'qrcode', '184192e7d4134ba8814a987bd15e6eb8.jpg', 'jpg', '0', '2015-12-07 16:03:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851603fda015160900cbf00c6', '8a225b8851603fda01516090487d00cb', 'ychStorePhotos', '14dfd54b44284d408deae5e2aa49c707.png', 'png', '0', '2015-12-02 10:40:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851603fda01516090487d00c9', '8a225b8851603fda01516090487d00cb', 'qrcode', 'baf251dd634c45be8eb43593e45fd041.jpg', 'jpg', '0', '2015-12-07 16:04:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851603fda015160beec9501cc', '8a225b8851603fda015160beec9501ce', 'qrcode', '90f6bea8c9344c3892a88b459b17116a.jpg', 'jpg', '0', '2015-12-02 11:32:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851603fda015160bfd9fa01db', '8a225b8851603fda015160beec9501ce', 'ychStorePhotos', '9727827b5285401083d22bf34b72e1a3.png', 'png', '0', '2015-12-02 11:32:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851603fda015160c222fe0203', '8a225b8851603fda015160c2669a020b', 'ychStorePhotos', '34e74775a6bc4a0fa0857e14f2ca271f.png', 'png', '0', '2015-12-02 11:35:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851603fda015160c266990209', '8a225b8851603fda015160c2669a020b', 'qrcode', '5bff5b710fbb48ccba4641a8da307132.jpg', 'jpg', '0', '2015-12-07 16:04:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851603fda015160f429ee02b3', '8a225b8851603fda015160f429ee02b5', 'qrcode', '45484fa6e10b436980f3e7e3679c514a.jpg', 'jpg', '0', '2015-12-02 12:31:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851603fda015160f53d9d02b7', '8a225b8851603fda015160f429ee02b5', 'ychStorePhotos', '6156dc647aa94fce8abbd50785fffecd.png', 'png', '0', '2015-12-02 12:31:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851603fda01516105a75102dc', '8a225b8851603fda01516105a75202de', 'qrcode', 'a13362242634460790a017f8906b7645.jpg', 'jpg', '0', '2015-12-02 12:51:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851603fda0151610719da02e3', '8a225b8851603fda01516105a75202de', 'ychStorePhotos', '5258f86d06614b9688b284f90fbf582d.png', 'png', '0', '2015-12-02 12:51:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88516110ac0151614b44ad014e', '8a225b88516110ac0151614b44b60150', 'qrcode', 'c55c9212f40f49b5abb508ea95f14097.jpg', 'jpg', '0', '2015-12-02 14:06:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88516110ac0151614cb3fd0152', '8a225b88516110ac0151614b44b60150', 'ychStorePhotos', 'e663527ac6874ea9a6dae6d558f69595.png', 'png', '0', '2015-12-02 14:06:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88516110ac0151616be95901fb', '8a225b88516110ac0151616c2c810203', 'ychStorePhotos', '2d3de892fbb648c580400f63f7fbfc9c.png', 'png', '1', '2015-12-02 14:40:00', '2016-04-27 12:45:47', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88516110ac0151616c2c800201', '8a225b88516110ac0151616c2c810203', 'qrcode', '5c49c5d827f143f599cc5da0c57a44e4.jpg', 'jpg', '0', '2015-12-06 18:47:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88516110ac015161f0b6c30257', '8a225b88516110ac015161f448a4025c', 'ychStorePhotos', 'd9854f0637574c1fb24f86ca881d3f5f.png', 'png', '0', '2015-12-02 17:09:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88516110ac015161f448a3025a', '8a225b88516110ac015161f448a4025c', 'qrcode', 'd108f68ac2854c2392e2673c3d33427a.jpg', 'jpg', '0', '2015-12-02 17:12:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88516110ac0151659a8b8a02f6', '8a225b88516110ac0151659af2de02f9', 'ychStorePhotos', '4cc6fafb8b3946d8b6b55c21405e533c.png', 'png', '0', '2015-12-03 10:10:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88516110ac0151659af2de02f7', '8a225b88516110ac0151659af2de02f9', 'qrcode', 'fd111a5e73e646a6b27c104baf945a17.jpg', 'jpg', '0', '0000-00-00 00:00:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88516110ac015165f6bf230389', '8a225b88516110ac015165f6bf23038b', 'qrcode', '43d94ac0ed664588b58b64b7c63686cd.jpg', 'jpg', '0', '2015-12-03 11:51:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88516110ac015165fb1ed5038e', '8a225b88516110ac015165f6bf23038b', 'ychStorePhotos', '79bc89901a1747faad04f4bb517ffb1a.png', 'png', '0', '2015-12-03 11:55:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88516110ac0151666628fd03a0', '8a225b88516110ac0151666628fd03a2', 'qrcode', 'a5d05cdb1ecb40cab2be5d32250fb16a.jpg', 'jpg', '0', '2015-12-03 13:54:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88516110ac01516667401403a4', '8a225b88516110ac0151666628fd03a2', 'ychStorePhotos', '72e1d910f878461bbd5a553ccd7a357e.png', 'png', '0', '2015-12-03 13:54:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885166902a015166ca13530010', '8a225b885166902a015166ca13600012', 'qrcode', 'ee8c2b9b8b3e4ea089de2b1283251684.jpg', 'jpg', '0', '2015-12-03 15:44:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885166902a015166ccb2620015', '8a225b885166902a015166ca13600012', 'ychStorePhotos', '8721ab84fdc442f58c61fa51dd21297c.png', 'png', '0', '2015-12-03 15:44:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885166902a01516728fa16004b', '8a225b885166902a01516728fa16004d', 'qrcode', '0f4c4ac10027483f8b5a59316987af70.jpg', 'jpg', '0', '2015-12-03 17:29:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885166902a0151672ceb6b004f', '8a225b885166902a01516728fa16004d', 'ychStorePhotos', '315e5e140b474da2bfb995a97c59aa54.png', 'png', '0', '2015-12-03 17:29:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885166902a01516af59fee0141', '8a225b885166902a01516af59fee0143', 'qrcode', '9da2c18cd52e42a99f92ed190b06c30b.jpg', 'jpg', '0', '2015-12-07 16:26:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885166902a01516b287290019c', '8a225b885166902a01516b287291019e', 'qrcode', '913942219a1c4616b562d623d43af1d2.jpg', 'jpg', '0', '2015-12-04 12:05:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885166902a01516b2a9b8d01a0', '8a225b885166902a01516b287291019e', 'ychStorePhotos', '1acf224f36244aa2a610ceb4cc39e723.png', 'png', '0', '2015-12-04 12:05:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885166902a01516bafcf15023f', '8a225b885166902a01516bafcf160241', 'qrcode', '543d501f374747e994b6f027f03f1b5b.jpg', 'jpg', '0', '2015-12-04 14:32:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885166902a01516bb114100255', '8a225b885166902a01516bafcf160241', 'ychStorePhotos', 'c1afcf7d3b2a442e8d293bf82cef3d45.png', 'png', '0', '2015-12-04 14:32:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885166902a01516bc222bf02b0', '8a225b885166902a01516bc222c002b2', 'qrcode', '87ac0204a51642b7b1aa6ecbcd13d4cb.jpg', 'jpg', '0', '2015-12-04 14:52:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885166902a01516bc2fd6a02b4', '8a225b885166902a01516bc222c002b2', 'ychStorePhotos', '7541a44269d4462583840bd4c14a4cef.png', 'png', '0', '2015-12-04 14:52:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885172ef7101517a35af100182', '8a225b8850eec1520151137115f609f2', 'qrcode', '6c1675e8d00a4a2d86c7e673b2ed7da6.jpg', 'jpg', '0', '2015-12-07 15:55:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885172ef7101517b35246f02fd', '8a225b8850eec1520150f4a847db0171', 'qrcode', 'abb490cd2ef6498b8aca4a5fe5c885dc.jpg', 'jpg', '0', '2015-12-07 14:50:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885172ef7101517b35ace602ff', '8a225b8850eec1520150fa62998e0340', 'qrcode', '4ce310b4a61040a98d5d2d3c994d6604.jpg', 'jpg', '0', '2015-12-07 14:51:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885172ef7101517b5c8bdc0320', '8a225b884feea02d014ffe77200001e2', 'qrcode', '5caae9385d694d9e885bd39ba13b333c.jpg', 'jpg', '0', '2015-12-07 15:34:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885172ef7101517b61e0b40328', '8a225b8850eec15201510eef02750816', 'qrcode', '47788e361699449aa0f619abfa9f1370.jpg', 'jpg', '0', '2015-12-07 15:39:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885172ef7101517b6340a90333', '8a225b8850eec15201512375dcec0e0c', 'qrcode', '80ab22e5ab324843960f7cb7f0f2c742.jpg', 'jpg', '0', '2015-12-07 15:41:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885172ef7101517b70dbd40388', '8a225b8850eec1520150fac6192d0471', 'qrcode', '852d02e0988c49e39688be73fd4a8034.jpg', 'jpg', '0', '2015-12-07 15:56:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885172ef7101517b71e1ba038d', '8a225b8850eec1520150fa6e3a650360', 'qrcode', '2b2c402459dd40c68e8b23f562326963.jpg', 'jpg', '0', '2015-12-07 15:57:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885172ef7101517b7278fb0392', '8a225b8850eec1520150f072c8a600b4', 'qrcode', '3945ae5a79fc4e06b90c92ae6d62713a.jpg', 'jpg', '0', '2015-12-07 15:57:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885172ef7101517b72e45c0397', '8a225b8850eec15201511991b8290cc7', 'qrcode', '9819a385c9d14c5f95cd6dae16ad8414.jpg', 'jpg', '0', '2015-12-07 15:58:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885172ef7101517b7368bf039b', '8a225b8850eec15201510f067237087e', 'qrcode', '16ddaa99042c4469b58f69cdb6f795b6.jpg', 'jpg', '0', '2015-12-07 15:58:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885172ef7101517b73d9be03a2', '8a225b8850eec15201511eeac80c0dce', 'qrcode', '2b55e30ff9f941ce8f75a23de5a010b2.jpg', 'jpg', '0', '2015-12-07 15:59:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885172ef7101517b7425fc03a7', '8a225b8850eec1520150faa92fa5043b', 'qrcode', '92492816556747b4b882f0c8a6a4d153.jpg', 'jpg', '0', '2015-12-07 15:59:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885172ef7101517b853c3203eb', '8a225b8850eec1520150ef9d12f90050', 'qrcode', '241bdb612c21441ba25765c1626ca5e7.jpg', 'jpg', '0', '2015-12-07 16:18:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885172ef7101517b85f34a03f2', '8a225b8850eec1520151189fcf980c3d', 'qrcode', '4648d184ceed42c6b7a1fca1439229dd.jpg', 'jpg', '0', '2015-12-07 16:19:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885172ef7101517b865abc03f7', '8a225b8850eec1520150ef4f775e0022', 'qrcode', 'dac7c78b6dbd42b69277917315ad898e.jpg', 'jpg', '0', '2015-12-07 16:19:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885172ef7101517b86b13f03fc', '8a225b8850eec1520150f503f01701ad', 'qrcode', '64f34e0cabad4587a3ccd00b77850839.jpg', 'jpg', '0', '2015-12-07 16:20:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885172ef7101517b878e8e0403', '8a225b8850eec1520150fa6093a70339', 'qrcode', '34ef81703f6d4d768430435aed092a77.jpg', 'jpg', '0', '2015-12-07 16:21:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885172ef7101517b88ba1a040d', '8a225b8850eec1520150ffbc7baa05fe', 'qrcode', 'cecb173518f946d2927cdc9ef9011ba6.jpg', 'jpg', '0', '2015-12-07 16:22:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885172ef7101517b89e96a0417', '8a225b8850eec152015118f0f7a60c54', 'qrcode', 'ebcc2d676f054858a8791eb66e24c3c1.jpg', 'jpg', '0', '2015-12-07 16:23:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885172ef7101517b8a4de5041c', '8a225b8850eec1520150ff7bcaa505e7', 'qrcode', '1f37a9fbcfb04927a3facde23782fbf6.jpg', 'jpg', '0', '2015-12-07 16:24:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851841f2f0151851db55a0132', '8a225b8851841f2f0151851db55a0134', 'qrcode', 'e3444792c5484121b77eb8dd0aa16a50.jpg', 'jpg', '0', '2015-12-09 13:10:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851841f2f01518525320c0136', '8a225b8851841f2f0151851db55a0134', 'ychStorePhotos', '9cbd87e55c9241eb999a5acedced82a2.png', 'png', '0', '2015-12-09 13:10:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851841f2f0151858b4610033f', '8a225b8851841f2f0151858b46100341', 'qrcode', 'd29d54721d814e4188d566e1f9b9ca98.jpg', 'jpg', '0', '2015-12-09 15:02:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851841f2f0151858c1cee0346', '8a225b8851841f2f0151858b46100341', 'ychStorePhotos', '54752f65bf9f43e0a386c44288a9373d.png', 'png', '0', '2015-12-09 15:02:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851841f2f015189a92e590681', '8a225b8851841f2f015189a9f0560686', 'ychStorePhotos', '2f133f964f5844c48f405b1a05bc3ac4.png', 'png', '0', '2015-12-10 10:13:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851841f2f015189a9f0560684', '8a225b8851841f2f015189a9f0560686', 'qrcode', '9ae3a3fef92a43f49d536260580211f3.jpg', 'jpg', '0', '2015-12-22 14:51:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885189b8e501518a17a6db0141', '8a225b885189b8e501518a17e5360146', 'ychStorePhotos', '6c526458cdb04faf99eb3cd1c548ff5b.png', 'png', '0', '2015-12-10 12:13:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885189b8e501518a17e5350144', '8a225b885189b8e501518a17e5360146', 'qrcode', '64622c658c0c4f66bf2b557b7fe36c9b.jpg', 'jpg', '0', '2015-12-22 10:50:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885189b8e501518a59885a0199', '8a225b885189b8e501518a59885b019b', 'qrcode', 'e249050844484cf4b1346fec1faf9232.jpg', 'jpg', '0', '2015-12-10 13:37:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885189b8e501518a647270019e', '8a225b885189b8e501518a59885b019b', 'ychStorePhotos', '144b5150f3c14b61903bfddfb1526ebf.png', 'png', '0', '2015-12-10 13:37:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885189b8e501518f0d065c052e', '8a225b885189b8e501518f0d065d0530', 'qrcode', '378e068ec0e4438d98be79c957c9e4f1.jpg', 'jpg', '0', '2015-12-11 11:20:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885189b8e501518f0de003053b', '8a225b885189b8e501518f0d065d0530', 'ychStorePhotos', '4107726c4971493bbc0690b3d2c747a1.png', 'png', '0', '2015-12-11 11:20:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851a341b80151a3a97d310029', '8a225b8851a341b80151a3a97d32002b', 'qrcode', 'cf88eadbd5444444b0dd446e00058fd9.jpg', 'jpg', '0', '2015-12-15 11:24:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851a341b80151a3dca26c0054', '8a225b8851a341b80151a3a97d32002b', 'ychStorePhotos', '80341be697c442a9b57944cd71ef41cc.png', 'png', '0', '2015-12-15 12:19:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851a341b80151a45809cb006d', '8a225b8851a341b80151a45809cc006f', 'qrcode', '10a09b43ed04430d8dce7777677793c9.jpg', 'jpg', '0', '2015-12-15 14:36:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851a341b80151a45a288f0071', '8a225b8851a341b80151a45809cc006f', 'ychStorePhotos', '0c9e07672a6249c9a8ae26a76fdee424.png', 'png', '0', '2015-12-15 14:36:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851a980bc0151a9d14d02006b', '8a225b8850eec152015119d8fc3a0cd2', 'ychStorePhotos', '8624b916753545f4b975ef0c1f80fc81.png', 'png', '0', '2015-12-16 16:04:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851ae03910151ae6c0b650070', '8a225b885166902a01516af59fee0143', 'ychStorePhotos', '5ba9522717e54a148f0c18ba07788c32.png', 'png', '0', '2015-12-17 13:31:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851c78a480151ccf8cc4100ba', '8a225b8851c78a480151ccf8cc4200bc', 'qrcode', 'dc3e649e4db94698958dedf86dff3bc2.jpg', 'jpg', '0', '2015-12-23 11:56:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851c78a480151ccfa9f2400be', '8a225b8851c78a480151ccf8cc4200bc', 'ychStorePhotos', 'fdba7ea1a9e4455ab0ca0f4263549df4.png', 'png', '0', '2015-12-23 11:56:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851c78a480151cd63ea1c00cf', '8a225b8851c78a480151cd63ea1c00d1', 'qrcode', '2a5ae3efb2024972b8e1f99a91cbdcfd.jpg', 'jpg', '0', '2015-12-23 13:53:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851c78a480151cd6574b000d3', '8a225b8851c78a480151cd63ea1c00d1', 'ychStorePhotos', 'ba9d0580b90846ce85734ea4e1d19857.png', 'png', '0', '2015-12-23 13:53:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851c78a480151cdc474e000e4', '8a225b8851c78a480151cdc474e100e6', 'qrcode', '5d8f6f0b1b82462caf022649086ffae4.jpg', 'jpg', '0', '2015-12-23 15:38:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851c78a480151cdc53eed00e8', '8a225b8851c78a480151cdc474e100e6', 'ychStorePhotos', 'f7aae12ee4914a84b734c037e2479aa4.png', 'png', '0', '2015-12-23 15:38:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851c78a480151cdcac95300ee', '8a225b8851c78a480151cdcac95300f0', 'qrcode', '373b4e956ec64272840270c862624cf8.jpg', 'jpg', '0', '2015-12-23 15:48:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851c78a480151cdcf2e4400f4', '8a225b8851c78a480151cdcac95300f0', 'ychStorePhotos', '24201b381bad48769b10baee1a95f0ab.png', 'png', '0', '2015-12-23 15:48:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851c78a480151d1d5734102c9', '8a225b8851c78a480151d1d5ab2c02ce', 'ychStorePhotos', '550c98d56335443db0b8a11ae6c842a5.png', 'png', '0', '2015-12-24 10:33:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851c78a480151d1d5ab2c02cc', '8a225b8851c78a480151d1d5ab2c02ce', 'qrcode', '76e9f36ff98d4baa9ea7a3c733a1776f.jpg', 'jpg', '0', '2015-12-24 10:34:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851c78a480151d1eeeb3402dd', '8a225b8851c78a480151d1ef284802e2', 'ychStorePhotos', '43ed999025794ed2a6a638863d3bb533.png', 'png', '0', '2015-12-24 11:01:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851c78a480151d1ef284802e0', '8a225b8851c78a480151d1ef284802e2', 'qrcode', '69fe85ead82c4897ae78925a05319d55.jpg', 'jpg', '0', '2015-12-24 11:02:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851e644660151e73d382f0049', '8a225b8851e644660151e73d3830004b', 'qrcode', '7efc464146eb4c4bb136400b22d1a383.jpg', 'jpg', '0', '2015-12-28 14:20:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851e644660151e73e6a2d004d', '8a225b8851e644660151e73d3830004b', 'ychStorePhotos', 'd310dc42bfb449d2a5cc914f57d53a66.png', 'png', '0', '2015-12-28 14:20:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851e644660151e76e93740059', '8a225b8851e644660151e76e9375005b', 'qrcode', '212d0cfb182a4b758faf4860bbc65ff1.jpg', 'jpg', '0', '2015-12-28 15:13:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851e644660151e76ed38b005d', '8a225b8851e644660151e76e9375005b', 'ychStorePhotos', '0db1bda4b65148c686ac5262ccee357e.png', 'png', '0', '2015-12-28 15:13:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851ebcd560151ec4320a10084', '8a225b8851ebcd560151ec4320af0086', 'qrcode', 'cd109b13175d43eeab7b81f1b2e07901.jpg', 'jpg', '0', '2015-12-29 13:44:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851ebcd560151ec4424de0089', '8a225b8851ebcd560151ec4320af0086', 'ychStorePhotos', '3285e526bf5a4a3ba664358cba8bed49.png', 'png', '0', '2015-12-29 13:44:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851f1c50c01521051fcee0433', '8a225b8850eec152015113c55a7d0a91', 'qrcode', '8dc5acee5aaa479d9612f6878d3337fc.jpg', 'jpg', '0', '2016-01-05 13:46:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851f1c50c0152163e48b40487', '8a225b8851f1c50c0152163e48b40489', 'qrcode', 'c801f2d480dc4608ba71dbf331f4ddc1.jpg', 'jpg', '0', '2016-01-07 10:03:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8851f1c50c01521a7a698b04f1', '8a225b8851f1c50c0152163e48b40489', 'ychStorePhotos', '710584815e4f4e9382a54b35d5b1f6e5.png', 'png', '0', '2016-01-07 13:06:00', '2016-04-19 17:03:08', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88535045b201535a8df8b8003c', '8a225b884fcffba9014fd5400afe0082', 'ychStorePhotos', 'a5a59b7087384f9bb550406532b4940c.jpg', 'jpg', '0', '2016-03-09 16:46:06', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88535045b201535a8df8b8003d', '8a225b884fcffba9014fd5400afe0082', 'ychStorePhotos', 'b6d17b72d6364f6c839d18b25bd6b77b.jpg', 'jpg', '0', '2016-03-09 16:46:06', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88535045b201535a8eb3f8003e', '8a225b884fcffba9014fd5400afe0082', 'ychStorePhotos', '21f87f37a9094eb4859f5c4e3ff78751.jpg', 'jpg', '0', '2016-03-09 16:46:54', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88535045b201535a8ebd50003f', '8a225b884fcffba9014fd5400afe0082', 'ychStorePhotos', '0946c21b75b3464ea71d638606d6b227.jpg', 'jpg', '0', '2016-03-09 16:46:57', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88535045b201535a8f47ff0040', '8a225b884fcffba9014fd5400afe0082', 'ychStorePhotos', '7bb722744d124d44a59f8c5b81f62310.jpg', 'jpg', '0', '2016-03-09 16:47:32', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88535045b201535a8f4f7c0041', '8a225b884fcffba9014fd5400afe0082', 'ychStorePhotos', '9c4a0095ae0c405483fdc27bc38a4a36.jpg', 'jpg', '0', '2016-03-09 16:47:34', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88535045b201535a8f528b0042', '8a225b884fcffba9014fd5400afe0082', 'ychStorePhotos', 'a5c47eb082864fe68a6d4c0a89a25fc4.jpg', 'jpg', '0', '2016-03-09 16:47:35', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88535045b201535a8f54670043', '8a225b884fcffba9014fd5400afe0082', 'ychStorePhotos', '84ac28a09927402488d2b05692425d44.jpg', 'jpg', '0', '2016-03-09 16:47:35', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88535045b201535e05d599005f', '40288896532bb75601532bb916340000', 'ychStorePhotos', '4238466a8d894e08bcf41b5cef07b037.jpg', 'jpg', '1', '2016-03-10 08:55:53', '2016-03-21 08:23:07', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88535045b201535e05d9a60060', '40288896532bb75601532bb916340000', 'ychStorePhotos', '30ab9ef5b4f54cc091f84b5eb81c994f.jpg', 'jpg', '1', '2016-03-10 08:55:55', '2016-03-21 08:23:07', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88535045b201535e05f6c90061', '8a7b87dc5340b256015344898f860010', 'ychStorePhotos', '402371e4360a45a786d3efd7d10a9d4b.jpg', 'jpg', '1', '2016-03-10 08:56:02', '2016-03-26 10:14:46', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88535045b201535e05fbb70062', '8a7b87dc5340b256015344898f860010', 'ychStorePhotos', '5bc3f752390e43a18515b4cef3a3541c.jpg', 'jpg', '1', '2016-03-10 08:56:03', '2016-03-26 10:14:46', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88535045b201535e0617160063', '8a7b87dc5340b256015344898f860010', 'ychStorePhotos', '35f7d9d7dfb2432a87eaafeb19382518.jpg', 'jpg', '1', '2016-03-10 08:56:10', '2016-03-26 10:14:46', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88535045b201535e065a2c0064', '8a7b87dc5340b256015344898f860010', 'ychStorePhotos', 'f8ba706808eb480a95f365570074f5c8.jpg', 'jpg', '1', '2016-03-10 08:56:27', '2016-03-26 10:14:46', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88535045b201535f02ea1d00b2', '4028888b52b40c910152b46259510000', 'ychStorePhotos', '211f98c2cdd240dfb9016e2ecb9e677b.png', 'png', '1', '2016-03-10 13:32:19', '2016-03-18 11:37:41', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88535045b2015364636a7e00e7', '8a225b88535045b2015363b866bd00d9', 'ychStorePhotos', 'e68909f6e9c144d786bee7a14aee2510.jpg', 'jpg', '0', '2016-03-11 14:35:50', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88535045b201536472737700e9', '8a225b88535045b2015363b866bd00d9', 'ychStorePhotos', '4d99f9753e73421cb2306a952fb07738.jpg', 'jpg', '0', '2016-03-11 14:52:15', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88535045b2015372c26125012f', '8a7b87dc5340b256015344898f860010', 'ychStorePhotos', '0dd615e304de4bf9860639ef25ab149e.jpg', 'jpg', '1', '2016-03-14 09:34:14', '2016-03-26 10:14:46', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88535045b2015372c261690130', '8a7b87dc5340b256015344898f860010', 'ychStorePhotos', '445d64ca1ca744c19a4e4f7363c54b08.jpg', 'jpg', '1', '2016-03-14 09:34:14', '2016-03-26 10:14:46', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88535045b2015372c262b70131', '8a7b87dc5340b256015344898f860010', 'ychStorePhotos', '1a9fe37a20c94b31a6ac620643a73e2c.jpg', 'jpg', '1', '2016-03-14 09:34:15', '2016-03-26 10:14:46', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88535045b2015372c26ba70132', '8a7b87dc5340b256015344898f860010', 'ychStorePhotos', '0a46a1a6c6ed4e00a5fe45ab5606ba24.jpg', 'jpg', '1', '2016-03-14 09:34:17', '2016-03-17 08:30:35', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88537931ed0153795f781b0021', '8a7b87dc533ff2400153400c70b40000', 'ychStorePhotos', 'caab7debbc5946ef92322004dfd80a91.jpg', 'jpg', '1', '2016-03-15 16:23:32', '2016-03-15 16:23:44', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88537931ed0153795fa3c20022', '8a7b87dc533ff2400153400c70b40000', 'ychStorePhotos', '1ef4fe6c9e9641668d57f69b0ba1d01f.jpg', 'jpg', '0', '2016-03-15 16:23:44', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8853796b22015381fb2cc10101', '8a7b87dc5340b256015344898f860010', 'ychStorePhotos', 'd744b953318145e1bf197a1d44a0ba87.jpg', 'jpg', '1', '2016-03-17 08:30:34', '2016-03-26 10:14:46', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885387cbde015387ccd6f00003', '4028888b52b40c910152b462702c0001', 'ychHead', '650842c19fff45398fde37e6d84901ed.jpg', 'jpg', '0', '2016-03-18 11:37:41', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885387cbde015387cd31960004', '4028888b52b40c910152b46259510000', 'ychStorePhotos', 'e09bbcffc4284bbf886bd50dd794d0c1.jpg', 'jpg', '1', '2016-03-18 11:38:04', '2016-03-18 11:46:36', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885387cbde015387ce5f450005', '4028888b52b40c910152b462702c0001', 'ychHead', '399f379e5f2c4eb4b80276a9da16a332.jpg', 'jpg', '0', '2016-03-18 11:39:22', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885387d3cb015387d4faac0000', '4028888b52b40c910152b462702c0001', 'ychHead', 'b83e893f8b4a4c1f8aaebf78734017e0.jpg', 'jpg', '1', '2016-03-18 11:46:35', '2016-04-28 15:10:53', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885387d3cb015387d4fc610001', '4028888b52b40c910152b46259510000', 'ychStorePhotos', 'b90ea377ad5449289ad3f22bce279ec8.jpg', 'jpg', '1', '2016-03-18 11:46:35', '2016-03-18 11:58:01', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885387d3cb015387d4fd900002', '4028888b52b40c910152b46259510000', 'ychStorePhotos', 'e30188b6b6804718b87c73108c2c1252.jpg', 'jpg', '1', '2016-03-18 11:46:35', '2016-03-18 11:58:01', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885387d3cb015387d4fe5e0003', '4028888b52b40c910152b46259510000', 'ychStorePhotos', '8f8462372a2b4490a5965bd6dacf666f.jpg', 'jpg', '1', '2016-03-18 11:46:36', '2016-03-18 11:58:01', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88538d1d6a0153968dc9a4000a', '40288896532bb75601532bb916340000', 'ychStorePhotos', 'd4f550b551a8458899b205ad22a2c30c.jpg', 'jpg', '0', '2016-03-21 08:23:07', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88538d1d6a015396c064370025', '8a7b87dc5340b256015344920768001d', 'ychHead', '3d405e41d0a04bdb82bfa36a098d743e.jpg', 'jpg', '0', '2016-03-21 09:18:24', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b88538d1d6a01539cde241f00d5', '8a225b88538d1d6a01539c7bfb1e00bb', 'ychStorePhotos', '1c4f572b1a9a49f6a9c439ad998456da.jpg', 'jpg', '1', '2016-03-22 13:48:37', '2016-03-28 11:29:15', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8853a60d5c0153b0b3ca4301db', '8a7b87dc5340b256015344898f860010', 'ychStorePhotos', '027159804b544c8c81b2dec817497f83.jpg', 'jpg', '1', '2016-03-26 10:14:45', '2016-03-26 10:15:14', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8853a60d5c0153b0b43ab901dc', '8a7b87dc5340b256015344898f8a0011', 'ychHead', '2df11d3dafcc44ca9d33db164265a223.jpg', 'jpg', '1', '2016-03-26 10:15:14', '2016-03-26 10:17:51', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8853a60d5c0153b0b69f7301dd', '8a7b87dc5340b256015344898f8a0011', 'ychHead', '4edd2d4b09314b7ab22a340f32fe47da.jpg', 'jpg', '1', '2016-03-26 10:17:51', '2016-03-26 10:19:32', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8853a60d5c0153b0b82a6a01de', '8a7b87dc5340b256015344898f8a0011', 'ychHead', 'a9a482136f5f4a3c9e305649520565b4.jpg', 'jpg', '1', '2016-03-26 10:19:32', '2016-03-26 10:19:52', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8853a60d5c0153b0b876bd01df', '8a7b87dc5340b256015344898f8a0011', 'ychHead', 'c0bfd565b57946a3b241130738be5176.jpg', 'jpg', '0', '2016-03-26 10:19:52', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b8853eab9550153eaca028c0001', '8a7b87dc533ff2400153400c70c10001', 'ychHead', '483b00c58136439cbd1b7c860fe396f2.jpg', 'jpg', '0', '2016-04-06 16:57:00', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a225b885456850401545bb4c099006d', '4028888b52b40c910152b462702c0001', 'ychHead', '79dea39f3d2842f79442ff23a08d5a6f.jpg', 'jpg', '0', '2016-04-28 15:10:52', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a7b87dc532fff040153360cd69e0004', '2c996981531253f10153126026b20002', 'head', '55fefdf5128d4f8386c9ac0e619cbbdbpng', null, '1', '2016-03-02 14:38:43', '2016-03-02 15:54:59', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a7b87dc532fff0401533652a61f0005', '2c996981531253f10153126026b20002', 'head', '43d64b4ee1464906b96ce7549e8fc452png', null, '0', '2016-03-02 15:54:59', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a7b87dc533ff2400153400d97760002', '8a7b87dc533ff2400153400c70b40000', 'head', '7dd3b0a699cb40eb9f5b7c145f9d5a4e.jpg', 'jpg', '0', '2016-03-04 13:15:45', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a7b87dc5340b2560153449169bd001b', '8a7b87dc5340b2560153448a58c10012', 'head', '084ba6b2c94a4672acf91e37cf37eb16.jpg', 'jpg', '0', '2016-03-05 10:18:13', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a7b87dc5340b25601534497f8ee0027', '8a7b87dc5340b256015344898f860010', 'head', 'dc5da3fef49d4fceb3552c7c5b9e777b.jpg', 'jpg', '1', '2016-03-05 10:25:23', '2016-03-05 10:25:42', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a7b87dc5340b2560153449843aa0028', '8a7b87dc5340b256015344898f860010', 'head', '06abe3a1192049adb2053643f2f42be5.jpg', 'jpg', '1', '2016-03-05 10:25:42', '2016-03-05 10:26:36', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a7b87dc5340b25601534499163e0029', '8a7b87dc5340b256015344898f860010', 'head', '8c3681ca4f104843871624175b33a52a.jpg', 'jpg', '0', '2016-03-05 10:26:36', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a7b87dc5340b256015344a2172b0036', '8a7b87dc5340b256015344a173d40034', 'head', 'c4d21996d08d477f925eced9cc8e6ea0.jpg', 'jpg', '0', '2016-03-05 10:36:26', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a7b87dc5340b256015344dbdd670060', '40288896532bb75601532bb916340000', 'head', '914dac5be1414457920b2e910ac4b5f9.jpg', 'jpg', '1', '2016-03-05 11:39:33', '2016-03-05 11:43:17', null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a7b87dc5340b256015344df4ae30068', '40288896532bb75601532bb916340000', 'head', '98455dcac1914fbc9f9ad336c97d3d3f.jpg', 'jpg', '0', '2016-03-05 11:43:17', null, null, null, null, null, null);
INSERT INTO `ybt_images` VALUES ('8a7b87dc534ec9bf01534ed628420007', '4028888b52b40c910152b46259510000', 'head', '56dfcad4eeab47c69f4296a93dc3d8e6.png', 'png', '0', '2016-03-07 10:09:31', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `ybt_pingpp`
-- ----------------------------
DROP TABLE IF EXISTS `ybt_pingpp`;
CREATE TABLE `ybt_pingpp` (
  `y_id` varchar(100) NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `amount_refunded` int(11) DEFAULT NULL,
  `amount_settle` int(11) DEFAULT NULL,
  `app` varchar(255) DEFAULT NULL,
  `body` varchar(255) DEFAULT NULL,
  `channel` varchar(255) DEFAULT NULL,
  `charge_id` varchar(255) DEFAULT NULL,
  `client_ip` varchar(255) DEFAULT NULL,
  `created` bigint(20) DEFAULT NULL,
  `credential` varchar(255) DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `extra` varchar(255) DEFAULT NULL,
  `failure_code` varchar(255) DEFAULT NULL,
  `failure_msg` varchar(255) DEFAULT NULL,
  `id` varchar(255) DEFAULT NULL,
  `livemode` tinyint(1) DEFAULT NULL,
  `metadata` varchar(255) DEFAULT NULL,
  `object` varchar(255) DEFAULT NULL,
  `order_no` varchar(255) DEFAULT NULL,
  `paid` tinyint(1) DEFAULT NULL,
  `recipient` varchar(255) DEFAULT NULL,
  `refunded` tinyint(1) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `succeed` tinyint(1) DEFAULT NULL,
  `time_expire` bigint(20) DEFAULT NULL,
  `time_paid` bigint(20) DEFAULT NULL,
  `time_settle` bigint(20) DEFAULT NULL,
  `time_succeed` bigint(20) DEFAULT NULL,
  `time_transferred` bigint(20) DEFAULT NULL,
  `transaction_no` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`y_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ybt_pingpp
-- ----------------------------

-- ----------------------------
-- Table structure for `ybt_sms`
-- ----------------------------
DROP TABLE IF EXISTS `ybt_sms`;
CREATE TABLE `ybt_sms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `states` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ybt_sms
-- ----------------------------
INSERT INTO `ybt_sms` VALUES ('1', '436841', '【芸备胎】您的验证码是436841，30分钟内有效。如非本人操作，请忽略本短信', '2016-03-07 14:26:16', '13521880408', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('2', '878718', '【芸备胎】您的验证码是878718，30分钟内有效。如非本人操作，请忽略本短信', '2016-03-07 14:31:44', '13521880408', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('3', '712910', '【芸备胎】您的验证码是712910，30分钟内有效。如非本人操作，请忽略本短信', '2016-03-07 14:32:16', '18514640741', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('4', '874975', '【芸备胎】您的验证码是874975，30分钟内有效。如非本人操作，请忽略本短信', '2016-03-10 16:35:16', '13718296652', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('5', '305749', '【芸备胎】您的验证码是305749，30分钟内有效。如非本人操作，请忽略本短信', '2016-03-11 08:57:54', '18500153156', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('6', '020791', '【芸备胎】您的验证码是020791，30分钟内有效。如非本人操作，请忽略本短信', '2016-03-19 12:53:38', '18514765532', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('7', '721210', '【芸备胎】您的验证码是721210，30分钟内有效。如非本人操作，请忽略本短信', '2016-03-21 09:15:52', '18701622703', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('8', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！145862632500198，商品名称：米其林 ENERGY XM 2 韧悦 205/55R16 91 MECHELIN ，数量：1个，联系人：杨敬波，联系电话：18201652522，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-03-22 13:58:45', '13718815308', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('9', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！145862790000160，商品名称：米其林 ENERGY XM 2 韧悦 205/55R16 91 MECHELIN ，数量：4个，联系人：杨敬波，联系电话：18201652522，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-03-22 14:25:00', '13718815308', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('10', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！145863202700185，商品名称：米其林 DIAMARIS 285/45R19* 107V MECHELIN ，数量：1个，联系人：杨敬波，联系电话：18201652522，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-03-22 15:33:47', '15010006964', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('11', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！145863215800114，商品名称：米其林 DIAMARIS 285/45R19* 107V MECHELIN ，数量：1个，联系人：杨敬波，联系电话：18201652522，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-03-22 15:35:58', '15010006964', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('12', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！145863655000128，商品名称：米其林 DIAMARIS 285/45R19* 107V MECHELIN ，数量：1个，联系人：杨敬波，联系电话：18201652522，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-03-22 16:49:10', '15010006964', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('13', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！145863678100118，商品名称：米其林 DIAMARIS 285/45R19* 107V MECHELIN ，数量：1个，联系人：杨敬波，联系电话：18201652522，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-03-22 16:53:01', '15010006964', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('14', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！145863687700136，商品名称：米其林 DIAMARIS 285/45R19* 107V MECHELIN ，数量：1个，联系人：杨敬波，联系电话：18201652522，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-03-22 16:54:37', '15010006964', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('15', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！145863703900184，商品名称：米其林 DIAMARIS 285/45R19* 107V MECHELIN ，数量：1个，联系人：杨敬波，联系电话：18201652522，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-03-22 16:57:19', '15010006964', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('16', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！145870096200127，商品名称：米其林 DIAMARIS 285/45R19* 107V MECHELIN ，数量：1个，联系人：王琳，联系电话：18500153156，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-03-23 10:42:42', '15010006964', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('17', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！145870125200187，商品名称：米其林 DIAMARIS 285/45R19* 107V MECHELIN ，数量：1个，联系人：王琳，联系电话：18500153156，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-03-23 10:47:32', '15010006964', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('18', '419495', '【芸备胎】您的验证码是419495，30分钟内有效。如非本人操作，请忽略本短信', '2016-03-23 16:54:12', '13911401639', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('19', '465778', '【芸备胎】您的验证码是465778，30分钟内有效。如非本人操作，请忽略本短信', '2016-03-23 16:55:29', '13911401639', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('20', '740350', '【芸备胎】您的验证码是740350，30分钟内有效。如非本人操作，请忽略本短信', '2016-03-23 16:55:54', '13911401639', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('21', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！145878322000118，商品名称：米其林 DIAMARIS 285/45R19* 107V MECHELIN ，数量：1个，联系人：xxx，联系电话：13511006179，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-03-24 09:33:40', '15010006968', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('22', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！145914972200113，商品名称：米其林 DIAMARIS 285/45R19* 107V MECHELIN ，数量：1个，联系人：xxx，联系电话：13511006179，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-03-28 15:22:03', '15010006968', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('23', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！145915483800118，商品名称：米其林 ENERGY XM 2 韧悦 175/65R15 84H MECHELIN ，数量：4个，联系人：杨敬波，联系电话：18201652522，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-03-28 16:47:18', '18310151520', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('24', '923510', '【芸备胎】您的验证码是923510，30分钟内有效。如非本人操作，请忽略本短信', '2016-03-29 13:45:16', '13261147520', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('25', '512475', '【芸备胎】您的验证码是512475，30分钟内有效。如非本人操作，请忽略本短信', '2016-03-29 13:45:19', '13261147520', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('26', '032221', '【芸备胎】您的验证码是032221，30分钟内有效。如非本人操作，请忽略本短信', '2016-03-29 18:00:01', '13041626689', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('27', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！145950287100112，商品名称：阿波罗 112 185/30R15 86M  【芸备胎测试】 ，数量：1个，联系人：花花，联系电话：15010006968，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-01 17:27:51', '13581566690', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('28', '288588', '【芸备胎】您的验证码是288588，30分钟内有效。如非本人操作，请忽略本短信', '2016-04-05 12:57:19', '15811199954', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('29', '685510', '【芸备胎】您的验证码是685510，30分钟内有效。如非本人操作，请忽略本短信', '2016-04-05 17:10:20', '13811868371', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('30', '400188', '【芸备胎】您的验证码是400188，30分钟内有效。如非本人操作，请忽略本短信', '2016-04-08 12:42:53', '18910591508', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('31', '750303', '【芸备胎】您的验证码是750303，30分钟内有效。如非本人操作，请忽略本短信', '2016-04-08 13:05:06', '18910596575', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('32', '336855', '【芸备胎】您的验证码是336855，30分钟内有效。如非本人操作，请忽略本短信', '2016-04-08 13:11:18', '18910591508', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('33', '993869', '【芸备胎】您的验证码是993869，30分钟内有效。如非本人操作，请忽略本短信', '2016-04-20 15:19:54', '15210211497', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('34', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146163832400180，商品名称：韩泰 K407 195/65R15 V HANKOOK ，数量：2个，联系人：衡超辉，联系电话：15321005750，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-26 10:38:44', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('35', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146163832440105，商品名称：韩泰 K407 195/65R15 V HANKOOK ，数量：2个，联系人：杨帆，联系电话：15801676924，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-26 10:38:44', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('36', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146163837900112，商品名称：米其林 ENERGY XM 2 韧悦 205/55R16 91V MECHELIN ，数量：11个，联系人：杨帆，联系电话：15801676924，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-26 10:39:39', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('37', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146164015300123，商品名称：米其林 DIAMARIS 235/60R18 103V MECHELIN【 测试】 ，数量：100个，联系人：杨帆，联系电话：15801676924，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-26 11:09:13', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('38', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146164035300182，商品名称：米其林 DIAMARIS 235/60R18 103V MECHELIN【 测试】 ，数量：1个，联系人：衡超辉，联系电话：15321005750，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-26 11:12:33', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('39', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146164036300134，商品名称：米其林 DIAMARIS 235/60R18 103V MECHELIN【 测试】 ，数量：50个，联系人：杨帆，联系电话：15801676924，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-26 11:12:43', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('40', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146164042400179，商品名称：米其林 DIAMARIS 235/60R18 103V MECHELIN【 测试】 ，数量：2个，联系人：王琳，联系电话：18500153156，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-26 11:13:44', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('41', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146164056100183，商品名称：米其林 ENERGY XM 2 韧悦 215/70R15 98H MECHELIN【测试】 米其林 DIAMARIS 235/60R18 103V MECHELIN【 测试】 ，数量：2个，联系人：衡超辉，联系电话：15321005750，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-26 11:16:01', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('42', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146164063500162，商品名称：米其林 ENERGY XM 2 韧悦 215/70R15 98H MECHELIN【测试】 米其林 DIAMARIS 235/60R18 103V MECHELIN【 测试】 ，数量：2个，联系人：王琳，联系电话：18500153156，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-26 11:17:15', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('43', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146164276800192，商品名称：米其林 ENERGY XM 2 韧悦 205/55R16 91V MECHELIN ，数量：2个，联系人：金双江，联系电话：13581566690，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-26 11:52:48', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('44', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146165229300185，商品名称：米其林 ENERGY XM 2 韧悦 205/55R16 91V MECHELIN ，数量：3个，联系人：金双江，联系电话：13581566690，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-26 14:31:33', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('45', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146165247600108，商品名称：韩泰 K407 195/65R15 V HANKOOK ，数量：2个，联系人：金双江，联系电话：13581566690，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-26 14:34:36', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('46', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146165266200184，商品名称：米其林 ENERGY XM 2 韧悦 205/55R16 91V MECHELIN ，数量：2个，联系人：金双江，联系电话：13581566690，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-26 14:37:42', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('47', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146165327200156，商品名称：米其林 ENERGY XM 2 韧悦 215/60R16 95H MECHELIN ，数量：2个，联系人：花花，联系电话：15010006968，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-26 14:47:52', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('48', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146165865500104，商品名称：韩泰 K407 195/65R15 V HANKOOK ，数量：4个，联系人：徐清，联系电话：18601059596，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-26 16:17:35', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('49', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146165895800190，商品名称：韩泰 K407 195/65R15 V HANKOOK 韩泰 K407 205/55R16 91V HANKOOK ，数量：4个，联系人：王琳，联系电话：18500153156，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-26 16:22:38', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('50', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146171800300180，商品名称：韩泰 K407 195/65R15 V HANKOOK ，数量：2个，联系人：金双江，联系电话：13581566690，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-27 08:46:43', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('51', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146171914000190，商品名称：韩泰 K407 195/65R15 V HANKOOK ，数量：2个，联系人：金双江，联系电话：13581566690，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-27 09:05:40', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('52', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146171960600123，商品名称：米其林 DIAMARIS 235/60R18 103V MECHELIN【 测试】 ，数量：2个，联系人：金双江，联系电话：13581566690，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-27 09:13:26', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('53', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146171963100160，商品名称：米其林 DIAMARIS 235/60R18 103V MECHELIN【 测试】 ，数量：1个，联系人：衡超辉，联系电话：15321005750，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-27 09:13:51', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('54', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146171969000141，商品名称：米其林 DIAMARIS 235/60R18 103V MECHELIN【 测试胎 购买无效】 ，数量：1个，联系人：张传勇，联系电话：18931546890，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-27 09:14:50', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('55', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146171969100174，商品名称：米其林 DIAMARIS 235/60R18 103V MECHELIN【 测试胎 购买无效】 ，数量：2个，联系人：金双江，联系电话：13581566690，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-27 09:14:51', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('56', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146171972100153，商品名称：米其林 DIAMARIS 235/60R18 103V MECHELIN【 测试胎 购买无效】 ，数量：1个，联系人：张传勇，联系电话：18931546890，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-27 09:15:21', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('57', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146171976300197，商品名称：米其林 DIAMARIS 235/60R18 103V MECHELIN【 测试胎 购买无效】 ，数量：1个，联系人：张传勇，联系电话：18931546890，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-27 09:16:03', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('58', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146171976900137，商品名称：米其林 DIAMARIS 235/60R18 103V MECHELIN【 测试胎 购买无效】 ，数量：1个，联系人：衡超辉，联系电话：15321005750，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-27 09:16:09', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('59', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146172001500170，商品名称：米其林 DIAMARIS 235/60R18 103V MECHELIN【 测试胎 购买无效】 ，数量：1个，联系人：张传勇，联系电话：18931546890，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-27 09:20:15', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('60', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146172094800173，商品名称：米其林 DIAMARIS 235/60R18 103V MECHELIN【 测试胎 购买无效】 ，数量：1个，联系人：王琳，联系电话：18500153156，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-27 09:35:48', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('61', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146172113000102，商品名称：米其林 DIAMARIS 235/60R18 103V MECHELIN【 测试胎 购买无效】 ，数量：1个，联系人：王琳，联系电话：18500153156，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-27 09:38:50', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('62', '716680', '【芸备胎】您的验证码是716680，30分钟内有效。如非本人操作，请忽略本短信', '2016-04-27 15:15:42', '13161661219', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('63', '293149', '【芸备胎】您的验证码是293149，30分钟内有效。如非本人操作，请忽略本短信', '2016-04-27 15:16:49', '13161661219', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('64', '437634', '【芸备胎】您的验证码是437634，30分钟内有效。如非本人操作，请忽略本短信', '2016-04-27 15:17:12', '13161661219', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('65', '898367', '【芸备胎】您的验证码是898367，30分钟内有效。如非本人操作，请忽略本短信', '2016-04-27 16:19:32', '15010440640', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('66', '976701', '【芸备胎】您的验证码是976701，30分钟内有效。如非本人操作，请忽略本短信', '2016-04-27 16:19:56', '15010440640', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('67', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146182049300100，商品名称：韩泰 K407 195/65R15 V HANKOOK 韩泰 K407 185/65R15 H HANKOOK ，数量：6个，联系人：，联系电话：13161661219，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-28 13:14:54', '13311293102', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('68', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146182049300100，商品名称：韩泰 K407 195/65R15 V HANKOOK 韩泰，数量：6个，联系人：，联系电话：13161661219，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-28 13:14:54', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('69', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146182970400141，商品名称：韩泰 K415 185/65R15 88H HANKOOK 韩泰 K407 195/65R15 V HANKOOK ，数量：6个，联系人：金双江，联系电话：13581566690，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-28 15:48:24', '13311293102', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('70', null, '通知类短信模板ID:11196; 短信内容：【芸备胎】您有一个新订单！146182970400141，商品名称：韩泰 K415 185/65R15 88H HANKOOK，数量：6个，联系人：金双江，联系电话：13581566690，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-28 15:48:24', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('71', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146191378900175订单内容：韩泰 K407 195/65R15 V HANKOOK【5个】; 米其林 ENERGY XM 2 韧悦 195/65R15【5个】; 联系人：花花，联系电话：15010006968，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-29 15:09:49', '13311293102', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('72', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146191378900175订单内容：韩泰 K407 195/65R15 V HANKOOK【5个】; 米其林 ENERGY XM 2 韧悦 195/65R15【5个】; 联系人：花花，联系电话：15010006968，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-29 15:09:49', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('73', '244045', '【芸备胎】您的验证码是244045，30分钟内有效。如非本人操作，请忽略本短信', '2016-04-29 16:09:38', '18201681858', '2', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('74', '478475', '【芸备胎】您的验证码是478475，30分钟内有效。如非本人操作，请忽略本短信', '2016-04-29 16:09:54', '18201681858', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('75', '528026', '【芸备胎】您的验证码是528026，30分钟内有效。如非本人操作，请忽略本短信', '2016-04-29 16:10:16', '18201681858', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('76', '839039', '【芸备胎】您的验证码是839039，30分钟内有效。如非本人操作，请忽略本短信', '2016-04-29 16:19:49', '13521592336', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('77', '785633', '【芸备胎】您的验证码是785633，30分钟内有效。如非本人操作，请忽略本短信', '2016-04-29 17:59:20', '18201681858', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('78', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146192503500182订单内容：韩泰 K407 205/55R16 91V HANKOOK【10个】; 米其林 ENERGY XM 2 韧悦 205/55R16【8个】; 联系人：张先生，联系电话：18201681858，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-29 18:17:15', '13311293102', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('79', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146192503500182订单内容：韩泰 K407 205/55R16 91V HANKOOK【10个】; 米其林 ENERGY XM 2 韧悦 205/55R16【8个】; 联系人：张先生，联系电话：18201681858，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-29 18:17:15', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('80', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146192519900109订单内容：米其林 ENERGY XM 2 韧悦 205/55R16【6个】; 韩泰 K407 205/55R16 91V HANKOOK【10个】; 联系人：张先生，联系电话：18201681858，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-29 18:19:59', '13311293102', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('81', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146192519900109订单内容：米其林 ENERGY XM 2 韧悦 205/55R16【6个】; 韩泰 K407 205/55R16 91V HANKOOK【10个】; 联系人：张先生，联系电话：18201681858，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-29 18:19:59', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('82', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146192540100114订单内容：米其林 ENERGY XM 2 韧悦 205/55R16【8个】; 联系人：张先生，联系电话：18201681858，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-29 18:23:21', '13311293102', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('83', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146192540100114订单内容：米其林 ENERGY XM 2 韧悦 205/55R16【8个】; 联系人：张先生，联系电话：18201681858，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-29 18:23:21', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('84', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146193424800111订单内容：米其林 ENERGY XM 2 韧悦 205/55R16【10个】; 韩泰 K407 205/55R16 91V HANKOOK【10个】; 联系人：张先生，联系电话：18201681858，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-29 20:50:48', '13311293102', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('85', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146193424800111订单内容：米其林 ENERGY XM 2 韧悦 205/55R16【10个】; 韩泰 K407 205/55R16 91V HANKOOK【10个】; 联系人：张先生，联系电话：18201681858，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-29 20:50:48', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('86', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146197749600195订单内容：米其林 ENERGY XM 2 韧悦 205/55R16【10个】; 联系人：李先生，联系电话：15010385660，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-30 08:51:36', '13311293102', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('87', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146197749600195订单内容：米其林 ENERGY XM 2 韧悦 205/55R16【10个】; 联系人：李先生，联系电话：15010385660，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-04-30 08:51:36', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('88', '942855', '【芸备胎】您的验证码是942855，30分钟内有效。如非本人操作，请忽略本短信', '2016-05-01 17:39:07', '13601104754', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('89', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146215728400175订单内容：米其林 ENERGY XM 2 韧悦 215/60R16【8个】; 联系人：李先生，联系电话：15010385660，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-05-02 10:48:04', '13311293102', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('90', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146215728400175订单内容：米其林 ENERGY XM 2 韧悦 215/60R16【8个】; 联系人：李先生，联系电话：15010385660，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-05-02 10:48:04', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('91', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146216781900138订单内容：米其林 ENERGY XM 2 韧悦 215/60R16【2个】; 联系人：李先生，联系电话：15010385660，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-05-02 13:43:39', '13311293102', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('92', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146216781900138订单内容：米其林 ENERGY XM 2 韧悦 215/60R16【2个】; 联系人：李先生，联系电话：15010385660，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-05-02 13:43:39', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('93', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146232929600172订单内容：米其林 ENERGY XM 2 韧悦 215/60R16【10个】; 联系人：李先生，联系电话：15010385660，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-05-04 10:34:56', '13311293102', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('94', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146232929600172订单内容：米其林 ENERGY XM 2 韧悦 215/60R16【10个】; 联系人：李先生，联系电话：15010385660，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-05-04 10:34:56', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('95', '816382', '【芸备胎】您的验证码是816382，30分钟内有效。如非本人操作，请忽略本短信', '2016-05-04 10:59:12', '15601215734', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('96', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146234085700111订单内容：米其林 ENERGY XM 2 韧悦 205/55R16【4个】; 联系人：李先生，联系电话：15010385660，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-05-04 13:47:37', '13311293102', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('97', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146234085700111订单内容：米其林 ENERGY XM 2 韧悦 205/55R16【4个】; 联系人：李先生，联系电话：15010385660，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-05-04 13:47:37', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('98', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146234091400187订单内容：米其林 ENERGY XM 2 韧悦 215/60R16【6个】; 联系人：李先生，联系电话：15010385660，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-05-04 13:48:34', '13311293102', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('99', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146234091400187订单内容：米其林 ENERGY XM 2 韧悦 215/60R16【6个】; 联系人：李先生，联系电话：15010385660，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-05-04 13:48:34', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('100', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146241729100173订单内容：韩泰 K715 175/65R15 H HANKOOK【3个】; 联系人：金双江，联系电话：13581566690，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-05-05 11:01:31', '13311293102', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('101', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146241729100173订单内容：韩泰 K715 175/65R15 H HANKOOK【3个】; 联系人：金双江，联系电话：13581566690，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-05-05 11:01:31', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('102', '818086', '【芸备胎】您的验证码是818086，30分钟内有效。如非本人操作，请忽略本短信', '2016-05-05 14:18:17', '18612557838', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('103', '118648', '【芸备胎】您的验证码是118648，30分钟内有效。如非本人操作，请忽略本短信', '2016-05-05 14:18:55', '18612557838', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('104', '785438', '【芸备胎】您的验证码是785438，30分钟内有效。如非本人操作，请忽略本短信', '2016-05-05 14:19:18', '18612557838', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('105', '025904', '【芸备胎】您的验证码是025904，30分钟内有效。如非本人操作，请忽略本短信', '2016-05-05 14:19:27', '13121278866', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('106', '759944', '【芸备胎】您的验证码是759944，30分钟内有效。如非本人操作，请忽略本短信', '2016-05-05 14:20:38', '13381161868', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('107', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146329490500146订单内容：米其林 PRIMACY 3 ST 浩悦 225/55R16【2个】; 韩泰 K407 185/65R15 H HANKOOK【6个】; 联系人：，联系电话：13161661219，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-05-15 14:48:25', '13311293102', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('108', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146329490500146订单内容：米其林 PRIMACY 3 ST 浩悦 225/55R16【2个】; 韩泰 K407 185/65R15 H HANKOOK【6个】; 联系人：，联系电话：13161661219，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-05-15 14:48:25', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('109', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146329537900184订单内容：米其林 PRIMACY 3 ST 浩悦 225/55R16【2个】; 韩泰 K407 185/65R15 H HANKOOK【5个】; 联系人：，联系电话：13161661219，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-05-15 14:56:19', '13311293102', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('110', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146329537900184订单内容：米其林 PRIMACY 3 ST 浩悦 225/55R16【2个】; 韩泰 K407 185/65R15 H HANKOOK【5个】; 联系人：，联系电话：13161661219，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-05-15 14:56:20', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('111', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146344804300196订单内容：韩泰 K407 185/65R15 H HANKOOK【5个】; 联系人：张淼，联系电话：13552413884，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-05-17 09:20:43', '13311293102', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('112', null, '通知类短信模板ID:13449; 短信内容：【芸备胎采购系统】新订单！订单号：146344804300196订单内容：韩泰 K407 185/65R15 H HANKOOK【5个】; 联系人：张淼，联系电话：13552413884，请及时处理。详情查看http://www.yunbeitai.com/ych', '2016-05-17 09:20:43', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('113', '786892', '【芸备胎】您的验证码是786892，30分钟内有效。如非本人操作，请忽略本短信', '2016-05-18 13:42:16', '18611319942', '1', '1', null, null);
INSERT INTO `ybt_sms` VALUES ('114', null, '通知类短信模板ID:11196; 短信内容：【芸备胎采购系统】发货提醒！订单号：146422465200168订单内容：买方店铺名称：【北京绿天使汽车装饰有限公司】。商品清单：【1】普利司通 EP150 185/60R15 （4条）; 联系人：李新，联系电话：13521592336，请及时发货。详情查看http://www.yunbeitai.com/ych', '2016-05-26 09:04:12', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('115', null, '通知类短信模板ID:11196; 短信内容：【芸备胎采购系统】发货提醒！订单号：146434714000106订单内容：买方店铺名称：【北京天成广达汽车修理有限公司】。商品清单：【1】韩泰 K407 195/65R15 V HANKOOK（2条）; 联系人：，联系电话：13161661219，请及时发货。详情查看http://www.yunbeitai.com/ych', '2016-05-27 19:05:40', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('116', null, '通知类短信模板ID:11196; 短信内容：【芸备胎采购系统】发货提醒！订单号：146434714000106订单内容：买方店铺名称：【北京天成广达汽车修理有限公司】。商品清单：【1】韩泰 K407 195/65R15 V HANKOOK（2条）; 联系人：，联系电话：13161661219，请及时发货。详情查看http://www.yunbeitai.com/ych', '2016-05-27 19:06:02', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('117', null, '通知类短信模板ID:11196; 短信内容：【芸备胎采购系统】发货提醒！订单号：146526066200194订单内容：买方店铺名称：【北京天成广达汽车修理有限公司】。商品清单：【1】韩泰 K117 225/55R17 97Y HANKOOK（8条）; 联系人：，联系电话：13161661219，请及时发货。详情查看http://www.yunbeitai.com/ych', '2016-06-07 08:51:03', '13521880408', '1', '2', null, null);
INSERT INTO `ybt_sms` VALUES ('118', null, '通知类短信模板ID:11196; 短信内容：【芸备胎采购系统】发货提醒！订单号：146526066200194订单内容：买方店铺名称：【北京天成广达汽车修理有限公司】。商品清单：【1】韩泰 K117 225/55R17 97Y HANKOOK（8条）; 联系人：，联系电话：13161661219，请及时发货。详情查看http://www.yunbeitai.com/ych', '2016-06-07 08:58:50', '13521880408', '1', '2', null, null);

-- ----------------------------
-- Table structure for `ybt_statistic_sql`
-- ----------------------------
DROP TABLE IF EXISTS `ybt_statistic_sql`;
CREATE TABLE `ybt_statistic_sql` (
  `id` varchar(32) NOT NULL,
  `bz` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  `header_chs` longtext,
  `header_en` longtext,
  `sql_body` longtext,
  `sql_code` varchar(255) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `type_code` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `headerchs` varchar(255) DEFAULT NULL,
  `headeren` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ybt_statistic_sql
-- ----------------------------
INSERT INTO `ybt_statistic_sql` VALUES ('1', '不知道', '2016-04-29 13:17:40', '0', '访问者,访问次数,被访问者', 'FROM_NAME,COUNT,TO_NAME', 'select e.name as FROM_NAME,a.business_id B_ID,count(a.business_id) as COUNT,d.name  as TO_NAME,a.create_time from ybt_browse_records a,ybt_business e,(select b.name,c.id from ybt_business b,ybt_stock c where  b.id=c.business_id) d where a.business_id =e.id and a.good_id=d.id group by  a.business_id ', 'BUSINESS_PV2', '商户访问统计', '商户', 'BUSINESS', '0000-00-00 00:00:00', null, null);
INSERT INTO `ybt_statistic_sql` VALUES ('2', '2', '2016-05-04 13:46:20', '0', '店铺名,品牌,胎面宽,扁平比,直径,关键字,时间', 'B_NAME,BRAND,WIDE,DIAMETER,FLAT,SEARCH,CREATE_TIME', 'select a.name B_NAME,c.brand BRAND,b.wide WIDE,b.diameter DIAMETER,b.flat FLAT,b.search SEARCH,b.create_time CREATE_TIME from ybt_business a ,hot_tire_standard b,ybt_dm_brand c where a.id=b.bid and b.brand_id=c.brand_id union all select a.name,b.brand_id,b.wide,b.diameter,b.flat,b.search,b.create_time from ybt_business a ,hot_tire_standard b where a.id=b.bid and (b.brand_id IS NULL or b.brand_id=\'\')', 'BUSINESS_SEARCH', '商户搜索', '商户', 'BUSINESS', '0000-00-00 00:00:00', null, null);
INSERT INTO `ybt_statistic_sql` VALUES ('3', '3', '2016-05-06 08:46:08', '0', '店铺名,商品,点击量(不包含官方点击),销量', 'NAME,GOODS,COUNT,SALES_NUM', 'select m.`name` as NAME,m.concat GOODS,COUNT(m.id) COUNT ,m.sales_num SALES_NUM from (    SELECT CONCAT_WS( \'\',b.brand, \' \', p.pattern,\' \', t.model,\' \',l.load_id,sp.speed_id,\' \',b.alias ) AS concat,st.pfbz,st.id,st.sales_num SALES_NUM,bs.`name`  FROM ybt_stock st, ybt_business bs,ybt_tire t  LEFT JOIN ybt_dm_brand b ON t.brand_id = b.brand_id AND b.deleted != 1  LEFT JOIN ybt_dm_load l ON t.load_id = l.load_id AND l.deleted != 1  LEFT JOIN ybt_dm_speed sp ON t.speed_id = sp.speed_id AND sp.deleted != 1  LEFT JOIN ybt_dm_pattern p ON t.pattern_id = p.pattern_id AND p.deleted != 1  WHERE st.business_id = bs.id and t.id = st.tire_id and st.deleted != 1  UNION ALL  SELECT CONCAT_WS( \'\',b.brand, \' \',t.pattern,\' \', t.model,\' \',l.loads,sp.speed,\' \',b.alias ) AS concat,st.pfbz,st.id,st.sales_num SALES_NUM ,bs.`name`  FROM ybt_stock st, ybt_business bs,ybt_tire_custom t   LEFT JOIN ybt_dm_brand b ON t.brand_id = b.brand_id AND b.deleted != 1   LEFT JOIN ybt_dm_load l ON t.load_id = l.load_id AND l.deleted != 1   LEFT JOIN ybt_dm_speed sp ON t.speed_id = sp.speed_id AND sp.deleted != 1  LEFT JOIN ybt_dm_pattern p ON t.pattern_id = p.pattern_id AND p.deleted != 1   WHERE st.business_id = bs.id and st.tire_c_id=t.id and st.deleted != 1  ) AS m  LEFT JOIN  (select a.*,bus.`name` from ybt_browse_records a,ybt_business bus where a.business_id=bus.id and bus.`name` not like \'%芸备胎%\') as h ON h.good_id=m.id LEFT JOIN  (select b.number, b.stock_id from ybt_order a,ybt_order_good b,ybt_business bus where a.id=b.order_id and a.buyer_id=bus.id and bus.`name` not like \'%芸备胎%\') as k ON h.good_id=k.stock_id	where  m.pfbz=1 group by m.id ', 'GOODS_PV', '采购商品点击量', '商品', 'GOODS', '0000-00-00 00:00:00', null, null);
INSERT INTO `ybt_statistic_sql` VALUES ('4', '4', '2016-05-23 14:42:01', '0', '店铺名,商品数量,最后上传时间', 'NAME,COUNT,CREATETIME', 'select b.`name` as NAME,CAST(COUNT(*) AS CHAR) as COUNT,date_format(t.create_time,\'%Y-%m-%d\') as CREATETIME from ybt_stock t ,ybt_business b\r\n where t.business_id=b.id and t.deleted=0 and t.released=1 GROUP BY t.business_id,date_format(t.create_time,\'%Y-%m-%d\') ', 'BUSINESS_UP_STOCK', '商户上传轮胎情况', '商户', 'BUSINESS', '0000-00-00 00:00:00', null, null);

-- ----------------------------
-- Table structure for `ybt_web_dept`
-- ----------------------------
DROP TABLE IF EXISTS `ybt_web_dept`;
CREATE TABLE `ybt_web_dept` (
  `deptid` varchar(100) NOT NULL,
  `deleted` varchar(2) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `parentid` varchar(100) DEFAULT NULL,
  `remarks` longtext,
  `roleid` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`deptid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ybt_web_dept
-- ----------------------------

-- ----------------------------
-- Table structure for `ybt_web_menu`
-- ----------------------------
DROP TABLE IF EXISTS `ybt_web_menu`;
CREATE TABLE `ybt_web_menu` (
  `menuid` varchar(100) NOT NULL,
  `isdisplay` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `parentid` varchar(100) DEFAULT NULL,
  `sortnum` int(11) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`menuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ybt_web_menu
-- ----------------------------
INSERT INTO `ybt_web_menu` VALUES ('0', '0', '根节点', '-1', '0', '');
INSERT INTO `ybt_web_menu` VALUES ('1', '0', '组织结构', '0', '5', '');
INSERT INTO `ybt_web_menu` VALUES ('2', '0', '角色管理', '1', '3', 'admin/role');
INSERT INTO `ybt_web_menu` VALUES ('4', '0', '用户管理', '1', '1', 'admin/user');
INSERT INTO `ybt_web_menu` VALUES ('402880e75543975a0155439b5b210000', '0', '系统管理', '0', '6', '');
INSERT INTO `ybt_web_menu` VALUES ('402880e75543975a0155439c4d260001', '0', '字典管理', '402880e75543975a0155439b5b210000', '1', '');
INSERT INTO `ybt_web_menu` VALUES ('402880e75543975a0155439cc55d0002', '0', '系统日志', '402880e75543975a0155439b5b210000', '2', '');
INSERT INTO `ybt_web_menu` VALUES ('4028888d547f800b01547f9354730000', '0', '商品管理', '0', '5', '');
INSERT INTO `ybt_web_menu` VALUES ('4028888d547f800b01547f95471c0002', '0', '图片管理', '4028888d547f800b01547f9354730000', '2', 'admin/business/stockListView');
INSERT INTO `ybt_web_menu` VALUES ('4028889253c160670153c166fc780001', '0', '财务管理', '0', '5', '');
INSERT INTO `ybt_web_menu` VALUES ('40288896547e83c501547eea3bd20000', '0', '报表管理', '0', '6', '');
INSERT INTO `ybt_web_menu` VALUES ('40288896547e83c501547eeaa5d90001', '0', '报表查询', '40288896547e83c501547eea3bd20000', '1', 'statistic/getStatistic');
INSERT INTO `ybt_web_menu` VALUES ('6', '0', '部门管理', '1', '2', 'admin/dept');
INSERT INTO `ybt_web_menu` VALUES ('7', '0', '菜单管理', '1', '4', 'admin/menu');

-- ----------------------------
-- Table structure for `ybt_web_menu_role`
-- ----------------------------
DROP TABLE IF EXISTS `ybt_web_menu_role`;
CREATE TABLE `ybt_web_menu_role` (
  `id` varchar(100) NOT NULL,
  `menuid` varchar(100) DEFAULT NULL,
  `roleid` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ybt_web_menu_role
-- ----------------------------
INSERT INTO `ybt_web_menu_role` VALUES ('0', '0', '8a8180b2490d23dc01490dde58a90143');
INSERT INTO `ybt_web_menu_role` VALUES ('1', '1', '8a8180b2490d23dc01490dde58a90143');
INSERT INTO `ybt_web_menu_role` VALUES ('2', '2', '8a8180b2490d23dc01490dde58a90143');
INSERT INTO `ybt_web_menu_role` VALUES ('4', '4', '8a8180b2490d23dc01490dde58a90143');
INSERT INTO `ybt_web_menu_role` VALUES ('402880e75543975a0155439e80df0003', '402880e75543975a0155439b5b210000', '8a8180b2490d23dc01490dde58a90143');
INSERT INTO `ybt_web_menu_role` VALUES ('402880e75543975a0155439e80f10004', '402880e75543975a0155439c4d260001', '8a8180b2490d23dc01490dde58a90143');
INSERT INTO `ybt_web_menu_role` VALUES ('402880e75543975a0155439e80f50005', '402880e75543975a0155439cc55d0002', '8a8180b2490d23dc01490dde58a90143');
INSERT INTO `ybt_web_menu_role` VALUES ('4028888c52825ef80152826ba28d0001', '5', '4028888c52825ef801528265ff880000');
INSERT INTO `ybt_web_menu_role` VALUES ('4028888c52825ef80152826bc1670002', '5', '8a8180b2490d23dc01490dde58a90143');
INSERT INTO `ybt_web_menu_role` VALUES ('8a225b8854db91aa0154db95f2d40000', '', '8a8180b2490d23dc01490dde58a90143');
INSERT INTO `ybt_web_menu_role` VALUES ('8a225b8854db91aa0154db9ab6c50001', '6', '8a8180b2490d23dc01490dde58a90143');
INSERT INTO `ybt_web_menu_role` VALUES ('8a225b8854db91aa0154db9ab6d90002', '7', '8a8180b2490d23dc01490dde58a90143');
INSERT INTO `ybt_web_menu_role` VALUES ('8a225b8854db91aa0154db9cfa110003', '4028888d547f800b01547f9354730000', '8a8180b2490d23dc01490dde58a90143');
INSERT INTO `ybt_web_menu_role` VALUES ('8a225b8854db91aa0154db9cfa220004', '4028888d547f800b01547f95471c0002', '8a8180b2490d23dc01490dde58a90143');
INSERT INTO `ybt_web_menu_role` VALUES ('8a225b8854db91aa0154db9cfa340005', '4028889253c160670153c166fc780001', '8a8180b2490d23dc01490dde58a90143');
INSERT INTO `ybt_web_menu_role` VALUES ('8a225b8854db91aa0154db9cfa430006', '4028889253c160670153c16821a10002', '8a8180b2490d23dc01490dde58a90143');
INSERT INTO `ybt_web_menu_role` VALUES ('8a225b8854db91aa0154db9cfa540007', '4028889253c195060153c198cd060002', '8a8180b2490d23dc01490dde58a90143');
INSERT INTO `ybt_web_menu_role` VALUES ('8a225b8854db91aa0154db9cfa600008', '40288896547e83c501547eeaa5d90001', '8a8180b2490d23dc01490dde58a90143');
INSERT INTO `ybt_web_menu_role` VALUES ('8a225b8854db91aa0154dba1be3c0009', '40288896547e83c501547eea3bd20000', '8a8180b2490d23dc01490dde58a90143');

-- ----------------------------
-- Table structure for `ybt_web_role`
-- ----------------------------
DROP TABLE IF EXISTS `ybt_web_role`;
CREATE TABLE `ybt_web_role` (
  `roleid` varchar(100) NOT NULL,
  `code` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `parentid` varchar(100) DEFAULT NULL,
  `remarks` longtext,
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ybt_web_role
-- ----------------------------
INSERT INTO `ybt_web_role` VALUES ('0', 'root', '根角色', '-1', null);
INSERT INTO `ybt_web_role` VALUES ('4028888c52825ef801528265ff880000', 'business', 'business', '0', '商户老板');
INSERT INTO `ybt_web_role` VALUES ('8a8180b2490d23dc01490dde58a90143', 'admin', 'admin', '0', '超级管理员');
INSERT INTO `ybt_web_role` VALUES ('8a8180b2490d23dc01490de015a80153', 'manager', 'manager', '0', '业务系统管理员');

-- ----------------------------
-- Table structure for `ybt_web_user`
-- ----------------------------
DROP TABLE IF EXISTS `ybt_web_user`;
CREATE TABLE `ybt_web_user` (
  `userid` varchar(100) NOT NULL,
  `birth` varchar(50) DEFAULT NULL,
  `createtime` varchar(50) DEFAULT NULL,
  `deleted` varchar(2) DEFAULT NULL,
  `deptid` varchar(100) DEFAULT NULL,
  `disable` varchar(2) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `mail` varchar(50) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `post` varchar(10) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ybt_web_user
-- ----------------------------
INSERT INTO `ybt_web_user` VALUES ('aaa', null, null, '0', '1', '0', null, null, null, 'test11', null, null, null);
INSERT INTO `ybt_web_user` VALUES ('admin', '', '2014-10-15', '0', '1', '0', '', '', '', '管理员', '6dd22d0a56476976e47c97fc77759d5252b32b68', '', '0f91f2e2004fda5a');

-- ----------------------------
-- Table structure for `ybt_web_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `ybt_web_user_role`;
CREATE TABLE `ybt_web_user_role` (
  `id` varchar(255) NOT NULL,
  `roleid` varchar(255) DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ybt_web_user_role
-- ----------------------------
INSERT INTO `ybt_web_user_role` VALUES ('40283f824845020b0148451f45c20000', '8a8180b2490d23dc01490dde58a90143', 'admin');

-- ----------------------------
-- Function structure for `DDH`
-- ----------------------------
DROP FUNCTION IF EXISTS `DDH`;
DELIMITER ;;
CREATE DEFINER=`Yunbeitai`@`%` FUNCTION `DDH`() RETURNS char(15) CHARSET utf8
    READS SQL DATA
BEGIN
    RETURN(SELECT CONCAT(unix_timestamp(now()),RIGHT(100001+IFNULL(MAX(SUBSTR(t.order_no, 10, 3)),0),3),RIGHT(101+ceil(rand()*99),2)) FROM ybt_order t where LEFT(t.order_no,10)= unix_timestamp(now()));
END
;;
DELIMITER ;
