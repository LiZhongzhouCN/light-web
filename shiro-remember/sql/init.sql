INSERT INTO permission(id,url,description) VALUES ('1', '/user', 'user:user');
INSERT INTO permission(id,url,description) VALUES ('2', '/user/add', 'user:add');
INSERT INTO permission(id,url,description) VALUES ('3', '/user/delete', 'user:delete');

INSERT INTO role(id,description,name) VALUES ('1', 'admin', '超级管理员');
INSERT INTO role(id,description,name) VALUES ('2', 'user', '用户');

INSERT INTO role_permission(role_id,permission_id) VALUES ('1', '2');
INSERT INTO role_permission(role_id,permission_id) VALUES ('1', '3');
INSERT INTO role_permission(role_id,permission_id) VALUES ('2', '1');
INSERT INTO role_permission(role_id,permission_id) VALUES ('1', '1');

INSERT INTO user_role(user_id,role_id) VALUES ('1', '1');
INSERT INTO user_role(user_id,role_id) VALUES ('2', '2');

select t1.* from role t1 inner join user_role t2 on t1.id = t2.role_id inner join user t3 on t3.id = t2.user_id where t3.username = 'admin';

select t1.* from permission t1 inner join role_permission t2 on t1.id = t2.permission_id inner join user_role t3 on t3.role_id = t2.role_id inner join user t4 on t4.id = t3.user_id where t4.username = 'admin';

select * from jpa_test.user;