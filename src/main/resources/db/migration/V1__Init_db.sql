 create sequence hibernate_sequence start 1 increment 1;

 create table movies (
                    id int8 not null,
                    created_at int8,
                     description varchar(255),
                      duration int4,
                       genre varchar(255),
                        last_modified int8,
                         release_date date,
                          title varchar(255),
                           primary key (id));