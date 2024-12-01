CREATE DATABASE todo CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE `todo`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT,
    `status`      varchar(32)  NOT NULL COMMENT '상태',
    `title`       varchar(256) NOT NULL COMMENT '제목',
    `content`     varchar(512) NOT NULL COMMENT '본문',
    `created_at`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `modified_at` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT 'todo';
CREATE INDEX idx__status ON todo (status);
