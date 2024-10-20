CREATE TABLE IF NOT EXISTS users
(
    id    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name  VARCHAR(255)                            NOT NULL,
    role  VARCHAR(255)                            NOT NULL,
    email VARCHAR(255)                            NOT NULL,
    CONSTRAINT PK_USER PRIMARY KEY (id),
    CONSTRAINT UQ_USER_EMAIL UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS status
(
    id      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    status  VARCHAR(255)                            NOT NULL,
    CONSTRAINT PK_STATUS PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS resources
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    resource  VARCHAR(255)                            NOT NULL,
    CONSTRAINT PK_RESOURCES PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS projects
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    pm_id         BIGINT                                  NOT NULL,
    project_name  VARCHAR(255)                            NOT NULL,
    status        BIGINT                                  NOT NULL,
    estimation    BIGINT                                  NOT NULL,
    last_update   TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    start_project TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    end_project   TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    CONSTRAINT PK_PROJECT PRIMARY KEY (id),
    CONSTRAINT FK_PROJECT_ON_OWNER FOREIGN KEY (pm_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT UQ_OWNER_PROJECT_NAME UNIQUE(pm_id, project_name)
);

CREATE TABLE IF NOT EXISTS project_resources
(
    project_id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    resource_id  BIGINT                            NOT NULL,
    CONSTRAINT PK_PROJECT_RESOURCES PRIMARY KEY (project_id, resource_id),
    CONSTRAINT FK_CE_ON_PROJECT FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE,
    CONSTRAINT FK_CE_ON_RESOURCES FOREIGN KEY (resource_id) REFERENCES resources (id) ON DELETE CASCADE
);

