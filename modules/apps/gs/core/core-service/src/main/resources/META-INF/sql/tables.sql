create table Core_Client (
	clientId LONG not null primary key,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	parentClientId LONG,
	name VARCHAR(75) null,
	description STRING null,
	dashboardUrl VARCHAR(75) null,
	logoId LONG,
	logoUrl VARCHAR(75) null,
	websiteUrl VARCHAR(75) null,
	addressId LONG,
	active_ BOOLEAN
);

create table Core_Engagement (
	engagementId LONG not null primary key,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	calendarBookingId LONG,
	clientId LONG,
	projectId LONG,
	title VARCHAR(75) null,
	description STRING null,
	leadUserId LONG,
	leadName VARCHAR(75) null,
	typeCategoryId LONG,
	difficultyId INTEGER,
	progressStatusId INTEGER,
	approvalStatusId INTEGER
);

create table Core_MemberOf (
	memberOfId LONG not null primary key,
	companyId LONG,
	createDate DATE null,
	userId LONG,
	clientId LONG,
	projectId LONG,
	engagementId LONG,
	memberRoleId INTEGER
);

create table Core_Project (
	projectId LONG not null primary key,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	clientId LONG,
	calendarId LONG,
	name VARCHAR(75) null,
	description STRING null,
	dashboardUrl VARCHAR(75) null,
	lesaProjectKey VARCHAR(75) null,
	logoId LONG,
	logoUrl VARCHAR(75) null
);

create table Core_StaffMember (
	userId LONG not null primary key,
	companyId LONG,
	employeeType INTEGER,
	employerName VARCHAR(75) null
);