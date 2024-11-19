-- @author AbrahamRH
-- @Date 15/11/2024
-- @Description Create the table task


create sequence if not exists task_id_seq
    start with 1
    increment by 1;

create table task (
    task_id     bigint not null default nextval('task_id_seq'),
    name        varchar(50) not null,
    description varchar(200),
    completed   boolean default false,

    constraint pk_task primary key(task_id),
    unique(name)
);

alter sequence task_id_seq
    owned by task.task_id;