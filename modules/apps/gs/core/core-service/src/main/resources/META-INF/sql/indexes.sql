create index IX_BE3DFDF5 on Core_Client (parentClientId);

create index IX_66D122BF on Core_Engagement (projectId);

create index IX_9EED8EE1 on Core_MemberOf (clientId, projectId, engagementId);
create index IX_C2838367 on Core_MemberOf (userId, clientId, projectId, engagementId);

create index IX_7E76ECF9 on Core_Project (clientId);