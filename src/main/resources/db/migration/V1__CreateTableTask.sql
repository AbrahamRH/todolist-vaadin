-- @author AbrahamRH
-- @Date 15/11/2024
-- @Description Create the table task

create table if not exists task (
    id          serial      not null primary key,
    name        varchar(20) not null constraint task_name_uk unique,
    description varchar(50)
);