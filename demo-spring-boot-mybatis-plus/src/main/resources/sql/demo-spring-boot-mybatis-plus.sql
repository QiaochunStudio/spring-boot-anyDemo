-- ----------------------------
-- 商品表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `t_goods`
(
    `id`               BIGINT(20)                                             NOT NULL COMMENT '主键ID',
    `create_date_time` DATETIME                                               NOT NULL COMMENT '创建时间',
    `create_user_id`   BIGINT(20) COMMENT '创建者ID',
    `create_user_name` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '创建者名字',
    `update_date_time` DATETIME COMMENT '更新时间',
    `update_user_id`   BIGINT(20) COMMENT '更新者ID',
    `update_user_name` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '更新者名字',

    `enabled_state`    TINYINT(1) DEFAULT 0 COMMENT '启用状态：0-不启用；1-启用',
    `deleted_state`    TINYINT(1) DEFAULT 0 COMMENT '逻辑删除状态：0-不删除；1-删除',


    `name`             VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '商品名称',
    `price`            DECIMAL(11, 2)                                         NOT NULL COMMENT '商品单价',

    PRIMARY KEY (`id`)

) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT '商品表';