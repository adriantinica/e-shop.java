CREATE TABLE Entity (
    id int PRIMARY KEY,
    discriminator character varying (100),
    createdAt character varying (60),
    readAt character varying (60),
    updatedAt character varying (60),
    deletedAt character varying (60)
); 

CREATE TABLE DummyEntity (
    entity_id int REFERENCES Entity(id),
    testValue character varying (60),
    readAt character varying (60),
    updatedAt character varying (60),
    deletedAt character varying (60)   
); 

