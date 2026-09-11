CREATE DATABASE IF NOT EXISTS `Questionnaire`;
USE `Questionnaire`;

create table user_info
(
    id        bigint auto_increment comment '用户的唯一标识码'
        primary key,
    phone     varchar(16)                  not null comment '用户的电话，唯一',
    name      varchar(64) default '户晨风' not null comment '用户的姓名',
    password  varchar(16) default 'yzunlp' not null comment '用户的密码',
    china_id  varchar(20)                  null comment '用户的身份证号',
    birthday  date                         null comment '用户的生日',
    sex       tinyint(1)                   null comment '用户的性别，男1，女2',
    area_code bigint                       null comment '用户所在地区编码',
    user_code varchar(20)                  null comment '用户唯一编号(登录凭证)',
    constraint user_info_k_phone
        unique (phone),
    constraint user_info_k_code
        unique (user_code)
)
    comment '用户基本信息';

create table user_health
(
    id                  bigint auto_increment
        primary key,
    hypertension        tinyint(1) not null comment '是否有高血压',
    hypertension_year   int        null comment '高血压(如果有)病程几年',
    hypertension_drug   tinyint(1) null comment '高血压(如果有)是否规律服药',
    diabetes            tinyint(1) not null comment '是否有糖尿病',
    diabetes_year       int        null comment '糖尿病(如果有)病程几年',
    diabetes_drug       tinyint(1) null comment '糖尿病(如果有)是否规律服药',
    hyperlipidemia      tinyint(1) not null comment '是否有高血脂',
    hyperlipidemia_year int        null comment '高血脂(如果有)病程几年',
    hyperlipidemia_drug tinyint(1) null comment '高血脂(如果有)是否规律服药',
    tumor               tinyint(1) not null comment '是否有肿瘤'
)
    comment '用户基础疾病调查情况';

create table info2health
(
    info_id   bigint not null comment '外键--user_info--id',
    health_id bigint not null comment '外键--user_health--id',
    id        bigint auto_increment comment '唯一标识号'
        primary key,
    constraint info2health___fk_health_id
        foreign key (health_id) references user_health (id),
    constraint info2health___fk_info_id
        foreign key (info_id) references user_info (id)
)
    comment '用户回访情况';


create table user_propolis
(
    id                 bigint auto_increment comment '蜂胶使用情况 唯一标识号'
        primary key,
    propolis           tinyint(1) not null comment '是否服用过蜂胶',
    propolis_year      year       null comment '从何时开始使用蜂胶',
    propolis_frequency varchar(2) null comment '蜂胶使用频率(单选: ABCD)',
    user_id            bigint     not null comment '外键--user_info--id',
    update_time        datetime   not null comment '上传时间',
    constraint user_propolis_user_info_id_fk
        foreign key (user_id) references user_info (id)
)
    comment '用户的蜂胶使用情况';

create table user_eval
(
    id          bigint auto_increment comment '主观评价唯一标识号'
        primary key,
    evaluation  varchar(16) not null comment '使用逗号分割，共六个评分(评分范围[0, 3])',
    user_id     bigint      not null comment '外键--user_info--id',
    update_time datetime    not null comment '上传时间',
    constraint user_eval_user_info_id_fk
        foreign key (user_id) references user_info (id)
)
    comment '用户的主观评价';

create table user_checkup_form
(
    id          bigint auto_increment comment '唯一表示号'
        primary key,
    user_id     bigint       not null comment '外键--user_info-id',
    pic_url     varchar(256) not null comment '体检单(图片)地址',
    update_time datetime     null comment '上传时间',
    constraint user_checkup_form_user_info_id_fk
        foreign key (user_id) references user_info (id)
)
    comment '用户体检单';