DELETE FROM user_activity;
DELETE FROM user_steps;
DELETE FROM user_module;
DELETE FROM survey_answer;
DELETE FROM survey;
DELETE FROM goal;
DELETE FROM trophy;
DELETE FROM module;
DELETE FROM user;
DELETE FROM activity;
DELETE FROM activity_level;
DELETE FROM tips;
DELETE FROM tips;

INSERT INTO activity VALUES ("Aerobics",115,145,190);
INSERT INTO activity VALUES ("Badminton",100,150,170);
INSERT INTO activity VALUES ("Baseball",0,125,0);
INSERT INTO activity VALUES ("Basketball",130,195,220);
INSERT INTO activity VALUES ("Billiard",0,60,0);
INSERT INTO activity VALUES ("Bosu ball",0,120,0);
INSERT INTO activity VALUES ("Bowling",0,55,0);
INSERT INTO activity VALUES ("Boxing, competitive",0,213,0);
INSERT INTO activity VALUES ("Boxing, training",0,135,0);
INSERT INTO activity VALUES ("Canoeing",0,70,0);
INSERT INTO activity VALUES ("Climbing",0,264,0);
INSERT INTO activity VALUES ("Corebar",0,140,0);
INSERT INTO activity VALUES ("Corepulse",0,155,0);
INSERT INTO activity VALUES ("Coretraining",0,100,0);
INSERT INTO activity VALUES ("Crossfit",0,220,0);
INSERT INTO activity VALUES ("Cycling",93,160,200);
INSERT INTO activity VALUES ("Dancing",55,100,175);
INSERT INTO activity VALUES ("Dancestep",0,110,0);
INSERT INTO activity VALUES ("Dart",0,60,0);
INSERT INTO activity VALUES ("Elliptical trainer",130,200,270);
INSERT INTO activity VALUES ("Fencing",0,150,0);
INSERT INTO activity VALUES ("Floorball",100,170,200);
INSERT INTO activity VALUES ("Freediving",0,190,0);
INSERT INTO activity VALUES ("Frisbee",0,118,0);
INSERT INTO activity VALUES ("Golf, with cart",0,70,0);
INSERT INTO activity VALUES ("Golf, walking",0,100,0);
INSERT INTO activity VALUES ("Gymnastics",0,150,0);
INSERT INTO activity VALUES ("Handball",0,230,0);
INSERT INTO activity VALUES ("Hiking",155,180,235);
INSERT INTO activity VALUES ("Hula hoop",100,130,170);
INSERT INTO activity VALUES ("In-line skating",0,84,0);
INSERT INTO activity VALUES ("Icehockey",100,140,240);
INSERT INTO activity VALUES ("Ice skating",95,135,170);
INSERT INTO activity VALUES ("Judo",0,185,0);
INSERT INTO activity VALUES ("Lacrosse",0,200,0);
INSERT INTO activity VALUES ("Martial arts, various",120,140,240);
INSERT INTO activity VALUES ("Movement-training",0,110,0);
INSERT INTO activity VALUES ("Nordic walking",100,120,145);
INSERT INTO activity VALUES ("Orienteering",0,151,0);
INSERT INTO activity VALUES ("Pilates",0,70,0);
INSERT INTO activity VALUES ("Ping Pong",0,90,0);
INSERT INTO activity VALUES ("Racquetball",138,170,205);
INSERT INTO activity VALUES ("Rafting",0,125,0);
INSERT INTO activity VALUES ("Roller Skating",0,173,0);
INSERT INTO activity VALUES ("Rowing",75,150,289);
INSERT INTO activity VALUES ("Rugby",0,255,0);
INSERT INTO activity VALUES ("Running",185,230,305);
INSERT INTO activity VALUES ("Sailing",0,100,0);
INSERT INTO activity VALUES ("Sex",0,66,0);
INSERT INTO activity VALUES ("Scuba diving",0,190,0);
INSERT INTO activity VALUES ("Skiing, Cross country",110,220,330);
INSERT INTO activity VALUES ("Skiing, Downhill",60,90,150);
INSERT INTO activity VALUES ("Skiing, Roller",100,120,175);
INSERT INTO activity VALUES ("Skiing, Water",0,160,0);
INSERT INTO activity VALUES ("Skipping rope",100,200,285);
INSERT INTO activity VALUES ("Skydiving",0,90,0);
INSERT INTO activity VALUES ("Snowboarding",0,150,0);
INSERT INTO activity VALUES ("Snowshoeing",0,156,0);
INSERT INTO activity VALUES ("Soccer",144,166,195);
INSERT INTO activity VALUES ("Spinning",120,160,220);
INSERT INTO activity VALUES ("Squash",0,205,0);
INSERT INTO activity VALUES ("Stair climbing machine",0,160,0);
INSERT INTO activity VALUES ("Surfing",0,150,0);
INSERT INTO activity VALUES ("Swimming",120,225,290);
INSERT INTO activity VALUES ("Swimming, water aerobics",0,100,0);
INSERT INTO activity VALUES ("Swimming, treading water",0,98,0);
INSERT INTO activity VALUES ("Tai chi",0,8,0);
INSERT INTO activity VALUES ("Tennis, doubles",0,110,0);
INSERT INTO activity VALUES ("Tennis, singles",0,160,0);
INSERT INTO activity VALUES ("Volleyball, Beach",100,200,225);
INSERT INTO activity VALUES ("Volleyball, indoor",70,150,180);
INSERT INTO activity VALUES ("Weight lifiting",125,190,255);
INSERT INTO activity VALUES ("Windsurfing",0,100,0);
INSERT INTO activity VALUES ("Wrestling",120,150,300);
INSERT INTO activity VALUES ("Yoga",0,100,0);
INSERT INTO activity VALUES ("Zumba",0,130,0);

