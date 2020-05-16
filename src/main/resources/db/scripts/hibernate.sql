create table if not exists site (
       id int8 not null,
        created timestamp,
        login varchar(255) not null,
        password varchar(255) not null,
        site varchar(255),
        primary key (id)
    );

 create table if not exists url (
       code varchar(255) not null,
        statistic int8 not null,
        url varchar(255) not null,
        site_id int8,
        primary key (code)
    );

    alter table if exists site
       add constraint UK_qhmupqn5tv8uv0t7jr6vq92ji unique (site);

    alter table if exists url
       add constraint SITE_IF_FK
       foreign key (site_id)
       references site;