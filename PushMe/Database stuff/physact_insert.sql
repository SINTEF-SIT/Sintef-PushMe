DELETE FROM user_activity;
DELETE FROM user_steps;
DELETE FROM user;
DELETE FROM activity;
DELETE FROM activity_level;
DELETE FROM tips;

INSERT INTO activity VALUES ("running",22,33,44);
INSERT INTO activity VALUES ("skating",11,22,33);
INSERT INTO activity VALUES ("sailing",10,20,30);

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