INSERT INTO activity_level VALUES (1,1,"Inactive (Mostly sitting)");
INSERT INTO activity_level VALUES (2,2,"Minimum Activity (Doing the bare minimum of activity each day).");
INSERT INTO activity_level VALUES (3,3,"The Walker (A lot of walking but no training sessions).");
INSERT INTO activity_level VALUES (4,4,"Active Lifestyle (1-2 training sessions a week).");
INSERT INTO activity_level VALUES (5,5,"The Athlete (3-5 training sessions each week and active the rest of the day).");

INSERT INTO user VALUES ("test@test", "123", "Test", 20000101010101, 123, 123, "male", "Inactive (Mostly sitting)", false);
INSERT INTO user VALUES ("user@user", "123", "User", 20000102010101, 321, 321, "female", "Inactive (Mostly sitting)", false);
INSERT INTO user VALUES ("a@bruker", "123", "Mr. A", 20000102010101, 321, 321, "female", "Inactive (Mostly sitting)", false);
INSERT INTO user VALUES ("b@bruker", "123", "Mr. B", 20000102010101, 321, 321, "female", "Inactive (Mostly sitting)", false);
INSERT INTO user VALUES ("c@bruker", "123", "Mr. C", 20000102010101, 321, 321, "female", "Inactive (Mostly sitting)", false);
INSERT INTO user VALUES ("d@bruker", "123", "Mr. D", 20000102010101, 321, 321, "female", "Inactive (Mostly sitting)", false);
INSERT INTO user VALUES ("e@bruker", "123", "Mr. E", 20000102010101, 321, 321, "female", "Inactive (Mostly sitting)", false);
INSERT INTO user VALUES ("f@bruker", "123", "Mr. F", 20000102010101, 321, 321, "female", "Inactive (Mostly sitting)", false);
INSERT INTO user VALUES ("g@bruker", "123", "Mr. G", 20000102010101, 321, 321, "female", "The Walker (A lot of walking but no training sessions).", false);
INSERT INTO user VALUES ("h@bruker", "123", "Mr. H", 20000102010101, 321, 321, "female", "The Walker (A lot of walking but no training sessions).", false);
INSERT INTO user VALUES ("i@bruker", "123", "Mr. I", 20000102010101, 321, 321, "female", "The Walker (A lot of walking but no training sessions).", false);
INSERT INTO user VALUES ("j@bruker", "123", "Mr. J", 20000102010101, 321, 321, "female", "The Walker (A lot of walking but no training sessions).", false);
INSERT INTO user VALUES ("k@bruker", "123", "Mr. K", 20000102010101, 321, 321, "female", "Minimum Activity (Doing the bare minimum of activity each day).", false);
INSERT INTO user VALUES ("l@bruker", "123", "Mr. L", 20000102010101, 321, 321, "female", "Minimum Activity (Doing the bare minimum of activity each day).", false);
INSERT INTO user VALUES ("m@bruker", "123", "Mr. M", 20000102010101, 321, 321, "female", "Minimum Activity (Doing the bare minimum of activity each day).", false);
INSERT INTO user VALUES ("n@bruker", "123", "Mr. N", 20000102010101, 321, 321, "female", "The Athlete (3-5 training sessions each week and active the rest of the day).", false);
INSERT INTO user VALUES ("o@bruker", "123", "Mr. O", 20000102010101, 321, 321, "female", "The Athlete (3-5 training sessions each week and active the rest of the day).", false);
INSERT INTO user VALUES ("p@bruker", "123", "Mr. P", 20000102010101, 321, 321, "female", "The Athlete (3-5 training sessions each week and active the rest of the day).", false);
INSERT INTO user VALUES ("q@bruker", "123", "Mr. Q", 20000102010101, 321, 321, "female", "The Athlete (3-5 training sessions each week and active the rest of the day).", false);
INSERT INTO user VALUES ("r@bruker", "123", "Mr. R", 20000102010101, 321, 321, "female", "The Athlete (3-5 training sessions each week and active the rest of the day).", false);
INSERT INTO user VALUES ("s@bruker", "123", "Mr. S", 20000102010101, 321, 321, "female", "Active Lifestyle (1-2 training sessions a week).", false);
INSERT INTO user VALUES ("t@bruker", "123", "Mr. T", 20000102010101, 321, 321, "female", "Active Lifestyle (1-2 training sessions a week).", false);
INSERT INTO user VALUES ("admin@admin", "123", "Admin", null, 0, 0, null, null, true);

