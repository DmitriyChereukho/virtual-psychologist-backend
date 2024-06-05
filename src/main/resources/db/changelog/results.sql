CREATE TABLE results (
    id UUID PRIMARY KEY not null,
    problem_id UUID NOT NULL,
    created_at DATE,
    duration VARCHAR(255),
    nodes VARCHAR(255),
    user_id UUID not null,
    FOREIGN KEY (user_id) REFERENCES users(id)
);