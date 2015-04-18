DELETE FROM user_activity;
DELETE FROM user_steps;
DELETE FROM user;
DELETE FROM activity;
DELETE FROM activity_level;
DELETE FROM tips;


INSERT INTO user VALUES ("embugge@hotmail.com","erik","secret");
INSERT INTO user VALUES ("bob@hotmail.com","bob","secret");

INSERT INTO activity VALUES ("running",22,33,44);
INSERT INTO activity VALUES ("skating",11,22,33);
INSERT INTO activity VALUES ("sailing",10,20,30);

INSERT INTO user_activity VALUES (1,"embugge@hotmail.com","running",1,5500,11111111111111);
INSERT INTO user_activity VALUES (2,"embugge@hotmail.com","sailing",2,20000,11111111111111);
INSERT INTO user_activity VALUES (3,"embugge@hotmail.com","skating",1,6800,11111111111111);
INSERT INTO user_activity VALUES (4,"bob@hotmail.com","running",2,3000,11111111111111);
INSERT INTO user_activity VALUES (5,"bob@hotmail.com","skating",3,1500,11111111111111);

INSERT INTO activity_level VALUES (1,1,"Low");
INSERT INTO activity_level VALUES (2,2,"Med");
INSERT INTO activity_level VALUES (3,3,"High");

INSERT INTO tips VALUES(1, "Pick the parking-spot the farthest away from the shop.");
INSERT INTO tips VALUES(2, "Take a detour while walking to your buss.");
INSERT INTO tips VALUES(3, "Use the stairs!");
INSERT INTO tips VALUES(4, "Pushups/situps(get up) during tv commercials.");
INSERT INTO tips VALUES(5, "Go to the store if possible.");
INSERT INTO tips VALUES(6, "Use your bike!");
INSERT INTO tips VALUES(7, "Walk to work.");