INSERT INTO trophy VALUES (1, 1, "Week trophy: 12.04.2015", 20150428000000, "test@test");
INSERT INTO trophy VALUES (2, 2, "Month trophy: 12.04.2015", 20150228000000, "test@test");
#INSERT INTO trophy VALUES (3, 2, "Month trophy: 12.04.2015", 20150428000000, "user@user");

INSERT INTO survey VALUES(1, 'System survey', 'Do you like the system?', 'Yes', 'No', 
'I don\'t know yet', 'Are you sure?', 'Yes', 'No', '*sigh*', 'From 1 to 3 what would you rate PushMe?', 
'1', '2', '9001', 'Do you like the color theme?', 'Yes', 'No', 'I\'m blind.', 
'Would you like more surveys in the future?', 'No', 'No.', 'Noooooooooo!');



INSERT INTO module VALUES (1,"Dashboard","Navigation button to the dashboard page");
INSERT INTO module VALUES (2,"Useractivity","The navigation button to the activity registration page");
INSERT INTO module VALUES (3,"Profile", "The navigation button to the profile page");
INSERT INTO module VALUES (4,"Statistics","The navigation button to the statistics page");

INSERT INTO tips VALUES(1, "Pick the parking-spot the farthest away from the shop.", "Statistically, using the first free parking lot is
less time consuming the looking for a closer lot. And it's better for you!");
INSERT INTO tips VALUES(2, "Take a detour while walking to your buss.", "If you have some free time, why not walk around the neighbourhood?");
INSERT INTO tips VALUES(3, "Use the stairs!", "Stairs is a good way to tone the ass aswell as the legs.");
INSERT INTO tips VALUES(4, "Pushups/situps(get up) during tv commercials.", "Fun fact: The worlds best female arm wrestler(2012)
stated this was the trick who got her that far.");
INSERT INTO tips VALUES(5, "Walk to the store if possible.", "Save the enviroment by using your own meat machine!");
INSERT INTO tips VALUES(6, "Use your bike!", "If you live within five or ten miles of where you work, 
take the opportunity to bike instead of drive to work. A thirty-minute bike ride will leave you feeling energized.");
INSERT INTO tips VALUES(8, "Go visit friends/family.", "Surprise Your dear ones with a visit! A small detour while excersicing is a good
way to make better connection between you and your loved ones(PS. not recomended if you have been sweating alot).");
INSERT INTO tips VALUES(9, "Exercise with a friend.", "Another reason you may feel the need for a personal 
trainer is to be motivated to workout. Find a gym buddy instead and make a pact to ensure workouts will be completed properly.");
INSERT INTO tips VALUES(10, "Vigorous housekeeping.", "House chores like sweeping, mopping, gardening and washing the car can be 
great calorie burners that help you complete the tasks you probably needed to do anyway.");
INSERT INTO tips VALUES(11, "Body weight exercises.", "Some of the best muscle-toning workouts rely on only using your body weight. 
You can do exercises like pull-ups, push-ups, crunches, squats and lunges without any specialized equipment — or even stepping a foot 
outside of your bedroom.");
INSERT INTO tips VALUES(12, "Walk/bike to work.", "For many people, going to the gym just isn’t much fun, which makes it challenging to 
sustain as a lifestyle choice. Find a sport or an active leisure pursuit that you can enjoy. You’ll find it easier to commit.");
INSERT INTO tips VALUES(13, "Find something you enjoy.", "Going to the gym is for many a pain. Exercising should be fun, make sure it is!");
