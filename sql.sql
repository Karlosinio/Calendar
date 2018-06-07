IF EXISTS (SELECT 1 FROM master.dbo.sysdatabases WHERE NAME='calendar')
DROP DATABASE calendar
GO

CREATE DATABASE calendar
GO

CREATE TABLE calendar.dbo.people
(
	id			INT,
	firstName	VARCHAR(100),
	lastName	VARCHAR(100)
);
GO

CREATE TABLE calendar.dbo.events
(
	id			INT,
	name		VARCHAR(100),
	dat			SMALLDATETIME,
	descrip		VARCHAR(400),
	place		VARCHAR(100)
);
GO

CREATE TABLE calendar.dbo.events_people
(
	event_id	INT,
	people_id	INT
);
GO

CREATE TABLE calendar.dbo.reminders
(
	event_id	INT,
	dat			SMALLDATETIME
);
GO

USE calendar
GO

SELECT * FROM people
SELECT * FROM events
SELECT * FROM events_people
SELECT * FROM reminders